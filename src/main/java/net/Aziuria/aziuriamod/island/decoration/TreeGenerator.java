package net.Aziuria.aziuriamod.island.decoration;

import net.Aziuria.aziuriamod.island.helper.FruitTreeHelper;
import net.Aziuria.aziuriamod.island.type.IslandBiomeType;
import net.Aziuria.aziuriamod.worldgen.ModConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class TreeGenerator {

    public static void generateTree(ServerLevel level, BlockPos pos, IslandBiomeType biomeType, RandomSource random) {

        if (random.nextDouble() < 0.7) return; // 50% chance to skip tree

        ResourceKey<ConfiguredFeature<?, ?>> featureKey;

        // --- attempt to pick a fruit tree first ---
        featureKey = FruitTreeHelper.pickFruitTree(biomeType, random);

        // --- if no fruit tree picked, fall back to vanilla trees ---
        if (featureKey == null) {
            featureKey = switch (biomeType) {

                case FOREST, PLAINS, SUNFLOWER_PLAINS, FLOWER_FOREST, MEADOW, GROVE, WOODED_BADLANDS -> {
                    double chance = random.nextDouble();
                    if (chance < 0.1) yield TreeFeatures.BIRCH;
                    else if (chance < 0.3) yield TreeFeatures.FANCY_OAK;
                    else yield TreeFeatures.OAK;
                }
                case BIRCH -> TreeFeatures.BIRCH;
                case DARK_FOREST -> random.nextDouble() < 0.3 ? TreeFeatures.DARK_OAK : TreeFeatures.FANCY_OAK;
                case TAIGA -> random.nextDouble() < 0.2 ? TreeFeatures.PINE : TreeFeatures.SPRUCE;
                case SNOWY, MOUNTAINS -> TreeFeatures.SPRUCE;
                case SWAMP -> TreeFeatures.SWAMP_OAK;
                case JUNGLE, BAMBOO_JUNGLE, SPARSE_JUNGLE -> random.nextDouble() < 0.2 ? TreeFeatures.MEGA_JUNGLE_TREE : TreeFeatures.JUNGLE_TREE;
                case SAVANNA, SAVANNA_PLATEAU, WINDSWEPT_SAVANNA -> TreeFeatures.ACACIA;
                case DESERT, BEACH, MUSHROOM -> null; // no vanilla trees here
                case CHERRY_GROVE -> TreeFeatures.CHERRY; // vanilla cherry tree

                default -> null; // <-- covers any remaining enum values
            };
        }

        if (featureKey == null) return;

        Registry<ConfiguredFeature<?, ?>> registry = level.registryAccess()
                .registryOrThrow(Registries.CONFIGURED_FEATURE);

        ConfiguredFeature<?, ?> feature = registry.get(featureKey);
        if (feature != null) {
            feature.place(level, level.getChunkSource().getGenerator(), random, pos);
        }
    }
}