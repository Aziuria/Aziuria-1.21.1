package net.Aziuria.aziuriamod.block.custom.custom.decorative;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GravestoneBlock extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public static final MapCodec<GravestoneBlock> CODEC = MapCodec.unit(() ->
            new GravestoneBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(1.5f)
                    .noOcclusion()
            )
    );

    public GravestoneBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        // Makes it face the player when placed
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    @SuppressWarnings("deprecation") // suppresses the rotate warning
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction dir = state.getValue(FACING);

        // Base sizes (8 wide, 12 tall, 2 thick)
        double height = 0.75;     // 12 / 16
        double thickness = 0.125; // 2 / 16
        double offset = 0.3125;   // 5 / 16

        return switch (dir) {
            case NORTH -> Shapes.box(
                    0.25, 0.0, 0.4375 + offset,
                    0.75, height, 0.5625 + offset
            );
            case SOUTH -> Shapes.box(
                    0.25, 0.0, 0.4375 - offset,
                    0.75, height, 0.5625 - offset
            );
            case EAST -> Shapes.box(
                    0.4375 - offset, 0.0, 0.25,
                    0.5625 - offset, height, 0.75
            );
            case WEST -> Shapes.box(
                    0.4375 + offset, 0.0, 0.25,
                    0.5625 + offset, height, 0.75
            );
            default -> Shapes.box(0.25, 0.0, 0.4375, 0.75, height, 0.5625);
        };
    }

    @Override
    public boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side) {
        return false;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public MapCodec<GravestoneBlock> codec() {
        return CODEC;
    }
}