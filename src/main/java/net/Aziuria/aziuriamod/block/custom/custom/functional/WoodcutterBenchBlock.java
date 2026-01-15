package net.Aziuria.aziuriamod.block.custom.custom.functional;

import com.mojang.serialization.MapCodec;
import net.Aziuria.aziuriamod.block.part.WoodcutterPart;
import net.Aziuria.aziuriamod.block.entity.custom.WoodcutterBenchBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class WoodcutterBenchBlock extends BaseEntityBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<WoodcutterPart> PART = EnumProperty.create("part", WoodcutterPart.class);

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
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(PART, WoodcutterPart.LEFT));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, PART);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction facing = context.getHorizontalDirection();
        BlockPos pos = context.getClickedPos();
        BlockPos otherPos = pos.relative(facing.getCounterClockWise()); // Moved LEFT to connect

        Level level = context.getLevel();

        // Check if other position can be replaced
        if (!level.getBlockState(otherPos).canBeReplaced(context)) {
            return null;
        }

        // Deny placement if block above this position is not replaceable
        BlockPos abovePos = pos.above();
        if (!level.getBlockState(abovePos).canBeReplaced(context)) {
            return null;
        }

        // Deny placement if block above the other position is not replaceable
        BlockPos otherAbovePos = otherPos.above();
        if (!level.getBlockState(otherAbovePos).canBeReplaced(context)) {
            return null;
        }

        return this.defaultBlockState()
                .setValue(FACING, facing)
                .setValue(PART, WoodcutterPart.RIGHT); // This block will now be RIGHT, other will be LEFT
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        Direction facing = state.getValue(FACING);
        BlockPos otherPos = pos.relative(facing.getCounterClockWise()); // Place other part LEFT of this block

        BlockState otherState = this.defaultBlockState()
                .setValue(FACING, facing)
                .setValue(PART, WoodcutterPart.LEFT);

        level.setBlock(otherPos, otherState, 3);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            Direction facing = state.getValue(FACING);
            WoodcutterPart part = state.getValue(PART);
            BlockPos otherPos = (part == WoodcutterPart.RIGHT)
                    ? pos.relative(facing.getCounterClockWise())
                    : pos.relative(facing.getClockWise());

            if (level.getBlockState(otherPos).getBlock() == this) {
                level.destroyBlock(otherPos, false);
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState,
                                  LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        // Check if paired block still exists, else break this block
        WoodcutterPart part = state.getValue(PART);
        Direction facing = state.getValue(FACING);
        BlockPos otherPos = (part == WoodcutterPart.RIGHT)
                ? pos.relative(facing.getCounterClockWise())
                : pos.relative(facing.getClockWise());

        if (!level.getBlockState(otherPos).is(this)) {
            return Blocks.AIR.defaultBlockState();
        }

        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    private static final VoxelShape LEFT_SHAPE = Shapes.box(0, 0, 0, 1, 1.5, 1);
    private static final VoxelShape RIGHT_SHAPE = Shapes.box(0, 0, 0, 1, 1.5, 1);

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(PART) == WoodcutterPart.LEFT ? LEFT_SHAPE : RIGHT_SHAPE;
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return getShape(state, level, pos, context);
    }

    @Override
    public VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return getShape(state, level, pos, CollisionContext.empty());
    }

    @Override
    public boolean skipRendering(BlockState state, BlockState adjacentState, Direction side) {
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