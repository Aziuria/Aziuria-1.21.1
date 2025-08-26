package net.Aziuria.aziuriamod.handler;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.WeakHashMap;

public class MilkBucketHandler {

    // Stores last hint time per player
    private static final WeakHashMap<Player, Long> lastHintTime = new WeakHashMap<>();
    // Cooldown in milliseconds (6 minutes)
    private static final long COOLDOWN = 6 * 60 * 1000L;

    @SubscribeEvent
    public static void onUseMilkBucket(PlayerInteractEvent.RightClickItem event) {
        ItemStack stack = event.getItemStack();
        if (stack.getItem() == Items.MILK_BUCKET) {
            // Cancel the interaction
            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.FAIL);

            if (event.getEntity() instanceof Player player && !player.level().isClientSide) {
                long currentTime = System.currentTimeMillis();
                long lastTime = lastHintTime.getOrDefault(player, 0L);

                if (currentTime - lastTime >= COOLDOWN) {
                    player.displayClientMessage(
                            Component.literal("Hint: Use bottles instead of drinking milk directly"),
                            false // false = chat, not action bar
                    );
                    lastHintTime.put(player, currentTime);
                }
            }
        }
    }
}