package net.Aziuria.aziuriamod.handler;

import net.Aziuria.aziuriamod.item.ModItems;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.ItemFishedEvent;

public class BaitedFishingHandler {

    @SubscribeEvent
    public static void onFishCaught(ItemFishedEvent event) {
        Player player = event.getEntity();
        ItemStack usedRod = player.getMainHandItem();

        // Make sure it’s one of your baited rods
        if (usedRod.is(ModItems.WORM_FISHING_ROD.get()) ||
                usedRod.is(ModItems.BREAD_FISHING_ROD.get()) ||
                usedRod.is(ModItems.CORN_FISHING_ROD.get())) {

            // Store the current damage (including what the event applied)
            int damage = event.getRodDamage();

            // Create a vanilla fishing rod with the same damage
            ItemStack vanillaRod = new ItemStack(Items.FISHING_ROD);
            vanillaRod.setDamageValue(Math.min(damage, vanillaRod.getMaxDamage() - 1));

            // Replace the rod in-hand
            player.setItemInHand(player.getUsedItemHand(), vanillaRod);
        }
    }
}