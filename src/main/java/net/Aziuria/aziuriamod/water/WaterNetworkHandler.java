package net.Aziuria.aziuriamod.water;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

public class WaterNetworkHandler {

    public static final ResourceLocation WATER_SYNC_ID =
            ResourceLocation.fromNamespaceAndPath("aziuriamod", "water_state_sync");

    public static void register(final RegisterPayloadHandlersEvent event) {
        var registrar = event.registrar("aziuriamod").optional();

        registrar.playBidirectional(
                WaterStateSyncPacket.TYPE,
                WaterStateSyncPacket.CODEC,
                WaterStateSyncPacket::handle
        );
    }

    public static void sendWaterStateToClient(ServerPlayer player, WaterStateSyncPacket packet) {
        PacketDistributor.sendToPlayer(player, packet);
    }

    public static void sendWaterStateToAll(WaterStateSyncPacket packet) {
        PacketDistributor.sendToAllPlayers(packet);
    }
}