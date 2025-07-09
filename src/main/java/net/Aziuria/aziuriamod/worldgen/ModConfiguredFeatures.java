package net.Aziuria.aziuriamod.worldgen;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.Aziuria.aziuriamod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.SimpleBlockFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SULPHUR_ORE_ULTRA_DEEP_KEY = registerKey("sulphur_ore_ultra_deep");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SULPHUR_ORE_DEEP_KEY = registerKey("sulphur_ore_deep");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SULPHUR_ORE_MID_KEY = registerKey("sulphur_ore_mid");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SULPHUR_ORE_UPPER_KEY = registerKey("sulphur_ore_upper");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SULPHUR_ORE_MOUNTAINS_KEY = registerKey("sulphur_ore_mountains");

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_POTASSIUM_ORE_ULTRA_DEEP_KEY = registerKey("potassium_ore_ultra_deep");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_POTASSIUM_ORE_DEEP_KEY = registerKey("potassium_ore_deep");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_POTASSIUM_ORE_MID_KEY = registerKey("potassium_ore_mid");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_POTASSIUM_ORE_UPPER_KEY = registerKey("potassium_ore_upper");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_POTASSIUM_ORE_MOUNTAINS_KEY = registerKey("potassium_ore_mountains");

    public static final ResourceKey<ConfiguredFeature<?, ?>> LEAF_LITTER_KEY = registerKey("leaf_litter");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLAX_FLOWER_KEY = registerKey("flax_flower");

    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_KEY_VARIANT_1 = registerKey("apple_variant_1");
    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_KEY_VARIANT_2 = registerKey("apple_variant_2");
    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_KEY_VARIANT_3 = registerKey("apple_variant_3");
    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_KEY_VARIANT_4 = registerKey("apple_variant_4");
    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_KEY_VARIANT_5 = registerKey("apple_variant_5");
    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_KEY_VARIANT_6 = registerKey("apple_variant_6");
    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_KEY_VARIANT_7 = registerKey("apple_variant_7");
    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_KEY_VARIANT_8 = registerKey("apple_variant_8");
    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_KEY_VARIANT_9 = registerKey("apple_variant_9");
    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_KEY_VARIANT_10 = registerKey("apple_variant_10");



    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);


        List<OreConfiguration.TargetBlockState> overworldSulphurOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.SULPHUR_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_SULPHUR_ORE.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> overworldPotassiumOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.POTASSIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_POTASSIUM_ORE.get().defaultBlockState()));

        register(context, OVERWORLD_SULPHUR_ORE_ULTRA_DEEP_KEY, Feature.ORE, new OreConfiguration(overworldSulphurOres, 6));
        register(context, OVERWORLD_SULPHUR_ORE_DEEP_KEY, Feature.ORE, new OreConfiguration(overworldSulphurOres, 5));
        register(context, OVERWORLD_SULPHUR_ORE_MID_KEY, Feature.ORE, new OreConfiguration(overworldSulphurOres, 4));
        register(context, OVERWORLD_SULPHUR_ORE_UPPER_KEY, Feature.ORE, new OreConfiguration(overworldSulphurOres, 3));
        register(context, OVERWORLD_SULPHUR_ORE_MOUNTAINS_KEY, Feature.ORE, new OreConfiguration(overworldSulphurOres, 2));

        register(context, OVERWORLD_POTASSIUM_ORE_ULTRA_DEEP_KEY, Feature.ORE, new OreConfiguration(overworldPotassiumOres, 6));
        register(context, OVERWORLD_POTASSIUM_ORE_DEEP_KEY, Feature.ORE, new OreConfiguration(overworldPotassiumOres, 5));
        register(context, OVERWORLD_POTASSIUM_ORE_MID_KEY, Feature.ORE, new OreConfiguration(overworldPotassiumOres, 4));
        register(context, OVERWORLD_POTASSIUM_ORE_UPPER_KEY, Feature.ORE, new OreConfiguration(overworldPotassiumOres, 3));
        register(context, OVERWORLD_POTASSIUM_ORE_MOUNTAINS_KEY, Feature.ORE, new OreConfiguration(overworldPotassiumOres, 2));

        register(context, LEAF_LITTER_KEY, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(
                        Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.LEAF_LITTER.get().defaultBlockState())),
                        List.of(Blocks.GRASS_BLOCK)

                )
        );

        register(context, FLAX_FLOWER_KEY, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(
                        Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.FLAX_FLOWER_BLOCK.get().defaultBlockState())),
                        List.of(Blocks.GRASS_BLOCK),
                        12
                )
        );

        // Variant 1 - Small, bushy
        register(context, APPLE_KEY_VARIANT_1, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(3, 1, 1),
                BlockStateProvider.simple(ModBlocks.APPLE_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// Variant 2 - Medium, standard
        register(context, APPLE_KEY_VARIANT_2, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(4, 2, 2),
                BlockStateProvider.simple(ModBlocks.APPLE_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(2), 3),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Variant 3 - Tall, slim
        register(context, APPLE_KEY_VARIANT_3, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new StraightTrunkPlacer(6, 2, 0),
                BlockStateProvider.simple(ModBlocks.APPLE_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(1), ConstantInt.of(2), 2),
                new TwoLayersFeatureSize(2, 0, 2)).build());

// Variant 4 - Short, wide canopy
        register(context, APPLE_KEY_VARIANT_4, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(3, 1, 1),
                BlockStateProvider.simple(ModBlocks.APPLE_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), 3),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// Variant 5 - Medium, dense leaves
        register(context, APPLE_KEY_VARIANT_5, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(5, 2, 1),
                BlockStateProvider.simple(ModBlocks.APPLE_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Variant 6 - Large, spreading branches
        register(context, APPLE_KEY_VARIANT_6, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(7, 3, 2),
                BlockStateProvider.simple(ModBlocks.APPLE_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(2, 0, 2)).build());

// Variant 7 - Medium, layered canopy
        register(context, APPLE_KEY_VARIANT_7, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(5, 2, 2),
                BlockStateProvider.simple(ModBlocks.APPLE_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(2), 4),
                new TwoLayersFeatureSize(2, 0, 2)).build());

// Variant 8 - Small, compact
        register(context, APPLE_KEY_VARIANT_8, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new StraightTrunkPlacer(3, 1, 0),
                BlockStateProvider.simple(ModBlocks.APPLE_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(1), ConstantInt.of(2), 2),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// Variant 9 - Tall, fluffy canopy
        register(context, APPLE_KEY_VARIANT_9, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new StraightTrunkPlacer(7, 2, 0),
                BlockStateProvider.simple(ModBlocks.APPLE_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), 3),
                new TwoLayersFeatureSize(2, 1, 2)).build());

// Variant 10 - Large, layered, lush
        register(context, APPLE_KEY_VARIANT_10, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(6, 3, 2),
                BlockStateProvider.simple(ModBlocks.APPLE_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(3), 4),
                new TwoLayersFeatureSize(2, 1, 3)).build());


    }



    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}