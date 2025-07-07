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

    public static final ResourceKey<BiomeModifier> ADD_SULPHUR_ORE = registerKey("add_sulphur_ore");
    public static final ResourceKey<BiomeModifier> ADD_POTASSIUM_ORE = registerKey("add_potassium_ore");
    public static final ResourceKey<BiomeModifier> ADD_SULPHUR_ORE_RARE_MID = registerKey("add_sulphur_ore_rare_mid");
    public static final ResourceKey<BiomeModifier> ADD_POTASSIUM_ORE_RARE_MID = registerKey("add_potassium_ore_rare_mid");
    public static final ResourceKey<BiomeModifier> ADD_SULPHUR_ORE_SURFACE_RARE = registerKey("add_sulphur_ore_surface_rare");
    public static final ResourceKey<BiomeModifier> ADD_POTASSIUM_ORE_SURFACE_RARE = registerKey("add_potassium_ore_surface_rare");

    public static final ResourceKey<BiomeModifier> ADD_LEAF_LITTER = registerKey("add_leaf_litter");
    public static final ResourceKey<BiomeModifier> ADD_LEAF_LITTER_EXTRA = registerKey("add_leaf_litter_extra");
    public static final ResourceKey<BiomeModifier> ADD_LEAF_LITTER_EXTRA2 = registerKey("add_leaf_litter_extra2");
    public static final ResourceKey<BiomeModifier> ADD_LEAF_LITTER_EXTRA3 = registerKey("add_leaf_litter_extra3");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        // CF -> PF -> BM
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_SULPHUR_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SULPHUR_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_POTASSIUM_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.POTASSIUM_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_SULPHUR_ORE_RARE_MID, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SULPHUR_ORE_RARE_MID_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_POTASSIUM_ORE_RARE_MID, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.POTASSIUM_ORE_RARE_MID_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_SULPHUR_ORE_SURFACE_RARE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SULPHUR_ORE_SURFACE_RARE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_POTASSIUM_ORE_SURFACE_RARE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.POTASSIUM_ORE_SURFACE_RARE_PLACED_KEY)),
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
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, name));
    }
}
