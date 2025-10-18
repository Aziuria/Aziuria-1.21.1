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
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.SimpleBlockFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;
import java.util.OptionalInt;

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

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SPINEL_ORE_ULTRA_DEEP_KEY = registerKey("spinel_ore_ultra_deep");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SPINEL_ORE_DEEP_KEY = registerKey("spinel_ore_deep");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SPINEL_ORE_MID_KEY = registerKey("spinel_ore_mid");

    public static final ResourceKey<ConfiguredFeature<?, ?>> LEAF_LITTER_KEY = registerKey("leaf_litter");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLAX_FLOWER_KEY = registerKey("flax_flower");
    public static final ResourceKey<ConfiguredFeature<?, ?>> YUCCA_PLANT_KEY = registerKey("yucca_plant");

    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_KEY_VARIANT_1 = registerKey("apple_variant_1");
    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_KEY_VARIANT_2 = registerKey("apple_variant_2");
    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_KEY_VARIANT_3 = registerKey("apple_variant_3");
    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_KEY_VARIANT_4 = registerKey("apple_variant_4");
    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_KEY_VARIANT_5 = registerKey("apple_variant_5");
    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_KEY_VARIANT_6 = registerKey("apple_variant_6");
    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_KEY_VARIANT_7 = registerKey("apple_variant_7");

    public static final ResourceKey<ConfiguredFeature<?, ?>> PEAR_KEY_VARIANT_1 = registerKey("pear_variant_1");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PEAR_KEY_VARIANT_2 = registerKey("pear_variant_2");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PEAR_KEY_VARIANT_3 = registerKey("pear_variant_3");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PEAR_KEY_VARIANT_4 = registerKey("pear_variant_4");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PEAR_KEY_VARIANT_5 = registerKey("pear_variant_5");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PEAR_KEY_VARIANT_6 = registerKey("pear_variant_6");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PEAR_KEY_VARIANT_7 = registerKey("pear_variant_7");

    public static final ResourceKey<ConfiguredFeature<?, ?>> CHERRY_KEY_VARIANT_1 = registerKey("cherry_variant_1");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CHERRY_KEY_VARIANT_2 = registerKey("cherry_variant_2");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CHERRY_KEY_VARIANT_3 = registerKey("cherry_variant_3");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CHERRY_KEY_VARIANT_4 = registerKey("cherry_variant_4");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CHERRY_KEY_VARIANT_5 = registerKey("cherry_variant_5");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CHERRY_KEY_VARIANT_6 = registerKey("cherry_variant_6");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CHERRY_KEY_VARIANT_7 = registerKey("cherry_variant_7");

    public static final ResourceKey<ConfiguredFeature<?, ?>> AVOCADO_KEY_VARIANT_1 = registerKey("avocado_variant_1");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AVOCADO_KEY_VARIANT_2 = registerKey("avocado_variant_2");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AVOCADO_KEY_VARIANT_3 = registerKey("avocado_variant_3");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AVOCADO_KEY_VARIANT_4 = registerKey("avocado_variant_4");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AVOCADO_KEY_VARIANT_5 = registerKey("avocado_variant_5");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AVOCADO_KEY_VARIANT_6 = registerKey("avocado_variant_6");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AVOCADO_KEY_VARIANT_7 = registerKey("avocado_variant_7");

    public static final ResourceKey<ConfiguredFeature<?, ?>> DARK_OAK_VARIANT_1 = registerKey("dark_oak_variant_1");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DARK_OAK_VARIANT_2 = registerKey("dark_oak_variant_2");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIRCH_VARIANT_1 = registerKey("birch_variant_1");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACKCURRANT_BUSH_KEY = registerKey("blackcurrant_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STRAWBERRY_BUSH_KEY = registerKey("strawberry_bush");



    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);


        List<OreConfiguration.TargetBlockState> overworldSulphurOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.SULPHUR_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_SULPHUR_ORE.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> overworldPotassiumOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.POTASSIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_POTASSIUM_ORE.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> overworldSpinelOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.SPINEL_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_SPINEL_ORE.get().defaultBlockState()));

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

        register(context, OVERWORLD_SPINEL_ORE_ULTRA_DEEP_KEY, Feature.ORE, new OreConfiguration(overworldSpinelOres, 4));
        register(context, OVERWORLD_SPINEL_ORE_DEEP_KEY, Feature.ORE, new OreConfiguration(overworldSpinelOres, 3));
        register(context, OVERWORLD_SPINEL_ORE_MID_KEY, Feature.ORE, new OreConfiguration(overworldSpinelOres, 2));

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

        register(context, YUCCA_PLANT_KEY, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(
                        Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.YUCCA_PLANT_BLOCK.get().defaultBlockState())),
                        List.of(Blocks.SAND, Blocks.RED_SAND),
                        6 // patch count; lower for realistic sparse desert growth
                )
        );

