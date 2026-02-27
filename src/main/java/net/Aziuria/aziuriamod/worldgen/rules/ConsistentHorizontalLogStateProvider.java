package net.Aziuria.aziuriamod.worldgen.rules;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.Aziuria.aziuriamod.worldgen.rules.registry.ModBlockStateProviders;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;

public class ConsistentHorizontalLogStateProvider extends BlockStateProvider {

    public static final MapCodec<ConsistentHorizontalLogStateProvider> CODEC =
            RecordCodecBuilder.mapCodec(instance ->
                    instance.group(
                            BlockState.CODEC.fieldOf("state").forGetter(p -> p.base)
                    ).apply(instance, ConsistentHorizontalLogStateProvider::new)
            );

    private final BlockState base;

    public ConsistentHorizontalLogStateProvider(BlockState base) {
        this.base = base;
    }

    @Override
    protected BlockStateProviderType<?> type() {
        return ModBlockStateProviders.CONSISTENT_HORIZONTAL_LOG.get();
    }


    @Override
    public BlockState getState(RandomSource random, BlockPos pos) {

        // deterministic pseudo-random from tree origin area
        int hash = (int) Mth.getSeed(pos.getX() >> 4, 0, pos.getZ() >> 4);

        Direction.Axis axis = (hash & 1) == 0
                ? Direction.Axis.X
                : Direction.Axis.Z;

        if (base.hasProperty(RotatedPillarBlock.AXIS)) {
            return base.setValue(RotatedPillarBlock.AXIS, axis);
        }

        return base;
    }
}