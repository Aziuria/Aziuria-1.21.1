//package net.Aziuria.aziuriamod.water;
//
//import net.minecraft.server.level.ServerLevel;
//import net.minecraft.server.level.ServerPlayer;
//import net.minecraft.world.phys.Vec3;
//import net.neoforged.bus.api.SubscribeEvent;
//import net.neoforged.neoforge.event.tick.LevelTickEvent;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//public class WaterTickHandler {
//
//    private static final int TICKS_PER_CHECK = 20;
//    private static int tickCounter = 0;
//
//    private static final Map<UUID, Vec3> lastPositions = new HashMap<>();
//    private static final Map<UUID, Integer> lastSentWaterLevels = new HashMap<>();
//
//    @SubscribeEvent
//    public static void onLevelTick(LevelTickEvent.Post event) {
//        if (!(event.getLevel() instanceof ServerLevel level)) return;
//
//        tickCounter++;
//        if (tickCounter < TICKS_PER_CHECK) return;
//
//        tickCounter = 0;
//
//        for (ServerPlayer player : level.getServer().getPlayerList().getPlayers()) {
//            PlayerWaterCapability cap = player.getCapability(ModCapabilities.WATER_CAP);
//            if (cap == null) continue;
//
//            // Decay water based on activity
//            handleDecay(player, cap);
//        }
//    }
//
//    private static void handleDecay(ServerPlayer player, PlayerWaterCapability cap) {
//        UUID id = player.getUUID();
//        Vec3 current = player.position();
//        Vec3 last = lastPositions.getOrDefault(id, current);
//        boolean moved = current.distanceToSqr(last) > 0.01;
//        lastPositions.put(id, current);
//        boolean sprinting = player.isSprinting();
//        boolean onGround = player.onGround();
//
//        int decay = 0;
//
//        if (onGround) {
//            if (sprinting) decay += 2;
//            else if (moved) decay += 1;
//        } else {
//            decay += 2; // In air or jumping costs more water
//        }
//
//        // If player is hungry and low saturation, increase decay
//        if (player.getFoodData().getFoodLevel() > 0 && player.getFoodData().getSaturationLevel() < 2.0f) {
//            decay += 1;
//        }
//
//        if (decay > 0) {
//            cap.drainWater(decay);
//            System.out.println("[WaterTick] " + player.getName().getString() + " water decay: " + decay);
//
//            int currentLevel = cap.getWaterLevel();
//            Integer lastSent = lastSentWaterLevels.get(id);
//
//            if (lastSent == null || lastSent != currentLevel) {
//                WaterNetworkHandler.sendWaterLevel(player, currentLevel);
//                lastSentWaterLevels.put(id, currentLevel);
//            }
//        }
//    }
//}