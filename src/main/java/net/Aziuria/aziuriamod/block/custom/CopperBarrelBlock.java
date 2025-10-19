package net.Aziuria.aziuriamod.block.custom;

import com.mojang.serialization.MapCodec;
import net.Aziuria.aziuriamod.block.ModBlocks;
import net.Aziuria.aziuriamod.block.entity.SteelBarrelBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.fluids.FluidUtil;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CopperBarrelBlock extends BaseEntityBlock implements WeatheringCopper {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    private final WeatherState weatherState;

    public static final MapCodec<CopperBarrelBlock> CODEC = MapCodec.unit(() ->
            new CopperBarrelBlock(WeatherState.UNAFFECTED, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .strength(2f)
                    .noOcclusion()
                    .randomTicks()) // important for oxidation
    );

    public CopperBarrelBlock(WeatherState weatherState, BlockBehaviour.Properties properties) {
        super(properties);
        this.weatherState = weatherState;
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    // --- WeatheringCopper Implementation ---
    @Override
    public WeatherState getAge() {
        return this.weatherState;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return this.getNext(state).isPresent();
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        this.getNext(state).ifPresent(next -> {
            // mimic vanilla oxidation rate (~5.7%)
            if (random.nextFloat() < 0.05688889F) {

                // <<< ADDED: preserve fluid before changing state
                BlockEntity oldBE = level.getBlockEntity(pos);
                SteelBarrelBlockEntity steelBarrelOld = null;
                if (oldBE instanceof SteelBarrelBlockEntity steel) {
                    steelBarrelOld = steel;
                }

                level.setBlock(pos, next, 2);

                if (steelBarrelOld != null) {
                    BlockEntity newBE = level.getBlockEntity(pos);
                    if (newBE instanceof SteelBarrelBlockEntity steelBarrelNew) {
                        steelBarrelNew.getTank().setFluid(steelBarrelOld.getTank().getFluid().copy());
                    }
                }
                // <<< END
            }
        });
    }

    @Override
    public Optional<BlockState> getNext(BlockState state) {
        if (state.is(ModBlocks.COPPER_BARREL.get())) {
            return Optional.of(ModBlocks.EXPOSED_COPPER_BARREL.get().withPropertiesOf(state));
        }
        if (state.is(ModBlocks.EXPOSED_COPPER_BARREL.get())) {
            return Optional.of(ModBlocks.WEATHERED_COPPER_BARREL.get().withPropertiesOf(state));
        }
        if (state.is(ModBlocks.WEATHERED_COPPER_BARREL.get())) {
            return Optional.of(ModBlocks.OXIDIZED_COPPER_BARREL.get().withPropertiesOf(state));
        }
        return Optional.empty();
    }

    public Optional<BlockState> getPrevious(BlockState state) {
        if (state.is(ModBlocks.OXIDIZED_COPPER_BARREL.get())) {
            return Optional.of(ModBlocks.WEATHERED_COPPER_BARREL.get().withPropertiesOf(state));
        }
        if (state.is(ModBlocks.WEATHERED_COPPER_BARREL.get())) {
            return Optional.of(ModBlocks.EXPOSED_COPPER_BARREL.get().withPropertiesOf(state));
        }
        if (state.is(ModBlocks.EXPOSED_COPPER_BARREL.get())) {
            return Optional.of(ModBlocks.COPPER_BARREL.get().withPropertiesOf(state));
        }
        return Optional.empty();
    }

    // --- Core Barrel Behavior (from SteelBarrelBlock) ---
    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.box(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        // Reuse SteelBarrelBlockEntity — both can share the same fluid handler logic
        return new SteelBarrelBlockEntity(pos, state);
    }

    // ======================================
// ADD THIS: BlockEntity ticker for server-side ticking
// ======================================
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        // Only tick on the server side
        return level.isClientSide ? null : (lvl, pos, st, be) -> {
            if (be instanceof SteelBarrelBlockEntity barrel) {
                barrel.tick(); // call the auto-rain/lava tick
            }
        };
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (state.getBlock() != newState.getBlock()) {
            // Optional drop logic if needed
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos,
                                              Player pPlayer, InteractionHand pHand, BlockHitResult pRayTrace) {
        if (FluidUtil.interactWithFluidHandler(pPlayer, pHand, pLevel, pPos, pRayTrace.getDirection())) {
            return ItemInteractionResult.sidedSuccess(pLevel.isClientSide());
        }

        return super.useItemOn(pStack, pState, pLevel, pPos, pPlayer, pHand, pRayTrace);
    }
}