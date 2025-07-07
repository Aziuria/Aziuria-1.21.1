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

    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
