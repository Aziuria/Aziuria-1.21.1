package net.Aziuria.aziuriamod.block;

import com.mojang.serialization.MapCodec;
import net.Aziuria.aziuriamod.block.entity.WoodcutterBenchBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class WoodcutterBenchBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public static final MapCodec<WoodcutterBenchBlock> CODEC = MapCodec.unit(() ->
            new WoodcutterBenchBlock(Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(1.5f)
                    .noOcclusion()
                    .lightLevel(state -> 10)
            )
    );

    public WoodcutterBenchBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        // Face the block opposite to the player placing it
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    private static final VoxelShape SHAPE_S = Shapes.box(0.0, 0.0, 0.0, 2.0, 1.5, 1.0);   // South, base shape
    private static final VoxelShape SHAPE_N = Shapes.box(-1.0, 0.0, 0.0, 1.0, 1.5, 1.0);  // North shifted left by 1 on X
    private static final VoxelShape SHAPE_E = Shapes.box(0.0, 0.0, -1.0, 1.0, 1.5, 1.0);  // East shifted back by 1 on Z, 1 wide, 1 deep (adjusted)
    private static final VoxelShape SHAPE_W = Shapes.box(0.0, 0.0, 0.0, 1.0, 1.5, 2.0); // West 2 deep unshifted

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction facing = state.getValue(FACING);
        switch (facing) {
            case NORTH: return SHAPE_N;
            case SOUTH: return SHAPE_S;
            case EAST:  return SHAPE_E;
            case WEST:  return SHAPE_W;
            default:    return SHAPE_N;  // fallback
        }
    }

    // --- NEW: Visual Hitbox shape (outline when looking at the block) ---
    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return getShape(state, level, pos, context);  // <-- Return same shape as collision box
    }

    // --- NEW: Interaction shape (used for clicking/interactions) ---
    @Override
    public VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return getShape(state, level, pos, CollisionContext.empty());  // <-- Same shape, no collision context needed
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
    public MapCodec<WoodcutterBenchBlock> codec() {
        return CODEC;
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter world, BlockPos pos) {
        return 10;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new WoodcutterBenchBlockEntity(pos, state);
    }


}