package net.Aziuria.aziuriamod.villager.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;

import java.util.*;

public class CutTreeGoal extends Goal {

    private final Villager villager;
    private final double speed;
    private BlockPos targetLogPos = null;
    private BlockPos originalLogPos = null; // Track original log during leaves clearing

    private Deque<BlockPos> logQueue = new LinkedList<>();
    private Set<BlockPos> visitedLogs = new HashSet<>();

    private static final int CHOP_DURATION = 40; // ticks

    private int chopProgress = 0;

    // Navigation retry variables
    private int navigationRetry = 0;
    private static final int MAX_NAV_RETRY = 40; // 2 seconds @ 20 TPS

    // Cooldown fields
    private int cooldownTicks = 0;
    private static final int COOLDOWN_DURATION = 20 * 5; // 5 seconds cooldown (20 ticks per second)

    private enum Phase {
        MOVING,
        CHOPPING
    }

    private Phase phase = null;

    public CutTreeGoal(Villager villager, double speed) {
        this.villager = villager;
        this.speed = speed;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (cooldownTicks > 0) return false; // Villager is cooling down

        if (!villager.level().isDay() || villager.level().isRaining()) return false;
        if (!villager.isAlive() || villager.isSleeping() || villager.isBaby()) return false;

        ensureVillagerHasAxe(villager);
        if (!(villager.getMainHandItem().getItem() instanceof AxeItem)) return false;

        originalLogPos = findLog();
        if (originalLogPos == null) return false;

        logQueue.clear();                              // ← Clear queue
        visitedLogs.clear();                           // ← Clear visited set
        logQueue.add(originalLogPos);                  // ← Add initial log pos to queue
        visitedLogs.add(originalLogPos);

        targetLogPos = originalLogPos;
        phase = Phase.MOVING;
        chopProgress = 0;
        navigationRetry = 0;
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        if (!villager.isAlive() || villager.isSleeping() || villager.isBaby()) return false;
        if (targetLogPos == null) return false;

        Level level = villager.level();
        BlockState targetState = level.getBlockState(targetLogPos);

        return switch (phase) {
            case MOVING -> true; // continue while moving
            case CHOPPING -> targetState.getBlock() instanceof RotatedPillarBlock;
            default -> false;
        };
    }

    @Override
    public void stop() {
        targetLogPos = null;
        originalLogPos = null;
        chopProgress = 0;
        phase = null;
        navigationRetry = 0;
        villager.getNavigation().stop();
    }

    @Override
    public void tick() {
        // Handle cooldown countdown first
        if (cooldownTicks > 0) {
            cooldownTicks--;
            return;  // Don't do anything during cooldown
        }

        ensureVillagerHasAxe(villager);

        if (targetLogPos == null || originalLogPos == null) return;

        villager.getLookControl().setLookAt(
                targetLogPos.getX() + 0.5,
                targetLogPos.getY() + 0.5,
                targetLogPos.getZ() + 0.5
        );

        switch (phase) {
            case MOVING -> {
                // If navigation isn't running or path is stale, try to move again
                if (!villager.getNavigation().isInProgress() || villager.getNavigation().isDone()) {
                    boolean pathStarted = villager.getNavigation().moveTo(
                            targetLogPos.getX() + 0.5,
                            targetLogPos.getY(),
                            targetLogPos.getZ() + 0.5,
                            speed
                    );
                    if (!pathStarted) {
                        navigationRetry++;
                        if (navigationRetry > MAX_NAV_RETRY) {
                            stop();
                            return;
                        }
                    } else {
                        navigationRetry = 0; // reset retries if navigation started
                    }
                } else {
                    navigationRetry = 0; // navigation is working
                }

                // ⇨ UPDATED proximity check using helper
                if (isWithinChopRange(villager, targetLogPos)) {
                    navigationRetry = 0; // Reset retry once close
                    villager.getNavigation().stop();
                    chopProgress = 0;
                    phase = Phase.CHOPPING;
                    targetLogPos = originalLogPos; // ensure target is log
                }
            }

            case CHOPPING -> {
                // ⇨ UPDATED proximity check using helper
                if (!isWithinChopRange(villager, targetLogPos)) {
                    phase = Phase.MOVING;
                    chopProgress = 0;
                    return;
                }

                chopProgress++;

                if (chopProgress % 5 == 0 && villager.level() instanceof ServerLevel serverLevel) {
                    BlockState blockState = serverLevel.getBlockState(targetLogPos);
                    serverLevel.sendParticles(
                            new BlockParticleOption(ParticleTypes.BLOCK, blockState),
                            targetLogPos.getX() + 0.5,
                            targetLogPos.getY() + 0.5,
                            targetLogPos.getZ() + 0.5,
                            5,
                            0.2, 0.2, 0.2,
                            0.02
                    );
                }

                if (chopProgress % 10 == 0) {
                    Level level = villager.level();
                    BlockState state = level.getBlockState(targetLogPos);
                    Block block = state.getBlock();
                    level.playSound(
                            null,
                            targetLogPos,
                            block.getSoundType(state, level, targetLogPos, villager).getHitSound(),
                            SoundSource.BLOCKS,
                            0.8F,
                            1.0F
                    );
                }

                if (chopProgress >= CHOP_DURATION) {
                    chopLog(targetLogPos);

                    logQueue.pollFirst();
                    addConnectedLogs(targetLogPos);

                    if (!logQueue.isEmpty()) {
                        targetLogPos = logQueue.peekFirst();
                        originalLogPos = targetLogPos;
                        phase = Phase.MOVING;
                        chopProgress = 0;
                        navigationRetry = 0;
                    } else {
                        // Check up to 3 blocks above for additional logs
                        boolean foundAboveLog = false;
                        for (int yOffset = 1; yOffset <= 3; yOffset++) {
                            BlockPos posAbove = targetLogPos.above(yOffset);
                            BlockState stateAbove = villager.level().getBlockState(posAbove);
                            if (stateAbove.getBlock() instanceof RotatedPillarBlock && !visitedLogs.contains(posAbove)) {
                                logQueue.addFirst(posAbove);
                                visitedLogs.add(posAbove);
                                targetLogPos = logQueue.peekFirst();
                                originalLogPos = targetLogPos;
                                phase = Phase.MOVING;
                                chopProgress = 0;
                                navigationRetry = 0;
                                foundAboveLog = true;
                                break;
                            }
                        }
                        if (!foundAboveLog) {
                            // Finished chopping the whole tree, start cooldown
                            targetLogPos = null;
                            originalLogPos = null;
                            phase = null;
                            cooldownTicks = COOLDOWN_DURATION;
                        }
                    }
                }
            }
        }
    }

