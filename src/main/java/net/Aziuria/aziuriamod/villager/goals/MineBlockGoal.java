package net.Aziuria.aziuriamod.villager.goals;

import net.Aziuria.aziuriamod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;
import java.util.Set;

public class MineBlockGoal extends Goal {

    private final Villager villager;
    private final double speed;
    private BlockPos targetBlockPos = null;

    private static final int PROTECTION_RADIUS = 8;
    private static final int MINING_DURATION = 40;  // ticks
    private static final int MAX_STUCK_TICKS = 100; // ticks (~5 seconds)
    private static final int MAX_PATHFINDING_FAILURES = 3;
    private static final double MOVEMENT_EPSILON = 0.1;

    private int miningProgress = 0;
    private int stuckTicks = 0; // Tracks how long villager is stuck trying to reach block
    private int pathfindingFailures = 0; // Counts how many times navigation failed to start
    private Vec3 lastExactPos = null; // To check if villager moves precisely


    private int minedCount = 0;
    private int cooldownTicks = 0;
    private static final int COOLDOWN_DURATION = 100;

    private enum Phase {
        MOVING,
        MINING
    }
    private Phase phase = null;

    private static final Set<Block> ORE_BLOCKS = Set.of(
            Blocks.COAL_ORE,
            Blocks.DEEPSLATE_COAL_ORE,
            Blocks.IRON_ORE,
            Blocks.DEEPSLATE_IRON_ORE,
            Blocks.GOLD_ORE,
            Blocks.DEEPSLATE_GOLD_ORE,
            Blocks.REDSTONE_ORE,
            Blocks.DEEPSLATE_REDSTONE_ORE,
            Blocks.DIAMOND_ORE,
            Blocks.DEEPSLATE_DIAMOND_ORE,
            Blocks.EMERALD_ORE,
            Blocks.DEEPSLATE_EMERALD_ORE,
            Blocks.LAPIS_ORE,
            Blocks.DEEPSLATE_LAPIS_ORE,
            Blocks.NETHER_GOLD_ORE,
            Blocks.NETHER_QUARTZ_ORE,
            Blocks.ANCIENT_DEBRIS,
            Blocks.AMETHYST_BLOCK,
            Blocks.BUDDING_AMETHYST,
            Blocks.OBSIDIAN,
            ModBlocks.POTASSIUM_ORE.get(),
            ModBlocks.DEEPSLATE_POTASSIUM_ORE.get(),
            ModBlocks.SULPHUR_ORE.get(),
            ModBlocks.DEEPSLATE_SULPHUR_ORE.get(),
            ModBlocks.TIN_ORE.get(),
            ModBlocks.DEEPSLATE_TIN_ORE.get(),
            ModBlocks.SPINEL_ORE.get(),
            ModBlocks.DEEPSLATE_SPINEL_ORE.get()
    );

    private static final Set<Block> STONE_BLOCKS = Set.of(
            Blocks.STONE,
            Blocks.DEEPSLATE,
            Blocks.COBBLESTONE
    );

    public MineBlockGoal(Villager villager, double speed) {
        this.villager = villager;
        this.speed = speed;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (cooldownTicks > 0) return false;
        if (!villager.level().isDay() || villager.level().isRaining()) return false;
        if (!villager.isAlive() || villager.isSleeping() || villager.isBaby()) return false;

        ensureVillagerHasPickaxe(villager);
        if (!(villager.getMainHandItem().getItem() instanceof PickaxeItem)) return false;

        targetBlockPos = findOreOrStone();
        if (targetBlockPos == null) return false;

        phase = Phase.MOVING;
        miningProgress = 0;
        stuckTicks = 0;
        pathfindingFailures = 0;
        lastExactPos = null;
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        if (targetBlockPos == null) return false;
        BlockState state = villager.level().getBlockState(targetBlockPos);
        Block block = state.getBlock();
        boolean blockValid = ORE_BLOCKS.contains(block) || STONE_BLOCKS.contains(block);
        return blockValid && !isProtectedArea(targetBlockPos) && villager.isAlive();
    }

