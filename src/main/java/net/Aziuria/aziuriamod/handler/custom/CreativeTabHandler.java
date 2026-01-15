package net.Aziuria.aziuriamod.handler.custom;

import net.Aziuria.aziuriamod.block.ModBlocks;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

public class CreativeTabHandler {

    @SubscribeEvent
    public void onBuildCreativeTab(BuildCreativeModeTabContentsEvent event) {

        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {

            ItemStack vanillaOakSapling = new ItemStack(Blocks.OAK_SAPLING);
            ItemStack customOakSapling  = new ItemStack(ModBlocks.CUSTOM_OAK_SAPLING.get());

            // Remove vanilla oak sapling
            event.remove(
                    vanillaOakSapling,
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );

            // Insert custom sapling EXACTLY where oak sapling used to be
            event.insertBefore(
                    new ItemStack(Blocks.SPRUCE_SAPLING), // anchor point
                    customOakSapling,
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );
            // --- BIRCH ---
            ItemStack vanillaBirchSapling = new ItemStack(Blocks.BIRCH_SAPLING);
            ItemStack customBirchSapling  = new ItemStack(ModBlocks.CUSTOM_BIRCH_SAPLING.get());

            event.remove(
                    vanillaBirchSapling,
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );

            // Insert birch AFTER spruce → anchor before jungle
            event.insertBefore(
                    new ItemStack(Blocks.JUNGLE_SAPLING),
                    customBirchSapling,
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );
        }
    }
}