    private void addConnectedLogs(BlockPos pos) {
        Level level = villager.level();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -1; dz <= 1; dz++) {
                    if (dx == 0 && dy == 0 && dz == 0) continue; // Skip self
                    BlockPos adjacent = pos.offset(dx, dy, dz);
                    if (visitedLogs.contains(adjacent)) continue; // Already processed
                    BlockState state = level.getBlockState(adjacent);
                    if (state.getBlock() instanceof RotatedPillarBlock) {
                        logQueue.addLast(adjacent);       // Add to queue to chop later
                        visitedLogs.add(adjacent);        // Mark visited
                    }
                }
            }
        }
    }


    private void chopLog(BlockPos pos) {
        Level level = villager.level();
        BlockState state = level.getBlockState(pos);
        Block block = state.getBlock();

        if (!(block instanceof RotatedPillarBlock)) return;

        level.playSound(null, pos, block.getSoundType(state, level, pos, villager).getBreakSound(),
                SoundSource.BLOCKS, 1.0F, 1.0F);

        Block.getDrops(state, (ServerLevel) level, pos, null).forEach(stack -> villager.getInventory().addItem(stack));

        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);

        BlockPos below = pos.below();
        BlockState belowState = level.getBlockState(below);
        if ((belowState.is(Blocks.GRASS_BLOCK) || belowState.is(Blocks.DIRT)) && level.isEmptyBlock(pos)) {
            Block sapling = getSaplingForLog(block);
            if (sapling != null) {
                level.setBlock(pos, sapling.defaultBlockState(), 3);
            }
        }
    }

    private Block getSaplingForLog(Block logBlock) {
        if (logBlock == Blocks.OAK_LOG || logBlock == Blocks.OAK_WOOD) return Blocks.OAK_SAPLING;
        if (logBlock == Blocks.BIRCH_LOG || logBlock == Blocks.BIRCH_WOOD) return Blocks.BIRCH_SAPLING;
        if (logBlock == Blocks.SPRUCE_LOG || logBlock == Blocks.SPRUCE_WOOD) return Blocks.SPRUCE_SAPLING;
        if (logBlock == Blocks.JUNGLE_LOG || logBlock == Blocks.JUNGLE_WOOD) return Blocks.JUNGLE_SAPLING;
        if (logBlock == Blocks.ACACIA_LOG || logBlock == Blocks.ACACIA_WOOD) return Blocks.ACACIA_SAPLING;
        if (logBlock == Blocks.DARK_OAK_LOG || logBlock == Blocks.DARK_OAK_WOOD) return Blocks.DARK_OAK_SAPLING;
        return null;
    }

    private BlockPos findLog() {
        BlockPos origin = villager.blockPosition();
        Level level = villager.level();
        int radius = 15;

        BlockPos closestLog = null;
        double closestDistanceSq = Double.MAX_VALUE;

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -3; dy <= 6; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    BlockPos pos = origin.offset(dx, dy, dz);
                    BlockState state = level.getBlockState(pos);
                    Block block = state.getBlock();

                    if (block instanceof RotatedPillarBlock && hasNearbyLeaves(pos) && !isProtectedArea(pos)) {
                        double distSq = pos.distSqr(origin);
                        if (distSq < closestDistanceSq) {
                            closestDistanceSq = distSq;
                            closestLog = pos;
                        }
                    }
                }
            }
        }
        return closestLog;
    }

    private boolean hasNearbyLeaves(BlockPos pos) {
        Level level = villager.level();
        int checkRadius = 2;
        for (int dx = -checkRadius; dx <= checkRadius; dx++) {
            for (int dy = -checkRadius; dy <= checkRadius; dy++) {
                for (int dz = -checkRadius; dz <= checkRadius; dz++) {
                    BlockPos checkPos = pos.offset(dx, dy, dz);
                    if (level.getBlockState(checkPos).getBlock() instanceof LeavesBlock) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isProtectedArea(BlockPos pos) {
        // Implement your logic here for protected zones
        return false;
    }

    private void ensureVillagerHasAxe(Villager villager) {
        ItemStack mainHand = villager.getMainHandItem();
        if (!(mainHand.getItem() instanceof AxeItem)) {
            villager.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.IRON_AXE));
        }
    }

    private boolean isWithinChopRange(Villager villager, BlockPos target) {
        double horizontalDistanceSq =
                Math.pow(villager.getX() - (target.getX() + 0.5), 2) +
                        Math.pow(villager.getZ() - (target.getZ() + 0.5), 2);
        double verticalDistance = Math.abs(villager.getY() - target.getY());

        return horizontalDistanceSq <= 2.0 && verticalDistance <= 6.0;
    }
}