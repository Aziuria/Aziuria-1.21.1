package net.Aziuria.aziuriamod.worldgen;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.Aziuria.aziuriamod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
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
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
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

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORANGE_KEY_VARIANT_1 = registerKey("orange_variant_1");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORANGE_KEY_VARIANT_2 = registerKey("orange_variant_2");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORANGE_KEY_VARIANT_3 = registerKey("orange_variant_3");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORANGE_KEY_VARIANT_4 = registerKey("orange_variant_4");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORANGE_KEY_VARIANT_5 = registerKey("orange_variant_5");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORANGE_KEY_VARIANT_6 = registerKey("orange_variant_6");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORANGE_KEY_VARIANT_7 = registerKey("orange_variant_7");

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

// Variant 1 - Large, spreading branches, more realistic
        RandomSource random = RandomSource.create(); // your generator

        // Variant 1 - Large, spreading branches, more snug leaves
        int trunkHeight1 = 4 + random.nextInt(5); // 4-8 blocks
        int branchSpread1 = 2; // 2-3 blocks
        int foliageRadius1 = 2; // 3-4 blocks
        int foliageOffset1 = 0; // lowered by 1 so leaves wrap around trunk

        register(context, APPLE_KEY_VARIANT_1, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new ForkingTrunkPlacer(trunkHeight1, 3, branchSpread1),
                        BlockStateProvider.simple(ModBlocks.APPLE_LEAVES.get()),
                        new BlobFoliagePlacer(ConstantInt.of(foliageRadius1), ConstantInt.of(foliageOffset1), 3),
                        new TwoLayersFeatureSize(2, 0, 2)
                ).build()
        );

// Variant 2 - Medium, standard
        int trunkHeight2 = 4 + random.nextInt(2); // 4-5 blocks
        int branchSpread2 = 2 + random.nextInt(1); // 2-2 (slight variation)
        int foliageRadius2 = 2; // 2-3
        int foliageOffset2 = 1; // 1-2
        register(context, APPLE_KEY_VARIANT_2, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new ForkingTrunkPlacer(trunkHeight2, 2, branchSpread2),
                        BlockStateProvider.simple(ModBlocks.APPLE_LEAVES.get()),
                        new BlobFoliagePlacer(ConstantInt.of(foliageRadius2), ConstantInt.of(foliageOffset2), 3),
                        new TwoLayersFeatureSize(1, 0, 2)
                ).build()
        );

// Variant 3 - Apple - Tall, slim with 1 fork
        int trunkHeight3 = 7 + random.nextInt(1); // 6-7
        int branchSpread3 = 1; // mostly straight
        int foliageRadius3 = 2; // 3-4
        int foliageOffset3 = 0; // straight up

        register(context, APPLE_KEY_VARIANT_3, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new ForkingTrunkPlacer(trunkHeight3, 2, branchSpread3), // 2 = 1 extra fork
                        BlockStateProvider.simple(ModBlocks.APPLE_LEAVES.get()),
                        new FancyFoliagePlacer(ConstantInt.of(foliageRadius3), ConstantInt.of(foliageOffset3), 5),
                        new ThreeLayersFeatureSize(2, 1, 2, 3, 2, OptionalInt.of(3))
                ).build()
        );

// Variant 4 - Short, wide canopy
        int trunkHeight4 = 3 + random.nextInt(2); // 3-4
        int branchSpread4 = 1; // minimal branching
        int foliageRadius4 = 3; // 3-3
        int foliageOffset4 = 1 + random.nextInt(2); // 1-2
        register(context, APPLE_KEY_VARIANT_4, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new ForkingTrunkPlacer(trunkHeight4, 1, branchSpread4),
                        BlockStateProvider.simple(ModBlocks.APPLE_LEAVES.get()),
                        new FancyFoliagePlacer(ConstantInt.of(foliageRadius4), ConstantInt.of(foliageOffset4), 3),
                        new TwoLayersFeatureSize(1, 0, 1)
                ).build()
        );

