package net.Aziuria.aziuriamod.island.decoration;

import net.Aziuria.aziuriamod.block.ModBlocks;
import net.Aziuria.aziuriamod.island.type.IslandBiomeType;
import net.Aziuria.aziuriamod.island.util.BlockBatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.util.RandomSource;

public class DecorationGenerator {

    // --- Entry method ---
    public static void placeDecorations(ServerLevel level, BlockPos pos, RandomSource random, IslandBiomeType biomeType, BlockBatcher batcher, Block topBlock) {
        if (!isSoil(topBlock)) return;

        double roll = random.nextDouble();

        // Soil type flags
        boolean landSoil = isLandSoil(topBlock);
        boolean sandSoil = isSand(topBlock);

        switch (biomeType) {
            case PLAINS, SUNFLOWER_PLAINS, FLOWER_FOREST -> placePlains(level, pos, roll, biomeType, random, batcher, landSoil);
            case FOREST, GROVE, WOODED_BADLANDS -> placeForest(level, pos, roll, batcher, landSoil);
            case MEADOW -> placeMeadow(level, pos, roll, batcher, landSoil);
            case SWAMP -> placeSwamp(level, pos, roll, batcher, landSoil);
            case JUNGLE, BAMBOO_JUNGLE, SPARSE_JUNGLE -> placeJungle(level, pos, roll, batcher, landSoil);
            case MUSHROOM -> placeMushroom(level, pos, roll, batcher, landSoil);
            case DARK_FOREST -> placeDarkForest(level, pos, roll, batcher, landSoil);
            case SAVANNA, SAVANNA_PLATEAU, WINDSWEPT_SAVANNA -> placeSavanna(level, pos, roll, batcher, landSoil, sandSoil);
            case TAIGA -> placeTaiga(level, pos, roll, batcher, landSoil);
            case SNOWY, MOUNTAINS -> placeSnowy(level, pos, roll, batcher, landSoil);
            case DESERT, BEACH -> placeDesertBeach(level, pos, roll, batcher, sandSoil);
            case CHERRY_GROVE -> placeCherryGrove(level, pos, roll, batcher, landSoil);
            default -> placeDefault(level, pos, roll, batcher, landSoil);
        }

        // --- Modded Plants ---

        // Flax Flower on specific biomes
        if (landSoil && (biomeType == IslandBiomeType.PLAINS
                || biomeType == IslandBiomeType.SUNFLOWER_PLAINS
                || biomeType == IslandBiomeType.MEADOW
                || biomeType == IslandBiomeType.FOREST
                || biomeType == IslandBiomeType.FLOWER_FOREST) && roll < 0.02) {
            batcher.setBlock(pos.above(), ModBlocks.FLAX_FLOWER_BLOCK.get().defaultBlockState());
        }

        // Yucca Plant on specific biomes
        if ((biomeType == IslandBiomeType.SAVANNA
                || biomeType == IslandBiomeType.SAVANNA_PLATEAU
                || biomeType == IslandBiomeType.DESERT
                || biomeType == IslandBiomeType.BADLANDS
                || biomeType == IslandBiomeType.BEACH) && roll < 0.03) {
            batcher.setBlock(pos.above(), ModBlocks.YUCCA_PLANT_BLOCK.get().defaultBlockState());
        }

        batcher.flush();
    }

    // --- Soil checks ---
    public static boolean isSoil(Block block) {
        return isLandSoil(block) || isSand(block);
    }

    public static boolean isLandSoil(Block block) {
        return block == Blocks.GRASS_BLOCK
                || block == Blocks.DIRT
                || block == Blocks.COARSE_DIRT
                || block == Blocks.PODZOL
                || block == Blocks.MYCELIUM;
    }

    public static boolean isSand(Block block) {
        return block == Blocks.SAND;
    }

    // --- Biome-specific placement methods ---
    private static void placePlains(ServerLevel level, BlockPos pos, double roll, IslandBiomeType biomeType, RandomSource random, BlockBatcher batcher, boolean landSoil) {
        if (!landSoil) return;
        if (roll < 0.03) batcher.setBlock(pos.above(), Blocks.DANDELION.defaultBlockState());
        else if (roll < 0.05) batcher.setBlock(pos.above(), Blocks.POPPY.defaultBlockState());
        else if (roll < 0.07) batcher.setBlock(pos.above(), Blocks.CORNFLOWER.defaultBlockState());
        else if (roll < 0.09) batcher.setBlock(pos.above(), Blocks.OXEYE_DAISY.defaultBlockState());
        else if (roll < 0.32) batcher.setBlock(pos.above(), Blocks.TALL_GRASS.defaultBlockState());
        else if (roll < 0.52) batcher.setBlock(pos.above(), Blocks.SHORT_GRASS.defaultBlockState());
    }

    private static void placeForest(ServerLevel level, BlockPos pos, double roll, BlockBatcher batcher, boolean landSoil) {
        if (!landSoil) return;
        if (roll < 0.10) batcher.setBlock(pos.above(), Blocks.TALL_GRASS.defaultBlockState());
        else if (roll < 0.12) batcher.setBlock(pos.above(), Blocks.DANDELION.defaultBlockState());
        else if (roll < 0.14) batcher.setBlock(pos.above(), Blocks.POPPY.defaultBlockState());
        else if (roll < 0.15) batcher.setBlock(pos.above(), Blocks.OXEYE_DAISY.defaultBlockState());
        else if (roll < 0.25) batcher.setBlock(pos.above(), Blocks.SHORT_GRASS.defaultBlockState());
    }

