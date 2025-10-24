package net.Aziuria.aziuriamod.fog;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.pathfinder.PathfindingContext;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

public class FogCreeperSpawner {

    private static final int SPAWN_INTERVAL_TICKS = 200; // every 10 seconds
    private static final int MAX_GLOBAL_ASSASSINS = 2;   // global cap
    private static int currentAssassinCount = 0;         // global counter
    private int tickCounter = 0;

    // Noise tracking
    public static final Map<BlockPos, Long> noisePositions = new ConcurrentHashMap<>();
    private final WeakHashMap<Creeper, FogBehaviorController> fogCreepers = new WeakHashMap<>();

    @SubscribeEvent
    public void onLevelTick(LevelTickEvent.Post event) {
        if (!(event.getLevel() instanceof ServerLevel serverLevel)) return;
        tick(serverLevel);
    }

    public void tick(ServerLevel level) {
        if (!FogEventManager.isEvilFogActive()) {
            fogCreepers.keySet().forEach(Mob::discard);
            fogCreepers.clear();
            currentAssassinCount = 0;
            noisePositions.clear();
            return;
        }

        tickCounter++;
        if (tickCounter >= SPAWN_INTERVAL_TICKS) {
            tickCounter = 0;

            long totalAssassins = level.getEntitiesOfClass(
                    Creeper.class,
                    level.getWorldBorder().getCollisionShape().bounds(),
                    e -> e.getTags().contains("fog_assassin")
            ).size();

            if (totalAssassins < MAX_GLOBAL_ASSASSINS) {
                spawnFogCreeper(level);
            }
        }

        fogCreepers.keySet().removeIf(creeper -> {
            if (creeper.isRemoved()) {
                currentAssassinCount = Math.max(0, currentAssassinCount - 1);
                return true;
            }
            return false;
        });

        fogCreepers.forEach((creeper, behavior) -> behavior.tick());
    }

