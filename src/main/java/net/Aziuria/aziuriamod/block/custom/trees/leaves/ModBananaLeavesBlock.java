package net.Aziuria.aziuriamod.block.custom.trees.leaves;

import net.Aziuria.aziuriamod.handler.blocks.FastLeafDecayHandler;
import net.Aziuria.aziuriamod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

public class ModBananaLeavesBlock extends LeavesBlock {

    public static final IntegerProperty PICK_COUNT = IntegerProperty.create("pick_count", 0, 9);
    public static final IntegerProperty MAX_PICK = IntegerProperty.create("max_pick", 3, 9);

    public ModBananaLeavesBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(PICK_COUNT, 0)
                .setValue(MAX_PICK, 5)); // default max pick count fallback
    }

    @Override
    public BlockState getStateForPlacement(net.minecraft.world.item.context.BlockPlaceContext context) {
        int randomMax = Mth.nextInt(context.getLevel().getRandom(), 3, 9);
        return this.defaultBlockState()
                .setValue(PICK_COUNT, 0)
                .setValue(MAX_PICK, randomMax)
                .setValue(BlockStateProperties.PERSISTENT, true);
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction face) {
        return 60;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction face) {
        return 30;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (!level.isClientSide) {
            int currentPicks = state.getValue(PICK_COUNT);
            int maxPicks = state.getValue(MAX_PICK);

            if (currentPicks < maxPicks) {
                // Drop a banana
                ItemStack banana = new ItemStack(ModItems.BANANA.get());
                ItemEntity bananaEntity = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.7, pos.getZ() + 0.5, banana);
                bananaEntity.setDefaultPickUpDelay();
                level.addFreshEntity(bananaEntity);

                level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 0.5F, 1.0F);

                level.setBlock(pos, state.setValue(PICK_COUNT, currentPicks + 1), 3);
            } else {
                int distance = getDistanceToNearestJungleLog(level, pos);
                if (distance > 7) distance = 7;

                BlockState jungleLeavesState = Blocks.JUNGLE_LEAVES.defaultBlockState()
                        .setValue(LeavesBlock.DISTANCE, distance)
                        .setValue(LeavesBlock.PERSISTENT, false);

                level.setBlock(pos, jungleLeavesState, 3);

                level.updateNeighborsAt(pos, Blocks.JUNGLE_LEAVES);
                if (level instanceof ServerLevel serverLevel) {
                    FastLeafDecayHandler.queueLeafForDecay(serverLevel, pos);
                }

                level.playSound(null, pos, SoundEvents.WOOD_BREAK, SoundSource.BLOCKS, 0.5F, 0.8F);
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

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

    public ItemStack getCloneItemStack(BlockState state, BlockGetter level, BlockPos pos, Player player) {
        return new ItemStack(this);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(PICK_COUNT, MAX_PICK);
    }

    private int getDistanceToNearestJungleLog(Level level, BlockPos pos) {
        int minDistance = 7;

        for (int dx = -4; dx <= 4; dx++) {
            for (int dy = -4; dy <= 4; dy++) {
                for (int dz = -4; dz <= 4; dz++) {
                    BlockPos checkPos = pos.offset(dx, dy, dz);
                    BlockState state = level.getBlockState(checkPos);
                    if (state.is(Blocks.JUNGLE_LOG)) {
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