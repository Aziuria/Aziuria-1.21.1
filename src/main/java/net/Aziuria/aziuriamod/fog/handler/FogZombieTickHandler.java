package net.Aziuria.aziuriamod.fog.handler;

import net.Aziuria.aziuriamod.fog.FogEventManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.Entity;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FogZombieTickHandler {

    private static final int DESPAWN_TIME_TICKS = 200; // 10 seconds (20 ticks = 1 second)
    private static boolean fogJustEnded = false;

    // Tracks despawn timers by zombie UUID
    private static final Map<UUID, Integer> despawnTimers = new HashMap<>();

    @SubscribeEvent
    public void onLevelTick(LevelTickEvent.Post event) {
        if (!(event.getLevel() instanceof ServerLevel level)) return;

        if (!FogEventManager.isEvilFogActive()) {
            // Fog just ended: start despawn timers for all fog zombies
            if (!fogJustEnded) {
                fogJustEnded = true;

                List<Zombie> allZombies = level.getEntitiesOfClass(Zombie.class, level.getWorldBorder().getCollisionShape().bounds());
                for (Zombie zombie : allZombies) {
                    if (zombie.getPersistentData().getBoolean("SpawnedByFog")) {
                        despawnTimers.put(zombie.getUUID(), DESPAWN_TIME_TICKS);
                    }
                }
            }

            // Count down timers, remove zombies when time runs out
            despawnTimers.entrySet().removeIf(entry -> {
                UUID zombieId = entry.getKey();
                int timeLeft = entry.getValue() - 1;

                Entity entity = level.getEntity(zombieId);
                if (!(entity instanceof Zombie zombie)) {
                    // Zombie no longer exists, remove from map
                    return true;
                }

                if (timeLeft <= 0) {
                    zombie.discard();
                    return true;
                } else {
                    entry.setValue(timeLeft);
                    return false;
                }
            });

        } else {
            // Fog active again: reset state
            fogJustEnded = false;
            despawnTimers.clear();
        }
    }
}