// Variant 5 - Medium, dense leaves
        int trunkHeight5 = 5 + random.nextInt(2); // 5-6
        int branchSpread5 = 1 + random.nextInt(2); // 1-2
        int foliageRadius5 = 2 + random.nextInt(2); // 2-3
        int foliageOffset5 = 1 + random.nextInt(2); // 2-3
        register(context, APPLE_KEY_VARIANT_5, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new ForkingTrunkPlacer(trunkHeight5, 2, branchSpread5),
                        BlockStateProvider.simple(ModBlocks.APPLE_LEAVES.get()),
                        new FancyFoliagePlacer(ConstantInt.of(foliageRadius5), ConstantInt.of(foliageOffset5), 3),
                        new TwoLayersFeatureSize(1, 0, 2)
                ).build()
        );

// Variant 6 - Large, spreading branches, more snug leaves
        int trunkHeight6 = 6 + random.nextInt(2); // 7-8
        int branchSpread6 = 2; // 2-3
        int foliageRadius6 = 2; // 3-4
        int foliageOffset6 = 0; // lowered by 1 so leaves wrap around branches

        register(context, APPLE_KEY_VARIANT_6, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new ForkingTrunkPlacer(trunkHeight6, 3, branchSpread6),
                        BlockStateProvider.simple(ModBlocks.APPLE_LEAVES.get()),
                        new BlobFoliagePlacer(ConstantInt.of(foliageRadius6), ConstantInt.of(foliageOffset6), 3),
                        new TwoLayersFeatureSize(2, 0, 2)
                ).build()
        );

// Variant 7 - Medium, layered canopy, avoid leaf decay
        int trunkHeight7 = 5 + random.nextInt(1); // 5-6
        int branchSpread7 = 2; // keep fixed, no extra
        int foliageRadius7 = 2; // smaller so leaves stay within 6 blocks of logs
        int foliageOffset7 = 0; // reduced so leaves are snug

        register(context, APPLE_KEY_VARIANT_7, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new ForkingTrunkPlacer(trunkHeight7, 2, branchSpread7),
                        BlockStateProvider.simple(ModBlocks.APPLE_LEAVES.get()),
                        new BlobFoliagePlacer(ConstantInt.of(foliageRadius7), ConstantInt.of(foliageOffset7), 3),
                        new TwoLayersFeatureSize(2, 0, 2)
                ).build()
        );


        // Variant 1 - Small, bushy dark oak
        register(context, DARK_OAK_VARIANT_1, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.DARK_OAK_LOG),
                new ForkingTrunkPlacer(4 + random.nextInt(2), 2, 1), // 4-5 blocks
                BlockStateProvider.simple(Blocks.DARK_OAK_LEAVES),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        // Variant 2 - Tall, slim dark oak
        register(context, DARK_OAK_VARIANT_2, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.DARK_OAK_LOG),
                new StraightTrunkPlacer(7 + random.nextInt(2), 3, 1),
                BlockStateProvider.simple(Blocks.DARK_OAK_LEAVES),
                new FancyFoliagePlacer(ConstantInt.of(4), ConstantInt.of(1), 5),
                new ThreeLayersFeatureSize(2, 1, 2, 3, 2, OptionalInt.of(3))
        ).build());

        register(context, BIRCH_VARIANT_1, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.BIRCH_LOG),
                new ForkingTrunkPlacer(6, 2, 1), // mostly straight, some branching
                BlockStateProvider.simple(Blocks.BIRCH_LEAVES),
                new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), 3), // moderate leaf radius & offset
                new ThreeLayersFeatureSize(1, 0, 1, 2, 1, OptionalInt.empty())// three layers with subtle variation
        ).build());

