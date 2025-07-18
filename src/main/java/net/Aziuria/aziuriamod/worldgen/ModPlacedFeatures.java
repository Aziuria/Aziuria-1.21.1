package net.Aziuria.aziuriamod.worldgen;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.Aziuria.aziuriamod.block.ModBlocks;
import net.Aziuria.aziuriamod.worldgen.rules.NearLogPlacementModifier;
import net.Aziuria.aziuriamod.worldgen.rules.NearbyWaterRadiusFilter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.ArrayList;
import java.util.List;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> SULPHUR_ORE_ULTRA_DEEP_PLACED_KEY = registerKey("sulphur_ore_ultra_deep_placed");
    public static final ResourceKey<PlacedFeature> SULPHUR_ORE_DEEP_PLACED_KEY = registerKey("sulphur_ore_deep_placed");
    public static final ResourceKey<PlacedFeature> SULPHUR_ORE_MID_PLACED_KEY = registerKey("sulphur_ore_mid_placed");
    public static final ResourceKey<PlacedFeature> SULPHUR_ORE_UPPER_PLACED_KEY = registerKey("sulphur_ore_upper_placed");
    public static final ResourceKey<PlacedFeature> SULPHUR_ORE_MOUNTAINS_PLACED_KEY = registerKey("sulphur_ore_mountains_placed");

    public static final ResourceKey<PlacedFeature> POTASSIUM_ORE_ULTRA_DEEP_PLACED_KEY = registerKey("potassium_ore_ultra_deep_placed");
    public static final ResourceKey<PlacedFeature> POTASSIUM_ORE_DEEP_PLACED_KEY = registerKey("potassium_ore_deep_placed");
    public static final ResourceKey<PlacedFeature> POTASSIUM_ORE_MID_PLACED_KEY = registerKey("potassium_ore_mid_placed");
    public static final ResourceKey<PlacedFeature> POTASSIUM_ORE_UPPER_PLACED_KEY = registerKey("potassium_ore_upper_placed");
    public static final ResourceKey<PlacedFeature> POTASSIUM_ORE_MOUNTAINS_PLACED_KEY = registerKey("potassium_ore_mountains_placed");

    public static final ResourceKey<PlacedFeature> LEAF_LITTER_PLACED_KEY = registerKey("leaf_litter_placed");
    public static final ResourceKey<PlacedFeature> LEAF_LITTER_EXTRA_PLACED_KEY = registerKey("leaf_litter_extra");
    public static final ResourceKey<PlacedFeature> LEAF_LITTER_EXTRA2_PLACED_KEY = registerKey("leaf_litter_extra2");
    public static final ResourceKey<PlacedFeature> LEAF_LITTER_EXTRA3_PLACED_KEY = registerKey("leaf_litter_extra3");

    public static final ResourceKey<PlacedFeature> FLAX_FLOWER_PLACED_KEY = registerKey("flax_flower_placed");

    public static final ResourceKey<PlacedFeature> APPLE_KEY_VARIANT_1 = registerKey("apple_variant_1");
    public static final ResourceKey<PlacedFeature> APPLE_KEY_VARIANT_2 = registerKey("apple_variant_2");
    public static final ResourceKey<PlacedFeature> APPLE_KEY_VARIANT_3 = registerKey("apple_variant_3");
    public static final ResourceKey<PlacedFeature> APPLE_KEY_VARIANT_4 = registerKey("apple_variant_4");
    public static final ResourceKey<PlacedFeature> APPLE_KEY_VARIANT_5 = registerKey("apple_variant_5");
    public static final ResourceKey<PlacedFeature> APPLE_KEY_VARIANT_6 = registerKey("apple_variant_6");
    public static final ResourceKey<PlacedFeature> APPLE_KEY_VARIANT_7 = registerKey("apple_variant_7");
    public static final ResourceKey<PlacedFeature> APPLE_KEY_VARIANT_8 = registerKey("apple_variant_8");
    public static final ResourceKey<PlacedFeature> APPLE_KEY_VARIANT_9 = registerKey("apple_variant_9");
    public static final ResourceKey<PlacedFeature> APPLE_KEY_VARIANT_10 = registerKey("apple_variant_10");

    public static final ResourceKey<PlacedFeature> DARK_OAK_KEY_VARIANT_1 = registerKey("dark_oak_variant_1");
    public static final ResourceKey<PlacedFeature> DARK_OAK_KEY_VARIANT_2 = registerKey("dark_oak_variant_2");

    public static final ResourceKey<PlacedFeature> BIRCH_VARIANT_1 = registerKey("birch_variant_1");



    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

