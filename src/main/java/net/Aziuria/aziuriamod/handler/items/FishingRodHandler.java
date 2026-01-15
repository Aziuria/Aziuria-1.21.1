package net.Aziuria.aziuriamod.handler.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.WeakHashMap;

public class FishingRodHandler {

    private static final WeakHashMap<Player, Long> lastHintTime = new WeakHashMap<>();
    private static final long COOLDOWN = 5 * 60 * 1000L; // 5 minutes

    @SubscribeEvent
    public static void onUseVanillaFishingRod(PlayerInteractEvent.RightClickItem event) {
        ItemStack stack = event.getItemStack();

        if (stack.getItem() == Items.FISHING_ROD) {
            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.FAIL);

            Player player = event.getEntity();
            if (!player.level().isClientSide) {
                long now = System.currentTimeMillis();
                long lastTime = lastHintTime.getOrDefault(player, 0L);
                if (now - lastTime >= COOLDOWN) {
                    player.displayClientMessage(
                            Component.literal("You tried fishing without bait, and failed to catch any."),
                            false
                    );
                    lastHintTime.put(player, now);
                }
            }
        }
    }
}