    @Override
    public void stop() {
        targetBlockPos = null;
        miningProgress = 0;
        stuckTicks = 0;
        pathfindingFailures = 0;
        lastExactPos = null;
        phase = null;
        villager.getNavigation().stop();

    }

    @Override
    public void tick() {

        if (cooldownTicks > 0) {
            cooldownTicks--;
            if (cooldownTicks == 0) {
                minedCount = 0;
            }
            return; // skip mining logic during cooldown
        }
        ensureVillagerHasPickaxe(villager);
        if (targetBlockPos == null) return;

        villager.getLookControl().setLookAt(
                targetBlockPos.getX() + 0.5,
                targetBlockPos.getY() + 0.5,
                targetBlockPos.getZ() + 0.5
        );

        switch (phase) {
            case MOVING -> {
                if (targetBlockPos == null) {
                    stop();
                    return;
                }

                if (!villager.getNavigation().isInProgress()) {
                    boolean pathStarted = villager.getNavigation().moveTo(
                            targetBlockPos.getX() + 0.5,
                            targetBlockPos.getY(),
                            targetBlockPos.getZ() + 0.5,
                            speed
                    );

                    if (!pathStarted) {
                        pathfindingFailures++;
                        if (pathfindingFailures >= MAX_PATHFINDING_FAILURES) {
                            stop();
                            return;
                        }
                    } else {
                        pathfindingFailures = 0;
                    }
                }

                Vec3 currentPos = villager.position();
                if (lastExactPos != null && currentPos.distanceToSqr(lastExactPos) < MOVEMENT_EPSILON * MOVEMENT_EPSILON) {
                    stuckTicks++;
                } else {
                    stuckTicks = 0;
                }
                lastExactPos = currentPos;

                if (stuckTicks > MAX_STUCK_TICKS) {
                    villager.getNavigation().stop();
                    // Try restarting navigation once more to get unstuck
                    boolean pathRestarted = villager.getNavigation().moveTo(
                            targetBlockPos.getX() + 0.5,
                            targetBlockPos.getY(),
                            targetBlockPos.getZ() + 0.5,
                            speed
                    );
                    if (!pathRestarted) {
                        stop();
                        return;
                    }
                    stuckTicks = 0;
                }

                // Switch to MINING phase if close enough
                if (isWithinMiningRange(targetBlockPos)) {
                    villager.getNavigation().stop();
                    miningProgress = 0;
                    phase = Phase.MINING;
                    stuckTicks = 0;
                }
            }
            case MINING -> {
                if (targetBlockPos == null) {
                    stop();
                    return;
                }

                // If too far, switch back to MOVING
                if (!isWithinMiningRange(targetBlockPos)) {
                    phase = Phase.MOVING;
                    miningProgress = 0;
                    stuckTicks = 0;
                    return;
                }

                miningProgress++;

                if (miningProgress % 5 == 0 && villager.level() instanceof ServerLevel serverLevel) {
                    BlockState blockState = serverLevel.getBlockState(targetBlockPos);
                    serverLevel.sendParticles(
                            new BlockParticleOption(ParticleTypes.BLOCK, blockState),
                            targetBlockPos.getX() + 0.5,
                            targetBlockPos.getY() + 0.5,
                            targetBlockPos.getZ() + 0.5,
                            5,
                            0.2, 0.2, 0.2,
                            0.02
                    );
                }

                if (miningProgress % 10 == 0) { // play mining sound every 10 ticks
                    Level level = villager.level();
                    BlockState state = level.getBlockState(targetBlockPos);
                    Block block = state.getBlock();
                    level.playSound(
                            null,
                            targetBlockPos,
                            block.getSoundType(state, level, targetBlockPos, villager).getHitSound(),
                            SoundSource.BLOCKS,
                            0.8F,
                            1.0F
                    );
                }

                if (miningProgress >= MINING_DURATION) {
                    mineBlock(targetBlockPos);
                    targetBlockPos = null;
                    phase = null;
                }
            }
        }
    }

