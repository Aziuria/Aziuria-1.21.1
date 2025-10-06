package net.Aziuria.aziuriamod.util;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {

        public static final TagKey<Block> NEEDS_STEEL_TOOL = createTag("needs_steel_tool");
        public static final TagKey<Block> INCORRECT_FOR_STEEL_TOOL = createTag("incorrect_for_steel_tool");

        public static final TagKey<Block> NEEDS_SPINEL_TOOL = createTag("needs_spinel_tool");
        public static final TagKey<Block> INCORRECT_FOR_SPINEL_TOOL = createTag("incorrect_for_spinel_tool");

        public static final TagKey<Block> NEEDS_COPPER_TOOL = createTag("needs_spinel_tool");
        public static final TagKey<Block> INCORRECT_FOR_COPPER_TOOL = createTag("incorrect_for_spinel_tool");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, name));
        }

    }

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, name));
        }


    }
