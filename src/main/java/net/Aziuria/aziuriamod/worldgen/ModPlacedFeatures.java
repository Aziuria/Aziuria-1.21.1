package net.Aziuria.aziuriamod.worldgen;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.Aziuria.aziuriamod.worldgen.NearLogPlacementModifier;

import java.util.List;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> SULPHUR_ORE_PLACED_KEY = registerKey("sulphur_ore_placed");
    public static final ResourceKey<PlacedFeature> POTASSIUM_ORE_PLACED_KEY = registerKey("potassium_ore_placed");
    public static final ResourceKey<PlacedFeature> SULPHUR_ORE_RARE_MID_PLACED_KEY = registerKey("sulphur_ore_rare_mid_placed");
    public static final ResourceKey<PlacedFeature> POTASSIUM_ORE_RARE_MID_PLACED_KEY = registerKey("potassium_ore_rare_mid_placed");
    public static final ResourceKey<PlacedFeature> SULPHUR_ORE_SURFACE_RARE_PLACED_KEY = registerKey("sulphur_ore_surface_rare_placed");
    public static final ResourceKey<PlacedFeature> POTASSIUM_ORE_SURFACE_RARE_PLACED_KEY = registerKey("potassium_ore_surface_rare_placed");

    public static final ResourceKey<PlacedFeature> LEAF_LITTER_PLACED_KEY = registerKey("leaf_litter_placed");
    public static final ResourceKey<PlacedFeature> LEAF_LITTER_EXTRA_PLACED_KEY = registerKey("leaf_litter_extra");
    public static final ResourceKey<PlacedFeature> LEAF_LITTER_EXTRA2_PLACED_KEY = registerKey("leaf_litter_extra2");
    public static final ResourceKey<PlacedFeature> LEAF_LITTER_EXTRA3_PLACED_KEY = registerKey("leaf_litter_extra3");


    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, SULPHUR_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_SULPHUR_ORE_KEY),
                ModOrePlacement.commonOrePlacement(9, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(20))));
        register(context, POTASSIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_POTASSIUM_ORE_KEY),
                ModOrePlacement.commonOrePlacement(9, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(20))));

        register(context, SULPHUR_ORE_RARE_MID_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_SULPHUR_ORE_RARE_MID_KEY),
                ModOrePlacement.rareOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(20), VerticalAnchor.absolute(40))));
        register(context, POTASSIUM_ORE_RARE_MID_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_POTASSIUM_ORE_RARE_MID_KEY),
                ModOrePlacement.rareOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(20), VerticalAnchor.absolute(40))));

        register(context, SULPHUR_ORE_SURFACE_RARE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_SULPHUR_ORE_SURFACE_RARE_KEY),
                ModOrePlacement.rareOrePlacement(40, HeightRangePlacement.uniform(VerticalAnchor.absolute(40), VerticalAnchor.absolute(80))));
        register(context, POTASSIUM_ORE_SURFACE_RARE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_POTASSIUM_ORE_SURFACE_RARE_KEY),
                ModOrePlacement.rareOrePlacement(40, HeightRangePlacement.uniform(VerticalAnchor.absolute(40), VerticalAnchor.absolute(80))));

// original
        register(context, LEAF_LITTER_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.LEAF_LITTER_KEY),
                List.of(
                        RarityFilter.onAverageOnceEvery(2),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        NearLogPlacementModifier.of(6, 0.7),
                        BiomeFilter.biome()
                ));

// additional (different key)
        register(context, LEAF_LITTER_EXTRA_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.LEAF_LITTER_KEY),
                List.of(
                        RarityFilter.onAverageOnceEvery(2),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        NearLogPlacementModifier.of(6, 0.7),
                        BiomeFilter.biome()
                ));

        register(context, LEAF_LITTER_EXTRA2_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.LEAF_LITTER_KEY),
                List.of(
                        RarityFilter.onAverageOnceEvery(3),  // Slightly less frequent to balance
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        NearLogPlacementModifier.of(5, 0.5),  // Smaller radius, gentler falloff
                        BiomeFilter.biome()
                ));

        register(context, LEAF_LITTER_EXTRA3_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.LEAF_LITTER_KEY),
                List.of(
                        RarityFilter.onAverageOnceEvery(4),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        NearLogPlacementModifier.of(8, 1.0),  // Larger radius, stronger falloff
                        BiomeFilter.biome()
                ));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
