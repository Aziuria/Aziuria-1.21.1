package net.Aziuria.aziuriamod.events;

import net.minecraft.server.level.ServerLevel;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.Aziuria.aziuriamod.handler.VegetationGrowthHandler;

public class ModEvents {

    private static int tickCounter = 0;

    @SubscribeEvent
    public static void onWorldTick(LevelTickEvent.Post event) {
        if (!(event.getLevel() instanceof ServerLevel level)) return;

        tickCounter++;

        if (tickCounter % 500 == 0) {
            VegetationGrowthHandler.spreadPlants(level);
        }
    }
}