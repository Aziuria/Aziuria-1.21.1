package net.Aziuria.aziuriamod.fog;

import net.Aziuria.aziuriamod.block.entity.SpeakerBlockEntity;
import net.Aziuria.aziuriamod.fog.network.FogStateSyncPacket;   // ← Packet import
import net.Aziuria.aziuriamod.fog.network.NetworkHandler;       // ← Network handler import
import net.Aziuria.aziuriamod.sounds.FadingSirenSoundInstance;
import net.Aziuria.aziuriamod.sounds.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;                // ← ServerLevel import
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;

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

    private static final int TRANSITION_DURATION = 20 * 6;
    private static final int FOG_COOLDOWN_TICKS = 20 * 60 * 5;

    private static long nextFogCheckTime = 0;


    // LOAD SAVED DATA & SYNC TO CLIENTS ON SERVER STARTUP
    public static void loadFromSavedData(Level level) {
        if (!(level instanceof ServerLevel serverLevel)) return;

        FogEventSavedData data = FogEventSavedData.get(serverLevel);
        activeFog = FogRegistry.getAll().stream()
                .filter(f -> f.getId().equals(data.getActiveFogId()))
                .findFirst()
                .orElse(null);

        int ordinal = data.getCurrentIntensityOrdinal();
        currentIntensity = ordinal >= 0 && ordinal < FogIntensity.values().length
                ? FogIntensity.values()[ordinal]
                : FogIntensity.MEDIUM;

        fogStart = data.getFogStart();
        fogEnd = data.getFogEnd();
        fogFadeInEnd = data.getFogFadeInEnd();
        fogFadeOutStart = data.getFogFadeOutStart();
        dissipatingMessageSent = data.getDissipatingMessageSent();
        nextFogCheckTime = data.getNextFogCheckTime();


        // SEND CURRENT FOG STATE TO ALL CLIENTS
        NetworkHandler.sendFogStateToAll(new FogStateSyncPacket(
                activeFog == null ? "" : activeFog.getId(),
                currentIntensity == null ? 0 : currentIntensity.ordinal(),
                fogStart,
                fogEnd,
                fogFadeInEnd,
                fogFadeOutStart
        ));

    }

    // SAVE CURRENT FOG DATA
    public static void saveToSavedData(Level level) {
        if (!(level instanceof ServerLevel serverLevel)) return;

        FogEventSavedData data = FogEventSavedData.get(serverLevel);
        data.setActiveFogId(activeFog == null ? "" : activeFog.getId());
        data.setCurrentIntensityOrdinal(currentIntensity == null ? 0 : currentIntensity.ordinal());
        data.setFogStart(fogStart);
        data.setFogEnd(fogEnd);
        data.setFogFadeInEnd(fogFadeInEnd);
        data.setFogFadeOutStart(fogFadeOutStart);
        data.setDissipatingMessageSent(dissipatingMessageSent);
        data.setNextFogCheckTime(nextFogCheckTime);
    }

    // MAIN TICK FUNCTION
    public static void tick(Level level) {
        if (level.isClientSide()) {
            // CLIENT SIDE LOGIC
            ClientLevel clientLevel = (ClientLevel) level;
            if (mc.isPaused()) return;

            long time = clientLevel.getGameTime();

            if (activeFog == null && time < nextFogCheckTime) return;

            if (activeFog != null && time >= fogEnd) {
                if (!dissipatingMessageSent && mc.player != null) {
                    mc.player.sendSystemMessage(Component.literal("⚠ Fog is dissipating...")
                            .withStyle(style -> style.withColor(net.minecraft.ChatFormatting.YELLOW)));
                    dissipatingMessageSent = true;
                    saveToSavedData(level);
                }

                if (time >= fogEnd + TRANSITION_DURATION) {
                    activeFog = null;
                    dissipatingMessageSent = false;
                    nextFogCheckTime = time + FOG_COOLDOWN_TICKS;
                    stopAllSirens();
                    saveToSavedData(level);
                }
            }

            if (activeFog == null && time >= nextFogCheckTime) {
                for (FogType type : FogRegistry.getAll()) {
                    if (type.shouldStart(clientLevel, random)) {
                        if (mc.player != null) {
                            mc.player.sendSystemMessage(Component.literal("⚠ Fog is approaching...")
                                    .withStyle(style -> style.withColor(net.minecraft.ChatFormatting.RED)));
                        }
                        startFogNow(type);
                        saveToSavedData(level);
                        break;
                    }
                }
            }
        } else {
            // SERVER SIDE LOGIC
            ServerLevel serverLevel = (ServerLevel) level;
            long time = serverLevel.getGameTime();

            if (activeFog == null && time < nextFogCheckTime) return;

            if (activeFog != null && time >= fogEnd) {

                activeFog = null;
                dissipatingMessageSent = false;
                nextFogCheckTime = time + FOG_COOLDOWN_TICKS;

                NetworkHandler.sendFogStateToAll(new FogStateSyncPacket(
                        "", 0, 0, 0, 0, 0
                ));
                saveToSavedData(level);
                return;

            }

            if (activeFog == null && time >= nextFogCheckTime) {
                // Skip fog for first day
                if (time < 24000) return;
                for (FogType type : FogRegistry.getAll()) {
                    if (type.shouldStart(serverLevel, random)) {

                        startFogNow(type, serverLevel);

                        // Set nextFogCheckTime dynamically instead of a fixed huge cooldown
                        long dynamicCooldown = 20 * (60 * (1 + random.nextInt(360))); // 1–6 minutes
                        nextFogCheckTime = time + dynamicCooldown;

                        saveToSavedData(level);
                        break;

                    }
                }
            }
        }
    }


    public static void startFogNow(FogType type, ServerLevel serverLevel) {
        activeFog = type;
        currentIntensity = FogIntensity.values()[random.nextInt(FogIntensity.values().length)];
        long time = serverLevel.getGameTime();

        long duration = type.getDurationTicks(random);
        fogStart = time;
        fogFadeInEnd = time + TRANSITION_DURATION;
        fogFadeOutStart = time + duration - TRANSITION_DURATION;
        fogEnd = time + duration;
        dissipatingMessageSent = false;
        nextFogCheckTime = fogEnd + FOG_COOLDOWN_TICKS;


        NetworkHandler.sendFogStateToAll(new FogStateSyncPacket(
                activeFog.getId(),
                currentIntensity.ordinal(),
                fogStart,
                fogEnd,
                fogFadeInEnd,
                fogFadeOutStart
        ));
    }

    // CLIENT-SIDE: START FOG WITHOUT SYNC (used only by client visuals)
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

        saveToSavedData(mc.level);

        BlockPos playerPos = mc.player.blockPosition();
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

        if (mc.level != null) {
            nextFogCheckTime = mc.level.getGameTime() + FOG_COOLDOWN_TICKS;
            saveToSavedData(mc.level);
        }

        stopAllSirens();
    }

    private static void stopAllSirens() {
        if (mc == null || mc.getSoundManager() == null) return;
        SoundManager soundManager = mc.getSoundManager();
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
        float horizonDistance = 50 * 16f;

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

    public static long getFogStart() {
        return fogStart;
    }

    public static long getFogEnd() {
        return fogEnd;
    }

    public static long getFogFadeInEnd() {
        return fogFadeInEnd;
    }

    public static long getFogFadeOutStart() {
        return fogFadeOutStart;
    }
}