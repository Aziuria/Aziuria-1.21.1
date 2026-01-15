package net.Aziuria.aziuriamod.block.custom.trees.leaves;

import net.Aziuria.aziuriamod.handler.blocks.FastLeafDecayHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class ModOakLeavesBlock extends LeavesBlock {

    public ModOakLeavesBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    // ---------------- FIRE BEHAVIOUR ----------------

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction face) {
        return 60;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction face) {
        return 30;
    }

    // ---------------- FAST LEAF DECAY ----------------

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, net.minecraft.util.RandomSource random) {
        super.tick(state, level, pos, random);

        if (!state.getValue(PERSISTENT)) {
            FastLeafDecayHandler.queueLeafForDecay(level, pos);
        }
    }

    // ---------------- CUSTOM DROPS ----------------

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state,
                              net.minecraft.world.level.block.entity.BlockEntity blockEntity, ItemStack tool) {
        if (!level.isClientSide) {
            if (tool.getItem() instanceof ShearsItem) {
                popResource(level, pos, new ItemStack(this));
            } else {
                super.playerDestroy(level, player, pos, state, blockEntity, tool);
            }
        }
    }

    // ---------------- CLONE PICK ----------------

    public ItemStack getCloneItemStack(BlockState state, BlockGetter level, BlockPos pos, Player player) {
        return new ItemStack(this);
    }

    private int getDistanceToNearestOakLog(Level level, BlockPos pos) {
        int minDistance = 7;

        for (int dx = -4; dx <= 4; dx++) {
            for (int dy = -4; dy <= 4; dy++) {
                for (int dz = -4; dz <= 4; dz++) {
                    BlockPos checkPos = pos.offset(dx, dy, dz);
                    BlockState state = level.getBlockState(checkPos);
                    if (state.is(Blocks.OAK_LOG)) {
                        int dist = Math.abs(dx) + Math.abs(dy) + Math.abs(dz);
                        if (dist < minDistance) {
                            minDistance = dist;
                            if (minDistance == 1) return 1;
                        }
                    }
                }
            }
        }
        return minDistance;
    }
}