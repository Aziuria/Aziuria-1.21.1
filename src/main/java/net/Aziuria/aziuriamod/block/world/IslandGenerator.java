package net.Aziuria.aziuriamod.block.world;

import net.Aziuria.aziuriamod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.synth.SimplexNoise;

public class IslandGenerator {

    public static void generateIsland(ServerLevel level, BlockPos center, IslandType type, IslandBiomeType biomeType, long seed) {
        if (level == null || center == null || type == null || biomeType == null) {
            throw new IllegalArgumentException("Level, center, type or biomeType cannot be null");
        }

        long combinedSeed = seed ^ (center.getX() * 341873128712L) ^ (center.getZ() * 132897987541L);
        RandomSource random = RandomSource.create(combinedSeed);

        int width = type.getRandomWidth(random);
        int length = type.getRandomLength(random);
        int maxHeight = type.getMaxHeight();

        BlockBatcher batcher = new BlockBatcher(level, 500);
        SimplexNoise noise = new SimplexNoise(random);

        for (int x = -width / 2; x <= width / 2; x++) {
            for (int z = -length / 2; z <= length / 2; z++) {
                int worldX = center.getX() + x;
                int worldZ = center.getZ() + z;

                int terrainHeight = level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, worldX, worldZ);
                int oceanFloorHeight = level.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, worldX, worldZ);
                int baseY = Math.max(terrainHeight, oceanFloorHeight + 1) - 1;

                double nx = x / (double)(width / 2);
                double nz = z / (double)(length / 2);
                double distanceFalloff = 1.0 - Math.sqrt(nx * nx + nz * nz);
                double noiseValue = noise.getValue(x * 0.1, z * 0.1, 0);
                double shapeFactor = Math.min(Math.max(distanceFalloff + noiseValue * 0.3, 0.0), 1.0);

                if (shapeFactor > 0) {
                    int columnHeight = (int)(shapeFactor * maxHeight);
                    int topY = baseY + columnHeight;

                    // Fill under water
                    for (int y = oceanFloorHeight; y <= topY; y++) {
                        BlockPos fillPos = new BlockPos(worldX, y, worldZ);
                        double verticalRatio = (y - oceanFloorHeight) / (double)(topY - oceanFloorHeight);

                        if (y >= baseY) continue;

                        if (verticalRatio < 0.2) {
                            batcher.setBlock(fillPos, Blocks.STONE.defaultBlockState());
                        } else if (verticalRatio < 0.4) {
                            batcher.setBlock(fillPos, random.nextDouble() < 0.5 ? Blocks.GRAVEL.defaultBlockState() : Blocks.CLAY.defaultBlockState());
                        } else {
                            batcher.setBlock(fillPos, random.nextDouble() < 0.7 ? Blocks.DIRT.defaultBlockState() : Blocks.SAND.defaultBlockState());
                        }
                    }

                    // Island terrain
                    for (int y = 0; y <= columnHeight; y++) {
                        BlockPos pos = new BlockPos(worldX, baseY + y, worldZ);

                        if (y == columnHeight) {
                            if (shapeFactor < 0.15) {
                                batcher.setBlock(pos, Blocks.SAND.defaultBlockState());
                            } else {
                                switch (biomeType) {
                                    case DESERT -> batcher.setBlock(pos, Blocks.SAND.defaultBlockState());
                                    case SNOWY -> batcher.setBlock(pos, Blocks.SNOW_BLOCK.defaultBlockState());
                                    case SWAMP -> batcher.setBlock(pos, Blocks.DIRT.defaultBlockState());
                                    default -> batcher.setBlock(pos, Blocks.GRASS_BLOCK.defaultBlockState());
                                }
                            }
                        } else if (y > columnHeight - 3) {
                            batcher.setBlock(pos, Blocks.DIRT.defaultBlockState());
                        } else {
                            double oreRoll = random.nextDouble();
                            if (oreRoll < 0.03) {
                                batcher.setBlock(pos, ModBlocks.SULPHUR_ORE.get().defaultBlockState());
                            } else if (oreRoll < 0.06) {
                                batcher.setBlock(pos, ModBlocks.POTASSIUM_ORE.get().defaultBlockState());
                            } else {
                                batcher.setBlock(pos, Blocks.STONE.defaultBlockState());
                            }
                        }
                    }

                    // Decorations
                    BlockPos decoPos = new BlockPos(worldX, baseY + columnHeight + 1, worldZ);
                    double decoRoll = random.nextDouble();

// Only place flowers/grass/flax on grass block underneath
                    boolean isGrassBelow = level.getBlockState(decoPos.below()).is(Blocks.GRASS_BLOCK);

                    if (isGrassBelow) {
                        // Universal flax and tall grass chance
                        if (decoRoll < 0.02) {
                            batcher.setBlock(decoPos, Blocks.TALL_GRASS.defaultBlockState());
                        } else if (decoRoll < 0.025) {
                            batcher.setBlock(decoPos, ModBlocks.FLAX_FLOWER_BLOCK.get().defaultBlockState());
                        }
                    }

                    switch (biomeType) {
                        case FOREST -> {
                            if (decoRoll < 0.01) batcher.setBlock(decoPos, Blocks.OAK_SAPLING.defaultBlockState());
                            else if (decoRoll < 0.015) batcher.setBlock(decoPos, Blocks.DANDELION.defaultBlockState());
                            else if (decoRoll < 0.017) batcher.setBlock(decoPos, Blocks.ALLIUM.defaultBlockState());
                            else if (decoRoll < 0.019) batcher.setBlock(decoPos, Blocks.BIRCH_SAPLING.defaultBlockState());
                        }
                        case DESERT -> {
                            if (decoRoll < 0.01) batcher.setBlock(decoPos, Blocks.CACTUS.defaultBlockState());
                            else if (decoRoll < 0.015) batcher.setBlock(decoPos, Blocks.DEAD_BUSH.defaultBlockState());
                        }
                        case SNOWY -> {
                            if (decoRoll < 0.012) batcher.setBlock(decoPos, Blocks.SPRUCE_SAPLING.defaultBlockState());
                            else if (decoRoll < 0.015) batcher.setBlock(decoPos, Blocks.SNOW.defaultBlockState()); // snow patches
                        }
                        case SWAMP -> {
                            if (decoRoll < 0.01) batcher.setBlock(decoPos, Blocks.BLUE_ORCHID.defaultBlockState());
                            else if (decoRoll < 0.02) batcher.setBlock(decoPos, Blocks.OAK_SAPLING.defaultBlockState());
                            else if (decoRoll < 0.025) batcher.setBlock(decoPos, Blocks.TALL_GRASS.defaultBlockState());
                        }
                        case PLAINS -> {
                            if (decoRoll < 0.01) batcher.setBlock(decoPos, Blocks.DANDELION.defaultBlockState());
                            else if (decoRoll < 0.015) batcher.setBlock(decoPos, Blocks.POPPY.defaultBlockState());
                            else if (decoRoll < 0.02) batcher.setBlock(decoPos, Blocks.TALL_GRASS.defaultBlockState());
                            else if (decoRoll < 0.025) batcher.setBlock(decoPos, Blocks.AZURE_BLUET.defaultBlockState());
                        }
                        case MOUNTAINS -> {
                            if (decoRoll < 0.01) batcher.setBlock(decoPos, Blocks.POPPY.defaultBlockState());
                            else if (decoRoll < 0.015) batcher.setBlock(decoPos, Blocks.TALL_GRASS.defaultBlockState());
                            else if (decoRoll < 0.02) batcher.setBlock(decoPos, Blocks.DANDELION.defaultBlockState());
                        }
                        case JUNGLE -> {
                            if (decoRoll < 0.01) batcher.setBlock(decoPos, Blocks.JUNGLE_SAPLING.defaultBlockState());
                            else if (decoRoll < 0.015) batcher.setBlock(decoPos, Blocks.LILY_OF_THE_VALLEY.defaultBlockState());
                            else if (decoRoll < 0.02) batcher.setBlock(decoPos, Blocks.FERN.defaultBlockState());
                        }
                        case BADLANDS -> {
                            if (decoRoll < 0.01) batcher.setBlock(decoPos, Blocks.DEAD_BUSH.defaultBlockState());
                            else if (decoRoll < 0.02) batcher.setBlock(decoPos, Blocks.RED_TULIP.defaultBlockState());
                        }
                        case SAVANNA -> {
                            if (decoRoll < 0.01) batcher.setBlock(decoPos, Blocks.ACACIA_SAPLING.defaultBlockState());
                            else if (decoRoll < 0.02) batcher.setBlock(decoPos, Blocks.DANDELION.defaultBlockState());
                        }
                        case TAIGA -> {
                            if (decoRoll < 0.01) batcher.setBlock(decoPos, Blocks.SPRUCE_SAPLING.defaultBlockState());
                            else if (decoRoll < 0.02) batcher.setBlock(decoPos, Blocks.FERN.defaultBlockState());
                        }
                        default -> {
                            // Fallback decorations
                            if (decoRoll < 0.01) batcher.setBlock(decoPos, Blocks.POPPY.defaultBlockState());
                        }
                    }
                }
            }
        }

        batcher.flush();
    }
}