    private static void placeMeadow(ServerLevel level, BlockPos pos, double roll, BlockBatcher batcher, boolean landSoil) {
        if (!landSoil) return;
        if (roll < 0.01) batcher.setBlock(pos.above(), Blocks.ALLIUM.defaultBlockState());
        else if (roll < 0.02) batcher.setBlock(pos.above(), Blocks.CORNFLOWER.defaultBlockState());
        else if (roll < 0.05) batcher.setBlock(pos.above(), Blocks.DANDELION.defaultBlockState());
        else if (roll < 0.16) batcher.setBlock(pos.above(), Blocks.SHORT_GRASS.defaultBlockState());
    }

    private static void placeSwamp(ServerLevel level, BlockPos pos, double roll, BlockBatcher batcher, boolean landSoil) {
        if (!landSoil) return;
        if (roll < 0.02) batcher.setBlock(pos.above(), Blocks.FERN.defaultBlockState());
        else if (roll < 0.023) batcher.setBlock(pos.above(), Blocks.SHORT_GRASS.defaultBlockState());
        else if (roll < 0.03) batcher.setBlock(pos.above(), Blocks.RED_MUSHROOM.defaultBlockState());
    }

    private static void placeJungle(ServerLevel level, BlockPos pos, double roll, BlockBatcher batcher, boolean landSoil) {
        if (!landSoil) return;
        if (roll < 0.01) batcher.setBlock(pos.above(), Blocks.MELON.defaultBlockState());
        else if (roll < 0.12) batcher.setBlock(pos.above(), Blocks.SHORT_GRASS.defaultBlockState());
        else if (roll < 0.23) batcher.setBlock(pos.above(), Blocks.TALL_GRASS.defaultBlockState());
        else if (roll < 0.26) batcher.setBlock(pos.above(), Blocks.FERN.defaultBlockState());
        else if (roll < 0.30) batcher.setBlock(pos.above(), Blocks.BAMBOO.defaultBlockState());
    }

    private static void placeMushroom(ServerLevel level, BlockPos pos, double roll, BlockBatcher batcher, boolean landSoil) {
        if (!landSoil) return;
        if (roll < 0.01) batcher.setBlock(pos.above(), Blocks.RED_MUSHROOM.defaultBlockState());
        else if (roll < 0.2) batcher.setBlock(pos.above(), Blocks.BROWN_MUSHROOM.defaultBlockState());
        else batcher.setBlock(pos.above(), Blocks.MYCELIUM.defaultBlockState());
    }

    private static void placeDarkForest(ServerLevel level, BlockPos pos, double roll, BlockBatcher batcher, boolean landSoil) {
        if (!landSoil) return;
        if (roll < 0.14) batcher.setBlock(pos.above(), Blocks.SHORT_GRASS.defaultBlockState());
        else if (roll < 0.141) batcher.setBlock(pos.above(), Blocks.POPPY.defaultBlockState());
        else if (roll < 0.142) batcher.setBlock(pos.above(), Blocks.CORNFLOWER.defaultBlockState());
        else if (roll < 0.18) batcher.setBlock(pos.above(), Blocks.TALL_GRASS.defaultBlockState());
    }

    private static void placeSavanna(ServerLevel level, BlockPos pos, double roll, BlockBatcher batcher, boolean landSoil, boolean sandSoil) {
        if (!landSoil && !sandSoil) return;
        if (landSoil) {
            if (roll < 0.06) batcher.setBlock(pos.above(), Blocks.TALL_GRASS.defaultBlockState());
            else if (roll < 0.08) batcher.setBlock(pos.above(), Blocks.SHORT_GRASS.defaultBlockState());
        }
        if (sandSoil) {
            if (roll < 0.04) batcher.setBlock(pos.above(), Blocks.DEAD_BUSH.defaultBlockState());
            if (roll < 0.10) batcher.setBlock(pos.above(), Blocks.CACTUS.defaultBlockState());
        }
    }

    private static void placeTaiga(ServerLevel level, BlockPos pos, double roll, BlockBatcher batcher, boolean landSoil) {
        if (!landSoil) return;
        if (roll < 0.01) batcher.setBlock(pos.above(), Blocks.FERN.defaultBlockState());
        else if (roll < 0.11) batcher.setBlock(pos.above(), Blocks.TALL_GRASS.defaultBlockState());
        else if (roll < 0.13) batcher.setBlock(pos.above(), Blocks.SWEET_BERRY_BUSH.defaultBlockState());
    }

    private static void placeSnowy(ServerLevel level, BlockPos pos, double roll, BlockBatcher batcher, boolean landSoil) {
        if (!landSoil) return;
        if (roll < 0.04) batcher.setBlock(pos.above(), Blocks.SNOW.defaultBlockState());
        else if (roll < 0.041) batcher.setBlock(pos.above(), Blocks.SHORT_GRASS.defaultBlockState());
    }

    private static void placeDesertBeach(ServerLevel level, BlockPos pos, double roll, BlockBatcher batcher, boolean sandSoil) {
        if (!sandSoil) return;
        if (roll < 0.05) batcher.setBlock(pos.above(), Blocks.DEAD_BUSH.defaultBlockState());
        else if (roll < 0.07) batcher.setBlock(pos.above(), Blocks.CACTUS.defaultBlockState());
    }

    private static void placeCherryGrove(ServerLevel level, BlockPos pos, double roll, BlockBatcher batcher, boolean landSoil) {
        if (!landSoil) return;
        if (roll < 0.05) batcher.setBlock(pos.above(), Blocks.PINK_PETALS.defaultBlockState());
    }

    private static void placeDefault(ServerLevel level, BlockPos pos, double roll, BlockBatcher batcher, boolean landSoil) {
        if (!landSoil) return;
        if (roll < 0.15) batcher.setBlock(pos.above(), Blocks.TALL_GRASS.defaultBlockState());
        else if (roll < 0.151) batcher.setBlock(pos.above(), Blocks.FERN.defaultBlockState());
    }



}