package net.Aziuria.aziuriamod.island.decoration;

import net.Aziuria.aziuriamod.block.ModBlocks;
import net.Aziuria.aziuriamod.island.type.IslandBiomeType;
import net.Aziuria.aziuriamod.island.util.SimplePlacementContext;
import net.Aziuria.aziuriamod.worldgen.rules.NearLogPlacementModifier;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;

import java.util.Optional;

import net.minecraft.world.level.chunk.ChunkGenerator;

public class TreeLitterSpawner {

    public static void spawnLitterAroundTree(WorldGenLevel level, ChunkGenerator chunkGenerator, BlockPos treePos, RandomSource random, IslandBiomeType biomeType) {
        var litterBlocks = new net.minecraft.world.level.block.Block[]{
                ModBlocks.LEAF_LITTER.get(),    // Add your mod's leaf litter block(s)
        };

        NearLogPlacementModifier litterPlacement = new NearLogPlacementModifier(9, 8.0);

        var ctx = new SimplePlacementContext(level, chunkGenerator, Optional.empty());

// Run 2 times to double litter amount roughly
        for (int i = 0; i < 4; i++) {
            litterPlacement.getPositions(ctx, random, treePos).forEach(pos -> {
                if (level.isEmptyBlock(pos)) {
                    var chosen = litterBlocks[random.nextInt(litterBlocks.length)];
                    level.setBlock(pos, chosen.defaultBlockState(), 2);
                }
            });
        }
    }
}