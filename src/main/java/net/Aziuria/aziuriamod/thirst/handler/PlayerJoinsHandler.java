package net.Aziuria.aziuriamod.thirst.handler;

import net.Aziuria.aziuriamod.thirst.capability.IThirst;
import net.Aziuria.aziuriamod.thirst.capability.ThirstProvider;
import net.Aziuria.aziuriamod.thirst.network.ThirstNetworkHandler;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class PlayerJoinsHandler {

    private static final String THIRST_NBT_KEY = "AziuriaThirstLevel";
    private static final String EXHAUSTION_NBT_KEY = "AziuriaThirstExhaustion";


    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        ThirstNetworkHandler.syncThirstLevel(player);
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        ThirstNetworkHandler.syncThirstLevel(player);
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

        if (oldThirstCap != null && newThirstCap != null) {
            newThirstCap.setThirst(oldThirstCap.getThirst());
            newThirstCap.setExhaustion(oldThirstCap.getExhaustion());
        }
    }
}