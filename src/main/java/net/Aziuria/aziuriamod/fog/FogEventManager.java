package net.Aziuria.aziuriamod.fog;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.RandomSource;

public class FogEventManager {
    private static final Minecraft mc = Minecraft.getInstance();
    private static final RandomSource random = RandomSource.create();

    private static FogType activeFog = null;
    private static FogIntensity currentIntensity = FogIntensity.MEDIUM;
    private static long fogEnd = 0;

    public static void tick() {
        ClientLevel level = mc.level;
        if (level == null || mc.isPaused()) return;

        long time = level.getGameTime();
        if (activeFog != null && time >= fogEnd) {
            activeFog = null;
        }

        if (activeFog == null) {
            for (FogType type : FogRegistry.getAll()) {
                if (type.shouldStart(level, random)) {
                    activeFog = type;
                    currentIntensity = FogIntensity.values()[random.nextInt(FogIntensity.values().length)];
                    fogEnd = time + type.getDurationTicks(random);
                    break;
                }
            }
        }
    }

    public static FogType getActiveFog() {
        return activeFog;
    }

    public static FogIntensity getIntensity() {
        return currentIntensity;
    }

    public static void startFogNow(FogType type) {
        if (mc.level == null) return;
        activeFog = type;
        currentIntensity = FogIntensity.values()[random.nextInt(FogIntensity.values().length)];
        fogEnd = mc.level.getGameTime() + type.getDurationTicks(random);
    }

    public static void stopFogNow() {  // ← New method to stop the fog
        activeFog = null;
        fogEnd = 0;
    }

    public static float getFogEndDistance() {
        return activeFog != null ? activeFog.getFogEnd(currentIntensity) : 0f;
    }

    public static boolean isEvilFogActive() {
        return activeFog != null && "evil".equals(activeFog.getId());
    }
}