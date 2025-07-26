package net.Aziuria.aziuriamod.block.custom;

import com.mojang.serialization.MapCodec;
import net.Aziuria.aziuriamod.block.entity.HookBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;


public class HookBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public static final MapCodec<HookBlock> CODEC = MapCodec.unit(() ->
            new HookBlock(Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.WOOD)
                    .noOcclusion())
    );

    public HookBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new HookBlockEntity(pos, state);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        // Ensure the interaction only occurs on the server side
        if (!level.isClientSide()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);

            // Ensure the block entity is an instance of HookBlockEntity
            if (blockEntity instanceof HookBlockEntity hookEntity) {
                ItemStack heldItem = player.getItemInHand(InteractionHand.MAIN_HAND); // Get the item the player is holding
                Vec3 hitVec = hitResult.getLocation().subtract(pos.getX(), pos.getY(), pos.getZ());

                // Calculate the slot based on the hit location
                int slot = HookBlockEntity.getClickedSlot(hitVec, state.getValue(FACING));

                // Handle the interaction on the shelf
                return hookEntity.onRightClick(level, pos, player, state, heldItem, slot);
            }
        }

        // Default success if the interaction is handled
        return InteractionResult.SUCCESS;
    }


    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);

        double startX = 0.25;   // 4 pixels in from left (0.25 blocks)
        double endX = 0.75;     // 4 pixels in from right
        double height = 1.0;    // full height
        double depth = 0.3125;  // 5 pixels deep (5/16 blocks)

        switch (direction) {
            case NORTH:
                return Shapes.box(startX, 0, 1 - depth, endX, height, 1);
            case SOUTH:
                return Shapes.box(startX, 0, 0, endX, height, depth);
            case EAST:
                // For EAST and WEST, width affects Z-axis instead of X
                return Shapes.box(0, 0, startX, depth, height, endX);
            case WEST:
                return Shapes.box(1 - depth, 0, startX, 1, height, endX);
            default:
                return Shapes.block();
        }
    }

    @Override
    public MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}