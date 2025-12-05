package net.Aziuria.aziuriamod.worldgen.rules;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.Aziuria.aziuriamod.worldgen.rules.registry.ModBlockStateProviders;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;

public class RandomFacingStateProvider extends BlockStateProvider {

    // ✔ MUST be MapCodec now
    public static final MapCodec<RandomFacingStateProvider> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    BlockState.CODEC.fieldOf("state").forGetter(provider -> provider.base)
            ).apply(instance, RandomFacingStateProvider::new)
    );

    private final BlockState base;

    public RandomFacingStateProvider(BlockState base) {
        this.base = base;
    }

    @Override
    protected BlockStateProviderType<?> type() {
        return ModBlockStateProviders.RANDOM_FACING.get();
    }

    @Override
    public BlockState getState(RandomSource random, BlockPos pos) {
        Direction dir = Direction.Plane.HORIZONTAL.getRandomDirection(random);

        if (base.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
            return base.setValue(BlockStateProperties.HORIZONTAL_FACING, dir);
        }

        return base;
    }
}