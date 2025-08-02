package net.Aziuria.aziuriamod.handler;

import net.Aziuria.aziuriamod.block.ModBlocks;
import net.Aziuria.aziuriamod.data.ModDataComponents;
import net.Aziuria.aziuriamod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.minecraft.nbt.CompoundTag;

import java.util.HashSet;
import java.util.Set;

public class SackItemInventoryHandler {

    public static class SackHandler extends ItemStackHandler {
        private final ItemStack stack;
        private final HolderLookup.Provider registries;

        // Set of allowed items
        private static final Set<Item> ALLOWED_ITEMS = new HashSet<>();
        private static final Set<Block> ALLOWED_BLOCKS = new HashSet<>();

        static {
            // Seeds
            ALLOWED_ITEMS.add(Items.CARROT);
            ALLOWED_ITEMS.add(Items.POTATO);
            ALLOWED_ITEMS.add(Items.BEETROOT);
            ALLOWED_ITEMS.add(Items.WHEAT);
            ALLOWED_ITEMS.add(ModItems.RADISH.get());
            ALLOWED_ITEMS.add(ModItems.CORN.get());
            ALLOWED_ITEMS.add(ModItems.PINEAPPLE.get());
            ALLOWED_ITEMS.add(ModItems.CUCUMBER.get());
            ALLOWED_ITEMS.add(ModItems.TOMATO.get());
            ALLOWED_ITEMS.add(ModItems.LETTUCE.get());
            ALLOWED_ITEMS.add(ModItems.ONION.get());
            ALLOWED_ITEMS.add(ModItems.SPRING_ONION.get());
            ALLOWED_ITEMS.add(Items.MELON_SEEDS);
            ALLOWED_ITEMS.add(Items.PUMPKIN_SEEDS);
            ALLOWED_ITEMS.add(Items.BEETROOT_SEEDS);
            ALLOWED_ITEMS.add(Items.WHEAT_SEEDS);
            ALLOWED_ITEMS.add(ModItems.RADISH_SEEDS.get());
            ALLOWED_ITEMS.add(ModItems.PINEAPPLE_SEEDS.get());
            ALLOWED_ITEMS.add(ModItems.CORN_SEEDS.get());
            ALLOWED_ITEMS.add(ModItems.CUCUMBER_SEEDS.get());
            ALLOWED_ITEMS.add(ModItems.TOMATO_SEEDS.get());
            ALLOWED_ITEMS.add(ModItems.LETTUCE_SEEDS.get());
            ALLOWED_ITEMS.add(ModItems.ONION_SEEDS.get());
            ALLOWED_ITEMS.add(ModItems.SPRING_ONION_SEEDS.get());
            ALLOWED_ITEMS.add(ModItems.BLACKCURRANT_SEEDS.get());
            ALLOWED_ITEMS.add(ModItems.STRAWBERRY_SEEDS.get());

            // Saplings
            ALLOWED_ITEMS.add(Items.OAK_SAPLING);
            ALLOWED_ITEMS.add(Items.SPRUCE_SAPLING);
            ALLOWED_ITEMS.add(Items.BIRCH_SAPLING);
            ALLOWED_ITEMS.add(Items.JUNGLE_SAPLING);
            ALLOWED_ITEMS.add(Items.ACACIA_SAPLING);
            ALLOWED_ITEMS.add(Items.DARK_OAK_SAPLING);
            ALLOWED_BLOCKS.add(ModBlocks.APPLE_SAPLING.get());
            ALLOWED_BLOCKS.add(ModBlocks.PEAR_SAPLING.get());
            ALLOWED_BLOCKS.add(ModBlocks.CHERRY_SAPLING.get());
            ALLOWED_BLOCKS.add(ModBlocks.AVOCADO_SAPLING.get());

            // Bamboo
            ALLOWED_ITEMS.add(Items.BAMBOO);

            // Flowers
            ALLOWED_ITEMS.add(Items.POPPY);
            ALLOWED_ITEMS.add(Items.DANDELION);
            ALLOWED_ITEMS.add(Items.CORNFLOWER);
            ALLOWED_ITEMS.add(Items.LILY_OF_THE_VALLEY);
            ALLOWED_ITEMS.add(Items.ALLIUM);
            ALLOWED_ITEMS.add(Items.AZURE_BLUET);
            ALLOWED_ITEMS.add(Items.OXEYE_DAISY);
            ALLOWED_ITEMS.add(Items.PINK_TULIP);
            ALLOWED_ITEMS.add(Items.RED_TULIP);
            ALLOWED_ITEMS.add(Items.ORANGE_TULIP);
            ALLOWED_ITEMS.add(Items.WHITE_TULIP);
            ALLOWED_ITEMS.add(Items.BLACK_DYE); // Black flowers
            ALLOWED_ITEMS.add(ModItems.FLAX_FLOWER.get());

            // Apples
            ALLOWED_ITEMS.add(Items.APPLE);
            ALLOWED_ITEMS.add(ModItems.PEAR.get());
            ALLOWED_ITEMS.add(ModItems.CHERRY.get());
            ALLOWED_ITEMS.add(ModItems.AVOCADO.get());
            ALLOWED_ITEMS.add(ModItems.BLACKCURRANT.get());
            ALLOWED_ITEMS.add(ModItems.STRAWBERRY.get());
            ALLOWED_ITEMS.add(Items.SWEET_BERRIES);
            ALLOWED_ITEMS.add(Items.GLOW_BERRIES);

            // Misc
            ALLOWED_BLOCKS.add(ModBlocks.LEAF_LITTER.get());
        }

        public SackHandler(ItemStack stack, HolderLookup.Provider registries) {
            super(3); // size of the inventory
            this.stack = stack;
            this.registries = registries;

            // Deserialize inventory on initialization
            CompoundTag tag = stack.getOrDefault(ModDataComponents.SACK_INVENTORY.get(), new CompoundTag());
            this.deserializeNBT(registries, tag);
        }

        @Override
        protected void onContentsChanged(int slot) {
            // Save inventory whenever the contents change
            SackItemInventoryHandler.saveInventory(stack, this, registries);
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            // Check if the item is allowed
            return isAllowedItem(stack.getItem());
        }

        // Utility method to check if an item is allowed
        private boolean isAllowedItem(Item item) {
            return ALLOWED_ITEMS.contains(item);
        }
    }

    // This method is still necessary for saving inventory.
    public static void saveInventory(ItemStack stack, ItemStackHandler handler, HolderLookup.Provider provider) {
        CompoundTag tag = handler.serializeNBT(provider); // Pass provider here
        stack.set(ModDataComponents.SACK_INVENTORY.get(), tag);
    }
}