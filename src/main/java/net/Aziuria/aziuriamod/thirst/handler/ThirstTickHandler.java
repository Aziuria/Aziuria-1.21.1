package net.Aziuria.aziuriamod.thirst.handler;

import net.neoforged.bus.api.SubscribeEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import net.Aziuria.aziuriamod.thirst.capability.IThirst;
import net.Aziuria.aziuriamod.thirst.capability.ThirstProvider;
import net.neoforged.neoforge.event.tick.PlayerTickEvent.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.HashMap;
import java.util.Map;

public class ThirstTickHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThirstTickHandler.class);
    private static final Map<UUID, Integer> lastThirstMap = new HashMap<>();

    @SubscribeEvent
    public static void onPlayerTick(Post event) {
        Player player = event.getEntity();
        IThirst thirst = ThirstProvider.THIRST_CAP.getCapability(player, null);

        if (thirst == null || !player.isAlive()) return;

        long gameTime = player.level().getGameTime();
        double horizontalSpeed = player.getDeltaMovement().horizontalDistanceSqr();
        double verticalSpeed = player.getDeltaMovement().y();
        UUID playerId = player.getUUID();
        int currentThirst = thirst.getThirst();
        int oldThirst = lastThirstMap.getOrDefault(playerId, currentThirst);

        // Exhaustion amounts per action (tweak as needed)
        final float EX_WALK = 0.004f;
        final float EX_SPRINT = 0.09f;
        final float EX_JUMP = 0.2f;
        final float EX_MINE = 0.015f;
        final float EX_HOT = 0.015f;

        // CASE 1: Sprinting
        if (player.isSprinting() && horizontalSpeed > 0.01) {
            thirst.addExhaustion(EX_SPRINT);
            LOGGER.debug("Sprinting exhaustion applied");
        }

        // CASE 2: Walking
        else if (!player.isSprinting() && horizontalSpeed > 0.0005) {
            thirst.addExhaustion(EX_WALK);
            LOGGER.debug("Walking exhaustion applied");
        }

        // CASE 3: Jumping
        if (verticalSpeed > 0.25) {
            thirst.addExhaustion(EX_JUMP);
            LOGGER.debug("Jumping exhaustion applied");
        }

        // CASE 4: Mining
        if (player.swinging) {
            thirst.addExhaustion(EX_MINE);
            LOGGER.debug("Mining exhaustion applied");
        }

        // CASE 5: Hot biome
        BlockPos pos = player.blockPosition();
        Biome biome = player.level().getBiome(pos).value();
        float temperature = biome.getBaseTemperature();

        if (temperature >= 1.5f && gameTime % 60 == 0) { // Apply once every 3 seconds
            thirst.addExhaustion(EX_HOT);
            LOGGER.debug("Hot biome exhaustion applied");
        }

        // Apply thirst reduction if exhaustion exceeds threshold
        if (thirst.getExhaustion() >= 4.0f) {
            thirst.setExhaustion(thirst.getExhaustion() - 4.0f);
            thirst.setThirst(currentThirst - 1);
            LOGGER.debug("Exhaustion threshold reached - thirst reduced: {} → {}", currentThirst, currentThirst - 1);
        }

        // Log only on thirst change
        int updatedThirst = thirst.getThirst();
        if (updatedThirst != oldThirst) {
            LOGGER.info("Player {} thirst changed: {} → {}", player.getName().getString(), oldThirst, updatedThirst);
            lastThirstMap.put(playerId, updatedThirst);
        }
    }
}