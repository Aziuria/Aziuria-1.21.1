package net.Aziuria.aziuriamod.exhaustion.network;

import net.Aziuria.aziuriamod.exhaustion.capability.ExhaustionProvider;
import net.Aziuria.aziuriamod.exhaustion.capability.Iexhaustion;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ExhaustionNetworkHandler {

    public static final ResourceLocation EXHAUST_SYNC_ID =
            ResourceLocation.fromNamespaceAndPath("aziuriamod", "exhaustion_sync");

    private static final Map<ServerPlayer, Float> lastSentExhaustion = new ConcurrentHashMap<>();

    public static void register(final RegisterPayloadHandlersEvent event) {
        var registrar = event.registrar("aziuriamod").optional();

        registrar.playBidirectional(
                ExhaustionSyncPacket.TYPE,
                ExhaustionSyncPacket.CODEC,
                ExhaustionSyncPacket::handle
        );
    }

    public static void sendExhaustionToClient(ServerPlayer player, ExhaustionSyncPacket packet) {
        PacketDistributor.sendToPlayer(player, packet);
    }

    public static void sendExhaustionToAll(ExhaustionSyncPacket packet) {
        PacketDistributor.sendToAllPlayers(packet);
    }

    public static void sendExhaustionLevel(ServerPlayer player, float exhaustion) {
        sendExhaustionToClient(player, new ExhaustionSyncPacket(exhaustion));
    }

    public static void syncExhaustionLevel(Player player) {
        if (!(player instanceof ServerPlayer serverPlayer)) return;

        Iexhaustion cap = player.getCapability(ExhaustionProvider.EXHAUSTION_CAP, null);
        if (cap == null) return;

        float exhaustion = cap.getExhaustion();
        Float last = lastSentExhaustion.get(serverPlayer);

        if (last == null || !last.equals(exhaustion)) {
            sendExhaustionLevel(serverPlayer, exhaustion);
            lastSentExhaustion.put(serverPlayer, exhaustion);
        }
    }

    public static void removePlayerCache(ServerPlayer player) {
        lastSentExhaustion.remove(player);
    }
}