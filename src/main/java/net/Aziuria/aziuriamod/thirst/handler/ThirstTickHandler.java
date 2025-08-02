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
import net.minecraft.world.Difficulty;

import java.util.UUID;
import java.util.HashMap;
import java.util.Map;

public class ThirstTickHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThirstTickHandler.class);
    private static final Map<UUID, Integer> lastThirstMap = new HashMap<>();

    @SubscribeEvent
    public static void onPlayerTick(Post event) {
        Player player = event.getEntity();
        if (player.isCreative()) return;
        IThirst thirst = ThirstProvider.THIRST_CAP.getCapability(player, null);

        if (thirst == null || !player.isAlive()) return;

        Difficulty difficulty = player.level().getDifficulty();
        if (difficulty == Difficulty.PEACEFUL) {
            long gameTime = player.level().getGameTime();
            // Restore 1 thirst point every 2 ticks (half a second)
            if (gameTime % 2 == 0) {
                int thirstLevel = thirst.getThirst();
                if (thirstLevel < 20) {  // MAX_THIRST = 20
                    thirst.setThirst(thirstLevel + 1);
                    thirst.setExhaustion(0);  // Reset exhaustion in peaceful
                }
            }
            return;  // Skip exhaustion and thirst reduction below
        }

        long gameTime = player.level().getGameTime();
        double horizontalSpeed = player.getDeltaMovement().horizontalDistanceSqr();
        double verticalSpeed = player.getDeltaMovement().y();
        UUID playerId = player.getUUID();
        int currentThirst = thirst.getThirst();
        int oldThirst = lastThirstMap.getOrDefault(playerId, currentThirst);

        // Exhaustion amounts per action (tweak as needed)
        final float EX_WALK = 0.0015f;
        final float EX_SPRINT = 0.009f;
        final float EX_JUMP = 0.03f;
        final float EX_MINE = 0.0007f;
        final float EX_HOT = 0.003f;

        // CASE 1: Sprinting
        if (player.isSprinting() && horizontalSpeed > 0.01) {
            thirst.addExhaustion(EX_SPRINT);
        }

        // CASE 2: Walking
        else if (!player.isSprinting() && horizontalSpeed > 0.0005) {
            thirst.addExhaustion(EX_WALK);
        }

        // CASE 3: Jumping
        if (verticalSpeed > 0.25) {
            thirst.addExhaustion(EX_JUMP);
        }

        // CASE 4: Mining
        if (player.swinging) {
            thirst.addExhaustion(EX_MINE);
        }

        // CASE 5: Hot biome
        BlockPos pos = player.blockPosition();
        Biome biome = player.level().getBiome(pos).value();
        float temperature = biome.getBaseTemperature();

        // CASE 5: Hot biome (apply every ~12.5 seconds)
        if (temperature >= 1.5f && gameTime % 250 == 0) {
            thirst.addExhaustion(EX_HOT);
        }

        // Apply thirst reduction if exhaustion exceeds threshold
        if (thirst.getExhaustion() >= 4.0f) {
            thirst.setExhaustion(thirst.getExhaustion() - 4.0f);
            thirst.setThirst(currentThirst - 1);
        }

        int updatedThirst = thirst.getThirst();
        if (updatedThirst != oldThirst) {
            lastThirstMap.put(playerId, updatedThirst);
        }
    }
}