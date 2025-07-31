//package net.Aziuria.aziuriamod.water;
//
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.server.level.ServerPlayer;
//import net.minecraft.world.entity.player.Player;
//import net.neoforged.neoforge.network.PacketDistributor;
//import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//public class WaterNetworkHandler {
//
//    // Unique ResourceLocation ID for your water sync packet
//    public static final ResourceLocation WATER_SYNC_ID =
//            ResourceLocation.fromNamespaceAndPath("aziuriamod", "water_state_sync");
//
//    // Cache last sent water level per player to avoid redundant syncs
//    private static final Map<ServerPlayer, Integer> lastSentWaterLevel = new ConcurrentHashMap<>();
//
//    public static void register(final RegisterPayloadHandlersEvent event) {
//        System.out.println("[WaterNetworkHandler] Registering water packets...");
//        var registrar = event.registrar("aziuriamod").optional();
//
//        registrar.playBidirectional(
//                WaterStateSyncPacket.TYPE,
//                WaterStateSyncPacket.CODEC,
//                WaterStateSyncPacket::handle
//        );
//    }
//
//    /** Send water state packet to a specific player */
//    public static void sendWaterStateToClient(ServerPlayer player, WaterStateSyncPacket packet) {
//        System.out.println("[WaterNetworkHandler] Sending water state to player " + player.getName().getString()
//                + ": waterLevel=" + packet.getWaterLevel());
//        PacketDistributor.sendToPlayer(player, packet);
//    }
//
//    /** Send water state packet to all players */
//    public static void sendWaterStateToAll(WaterStateSyncPacket packet) {
//        System.out.println("[WaterNetworkHandler] Sending water state to all players: waterLevel=" + packet.getWaterLevel());
//        PacketDistributor.sendToAllPlayers(packet);
//    }
//
//    /** Convenience method to send just water level */
//    public static void sendWaterLevel(ServerPlayer player, int waterLevel) {
//        sendWaterStateToClient(player, new WaterStateSyncPacket(waterLevel));
//    }
//
//    /** Sync water level to a player, but only if changed */
//    public static void syncWaterLevel(Player player) {
//        if (!(player instanceof ServerPlayer serverPlayer)) {
//            System.out.println("[WaterNetworkHandler] syncWaterLevel() called on non-server player, ignored");
//            return;
//        }
//
//        PlayerWaterCapability cap = ModCapabilities.get(player);
//        if (cap == null) {
//            System.out.println("[WaterNetworkHandler] syncWaterLevel() failed: capability missing");
//            return;
//        }
//
//        int waterLevel = cap.getWaterLevel();
//        Integer lastLevel = lastSentWaterLevel.get(serverPlayer);
//
//        if (lastLevel == null || lastLevel != waterLevel) {
//            sendWaterLevel(serverPlayer, waterLevel);
//            lastSentWaterLevel.put(serverPlayer, waterLevel);
//            System.out.println("[WaterNetworkHandler] syncWaterLevel() called for " + player.getName().getString() + ", waterLevel=" + waterLevel);
//        } else {
//            // No change, no send
//            // Uncomment below if you want minimal debug for no-send
//            // System.out.println("[WaterNetworkHandler] syncWaterLevel() skipped for " + player.getName().getString() + ", no change");
//        }
//    }
//
//    /** Optional: Call this when player logs out or is removed to clean up cache */
//    public static void removePlayerCache(ServerPlayer player) {
//        lastSentWaterLevel.remove(player);
//    }
//}