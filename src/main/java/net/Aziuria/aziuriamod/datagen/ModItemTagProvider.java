package net.Aziuria.aziuriamod.datagen;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.Aziuria.aziuriamod.item.ModItems;
import net.Aziuria.aziuriamod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, AziuriaMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
       // tag(ModTags.Items.TRANSFORMABLE_ITEMS)
       //         .add(ModItems.BISMUTH.get())
       //         .add(ModItems.RAW_BISMUTH.get())
       //         .add(Items.COAL)
       //         .add(Items.STICK)
       //         .add(Items.COMPASS);

        // Add knives tag
        tag(ModTags.KNIVES)
                .add(ModItems.WOOD_KNIFE.get())
                .add(ModItems.STONE_KNIFE.get())
                .add(ModItems.COPPER_KNIFE.get())
                .add(ModItems.IRON_KNIFE.get())
                .add(ModItems.GOLD_KNIFE.get())
                .add(ModItems.STEEL_KNIFE.get())
                .add(ModItems.SPINEL_KNIFE.get())
                .add(ModItems.DIAMOND_KNIFE.get())
                .add(ModItems.NETHERITE_KNIFE.get());

        tag(ItemTags.SWORDS)
                .add(ModItems.STEEL_SWORD.get());
        tag(ItemTags.PICKAXES)
                .add(ModItems.STEEL_PICKAXE.get());
        tag(ItemTags.AXES)
                .add(ModItems.STEEL_AXE.get());
        tag(ItemTags.SHOVELS)
                .add(ModItems.STEEL_SHOVEL.get());
        tag(ItemTags.HOES)
                .add(ModItems.STEEL_HOE.get());

        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.STEEL_HELMET.get())
                .add(ModItems.STEEL_CHESTPLATE.get())
                .add(ModItems.STEEL_LEGGINGS.get())
                .add(ModItems.STEEL_BOOTS.get());

        // Spinel tools
        tag(ItemTags.SWORDS)
                .add(ModItems.SPINEL_SWORD.get());
        tag(ItemTags.PICKAXES)
                .add(ModItems.SPINEL_PICKAXE.get());
        tag(ItemTags.AXES)
                .add(ModItems.SPINEL_AXE.get());
        tag(ItemTags.SHOVELS)
                .add(ModItems.SPINEL_SHOVEL.get());
        tag(ItemTags.HOES)
                .add(ModItems.SPINEL_HOE.get());

        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.SPINEL_HELMET.get())
                .add(ModItems.SPINEL_CHESTPLATE.get())
                .add(ModItems.SPINEL_LEGGINGS.get())
                .add(ModItems.SPINEL_BOOTS.get());

        // Spinel tools
        tag(ItemTags.SWORDS)
                .add(ModItems.COPPER_SWORD.get());
        tag(ItemTags.PICKAXES)
                .add(ModItems.COPPER_PICKAXE.get());
        tag(ItemTags.AXES)
                .add(ModItems.COPPER_AXE.get());
        tag(ItemTags.SHOVELS)
                .add(ModItems.COPPER_SHOVEL.get());
        tag(ItemTags.HOES)
                .add(ModItems.COPPER_HOE.get());

        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.COPPER_HELMET.get())
                .add(ModItems.COPPER_CHESTPLATE.get())
                .add(ModItems.COPPER_LEGGINGS.get())
                .add(ModItems.COPPER_BOOTS.get());

    }


}
