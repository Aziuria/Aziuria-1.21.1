package net.Aziuria.aziuriamod.tips.handler;

import net.Aziuria.aziuriamod.tips.network.ShowPebbleTipsPacket;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;

public class PlayerJoinHandlers {

    private static final String PEBBLE_TIPS_SHOWN = "aziuria_pebble_tips_shown";

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        var player = event.getEntity();
        var data = player.getPersistentData();

        if (!data.getBoolean(PEBBLE_TIPS_SHOWN)) {
            data.putBoolean(PEBBLE_TIPS_SHOWN, true);

            if (player instanceof ServerPlayer serverPlayer) {
                PacketDistributor.sendToPlayer(serverPlayer, new ShowPebbleTipsPacket());
            }
        }
    }
}