// SULPHUR PLACEMENT
        register(context, SULPHUR_ORE_ULTRA_DEEP_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_SULPHUR_ORE_ULTRA_DEEP_KEY),
                ModOrePlacement.commonOrePlacement(14, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-32))));

        register(context, SULPHUR_ORE_DEEP_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_SULPHUR_ORE_DEEP_KEY),
                ModOrePlacement.commonOrePlacement(12, HeightRangePlacement.triangle(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(0))));

        register(context, SULPHUR_ORE_MID_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_SULPHUR_ORE_MID_KEY),
                ModOrePlacement.commonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(32))));

        register(context, SULPHUR_ORE_UPPER_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_SULPHUR_ORE_UPPER_KEY),
                ModOrePlacement.commonOrePlacement(8, HeightRangePlacement.triangle(VerticalAnchor.absolute(32), VerticalAnchor.absolute(64))));

        register(context, SULPHUR_ORE_MOUNTAINS_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_SULPHUR_ORE_MOUNTAINS_KEY),
                ModOrePlacement.commonOrePlacement(6, HeightRangePlacement.triangle(VerticalAnchor.absolute(64), VerticalAnchor.absolute(128))));

// POTASSIUM PLACEMENT
        register(context, POTASSIUM_ORE_ULTRA_DEEP_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_POTASSIUM_ORE_ULTRA_DEEP_KEY),
                ModOrePlacement.commonOrePlacement(14, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-32))));

        register(context, POTASSIUM_ORE_DEEP_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_POTASSIUM_ORE_DEEP_KEY),
                ModOrePlacement.commonOrePlacement(12, HeightRangePlacement.triangle(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(0))));

        register(context, POTASSIUM_ORE_MID_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_POTASSIUM_ORE_MID_KEY),
                ModOrePlacement.commonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(32))));

        register(context, POTASSIUM_ORE_UPPER_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_POTASSIUM_ORE_UPPER_KEY),
                ModOrePlacement.commonOrePlacement(8, HeightRangePlacement.triangle(VerticalAnchor.absolute(32), VerticalAnchor.absolute(64))));

        register(context, POTASSIUM_ORE_MOUNTAINS_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_POTASSIUM_ORE_MOUNTAINS_KEY),
                ModOrePlacement.commonOrePlacement(6, HeightRangePlacement.triangle(VerticalAnchor.absolute(64), VerticalAnchor.absolute(128))));

        register(context, FLAX_FLOWER_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.FLAX_FLOWER_KEY),
                List.of(
                        RarityFilter.onAverageOnceEvery(4),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                ));

// original
        register(context, LEAF_LITTER_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.LEAF_LITTER_KEY),
                List.of(
                        RarityFilter.onAverageOnceEvery(1),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        NearLogPlacementModifier.of(5, 0.6),
                        BiomeFilter.biome()
                ));

// additional (different key)
        register(context, LEAF_LITTER_EXTRA_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.LEAF_LITTER_KEY),
                List.of(
                        RarityFilter.onAverageOnceEvery(2),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        NearLogPlacementModifier.of(7, 0.8),
                        BiomeFilter.biome()
                ));

        register(context, LEAF_LITTER_EXTRA2_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.LEAF_LITTER_KEY),
                List.of(
                        RarityFilter.onAverageOnceEvery(3),  // Slightly less frequent to balance
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        NearLogPlacementModifier.of(4, 0.4),  // Smaller radius, gentler falloff
                        BiomeFilter.biome()
                ));

        register(context, LEAF_LITTER_EXTRA3_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.LEAF_LITTER_KEY),
                List.of(
                        RarityFilter.onAverageOnceEvery(4),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        NearLogPlacementModifier.of(9, 1.2),  // Larger radius, stronger falloff
                        BiomeFilter.biome()

                ));


