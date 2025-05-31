package net.Aziuria.aziuriamod.fog;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;
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

    // ⬇️ NEW: cooldown before next fog can start again
    private static long nextFogCheckTime = 0; // ⬅️ timestamp for when fog can next be considered
    private static final int FOG_COOLDOWN_TICKS = 20 * 60 * 5; // ⬅️ 5 minutes cooldown

    public static void tick() {
        ClientLevel level = mc.level;
        if (level == null || mc.isPaused()) return;

        long time = level.getGameTime();

        // ⬇️ NEW: Skip if cooldown not yet over
        if (activeFog == null && !isFadingOut && time < nextFogCheckTime) return;

        // ⬇️ Fade-out handling
        if (activeFog != null && time >= fogEnd) {
            if (!isFadingOut) {
                isFadingOut = true;
                fogEnd = time + TRANSITION_DURATION;
            } else if (time >= fogEnd) {
                activeFog = null;
                isFadingOut = false;

                // ⬇️ Set next check time after fog ends
                nextFogCheckTime = time + FOG_COOLDOWN_TICKS; // ⬅️ wait 5 minutes after fog ends
            }
        }

        // ⬇️ NEW: Try to start new fog only after cooldown
        if (activeFog == null && !isFadingOut && time >= nextFogCheckTime) {
            for (FogType type : FogRegistry.getAll()) {
                if (type.shouldStart(level, random)) {

                    // Show red warning in chat
                    if (mc.player != null) {
                        mc.player.sendSystemMessage(Component.literal("⚠ Fog is approaching...")
                                .withStyle(style -> style.withColor(net.minecraft.ChatFormatting.RED)));
                    }

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

        // ⬇️ Reset the cooldown when fog starts (optional safety)
        nextFogCheckTime = fogEnd + FOG_COOLDOWN_TICKS;
    }

    public static void stopFogNow() {
        activeFog = null;
        fogStart = 0;
        fogEnd = 0;
        isFadingOut = false;

        // ⬇️ Start cooldown if fog manually stopped
        if (mc.level != null)
            nextFogCheckTime = mc.level.getGameTime() + FOG_COOLDOWN_TICKS;
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