// Variant 1 - Large, spreading branches
        register(context, APPLE_KEY_VARIANT_1, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(7, 3, 2),
                BlockStateProvider.simple(ModBlocks.APPLE_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(2, 0, 2)).build());

// Variant 2 - Medium, standard
        register(context, APPLE_KEY_VARIANT_2, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(4, 2, 2),
                BlockStateProvider.simple(ModBlocks.APPLE_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), 3),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Variant 3 - Tall, slim
        register(context, APPLE_KEY_VARIANT_3, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new StraightTrunkPlacer(6, 1, 0),
                BlockStateProvider.simple(ModBlocks.APPLE_LEAVES.get()),
                new FancyFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), 5),
                new ThreeLayersFeatureSize(2, 1, 2, 3, 2, OptionalInt.of(3))
        ).build());

// Variant 4 - Short, wide canopy (with FancyFoliagePlacer)
        register(context, APPLE_KEY_VARIANT_4, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(3, 1, 1),
                BlockStateProvider.simple(ModBlocks.APPLE_LEAVES.get()),
                new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), 3),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// Variant 5 - Medium, dense leaves (with FancyFoliagePlacer)
        register(context, APPLE_KEY_VARIANT_5, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(5, 2, 1),
                BlockStateProvider.simple(ModBlocks.APPLE_LEAVES.get()),
                new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),
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


        // Variant 1 - Small, bushy dark oak
        register(context, DARK_OAK_VARIANT_1, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.DARK_OAK_LOG),
                new ForkingTrunkPlacer(4, 2, 1),
                BlockStateProvider.simple(Blocks.DARK_OAK_LEAVES),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        // Variant 2 - Tall, slim dark oak
        register(context, DARK_OAK_VARIANT_2, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.DARK_OAK_LOG),
                new StraightTrunkPlacer(7, 3, 1),
                BlockStateProvider.simple(Blocks.DARK_OAK_LEAVES),
                new FancyFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), 5),
                new ThreeLayersFeatureSize(2, 1, 2, 3, 2, OptionalInt.of(3))
        ).build());

        register(context, BIRCH_VARIANT_1, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.BIRCH_LOG),
                new ForkingTrunkPlacer(6, 2, 1), // mostly straight, some branching
                BlockStateProvider.simple(Blocks.BIRCH_LEAVES),
                new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), 3), // moderate leaf radius & offset
                new ThreeLayersFeatureSize(1, 0, 1, 2, 1, OptionalInt.empty())// three layers with subtle variation
        ).build());

        // Variant 1 - Large, spreading branches
        register(context, PEAR_KEY_VARIANT_1, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(7, 3, 2),
                BlockStateProvider.simple(ModBlocks.PEAR_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(2, 0, 2)).build());

// Variant 2 - Medium, standard
        register(context, PEAR_KEY_VARIANT_2, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(4, 2, 2),
                BlockStateProvider.simple(ModBlocks.PEAR_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), 3),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Variant 3 - Tall, slim
        register(context, PEAR_KEY_VARIANT_3, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new StraightTrunkPlacer(6, 1, 0),
                BlockStateProvider.simple(ModBlocks.PEAR_LEAVES.get()),
                new FancyFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), 5),
                new ThreeLayersFeatureSize(2, 1, 2, 3, 2, OptionalInt.of(3))
        ).build());

// Variant 4 - Short, wide canopy (with FancyFoliagePlacer)
        register(context, PEAR_KEY_VARIANT_4, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(3, 1, 1),
                BlockStateProvider.simple(ModBlocks.PEAR_LEAVES.get()),
                new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), 3),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// Variant 5 - Medium, dense leaves (with FancyFoliagePlacer)
        register(context, PEAR_KEY_VARIANT_5, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(5, 2, 1),
                BlockStateProvider.simple(ModBlocks.PEAR_LEAVES.get()),
                new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Variant 6 - Large, spreading branches
        register(context, PEAR_KEY_VARIANT_6, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(7, 3, 2),
                BlockStateProvider.simple(ModBlocks.PEAR_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(2, 0, 2)).build());

// Variant 7 - Medium, layered canopy
        register(context, PEAR_KEY_VARIANT_7, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(5, 2, 2),
                BlockStateProvider.simple(ModBlocks.PEAR_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(2), 4),
                new TwoLayersFeatureSize(2, 0, 2)).build());

        // Variant 1 - Large, spreading branches
        register(context, CHERRY_KEY_VARIANT_1, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(7, 3, 2),
                BlockStateProvider.simple(ModBlocks.CHERRY_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(2, 0, 2)).build());

