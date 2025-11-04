package net.Aziuria.aziuriamod.handler;

import net.Aziuria.aziuriamod.item.ModItems;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent.ItemCraftedEvent;

public class FishingRodCraftingHandler {

    @SubscribeEvent
    public static void onCraft(ItemCraftedEvent event) {
        ItemStack crafted = event.getCrafting();

        // Only modify our custom baited fishing rods
        if (crafted.getItem() != ModItems.WORM_FISHING_ROD.get() &&
                crafted.getItem() != ModItems.BREAD_FISHING_ROD.get() &&
                crafted.getItem() != ModItems.CORN_FISHING_ROD.get()) {
            return;
        }

        // Find any vanilla fishing rod in the crafting grid
        Container craftMatrix = event.getInventory();
        ItemStack vanillaRod = ItemStack.EMPTY;
        for (int i = 0; i < craftMatrix.getContainerSize(); i++) {
            ItemStack stack = craftMatrix.getItem(i);
            if (stack.getItem() == Items.FISHING_ROD) {
                vanillaRod = stack;
                break;
            }
        }

        // If a vanilla rod was used, copy its damage to the new custom rod
        if (!vanillaRod.isEmpty()) {
            crafted.setDamageValue(vanillaRod.getDamageValue());
        }
    }
}