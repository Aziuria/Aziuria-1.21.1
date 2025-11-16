package net.Aziuria.aziuriamod.fog;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.entity.player.PlayerContainerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FogZombieSpawner {

    private static final RandomSource random = RandomSource.create();
    private static final Map<UUID, Integer> zombieEatingTicks = new HashMap<>();
    private static final Map<ServerLevel, Integer> tickCooldowns = new HashMap<>();

    // Tracks noise locations and their remaining investigation time (ticks)
    private static final Map<BlockPos, Integer> noisePositions = new HashMap<>();
    private static final int INVESTIGATION_TIME_TICKS = 100;  // about 5 seconds

    // Max herd size for grouped zombie behavior
    private static final int MAX_HERD_SIZE = 5;

    @SubscribeEvent
    public void onWorldTick(LevelTickEvent.Post event) {
        if (!(event.getLevel() instanceof ServerLevel level)) return;

        if (!FogEventManager.isEvilFogActive()) {
            // Remove all zombies spawned by fog once fog ends
            List<Zombie> allZombies = level.getEntitiesOfClass(Zombie.class, level.getWorldBorder().getCollisionShape().bounds());
            for (Zombie zombie : allZombies) {
                if (zombie.getPersistentData().getBoolean("SpawnedByFog")) {
                    zombie.remove(Entity.RemovalReason.DISCARDED);
                }
            }
            noisePositions.clear(); // Clear noise map
            return;
        }

        if (level.players().isEmpty()) return;

        int cooldown = tickCooldowns.getOrDefault(level, 0);        // <--
        if (cooldown > 0) {                                          // <--
            tickCooldowns.put(level, cooldown - 1);                  // <--
            return;                                                  // <--
        }
        tickCooldowns.put(level, 200);

        // 30% chance to spawn zombies
        if (random.nextInt(100) < 30) {
            int zombiesToSpawn = 4 + random.nextInt(7); // 4 to 10

            for (int i = 0; i < zombiesToSpawn; i++) {
                Player player = level.players().get(random.nextInt(level.players().size()));

                BlockPos pos = player.blockPosition().offset(
                        random.nextInt(101) - 50, // -50 to +50
                        random.nextInt(13) - 6,   // -6 to +6
                        random.nextInt(101) - 50  // -50 to +50
                );

                if (level.getWorldBorder().isWithinBounds(pos)
                        && level.isEmptyBlock(pos)
                        && level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP)) {
                    Zombie zombie = EntityType.ZOMBIE.create(level);
                    if (zombie != null) {
                        zombie.getPersistentData().putBoolean("SpawnedByFog", true);
                        zombie.moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0, 0);
                        zombie.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(40.0D);
                        zombie.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20 * 60 * 3, 1));
                        zombie.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 20 * 60 * 3, 0));


                        zombie.goalSelector.addGoal(0, new FloatGoal(zombie));  // vanilla
                        zombie.goalSelector.addGoal(1, new TargetRottenFleshGoal(zombie, 1.2D)); // our distraction


                        // NEW: 10% chance to spawn as baby zombie
                        if (random.nextFloat() < 0.2f) {  // 20% chance baby zombie
                            zombie.setBaby(true);
                        }
                        // Initial targeting goals for animals and Iron Golem

                        zombie.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(zombie, IronGolem.class, true));
                        zombie.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(zombie, Villager.class, true));
                        zombie.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(zombie, Animal.class, 10, true, false,
                                animal -> animal instanceof Sheep || animal instanceof Cow || animal instanceof Pig || animal instanceof Chicken));

                        // === LADDER CLIMBING PATCH ===
                        PathNavigation nav = zombie.getNavigation();
                        if (nav instanceof GroundPathNavigation groundNav) {
                            groundNav.setCanFloat(true); // allows climbing ladders & prevents stuck paths
                        }

                        level.addFreshEntity(zombie);
                    }
                }
            }
        }

        // **FIXED NOISE POSITIONS UPDATE**
        noisePositions.entrySet().removeIf(entry -> {
            int newVal = entry.getValue() - 1;
            entry.setValue(newVal);
            return newVal <= 0;
        });
        List<Zombie> zombies = level.getEntitiesOfClass(Zombie.class, level.getWorldBorder().getCollisionShape().bounds());

        for (Zombie zombie : zombies) {
            if (!zombie.getPersistentData().getBoolean("SpawnedByFog")) continue;

            assignDynamicTarget(zombie, level);


            Entity currentTarget = zombie.getTarget();

            if (currentTarget != null && (currentTarget instanceof Player || currentTarget instanceof Villager)) {
                Vec3 targetPos = currentTarget.position();

                int flankCount = 3; // Number of flank positions around target
                double flankRadius = 4.0D; // Distance from target to flank

                // Get nearby fog zombies targeting same entity within 20 blocks
                List<Zombie> nearbyZombies = level.getEntitiesOfClass(Zombie.class, zombie.getBoundingBox().inflate(20),
                        z -> z.getPersistentData().getBoolean("SpawnedByFog") && z.getTarget() == currentTarget);

                int index = nearbyZombies.indexOf(zombie);
                if (index == -1) index = 0; // fallback if somehow not found

                // Calculate angle to evenly space zombies around target
                double angle = (2 * Math.PI / flankCount) * (index % flankCount);

                double flankX = targetPos.x + flankRadius * Math.cos(angle);
                double flankZ = targetPos.z + flankRadius * Math.sin(angle);
                double flankY = level.getHeight(Heightmap.Types.MOTION_BLOCKING, (int) flankX, (int) flankZ);

                Vec3 flankPos = new Vec3(flankX, flankY, flankZ);

                // ---------- Add path check before moving to flank position ----------
                if (zombie.position().distanceToSqr(flankPos) > 2) {
                    if (zombie.getNavigation().createPath(flankPos.x, flankPos.y, flankPos.z, 0) != null) {  // <--
                        zombie.getNavigation().moveTo(flankPos.x, flankPos.y, flankPos.z, 1.0D);              // <--
                    }
                } else {
                    if (zombie.getNavigation().createPath(currentTarget.blockPosition(), 0) != null) {     // <--
                        zombie.getNavigation().moveTo(currentTarget, 1.2D);                                // <--
                    }
                }
                // -------------------------------------------------------------------
            }

            // Herd behavior: alert nearby fog zombies to same target (max herd size)
            if (currentTarget != null) {
                List<Zombie> nearbyZombies = level.getEntitiesOfClass(Zombie.class, zombie.getBoundingBox().inflate(20));
                int herdCount = 1; // include self
                for (Zombie nearbyZombie : nearbyZombies) {
                    if (herdCount >= MAX_HERD_SIZE) break;
                    if (nearbyZombie == zombie) continue;
                    if (nearbyZombie.getPersistentData().getBoolean("SpawnedByFog")) {
                        if (nearbyZombie.getTarget() != currentTarget) {
                            nearbyZombie.setTarget((Mob) currentTarget);
                            herdCount++;
                        }
                    }
                }
            }

            // If no target, investigate noise locations nearby
            if (zombie.getTarget() == null) {
                for (Map.Entry<BlockPos, Integer> noiseEntry : noisePositions.entrySet()) {
                    BlockPos noisePos = noiseEntry.getKey();
                    double distSqr = zombie.distanceToSqr(noisePos.getX() + 0.5, noisePos.getY() + 0.5, noisePos.getZ() + 0.5);
                    if (distSqr <= 40 * 40) {
                        // Move zombie toward noise
                        if (zombie.getNavigation().createPath(noisePos.getX() + 0.5, noisePos.getY(), noisePos.getZ() + 0.5, 0) != null) {  // <--
                            zombie.getNavigation().moveTo(noisePos.getX() + 0.5, noisePos.getY(), noisePos.getZ() + 0.5, 1.0);            // <--
                        }

                        // Herd moves together toward noise
                        List<Zombie> herd = level.getEntitiesOfClass(Zombie.class, zombie.getBoundingBox().inflate(20));
                        int herdCount = 1;
                        for (Zombie herdZombie : herd) {
                            if (herdCount >= MAX_HERD_SIZE) break;
                            if (herdZombie == zombie) continue;
                            if (herdZombie.getPersistentData().getBoolean("SpawnedByFog")) {
                                herdZombie.getNavigation().moveTo(noisePos.getX() + 0.5, noisePos.getY(), noisePos.getZ() + 0.5, 1.0);
                                herdCount++;
                            }
                        }

                        break; // only investigate one noise at a time
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        if (!(event.getLevel() instanceof ServerLevel level)) return;
        if (!FogEventManager.isEvilFogActive()) return;

        BlockPos pos = event.getPos();
        noisePositions.put(pos, INVESTIGATION_TIME_TICKS);
    }

    @SubscribeEvent
    public void onProjectileImpact(ProjectileImpactEvent event) {
        if (!(event.getEntity().level() instanceof ServerLevel level)) return;
        if (!FogEventManager.isEvilFogActive()) return;

        Vec3 vec = event.getRayTraceResult().getLocation();
        BlockPos pos = new BlockPos((int) vec.x, (int) vec.y, (int) vec.z);
        noisePositions.put(pos, INVESTIGATION_TIME_TICKS);
    }

    @SubscribeEvent
    public void onPlayerJump(LivingEvent.LivingJumpEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!(player.level() instanceof ServerLevel level)) return;
        if (!FogEventManager.isEvilFogActive()) return;

        BlockPos pos = player.blockPosition();
        noisePositions.put(pos, INVESTIGATION_TIME_TICKS);
    }

    @SubscribeEvent
    public void onPlayerUseItemFinish(LivingEntityUseItemEvent.Finish event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!(player.level() instanceof ServerLevel level)) return;
        if (!FogEventManager.isEvilFogActive()) return;

        ItemStack usedItem = event.getItem();
        if (usedItem.getItem().getFoodProperties(usedItem, player) != null || usedItem.getItem() instanceof PotionItem) {
            noisePositions.put(player.blockPosition(), INVESTIGATION_TIME_TICKS);
        }
    }

    // New event handler: Player interacts with block (doors)
    @SubscribeEvent
    public void onPlayerInteractDoor(PlayerInteractEvent.RightClickBlock event) {
        if (!(event.getLevel() instanceof ServerLevel level)) return;
        if (!FogEventManager.isEvilFogActive()) return;

        if (level.getBlockState(event.getPos()).getBlock() instanceof DoorBlock) {
            noisePositions.put(event.getPos(), INVESTIGATION_TIME_TICKS);
        }
    }

    // New event handler: Player opens villager trading GUI
    @SubscribeEvent
    public void onPlayerOpenVillagerTrade(PlayerContainerEvent.Open event) {
        if (!(event.getEntity().level() instanceof ServerLevel level)) return;
        if (!FogEventManager.isEvilFogActive()) return;

        if (event.getContainer() instanceof net.minecraft.world.inventory.MerchantMenu) {
            noisePositions.put(event.getEntity().blockPosition(), INVESTIGATION_TIME_TICKS);
        }
    }

    // Finds closest target with priority: Player > IronGolem > Villager > Animals (sheep, cow, pig, chicken)
    private void assignDynamicTarget(Zombie zombie, ServerLevel level) {
        double closestDistance = Double.MAX_VALUE;
        Entity closestTarget = null;

        for (Player player : level.players()) {
            if (player.isCreative() || player.isSpectator()) continue;
            double distance = zombie.distanceToSqr(player);
            if (distance < closestDistance) {
                closestDistance = distance;
                closestTarget = player;
            }
        }

        List<IronGolem> golems = level.getEntitiesOfClass(IronGolem.class, zombie.getBoundingBox().inflate(40));
        for (IronGolem golem : golems) {
            double distance = zombie.distanceToSqr(golem);
            if (distance < closestDistance) {
                closestDistance = distance;
                closestTarget = golem;
            }
        }

        List<Villager> villagers = level.getEntitiesOfClass(Villager.class, zombie.getBoundingBox().inflate(40));
        for (Villager villager : villagers) {
            double distance = zombie.distanceToSqr(villager);
            if (distance < closestDistance) {
                closestDistance = distance;
                closestTarget = villager;
            }
        }

        List<Animal> animals = level.getEntitiesOfClass(Animal.class, zombie.getBoundingBox().inflate(40));
        for (Animal animal : animals) {
            if (animal instanceof Sheep || animal instanceof Cow || animal instanceof Pig || animal instanceof Chicken) {
                double distance = zombie.distanceToSqr(animal);
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestTarget = animal;
                }
            }
        }

        if (closestTarget != null) {
            zombie.setTarget((Mob) closestTarget);
        }
    }
}