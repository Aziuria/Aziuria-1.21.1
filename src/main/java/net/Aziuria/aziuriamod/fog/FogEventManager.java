package net.Aziuria.aziuriamod.fog;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.RandomSource;

public class FogEventManager {
    private static final Minecraft mc = Minecraft.getInstance();
    private static final RandomSource random = RandomSource.create();

    private static FogType activeFog = null;
    private static FogIntensity currentIntensity = FogIntensity.MEDIUM;

    private static long fogStart = 0;
    private static long fogEnd = 0;
    private static final int TRANSITION_DURATION = 60; // ticks (3 seconds)

    private static boolean isFadingOut = false;

    public static void tick() {
        ClientLevel level = mc.level;
        if (level == null || mc.isPaused()) return;

        long time = level.getGameTime();

        // Fade-out handling
        if (activeFog != null && time >= fogEnd) {
            if (!isFadingOut) {
                isFadingOut = true;
                fogEnd = time + TRANSITION_DURATION;
            } else if (time >= fogEnd) {
                activeFog = null;
                isFadingOut = false;
            }
        }

        // Start fog if none is active or fading
        if (activeFog == null && !isFadingOut) {
            for (FogType type : FogRegistry.getAll()) {
                if (type.shouldStart(level, random)) {
                    startFogNow(type);
                    break;
                }
            }
        }
    }

    public static void startFogNow(FogType type) {
        if (mc.level == null) return;
        activeFog = type;
        currentIntensity = FogIntensity.values()[random.nextInt(FogIntensity.values().length)];
        long time = mc.level.getGameTime();
        fogStart = time;
        fogEnd = time + type.getDurationTicks(random);
        isFadingOut = false;
    }

    public static void stopFogNow() {
        activeFog = null;
        fogStart = 0;
        fogEnd = 0;
        isFadingOut = false;
    }

    public static FogType getActiveFog() {
        return activeFog;
    }

    public static FogIntensity getIntensity() {
        return currentIntensity;
    }

    public static float getFogEndDistance() {
        if (activeFog == null && !isFadingOut) return 0f;

        long time = mc.level.getGameTime();
        float target = activeFog != null ? activeFog.getFogEnd(currentIntensity) : 0f;

        if (isFadingOut) {
            float progress = 1.0f - Math.min(1.0f, (time - (fogEnd - TRANSITION_DURATION)) / (float) TRANSITION_DURATION);
            return target * progress;
        } else {
            float progress = Math.min(1.0f, (time - fogStart) / (float) TRANSITION_DURATION);
            return target * progress;
        }
    }

    public static boolean isEvilFogActive() {
        return activeFog != null && "evil".equals(activeFog.getId());
    }
}