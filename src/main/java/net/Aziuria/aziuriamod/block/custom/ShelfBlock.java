package net.Aziuria.aziuriamod.block.custom;

import com.mojang.serialization.MapCodec;
import net.Aziuria.aziuriamod.block.entity.ShelfBlockEntity;
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


public class ShelfBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public static final MapCodec<ShelfBlock> CODEC = MapCodec.unit(() ->
            new ShelfBlock(Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.WOOD)
                    .noOcclusion())
    );

    public ShelfBlock(Properties properties) {
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
        return new ShelfBlockEntity(pos, state);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        // Ensure the interaction only occurs on the server side
        if (!level.isClientSide()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);

            // Ensure the block entity is an instance of ShelfBlockEntity
            if (blockEntity instanceof ShelfBlockEntity shelfEntity) {
                ItemStack heldItem = player.getItemInHand(InteractionHand.MAIN_HAND); // Get the item the player is holding
                Vec3 hitVec = hitResult.getLocation().subtract(pos.getX(), pos.getY(), pos.getZ());

                // Calculate the slot based on the hit location
                int slot = ShelfBlockEntity.getClickedSlot(hitVec, state.getValue(FACING));

                // Handle the interaction on the shelf
                return shelfEntity.onRightClick(level, pos, player, state, heldItem, slot);
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

        // Full width and height (16x16) and depth of 9/16
        double offset = 0.0;  // No offset for the collision box
        double width = 1.0;   // Full width of the block
        double height = 1.0;  // Full height of the block
        double depth = 0.5625; // Depth is 9/16 of the block (0.5625)

        // Adjust the coordinates of the collision box based on the facing direction
        switch (direction) {
            case NORTH:
                // Start at the back (1 - depth) and go to the front (1.0)
                return Shapes.box(0, 0, 1 - depth, width, height, 1);

            case SOUTH:
                // Start at the back (0) and go to the front (depth)
                return Shapes.box(0, 0, 0, width, height, depth);

            case EAST:
                // Correct depth already for EAST direction (reaches the edge of the shelf)
                return Shapes.box(0, 0, 0, depth, height, 1);

            case WEST:
                // Correct depth already for WEST direction (reaches the edge of the shelf)
                return Shapes.box(1 - depth, 0, 0, 1, height, 1);

            default:
                return Shapes.block();
        }
    }

    @Override
    public MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}