package net.Aziuria.aziuriamod.water;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

public class WaterTickHandler {

    private static final int TICKS_PER_CHECK = 20;
    private static int tickCounter = 0;

    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event) {
        if (!(event.getLevel() instanceof ServerLevel level)) return;

        tickCounter++;
        if (tickCounter < TICKS_PER_CHECK) return;
        tickCounter = 0;

        for (ServerPlayer player : level.getServer().getPlayerList().getPlayers()) {
            processPlayerWaterDecay(player);
        }
    }

    private static void processPlayerWaterDecay(ServerPlayer player) {
        PlayerWaterCapability cap = player.getCapability(ModCapabilities.WATER_CAP);
        if (cap == null) return;

        int decayAmount = 0;

        if (player.onGround()) {
            if (player.isSprinting()) {
                decayAmount += 2;
            } else if (!player.getDeltaMovement().equals(Vec3.ZERO)) {
                decayAmount += 1;
            }
        } else {
            decayAmount += 2;
        }

        int foodLevel = player.getFoodData().getFoodLevel();
        float saturation = player.getFoodData().getSaturationLevel();

        if (foodLevel > 0 && saturation < 2.0f) {
            decayAmount += 1;
        }

        cap.drainWater(decayAmount);

        WaterStateSyncPacket packet = new WaterStateSyncPacket(cap.getWaterLevel());
        WaterNetworkHandler.sendWaterStateToClient(player, packet);
    }
}