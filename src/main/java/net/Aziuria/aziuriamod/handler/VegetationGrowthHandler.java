package net.Aziuria.aziuriamod.handler;

import net.Aziuria.aziuriamod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.Heightmap;
public class VegetationGrowthHandler {

    public static void spreadPlants(ServerLevel level) {
        RandomSource random = level.getRandom();

        level.players().forEach(player -> {
            BlockPos randomOffset = player.blockPosition().offset(
                    random.nextInt(-16, 16),
                    0,
                    random.nextInt(-16, 16)
            );

            BlockPos surfacePos = level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, randomOffset);
            BlockState groundState = level.getBlockState(surfacePos.below());

            if (!level.isEmptyBlock(surfacePos) || !groundState.is(Blocks.GRASS_BLOCK)) return;

            ResourceKey<Biome> biomeKey = level.getBiome(surfacePos).unwrapKey().orElse(null);
            if (biomeKey == null) return;

            ResourceLocation biomeID = biomeKey.location();
            String biomePath = biomeID.getPath();
            float chance = random.nextFloat();

            // Exclude Nether, End, Ocean biomes
            if (biomePath.contains("nether") || biomePath.contains("end")) return;



            // Special handling for warm ocean biome to spread grass on islands
            if (biomePath.contains("warm_ocean")) {
                // Custom check for islands in the warm ocean (island logic goes here)
                if (isOnIsland(level, surfacePos)) {
                    handleWarmOceanIslandGrowth(level, surfacePos, chance);
                }
                return; // Stop further biome checks if it's a warm ocean island
            }

            // Special handling for lukewarm ocean islands
            if (biomePath.contains("lukewarm_ocean")) {
                if (isOnIsland(level, surfacePos)) {
                    handleLukewarmOceanIslandGrowth(level, surfacePos, chance);
                }
                return; // Stop further checks for lukewarm ocean islands
            }

            // Special handling for tropical ocean islands (or generic ocean islands)
            if (biomePath.contains("ocean")) {
                // You can expand this further if needed for additional ocean-based islands
                if (isOnIsland(level, surfacePos)) {
                    handleOceanIslandGrowth(level, surfacePos, chance); // Assume `handleOceanIslandGrowth` is a function for generic ocean islands
                }
                return; // Stop further biome checks if it's an ocean island
            }

            // Biome-specific handlers
            if (biomePath.contains("plains")) {
                handlePlainsGrowth(level, surfacePos, chance);
            } else if (biomePath.contains("forest")) {
                handleForestGrowth(level, surfacePos, chance);
            } else if (biomePath.contains("taiga")) {
                handleTaigaGrowth(level, surfacePos, chance);
            } else if (biomePath.contains("swamp")) {
                handleSwampGrowth(level, surfacePos, chance);
            } else if (biomePath.contains("savanna")) {
                handleSavannaGrowth(level, surfacePos, chance);
            } else if (biomePath.contains("jungle")) {
                handleJungleGrowth(level, surfacePos, chance);
            } else if (biomePath.contains("meadow") || biomePath.contains("mountain") || biomePath.contains("hills")) {
                handleMeadowGrowth(level, surfacePos, chance);
            } else if (biomePath.contains("beach")) {
                handleBeachGrowth(level, surfacePos, chance);
            } else if (biomePath.contains("desert") || biomePath.contains("badlands")) {
                handleDesertGrowth(level, surfacePos, chance);
            } else if (biomePath.contains("snow") || biomePath.contains("frozen")) {
                handleSnowyGrowth(level, surfacePos, chance);
            } else if (biomePath.contains("river")) {
                handleRiverGrowth(level, surfacePos, chance);
            } else if (biomePath.contains("cherry")) {
                handleCherryGrowth(level, surfacePos, chance);
            } else if (biomePath.contains("lush")) {
                handleLushCaveGrowth(level, surfacePos, chance);
            } else {
                handleDefaultGrowth(level, surfacePos, chance);
            }
        });
    }

    // Utility method to check if the position is an island in warm ocean
    private static boolean isOnIsland(ServerLevel level, BlockPos pos) {
        // Check if the surrounding blocks are water (ocean biome), and the position is land (grass block).
        BlockState blockBelow = level.getBlockState(pos.below());
        return blockBelow.is(Blocks.GRASS_BLOCK);  // Check if the block below is a grass block (island-like behavior)
    }

    private static void handleOceanIslandGrowth(ServerLevel level, BlockPos pos, float chance) {
        handleGenericOceanIslandGrowth(level, pos, chance);
    }

    private static void handleWarmOceanIslandGrowth(ServerLevel level, BlockPos pos, float chance) {
        handleGenericOceanIslandGrowth(level, pos, chance);
    }

    private static void handleLukewarmOceanIslandGrowth(ServerLevel level, BlockPos pos, float chance) {
        handleGenericOceanIslandGrowth(level, pos, chance);
    }

    private static void handleGenericOceanIslandGrowth(ServerLevel level, BlockPos pos, float chance) {
        if (chance < 0.05f) level.setBlock(pos, Blocks.DANDELION.defaultBlockState(), 3);
        else if (chance < 0.10f) level.setBlock(pos, Blocks.POPPY.defaultBlockState(), 3);
        else if (chance < 0.15f) level.setBlock(pos, Blocks.OXEYE_DAISY.defaultBlockState(), 3);
        else if (chance < 0.20f) level.setBlock(pos, ModBlocks.FLAX_FLOWER_BLOCK.get().defaultBlockState(), 3);
        else if (chance < 0.25f) level.setBlock(pos, Blocks.CORNFLOWER.defaultBlockState(), 3);
        else if (chance < 0.30f) level.setBlock(pos, Blocks.TALL_GRASS.defaultBlockState(), 3);
    }

    private static void handlePlainsGrowth(ServerLevel level, BlockPos pos, float chance) {
        if (chance < 0.05f) level.setBlock(pos, Blocks.DANDELION.defaultBlockState(), 3);
        else if (chance < 0.10f) level.setBlock(pos, Blocks.POPPY.defaultBlockState(), 3);
        else if (chance < 0.15f) level.setBlock(pos, Blocks.OXEYE_DAISY.defaultBlockState(), 3);
        else if (chance < 0.20f) level.setBlock(pos, ModBlocks.FLAX_FLOWER_BLOCK.get().defaultBlockState(), 3);
        else if (chance < 0.25f) level.setBlock(pos, Blocks.CORNFLOWER.defaultBlockState(), 3);
        else if (chance < 0.30f) level.setBlock(pos, Blocks.TALL_GRASS.defaultBlockState(), 3);
    }

    private static void handleForestGrowth(ServerLevel level, BlockPos pos, float chance) {
        if (chance < 0.07f) level.setBlock(pos, Blocks.ALLIUM.defaultBlockState(), 3);
        else if (chance < 0.14f) level.setBlock(pos, Blocks.AZURE_BLUET.defaultBlockState(), 3);
        else if (chance < 0.20f) level.setBlock(pos, ModBlocks.FLAX_FLOWER_BLOCK.get().defaultBlockState(), 3);
        else if (chance < 0.21f) level.setBlock(pos, Blocks.OXEYE_DAISY.defaultBlockState(), 3);
        else if (chance < 0.30f) level.setBlock(pos, Blocks.TALL_GRASS.defaultBlockState(), 3);
    }

    private static void handleTaigaGrowth(ServerLevel level, BlockPos pos, float chance) {
        if (chance < 0.10f) level.setBlock(pos, Blocks.FERN.defaultBlockState(), 3);
        else if (chance < 0.15f) level.setBlock(pos, ModBlocks.FLAX_FLOWER_BLOCK.get().defaultBlockState(), 3);
        else if (chance < 0.20f) level.setBlock(pos, Blocks.TALL_GRASS.defaultBlockState(), 3);
    }

    private static void handleSwampGrowth(ServerLevel level, BlockPos pos, float chance) {
        if (chance < 0.12f) level.setBlock(pos, Blocks.BLUE_ORCHID.defaultBlockState(), 3);
        else if (chance < 0.20f) level.setBlock(pos, ModBlocks.FLAX_FLOWER_BLOCK.get().defaultBlockState(), 3);
        else if (chance < 0.22f) level.setBlock(pos, Blocks.TALL_GRASS.defaultBlockState(), 3);
    }

    private static void handleSavannaGrowth(ServerLevel level, BlockPos pos, float chance) {
        if (chance < 0.20f) level.setBlock(pos, Blocks.TALL_GRASS.defaultBlockState(), 3);
        else if (chance < 0.21f) level.setBlock(pos, ModBlocks.FLAX_FLOWER_BLOCK.get().defaultBlockState(), 3);
    }

    private static void handleJungleGrowth(ServerLevel level, BlockPos pos, float chance) {
        if (chance < 0.12f) level.setBlock(pos, Blocks.FERN.defaultBlockState(), 3);
        else if (chance < 0.14f) level.setBlock(pos, ModBlocks.FLAX_FLOWER_BLOCK.get().defaultBlockState(), 3);
        else if (chance < 0.20f) level.setBlock(pos, Blocks.TALL_GRASS.defaultBlockState(), 3);
    }

    private static void handleMeadowGrowth(ServerLevel level, BlockPos pos, float chance) {
        if (chance < 0.05f) level.setBlock(pos, Blocks.CORNFLOWER.defaultBlockState(), 3);
        else if (chance < 0.10f) level.setBlock(pos, Blocks.ALLIUM.defaultBlockState(), 3);
        else if (chance < 0.15f) level.setBlock(pos, Blocks.DANDELION.defaultBlockState(), 3);
        else if (chance < 0.17f) level.setBlock(pos, ModBlocks.FLAX_FLOWER_BLOCK.get().defaultBlockState(), 3);
        else if (chance < 0.20f) level.setBlock(pos, Blocks.POPPY.defaultBlockState(), 3);
        else if (chance < 0.30f) level.setBlock(pos, Blocks.TALL_GRASS.defaultBlockState(), 3);
    }

    private static void handleBeachGrowth(ServerLevel level, BlockPos pos, float chance) {
        if (chance < 0.10f) level.setBlock(pos, Blocks.DEAD_BUSH.defaultBlockState(), 3);
    }

    private static void handleDesertGrowth(ServerLevel level, BlockPos pos, float chance) {
        if (chance < 0.10f) level.setBlock(pos, Blocks.DEAD_BUSH.defaultBlockState(), 3);
    }

    private static void handleSnowyGrowth(ServerLevel level, BlockPos pos, float chance) {
        if (chance < 0.10f) level.setBlock(pos, Blocks.TALL_GRASS.defaultBlockState(), 3);
    }

    private static void handleRiverGrowth(ServerLevel level, BlockPos pos, float chance) {
        if (chance < 0.10f) level.setBlock(pos, Blocks.SUGAR_CANE.defaultBlockState(), 3);
        else if (chance < 0.11f) level.setBlock(pos, Blocks.POPPY.defaultBlockState(), 3);
        else if (chance < 0.12f) level.setBlock(pos, Blocks.DANDELION.defaultBlockState(), 3);
        else if (chance < 0.15f) level.setBlock(pos, Blocks.OXEYE_DAISY.defaultBlockState(), 3);
        else if (chance < 0.20f) level.setBlock(pos, ModBlocks.FLAX_FLOWER_BLOCK.get().defaultBlockState(), 3);
        else if (chance < 0.25f) level.setBlock(pos, Blocks.CORNFLOWER.defaultBlockState(), 3);
        else if (chance < 0.30f) level.setBlock(pos, Blocks.TALL_GRASS.defaultBlockState(), 3);
    }

    private static void handleCherryGrowth(ServerLevel level, BlockPos pos, float chance) {
        if (chance < 0.15f) level.setBlock(pos, Blocks.PINK_PETALS.defaultBlockState(), 3);
    }

    private static void handleLushCaveGrowth(ServerLevel level, BlockPos pos, float chance) {
        if (chance < 0.10f) level.setBlock(pos, Blocks.AZALEA.defaultBlockState(), 3);
        else if (chance < 0.20f) level.setBlock(pos, Blocks.FLOWERING_AZALEA.defaultBlockState(), 3);
    }

    private static void handleDefaultGrowth(ServerLevel level, BlockPos pos, float chance) {
        if (chance < 0.10f) level.setBlock(pos, Blocks.TALL_GRASS.defaultBlockState(), 3);
    }
}