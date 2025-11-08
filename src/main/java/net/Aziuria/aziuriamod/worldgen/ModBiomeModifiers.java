package net.Aziuria.aziuriamod.worldgen;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModBiomeModifiers {

    // SULPHUR
    public static final ResourceKey<BiomeModifier> ADD_SULPHUR_ORE_ULTRA_DEEP = registerKey("add_sulphur_ore_ultra_deep");
    public static final ResourceKey<BiomeModifier> ADD_SULPHUR_ORE_DEEP = registerKey("add_sulphur_ore_deep");
    public static final ResourceKey<BiomeModifier> ADD_SULPHUR_ORE_MID = registerKey("add_sulphur_ore_mid");
    public static final ResourceKey<BiomeModifier> ADD_SULPHUR_ORE_UPPER = registerKey("add_sulphur_ore_upper");
    public static final ResourceKey<BiomeModifier> ADD_SULPHUR_ORE_MOUNTAINS = registerKey("add_sulphur_ore_mountains");

    // TIN
    public static final ResourceKey<BiomeModifier> ADD_TIN_ORE_ULTRA_DEEP = registerKey("add_tin_ore_ultra_deep");
    public static final ResourceKey<BiomeModifier> ADD_TIN_ORE_DEEP = registerKey("add_tin_ore_deep");
    public static final ResourceKey<BiomeModifier> ADD_TIN_ORE_MID = registerKey("add_tin_ore_mid");
    public static final ResourceKey<BiomeModifier> ADD_TIN_ORE_UPPER = registerKey("add_tin_ore_upper");
    public static final ResourceKey<BiomeModifier> ADD_TIN_ORE_MOUNTAINS = registerKey("add_tin_ore_mountains");

    // POTASSIUM
    public static final ResourceKey<BiomeModifier> ADD_POTASSIUM_ORE_ULTRA_DEEP = registerKey("add_potassium_ore_ultra_deep");
    public static final ResourceKey<BiomeModifier> ADD_POTASSIUM_ORE_DEEP = registerKey("add_potassium_ore_deep");
    public static final ResourceKey<BiomeModifier> ADD_POTASSIUM_ORE_MID = registerKey("add_potassium_ore_mid");
    public static final ResourceKey<BiomeModifier> ADD_POTASSIUM_ORE_UPPER = registerKey("add_potassium_ore_upper");
    public static final ResourceKey<BiomeModifier> ADD_POTASSIUM_ORE_MOUNTAINS = registerKey("add_potassium_ore_mountains");

    public static final ResourceKey<BiomeModifier> ADD_SPINEL_ORE_ULTRA_DEEP = registerKey("add_spinel_ore_ultra_deep");
    public static final ResourceKey<BiomeModifier> ADD_SPINEL_ORE_DEEP = registerKey("add_spinel_ore_deep");
    public static final ResourceKey<BiomeModifier> ADD_SPINEL_ORE_MID = registerKey("add_spinel_ore_mid");

    public static final ResourceKey<BiomeModifier> ADD_LEAF_LITTER = registerKey("add_leaf_litter");
    public static final ResourceKey<BiomeModifier> ADD_LEAF_LITTER_EXTRA = registerKey("add_leaf_litter_extra");
    public static final ResourceKey<BiomeModifier> ADD_LEAF_LITTER_EXTRA2 = registerKey("add_leaf_litter_extra2");
    public static final ResourceKey<BiomeModifier> ADD_LEAF_LITTER_EXTRA3 = registerKey("add_leaf_litter_extra3");

    public static final ResourceKey<BiomeModifier> ADD_FLAX_FLOWER = registerKey("add_flax_flower");
    public static final ResourceKey<BiomeModifier> YUCCA_PLANT = registerKey("yucca_plant");

    public static final ResourceKey<BiomeModifier> APPLE_KEY_VARIANT_1 = registerKey("apple_variant_1");
    public static final ResourceKey<BiomeModifier> APPLE_KEY_VARIANT_2 = registerKey("apple_variant_2");
    public static final ResourceKey<BiomeModifier> APPLE_KEY_VARIANT_3 = registerKey("apple_variant_3");
    public static final ResourceKey<BiomeModifier> APPLE_KEY_VARIANT_4 = registerKey("apple_variant_4");
    public static final ResourceKey<BiomeModifier> APPLE_KEY_VARIANT_5 = registerKey("apple_variant_5");
    public static final ResourceKey<BiomeModifier> APPLE_KEY_VARIANT_6 = registerKey("apple_variant_6");
    public static final ResourceKey<BiomeModifier> APPLE_KEY_VARIANT_7 = registerKey("apple_variant_7");

    public static final ResourceKey<BiomeModifier> PEAR_KEY_VARIANT_1 = registerKey("pear_variant_1");
    public static final ResourceKey<BiomeModifier> PEAR_KEY_VARIANT_2 = registerKey("pear_variant_2");
    public static final ResourceKey<BiomeModifier> PEAR_KEY_VARIANT_3 = registerKey("pear_variant_3");
    public static final ResourceKey<BiomeModifier> PEAR_KEY_VARIANT_4 = registerKey("pear_variant_4");
    public static final ResourceKey<BiomeModifier> PEAR_KEY_VARIANT_5 = registerKey("pear_variant_5");
    public static final ResourceKey<BiomeModifier> PEAR_KEY_VARIANT_6 = registerKey("pear_variant_6");
    public static final ResourceKey<BiomeModifier> PEAR_KEY_VARIANT_7 = registerKey("pear_variant_7");

    public static final ResourceKey<BiomeModifier> CHERRY_KEY_VARIANT_1 = registerKey("cherry_variant_1");
    public static final ResourceKey<BiomeModifier> CHERRY_KEY_VARIANT_2 = registerKey("cherry_variant_2");
    public static final ResourceKey<BiomeModifier> CHERRY_KEY_VARIANT_3 = registerKey("cherry_variant_3");
    public static final ResourceKey<BiomeModifier> CHERRY_KEY_VARIANT_4 = registerKey("cherry_variant_4");
    public static final ResourceKey<BiomeModifier> CHERRY_KEY_VARIANT_5 = registerKey("cherry_variant_5");
    public static final ResourceKey<BiomeModifier> CHERRY_KEY_VARIANT_6 = registerKey("cherry_variant_6");
    public static final ResourceKey<BiomeModifier> CHERRY_KEY_VARIANT_7 = registerKey("cherry_variant_7");

    public static final ResourceKey<BiomeModifier> AVOCADO_KEY_VARIANT_1 = registerKey("avocado_variant_1");
    public static final ResourceKey<BiomeModifier> AVOCADO_KEY_VARIANT_2 = registerKey("avocado_variant_2");
    public static final ResourceKey<BiomeModifier> AVOCADO_KEY_VARIANT_3 = registerKey("avocado_variant_3");
    public static final ResourceKey<BiomeModifier> AVOCADO_KEY_VARIANT_4 = registerKey("avocado_variant_4");
    public static final ResourceKey<BiomeModifier> AVOCADO_KEY_VARIANT_5 = registerKey("avocado_variant_5");
    public static final ResourceKey<BiomeModifier> AVOCADO_KEY_VARIANT_6 = registerKey("avocado_variant_6");
    public static final ResourceKey<BiomeModifier> AVOCADO_KEY_VARIANT_7 = registerKey("avocado_variant_7");

    public static final ResourceKey<BiomeModifier> ORANGE_KEY_VARIANT_1 = registerKey("orange_variant_1");
    public static final ResourceKey<BiomeModifier> ORANGE_KEY_VARIANT_2 = registerKey("orange_variant_2");
    public static final ResourceKey<BiomeModifier> ORANGE_KEY_VARIANT_3 = registerKey("orange_variant_3");
    public static final ResourceKey<BiomeModifier> ORANGE_KEY_VARIANT_4 = registerKey("orange_variant_4");
    public static final ResourceKey<BiomeModifier> ORANGE_KEY_VARIANT_5 = registerKey("orange_variant_5");
    public static final ResourceKey<BiomeModifier> ORANGE_KEY_VARIANT_6 = registerKey("orange_variant_6");
    public static final ResourceKey<BiomeModifier> ORANGE_KEY_VARIANT_7 = registerKey("orange_variant_7");

    public static final ResourceKey<BiomeModifier> BANANA_KEY_VARIANT_1 = registerKey("banana_variant_1");
    public static final ResourceKey<BiomeModifier> BANANA_KEY_VARIANT_2 = registerKey("banana_variant_2");
    public static final ResourceKey<BiomeModifier> BANANA_KEY_VARIANT_3 = registerKey("banana_variant_3");
    public static final ResourceKey<BiomeModifier> BANANA_KEY_VARIANT_4 = registerKey("banana_variant_4");
    public static final ResourceKey<BiomeModifier> BANANA_KEY_VARIANT_5 = registerKey("banana_variant_5");
    public static final ResourceKey<BiomeModifier> BANANA_KEY_VARIANT_6 = registerKey("banana_variant_6");
    public static final ResourceKey<BiomeModifier> BANANA_KEY_VARIANT_7 = registerKey("banana_variant_7");

    public static final ResourceKey<BiomeModifier> DARK_OAK_KEY_VARIANT_1 = registerKey("dark_oak_variant_1");
    public static final ResourceKey<BiomeModifier> DARK_OAK_KEY_VARIANT_2 = registerKey("dark_oak_variant_2");

    public static final ResourceKey<BiomeModifier> BIRCH_VARIANT_1 = registerKey("birch_variant_1");

    public static final ResourceKey<BiomeModifier> BLACKCURRANT_BUSH = registerKey("blackcurrant_bush");
    public static final ResourceKey<BiomeModifier> STRAWBERRY_BUSH = registerKey("strawberry_bush");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        // CF -> PF -> BM
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

// SULPHUR
        context.register(ADD_SULPHUR_ORE_ULTRA_DEEP, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SULPHUR_ORE_ULTRA_DEEP_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_SULPHUR_ORE_DEEP, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SULPHUR_ORE_DEEP_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_SULPHUR_ORE_MID, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SULPHUR_ORE_MID_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_SULPHUR_ORE_UPPER, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SULPHUR_ORE_UPPER_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_SULPHUR_ORE_MOUNTAINS, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SULPHUR_ORE_MOUNTAINS_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        // TIN
        context.register(ADD_TIN_ORE_ULTRA_DEEP, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.TIN_ORE_ULTRA_DEEP_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_TIN_ORE_DEEP, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.TIN_ORE_DEEP_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_TIN_ORE_MID, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.TIN_ORE_MID_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_TIN_ORE_UPPER, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.TIN_ORE_UPPER_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_TIN_ORE_MOUNTAINS, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.TIN_ORE_MOUNTAINS_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

// POTASSIUM
        context.register(ADD_POTASSIUM_ORE_ULTRA_DEEP, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.POTASSIUM_ORE_ULTRA_DEEP_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_POTASSIUM_ORE_DEEP, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.POTASSIUM_ORE_DEEP_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_POTASSIUM_ORE_MID, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.POTASSIUM_ORE_MID_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_POTASSIUM_ORE_UPPER, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.POTASSIUM_ORE_UPPER_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_POTASSIUM_ORE_MOUNTAINS, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.POTASSIUM_ORE_MOUNTAINS_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_SPINEL_ORE_ULTRA_DEEP, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SPINEL_ORE_ULTRA_DEEP_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_SPINEL_ORE_DEEP, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SPINEL_ORE_DEEP_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_SPINEL_ORE_MID, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SPINEL_ORE_MID_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));



        context.register(ADD_LEAF_LITTER, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD), // ← Changed from explicit biomes list
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.LEAF_LITTER_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_LEAF_LITTER_EXTRA, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.LEAF_LITTER_EXTRA_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_LEAF_LITTER_EXTRA2, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.LEAF_LITTER_EXTRA2_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_LEAF_LITTER_EXTRA3, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.LEAF_LITTER_EXTRA3_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_FLAX_FLOWER, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.SUNFLOWER_PLAINS),
                        biomes.getOrThrow(Biomes.MEADOW),
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.FLOWER_FOREST)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.FLAX_FLOWER_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(YUCCA_PLANT, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.SAVANNA),
                        biomes.getOrThrow(Biomes.SAVANNA_PLATEAU),
                        biomes.getOrThrow(Biomes.DESERT),
                        biomes.getOrThrow(Biomes.BADLANDS),
                        biomes.getOrThrow(Biomes.BEACH)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.YUCCA_PLANT_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));



        // ------------------ APPLE VARIANTS ------------------
        context.register(APPLE_KEY_VARIANT_1, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.SUNFLOWER_PLAINS),
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.FLOWER_FOREST),
                        biomes.getOrThrow(Biomes.MEADOW),
                        biomes.getOrThrow(Biomes.GROVE),
                        biomes.getOrThrow(Biomes.WOODED_BADLANDS)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.APPLE_KEY_VARIANT_1)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
        context.register(APPLE_KEY_VARIANT_2, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.SUNFLOWER_PLAINS),
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.FLOWER_FOREST),
                        biomes.getOrThrow(Biomes.MEADOW),
                        biomes.getOrThrow(Biomes.GROVE),
                        biomes.getOrThrow(Biomes.WOODED_BADLANDS)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.APPLE_KEY_VARIANT_2)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
        context.register(APPLE_KEY_VARIANT_3, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.SUNFLOWER_PLAINS),
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.FLOWER_FOREST),
                        biomes.getOrThrow(Biomes.MEADOW),
                        biomes.getOrThrow(Biomes.GROVE),
                        biomes.getOrThrow(Biomes.WOODED_BADLANDS)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.APPLE_KEY_VARIANT_3)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
        context.register(APPLE_KEY_VARIANT_4, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.SUNFLOWER_PLAINS),
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.FLOWER_FOREST),
                        biomes.getOrThrow(Biomes.MEADOW),
                        biomes.getOrThrow(Biomes.GROVE),
                        biomes.getOrThrow(Biomes.WOODED_BADLANDS)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.APPLE_KEY_VARIANT_4)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
        context.register(APPLE_KEY_VARIANT_5, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.SUNFLOWER_PLAINS),
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.FLOWER_FOREST),
                        biomes.getOrThrow(Biomes.MEADOW),
                        biomes.getOrThrow(Biomes.GROVE),
                        biomes.getOrThrow(Biomes.WOODED_BADLANDS)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.APPLE_KEY_VARIANT_5)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
        context.register(APPLE_KEY_VARIANT_6, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.SUNFLOWER_PLAINS),
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.FLOWER_FOREST),
                        biomes.getOrThrow(Biomes.MEADOW),
                        biomes.getOrThrow(Biomes.GROVE),
                        biomes.getOrThrow(Biomes.WOODED_BADLANDS)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.APPLE_KEY_VARIANT_6)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
        context.register(APPLE_KEY_VARIANT_7, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.SUNFLOWER_PLAINS),
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.FLOWER_FOREST),
                        biomes.getOrThrow(Biomes.MEADOW),
                        biomes.getOrThrow(Biomes.GROVE),
                        biomes.getOrThrow(Biomes.WOODED_BADLANDS)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.APPLE_KEY_VARIANT_7)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        // PEAR VARIANTS
        context.register(PEAR_KEY_VARIANT_1, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.SUNFLOWER_PLAINS),
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.FLOWER_FOREST),
                        biomes.getOrThrow(Biomes.MEADOW),
                        biomes.getOrThrow(Biomes.GROVE),
                        biomes.getOrThrow(Biomes.WOODED_BADLANDS)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PEAR_KEY_VARIANT_1)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(PEAR_KEY_VARIANT_2, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.SUNFLOWER_PLAINS),
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.FLOWER_FOREST),
                        biomes.getOrThrow(Biomes.MEADOW),
                        biomes.getOrThrow(Biomes.GROVE),
                        biomes.getOrThrow(Biomes.WOODED_BADLANDS)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PEAR_KEY_VARIANT_2)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(PEAR_KEY_VARIANT_3, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.SUNFLOWER_PLAINS),
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.FLOWER_FOREST),
                        biomes.getOrThrow(Biomes.MEADOW),
                        biomes.getOrThrow(Biomes.GROVE),
                        biomes.getOrThrow(Biomes.WOODED_BADLANDS)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PEAR_KEY_VARIANT_3)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(PEAR_KEY_VARIANT_4, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.SUNFLOWER_PLAINS),
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.FLOWER_FOREST),
                        biomes.getOrThrow(Biomes.MEADOW),
                        biomes.getOrThrow(Biomes.GROVE),
                        biomes.getOrThrow(Biomes.WOODED_BADLANDS)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PEAR_KEY_VARIANT_4)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(PEAR_KEY_VARIANT_5, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.SUNFLOWER_PLAINS),
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.FLOWER_FOREST),
                        biomes.getOrThrow(Biomes.MEADOW),
                        biomes.getOrThrow(Biomes.GROVE),
                        biomes.getOrThrow(Biomes.WOODED_BADLANDS)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PEAR_KEY_VARIANT_5)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(PEAR_KEY_VARIANT_6, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.SUNFLOWER_PLAINS),
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.FLOWER_FOREST),
                        biomes.getOrThrow(Biomes.MEADOW),
                        biomes.getOrThrow(Biomes.GROVE),
                        biomes.getOrThrow(Biomes.WOODED_BADLANDS)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PEAR_KEY_VARIANT_6)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(PEAR_KEY_VARIANT_7, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.SUNFLOWER_PLAINS),
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.FLOWER_FOREST),
                        biomes.getOrThrow(Biomes.MEADOW),
                        biomes.getOrThrow(Biomes.GROVE),
                        biomes.getOrThrow(Biomes.WOODED_BADLANDS)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PEAR_KEY_VARIANT_7)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

