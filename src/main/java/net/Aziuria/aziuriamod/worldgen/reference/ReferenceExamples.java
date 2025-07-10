package net.Aziuria.aziuriamod.worldgen.reference;

/**
 * ReferenceExamples
 *
 * This file collects commented examples from your provided worldgen files for clean, organized future reference.
 * Each section explicitly states the original file it came from.
 *
 * These are untouched snippets for when you expand your Nether/End ore generation.
 */
public class ReferenceExamples {

    // =============================
    // From ModConfiguredFeatures.java
    // =============================



    // RuleTest netherrackReplaceables = new BlockMatchTest(Blocks.NETHERRACK);
    // RuleTest endReplaceables = new BlockMatchTest(Blocks.END_STONE);

    // register(context, NETHER_BISMUTH_ORE_KEY, Feature.ORE, new OreConfiguration(netherrackReplaceables,
    //         ModBlocks.BISMUTH_NETHER_ORE.get().defaultBlockState(), 9));
    //
    // register(context, END_BISMUTH_ORE_KEY, Feature.ORE, new OreConfiguration(endReplaceables,
    //         ModBlocks.BISMUTH_END_ORE.get().defaultBlockState(), 9));

    // =============================
    // From ModPlacedFeatures.java
    // =============================

    // public static final ResourceKey<PlacedFeature> NETHER_BISMUTH_ORE_PLACED_KEY = registerKey("nether_bismuth_ore_placed");
    // public static final ResourceKey<PlacedFeature> END_BISMUTH_ORE_PLACED_KEY = registerKey("end_bismuth_ore_placed");

    // register(context, NETHER_BISMUTH_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_BISMUTH_ORE_KEY),
    //         ModOrePlacement.commonOrePlacement(9, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));
    //
    // register(context, END_BISMUTH_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.END_BISMUTH_ORE_KEY),
    //         ModOrePlacement.commonOrePlacement(12, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));

    // =============================
    // From ModBiomeModifiers.java
    // =============================

// Example for individual Biomes!

    // public static final ResourceKey<BiomeModifier> ADD_NETHER_BISMUTH_ORE = registerKey("add_nether_bismuth_ore");
    // public static final ResourceKey<BiomeModifier> ADD_END_BISMUTH_ORE = registerKey("add_end_bismuth_ore");

// context.register(ADD_BISMUTH_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
//         HolderSet.direct(biomes.getOrThrow(Biomes.PLAINS), biomes.getOrThrow(Biomes.SAVANNA)),
//         HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.BISMUTH_ORE_PLACED_KEY)),
//         GenerationStep.Decoration.UNDERGROUND_ORES));

// context.register(ADD_NETHER_BISMUTH_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
//         biomes.getOrThrow(BiomeTags.IS_NETHER),
//         HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.NETHER_BISMUTH_ORE_PLACED_KEY)),
//         GenerationStep.Decoration.UNDERGROUND_ORES));

// context.register(ADD_END_BISMUTH_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
//         biomes.getOrThrow(BiomeTags.IS_END),
//         HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.END_BISMUTH_ORE_PLACED_KEY)),
//         GenerationStep.Decoration.UNDERGROUND_ORES));

}