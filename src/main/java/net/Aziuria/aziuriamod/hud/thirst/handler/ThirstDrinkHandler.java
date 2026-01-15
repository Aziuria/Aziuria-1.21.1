package net.Aziuria.aziuriamod.hud.thirst.handler;

import net.Aziuria.aziuriamod.hud.thirst.capability.IThirst;
import net.Aziuria.aziuriamod.hud.thirst.capability.ThirstProvider;
import net.Aziuria.aziuriamod.hud.thirst.registry.ThirstRegistry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThirstDrinkHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThirstDrinkHandler.class);
    private static final int MAX_THIRST = 20;

    @SubscribeEvent
    public static void onItemUseFinish(LivingEntityUseItemEvent.Finish event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return; // Only run on server-side players
        }

        ItemStack itemStack = event.getItem();
        if (itemStack == null || itemStack.isEmpty()) {
            return;
        }

        IThirst thirst = player.getCapability(ThirstProvider.THIRST_CAP, null);
        if (thirst == null) {
            return;
        }

        int currentThirst = thirst.getThirst();
        int restoreAmount = ThirstRegistry.getThirstRestore(itemStack.getItem());

        if (restoreAmount != 0) {
            int newThirst = currentThirst + restoreAmount;
            // Clamp thirst to [0, MAX_THIRST]
            newThirst = Math.max(0, Math.min(newThirst, MAX_THIRST));
            thirst.setThirst(newThirst);

        }
    }
}