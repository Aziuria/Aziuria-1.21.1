package net.Aziuria.aziuriamod.island.helper;

import net.Aziuria.aziuriamod.island.type.IslandBiomeType;
import net.Aziuria.aziuriamod.worldgen.ModConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.util.RandomSource;

import java.util.ArrayList;
import java.util.List;

public class FruitTreeHelper {

    public static ResourceKey<ConfiguredFeature<?, ?>> pickFruitTree(IslandBiomeType biomeType, RandomSource random) {

        if (random.nextDouble() >= 0.2) return null;
        List<ResourceKey<ConfiguredFeature<?, ?>>> possibleFruits = new ArrayList<>();

        // --- APPLE ---
        if (biomeType == IslandBiomeType.PLAINS ||
                biomeType == IslandBiomeType.SUNFLOWER_PLAINS ||
                biomeType == IslandBiomeType.FOREST ||
                biomeType == IslandBiomeType.FLOWER_FOREST ||
                biomeType == IslandBiomeType.MEADOW ||
                biomeType == IslandBiomeType.GROVE ||
                biomeType == IslandBiomeType.WOODED_BADLANDS) {
            possibleFruits.addAll(List.of(
                    ModConfiguredFeatures.APPLE_KEY_VARIANT_1,
                    ModConfiguredFeatures.APPLE_KEY_VARIANT_2,
                    ModConfiguredFeatures.APPLE_KEY_VARIANT_3,
                    ModConfiguredFeatures.APPLE_KEY_VARIANT_4,
                    ModConfiguredFeatures.APPLE_KEY_VARIANT_5,
                    ModConfiguredFeatures.APPLE_KEY_VARIANT_6,
                    ModConfiguredFeatures.APPLE_KEY_VARIANT_7
            ));
        }

        // --- PEAR ---
        if (biomeType == IslandBiomeType.PLAINS ||
                biomeType == IslandBiomeType.SUNFLOWER_PLAINS ||
                biomeType == IslandBiomeType.FOREST ||
                biomeType == IslandBiomeType.FLOWER_FOREST ||
                biomeType == IslandBiomeType.MEADOW ||
                biomeType == IslandBiomeType.WOODED_BADLANDS ||
                biomeType == IslandBiomeType.GROVE) {
            possibleFruits.addAll(List.of(
                    ModConfiguredFeatures.PEAR_KEY_VARIANT_1,
                    ModConfiguredFeatures.PEAR_KEY_VARIANT_2,
                    ModConfiguredFeatures.PEAR_KEY_VARIANT_3,
                    ModConfiguredFeatures.PEAR_KEY_VARIANT_4,
                    ModConfiguredFeatures.PEAR_KEY_VARIANT_5,
                    ModConfiguredFeatures.PEAR_KEY_VARIANT_6,
                    ModConfiguredFeatures.PEAR_KEY_VARIANT_7
            ));
        }

        // --- CHERRY ---
        if (biomeType == IslandBiomeType.CHERRY_GROVE ||
                biomeType == IslandBiomeType.PLAINS ||
                biomeType == IslandBiomeType.SUNFLOWER_PLAINS ||
                biomeType == IslandBiomeType.WOODED_BADLANDS ||
                biomeType == IslandBiomeType.FOREST ||
                biomeType == IslandBiomeType.FLOWER_FOREST ||
                biomeType == IslandBiomeType.MEADOW) {
            possibleFruits.addAll(List.of(
                    ModConfiguredFeatures.CHERRY_KEY_VARIANT_1,
                    ModConfiguredFeatures.CHERRY_KEY_VARIANT_2,
                    ModConfiguredFeatures.CHERRY_KEY_VARIANT_3,
                    ModConfiguredFeatures.CHERRY_KEY_VARIANT_4,
                    ModConfiguredFeatures.CHERRY_KEY_VARIANT_5,
                    ModConfiguredFeatures.CHERRY_KEY_VARIANT_6,
                    ModConfiguredFeatures.CHERRY_KEY_VARIANT_7
            ));
        }

        // --- AVOCADO ---
        if (biomeType == IslandBiomeType.JUNGLE ||
                biomeType == IslandBiomeType.BAMBOO_JUNGLE ||
                biomeType == IslandBiomeType.SPARSE_JUNGLE ||
                biomeType == IslandBiomeType.GROVE) {
            possibleFruits.addAll(List.of(
                    ModConfiguredFeatures.AVOCADO_KEY_VARIANT_1,
                    ModConfiguredFeatures.AVOCADO_KEY_VARIANT_2,
                    ModConfiguredFeatures.AVOCADO_KEY_VARIANT_3,
                    ModConfiguredFeatures.AVOCADO_KEY_VARIANT_4,
                    ModConfiguredFeatures.AVOCADO_KEY_VARIANT_5,
                    ModConfiguredFeatures.AVOCADO_KEY_VARIANT_6,
                    ModConfiguredFeatures.AVOCADO_KEY_VARIANT_7
            ));
        }

        // --- ORANGE ---
        if (biomeType == IslandBiomeType.JUNGLE ||
                biomeType == IslandBiomeType.BAMBOO_JUNGLE ||
                biomeType == IslandBiomeType.SPARSE_JUNGLE ||
                biomeType == IslandBiomeType.SAVANNA ||
                biomeType == IslandBiomeType.SAVANNA_PLATEAU ||
                biomeType == IslandBiomeType.WINDSWEPT_SAVANNA) {
            possibleFruits.addAll(List.of(
                    ModConfiguredFeatures.ORANGE_KEY_VARIANT_1,
                    ModConfiguredFeatures.ORANGE_KEY_VARIANT_2,
                    ModConfiguredFeatures.ORANGE_KEY_VARIANT_3,
                    ModConfiguredFeatures.ORANGE_KEY_VARIANT_4,
                    ModConfiguredFeatures.ORANGE_KEY_VARIANT_5,
                    ModConfiguredFeatures.ORANGE_KEY_VARIANT_6,
                    ModConfiguredFeatures.ORANGE_KEY_VARIANT_7
            ));
        }

        // --- BANANA ---
        if (biomeType == IslandBiomeType.JUNGLE ||
                biomeType == IslandBiomeType.BAMBOO_JUNGLE ||
                biomeType == IslandBiomeType.SPARSE_JUNGLE ||
                biomeType == IslandBiomeType.SAVANNA ||
                biomeType == IslandBiomeType.WINDSWEPT_SAVANNA ||
                biomeType == IslandBiomeType.SAVANNA_PLATEAU) {
            possibleFruits.addAll(List.of(
                    ModConfiguredFeatures.BANANA_KEY_VARIANT_1,
                    ModConfiguredFeatures.BANANA_KEY_VARIANT_2,
                    ModConfiguredFeatures.BANANA_KEY_VARIANT_3,
                    ModConfiguredFeatures.BANANA_KEY_VARIANT_4,
                    ModConfiguredFeatures.BANANA_KEY_VARIANT_5,
                    ModConfiguredFeatures.BANANA_KEY_VARIANT_6,
                    ModConfiguredFeatures.BANANA_KEY_VARIANT_7
            ));
        }

        if (possibleFruits.isEmpty()) return null;

        // pick one random fruit variant
        return possibleFruits.get(random.nextInt(possibleFruits.size()));
    }
}