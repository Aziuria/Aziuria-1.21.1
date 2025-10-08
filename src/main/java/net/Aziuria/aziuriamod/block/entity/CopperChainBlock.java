package net.Aziuria.aziuriamod.block.entity;

import net.Aziuria.aziuriamod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class CopperChainBlock extends ChainBlock implements WeatheringCopper, SimpleWaterloggedBlock {
    private final WeatherState weatherState;

    public CopperChainBlock(WeatherState weatherState, BlockBehaviour.Properties properties) {
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
            if (random.nextFloat() < 0.05688889F) { // vanilla oxidation chance
                level.setBlock(pos, next, 2);
            }
        });
    }

    /** Map current BlockState → next oxidized BlockState */
    @Override
    public Optional<BlockState> getNext(BlockState state) {
        if (state.is(ModBlocks.COPPER_CHAIN.get())) {
            return Optional.of(ModBlocks.EXPOSED_COPPER_CHAIN.get().withPropertiesOf(state));
        }
        if (state.is(ModBlocks.EXPOSED_COPPER_CHAIN.get())) {
            return Optional.of(ModBlocks.WEATHERED_COPPER_CHAIN.get().withPropertiesOf(state));
        }
        if (state.is(ModBlocks.WEATHERED_COPPER_CHAIN.get())) {
            return Optional.of(ModBlocks.OXIDIZED_COPPER_CHAIN.get().withPropertiesOf(state));
        }
        return Optional.empty();
    }

    /** Map current BlockState → previous (less oxidized) BlockState */
    public Optional<BlockState> getPrevious(BlockState state) {
        if (state.is(ModBlocks.OXIDIZED_COPPER_CHAIN.get())) {
            return Optional.of(ModBlocks.WEATHERED_COPPER_CHAIN.get().withPropertiesOf(state));
        }
        if (state.is(ModBlocks.WEATHERED_COPPER_CHAIN.get())) {
            return Optional.of(ModBlocks.EXPOSED_COPPER_CHAIN.get().withPropertiesOf(state));
        }
        if (state.is(ModBlocks.EXPOSED_COPPER_CHAIN.get())) {
            return Optional.of(ModBlocks.COPPER_CHAIN.get().withPropertiesOf(state));
        }
        return Optional.empty();
    }
}