// Variant 1 - Large, spreading branches, slightly taller than apple
        int trunkHeight8 = 6 + random.nextInt(3); // 6-8
        int branchSpread8 = 2; // slightly tighter than apple
        int foliageRadius8 = 2;
        int foliageOffset8 = 0; // taller canopy
        register(context, PEAR_KEY_VARIANT_1, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(trunkHeight8, 3, branchSpread8),
                BlockStateProvider.simple(ModBlocks.PEAR_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(foliageRadius8), ConstantInt.of(foliageOffset8), 3),
                new TwoLayersFeatureSize(2, 0, 2)).build());

// Variant 2 - Medium, standard, slightly narrower
        int trunkHeight9 = 5 + random.nextInt(2);
        int branchSpread9 = 1 + random.nextInt(2); // less spread than apple
        int foliageRadius9 = 2;
        int foliageOffset9 = 0;
        register(context, PEAR_KEY_VARIANT_2, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(trunkHeight9, 2, branchSpread9),
                BlockStateProvider.simple(ModBlocks.PEAR_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(foliageRadius9), ConstantInt.of(foliageOffset9), 3),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Variant 3 - Pear - Tall, slender with top fork
        int trunkHeight10 = 7 + random.nextInt(2); // 7-8
        int branchSpread10 = 1;
        int foliageRadius10 = 2; // 2-3
        int foliageOffset10 = 0;

        register(context, PEAR_KEY_VARIANT_3, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new ForkingTrunkPlacer(trunkHeight10, 2, branchSpread10), // 2 forks: one mid, one near top
                        BlockStateProvider.simple(ModBlocks.PEAR_LEAVES.get()),
                        new FancyFoliagePlacer(ConstantInt.of(foliageRadius10), ConstantInt.of(foliageOffset10), 5),
                        new ThreeLayersFeatureSize(2, 1, 2, 3, 2, OptionalInt.of(3))
                ).build()
        );

// Variant 4 - Short, wide canopy, leaves more compact
        int trunkHeight11 = 5 + random.nextInt(2);
        int branchSpread11 = 1;
        int foliageRadius11 = 2 + random.nextInt(1); // tighter than apple
        int foliageOffset11 = 1;
        register(context, PEAR_KEY_VARIANT_4, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(trunkHeight11, 1, branchSpread11),
                BlockStateProvider.simple(ModBlocks.PEAR_LEAVES.get()),
                new FancyFoliagePlacer(ConstantInt.of(foliageRadius11), ConstantInt.of(foliageOffset11), 3),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// Variant 5 - Medium, dense leaves, slightly taller
        int trunkHeight12 = 5 + random.nextInt(2);
        int branchSpread12 = 1;
        int foliageRadius12 = 2;
        int foliageOffset12 = 1;
        register(context, PEAR_KEY_VARIANT_5, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(trunkHeight12, 2, branchSpread12),
                BlockStateProvider.simple(ModBlocks.PEAR_LEAVES.get()),
                new FancyFoliagePlacer(ConstantInt.of(foliageRadius12), ConstantInt.of(foliageOffset12), 3),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Variant 6 - Large, spreading branches, taller canopy
        int trunkHeight13 = 7 + random.nextInt(2);
        int branchSpread13 = 2 + random.nextInt(2);
        int foliageRadius13 = 2;
        int foliageOffset13 = 1;
        register(context, PEAR_KEY_VARIANT_6, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(trunkHeight13, 3, branchSpread13),
                BlockStateProvider.simple(ModBlocks.PEAR_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(foliageRadius13), ConstantInt.of(foliageOffset13), 3),
                new TwoLayersFeatureSize(2, 0, 2)).build());

// Variant 7 - Medium, layered canopy, tighter leaves to prevent decay
        int trunkHeight14 = 5 + random.nextInt(2);
        int branchSpread14 = 2;
        int foliageRadius14 = 2;
        int foliageOffset14 = 1;
        register(context, PEAR_KEY_VARIANT_7, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(trunkHeight14, 2, branchSpread14),
                BlockStateProvider.simple(ModBlocks.PEAR_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(foliageRadius14), ConstantInt.of(foliageOffset14), 4),
                new TwoLayersFeatureSize(2, 0, 2)).build());

