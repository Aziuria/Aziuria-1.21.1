package net.Aziuria.aziuriamod.datagen;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.Aziuria.aziuriamod.block.ModBlocks;
import net.Aziuria.aziuriamod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, AziuriaMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.POTASSIUM_ORE.get())
                .add(ModBlocks.SULPHUR_ORE.get())
                .add(ModBlocks.DEEPSLATE_SULPHUR_ORE.get())
                .add(ModBlocks.DEEPSLATE_POTASSIUM_ORE.get())
                .add(ModBlocks.IRON_BARREL_EMPTY.get())
                .add(ModBlocks.STEEL_BARREL_EMPTY.get())
                .add(ModBlocks.STEEL_BLOCK.get());

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.IRON_BARREL_EMPTY.get())
                .add(ModBlocks.DEMAECATION_POST.get())
                .add(ModBlocks.DEMAECATION_POST_B.get())
                .add(ModBlocks.DEMAECATION_POST_C.get())
                .add(ModBlocks.DEMAECATION_POST_D.get())
                .add(ModBlocks.DEMAECATION_POST_E.get())
                .add(ModBlocks.SPEAKER.get())
                .add(ModBlocks.STEEL_BARREL_EMPTY.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.SULPHUR_ORE.get())
                .add(ModBlocks.POTASSIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_SULPHUR_ORE.get())
                .add(ModBlocks.DEEPSLATE_POTASSIUM_ORE.get())
                .add(ModBlocks.STEEL_BARS.get())
                .add(ModBlocks.STEEL_BLOCK.get());

        tag(ModTags.Blocks.NEEDS_STEEL_TOOL)
                .addTag(BlockTags.NEEDS_IRON_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_STEEL_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .remove(ModTags.Blocks.NEEDS_STEEL_TOOL);


    }
}
