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
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import  net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SULPHUR_ORE_KEY = registerKey("sulphur_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_POTASSIUM_ORE_KEY = registerKey("potassium_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SULPHUR_ORE_RARE_MID_KEY = registerKey("sulphur_ore_rare_mid");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_POTASSIUM_ORE_RARE_MID_KEY = registerKey("potassium_ore_rare_mid");

    // New rare surface ore keys
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SULPHUR_ORE_SURFACE_RARE_KEY = registerKey("sulphur_ore_surface_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_POTASSIUM_ORE_SURFACE_RARE_KEY = registerKey("potassium_ore_surface_rare");

    public static final ResourceKey<ConfiguredFeature<?, ?>> LEAF_LITTER_KEY = registerKey("leaf_litter");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);


        List<OreConfiguration.TargetBlockState> overworldSulphurOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.SULPHUR_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_SULPHUR_ORE.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> overworldPotassiumOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.POTASSIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_POTASSIUM_ORE.get().defaultBlockState()));

        register(context, OVERWORLD_SULPHUR_ORE_KEY, Feature.ORE, new OreConfiguration(overworldSulphurOres, 9));
        register(context, OVERWORLD_POTASSIUM_ORE_KEY, Feature.ORE, new OreConfiguration(overworldPotassiumOres, 9));

        register(context, OVERWORLD_SULPHUR_ORE_RARE_MID_KEY, Feature.ORE, new OreConfiguration(overworldSulphurOres, 5));
        register(context, OVERWORLD_POTASSIUM_ORE_RARE_MID_KEY, Feature.ORE, new OreConfiguration(overworldPotassiumOres, 5));

        // Rare surface ores - even smaller vein size (e.g. 4)
        register(context, OVERWORLD_SULPHUR_ORE_SURFACE_RARE_KEY, Feature.ORE, new OreConfiguration(overworldSulphurOres, 2));
        register(context, OVERWORLD_POTASSIUM_ORE_SURFACE_RARE_KEY, Feature.ORE, new OreConfiguration(overworldPotassiumOres, 2));

        register(context, LEAF_LITTER_KEY, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(
                        Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.LEAF_LITTER.get().defaultBlockState())),
                        List.of(Blocks.GRASS_BLOCK)
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