// CHERRY VARIANTS
        context.register(CHERRY_KEY_VARIANT_1, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.SUNFLOWER_PLAINS),
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.FLOWER_FOREST),
                        biomes.getOrThrow(Biomes.MEADOW),
                        biomes.getOrThrow(Biomes.CHERRY_GROVE),
                        biomes.getOrThrow(Biomes.WOODED_BADLANDS)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.CHERRY_KEY_VARIANT_1)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(CHERRY_KEY_VARIANT_2, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.SUNFLOWER_PLAINS),
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.FLOWER_FOREST),
                        biomes.getOrThrow(Biomes.MEADOW),
                        biomes.getOrThrow(Biomes.CHERRY_GROVE),
                        biomes.getOrThrow(Biomes.WOODED_BADLANDS)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.CHERRY_KEY_VARIANT_2)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(CHERRY_KEY_VARIANT_3, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.SUNFLOWER_PLAINS),
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.FLOWER_FOREST),
                        biomes.getOrThrow(Biomes.MEADOW),
                        biomes.getOrThrow(Biomes.CHERRY_GROVE),
                        biomes.getOrThrow(Biomes.WOODED_BADLANDS)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.CHERRY_KEY_VARIANT_3)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(CHERRY_KEY_VARIANT_4, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.SUNFLOWER_PLAINS),
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.FLOWER_FOREST),
                        biomes.getOrThrow(Biomes.MEADOW),
                        biomes.getOrThrow(Biomes.CHERRY_GROVE),
                        biomes.getOrThrow(Biomes.WOODED_BADLANDS)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.CHERRY_KEY_VARIANT_4)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(CHERRY_KEY_VARIANT_5, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.SUNFLOWER_PLAINS),
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.FLOWER_FOREST),
                        biomes.getOrThrow(Biomes.MEADOW),
                        biomes.getOrThrow(Biomes.CHERRY_GROVE),
                        biomes.getOrThrow(Biomes.WOODED_BADLANDS)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.CHERRY_KEY_VARIANT_5)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(CHERRY_KEY_VARIANT_6, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.SUNFLOWER_PLAINS),
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.FLOWER_FOREST),
                        biomes.getOrThrow(Biomes.MEADOW),
                        biomes.getOrThrow(Biomes.CHERRY_GROVE),
                        biomes.getOrThrow(Biomes.WOODED_BADLANDS)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.CHERRY_KEY_VARIANT_6)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(CHERRY_KEY_VARIANT_7, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.SUNFLOWER_PLAINS),
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.FLOWER_FOREST),
                        biomes.getOrThrow(Biomes.MEADOW),
                        biomes.getOrThrow(Biomes.CHERRY_GROVE),
                        biomes.getOrThrow(Biomes.WOODED_BADLANDS)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.CHERRY_KEY_VARIANT_7)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

