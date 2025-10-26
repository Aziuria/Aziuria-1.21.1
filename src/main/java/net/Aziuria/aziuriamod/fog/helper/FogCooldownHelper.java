package net.Aziuria.aziuriamod.fog.helper;

import net.Aziuria.aziuriamod.fog.FogIntensity;
import net.Aziuria.aziuriamod.fog.FogType;
import net.minecraft.util.RandomSource;

public class FogCooldownHelper {

    /**
     * Calculates an adaptive cooldown for fog based on type and intensity.
     */
    public static int calculate(RandomSource random, FogType activeFog, FogIntensity intensity,
                                int baseMin, int baseMax) {

        double min = baseMin;
        double max = baseMax;

        // Increase cooldown for high intensity
        if (intensity == FogIntensity.HIGH) {
            min *= 1.5;
            max *= 1.6;
        } else if (intensity == FogIntensity.LOW) {
            min *= 0.8;
            max *= 0.9;
        }

        // Further adjustment if the fog is "evil"
        if (activeFog != null && "evil".equals(activeFog.getId())) {
            min *= 1.25;
            max *= 1.25;
        }

        return random.nextInt((int)(max - min + 1)) + (int)min;
    }
}