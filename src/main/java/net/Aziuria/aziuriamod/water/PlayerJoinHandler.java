package net.Aziuria.aziuriamod.water;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;

public class PlayerJoinHandler {

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        PlayerWaterCapability cap = player.getCapability(ModCapabilities.WATER_CAP);
        int waterLevel = (cap != null) ? cap.getWaterLevel() : 20;

        WaterStateSyncPacket packet = new WaterStateSyncPacket(waterLevel);
        PacketDistributor.sendToPlayer(player, packet);
    }
}