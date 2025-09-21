package net.Aziuria.aziuriamod.exhaustion.handler;

import net.Aziuria.aziuriamod.exhaustion.capability.ExhaustionProvider;
import net.Aziuria.aziuriamod.exhaustion.capability.Iexhaustion;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.Difficulty;
import net.minecraft.core.BlockPos;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent.Post;

public class ExhaustionTickHandler {

    @SubscribeEvent
    public static void onPlayerTick(Post event) {
        Player player = event.getEntity();
        if (player.isCreative() || !player.isAlive()) return;

        Iexhaustion exhaustionCap = ExhaustionProvider.EXHAUSTION_CAP.getCapability(player, null);
        if (exhaustionCap == null) return;

        if (player.level().getDifficulty() == Difficulty.PEACEFUL) {
            exhaustionCap.setExhaustion(20f); // bar full = fully rested this means in any code encountered
            return;
        }

        long gameTime = player.level().getGameTime();
        double horizontalSpeed = player.getDeltaMovement().horizontalDistanceSqr();
        double verticalSpeed = Math.abs(player.getDeltaMovement().y());

        // === Trial exhaustion constants (5 extra zeroes to all so remove them after trial phases) ===
        final float DECAY_IDLE = -0.00000002f;  // slower idle decay
        final float EX_WALK    = -0.00000002f;
        final float EX_SPRINT  = -0.00000008f;
        final float EX_JUMP    = -0.0000001f;
        final float EX_MINE    = -0.00000003f;
        final float EX_HOT     = -0.000003f;
        final float EX_COLD    = -0.0000015f;
        final float EX_SWIM    = -0.00000005f;

        // === Calculate combined activity factor ===
        float activityExhaustion = 0f;

        if (player.isSprinting() && horizontalSpeed > 0.01) activityExhaustion += EX_SPRINT;
        else if (!player.isSprinting() && horizontalSpeed > 0.001) activityExhaustion += EX_WALK;

        if (verticalSpeed > 0.25 && !player.isFallFlying()) activityExhaustion += EX_JUMP;
        if (player.swinging) activityExhaustion += EX_MINE;

        // Limit maximum exhaustion per tick to avoid spiking
        activityExhaustion = Math.min(activityExhaustion, 0.0015f);

        // Apply combined activity exhaustion
        exhaustionCap.addExhaustion(activityExhaustion);

        // === Idle decay ===
        if (horizontalSpeed < 0.001 && verticalSpeed < 0.05) {
            exhaustionCap.addExhaustion(DECAY_IDLE);
        }

        // === Biome and swimming exhaustion (every ~12.5 seconds) ===
        BlockPos pos = player.blockPosition();
        Biome biome = player.level().getBiome(pos).value();
        float temperature = biome.getBaseTemperature();

        if (gameTime % 250 == 0) { // every 12.5 seconds
            // Hot biome
            if (temperature >= 1.5f) {
                float hotMultiplier = temperature - 1.5f; // scales with temperature
                exhaustionCap.addExhaustion(EX_HOT * hotMultiplier);
            }
            // Cold biome
            else if (temperature <= 0.2f) {
                float coldMultiplier = 0.2f - temperature; // scales with cold
                exhaustionCap.addExhaustion(EX_COLD * coldMultiplier);

                // Extra penalty if in water or lava
                if (player.isInWater() || player.isInLava()) {
                    exhaustionCap.addExhaustion(EX_COLD * coldMultiplier); // double penalty
                }
            }
        }

        // Swimming exhaustion (applied every tick while moving in water)
        if (player.isInWater() && horizontalSpeed > 0.01 && !player.isSpectator()) {
            exhaustionCap.addExhaustion(EX_SWIM);
        }
    }
}