// Apple Variant 1
        {
            List<PlacementModifier> modifiers = new ArrayList<>(VegetationPlacements.treePlacement(
                    NoiseThresholdCountPlacement.of(0.8, 0, 1),
                    ModBlocks.APPLE_SAPLING.get()
            ));
            modifiers.add(RarityFilter.onAverageOnceEvery(15));

            register(context, APPLE_KEY_VARIANT_1,
                    configuredFeatures.getOrThrow(ModConfiguredFeatures.APPLE_KEY_VARIANT_1),
                    modifiers
            );
        }

// Apple Variant 2
        {
            List<PlacementModifier> modifiers = new ArrayList<>(VegetationPlacements.treePlacement(
                    NoiseThresholdCountPlacement.of(0.8, 0, 1),
                    ModBlocks.APPLE_SAPLING.get()
            ));
            modifiers.add(RarityFilter.onAverageOnceEvery(18)); // retain your desired rarity

            register(context, APPLE_KEY_VARIANT_2,
                    configuredFeatures.getOrThrow(ModConfiguredFeatures.APPLE_KEY_VARIANT_2),
                    modifiers
            );
        }

// Apple Variant 3
        {
            List<PlacementModifier> modifiers = new ArrayList<>(VegetationPlacements.treePlacement(
                    NoiseThresholdCountPlacement.of(0.8, 0, 2),
                    ModBlocks.APPLE_SAPLING.get()
            ));
            modifiers.add(RarityFilter.onAverageOnceEvery(14));

            register(context, APPLE_KEY_VARIANT_3,
                    configuredFeatures.getOrThrow(ModConfiguredFeatures.APPLE_KEY_VARIANT_3),
                    modifiers
            );
        }

// Apple Variant 4
        {
            List<PlacementModifier> modifiers = new ArrayList<>(VegetationPlacements.treePlacement(
                    NoiseThresholdCountPlacement.of(0.8, 0, 1),
                    ModBlocks.APPLE_SAPLING.get()
            ));
            modifiers.add(RarityFilter.onAverageOnceEvery(15));

            register(context, APPLE_KEY_VARIANT_4,
                    configuredFeatures.getOrThrow(ModConfiguredFeatures.APPLE_KEY_VARIANT_4),
                    modifiers
            );
        }

// Apple Variant 5
        {
            List<PlacementModifier> modifiers = new ArrayList<>(VegetationPlacements.treePlacement(
                    NoiseThresholdCountPlacement.of(0.8, 0, 2),
                    ModBlocks.APPLE_SAPLING.get()
            ));
            modifiers.add(RarityFilter.onAverageOnceEvery(12));

            register(context, APPLE_KEY_VARIANT_5,
                    configuredFeatures.getOrThrow(ModConfiguredFeatures.APPLE_KEY_VARIANT_5),
                    modifiers
            );
        }

// Apple Variant 6
        {
            List<PlacementModifier> modifiers = new ArrayList<>(VegetationPlacements.treePlacement(
                    NoiseThresholdCountPlacement.of(0.8, 0, 1),
                    ModBlocks.APPLE_SAPLING.get()
            ));
            modifiers.add(RarityFilter.onAverageOnceEvery(14));

            register(context, APPLE_KEY_VARIANT_6,
                    configuredFeatures.getOrThrow(ModConfiguredFeatures.APPLE_KEY_VARIANT_6),
                    modifiers
            );
        }

// Apple Variant 7
        {
            List<PlacementModifier> modifiers = new ArrayList<>(VegetationPlacements.treePlacement(
                    NoiseThresholdCountPlacement.of(0.8, 0, 2),
                    ModBlocks.APPLE_SAPLING.get()
            ));
            modifiers.add(RarityFilter.onAverageOnceEvery(13));

            register(context, APPLE_KEY_VARIANT_7,
                    configuredFeatures.getOrThrow(ModConfiguredFeatures.APPLE_KEY_VARIANT_7),
                    modifiers
            );
        }