// Variant 1 - Large, spreading branches, slightly taller than pear
        int trunkHeight15 = 6 + random.nextInt(3); // 6-8 blocks
        int branchSpread15 = 2; // slightly tighter than apple
        int foliageRadius15 = 2;
        int foliageOffset15 = 0; // taller canopy
        register(context, CHERRY_KEY_VARIANT_1, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(trunkHeight15, 3, branchSpread15),
                BlockStateProvider.simple(ModBlocks.CHERRY_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(foliageRadius15), ConstantInt.of(foliageOffset15), 3),
                new TwoLayersFeatureSize(2, 0, 2)).build());

// Variant 2 - Medium, standard, slightly narrower
        int trunkHeight16 = 4 + random.nextInt(2); // 4-5 blocks
        int branchSpread16 = 1 + random.nextInt(2); // less spread than apple
        int foliageRadius16 = 2 + random.nextInt(1); // 2-2
        int foliageOffset16 = 0;
        register(context, CHERRY_KEY_VARIANT_2, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(trunkHeight16, 2, branchSpread16),
                BlockStateProvider.simple(ModBlocks.CHERRY_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(foliageRadius16), ConstantInt.of(foliageOffset16), 3),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Variant 3 - Tall, elegant cherry with layered side forks
        int trunkHeight17 = 6 + random.nextInt(3); // 6-8
        int branchSpread17 = 2;
        int forks17 = 4; // multiple mid & top anchors
        int foliageRadius17 = 2 + random.nextInt(2); // 2-3
        int foliageOffset17 = 1;

        register(context, CHERRY_KEY_VARIANT_3, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new ForkingTrunkPlacer(trunkHeight17, forks17, branchSpread17),
                        BlockStateProvider.simple(ModBlocks.CHERRY_LEAVES.get()),
                        new FancyFoliagePlacer(ConstantInt.of(foliageRadius17), ConstantInt.of(foliageOffset17), 3),
                        new ThreeLayersFeatureSize(2, 1, 2, 3, 1, OptionalInt.of(3))
                )
                        .decorators(List.of(new LeaveVineDecorator(0.05f))) // very subtle for “weeping” effect
                        .build()
        );

// Variant 4 - Short, wide canopy, leaves more compact
        int trunkHeight18 = 3 + random.nextInt(2); // 3-4
        int branchSpread18 = 1; // minimal branching
        int foliageRadius18 = 2 + random.nextInt(1); // tighter than apple
        int foliageOffset18 = 1;
        register(context, CHERRY_KEY_VARIANT_4, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(trunkHeight18, 1, branchSpread18),
                BlockStateProvider.simple(ModBlocks.CHERRY_LEAVES.get()),
                new FancyFoliagePlacer(ConstantInt.of(foliageRadius18), ConstantInt.of(foliageOffset18), 3),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// Variant 5 - Medium, dense leaves, slightly taller
        int trunkHeight19 = 5 + random.nextInt(2); // 5-6
        int branchSpread19 = 1; // narrow spread
        int foliageRadius19 = 2; // compact leaves
        int foliageOffset19 = 1;
        register(context, CHERRY_KEY_VARIANT_5, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(trunkHeight19, 2, branchSpread19),
                BlockStateProvider.simple(ModBlocks.CHERRY_LEAVES.get()),
                new FancyFoliagePlacer(ConstantInt.of(foliageRadius19), ConstantInt.of(foliageOffset19), 3),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Variant 6 - Large, spreading branches, taller canopy
        int trunkHeight20 = 7 + random.nextInt(2); // 7-8
        int branchSpread20 = 2; // 2-3
        int foliageRadius20 = 2; // fixed
        int foliageOffset20 = 0; // higher canopy
        register(context, CHERRY_KEY_VARIANT_6, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(trunkHeight20, 3, branchSpread20),
                BlockStateProvider.simple(ModBlocks.CHERRY_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(foliageRadius20), ConstantInt.of(foliageOffset20), 3),
                new TwoLayersFeatureSize(2, 0, 2)).build());

// Variant 7 - Medium, layered canopy, tighter leaves to prevent decay
        int trunkHeight21 = 6 + random.nextInt(2); // 5-6
        int branchSpread21 = 2; // fixed
        int foliageRadius21 = 2; // compact
        int foliageOffset21 = 1;
        register(context, CHERRY_KEY_VARIANT_7, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(trunkHeight21, 2, branchSpread21),
                BlockStateProvider.simple(ModBlocks.CHERRY_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(foliageRadius21), ConstantInt.of(foliageOffset21), 4),
                new TwoLayersFeatureSize(2, 0, 2)).build());

