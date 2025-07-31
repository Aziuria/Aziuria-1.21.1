package net.Aziuria.aziuriamod.thirst.handler;

import net.Aziuria.aziuriamod.thirst.capability.IThirst;
import net.Aziuria.aziuriamod.thirst.capability.ThirstProvider;
import net.Aziuria.aziuriamod.thirst.network.ThirstNetworkHandler;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class PlayerJoinsHandler {

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        // Ensure thirst is synced visually
        ThirstNetworkHandler.syncThirstLevel(player);

        // Also forcibly clean up effects on join
        ThirstDebuffHandler.removeThirstDebuffs(player);
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        ThirstNetworkHandler.syncThirstLevel(player);
        ThirstDebuffHandler.removeThirstDebuffs(player);
    }

    @SubscribeEvent
    public static void onPlayerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        ThirstNetworkHandler.syncThirstLevel(player);
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        IThirst oldThirstCap = event.getOriginal().getCapability(ThirstProvider.THIRST_CAP, null);
        IThirst newThirstCap = event.getEntity().getCapability(ThirstProvider.THIRST_CAP, null);

        if (newThirstCap == null) return;

        if (oldThirstCap != null) {
            newThirstCap.setThirst(oldThirstCap.getThirst());
            newThirstCap.setExhaustion(oldThirstCap.getExhaustion());
        } else {
            newThirstCap.setThirst(20);
            newThirstCap.setExhaustion(0f);
        }
    }

    private static void clearThirstDebuffs(ServerPlayer player) {
        player.removeEffect(MobEffects.DIG_SLOWDOWN);
        player.removeEffect(MobEffects.WEAKNESS);
        player.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
    }
}