// Apple Variant 8
        {
            List<PlacementModifier> modifiers = new ArrayList<>(VegetationPlacements.treePlacement(
                    NoiseThresholdCountPlacement.of(0.8, 0, 1),
                    ModBlocks.APPLE_SAPLING.get()
            ));
            modifiers.add(RarityFilter.onAverageOnceEvery(15));

            register(context, APPLE_KEY_VARIANT_8,
                    configuredFeatures.getOrThrow(ModConfiguredFeatures.APPLE_KEY_VARIANT_8),
                    modifiers
            );
        }

// Apple Variant 9
        {
            List<PlacementModifier> modifiers = new ArrayList<>(VegetationPlacements.treePlacement(
                    NoiseThresholdCountPlacement.of(0.8, 0, 2),
                    ModBlocks.APPLE_SAPLING.get()
            ));
            modifiers.add(RarityFilter.onAverageOnceEvery(13));

            register(context, APPLE_KEY_VARIANT_9,
                    configuredFeatures.getOrThrow(ModConfiguredFeatures.APPLE_KEY_VARIANT_9),
                    modifiers
            );
        }

// Apple Variant 10
        {
            List<PlacementModifier> modifiers = new ArrayList<>(VegetationPlacements.treePlacement(
                    NoiseThresholdCountPlacement.of(0.8, 0, 1),
                    ModBlocks.APPLE_SAPLING.get()
            ));
            modifiers.add(RarityFilter.onAverageOnceEvery(15));

            register(context, APPLE_KEY_VARIANT_10,
                    configuredFeatures.getOrThrow(ModConfiguredFeatures.APPLE_KEY_VARIANT_10),
                    modifiers
            );
        }

// Dark Oak Variant 1
        {
            List<PlacementModifier> modifiers = new ArrayList<>(List.of(
                    NoiseThresholdCountPlacement.of(0.6, 0, 2),
                    InSquarePlacement.spread(),
                    SurfaceWaterDepthFilter.forMaxDepth(1),
                    NearbyWaterRadiusFilter.INSTANCE,  // Added this line
                    RarityFilter.onAverageOnceEvery(8),
                    PlacementUtils.HEIGHTMAP,
                    BlockPredicateFilter.forPredicate(
                            BlockPredicate.wouldSurvive(Blocks.DARK_OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)
                    ), // <-- Added this line to prevent spawning on sand
                    BiomeFilter.biome()
            ));

            register(context, DARK_OAK_KEY_VARIANT_1,
                    configuredFeatures.getOrThrow(ModConfiguredFeatures.DARK_OAK_VARIANT_1),
                    modifiers
            );
        }

// Dark Oak Variant 2
        {
            List<PlacementModifier> modifiers = new ArrayList<>(List.of(
                    NoiseThresholdCountPlacement.of(0.6, 0, 2),
                    InSquarePlacement.spread(),
                    SurfaceWaterDepthFilter.forMaxDepth(1),
                    NearbyWaterRadiusFilter.INSTANCE,  // Added this line
                    RarityFilter.onAverageOnceEvery(10),
                    PlacementUtils.HEIGHTMAP,
                    BlockPredicateFilter.forPredicate(
                            BlockPredicate.wouldSurvive(Blocks.DARK_OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)
                    ), // <-- Added this line to prevent spawning on sand
                    BiomeFilter.biome()
            ));

            register(context, DARK_OAK_KEY_VARIANT_2,
                    configuredFeatures.getOrThrow(ModConfiguredFeatures.DARK_OAK_VARIANT_2),
                    modifiers
            );
        }

        {
            List<PlacementModifier> modifiers = new ArrayList<>(VegetationPlacements.treePlacement(
                    NoiseThresholdCountPlacement.of(0.8, 0, 1),
                    Blocks.BIRCH_SAPLING
            ));
            modifiers.add(RarityFilter.onAverageOnceEvery(6));

            register(context, BIRCH_VARIANT_1,
                    configuredFeatures.getOrThrow(ModConfiguredFeatures.BIRCH_VARIANT_1),
                    modifiers
            );
        }


    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
