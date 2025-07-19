package net.Aziuria.aziuriamod.block.custom;

import net.Aziuria.aziuriamod.worldgen.tree.ModTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;

/**
 * Apple Sapling Block that grows into random apple tree variants without crashing,
 * using a valid TreeGrower to avoid null crashes while preserving your custom random variant logic.
 */
public class ModAppleSaplingBlock extends SaplingBlock {

    public ModAppleSaplingBlock(BlockBehaviour.Properties properties) {
        // Use a valid grower to prevent null crash on advanceTree
        // Since we override the growth logic, we can pass APPLE_VARIANT_1 as fallback
        super(ModTreeGrowers.APPLE_VARIANT_1, properties);
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        super.tick(state, world, pos, random);
        if (!world.isClientSide && random.nextInt(7) == 0) { // 1/7 chance every tick to grow naturally
            growRandomTreeVariant(world, pos, state, random);
        }
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
        growRandomTreeVariant(world, pos, state, random);
    }

    private void growRandomTreeVariant(ServerLevel world, BlockPos pos, BlockState state, RandomSource random) {
        ChunkGenerator chunkGenerator = world.getChunkSource().getGenerator();
        int variant = random.nextInt(10);
        switch (variant) {
            case 0 -> ModTreeGrowers.APPLE_VARIANT_1.growTree(world, chunkGenerator, pos, state, random);
            case 1 -> ModTreeGrowers.APPLE_VARIANT_2.growTree(world, chunkGenerator, pos, state, random);
            case 2 -> ModTreeGrowers.APPLE_VARIANT_3.growTree(world, chunkGenerator, pos, state, random);
            case 3 -> ModTreeGrowers.APPLE_VARIANT_4.growTree(world, chunkGenerator, pos, state, random);
            case 4 -> ModTreeGrowers.APPLE_VARIANT_5.growTree(world, chunkGenerator, pos, state, random);
            case 5 -> ModTreeGrowers.APPLE_VARIANT_6.growTree(world, chunkGenerator, pos, state, random);
            default -> ModTreeGrowers.APPLE_VARIANT_7.growTree(world, chunkGenerator, pos, state, random);
        }
    }
}