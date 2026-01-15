package net.Aziuria.aziuriamod.hud.exhaustion.handler;

import net.Aziuria.aziuriamod.hud.exhaustion.capability.ExhaustionProvider;
import net.Aziuria.aziuriamod.hud.exhaustion.capability.Iexhaustion;
import net.Aziuria.aziuriamod.hud.exhaustion.registry.ExhaustionRegistry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExhaustHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExhaustHandler.class);
    private static final float MAX_EXHAUSTION = 20f; // optional cap

    @SubscribeEvent
    public static void onItemUseFinish(LivingEntityUseItemEvent.Finish event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return; // only server players

        ItemStack itemStack = event.getItem();
        if (itemStack == null || itemStack.isEmpty()) return;

        Iexhaustion exhaustion = player.getCapability(ExhaustionProvider.EXHAUSTION_CAP, null);
        if (exhaustion == null) return;

        float currentExhaustion = exhaustion.getExhaustion();
        int restoreAmount = ExhaustionRegistry.getExhaustionRestore(itemStack.getItem());

        if (restoreAmount != 0) {
            float newExhaustion = currentExhaustion + restoreAmount;
            newExhaustion = Math.max(0, Math.min(newExhaustion, MAX_EXHAUSTION)); // clamp
            exhaustion.setExhaustion(newExhaustion);
        }
    }
}