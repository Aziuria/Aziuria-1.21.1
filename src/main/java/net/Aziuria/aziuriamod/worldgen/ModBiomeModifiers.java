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

    // POTASSIUM
    public static final ResourceKey<BiomeModifier> ADD_POTASSIUM_ORE_ULTRA_DEEP = registerKey("add_potassium_ore_ultra_deep");
    public static final ResourceKey<BiomeModifier> ADD_POTASSIUM_ORE_DEEP = registerKey("add_potassium_ore_deep");
    public static final ResourceKey<BiomeModifier> ADD_POTASSIUM_ORE_MID = registerKey("add_potassium_ore_mid");
    public static final ResourceKey<BiomeModifier> ADD_POTASSIUM_ORE_UPPER = registerKey("add_potassium_ore_upper");
    public static final ResourceKey<BiomeModifier> ADD_POTASSIUM_ORE_MOUNTAINS = registerKey("add_potassium_ore_mountains");

    public static final ResourceKey<BiomeModifier> ADD_LEAF_LITTER = registerKey("add_leaf_litter");
    public static final ResourceKey<BiomeModifier> ADD_LEAF_LITTER_EXTRA = registerKey("add_leaf_litter_extra");
    public static final ResourceKey<BiomeModifier> ADD_LEAF_LITTER_EXTRA2 = registerKey("add_leaf_litter_extra2");
    public static final ResourceKey<BiomeModifier> ADD_LEAF_LITTER_EXTRA3 = registerKey("add_leaf_litter_extra3");

    public static final ResourceKey<BiomeModifier> ADD_FLAX_FLOWER = registerKey("add_flax_flower");

    public static final ResourceKey<BiomeModifier> APPLE_KEY_VARIANT_1 = registerKey("apple_variant_1");
    public static final ResourceKey<BiomeModifier> APPLE_KEY_VARIANT_2 = registerKey("apple_variant_2");
    public static final ResourceKey<BiomeModifier> APPLE_KEY_VARIANT_3 = registerKey("apple_variant_3");
    public static final ResourceKey<BiomeModifier> APPLE_KEY_VARIANT_4 = registerKey("apple_variant_4");
    public static final ResourceKey<BiomeModifier> APPLE_KEY_VARIANT_5 = registerKey("apple_variant_5");
    public static final ResourceKey<BiomeModifier> APPLE_KEY_VARIANT_6 = registerKey("apple_variant_6");
    public static final ResourceKey<BiomeModifier> APPLE_KEY_VARIANT_7 = registerKey("apple_variant_7");


    public static final ResourceKey<BiomeModifier> DARK_OAK_KEY_VARIANT_1 = registerKey("dark_oak_variant_1");
    public static final ResourceKey<BiomeModifier> DARK_OAK_KEY_VARIANT_2 = registerKey("dark_oak_variant_2");

    public static final ResourceKey<BiomeModifier> BIRCH_VARIANT_1 = registerKey("birch_variant_1");

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
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.FLAX_FLOWER_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(APPLE_KEY_VARIANT_1, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.APPLE_KEY_VARIANT_1)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(APPLE_KEY_VARIANT_2, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.MEADOW),
                        biomes.getOrThrow(Biomes.CHERRY_GROVE)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.APPLE_KEY_VARIANT_2)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(APPLE_KEY_VARIANT_3, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.RIVER),
                        biomes.getOrThrow(Biomes.PLAINS)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.APPLE_KEY_VARIANT_3)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(APPLE_KEY_VARIANT_4, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.APPLE_KEY_VARIANT_4)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(APPLE_KEY_VARIANT_5, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.FLOWER_FOREST)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.APPLE_KEY_VARIANT_5)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(APPLE_KEY_VARIANT_6, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.BIRCH_FOREST),
                        biomes.getOrThrow(Biomes.MEADOW)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.APPLE_KEY_VARIANT_6)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(APPLE_KEY_VARIANT_7, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.OLD_GROWTH_BIRCH_FOREST),
                        biomes.getOrThrow(Biomes.FOREST)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.APPLE_KEY_VARIANT_7)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

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
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, name));
    }
}
