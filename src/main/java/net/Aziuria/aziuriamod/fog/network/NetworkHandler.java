package net.Aziuria.aziuriamod.fog.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

public class NetworkHandler {

    public static final ResourceLocation FOG_SYNC_ID =
            new Identifier("aziuriamod", "fog_state_sync").toResourceLocation();

    // Remove @SubscribeEvent here
    public static void register(final RegisterPayloadHandlersEvent event) {
        var registrar = event.registrar("aziuriamod").optional();

        registrar.playBidirectional(
                FogStateSyncPacket.TYPE,
                FogStateSyncPacket.CODEC,
                FogStateSyncPacket::handle
        );
    }

    public static void sendFogStateToClient(ServerPlayer player, FogStateSyncPacket packet) {
        PacketDistributor.sendToPlayer(player, packet);
    }

    public static void sendFogStateToAll(FogStateSyncPacket packet) {
        PacketDistributor.sendToAllPlayers(packet);
    }
}