// Variant 2 - Medium, standard
        register(context, CHERRY_KEY_VARIANT_2, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(4, 2, 2),
                BlockStateProvider.simple(ModBlocks.CHERRY_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), 3),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Variant 3 - Tall, slim
        register(context, CHERRY_KEY_VARIANT_3, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new StraightTrunkPlacer(6, 1, 0),
                BlockStateProvider.simple(ModBlocks.CHERRY_LEAVES.get()),
                new FancyFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), 5),
                new ThreeLayersFeatureSize(2, 1, 2, 3, 2, OptionalInt.of(3))
        ).build());

// Variant 4 - Short, wide canopy (with FancyFoliagePlacer)
        register(context, CHERRY_KEY_VARIANT_4, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(3, 1, 1),
                BlockStateProvider.simple(ModBlocks.CHERRY_LEAVES.get()),
                new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), 3),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// Variant 5 - Medium, dense leaves (with FancyFoliagePlacer)
        register(context, CHERRY_KEY_VARIANT_5, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(5, 2, 1),
                BlockStateProvider.simple(ModBlocks.CHERRY_LEAVES.get()),
                new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Variant 6 - Large, spreading branches
        register(context, CHERRY_KEY_VARIANT_6, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(7, 3, 2),
                BlockStateProvider.simple(ModBlocks.CHERRY_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(2, 0, 2)).build());

// Variant 7 - Medium, layered canopy
        register(context, CHERRY_KEY_VARIANT_7, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(5, 2, 2),
                BlockStateProvider.simple(ModBlocks.CHERRY_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(2), 4),
                new TwoLayersFeatureSize(2, 0, 2)).build());

        // Variant 1 - Large, spreading branches
        register(context, AVOCADO_KEY_VARIANT_1, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                new ForkingTrunkPlacer(7, 3, 2),
                BlockStateProvider.simple(ModBlocks.AVOCADO_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(2, 0, 2)).build());

// Variant 2 - Medium, standard
        register(context, AVOCADO_KEY_VARIANT_2, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                new ForkingTrunkPlacer(4, 2, 2),
                BlockStateProvider.simple(ModBlocks.AVOCADO_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), 3),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Variant 3 - Tall, slim
        register(context, AVOCADO_KEY_VARIANT_3, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                new StraightTrunkPlacer(6, 1, 0),
                BlockStateProvider.simple(ModBlocks.AVOCADO_LEAVES.get()),
                new FancyFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), 5),
                new ThreeLayersFeatureSize(2, 1, 2, 3, 2, OptionalInt.of(3))
        ).build());

// Variant 4 - Short, wide canopy (with FancyFoliagePlacer)
        register(context, AVOCADO_KEY_VARIANT_4, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                new ForkingTrunkPlacer(3, 1, 1),
                BlockStateProvider.simple(ModBlocks.AVOCADO_LEAVES.get()),
                new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), 3),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// Variant 5 - Medium, dense leaves (with FancyFoliagePlacer)
        register(context, AVOCADO_KEY_VARIANT_5, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                new ForkingTrunkPlacer(5, 2, 1),
                BlockStateProvider.simple(ModBlocks.AVOCADO_LEAVES.get()),
                new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Variant 6 - Large, spreading branches
        register(context, AVOCADO_KEY_VARIANT_6, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                new ForkingTrunkPlacer(7, 3, 2),
                BlockStateProvider.simple(ModBlocks.AVOCADO_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(2, 0, 2)).build());

// Variant 7 - Medium, layered canopy
        register(context, AVOCADO_KEY_VARIANT_7, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                new ForkingTrunkPlacer(5, 2, 2),
                BlockStateProvider.simple(ModBlocks.AVOCADO_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(2), 4),
                new TwoLayersFeatureSize(2, 0, 2)).build());

        register(context, BLACKCURRANT_BUSH_KEY, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.BLACKCURRANT_BUSH.get()
                                .defaultBlockState().setValue(SweetBerryBushBlock.AGE, 3))
                        ), List.of(Blocks.GRASS_BLOCK)));

        register(context, STRAWBERRY_BUSH_KEY, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.STRAWBERRY_BUSH.get()
                                .defaultBlockState().setValue(SweetBerryBushBlock.AGE, 3))
                        ), List.of(Blocks.GRASS_BLOCK)));

    }



    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}