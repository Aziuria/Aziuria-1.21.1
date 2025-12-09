package net.Aziuria.aziuriamod.fog.events;

import net.Aziuria.aziuriamod.fog.FogEventManager;
import net.minecraft.server.level.ServerLevel;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.CanPlayerSleepEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

public class ModServerEvents {

    @SubscribeEvent
    public static void onPlayerSleep(CanPlayerSleepEvent event) {

        // Always use getters — never fields
        var player = event.getEntity(); // ServerPlayer
        var level = event.getLevel();   // Level

        // Only run server logic
        if (level instanceof ServerLevel serverLevel) {

            if (FogEventManager.isEvilFogActive()) {
                FogEventManager.stopFogNow();
            }
        }
    }

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Post event) {

        ServerLevel level = event.getServer().overworld();
        if (level.isClientSide()) return;

        if (FogEventManager.isEvilFogActive()) {
            long tickInDay = level.getGameTime() % 24000L;

            if (tickInDay < 13000L) { // night ended
                FogEventManager.stopFogNow();
            }
        }
    }
}