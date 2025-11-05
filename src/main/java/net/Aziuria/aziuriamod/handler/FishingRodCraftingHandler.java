package net.Aziuria.aziuriamod.handler;

import net.Aziuria.aziuriamod.data.ModDataComponents;
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

        // Only your baited rods
        if (!crafted.is(ModItems.WORM_FISHING_ROD.get()) &&
                !crafted.is(ModItems.BREAD_FISHING_ROD.get()) &&
                !crafted.is(ModItems.CORN_FISHING_ROD.get())) return;

        // Find vanilla rod in the input grid
        ItemStack vanillaRod = ItemStack.EMPTY;
        Container inv = event.getInventory();
        for (int i = 0; i < inv.getContainerSize(); i++) {
            ItemStack slot = inv.getItem(i);
            if (slot.is(Items.FISHING_ROD)) {
                vanillaRod = slot.copy();
                break;
            }
        }

        if (!vanillaRod.isEmpty()) {
            // Carry over the vanilla rod’s damage
            crafted.setDamageValue(vanillaRod.getDamageValue());
        }

        // Initialize or reset persistent components
        crafted.set(ModDataComponents.ROD_CAUGHT_COUNT.get(), 0);
        crafted.set(ModDataComponents.ROD_MAX_BEFORE_REVERT.get(), new java.util.Random().nextInt(4) + 1);

        // ---- Handle shift-click multi-craft ----
        // NeoForge creates multiple identical outputs during shift-click,
        // each must have the same components + damage applied manually.
        for (ItemStack stack : event.getEntity().getInventory().items) {
            if (stack.is(crafted.getItem()) && !stack.has(ModDataComponents.ROD_CAUGHT_COUNT.get())) {
                stack.set(ModDataComponents.ROD_CAUGHT_COUNT.get(), 0);
                stack.set(ModDataComponents.ROD_MAX_BEFORE_REVERT.get(), new java.util.Random().nextInt(4) + 1);
                if (!vanillaRod.isEmpty()) {
                    stack.setDamageValue(vanillaRod.getDamageValue());
                }
            }
        }
    }
}