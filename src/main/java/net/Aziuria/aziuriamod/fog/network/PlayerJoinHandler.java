package net.Aziuria.aziuriamod.fog.network;

import net.Aziuria.aziuriamod.fog.FogEventManager;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;

public class PlayerJoinHandler {

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        String activeFogId = "";
        int intensityOrdinal = 0;
        long fogStart = 0L;
        long fogEnd = 0L;
        long fogFadeInEnd = 0L;
        long fogFadeOutStart = 0L;

        if (FogEventManager.getActiveFog() != null) {
            activeFogId = FogEventManager.getActiveFog().getId();
            if (FogEventManager.getIntensity() != null) {
                intensityOrdinal = FogEventManager.getIntensity().ordinal();
            }
            fogStart = FogEventManager.getFogStart();
            fogEnd = FogEventManager.getFogEnd();
            fogFadeInEnd = FogEventManager.getFogFadeInEnd();
            fogFadeOutStart = FogEventManager.getFogFadeOutStart();
        }

        FogStateSyncPacket packet = new FogStateSyncPacket(
                activeFogId,
                intensityOrdinal,
                fogStart,
                fogEnd,
                fogFadeInEnd,
                fogFadeOutStart
        );

        PacketDistributor.sendToPlayer(player, packet);
    }
}