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
                int waterHeight = level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, worldX, worldZ);

                int maxIslandTop = waterHeight + 5;
                int maxIslandHeight = maxIslandTop - oceanFloorHeight;
                if (maxIslandHeight <= 0) continue; // skip very shallow ocean

                double nx = x / (double)(width / 2);
                double nz = z / (double)(length / 2);
                double radialFalloff = 1.0 - Math.pow(nx * nx + nz * nz, 0.4);
                double noiseValue = noise.getValue(x * 0.05, z * 0.05, 0);
                double shapeFactor = Math.min(Math.max(radialFalloff + noiseValue * 0.15, 0.0), 1.0);

// Scale island height by shape
                int columnHeight = Math.max((int)(shapeFactor * maxIslandHeight), 2);
                if (columnHeight < 1) continue;

                int baseY = oceanFloorHeight;
                int topY = baseY + columnHeight;

// Ocean floor smoothing: optional slight elevation before island base
                for (int y = oceanFloorHeight - 3; y < baseY; y++) {
                    BlockPos seabedPos = new BlockPos(worldX, y, worldZ);
                    double blend = random.nextDouble();
                    if (blend < 0.25) batcher.setBlock(seabedPos, Blocks.GRAVEL.defaultBlockState());
                    else if (blend < 0.5) batcher.setBlock(seabedPos, Blocks.SAND.defaultBlockState());
                    else if (blend < 0.75) batcher.setBlock(seabedPos, Blocks.DIRT.defaultBlockState());
                    else batcher.setBlock(seabedPos, Blocks.CLAY.defaultBlockState());
                }

// Terrain shaping from ocean floor to island top
                for (int y = baseY; y <= topY; y++) {
                    BlockPos pos = new BlockPos(worldX, y, worldZ);
                    double verticalRatio = (y - baseY) / (double)(columnHeight);

                    if (y == topY) {
                        // Top layer
                        if (shapeFactor < 0.15 || y <= waterHeight) {
                            batcher.setBlock(pos, Blocks.SAND.defaultBlockState());
                        } else {
                            switch (biomeType) {
                                case DESERT, BEACH -> batcher.setBlock(pos, Blocks.SAND.defaultBlockState());
                                case SNOWY -> batcher.setBlock(pos, Blocks.SNOW_BLOCK.defaultBlockState());
                                case SWAMP -> batcher.setBlock(pos, Blocks.DIRT.defaultBlockState());
                                case MUSHROOM -> batcher.setBlock(pos, Blocks.MYCELIUM.defaultBlockState());
                                default -> batcher.setBlock(pos, Blocks.GRASS_BLOCK.defaultBlockState());
                            }
                        }
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

// Decorations
                BlockPos decoPos = new BlockPos(worldX, baseY + columnHeight + 1, worldZ);
                double decoRoll = random.nextDouble();
                Block blockBelow = level.getBlockState(decoPos.below()).getBlock();
                boolean isSkyAboveClear = level.isEmptyBlock(decoPos) && level.isEmptyBlock(decoPos.above());

                if (isSkyAboveClear && (blockBelow == Blocks.GRASS_BLOCK || blockBelow == Blocks.MYCELIUM)) {
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
                            else if (decoRoll < 0.08) batcher.setBlock(decoPos, Blocks.LILY_PAD.defaultBlockState());
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

                    // Boosted flax generation
                    if (decoRoll < 0.02) {
                        batcher.setBlock(decoPos, ModBlocks.FLAX_FLOWER_BLOCK.get().defaultBlockState());
                    }
                }

// Boosted tree generation chance
                if (decoRoll < 0.03) {
                    generateTree(level, decoPos, biomeType, random);
                }
            }
        }

        batcher.flush();
    }

    private static void generateTree(ServerLevel level, BlockPos pos, IslandBiomeType biomeType, RandomSource random) {
        if (!level.getBlockState(pos.below()).isSolid()) return;

        ResourceKey<ConfiguredFeature<?, ?>> featureKey = switch (biomeType) {
            case FOREST       -> random.nextDouble() < 0.2 ? TreeFeatures.FANCY_OAK : TreeFeatures.OAK;
            case SNOWY        -> TreeFeatures.SPRUCE;
            case TAIGA        -> random.nextDouble() < 0.2 ? TreeFeatures.PINE : TreeFeatures.SPRUCE;
            case SWAMP        -> TreeFeatures.SWAMP_OAK;
            case JUNGLE       -> random.nextDouble() < 0.2 ? TreeFeatures.MEGA_JUNGLE_TREE : TreeFeatures.JUNGLE_TREE;
            case SAVANNA      -> TreeFeatures.ACACIA;
            case BIRCH        -> TreeFeatures.BIRCH;
            case DARK_FOREST  -> random.nextDouble() < 0.3 ? TreeFeatures.DARK_OAK : TreeFeatures.FANCY_OAK;
            case PLAINS       -> random.nextDouble() < 0.2 ? TreeFeatures.FANCY_OAK : TreeFeatures.OAK;
            case MOUNTAINS    -> TreeFeatures.SPRUCE;
            case MEADOW       -> TreeFeatures.FANCY_OAK;
            case BEACH, DESERT, MUSHROOM -> null;
            default           -> TreeFeatures.OAK;
        };

        if (featureKey == null) return;

        Registry<ConfiguredFeature<?, ?>> registry = level.registryAccess()
                .registryOrThrow(Registries.CONFIGURED_FEATURE);

        ConfiguredFeature<?, ?> feature = registry.get(featureKey);
        if (feature != null) {
            feature.place(level, level.getChunkSource().getGenerator(), random, pos);
        }
    }
}