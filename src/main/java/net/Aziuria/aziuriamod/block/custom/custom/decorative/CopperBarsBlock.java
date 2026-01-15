package net.Aziuria.aziuriamod.block.custom.custom.decorative;

import net.Aziuria.aziuriamod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class CopperBarsBlock extends IronBarsBlock implements WeatheringCopper {
    private final WeatherState weatherState;

    public CopperBarsBlock(WeatherState weatherState, BlockBehaviour.Properties properties) {
        super(properties);
        this.weatherState = weatherState;
    }

    /** Return this block's oxidation age */
    @Override
    public WeatherState getAge() {
        return this.weatherState;
    }

    /** Only randomly tick if there is a next stage to go to */
    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return this.getNext(state).isPresent();
    }

    /** Random tick => attempt to oxidize (mimic vanilla chance) */
    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        this.getNext(state).ifPresent(next -> {
            // Vanilla chance ≈ 0.05688889F
            if (random.nextFloat() < 0.05688889F) {
                level.setBlock(pos, next, 2);
            }
        });
    }

    /** Map current BlockState → next oxidized BlockState */
    @Override
    public Optional<BlockState> getNext(BlockState state) {
        if (state.is(ModBlocks.COPPER_BARS.get())) {
            return Optional.of(ModBlocks.EXPOSED_COPPER_BARS.get().withPropertiesOf(state));
        }
        if (state.is(ModBlocks.EXPOSED_COPPER_BARS.get())) {
            return Optional.of(ModBlocks.WEATHERED_COPPER_BARS.get().withPropertiesOf(state));
        }
        if (state.is(ModBlocks.WEATHERED_COPPER_BARS.get())) {
            return Optional.of(ModBlocks.OXIDIZED_COPPER_BARS.get().withPropertiesOf(state));
        }
        return Optional.empty();
    }

    /** Map current BlockState → previous (less oxidized) BlockState */
    public Optional<BlockState> getPrevious(BlockState state) {
        if (state.is(ModBlocks.OXIDIZED_COPPER_BARS.get())) {
            return Optional.of(ModBlocks.WEATHERED_COPPER_BARS.get().withPropertiesOf(state));
        }
        if (state.is(ModBlocks.WEATHERED_COPPER_BARS.get())) {
            return Optional.of(ModBlocks.EXPOSED_COPPER_BARS.get().withPropertiesOf(state));
        }
        if (state.is(ModBlocks.EXPOSED_COPPER_BARS.get())) {
            return Optional.of(ModBlocks.COPPER_BARS.get().withPropertiesOf(state));
        }
        return Optional.empty();
    }
}