// Variant 1 - Large, spreading branches, tall canopy
        int trunkHeight22 = 6 + random.nextInt(3); // 6-8 blocks
        int branchSpread22 = 2; // 2-3 blocks
        int foliageRadius22 = 2; // 3-4 blocks
        int foliageOffset22 = 1; // higher canopy
        register(context, AVOCADO_KEY_VARIANT_1, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                new ForkingTrunkPlacer(trunkHeight22, 2, branchSpread22),
                BlockStateProvider.simple(ModBlocks.AVOCADO_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(foliageRadius22), ConstantInt.of(foliageOffset22), 3),
                new TwoLayersFeatureSize(2, 0, 2)).build());

// Variant 2 - Medium, narrower canopy
        int trunkHeight23 = 5 + random.nextInt(2); // 5-6
        int branchSpread23 = 1 + random.nextInt(1); // 1-2
        int foliageRadius23 = 2 + random.nextInt(1); // 2-2
        int foliageOffset23 = 1; // modest height
        register(context, AVOCADO_KEY_VARIANT_2, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                new ForkingTrunkPlacer(trunkHeight23, 2, branchSpread23),
                BlockStateProvider.simple(ModBlocks.AVOCADO_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(foliageRadius23), ConstantInt.of(foliageOffset23), 3),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Variant 3 - Tall, slim, realistic avocado
        int trunkHeight24 = 7 + random.nextInt(3); // 7-9 blocks
        int branchSpread24 = 1 + random.nextInt(2); // slim, 1-2 blocks
        int foliageRadius24 = 3; // moderate canopy
        int foliageOffset24 = 1; // slightly lower than top for branches
        register(context, AVOCADO_KEY_VARIANT_3, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                new ForkingTrunkPlacer(trunkHeight24, 1, branchSpread24), // allows 3-4 forks
                BlockStateProvider.simple(ModBlocks.AVOCADO_LEAVES.get()),
                new FancyFoliagePlacer(ConstantInt.of(foliageRadius24), ConstantInt.of(foliageOffset24), 5),
                new ThreeLayersFeatureSize(2, 1, 2, 3, 2, OptionalInt.of(3))
        ).build());

