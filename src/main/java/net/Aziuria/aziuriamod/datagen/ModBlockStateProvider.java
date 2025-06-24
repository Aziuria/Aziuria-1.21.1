package net.Aziuria.aziuriamod.datagen;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.Aziuria.aziuriamod.block.*;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.CropBlock;
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
        blockWithItem(ModBlocks.POTASSIUM_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_POTASSIUM_ORE);

        blockWithItem(ModBlocks.STEEL_BLOCK);
        blockWithItem(ModBlocks.UNBREAKABLE_GLASS);

        makeCrop(((CropBlock) ModBlocks.RADISH_CROP.get()), "radish_crop_stage", "radish_crop_stage");
        makeCrop(((CropBlock) ModBlocks.CUCUMBER_CROP.get()), "cucumber_crop_stage", "cucumber_crop_stage");
        makeCrop(((CropBlock) ModBlocks.TOMATO_CROP.get()), "tomato_crop_stage", "tomato_crop_stage");
        makeCrop(((CropBlock) ModBlocks.LETTUCE_CROP.get()), "lettuce_crop_stage", "lettuce_crop_stage");
        makeCrop(((CropBlock) ModBlocks.ONION_CROP.get()), "onion_crop_stage", "onion_crop_stage");
        makeCrop(((CropBlock) ModBlocks.SPRING_ONION_CROP.get()), "spring_onion_crop_stage", "spring_onion_crop_stage");

        getVariantBuilder(ModBlocks.FLAX_FLOWER_BLOCK.get())
                .partialState()
                .setModels(new ConfiguredModel(
                        models().cross("flax_flower", modLoc("block/flax_flower")).renderType("cutout")
                ));

        // Optional: register its block item model if you want it to use the block model
        blockItem(ModBlocks.FLAX_FLOWER_BLOCK);

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
