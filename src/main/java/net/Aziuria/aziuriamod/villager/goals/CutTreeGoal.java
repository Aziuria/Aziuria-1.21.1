package net.Aziuria.aziuriamod.villager.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;

import java.util.EnumSet;

public class CutTreeGoal extends Goal {

    private final Villager villager;
    private final double speed;
    private BlockPos targetLogPos = null;

    public CutTreeGoal(Villager villager, double speed) {
        this.villager = villager;
        this.speed = speed;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        ensureVillagerHasAxe(villager);
        ItemStack mainHand = villager.getMainHandItem();
        if (!(mainHand.getItem() instanceof AxeItem)) {
            return false;
        }

        targetLogPos = findLog();
        return targetLogPos != null;
    }

    @Override
    public boolean canContinueToUse() {
        if (targetLogPos == null) return false;
        BlockState state = villager.level().getBlockState(targetLogPos);
        return state.getBlock() instanceof RotatedPillarBlock;
    }

    @Override
    public void stop() {
        targetLogPos = null;
        villager.getNavigation().stop();
    }

    @Override
    public void tick() {
        ensureVillagerHasAxe(villager);

        if (targetLogPos == null) return;

        villager.getLookControl().setLookAt(
                targetLogPos.getX() + 0.5,
                targetLogPos.getY() + 0.5,
                targetLogPos.getZ() + 0.5
        );

        if (!villager.getNavigation().isInProgress()) {
            villager.getNavigation().moveTo(
                    targetLogPos.getX() + 0.5,
                    targetLogPos.getY(),
                    targetLogPos.getZ() + 0.5,
                    speed
            );
        }

        if (villager.blockPosition().closerThan(targetLogPos, 1.5)) {
            chopLog(targetLogPos);

            // **NEW**: Move target upward if possible to chop the whole tree vertically
            BlockPos above = targetLogPos.above();
            BlockState aboveState = villager.level().getBlockState(above);
            if (aboveState.getBlock() instanceof RotatedPillarBlock) {
                targetLogPos = above;
            } else {
                targetLogPos = null; // no more logs above, reset to find new tree next time
            }
        }
    }

    private void chopLog(BlockPos pos) {
        Level level = villager.level();
        BlockState state = level.getBlockState(pos);
        Block block = state.getBlock();

        if (!(block instanceof RotatedPillarBlock)) return;

        level.playSound(null, pos, block.getSoundType(state, level, pos, villager).getBreakSound(),
                net.minecraft.sounds.SoundSource.BLOCKS, 1.0F, 1.0F);

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
        if (logBlock == Blocks.OAK_LOG || logBlock == Blocks.OAK_WOOD) {
            return Blocks.OAK_SAPLING;
        } else if (logBlock == Blocks.BIRCH_LOG || logBlock == Blocks.BIRCH_WOOD) {
            return Blocks.BIRCH_SAPLING;
        } else if (logBlock == Blocks.SPRUCE_LOG || logBlock == Blocks.SPRUCE_WOOD) {
            return Blocks.SPRUCE_SAPLING;
        } else if (logBlock == Blocks.JUNGLE_LOG || logBlock == Blocks.JUNGLE_WOOD) {
            return Blocks.JUNGLE_SAPLING;
        } else if (logBlock == Blocks.ACACIA_LOG || logBlock == Blocks.ACACIA_WOOD) {
            return Blocks.ACACIA_SAPLING;
        } else if (logBlock == Blocks.DARK_OAK_LOG || logBlock == Blocks.DARK_OAK_WOOD) {
            return Blocks.DARK_OAK_SAPLING;
        }
        return null;
    }

    private BlockPos findLog() {
        BlockPos origin = villager.blockPosition();
        Level level = villager.level();
        int radius = 15;

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    BlockPos pos = origin.offset(dx, dy, dz);
                    BlockState state = level.getBlockState(pos);
                    Block block = state.getBlock();

                    if (block instanceof RotatedPillarBlock && hasNearbyLeaves(pos)) {
                        return pos;
                    }
                }
            }
        }

        return null;
    }

    // Check for leaves within a 5x10x5 radius around the log block
    private boolean hasNearbyLeaves(BlockPos pos) {
        Level level = villager.level();

        int horizontalRadius = 5; // wider radius horizontally
        int verticalRadius = 10;  // taller radius vertically

        for (int dx = -horizontalRadius; dx <= horizontalRadius; dx++) {
            for (int dy = -verticalRadius; dy <= verticalRadius; dy++) {
                for (int dz = -horizontalRadius; dz <= horizontalRadius; dz++) {
                    BlockPos checkPos = pos.offset(dx, dy, dz);
                    BlockState state = level.getBlockState(checkPos);
                    if (state.getBlock() instanceof LeavesBlock) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static void ensureVillagerHasAxe(Villager villager) {
        if (!(villager.getMainHandItem().getItem() instanceof AxeItem)) {
            villager.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.IRON_AXE));
        }
    }
}