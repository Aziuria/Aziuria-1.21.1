package net.Aziuria.aziuriamod.handler;

import net.Aziuria.aziuriamod.fog.FogEventManager;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.level.LevelEvent;

public class FogEventWorldLoadHandler {

    @SubscribeEvent
    public static void onWorldLoad(LevelEvent.Load event) {
        Level level = (Level) event.getLevel();

        if (!level.isClientSide()) {
            FogEventManager.loadFromSavedData(level);
        }
    }

    @SubscribeEvent
    public static void onWorldSave(LevelEvent.Save event) {
        Level level = (Level) event.getLevel();

        if (!level.isClientSide()) {
            FogEventManager.saveToSavedData(level);
        }
    }
}