    private void mineBlock(BlockPos pos) {
        Level level = villager.level();
        BlockState state = level.getBlockState(pos);
        Block block = state.getBlock();

        if (!(ORE_BLOCKS.contains(block) || STONE_BLOCKS.contains(block))) return;

        level.playSound(
                null,
                pos,
                block.getSoundType(state, level, pos, villager).getBreakSound(),
                SoundSource.BLOCKS,
                1.0F,
                1.0F
        );

        Block.getDrops(state, (ServerLevel) level, pos, null)
                .forEach(stack -> villager.getInventory().addItem(stack));

        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);

        minedCount++;
        if (minedCount >= 16) {
            cooldownTicks = COOLDOWN_DURATION;
            stop(); // Stop mining and reset target
        }

    }

    private BlockPos findOreOrStone() {
        BlockPos origin = villager.blockPosition();
        Level level = villager.level();
        int radius = 15;

        BlockPos closestPos = null;
        double closestDistSq = Double.MAX_VALUE;

        // Search for ORE blocks only from current level up to 6 blocks above
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = 0; dy <= 6; dy++) {  // vertical range limited here
                for (int dz = -radius; dz <= radius; dz++) {
                    BlockPos pos = origin.offset(dx, dy, dz);
                    BlockState state = level.getBlockState(pos);
                    Block block = state.getBlock();
                    if (ORE_BLOCKS.contains(block) && !isProtectedArea(pos)) {
                        double distSq = pos.distSqr(origin);
                        if (distSq < closestDistSq) {
                            closestDistSq = distSq;
                            closestPos = pos;
                        }
                    }
                }
            }
        }

        if (closestPos != null) return closestPos;

        // Search for STONE blocks only from current level up to 6 blocks above
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = 0; dy <= 6; dy++) {  // changed here too
                for (int dz = -radius; dz <= radius; dz++) {
                    BlockPos pos = origin.offset(dx, dy, dz);
                    BlockState state = level.getBlockState(pos);
                    Block block = state.getBlock();
                    if (STONE_BLOCKS.contains(block) && !isProtectedArea(pos)) {
                        double distSq = pos.distSqr(origin);
                        if (distSq < closestDistSq) {
                            closestDistSq = distSq;
                            closestPos = pos;
                        }
                    }
                }
            }
        }

        return closestPos;
    }

    private boolean isProtectedArea(BlockPos pos) {
        Level level = villager.level();
        return isNearWood(pos, level, PROTECTION_RADIUS) ||
                isNearBed(pos, level, PROTECTION_RADIUS) ||
                isNearWindow(pos, level, PROTECTION_RADIUS);
    }

    private boolean isNearWood(BlockPos pos, Level level, int radius) {
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -radius; dy <= radius; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    BlockPos checkPos = pos.offset(dx, dy, dz);
                    Block block = level.getBlockState(checkPos).getBlock();

                    boolean isLog = block == Blocks.OAK_LOG || block == Blocks.BIRCH_LOG || block == Blocks.SPRUCE_LOG
                            || block == Blocks.ACACIA_LOG || block == Blocks.DARK_OAK_LOG || block == Blocks.JUNGLE_LOG;

                    if (isLog) {
                        // Check nearby leaves within 2-block radius of this log
                        if (!hasNearbyLeaves(checkPos, level, 2)) {
                            // This is a log with NO leaves nearby — count as protected wood
                            return true;
                        }
                        // Else: log has leaves near it, so ignore (not protected)
                    }
                }
            }
        }
        return false;
    }

    private boolean hasNearbyLeaves(BlockPos logPos, Level level, int checkRadius) {
        for (int dx = -checkRadius; dx <= checkRadius; dx++) {
            for (int dy = -checkRadius; dy <= checkRadius; dy++) {
                for (int dz = -checkRadius; dz <= checkRadius; dz++) {
                    BlockPos checkPos = logPos.offset(dx, dy, dz);
                    Block block = level.getBlockState(checkPos).getBlock();
                    if (block == Blocks.OAK_LEAVES
                            || block == Blocks.BIRCH_LEAVES
                            || block == Blocks.SPRUCE_LEAVES
                            || block == Blocks.ACACIA_LEAVES
                            || block == Blocks.DARK_OAK_LEAVES
                            || block == Blocks.JUNGLE_LEAVES
                            || block == Blocks.MANGROVE_LEAVES
                            || block == Blocks.CHERRY_LEAVES
                            || block == ModBlocks.APPLE_LEAVES.get()  // your custom apple leaves
                    ) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isNearBed(BlockPos pos, Level level, int radius) {
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -radius; dy <= radius; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    BlockPos checkPos = pos.offset(dx, dy, dz);
                    BlockState state = level.getBlockState(checkPos);
                    if (state.getBlock() instanceof BedBlock) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isNearWindow(BlockPos pos, Level level, int radius) {
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -radius; dy <= radius; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    BlockPos checkPos = pos.offset(dx, dy, dz);
                    Block block = level.getBlockState(checkPos).getBlock();

                    if (block == Blocks.GLASS || block == Blocks.GLASS_PANE
                            || block == Blocks.TINTED_GLASS
                            // Stained glass blocks
                            || block == Blocks.WHITE_STAINED_GLASS || block == Blocks.ORANGE_STAINED_GLASS
                            || block == Blocks.MAGENTA_STAINED_GLASS || block == Blocks.LIGHT_BLUE_STAINED_GLASS
                            || block == Blocks.YELLOW_STAINED_GLASS || block == Blocks.LIME_STAINED_GLASS
                            || block == Blocks.PINK_STAINED_GLASS || block == Blocks.GRAY_STAINED_GLASS
                            || block == Blocks.LIGHT_GRAY_STAINED_GLASS || block == Blocks.CYAN_STAINED_GLASS
                            || block == Blocks.PURPLE_STAINED_GLASS || block == Blocks.BLUE_STAINED_GLASS
                            || block == Blocks.BROWN_STAINED_GLASS || block == Blocks.GREEN_STAINED_GLASS
                            || block == Blocks.RED_STAINED_GLASS || block == Blocks.BLACK_STAINED_GLASS
                            // Stained glass panes
                            || block == Blocks.WHITE_STAINED_GLASS_PANE || block == Blocks.ORANGE_STAINED_GLASS_PANE
                            || block == Blocks.MAGENTA_STAINED_GLASS_PANE || block == Blocks.LIGHT_BLUE_STAINED_GLASS_PANE
                            || block == Blocks.YELLOW_STAINED_GLASS_PANE || block == Blocks.LIME_STAINED_GLASS_PANE
                            || block == Blocks.PINK_STAINED_GLASS_PANE || block == Blocks.GRAY_STAINED_GLASS_PANE
                            || block == Blocks.LIGHT_GRAY_STAINED_GLASS_PANE || block == Blocks.CYAN_STAINED_GLASS_PANE
                            || block == Blocks.PURPLE_STAINED_GLASS_PANE || block == Blocks.BLUE_STAINED_GLASS_PANE
                            || block == Blocks.BROWN_STAINED_GLASS_PANE || block == Blocks.GREEN_STAINED_GLASS_PANE
                            || block == Blocks.RED_STAINED_GLASS_PANE || block == Blocks.BLACK_STAINED_GLASS_PANE) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static void ensureVillagerHasPickaxe(Villager villager) {
        if (!(villager.getMainHandItem().getItem() instanceof PickaxeItem)) {
            villager.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.IRON_PICKAXE));
        }
    }

    private boolean isWithinMiningRange(BlockPos target) {
        Vec3 villagerPos = villager.position();
        double horizontalDistanceSq =
                Math.pow(villagerPos.x - (target.getX() + 0.5), 2) +
                        Math.pow(villagerPos.z - (target.getZ() + 0.5), 2);
        double verticalDistance = Math.abs(villagerPos.y - (target.getY() + 0.5));
        return horizontalDistanceSq <= 2.25 && verticalDistance <= 6.0;
    }
}