package net.Aziuria.aziuriamod.worldgen;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.Aziuria.aziuriamod.block.ModBlocks;
import net.Aziuria.aziuriamod.worldgen.rules.DynamicForkingTrunkPlacer;
import net.Aziuria.aziuriamod.worldgen.rules.RandomFacingStateProvider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
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

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_TIN_ORE_ULTRA_DEEP_KEY = registerKey("tin_ore_ultra_deep");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_TIN_ORE_DEEP_KEY = registerKey("tin_ore_deep");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_TIN_ORE_MID_KEY = registerKey("tin_ore_mid");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_TIN_ORE_UPPER_KEY = registerKey("tin_ore_upper");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_TIN_ORE_MOUNTAINS_KEY = registerKey("tin_ore_mountains");

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SPINEL_ORE_ULTRA_DEEP_KEY = registerKey("spinel_ore_ultra_deep");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SPINEL_ORE_DEEP_KEY = registerKey("spinel_ore_deep");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SPINEL_ORE_MID_KEY = registerKey("spinel_ore_mid");

    public static final ResourceKey<ConfiguredFeature<?, ?>> LEAF_LITTER_KEY = registerKey("leaf_litter");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STICK_A = registerKey("stick_a");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STICK_B = registerKey("stick_b");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STICK_C = registerKey("stick_c");
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

    public static final ResourceKey<ConfiguredFeature<?, ?>> BANANA_KEY_VARIANT_1 = registerKey("banana_variant_1");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BANANA_KEY_VARIANT_2 = registerKey("banana_variant_2");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BANANA_KEY_VARIANT_3 = registerKey("banana_variant_3");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BANANA_KEY_VARIANT_4 = registerKey("banana_variant_4");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BANANA_KEY_VARIANT_5 = registerKey("banana_variant_5");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BANANA_KEY_VARIANT_6 = registerKey("banana_variant_6");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BANANA_KEY_VARIANT_7 = registerKey("banana_variant_7");

    public static final ResourceKey<ConfiguredFeature<?, ?>> OAK_KEY_VARIANT_1 = registerKey("oak_variant_1");

    public static final ResourceKey<ConfiguredFeature<?, ?>> DARK_OAK_VARIANT_1 = registerKey("dark_oak_variant_1");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DARK_OAK_VARIANT_2 = registerKey("dark_oak_variant_2");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIRCH_VARIANT_1 = registerKey("birch_variant_1");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACKCURRANT_BUSH_KEY = registerKey("blackcurrant_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUEBERRY_BUSH_KEY = registerKey("blueberry_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STRAWBERRY_BUSH_KEY = registerKey("strawberry_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GOOSEBERRY_BUSH_KEY = registerKey("gooseberry_bush");



    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);


        List<OreConfiguration.TargetBlockState> overworldSulphurOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.SULPHUR_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_SULPHUR_ORE.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> overworldTinOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.TIN_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_TIN_ORE.get().defaultBlockState()));

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

        register(context, OVERWORLD_TIN_ORE_ULTRA_DEEP_KEY, Feature.ORE, new OreConfiguration(overworldTinOres, 4));
        register(context, OVERWORLD_TIN_ORE_DEEP_KEY, Feature.ORE, new OreConfiguration(overworldTinOres, 5));
        register(context, OVERWORLD_TIN_ORE_MID_KEY, Feature.ORE, new OreConfiguration(overworldTinOres, 6));
        register(context, OVERWORLD_TIN_ORE_UPPER_KEY, Feature.ORE, new OreConfiguration(overworldTinOres, 5));
        register(context, OVERWORLD_TIN_ORE_MOUNTAINS_KEY, Feature.ORE, new OreConfiguration(overworldTinOres, 4));

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
                        new SimpleBlockConfiguration(
                                new RandomFacingStateProvider(ModBlocks.LEAF_LITTER.get().defaultBlockState())
                        ),
                        List.of(Blocks.GRASS_BLOCK)
                )
        );

        register(context, STICK_A, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(
                        Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(
                                new RandomFacingStateProvider(ModBlocks.STICK_A.get().defaultBlockState())
                        ),
                        List.of(Blocks.GRASS_BLOCK),
                        8
                )
        );

        register(context, STICK_B, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(
                        Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(
                                new RandomFacingStateProvider(ModBlocks.STICK_B.get().defaultBlockState())
                        ),
                        List.of(Blocks.GRASS_BLOCK),
                        8
                )
        );

        register(context, STICK_C, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(
                        Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(
                                new RandomFacingStateProvider(ModBlocks.STICK_C.get().defaultBlockState())
                        ),
                        List.of(Blocks.GRASS_BLOCK),
                        8
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

// ===== Dynamic Realistic Tree Variant =====
        RandomSource random = RandomSource.create();

// 1. Dynamic trunk height
        int trunkHeight;
        int trunkType = random.nextInt(3); // 0=short,1=medium,2=tall

        switch (trunkType) {
            case 0 -> trunkHeight = 4 + random.nextInt(2);       // 4-5 blocks (short)
            case 1 -> trunkHeight = 5 + random.nextInt(2);       // 5-6 blocks (medium)
            default -> trunkHeight = 7 + random.nextInt(3);      // 7-9 blocks (tall)
        }

// 2. Probabilistic trunk forks
        int forkChance = random.nextInt(100);
        int baseBranchSpread = 1;
        if (trunkHeight >= 7) baseBranchSpread = 1;        // tall & slim
        else if (forkChance < 10) baseBranchSpread = 4;    // rare wide fork
        else if (forkChance < 30) baseBranchSpread = 3;    // uncommon fork
        else baseBranchSpread = 2;                          // standard

        int extraHeight = 1 + random.nextInt(2);
        if (trunkType == 2) extraHeight += 1;              // tall trees slightly taller

// 3. Foliage radius (bumped for tall trees)
        int foliageRadius;
        switch (trunkType) {
            case 0 -> foliageRadius = 2;                    // short, bushy
            case 1 -> foliageRadius = 2 + random.nextInt(2); // medium: 2-3
            default -> foliageRadius = 3;                   // tall & slim: 3 blocks
        }
        foliageRadius = Math.min(foliageRadius, 3);        // cap still safe

// 4. Foliage offset (probabilistic ±1)
        IntProvider foliageOffsetProvider = UniformInt.of(0, 1); // 0–1 to be safe
        if (trunkType == 0) foliageOffsetProvider = ConstantInt.of(0); // bushy short tree

// 5. Foliage type
        boolean useFancyFoliage = (trunkType == 1 || trunkType == 2);
        FoliagePlacer foliagePlacer = useFancyFoliage
                ? new FancyFoliagePlacer(ConstantInt.of(foliageRadius), foliageOffsetProvider, 3)
                : new BlobFoliagePlacer(ConstantInt.of(foliageRadius), foliageOffsetProvider, 3);

// 6. Feature size
        TwoLayersFeatureSize featureSize = new TwoLayersFeatureSize(
                trunkType == 0 ? 1 : 2, // short = 1 layer, else 2 layers
                0,
                2
        );

        // 7. Optional minor branch count
        int minorBranches = trunkHeight >= 7 ? 1 : 2 + random.nextInt(2);

// 7. Register tree
        // 8. Register tree
        register(context, APPLE_KEY_VARIANT_1, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.APPLE_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 2 - Medium, standard

        register(context, APPLE_KEY_VARIANT_2, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.APPLE_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 3 - Apple - Tall, slim with 1 fork
        register(context, APPLE_KEY_VARIANT_3, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.APPLE_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 4 - Short, wide canopy
        register(context, APPLE_KEY_VARIANT_4, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.APPLE_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 5 - Medium, dense leaves
        register(context, APPLE_KEY_VARIANT_5, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.APPLE_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 6 - Large, spreading branches, more snug leaves
        register(context, APPLE_KEY_VARIANT_6, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.APPLE_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 7 - Medium, layered canopy, avoid leaf decay
        register(context, APPLE_KEY_VARIANT_7, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.APPLE_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );


        // Variant 1 - Small, bushy dark oak
        register(context, DARK_OAK_VARIANT_1, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.DARK_OAK_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(Blocks.DARK_OAK_LEAVES),
                        foliagePlacer,
                        featureSize
                ).build()
        );

        // Variant 2 - Tall, slim dark oak
        register(context, DARK_OAK_VARIANT_2, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.DARK_OAK_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(Blocks.DARK_OAK_LEAVES),
                        foliagePlacer,
                        featureSize
                ).build()
        );

        register(context, BIRCH_VARIANT_1, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.BIRCH_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(Blocks.BIRCH_LEAVES),
                        foliagePlacer,
                        featureSize
                ).build()
        );

        register(context, OAK_KEY_VARIANT_1, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new DynamicForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread, minorBranches),
                        BlockStateProvider.simple(Blocks.OAK_LEAVES),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 1 - Large, spreading branches, slightly taller than apple
        register(context, PEAR_KEY_VARIANT_1, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.PEAR_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 2 - Medium, standard, slightly narrower
        register(context, PEAR_KEY_VARIANT_2, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.PEAR_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 3 - Pear - Tall, slender with top fork
        register(context, PEAR_KEY_VARIANT_3, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.PEAR_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 4 - Short, wide canopy, leaves more compact
        register(context, PEAR_KEY_VARIANT_4, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.PEAR_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 5 - Medium, dense leaves, slightly taller
        register(context, PEAR_KEY_VARIANT_5, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.PEAR_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 6 - Large, spreading branches, taller canopy
        register(context, PEAR_KEY_VARIANT_6, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.PEAR_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 7 - Medium, layered canopy, tighter leaves to prevent decay
        register(context, PEAR_KEY_VARIANT_7, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.PEAR_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 1 - Large, spreading branches, slightly taller than pear
        register(context, CHERRY_KEY_VARIANT_1, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.CHERRY_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 2 - Medium, standard, slightly narrower
        register(context, CHERRY_KEY_VARIANT_2, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.CHERRY_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 3 - Tall, elegant cherry with layered side forks
        register(context, CHERRY_KEY_VARIANT_3, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.CHERRY_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 4 - Short, wide canopy, leaves more compact
        register(context, CHERRY_KEY_VARIANT_4, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.CHERRY_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 5 - Medium, dense leaves, slightly taller
        register(context, CHERRY_KEY_VARIANT_5, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.CHERRY_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 6 - Large, spreading branches, taller canopy
        register(context, CHERRY_KEY_VARIANT_6, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.CHERRY_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 7 - Medium, layered canopy, tighter leaves to prevent decay
        register(context, CHERRY_KEY_VARIANT_7, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.CHERRY_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 1 - Large, spreading branches, tall canopy
        register(context, AVOCADO_KEY_VARIANT_1, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.AVOCADO_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 2 - Medium, narrower canopy
        register(context, AVOCADO_KEY_VARIANT_2, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.AVOCADO_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 3 - Tall, slim, realistic avocado
        register(context, AVOCADO_KEY_VARIANT_3, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.AVOCADO_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 4 - Short, wide, dense canopy
        register(context, AVOCADO_KEY_VARIANT_4, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.AVOCADO_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 5 - Medium, compact and dense
        register(context, AVOCADO_KEY_VARIANT_5, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.AVOCADO_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 6 - Large, spreading with airy canopy
        register(context, AVOCADO_KEY_VARIANT_6, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.AVOCADO_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 7 - Medium, layered, dense leaves
        register(context, AVOCADO_KEY_VARIANT_7, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.AVOCADO_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 1 - Large, spreading branches, wide canopy, consistent leaf coverage
        register(context, ORANGE_KEY_VARIANT_1, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.ORANGE_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                )
                        .decorators(List.of(
                                new LeaveVineDecorator(0.3f) // same vine logic as your old orange trees
                        ))
                        .build()
        );

// Variant 2 - Medium, standard, tighter canopy
        register(context, ORANGE_KEY_VARIANT_2, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.ORANGE_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 3 - Tall, slim, subtle side branches
        register(context, ORANGE_KEY_VARIANT_3, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.ORANGE_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 4 - Short, wide canopy
        register(context, ORANGE_KEY_VARIANT_4, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.ORANGE_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 5 - Medium, dense leaves
        register(context, ORANGE_KEY_VARIANT_5, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.ORANGE_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 6 - Large, spreading branches, taller canopy, with vanilla vines
        register(context, ORANGE_KEY_VARIANT_6, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.ORANGE_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                )
                        .decorators(List.of(
                                new LeaveVineDecorator(0.3f) // same vine logic as your old orange trees
                        ))
                        .build()
        );

// Variant 7 - Layered, rounded canopy with safe leaf range and subtle vines
        register(context, ORANGE_KEY_VARIANT_7, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.ORANGE_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                )
                        .decorators(List.of(
                                new LeaveVineDecorator(0.15f) // same vine logic as your old orange trees
                        ))
                        .build()
        );

        // Variant 1 - Tall, slightly spreading canopy, minimal side branches
        register(context, BANANA_KEY_VARIANT_1, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.BANANA_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                )
                        .decorators(List.of(
                                new LeaveVineDecorator(0.3f) // same vine logic as your old orange trees
                        ))
                        .build()
        );

// Variant 2 - Medium height, compact canopy
        register(context, BANANA_KEY_VARIANT_2, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.BANANA_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 3 - Slim, tall, minimal side leaves
        register(context, BANANA_KEY_VARIANT_3, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.BANANA_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 4 - Shorter, bushier top
        register(context, BANANA_KEY_VARIANT_4, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.BANANA_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );


// Variant 5 - Medium, slightly denser canopy
        register(context, BANANA_KEY_VARIANT_5, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.BANANA_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                ).build()
        );

// Variant 6 - Tall, slightly spreading, with light vines
        register(context, BANANA_KEY_VARIANT_6, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.BANANA_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                )
                        .decorators(List.of(
                                new LeaveVineDecorator(0.15f) // same vine logic as your old orange trees
                        ))
                        .build()
        );

// Variant 7 - Layered canopy, tall, slim
        register(context, BANANA_KEY_VARIANT_7, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                        new ForkingTrunkPlacer(trunkHeight, extraHeight, baseBranchSpread),
                        BlockStateProvider.simple(ModBlocks.BANANA_LEAVES.get()),
                        foliagePlacer,
                        featureSize
                )
                        .decorators(List.of(
                                new LeaveVineDecorator(0.1f) // same vine logic as your old orange trees
                        ))
                        .build()
        );

        register(context, BLACKCURRANT_BUSH_KEY, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.BLACKCURRANT_BUSH.get()
                                .defaultBlockState().setValue(SweetBerryBushBlock.AGE, 3))
                        ), List.of(Blocks.GRASS_BLOCK)));

        register(context, BLUEBERRY_BUSH_KEY, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.BLUEBERRY_BUSH.get()
                                .defaultBlockState().setValue(SweetBerryBushBlock.AGE, 3))
                        ), List.of(Blocks.GRASS_BLOCK)));

        register(context, GOOSEBERRY_BUSH_KEY, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.GOOSEBERRY_BUSH.get()
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