package net.Aziuria.aziuriamod.block.world;

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

        if (random.nextDouble() < 0.5) return;
        ResourceKey<ConfiguredFeature<?, ?>> featureKey = switch (biomeType) {

            case FOREST, PLAINS -> {
                double chance = random.nextDouble();
                if (chance < 0.1) yield TreeFeatures.BIRCH;
                else if (chance < 0.3) yield TreeFeatures.FANCY_OAK;
                else yield TreeFeatures.OAK;
            }
            case MEADOW -> TreeFeatures.FANCY_OAK;
            case SNOWY, MOUNTAINS -> TreeFeatures.SPRUCE;
            case TAIGA -> random.nextDouble() < 0.2 ? TreeFeatures.PINE : TreeFeatures.SPRUCE;
            case SWAMP -> TreeFeatures.SWAMP_OAK;
            case JUNGLE -> random.nextDouble() < 0.2 ? TreeFeatures.MEGA_JUNGLE_TREE : TreeFeatures.JUNGLE_TREE;
            case SAVANNA -> TreeFeatures.ACACIA;
            case BIRCH -> TreeFeatures.BIRCH;
            case DARK_FOREST -> random.nextDouble() < 0.3 ? TreeFeatures.DARK_OAK : TreeFeatures.FANCY_OAK;
            default -> null;
        };

        if (featureKey == null) {
            return;
        }

        Registry<ConfiguredFeature<?, ?>> registry = level.registryAccess()
                .registryOrThrow(Registries.CONFIGURED_FEATURE);

        ConfiguredFeature<?, ?> feature = registry.get(featureKey);
        if (feature != null) {
            feature.place(level, level.getChunkSource().getGenerator(), random, pos);
        }
    }
}