    private void spawnFogCreeper(ServerLevel level) {
        if (currentAssassinCount >= MAX_GLOBAL_ASSASSINS) return;

        var random = level.random;
        var player = level.getRandomPlayer();
        if (player == null) return;

        double angle = random.nextDouble() * Math.PI * 2;
        double distance = 15 + random.nextDouble() * 10;
        double spawnX = player.getX() + Math.cos(angle) * distance;
        double spawnZ = player.getZ() + Math.sin(angle) * distance;
        double spawnY = player.getY();

        Creeper creeper = new Creeper(EntityType.CREEPER, level);
        creeper.moveTo(spawnX, spawnY, spawnZ, random.nextFloat() * 360F, 0F);
        creeper.addTag("fog_assassin");
        creeper.setPersistenceRequired();

        // Target goals for mobs
        creeper.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(creeper, IronGolem.class, true));
        creeper.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(creeper, Villager.class, true));

        level.addFreshEntity(creeper);
        fogCreepers.put(creeper, new FogBehaviorController(creeper));
        currentAssassinCount++;
    }

    private static class FogBehaviorController {
        private static final double AMBUSH_DISTANCE = 2.0;
        private final Creeper creeper;
        private boolean isSneaking = true;
        private boolean isFuseStarted = false;
        private Player targetPlayer;
        private StealthPathNavigation stealthNavigation;

        public FogBehaviorController(Creeper creeper) {
            this.creeper = creeper;

            // Ladder climbing patch
            PathNavigation nav = creeper.getNavigation();
            if (nav instanceof GroundPathNavigation groundNav) {
                groundNav.setCanFloat(true);
            }
        }

        public void tick() {
            if (!FogEventManager.isEvilFogActive()) {
                creeper.discard();
                return;
            }

            targetPlayer = null;

            // --- Priority System Start ---
            Player closestPlayer = creeper.level().getNearestPlayer(creeper, 40);
            Player zombiePlayer = null;
            double zombieDistance = Double.MAX_VALUE;

            // Zombies alert
            for (Zombie zombie : creeper.level().getEntitiesOfClass(Zombie.class, creeper.getBoundingBox().inflate(20))) {
                if (zombie.getTarget() instanceof Player p) {
                    double dist = creeper.distanceToSqr(p);
                    if (dist < zombieDistance) {
                        zombiePlayer = p;
                        zombieDistance = dist;
                    }
                }
            }

            // --- PATCHED LINE ---
            // Ignore Creative/Spectator players
            if (closestPlayer != null && !closestPlayer.isCreative() && !closestPlayer.isSpectator()
                    && (closestPlayer.distanceToSqr(creeper) <= zombieDistance)) {
                targetPlayer = closestPlayer;
            } else if (zombiePlayer != null) {
                targetPlayer = zombiePlayer;
            }
            // --- End Patch ---

            // Noise investigation if no player
            if (targetPlayer == null && !noisePositions.isEmpty()) {
                long now = creeper.level().getGameTime();
                noisePositions.entrySet().removeIf(e -> now - e.getValue() > 200);

                BlockPos closestNoise = null;
                double closestNoiseDist = Double.MAX_VALUE;
                for (BlockPos noisePos : noisePositions.keySet()) {
                    double dist = creeper.distanceToSqr(noisePos.getX(), noisePos.getY(), noisePos.getZ());
                    if (dist < closestNoiseDist) {
                        closestNoise = noisePos;
                        closestNoiseDist = dist;
                    }
                }

                if (closestNoise != null) {
                    getNavigation().moveTo(closestNoise.getX() + 0.5, closestNoise.getY(), closestNoise.getZ() + 0.5, 1.0);
                    return;
                }
            }

            // Iron Golems / Villagers fallback
            if (targetPlayer == null) {
                Mob closestEntity = null;
                double closestDist = Double.MAX_VALUE;

                for (IronGolem golem : creeper.level().getEntitiesOfClass(IronGolem.class, creeper.getBoundingBox().inflate(20))) {
                    double dist = creeper.distanceToSqr(golem);
                    if (dist < closestDist) {
                        closestEntity = golem;
                        closestDist = dist;
                    }
                }

                for (Villager villager : creeper.level().getEntitiesOfClass(Villager.class, creeper.getBoundingBox().inflate(20))) {
                    double dist = creeper.distanceToSqr(villager);
                    if (dist < closestDist) {
                        closestEntity = villager;
                        closestDist = dist;
                    }
                }

                if (closestEntity != null) {
                    creeper.getNavigation().moveTo(closestEntity, 1.0);
                    return;
                }
            }
            // --- Priority System End ---

            if (targetPlayer != null) {
                if (isSneaking) handleSneakingBehavior();
                else creeper.getNavigation().moveTo(targetPlayer, 1.0);
            }
        }

        private PathNavigation getNavigation() {
            if (isSneaking && targetPlayer != null) {
                if (stealthNavigation == null) {
                    stealthNavigation = new StealthPathNavigation(creeper, (ServerLevel) creeper.level(), targetPlayer);
                }
                return stealthNavigation;
            }
            return creeper.getNavigation();
        }

        private void handleSneakingBehavior() {
            if (targetPlayer == null) return;

            if (isPlayerSeeingCreeper(targetPlayer)) {
                isSneaking = false;
                return;
            }

            Vec3 behindPos = getBehindPlayerPosition(targetPlayer);
            double distToBehind = creeper.position().distanceTo(behindPos);

            if (distToBehind > AMBUSH_DISTANCE) {
                getNavigation().moveTo(behindPos.x, behindPos.y, behindPos.z, 1.0);
            } else {
                followPlayerSilently(targetPlayer);
                if (isPlayerFacingCreeper(targetPlayer)) startFuse();
            }
        }

        private boolean isPlayerSeeingCreeper(Player player) {
            Vec3 playerEye = player.getEyePosition(1.0F);
            Vec3 creeperPos = creeper.position().add(0, creeper.getEyeHeight(), 0);

            double maxViewDistance = 30.0;
            if (playerEye.distanceTo(creeperPos) > maxViewDistance) return false;

            Vec3 lookVec = player.getLookAngle();
            Vec3 dirToCreeper = creeperPos.subtract(playerEye).normalize();
            double dot = lookVec.dot(dirToCreeper);
            double fovCos = Math.cos(Math.toRadians(60));
            if (dot < fovCos) return false;

            HitResult ray = player.level().clip(new ClipContext(playerEye, creeperPos,
                    ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));
            return ray.getType() == HitResult.Type.MISS;
        }

        private Vec3 getBehindPlayerPosition(Player player) {
            Vec3 playerPos = player.position();
            Vec3 lookVec = player.getLookAngle().normalize();
            return playerPos.subtract(lookVec.scale(AMBUSH_DISTANCE));
        }

        private void followPlayerSilently(Player player) {
            Vec3 targetPos = player.position();
            if (creeper.position().distanceTo(targetPos) > AMBUSH_DISTANCE) {
                getNavigation().moveTo(targetPos.x, targetPos.y, targetPos.z, 1.0);
            } else getNavigation().stop();
        }

        private boolean isPlayerFacingCreeper(Player player) {
            Vec3 playerEye = player.getEyePosition(1.0F);
            Vec3 creeperPos = creeper.position().add(0, creeper.getEyeHeight(), 0);
            Vec3 dirToCreeper = creeperPos.subtract(playerEye).normalize();
            Vec3 playerLook = player.getLookAngle();
            double dot = playerLook.dot(dirToCreeper);
            return dot > Math.cos(Math.toRadians(30));
        }

        private void startFuse() {
            if (!isFuseStarted) {
                isFuseStarted = true;
                creeper.ignite();
            }
        }

        // --- Stealth Navigation ---
        private static class StealthPathNavigation extends GroundPathNavigation {
            private final Player player;

            public StealthPathNavigation(Creeper mob, ServerLevel level, Player player) {
                super(mob, level);
                this.player = player;
            }

            @Override
            protected PathFinder createPathFinder(int maxDistance) {
                return new PathFinder(new StealthNodeEvaluator(player), 200);
            }
        }

        private static class StealthNodeEvaluator extends WalkNodeEvaluator {
            private final Player player;

            public StealthNodeEvaluator(Player player) {
                this.player = player;
            }

            @Override
            public PathType getPathType(PathfindingContext context, int x, int y, int z) {
                BlockPos pos = new BlockPos(x, y, z);
                if (isVisibleToPlayer(pos)) return PathType.BLOCKED;
                return super.getPathType(context, x, y, z);
            }

            private boolean isVisibleToPlayer(BlockPos pos) {
                Vec3 playerEye = player.getEyePosition(1.0F);
                Vec3 target = Vec3.atCenterOf(pos);

                HitResult result = player.level().clip(new ClipContext(playerEye, target,
                        ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));

                return result.getType() == HitResult.Type.MISS;
            }
        }
    }
}