// AVOCADO VARIANTS
        context.register(AVOCADO_KEY_VARIANT_1, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JUNGLE),
                        biomes.getOrThrow(Biomes.BAMBOO_JUNGLE),
                        biomes.getOrThrow(Biomes.SPARSE_JUNGLE),
                        biomes.getOrThrow(Biomes.GROVE)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.AVOCADO_KEY_VARIANT_1)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(AVOCADO_KEY_VARIANT_2, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JUNGLE),
                        biomes.getOrThrow(Biomes.BAMBOO_JUNGLE),
                        biomes.getOrThrow(Biomes.SPARSE_JUNGLE),
                        biomes.getOrThrow(Biomes.GROVE)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.AVOCADO_KEY_VARIANT_2)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(AVOCADO_KEY_VARIANT_3, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JUNGLE),
                        biomes.getOrThrow(Biomes.BAMBOO_JUNGLE),
                        biomes.getOrThrow(Biomes.SPARSE_JUNGLE),
                        biomes.getOrThrow(Biomes.GROVE)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.AVOCADO_KEY_VARIANT_3)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(AVOCADO_KEY_VARIANT_4, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JUNGLE),
                        biomes.getOrThrow(Biomes.BAMBOO_JUNGLE),
                        biomes.getOrThrow(Biomes.SPARSE_JUNGLE),
                        biomes.getOrThrow(Biomes.GROVE)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.AVOCADO_KEY_VARIANT_4)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(AVOCADO_KEY_VARIANT_5, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JUNGLE),
                        biomes.getOrThrow(Biomes.BAMBOO_JUNGLE),
                        biomes.getOrThrow(Biomes.SPARSE_JUNGLE),
                        biomes.getOrThrow(Biomes.GROVE)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.AVOCADO_KEY_VARIANT_5)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(AVOCADO_KEY_VARIANT_6, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JUNGLE),
                        biomes.getOrThrow(Biomes.BAMBOO_JUNGLE),
                        biomes.getOrThrow(Biomes.SPARSE_JUNGLE),
                        biomes.getOrThrow(Biomes.GROVE)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.AVOCADO_KEY_VARIANT_6)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(AVOCADO_KEY_VARIANT_7, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JUNGLE),
                        biomes.getOrThrow(Biomes.BAMBOO_JUNGLE),
                        biomes.getOrThrow(Biomes.SPARSE_JUNGLE),
                        biomes.getOrThrow(Biomes.GROVE)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.AVOCADO_KEY_VARIANT_7)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        // ORANGE VARIANTS
        context.register(ORANGE_KEY_VARIANT_1, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JUNGLE),
                        biomes.getOrThrow(Biomes.BAMBOO_JUNGLE),
                        biomes.getOrThrow(Biomes.SPARSE_JUNGLE),
                        biomes.getOrThrow(Biomes.SAVANNA),
                        biomes.getOrThrow(Biomes.SAVANNA_PLATEAU),
                        biomes.getOrThrow(Biomes.WINDSWEPT_SAVANNA)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ORANGE_KEY_VARIANT_1)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(ORANGE_KEY_VARIANT_2, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JUNGLE),
                        biomes.getOrThrow(Biomes.BAMBOO_JUNGLE),
                        biomes.getOrThrow(Biomes.SPARSE_JUNGLE),
                        biomes.getOrThrow(Biomes.SAVANNA),
                        biomes.getOrThrow(Biomes.SAVANNA_PLATEAU),
                        biomes.getOrThrow(Biomes.WINDSWEPT_SAVANNA)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ORANGE_KEY_VARIANT_2)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        // ORANGE VARIANTS
        context.register(ORANGE_KEY_VARIANT_3, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JUNGLE),
                        biomes.getOrThrow(Biomes.BAMBOO_JUNGLE),
                        biomes.getOrThrow(Biomes.SPARSE_JUNGLE),
                        biomes.getOrThrow(Biomes.SAVANNA),
                        biomes.getOrThrow(Biomes.SAVANNA_PLATEAU),
                        biomes.getOrThrow(Biomes.WINDSWEPT_SAVANNA)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ORANGE_KEY_VARIANT_3)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(ORANGE_KEY_VARIANT_4, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JUNGLE),
                        biomes.getOrThrow(Biomes.BAMBOO_JUNGLE),
                        biomes.getOrThrow(Biomes.SPARSE_JUNGLE),
                        biomes.getOrThrow(Biomes.SAVANNA),
                        biomes.getOrThrow(Biomes.SAVANNA_PLATEAU),
                        biomes.getOrThrow(Biomes.WINDSWEPT_SAVANNA)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ORANGE_KEY_VARIANT_4)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        // ORANGE VARIANTS
        context.register(ORANGE_KEY_VARIANT_5, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JUNGLE),
                        biomes.getOrThrow(Biomes.BAMBOO_JUNGLE),
                        biomes.getOrThrow(Biomes.SPARSE_JUNGLE),
                        biomes.getOrThrow(Biomes.SAVANNA),
                        biomes.getOrThrow(Biomes.SAVANNA_PLATEAU),
                        biomes.getOrThrow(Biomes.WINDSWEPT_SAVANNA)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ORANGE_KEY_VARIANT_5)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(ORANGE_KEY_VARIANT_6, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JUNGLE),
                        biomes.getOrThrow(Biomes.BAMBOO_JUNGLE),
                        biomes.getOrThrow(Biomes.SPARSE_JUNGLE),
                        biomes.getOrThrow(Biomes.SAVANNA),
                        biomes.getOrThrow(Biomes.SAVANNA_PLATEAU),
                        biomes.getOrThrow(Biomes.WINDSWEPT_SAVANNA)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ORANGE_KEY_VARIANT_6)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(ORANGE_KEY_VARIANT_7, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JUNGLE),
                        biomes.getOrThrow(Biomes.BAMBOO_JUNGLE),
                        biomes.getOrThrow(Biomes.SPARSE_JUNGLE),
                        biomes.getOrThrow(Biomes.SAVANNA),
                        biomes.getOrThrow(Biomes.SAVANNA_PLATEAU),
                        biomes.getOrThrow(Biomes.WINDSWEPT_SAVANNA)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ORANGE_KEY_VARIANT_7)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        // ---------------- BANANA VARIANTS ----------------

        context.register(BANANA_KEY_VARIANT_1, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JUNGLE),
                        biomes.getOrThrow(Biomes.BAMBOO_JUNGLE),
                        biomes.getOrThrow(Biomes.SPARSE_JUNGLE),
                        biomes.getOrThrow(Biomes.SAVANNA),
                        biomes.getOrThrow(Biomes.SAVANNA_PLATEAU),
                        biomes.getOrThrow(Biomes.WINDSWEPT_SAVANNA)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.BANANA_KEY_VARIANT_1)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(BANANA_KEY_VARIANT_2, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JUNGLE),
                        biomes.getOrThrow(Biomes.BAMBOO_JUNGLE),
                        biomes.getOrThrow(Biomes.SPARSE_JUNGLE),
                        biomes.getOrThrow(Biomes.SAVANNA),
                        biomes.getOrThrow(Biomes.SAVANNA_PLATEAU)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.BANANA_KEY_VARIANT_2)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(BANANA_KEY_VARIANT_3, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JUNGLE),
                        biomes.getOrThrow(Biomes.BAMBOO_JUNGLE),
                        biomes.getOrThrow(Biomes.SPARSE_JUNGLE)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.BANANA_KEY_VARIANT_3)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(BANANA_KEY_VARIANT_4, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JUNGLE),
                        biomes.getOrThrow(Biomes.BAMBOO_JUNGLE),
                        biomes.getOrThrow(Biomes.SPARSE_JUNGLE)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.BANANA_KEY_VARIANT_4)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(BANANA_KEY_VARIANT_5, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JUNGLE),
                        biomes.getOrThrow(Biomes.BAMBOO_JUNGLE),
                        biomes.getOrThrow(Biomes.SPARSE_JUNGLE)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.BANANA_KEY_VARIANT_5)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(BANANA_KEY_VARIANT_6, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JUNGLE),
                        biomes.getOrThrow(Biomes.BAMBOO_JUNGLE),
                        biomes.getOrThrow(Biomes.SPARSE_JUNGLE)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.BANANA_KEY_VARIANT_6)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(BANANA_KEY_VARIANT_7, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JUNGLE),
                        biomes.getOrThrow(Biomes.BAMBOO_JUNGLE),
                        biomes.getOrThrow(Biomes.SPARSE_JUNGLE)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.BANANA_KEY_VARIANT_7)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(DARK_OAK_KEY_VARIANT_1, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.RIVER)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.DARK_OAK_KEY_VARIANT_1)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(DARK_OAK_KEY_VARIANT_2, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.RIVER)

                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.DARK_OAK_KEY_VARIANT_2)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ModBiomeModifiers.BIRCH_VARIANT_1, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.BIRCH_FOREST),
                        biomes.getOrThrow(Biomes.OLD_GROWTH_BIRCH_FOREST)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.BIRCH_VARIANT_1)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

// Strawberry Bush (cool forests, meadows)
        context.register(STRAWBERRY_BUSH, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.BIRCH_FOREST),
                        biomes.getOrThrow(Biomes.OLD_GROWTH_BIRCH_FOREST),
                        biomes.getOrThrow(Biomes.TAIGA),
                        biomes.getOrThrow(Biomes.MEADOW)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.STRAWBERRY_BUSH_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

// Blackcurrant Bush (cooler / boreal)
        context.register(BLACKCURRANT_BUSH, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.TAIGA),
                        biomes.getOrThrow(Biomes.OLD_GROWTH_PINE_TAIGA),
                        biomes.getOrThrow(Biomes.OLD_GROWTH_SPRUCE_TAIGA),
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.MEADOW),
                        biomes.getOrThrow(Biomes.WINDSWEPT_HILLS),
                        biomes.getOrThrow(Biomes.WINDSWEPT_FOREST)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.BLACKCURRANT_BUSH_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, name));
    }
}
