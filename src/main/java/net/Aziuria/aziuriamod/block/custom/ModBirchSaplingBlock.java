package net.Aziuria.aziuriamod.block.custom;

import net.Aziuria.aziuriamod.worldgen.tree.ModTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;

public class ModBirchSaplingBlock extends SaplingBlock {

    // 70% modded, 30% vanilla
    private static final int MODDED_CHANCE = 4; // out of 10

    public ModBirchSaplingBlock(Properties properties) {
        // Fallback grower MUST be valid
        super(TreeGrower.BIRCH, properties);
    }

    /**
     * Vanilla saplings grow via randomTick, not tick.
     */
    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.getMaxLocalRawBrightness(pos.above()) >= 9 && random.nextInt(7) == 0) {
            growRandomTreeVariant(level, pos, state, random);
        }
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        growRandomTreeVariant(level, pos, state, random);
    }

    private void growRandomTreeVariant(ServerLevel level, BlockPos pos, BlockState state, RandomSource random) {
        ChunkGenerator generator = level.getChunkSource().getGenerator();

        // Roll 0–9
        if (random.nextInt(10) < MODDED_CHANCE) {
            // MODDED OAK (more common)
            ModTreeGrowers.BIRCH_VARIANT_1.growTree(level, generator, pos, state, random);
        } else {
            // VANILLA OAK
            TreeGrower.BIRCH.growTree(level, generator, pos, state, random);
        }
    }
}