package net.Aziuria.aziuriamod.handler.items;

import net.Aziuria.aziuriamod.data.ModDataComponents;
import net.Aziuria.aziuriamod.item.ModItems;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.ItemFishedEvent;

import java.util.Random;

public class BaitedFishingHandler {

    private static final Random RANDOM = new Random();

    // --- Handle fishing events ---
    @SubscribeEvent
    public static void onFishCaught(ItemFishedEvent event) {
        Player player = event.getEntity();
        ItemStack rod = player.getMainHandItem();

        // Only handle custom baited rods
        if (!rod.is(ModItems.WORM_FISHING_ROD.get()) &&
                !rod.is(ModItems.BREAD_FISHING_ROD.get()) &&
                !rod.is(ModItems.CORN_FISHING_ROD.get())) return;

        // Initialize components if not present
        if (!rod.has(ModDataComponents.ROD_CAUGHT_COUNT.get())) {
            rod.set(ModDataComponents.ROD_CAUGHT_COUNT.get(), 0);
        }
        if (!rod.has(ModDataComponents.ROD_MAX_BEFORE_REVERT.get())) {
            rod.set(ModDataComponents.ROD_MAX_BEFORE_REVERT.get(), RANDOM.nextInt(4) + 1); // 1–4
        }

        // Increment caught count
        int caughtCount = rod.getOrDefault(ModDataComponents.ROD_CAUGHT_COUNT.get(), 0) + 1;
        rod.set(ModDataComponents.ROD_CAUGHT_COUNT.get(), caughtCount);

        int maxBeforeRevert = rod.getOrDefault(ModDataComponents.ROD_MAX_BEFORE_REVERT.get(), 1);

        // Chance-based revert if caughtCount >= maxBeforeRevert
        if (caughtCount >= maxBeforeRevert) {
            int totalDamage = rod.getDamageValue() + event.getRodDamage();

            ItemStack vanillaRod = new ItemStack(Items.FISHING_ROD);
            vanillaRod.setDamageValue(Math.min(totalDamage, vanillaRod.getMaxDamage() - 1));

            // Replace rod in hand
            player.setItemInHand(player.getUsedItemHand(), vanillaRod);
        }
    }

}