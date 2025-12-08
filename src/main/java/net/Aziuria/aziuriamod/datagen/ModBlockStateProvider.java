package net.Aziuria.aziuriamod.datagen;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.Aziuria.aziuriamod.block.*;
import net.Aziuria.aziuriamod.block.custom.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, AziuriaMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.SULPHUR_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_SULPHUR_ORE);
        blockWithItem(ModBlocks.TIN_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_TIN_ORE);
        blockWithItem(ModBlocks.POTASSIUM_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_POTASSIUM_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_SPINEL_ORE);
        blockWithItem(ModBlocks.SPINEL_ORE);
        blockWithItem(ModBlocks.SPECTRAL_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_SPECTRAL_ORE);

        blockWithItem(ModBlocks.STEEL_BLOCK);
        blockWithItem(ModBlocks.UNBREAKABLE_GLASS);

        makeCrop(((CropBlock) ModBlocks.RADISH_CROP.get()), "radish_crop_stage", "radish_crop_stage");
        makeCrop(((CropBlock) ModBlocks.CUCUMBER_CROP.get()), "cucumber_crop_stage", "cucumber_crop_stage");
        makeCrop(((CropBlock) ModBlocks.TOMATO_CROP.get()), "tomato_crop_stage", "tomato_crop_stage");
        makeCrop(((CropBlock) ModBlocks.LETTUCE_CROP.get()), "lettuce_crop_stage", "lettuce_crop_stage");
        makeCrop(((CropBlock) ModBlocks.ONION_CROP.get()), "onion_crop_stage", "onion_crop_stage");
        makeCrop(((CropBlock) ModBlocks.SPRING_ONION_CROP.get()), "spring_onion_crop_stage", "spring_onion_crop_stage");
        makeCrop(((CropBlock) ModBlocks.CORN_CROP.get()), "corn_crop_stage", "corn_crop_stage");
        makeCrop(((CropBlock) ModBlocks.COFFEE_CROP.get()), "coffee_crop_stage", "coffee_crop_stage");
        makeCrop(((CropBlock) ModBlocks.TEA_CROP.get()), "tea_crop_stage", "tea_crop_stage");
        makeCrop(((CropBlock) ModBlocks.PINEAPPLE_CROP.get()), "pineapple_crop_stage", "pineapple_crop_stage");

        makeBush(((SweetBerryBushBlock) ModBlocks.BLACKCURRANT_BUSH.get()), "blackcurrant_bush_stage", "blackcurrant_bush_stage");
        makeBush(((SweetBerryBushBlock) ModBlocks.BLUEBERRY_BUSH.get()), "blueberry_bush_stage", "blueberry_bush_stage");
        makeBush(((SweetBerryBushBlock) ModBlocks.GOOSEBERRY_BUSH.get()), "gooseberry_bush_stage", "gooseberry_bush_stage");
        makeBush(((SweetBerryBushBlock) ModBlocks.STRAWBERRY_BUSH.get()), "strawberry_bush_stage", "strawberry_bush_stage");

        getVariantBuilder(ModBlocks.FLAX_FLOWER_BLOCK.get())
                .partialState()
                .setModels(new ConfiguredModel(
                        models().cross("flax_flower", modLoc("block/flax_flower")).renderType("cutout")
                ));
        // Yucca plant
        getVariantBuilder(ModBlocks.YUCCA_PLANT_BLOCK.get())
                .partialState()
                .setModels(new ConfiguredModel(
                        models().cross("yucca_plant", modLoc("block/yucca_plant")).renderType("cutout")
                ));


        // Optional: register its block item model if you want it to use the block model
        blockItem(ModBlocks.FLAX_FLOWER_BLOCK);
        blockItem(ModBlocks.YUCCA_PLANT_BLOCK);

        leavesBlock(ModBlocks.APPLE_LEAVES);
        saplingBlock(ModBlocks.APPLE_SAPLING);
        leavesBlock(ModBlocks.PEAR_LEAVES);
        saplingBlock(ModBlocks.PEAR_SAPLING);
        leavesBlock(ModBlocks.CHERRY_LEAVES);
        saplingBlock(ModBlocks.CHERRY_SAPLING);
        leavesBlock(ModBlocks.AVOCADO_LEAVES);
        saplingBlock(ModBlocks.AVOCADO_SAPLING);
        leavesBlock(ModBlocks.ORANGE_LEAVES);
        saplingBlock(ModBlocks.ORANGE_SAPLING);
        leavesBlock(ModBlocks.BANANA_LEAVES);
        saplingBlock(ModBlocks.BANANA_SAPLING);

    }

    private void saplingBlock(DeferredBlock<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void leavesBlock(DeferredBlock<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));


    }

    public void makeBush(SweetBerryBushBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().cross(modelName + state.getValue(BlackcurrantBushBlock.AGE),
                ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, "block/" + textureName + state.getValue(BlackcurrantBushBlock.AGE))).renderType("cutout"));

        return models;
    }

    public void makeCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);


    }
    private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName) {
        int age;
        if (block instanceof RadishCropBlock) {
            age = state.getValue(RadishCropBlock.AGE);
        } else if (block instanceof CucumberCropBlock) {
            age = state.getValue(CucumberCropBlock.AGE);
        } else if (block instanceof TomatoCropBlock) {
            age = state.getValue(TomatoCropBlock.AGE);
        } else if (block instanceof LettuceCropBlock) {
            age = state.getValue(LettuceCropBlock.AGE);
        } else if (block instanceof OnionCropBlock) {
            age = state.getValue(OnionCropBlock.AGE);
        } else if (block instanceof SpringOnionCropBlock) {
            age = state.getValue(SpringOnionCropBlock.AGE);
        } else if (block instanceof CornCropBlock) {
            age = state.getValue(CornCropBlock.AGE);
        } else if (block instanceof CoffeeCropBlock) {
            age = state.getValue(CoffeeCropBlock.AGE);
        } else if (block instanceof TeaCropBlock) {
            age = state.getValue(TeaCropBlock.AGE);
        } else if (block instanceof PineappleCropBlock) {
            age = state.getValue(PineappleCropBlock.AGE);
        } else {
            age = 0; // fallback or throw exception for safety
        }

        return new ConfiguredModel[] {
                new ConfiguredModel(models().crop(
                        modelName + age,
                        ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, "block/" + textureName + age)
                ).renderType("cutout"))
        };

    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("aziuriamod:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("aziuriamod:block/" + deferredBlock.getId().getPath() + appendix));
    }

}
