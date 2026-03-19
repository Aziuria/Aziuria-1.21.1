package net.Aziuria.aziuriamod.handler.blocks;

import net.Aziuria.aziuriamod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SeaPickleBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.Fluids;

public class VegetationGrowthHandler {

    public static void spreadPlants(ServerLevel level) {

        RandomSource random = level.getRandom();

        level.players().forEach(player -> {
            BlockPos randomOffset = player.blockPosition().offset(
                    random.nextInt(-64, 64),
                    0,
                    random.nextInt(-64, 64)
            );

            BlockPos surfacePos = level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, randomOffset);
            BlockState groundState = level.getBlockState(surfacePos.below());

            if (!level.isEmptyBlock(surfacePos) || !groundState.is(Blocks.GRASS_BLOCK)) return;

            ResourceKey<Biome> biomeKey = level.getBiome(surfacePos).unwrapKey().orElse(null);
            if (biomeKey == null) return;

            ResourceLocation biomeID = biomeKey.location();
            String biomePath = biomeID.getPath();
            float chance = random.nextFloat();

            if (biomeID.equals(ResourceLocation.fromNamespaceAndPath(
                    "aziuriamod", "spectral_soulbound_forest"
            ))) {
                handleSpectralSoulboundForestGrowth(level, surfacePos, chance);
                return;
            }

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
            if (biomePath.contains("sunflower_plains")) {
                handleSunflowerPlainsGrowth(level, surfacePos, chance);
            } else if (biomePath.contains("cherry")) {
                handleCherryGrowth(level, surfacePos, chance);
            } else if (biomePath.equals("dark_forest")) {
                handleDarkForestGrowth(level, surfacePos, chance);
            } else if (biomePath.contains("lush")) {
                handleLushCaveGrowth(level, surfacePos, chance);
            } else if (biomePath.equals("flower_forest")) {
                handleFlowerForestGrowth(level, surfacePos, chance);
            } else if (biomePath.equals("windswept")) {
                handleWindsweptGrowth(level, surfacePos, chance);
            } else if (biomePath.contains("taiga")) {
                handleTaigaGrowth(level, surfacePos, chance);
            } else if (biomePath.contains("swamp")) {
                handleSwampGrowth(level, surfacePos, chance);
            } else if (biomePath.contains("savanna")) {
                handleSavannaGrowth(level, surfacePos, chance);
            } else if (biomePath.contains("jungle")) {
                handleJungleGrowth(level, surfacePos, chance);
            } else if (biomePath.equals("river")) {
                handleRiverGrowth(level, surfacePos, chance);
            } else if (biomePath.contains("snow") || biomePath.contains("frozen")) {
                handleSnowyGrowth(level, surfacePos, chance);
            } else if (biomePath.contains("meadow") || biomePath.contains("mountain")) {
                handleMeadowGrowth(level, surfacePos, chance);
            } else if (biomePath.contains("beach")) {
                handleBeachGrowth(level, surfacePos, chance);
            } else if (biomePath.contains("desert") || biomePath.contains("badlands")) {
                handleDesertGrowth(level, surfacePos, chance);
            } else if (biomePath.contains("forest")) {
                handleForestGrowth(level, surfacePos, chance);
            } else if (biomePath.contains("plains")) {
                handlePlainsGrowth(level, surfacePos, chance);
            } else {
                handleDefaultGrowth(level, surfacePos, chance);
            }

            // --- EXTRA SURFACE PLACEMENT DURING RAIN (FROM HERE) ---
            if (level.isRaining() && level.isRainingAt(surfacePos)) {
                // Only place on the surface if empty
                if (level.isEmptyBlock(surfacePos) || level.getBlockState(surfacePos).isAir()) {
                    float rainChance = random.nextFloat();

                    if (biomeID.equals(ResourceLocation.fromNamespaceAndPath(
                            "aziuriamod", "spectral_soulbound_forest"
                    ))) {
                        handleSpectralSoulboundForestGrowth(level, surfacePos, rainChance);
                    }

                    // Call the same biome handler again at the surface position
                    if (biomePath.contains("sunflower_plains")) {
                        handleSunflowerPlainsGrowth(level, surfacePos, rainChance);
                    } else if (biomePath.contains("cherry")) {
                        handleCherryGrowth(level, surfacePos, rainChance);
                    } else if (biomePath.equals("dark_forest")) {
                        handleDarkForestGrowth(level, surfacePos, rainChance);
                    } else if (biomePath.contains("lush")) {
                        handleLushCaveGrowth(level, surfacePos, rainChance);
                    } else if (biomePath.equals("flower_forest")) {
                        handleFlowerForestGrowth(level, surfacePos, rainChance);
                    } else if (biomePath.equals("windswept")) {
                        handleWindsweptGrowth(level, surfacePos, rainChance);
                    } else if (biomePath.contains("taiga")) {
                        handleTaigaGrowth(level, surfacePos, rainChance);
                    } else if (biomePath.contains("swamp")) {
                        handleSwampGrowth(level, surfacePos, rainChance);
                    } else if (biomePath.contains("savanna")) {
                        handleSavannaGrowth(level, surfacePos, rainChance);
                    } else if (biomePath.contains("jungle")) {
                        handleJungleGrowth(level, surfacePos, rainChance);
                    } else if (biomePath.equals("river")) {
                        handleRiverGrowth(level, surfacePos, rainChance);
                    } else if (biomePath.contains("snow") || biomePath.contains("frozen")) {
                        handleSnowyGrowth(level, surfacePos, rainChance);
                    } else if (biomePath.contains("meadow") || biomePath.contains("mountain")) {
                        handleMeadowGrowth(level, surfacePos, rainChance);
                    } else if (biomePath.contains("beach")) {
                        handleBeachGrowth(level, surfacePos, rainChance);
                    } else if (biomePath.contains("desert") || biomePath.contains("badlands")) {
                        handleDesertGrowth(level, surfacePos, rainChance);
                    } else if (biomePath.contains("forest")) {
                        handleForestGrowth(level, surfacePos, rainChance);
                    } else if (biomePath.contains("plains")) {
                        handlePlainsGrowth(level, surfacePos, rainChance);
                    } else {
                        handleDefaultGrowth(level, surfacePos, rainChance);
                    }
                }
            }

            //(TO HERE ALL RAIN SEGMENT JUST IN CASE NEED REMOVING)

        });
    }

    public static void spreadUnderwaterPlants(ServerLevel level) {
        RandomSource random = level.getRandom();

        level.players().forEach(player -> {
            // ✅ Single random offset per player (instead of 30 loops)
            BlockPos randomOffset = player.blockPosition().offset(
                    random.nextInt(-64, 64),
                    0,
                    random.nextInt(-64, 64)
            );

            // Ocean floor heightmap
            BlockPos seaFloor = level.getHeightmapPos(Heightmap.Types.OCEAN_FLOOR, randomOffset);
            ResourceKey<Biome> biomeKey = level.getBiome(seaFloor).unwrapKey().orElse(null);
            String biomePath = biomeKey != null ? biomeKey.location().getPath() : "unknown";
            float roll = random.nextFloat();

            ResourceLocation biomeID = biomeKey.location();

            // ===== ADDED: SPECTRAL BIOME CHECK (UNDERWATER) =====
            boolean isSpectralSoulboundForest = biomeID.equals(
                    ResourceLocation.fromNamespaceAndPath("aziuriamod", "spectral_soulbound_forest")
            );

            // Skip if not underwater
            if (!level.getFluidState(seaFloor).is(Fluids.WATER)) return;

            // --- Kelp growth (ocean variants) ---
            boolean kelpAllowed =
                    biomePath.contains("ocean")
                            || biomePath.contains("deep_ocean")
                            || biomePath.contains("cold_ocean")
                            || biomePath.contains("warm_ocean")
                            || isSpectralSoulboundForest;
            if (kelpAllowed && roll < 0.15f) {
                level.setBlock(seaFloor, Blocks.KELP_PLANT.defaultBlockState(), 3);
                BlockPos growPos = seaFloor.above();
                int extraHeight = 1 + random.nextInt(5);
                for (int h = 0; h < extraHeight; h++) {
                    if (level.getFluidState(growPos).is(Fluids.WATER)) {
                        level.setBlock(growPos, Blocks.KELP.defaultBlockState(), 3);
                        growPos = growPos.above();
                    } else break;
                }
                return;
            }

// --- Seagrass growth (any water) ---
            if (roll < 0.35f) {
                if (random.nextBoolean()) {
                    // Normal seagrass
                    level.setBlock(seaFloor, Blocks.SEAGRASS.defaultBlockState(), 3);
                } else {
                    // Proper tall seagrass placement
                    placeTallSeagrass(level, seaFloor);
                }
            }

            // --- Sea pickles (warm ocean only) ---
            if ((biomePath.contains("warm_ocean") || biomePath.contains("deep_warm_ocean")) && roll > 0.95f) {
                level.setBlock(
                        seaFloor,
                        Blocks.SEA_PICKLE.defaultBlockState().setValue(SeaPickleBlock.PICKLES, 1 + random.nextInt(4)),
                        3
                );
            }
        });
    }

    public static void spreadSugarCane(ServerLevel level) {
        RandomSource random = level.getRandom();

        level.players().forEach(player -> {
            BlockPos randomOffset = player.blockPosition().offset(
                    random.nextInt(-64, 64),
                    0,
                    random.nextInt(-64, 64)
            );

            // Use ocean floor heightmap for water-adjacent blocks
            BlockPos surfacePos = level.getHeightmapPos(Heightmap.Types.OCEAN_FLOOR, randomOffset);
            BlockState ground = level.getBlockState(surfacePos.below());

            // Only spawn on sand, dirt, or grass
            if (!ground.is(Blocks.SAND) && !ground.is(Blocks.DIRT) && !ground.is(Blocks.GRASS_BLOCK)) return;

            // Must be next to water (checks one block below adjacent blocks like vanilla)
            boolean canGrow = false;
            for (Direction dir : Direction.Plane.HORIZONTAL) {
                BlockPos adjPos = surfacePos.below().relative(dir);
                if (level.getFluidState(adjPos).is(Fluids.WATER)) {
                    canGrow = true;
                    break;
                }
            }
            if (!canGrow) return;

            // Avoid snow/frozen/ice biomes
            ResourceKey<Biome> biomeKey = level.getBiome(surfacePos).unwrapKey().orElse(null);
            if (biomeKey != null) {
                String biomePath = biomeKey.location().getPath();
                if (biomePath.contains("snow") || biomePath.contains("frozen") || biomePath.contains("ice")) return;
            }

            // Only place if the block is empty
            if (!level.isEmptyBlock(surfacePos)) return;

            // Chance roll (50% default for natural growth)
            if (random.nextFloat() < 0.15f) {
                // Place sugar cane as 1 block tall
                level.setBlock(surfacePos, Blocks.SUGAR_CANE.defaultBlockState(), 3);
            }
        });
    }

    // Utility method to check if the position is an island in warm ocean
    private static boolean isOnIsland(ServerLevel level, BlockPos pos) {
        // Check if the surrounding blocks are water (ocean biome), and the position is land (grass block).
        BlockState blockBelow = level.getBlockState(pos.below());
        return blockBelow.is(Blocks.GRASS_BLOCK);  // Check if the block below is a grass block (island-like behavior)
    }

    // ===== HELPER METHOD: SAFE DOUBLE PLANT PLACEMENT =====
    private static void placeDoublePlant(ServerLevel level, BlockPos pos, BlockState state) {

        // Ensure space above for the upper half
        if (!level.isEmptyBlock(pos.above())) return;

        if (!state.canSurvive(level, pos)) return; // changes

        // Place both halves correctly
        level.setBlock(pos, state.setValue(net.minecraft.world.level.block.DoublePlantBlock.HALF,
                net.minecraft.world.level.block.state.properties.DoubleBlockHalf.LOWER), 3);

        level.setBlock(pos.above(), state.setValue(net.minecraft.world.level.block.DoublePlantBlock.HALF,
                net.minecraft.world.level.block.state.properties.DoubleBlockHalf.UPPER), 3);
    }

    private static void placeTallSeagrass(ServerLevel level, BlockPos pos) {

        // ✅ SAFETY 1: must be water at base
        if (!level.getFluidState(pos).is(Fluids.WATER)) return;

        // ✅ SAFETY 2: must be water above for upper half
        if (!level.getFluidState(pos.above()).is(Fluids.WATER)) return;

        // Place lower half
        level.setBlock(pos,
                Blocks.TALL_SEAGRASS.defaultBlockState().setValue(
                        net.minecraft.world.level.block.DoublePlantBlock.HALF,
                        net.minecraft.world.level.block.state.properties.DoubleBlockHalf.LOWER
                ), 3);

        // Place upper half
        level.setBlock(pos.above(),
                Blocks.TALL_SEAGRASS.defaultBlockState().setValue(
                        net.minecraft.world.level.block.DoublePlantBlock.HALF,
                        net.minecraft.world.level.block.state.properties.DoubleBlockHalf.UPPER
                ), 3);
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
        else if (chance < 0.20f) level.setBlock(pos, Blocks.CORNFLOWER.defaultBlockState(), 3);
        else if (chance < 0.55f) placeDoublePlant(level, pos, Blocks.TALL_GRASS.defaultBlockState());
        else if (chance < 0.70f) level.setBlock(pos, Blocks.SHORT_GRASS.defaultBlockState(), 3);
    }

    private static void handleWindsweptGrowth(ServerLevel level, BlockPos pos, float chance) {
        if (chance < 0.04f) level.setBlock(pos, Blocks.DANDELION.defaultBlockState(), 3);
        else if (chance < 0.08f) level.setBlock(pos, Blocks.POPPY.defaultBlockState(), 3);
        else if (chance < 0.18f) level.setBlock(pos, Blocks.FERN.defaultBlockState(), 3);
        else if (chance < 0.55f) placeDoublePlant(level, pos, Blocks.TALL_GRASS.defaultBlockState());
        else if (chance < 0.70f) level.setBlock(pos, Blocks.SHORT_GRASS.defaultBlockState(), 3);
    }

    private static void handleSunflowerPlainsGrowth(ServerLevel level, BlockPos pos, float chance) {
        if (chance < 0.25f) placeDoublePlant(level, pos, Blocks.SUNFLOWER.defaultBlockState());
        else if (chance < 0.35f) level.setBlock(pos, Blocks.DANDELION.defaultBlockState(), 3); // Dandelions
        else if (chance < 0.45f) level.setBlock(pos, Blocks.POPPY.defaultBlockState(), 3); // Poppies
        else if (chance < 0.50f) level.setBlock(pos, Blocks.CORNFLOWER.defaultBlockState(), 3); // Cornflowers (rare)
        else if (chance < 0.55f) level.setBlock(pos, ModBlocks.FLAX_FLOWER_BLOCK.get().defaultBlockState(), 3); // Optional Flax
        else if (chance < 0.80f) placeDoublePlant(level, pos, Blocks.TALL_GRASS.defaultBlockState());
        else if (chance < 1.00f) level.setBlock(pos, Blocks.SHORT_GRASS.defaultBlockState(), 3); // Short grass filler
    }

    private static void handleFlowerForestGrowth(ServerLevel level, BlockPos pos, float chance) {
        if (chance < 0.04f) level.setBlock(pos, Blocks.DANDELION.defaultBlockState(), 3);
        else if (chance < 0.08f) level.setBlock(pos, Blocks.POPPY.defaultBlockState(), 3);
        else if (chance < 0.12f) level.setBlock(pos, Blocks.ALLIUM.defaultBlockState(), 3);
        else if (chance < 0.16f) level.setBlock(pos, Blocks.AZURE_BLUET.defaultBlockState(), 3);
        else if (chance < 0.19f) level.setBlock(pos, Blocks.LILY_OF_THE_VALLEY.defaultBlockState(), 3);
        else if (chance < 0.23f) level.setBlock(pos, Blocks.OXEYE_DAISY.defaultBlockState(), 3);
        else if (chance < 0.27f) level.setBlock(pos, Blocks.RED_TULIP.defaultBlockState(), 3);
        else if (chance < 0.31f) level.setBlock(pos, Blocks.ORANGE_TULIP.defaultBlockState(), 3);
        else if (chance < 0.35f) level.setBlock(pos, Blocks.WHITE_TULIP.defaultBlockState(), 3);
        else if (chance < 0.39f) level.setBlock(pos, Blocks.PINK_TULIP.defaultBlockState(), 3);
        else if (chance < 0.44f) level.setBlock(pos, ModBlocks.FLAX_FLOWER_BLOCK.get().defaultBlockState(), 3);
        else if (chance < 0.48f) level.setBlock(pos, Blocks.CORNFLOWER.defaultBlockState(), 3);
        else if (chance < 0.68f) placeDoublePlant(level, pos, Blocks.TALL_GRASS.defaultBlockState());
        else if (chance < 0.88f) level.setBlock(pos, Blocks.SHORT_GRASS.defaultBlockState(), 3);
        else if (chance < 0.91f) placeDoublePlant(level, pos, Blocks.LILAC.defaultBlockState());
        else if (chance < 0.94f) placeDoublePlant(level, pos, Blocks.ROSE_BUSH.defaultBlockState());
        else if (chance < 0.97f) placeDoublePlant(level, pos, Blocks.PEONY.defaultBlockState());
    }

    private static void handlePlainsGrowth(ServerLevel level, BlockPos pos, float chance) {
        if (chance < 0.05f) level.setBlock(pos, Blocks.DANDELION.defaultBlockState(), 3);
        else if (chance < 0.10f) level.setBlock(pos, Blocks.POPPY.defaultBlockState(), 3);
        else if (chance < 0.15f) level.setBlock(pos, Blocks.OXEYE_DAISY.defaultBlockState(), 3);
        else if (chance < 0.20f) level.setBlock(pos, Blocks.AZURE_BLUET.defaultBlockState(), 3);
        else if (chance < 0.25f) level.setBlock(pos, ModBlocks.FLAX_FLOWER_BLOCK.get().defaultBlockState(), 3);
        else if (chance < 0.30f) level.setBlock(pos, Blocks.CORNFLOWER.defaultBlockState(), 3);
        else if (chance < 0.45f) placeDoublePlant(level, pos, Blocks.TALL_GRASS.defaultBlockState());
        else if (chance < 0.80f) level.setBlock(pos, Blocks.SHORT_GRASS.defaultBlockState(), 3);
    }

    private static void handleForestGrowth(ServerLevel level, BlockPos pos, float chance) {
        if (chance < 0.05f) level.setBlock(pos, Blocks.DANDELION.defaultBlockState(), 3);
        else if (chance < 0.10f) level.setBlock(pos, Blocks.POPPY.defaultBlockState(), 3);
        else if (chance < 0.14f) level.setBlock(pos, Blocks.CORNFLOWER.defaultBlockState(), 3);
        else if (chance < 0.19f) level.setBlock(pos, ModBlocks.FLAX_FLOWER_BLOCK.get().defaultBlockState(), 3);
        else if (chance < 0.21f) level.setBlock(pos, Blocks.LILY_OF_THE_VALLEY.defaultBlockState(), 3);
        else if (chance < 0.23f) placeDoublePlant(level, pos, Blocks.LILAC.defaultBlockState());
        else if (chance < 0.25f) placeDoublePlant(level, pos, Blocks.PEONY.defaultBlockState());
        else if (chance < 0.27f) placeDoublePlant(level, pos, Blocks.ROSE_BUSH.defaultBlockState());
        else if (chance < 0.40f) level.setBlock(pos, Blocks.SHORT_GRASS.defaultBlockState(), 3);
        else if (chance < 0.60f) placeDoublePlant(level, pos, Blocks.TALL_GRASS.defaultBlockState());
    }

    private static void handleTaigaGrowth(ServerLevel level, BlockPos pos, float chance) {
        if (chance < 0.10f) level.setBlock(pos, Blocks.FERN.defaultBlockState(), 3);
        else if (chance < 0.20f) level.setBlock(pos, Blocks.SHORT_GRASS.defaultBlockState(), 3);
        else if (chance < 0.30f) placeDoublePlant(level, pos, Blocks.TALL_GRASS.defaultBlockState());
    }

    private static void handleSwampGrowth(ServerLevel level, BlockPos pos, float chance) {
        if (chance < 0.12f) level.setBlock(pos, Blocks.BLUE_ORCHID.defaultBlockState(), 3);
        else if (chance < 0.22f) placeDoublePlant(level, pos, Blocks.TALL_GRASS.defaultBlockState());
        else if (chance < 0.32f) level.setBlock(pos, Blocks.SHORT_GRASS.defaultBlockState(), 3);
    }

    private static void handleSavannaGrowth(ServerLevel level, BlockPos pos, float chance) {
        if (chance < 0.20f) placeDoublePlant(level, pos, Blocks.TALL_GRASS.defaultBlockState());
        else if (chance < 0.40f) level.setBlock(pos, Blocks.SHORT_GRASS.defaultBlockState(), 3);
        else if (chance < 0.42f) level.setBlock(pos, ModBlocks.FLAX_FLOWER_BLOCK.get().defaultBlockState(), 3);
        else if (chance < 0.44f) level.setBlock(pos, ModBlocks.YUCCA_PLANT_BLOCK.get().defaultBlockState(), 3);
    }

    private static void handleJungleGrowth(ServerLevel level, BlockPos pos, float chance) {
        if (chance < 0.12f) level.setBlock(pos, Blocks.FERN.defaultBlockState(), 3);
        else if (chance < 0.22f) placeDoublePlant(level, pos, Blocks.TALL_GRASS.defaultBlockState());
        else if (chance < 0.40f) level.setBlock(pos, Blocks.SHORT_GRASS.defaultBlockState(), 3);
    }

    private static void handleMeadowGrowth(ServerLevel level, BlockPos pos, float chance) {
        if (chance < 0.05f) level.setBlock(pos, Blocks.CORNFLOWER.defaultBlockState(), 3);
        else if (chance < 0.10f) level.setBlock(pos, Blocks.ALLIUM.defaultBlockState(), 3);
        else if (chance < 0.15f) level.setBlock(pos, Blocks.DANDELION.defaultBlockState(), 3);
        else if (chance < 0.17f) level.setBlock(pos, ModBlocks.FLAX_FLOWER_BLOCK.get().defaultBlockState(), 3);
        else if (chance < 0.20f) level.setBlock(pos, Blocks.POPPY.defaultBlockState(), 3);
        else if (chance < 0.30f) placeDoublePlant(level, pos, Blocks.TALL_GRASS.defaultBlockState());
        else if (chance < 0.40f) level.setBlock(pos, Blocks.SHORT_GRASS.defaultBlockState(), 3);
    }

    private static void handleBeachGrowth(ServerLevel level, BlockPos pos, float chance) {
        BlockState groundState = level.getBlockState(pos.below());
        if (!groundState.is(Blocks.SAND) && !groundState.is(Blocks.RED_SAND)) return; // Only grow on sand or red sand
        if (chance < 0.10f) level.setBlock(pos, Blocks.DEAD_BUSH.defaultBlockState(), 3);
        else if (chance < 0.17f) level.setBlock(pos, ModBlocks.YUCCA_PLANT_BLOCK.get().defaultBlockState(), 3);
    }

    private static void handleDesertGrowth(ServerLevel level, BlockPos pos, float chance) {
        BlockState groundState = level.getBlockState(pos.below());
        if (!groundState.is(Blocks.SAND) && !groundState.is(Blocks.RED_SAND)) return; // Only grow on sand or red sand
        if (chance < 0.05f) level.setBlock(pos, ModBlocks.YUCCA_PLANT_BLOCK.get().defaultBlockState(), 3);
        else if (chance < 0.10f) level.setBlock(pos, Blocks.DEAD_BUSH.defaultBlockState(), 3);
    }

    private static void handleSnowyGrowth(ServerLevel level, BlockPos pos, float chance) {
        if (chance < 0.10f) level.setBlock(pos, Blocks.SHORT_GRASS.defaultBlockState(), 3);
        else if (chance < 0.20f) placeDoublePlant(level, pos, Blocks.TALL_GRASS.defaultBlockState());
    }

    private static void handleRiverGrowth(ServerLevel level, BlockPos pos, float chance) {
        if (chance < 0.02f) level.setBlock(pos, Blocks.SUGAR_CANE.defaultBlockState(), 3);
        else if (chance < 0.10f) level.setBlock(pos, Blocks.POPPY.defaultBlockState(), 3);
        else if (chance < 0.15f) level.setBlock(pos, Blocks.DANDELION.defaultBlockState(), 3);
        else if (chance < 0.20f) level.setBlock(pos, Blocks.OXEYE_DAISY.defaultBlockState(), 3);
        else if (chance < 0.21f) level.setBlock(pos, ModBlocks.FLAX_FLOWER_BLOCK.get().defaultBlockState(), 3);
        else if (chance < 0.31f) level.setBlock(pos, Blocks.CORNFLOWER.defaultBlockState(), 3);
        else if (chance < 0.64f) placeDoublePlant(level, pos, Blocks.TALL_GRASS.defaultBlockState());
        else if (chance < 0.98f) level.setBlock(pos, Blocks.SHORT_GRASS.defaultBlockState(), 3);
    }

    private static void handleCherryGrowth(ServerLevel level, BlockPos pos, float chance) {
        if (chance < 0.15f) level.setBlock(pos, Blocks.PINK_PETALS.defaultBlockState(), 3);
        else if (chance < 0.25f) placeDoublePlant(level, pos, Blocks.TALL_GRASS.defaultBlockState());
        else if (chance < 0.35f) level.setBlock(pos, Blocks.SHORT_GRASS.defaultBlockState(), 3);
    }

    private static void handleLushCaveGrowth(ServerLevel level, BlockPos pos, float chance) {
        if (chance < 0.10f) level.setBlock(pos, Blocks.AZALEA.defaultBlockState(), 3);
        else if (chance < 0.20f) level.setBlock(pos, Blocks.FLOWERING_AZALEA.defaultBlockState(), 3);
    }

    private static void handleDefaultGrowth(ServerLevel level, BlockPos pos, float chance) {
        if (chance < 0.10f) level.setBlock(pos, Blocks.SHORT_GRASS.defaultBlockState(), 3);
        else if (chance < 0.20f) placeDoublePlant(level, pos, Blocks.TALL_GRASS.defaultBlockState());
    }

    private static void handleSpectralSoulboundForestGrowth(ServerLevel level, BlockPos pos, float chance) {
        if (chance < 0.06f) level.setBlock(pos, ModBlocks.FLAX_FLOWER_BLOCK.get().defaultBlockState(), 3);
        else if (chance < 0.12f) level.setBlock(pos, Blocks.ALLIUM.defaultBlockState(), 3);
        else if (chance < 0.18f) level.setBlock(pos, Blocks.AZURE_BLUET.defaultBlockState(), 3);
        else if (chance < 0.24f) level.setBlock(pos, Blocks.LILY_OF_THE_VALLEY.defaultBlockState(), 3);
        else if (chance < 0.40f) level.setBlock(pos, Blocks.SHORT_GRASS.defaultBlockState(), 3);
        else if (chance < 0.56f) placeDoublePlant(level, pos, Blocks.TALL_GRASS.defaultBlockState());
    }

    private static void handleDarkForestGrowth(ServerLevel level, BlockPos pos, float chance) {
        if (chance < 0.02f) level.setBlock(pos, ModBlocks.FLAX_FLOWER_BLOCK.get().defaultBlockState(), 3);
        else if (chance < 0.06f) level.setBlock(pos, Blocks.DANDELION.defaultBlockState(), 3);
        else if (chance < 0.10f) level.setBlock(pos, Blocks.POPPY.defaultBlockState(), 3);
        else if (chance < 0.40f) level.setBlock(pos, Blocks.SHORT_GRASS.defaultBlockState(), 3);
        else if (chance < 0.70f) placeDoublePlant(level, pos, Blocks.TALL_GRASS.defaultBlockState());
    }
}