package net.Aziuria.aziuriamod.block.world;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class TreeGenerator {

    /**
     * Generates a vanilla-shaped tree at the given position,
     * using Minecraft's built-in ConfiguredFeature.
     * If placement fails, fallback to planting a sapling.
     */
    public static void generateTree(ServerLevel level, BlockPos pos, IslandBiomeType biomeType, RandomSource random) {
        // Pick a proper feature key based on biome type
        ResourceKey<ConfiguredFeature<?, ?>> featureKey = switch (biomeType) {
            case FOREST, PLAINS -> random.nextDouble() < 0.2 ? TreeFeatures.FANCY_OAK : TreeFeatures.OAK;
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
            System.out.println("[TreeGenerator] No feature defined for biome: " + biomeType);
            return;
        }

        Registry<ConfiguredFeature<?, ?>> registry = level.registryAccess()
                .registryOrThrow(Registries.CONFIGURED_FEATURE);

        ConfiguredFeature<?, ?> feature = registry.get(featureKey);
        if (feature != null) {
            boolean success = feature.place(level, level.getChunkSource().getGenerator(), random, pos);
            if (!success) {
                System.out.println("[TreeGenerator] Feature placement failed at " + pos + ", planting sapling instead.");
                plantSapling(level, pos, biomeType);
            } else {
                System.out.println("[TreeGenerator] Placed tree feature " + featureKey.location() + " at " + pos);
            }
        } else {
            System.out.println("[TreeGenerator] Could not find feature in registry: " + featureKey);
            plantSapling(level, pos, biomeType);
        }
    }

    public static void plantSapling(ServerLevel level, BlockPos pos, IslandBiomeType biome) {
        BlockState saplingState = null;
        switch (biome) {
            case FOREST:
                saplingState = Blocks.OAK_SAPLING.defaultBlockState();
                break;
            case BIRCH:
                saplingState = Blocks.BIRCH_SAPLING.defaultBlockState();
                break;
            case JUNGLE:
                saplingState = Blocks.JUNGLE_SAPLING.defaultBlockState();
                break;
            case SAVANNA:
                saplingState = Blocks.ACACIA_SAPLING.defaultBlockState();
                break;
            case TAIGA:
                saplingState = Blocks.SPRUCE_SAPLING.defaultBlockState();
                break;
            case DARK_FOREST:
                saplingState = Blocks.DARK_OAK_SAPLING.defaultBlockState();
                break;
            // Add other mappings if needed
            default:
                saplingState = null;
        }

        if (saplingState != null) {
            BlockPos belowPos = pos.below();
            BlockState belowState = level.getBlockState(belowPos);

            if ((belowState.is(Blocks.DIRT) || belowState.is(Blocks.GRASS_BLOCK)) && level.isEmptyBlock(pos)) {
                level.setBlock(pos, saplingState, 2);

                // Try to immediately grow the sapling into a tree
                if (saplingState.getBlock() instanceof SaplingBlock saplingBlock) {
                    saplingBlock.advanceTree(level, pos, saplingState, RandomSource.create());
                }
            }
        }
    }
}