// Variant 4 - Short, wide, dense canopy
        int trunkHeight25 = 4 + random.nextInt(2); // 3-4
        int branchSpread25 = 1; // minimal
        int foliageRadius25 = 2 + random.nextInt(1); // 2-2
        int foliageOffset25 = 1; // low canopy
        register(context, AVOCADO_KEY_VARIANT_4, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                new ForkingTrunkPlacer(trunkHeight25, 1, branchSpread25),
                BlockStateProvider.simple(ModBlocks.AVOCADO_LEAVES.get()),
                new FancyFoliagePlacer(ConstantInt.of(foliageRadius25), ConstantInt.of(foliageOffset25), 3),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// Variant 5 - Medium, compact and dense
        int trunkHeight26 = 5 + random.nextInt(2); // 5-6
        int branchSpread26 = 1; // narrow
        int foliageRadius26 = 2; // tight leaves
        int foliageOffset26 = 2; // slightly higher canopy
        register(context, AVOCADO_KEY_VARIANT_5, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                new ForkingTrunkPlacer(trunkHeight26, 2, branchSpread26),
                BlockStateProvider.simple(ModBlocks.AVOCADO_LEAVES.get()),
                new FancyFoliagePlacer(ConstantInt.of(foliageRadius26), ConstantInt.of(foliageOffset26), 3),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Variant 6 - Large, spreading with airy canopy
        int trunkHeight27 = 7 + random.nextInt(2); // 7-8
        int branchSpread27 = 2 + random.nextInt(2); // 2-3
        int foliageRadius27 = 2; // fixed
        int foliageOffset27 = 1; // higher canopy
        register(context, AVOCADO_KEY_VARIANT_6, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                new ForkingTrunkPlacer(trunkHeight27, 3, branchSpread27),
                BlockStateProvider.simple(ModBlocks.AVOCADO_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(foliageRadius27), ConstantInt.of(foliageOffset27), 3),
                new TwoLayersFeatureSize(2, 0, 2)).build());

// Variant 7 - Medium, layered, dense leaves
        int trunkHeight28 = 5 + random.nextInt(2); // 5-6
        int branchSpread28 = 2; // slightly wider
        int foliageRadius28 = 2; // compact
        int foliageOffset28 = 0; // snug canopy
        register(context, AVOCADO_KEY_VARIANT_7, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                new ForkingTrunkPlacer(trunkHeight28, 2, branchSpread28),
                BlockStateProvider.simple(ModBlocks.AVOCADO_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(foliageRadius28), ConstantInt.of(foliageOffset28), 2),
                new TwoLayersFeatureSize(2, 0, 2)).build());

// Variant 1 - Large, spreading branches, wide canopy, consistent leaf coverage
        int trunkHeight29 = 6 + random.nextInt(3);
        int branchSpread29 = 2 + random.nextInt(2);
        int forks29 = 3 + random.nextInt(1); // 3-4 forks
        int foliageRadius29 = 2 + random.nextInt(1); // 2-3
        int foliageOffset29 = 1;

        register(context, ORANGE_KEY_VARIANT_1, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                        new ForkingTrunkPlacer(trunkHeight29, forks29, branchSpread29),
                        BlockStateProvider.simple(ModBlocks.ORANGE_LEAVES.get()),
                        new BlobFoliagePlacer(ConstantInt.of(foliageRadius29), ConstantInt.of(foliageOffset29), 4), // taller blob
                        new TwoLayersFeatureSize(2, 0, 2)
                )
                        .decorators(List.of(new LeaveVineDecorator(0.3f)))
                        .build()
        );

// Variant 2 - Medium, standard, tighter canopy
        int trunkHeight30 = 4 + random.nextInt(2);
        int branchSpread30 = 1 + random.nextInt(2);
        int forks30 = 2 + random.nextInt(2); // 2-3 forks
        int foliageRadius30 = 2 + random.nextInt(2);
        int foliageOffset30 = 1 + random.nextInt(2);
        register(context, ORANGE_KEY_VARIANT_2, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                        new ForkingTrunkPlacer(trunkHeight30, forks30, branchSpread30),
                        BlockStateProvider.simple(ModBlocks.ORANGE_LEAVES.get()),
                        new BlobFoliagePlacer(ConstantInt.of(foliageRadius30), ConstantInt.of(foliageOffset30), 3),
                        new TwoLayersFeatureSize(1, 0, 2)
                ).build()
        );

// Variant 3 - Tall, slim, subtle side branches
        int trunkHeight31 = 6 + random.nextInt(2);
        int branchSpread31 = 1;
        int forks31 = 2; // small number for mostly straight trunk
        int foliageRadius31 = 3 + random.nextInt(2);
        int foliageOffset31 = 0;
        register(context, ORANGE_KEY_VARIANT_3, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                        new ForkingTrunkPlacer(trunkHeight31, forks31, branchSpread31),
                        BlockStateProvider.simple(ModBlocks.ORANGE_LEAVES.get()),
                        new FancyFoliagePlacer(ConstantInt.of(foliageRadius31), ConstantInt.of(foliageOffset31), 2),
                        new ThreeLayersFeatureSize(2, 1, 1, 2, 1, OptionalInt.of(3))
                ).build()
        );

