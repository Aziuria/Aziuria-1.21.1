package net.Aziuria.aziuriamod.block.world;

import net.Aziuria.aziuriamod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.synth.SimplexNoise;

import java.util.ArrayList;
import java.util.List;

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

        // Store exact sapling candidate spots (must be soil height + 1)
        List<BlockPos> treePositions = new ArrayList<>();

        for (int x = -width / 2; x <= width / 2; x++) {
            for (int z = -length / 2; z <= length / 2; z++) {
                int worldX = center.getX() + x;
                int worldZ = center.getZ() + z;

                int terrainHeight = level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, worldX, worldZ);
                int oceanFloorHeight = level.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, worldX, worldZ);
                int waterHeight = terrainHeight;

                int maxIslandTop = waterHeight + (int)(type.getMaxHeight() * 0.8);
                int maxIslandHeight = maxIslandTop - oceanFloorHeight;
                if (maxIslandHeight <= 0) continue;

                double nx = x / (double)(width / 2);
                double nz = z / (double)(length / 2);
                double radialFalloff = 1.0 - Math.pow(nx * nx + nz * nz, 0.4);
                double noiseValue = noise.getValue(x * 0.05, z * 0.05, 0);
                double shapeFactor = Math.min(Math.max(radialFalloff + noiseValue * 0.15, 0.0), 1.0);

                int columnHeight = Math.max((int)(shapeFactor * maxIslandHeight), 2);
                if (columnHeight < 1) continue;

                int baseY = oceanFloorHeight;
                int topY = baseY + columnHeight;

                // Seabed
                for (int y = oceanFloorHeight - 3; y < baseY; y++) {
                    BlockPos seabedPos = new BlockPos(worldX, y, worldZ);
                    double blend = random.nextDouble();
                    if (blend < 0.25) batcher.setBlock(seabedPos, Blocks.GRAVEL.defaultBlockState());
                    else if (blend < 0.5) batcher.setBlock(seabedPos, Blocks.SAND.defaultBlockState());
                    else if (blend < 0.75) batcher.setBlock(seabedPos, Blocks.DIRT.defaultBlockState());
                    else batcher.setBlock(seabedPos, Blocks.CLAY.defaultBlockState());
                }

                Block topBlock = Blocks.GRASS_BLOCK;

                // Column
                for (int y = baseY; y <= topY; y++) {
                    BlockPos pos = new BlockPos(worldX, y, worldZ);
                    if (y == topY) {
                        if (shapeFactor < 0.15 || y <= waterHeight) {
                            topBlock = Blocks.SAND;
                        } else {
                            switch (biomeType) {
                                case DESERT, BEACH -> topBlock = Blocks.SAND;
                                case SNOWY -> topBlock = Blocks.SNOW_BLOCK;
                                case SWAMP -> topBlock = Blocks.DIRT;
                                case MUSHROOM -> topBlock = Blocks.MYCELIUM;
                                default -> topBlock = Blocks.GRASS_BLOCK;
                            }
                        }
                        batcher.setBlock(pos, topBlock.defaultBlockState());
                    } else if (y > topY - 3) {
                        batcher.setBlock(pos, Blocks.DIRT.defaultBlockState());
                    } else {
                        double oreRoll = random.nextDouble();
                        if (oreRoll < 0.01) batcher.setBlock(pos, Blocks.COAL_ORE.defaultBlockState());
                        else if (oreRoll < 0.015) batcher.setBlock(pos, Blocks.IRON_ORE.defaultBlockState());
                        else if (oreRoll < 0.02) batcher.setBlock(pos, Blocks.COPPER_ORE.defaultBlockState());
                        else if (oreRoll < 0.022) batcher.setBlock(pos, Blocks.REDSTONE_ORE.defaultBlockState());
                        else if (oreRoll < 0.024) batcher.setBlock(pos, Blocks.LAPIS_ORE.defaultBlockState());
                        else if (oreRoll < 0.025) batcher.setBlock(pos, Blocks.GOLD_ORE.defaultBlockState());
                        else if (oreRoll < 0.026) batcher.setBlock(pos, Blocks.DIAMOND_ORE.defaultBlockState());
                        else if (oreRoll < 0.027) batcher.setBlock(pos, Blocks.EMERALD_ORE.defaultBlockState());
                        else if (oreRoll < 0.03) batcher.setBlock(pos, ModBlocks.SULPHUR_ORE.get().defaultBlockState());
                        else if (oreRoll < 0.06) batcher.setBlock(pos, ModBlocks.POTASSIUM_ORE.get().defaultBlockState());
                        else batcher.setBlock(pos, Blocks.STONE.defaultBlockState());
                    }
                }

                for (int y = baseY - 1; y > oceanFloorHeight - 5; y--) {
                    BlockPos fillPos = new BlockPos(worldX, y, worldZ);
                    if (level.isEmptyBlock(fillPos)) {
                        batcher.setBlock(fillPos, Blocks.STONE.defaultBlockState());
                    }
                }

                // Decorations and potential trees
                BlockPos decoPos = new BlockPos(worldX, topY + 1, worldZ);
                double decoRoll = random.nextDouble();

                boolean isGoodSoil = topBlock == Blocks.GRASS_BLOCK || topBlock == Blocks.DIRT
                        || topBlock == Blocks.COARSE_DIRT || topBlock == Blocks.PODZOL
                        || topBlock == Blocks.MYCELIUM;

                if (isGoodSoil) {
                    switch (biomeType) {
                        case FOREST -> {
                            if (decoRoll < 0.06) batcher.setBlock(decoPos, Blocks.TALL_GRASS.defaultBlockState());
                            else if (decoRoll < 0.08) batcher.setBlock(decoPos, Blocks.DANDELION.defaultBlockState());
                            else if (decoRoll < 0.10) batcher.setBlock(decoPos, Blocks.POPPY.defaultBlockState());
                        }
                        case SNOWY, TAIGA -> {
                            if (decoRoll < 0.06) batcher.setBlock(decoPos, Blocks.SWEET_BERRY_BUSH.defaultBlockState());
                            else if (decoRoll < 0.09) batcher.setBlock(decoPos, Blocks.SNOW.defaultBlockState());
                        }
                        case SWAMP -> {
                            if (decoRoll < 0.05) batcher.setBlock(decoPos, Blocks.FERN.defaultBlockState());
                        }
                        case JUNGLE -> {
                            if (decoRoll < 0.04) batcher.setBlock(decoPos, Blocks.MELON.defaultBlockState());
                            else if (decoRoll < 0.08) batcher.setBlock(decoPos, Blocks.FERN.defaultBlockState());
                        }
                        case PLAINS -> {
                            if (decoRoll < 0.04) batcher.setBlock(decoPos, Blocks.DANDELION.defaultBlockState());
                            else if (decoRoll < 0.07) batcher.setBlock(decoPos, Blocks.POPPY.defaultBlockState());
                            else if (decoRoll < 0.12) batcher.setBlock(decoPos, Blocks.TALL_GRASS.defaultBlockState());
                        }
                        case MUSHROOM -> {
                            if (decoRoll < 0.08) batcher.setBlock(decoPos, Blocks.RED_MUSHROOM.defaultBlockState());
                            else if (decoRoll < 0.14) batcher.setBlock(decoPos, Blocks.BROWN_MUSHROOM.defaultBlockState());
                        }
                        case MEADOW -> {
                            if (decoRoll < 0.05) batcher.setBlock(decoPos, Blocks.ALLIUM.defaultBlockState());
                            else if (decoRoll < 0.09) batcher.setBlock(decoPos, Blocks.CORNFLOWER.defaultBlockState());
                        }
                        case DARK_FOREST -> {
                            if (decoRoll < 0.05) batcher.setBlock(decoPos, Blocks.BROWN_MUSHROOM.defaultBlockState());
                            else if (decoRoll < 0.09) batcher.setBlock(decoPos, Blocks.FERN.defaultBlockState());
                        }
                        case SAVANNA -> {
                            if (decoRoll < 0.05) batcher.setBlock(decoPos, Blocks.DEAD_BUSH.defaultBlockState());
                        }
                    }

                    if (decoRoll < 0.02) {
                        batcher.setBlock(decoPos, ModBlocks.FLAX_FLOWER_BLOCK.get().defaultBlockState());
                    }

                    if (decoRoll < 0.03) {
                        // Save exact spot for tree or sapling, soil height + 1
                        treePositions.add(decoPos);
                    }
                }
            }
        }

        // Flush terrain + decorations first
        batcher.flush();

        // NOW reliably plant trees or saplings
        for (BlockPos treePos : treePositions) {
            BlockPos soil = treePos.below();
            if (!level.getBlockState(soil).isSolid()) continue;

            if (random.nextBoolean()) {
                generateTree(level, treePos, biomeType, random);
            } else {
                plantSapling(level, treePos, biomeType);
            }
        }
    }

    private static void generateTree(ServerLevel level, BlockPos pos, IslandBiomeType biomeType, RandomSource random) {
        ResourceKey<ConfiguredFeature<?, ?>> featureKey = switch (biomeType) {
            case FOREST -> random.nextDouble() < 0.2 ? TreeFeatures.FANCY_OAK : TreeFeatures.OAK;
            case SNOWY -> TreeFeatures.SPRUCE;
            case TAIGA -> random.nextDouble() < 0.2 ? TreeFeatures.PINE : TreeFeatures.SPRUCE;
            case SWAMP -> TreeFeatures.SWAMP_OAK;
            case JUNGLE -> random.nextDouble() < 0.2 ? TreeFeatures.MEGA_JUNGLE_TREE : TreeFeatures.JUNGLE_TREE;
            case SAVANNA -> TreeFeatures.ACACIA;
            case BIRCH -> TreeFeatures.BIRCH;
            case DARK_FOREST -> random.nextDouble() < 0.3 ? TreeFeatures.DARK_OAK : TreeFeatures.FANCY_OAK;
            case PLAINS -> random.nextDouble() < 0.2 ? TreeFeatures.FANCY_OAK : TreeFeatures.OAK;
            case MOUNTAINS -> TreeFeatures.SPRUCE;
            case MEADOW -> TreeFeatures.FANCY_OAK;
            default -> null;
        };

        if (featureKey == null) return;

        Registry<ConfiguredFeature<?, ?>> registry = level.registryAccess()
                .registryOrThrow(Registries.CONFIGURED_FEATURE);

        ConfiguredFeature<?, ?> feature = registry.get(featureKey);
        if (feature != null) {
            feature.place(level, level.getChunkSource().getGenerator(), random, pos);
        }
    }

    private static void plantSapling(ServerLevel level, BlockPos pos, IslandBiomeType biomeType) {
        Block sapling = switch (biomeType) {
            case FOREST, PLAINS, MEADOW -> Blocks.OAK_SAPLING;
            case SNOWY, TAIGA, MOUNTAINS -> Blocks.SPRUCE_SAPLING;
            case JUNGLE -> Blocks.JUNGLE_SAPLING;
            case SAVANNA -> Blocks.ACACIA_SAPLING;
            case BIRCH -> Blocks.BIRCH_SAPLING;
            case DARK_FOREST -> Blocks.DARK_OAK_SAPLING;
            case SWAMP -> Blocks.OAK_SAPLING;
            default -> null;
        };

        if (sapling != null && level.isEmptyBlock(pos)) {
            level.setBlock(pos, sapling.defaultBlockState(), 3);
        }
    }
}