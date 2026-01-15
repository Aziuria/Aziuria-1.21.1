package net.Aziuria.aziuriamod.hud.thirst.network;

import net.Aziuria.aziuriamod.hud.thirst.capability.ThirstProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ThirstNetworkHandler {

    public static final ResourceLocation THIRST_SYNC_ID =
            ResourceLocation.fromNamespaceAndPath("aziuriamod", "thirst_sync");

    private static final Map<ServerPlayer, Integer> lastSentThirst = new ConcurrentHashMap<>();

    public static void register(final RegisterPayloadHandlersEvent event) {
        var registrar = event.registrar("aziuriamod").optional();

        registrar.playBidirectional(
                ThirstSyncPacket.TYPE,
                ThirstSyncPacket.CODEC,
                ThirstSyncPacket::handle
        );
    }

    public static void sendThirstToClient(ServerPlayer player, ThirstSyncPacket packet) {
        PacketDistributor.sendToPlayer(player, packet);
    }

    public static void sendThirstToAll(ThirstSyncPacket packet) {
        PacketDistributor.sendToAllPlayers(packet);
    }

    public static void sendThirstLevel(ServerPlayer player, int thirst) {
        sendThirstToClient(player, new ThirstSyncPacket(thirst));
    }

    public static void syncThirstLevel(Player player) {
        if (!(player instanceof ServerPlayer serverPlayer)) {
            return;
        }

        var thirstCap = player.getCapability(ThirstProvider.THIRST_CAP, null);
        if (thirstCap == null) {
            return;
        }

        int thirstLevel = thirstCap.getThirst();
        Integer lastLevel = lastSentThirst.get(serverPlayer);

        if (lastLevel == null || lastLevel != thirstLevel) {
            sendThirstLevel(serverPlayer, thirstLevel);
            lastSentThirst.put(serverPlayer, thirstLevel);
        }
    }

    public static void removePlayerCache(ServerPlayer player) {
        lastSentThirst.remove(player);
    }
}