// Variant 4 - Short, wide canopy
        int trunkHeight32 = 4 + random.nextInt(2);
        int branchSpread32 = 1;
        int forks32 = 2; // subtle forks
        int foliageRadius32 = 2 + random.nextInt(2);
        int foliageOffset32 = 1 + random.nextInt(2);
        register(context, ORANGE_KEY_VARIANT_4, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                        new ForkingTrunkPlacer(trunkHeight32, forks32, branchSpread32),
                        BlockStateProvider.simple(ModBlocks.ORANGE_LEAVES.get()),
                        new FancyFoliagePlacer(ConstantInt.of(foliageRadius32), ConstantInt.of(foliageOffset32), 3),
                        new TwoLayersFeatureSize(1, 0, 1)
                ).build()
        );

// Variant 5 - Medium, dense leaves
        int trunkHeight33 = 5 + random.nextInt(2);
        int branchSpread33 = 1 + random.nextInt(2);
        int forks33 = 2 + random.nextInt(2); // slightly more forks for density
        int foliageRadius33 = 2 + random.nextInt(2);
        int foliageOffset33 = 2 + random.nextInt(2);
        register(context, ORANGE_KEY_VARIANT_5, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                        new ForkingTrunkPlacer(trunkHeight33, forks33, branchSpread33),
                        BlockStateProvider.simple(ModBlocks.ORANGE_LEAVES.get()),
                        new FancyFoliagePlacer(ConstantInt.of(foliageRadius33), ConstantInt.of(foliageOffset33), 3),
                        new TwoLayersFeatureSize(1, 0, 2)
                ).build()
        );

// Variant 6 - Large, spreading branches, taller canopy, with vanilla vines
        int trunkHeight34 = 7 + random.nextInt(2); // 7-8
        int branchSpread34 = 2 + random.nextInt(2); // 2-3
        int forks34 = 2; // 3-4 forks
        int foliageRadius34 = 2; // 3-4
        int foliageOffset34 = 0;

        register(context, ORANGE_KEY_VARIANT_6, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                        new ForkingTrunkPlacer(trunkHeight34, forks34, branchSpread34),
                        BlockStateProvider.simple(ModBlocks.ORANGE_LEAVES.get()),
                        new BlobFoliagePlacer(ConstantInt.of(foliageRadius34), ConstantInt.of(foliageOffset34), 3),
                        new TwoLayersFeatureSize(2, 0, 2)
                )
                        .decorators(List.of(new LeaveVineDecorator(0.3f))) // 30% chance for vines
                        .build()
        );

// Variant 7 - Layered, rounded canopy with safe leaf range and subtle vines
        int trunkHeight35 = 5 + random.nextInt(3);  // 5–7 blocks
        int branchSpread35 = 2;                     // never more than 2
        int forks35 = 3 + random.nextInt(2);        // 3–4 forks
        int foliageRadius35 = 2;                    // 2 radius max
        int foliageOffset35 = 1;                    // start a bit higher

        register(context, ORANGE_KEY_VARIANT_7, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                        new ForkingTrunkPlacer(trunkHeight35, forks35, branchSpread35),
                        BlockStateProvider.simple(ModBlocks.ORANGE_LEAVES.get()),
                        // Radius 2 + branchSpread 2 = 4 max leaf distance (safe)
                        new FancyFoliagePlacer(ConstantInt.of(foliageRadius35), ConstantInt.of(foliageOffset35), 3),
                        new TwoLayersFeatureSize(2, 0, 2)
                )
                        .decorators(List.of(
                                new LeaveVineDecorator(0.15f) // optional light vines
                        ))
                        .build()
        );

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