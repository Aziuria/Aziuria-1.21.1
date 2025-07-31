//package net.Aziuria.aziuriamod.water;
//
//import net.minecraft.server.level.ServerPlayer;
//import net.neoforged.bus.api.SubscribeEvent;
//import net.neoforged.neoforge.event.entity.player.PlayerEvent;
//
//public class PlayerWaterEventHandler {
//
//    @SubscribeEvent
//    public static void onClone(PlayerEvent.Clone event) {
//        PlayerWaterCapability oldCap = event.getOriginal().getCapability(ModCapabilities.WATER_CAP);
//        PlayerWaterCapability newCap = event.getEntity().getCapability(ModCapabilities.WATER_CAP);
//
//        if (oldCap != null && newCap != null) {
//            int oldLevel = oldCap.getWaterLevel();
//            newCap.setWaterLevel(oldLevel);
//
//            if (event.getEntity() instanceof ServerPlayer player) {
//                newCap.setOnWaterLevelChanged(() ->
//                        WaterNetworkHandler.sendWaterLevel(player, newCap.getWaterLevel()));
//            }
//        }
//    }
//
//    @SubscribeEvent
//    public static void onPlayerSave(PlayerEvent.SaveToFile event) {
//        if (!(event.getEntity() instanceof ServerPlayer player)) return;
//        PlayerWaterCapability cap = player.getCapability(ModCapabilities.WATER_CAP);
//        if (cap != null) {
//            player.getPersistentData().putInt("AziuriaWaterLevel", cap.getWaterLevel());
//            System.out.println("[SaveToFile] Saved water level: " + cap.getWaterLevel());
//        }
//    }
//
//    @SubscribeEvent
//    public static void onPlayerLoad(PlayerEvent.LoadFromFile event) {
//        if (!(event.getEntity() instanceof ServerPlayer player)) return;
//        PlayerWaterCapability cap = player.getCapability(ModCapabilities.WATER_CAP);
//        if (cap != null && player.getPersistentData().contains("AziuriaWaterLevel")) {
//            int loadedLevel = player.getPersistentData().getInt("AziuriaWaterLevel");
//            cap.setWaterLevel(loadedLevel);
//            System.out.println("[LoadFromFile] Loaded water level: " + loadedLevel);
//        }
//    }
//}