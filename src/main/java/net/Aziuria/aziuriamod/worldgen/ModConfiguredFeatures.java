package net.Aziuria.aziuriamod.worldgen;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.Aziuria.aziuriamod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.SimpleBlockFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import  net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;

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

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}