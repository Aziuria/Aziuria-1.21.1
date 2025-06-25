package net.Aziuria.aziuriamod.fog;

import net.Aziuria.aziuriamod.block.entity.SpeakerBlockEntity;
import net.Aziuria.aziuriamod.sounds.FadingSirenSoundInstance;
import net.Aziuria.aziuriamod.sounds.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.sounds.SoundSource;

public class FogEventManager {
    private static final Minecraft mc = Minecraft.getInstance();
    private static final RandomSource random = RandomSource.create();

    private static FogType activeFog = null;
    private static FogIntensity currentIntensity = FogIntensity.MEDIUM;

    private static long fogStart = 0;
    private static long fogEnd = 0;
    private static long fogFadeInEnd = 0;
    private static long fogFadeOutStart = 0;

    private static boolean dissipatingMessageSent = false;

    private static final int TRANSITION_DURATION = 20 * 6; // 6 seconds (60 ticks)
    private static final int FOG_COOLDOWN_TICKS = 20 * 60 * 5; // 5 minutes cooldown

    private static long nextFogCheckTime = 0;

    public static void tick() {
        ClientLevel level = mc.level;
        if (level == null || mc.isPaused()) return;

        long time = level.getGameTime();

        if (activeFog == null && time < nextFogCheckTime) return;

        // End fog after full duration
        if (activeFog != null && time >= fogEnd) {
            if (!dissipatingMessageSent && mc.player != null) {
                mc.player.sendSystemMessage(Component.literal("⚠ Fog is dissipating...")
                        .withStyle(style -> style.withColor(net.minecraft.ChatFormatting.YELLOW)));
                dissipatingMessageSent = true;
            }

            // After fade-out completes, clear the fog and stop sirens
            if (time >= fogEnd + TRANSITION_DURATION) {
                activeFog = null;
                dissipatingMessageSent = false;
                nextFogCheckTime = time + FOG_COOLDOWN_TICKS;
                stopAllSirens();  // <-- Stop siren sounds here
            }
        }

        // Start new fog if none active and cooldown passed
        if (activeFog == null && time >= nextFogCheckTime) {
            for (FogType type : FogRegistry.getAll()) {
                if (type.shouldStart(level, random)) {
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
        if (mc.level == null || mc.player == null) return;

        activeFog = type;
        currentIntensity = FogIntensity.values()[random.nextInt(FogIntensity.values().length)];
        long time = mc.level.getGameTime();

        long duration = type.getDurationTicks(random);
        fogStart = time;
        fogFadeInEnd = time + TRANSITION_DURATION;
        fogFadeOutStart = time + duration - TRANSITION_DURATION;
        fogEnd = time + duration;

        dissipatingMessageSent = false;
        nextFogCheckTime = fogEnd + FOG_COOLDOWN_TICKS;

        BlockPos playerPos = mc.player.blockPosition();

        // Check if there's a speaker block nearby before playing the siren
        int radius = 150;
        for (BlockPos pos : BlockPos.betweenClosed(playerPos.offset(-radius, -radius, -radius),
                playerPos.offset(radius, radius, radius))) {
            if (mc.level.getBlockEntity(pos) instanceof SpeakerBlockEntity) {
                mc.getSoundManager().play(new FadingSirenSoundInstance(ModSounds.SIREN.get(), pos.immutable()));
            }
        }
    }

    public static void stopFogNow() {
        activeFog = null;
        fogStart = 0;
        fogEnd = 0;
        fogFadeInEnd = 0;
        fogFadeOutStart = 0;
        dissipatingMessageSent = false;

        if (mc.level != null)
            nextFogCheckTime = mc.level.getGameTime() + FOG_COOLDOWN_TICKS;

        stopAllSirens(); // <-- Also stop sirens if fog is stopped manually
    }

    private static void stopAllSirens() {
        if (mc == null || mc.getSoundManager() == null) return;

        SoundManager soundManager = mc.getSoundManager();
        // Stop all siren sounds on BLOCKS source channel
        soundManager.stop(ModSounds.SIREN.get().getLocation(), SoundSource.BLOCKS);
    }

    public static FogType getActiveFog() {
        return activeFog;
    }

    public static FogIntensity getIntensity() {
        return currentIntensity;
    }

    public static float getFogEndDistance() {
        if (activeFog == null) return 0f;

        long time = mc.level.getGameTime();
        float target = activeFog.getFogEnd(currentIntensity);

        float horizonDistance = 50 * 16f; // 30 chunks away (800 blocks)

        if (time <= fogFadeInEnd) {
            float progress = clamp((time - fogStart) / (float) TRANSITION_DURATION);
            return horizonDistance - (horizonDistance - target) * progress;
        } else if (time >= fogFadeOutStart) {
            float progress = clamp((time - fogFadeOutStart) / (float) TRANSITION_DURATION);
            return target + (horizonDistance - target) * progress;
        } else {
            return target;
        }
    }

    private static float clamp(float v) {
        return Math.max(0f, Math.min(1f, v));
    }

    public static boolean isEvilFogActive() {
        return activeFog != null && "evil".equals(activeFog.getId());
    }
}