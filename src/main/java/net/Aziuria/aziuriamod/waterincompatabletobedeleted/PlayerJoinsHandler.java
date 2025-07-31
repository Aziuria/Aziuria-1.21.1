//package net.Aziuria.aziuriamod.water;
//
//import net.minecraft.server.level.ServerPlayer;
//import net.neoforged.bus.api.SubscribeEvent;
//import net.neoforged.neoforge.event.entity.player.PlayerEvent;
//
//public class PlayerJoinsHandler {
//
//    @SubscribeEvent
//    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
//        if (!(event.getEntity() instanceof ServerPlayer player)) return;
//        syncWaterLevel(player);
//    }
//
//    @SubscribeEvent
//    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
//        if (!(event.getEntity() instanceof ServerPlayer player)) return;
//        syncWaterLevel(player);
//    }
//
//    @SubscribeEvent
//    public static void onPlayerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
//        if (!(event.getEntity() instanceof ServerPlayer player)) return;
//        syncWaterLevel(player);
//    }
//
//    private static void syncWaterLevel(ServerPlayer player) {
//        PlayerWaterCapability cap = player.getCapability(ModCapabilities.WATER_CAP);
//        if (cap != null) {
//            // Setup callback to send water level when it changes
//            cap.setOnWaterLevelChanged(() -> WaterNetworkHandler.sendWaterLevel(player, cap.getWaterLevel()));
//
//            // Sync current water level immediately on join/respawn/dimension change
//            WaterNetworkHandler.sendWaterLevel(player, cap.getWaterLevel());
//        } else {
//            System.out.println("[PlayerJoinHandler] ERROR: Water capability is null for " + player.getName().getString());
//        }
//    }
//}