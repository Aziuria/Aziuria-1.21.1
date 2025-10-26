package net.Aziuria.aziuriamod.fog;

import net.Aziuria.aziuriamod.block.entity.SpeakerBlockEntity;
import net.Aziuria.aziuriamod.fog.helper.FogCooldownHelper;
import net.Aziuria.aziuriamod.fog.helper.FogProbabilityHelper;
import net.Aziuria.aziuriamod.fog.helper.NightCycleHelper;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    private static long nextFogCheckTime = 0;

    // --- FIRST-DAY DENIAL ---
    private static boolean firstDayFogDenied = false;

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

        // --- FIRST-DAY DENIAL --- robust using NightCycleHelper
        firstDayFogDenied = serverLevel.getDayTime() >= 24000L;

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

        // --- FIRST-DAY DENIAL CHECK (robust) ---
        if (!firstDayFogDenied) {
            long time = level.getGameTime();
            if (time < 24000L || NightCycleHelper.isNight(time)) {
                // Deny all fog during first day or first night
                return;
            }
            firstDayFogDenied = true; // now allow fog normally
            FogProbabilityHelper.reset();
        }

        if (level.isClientSide()) {
            // CLIENT SIDE LOGIC
            ClientLevel clientLevel = (ClientLevel) level;
            if (mc.isPaused()) return;

            long time = clientLevel.getGameTime();

            if (activeFog != null && time >= fogEnd) {
                // --- SMOOTHLY END EVIL FOG AT SUNRISE (CLIENT SIDE) ---
                if (activeFog != null && "evil".equals(activeFog.getId())) {
                    // --- UPDATED: Use NightCycleHelper ---
                    if (!NightCycleHelper.isNight(time)) {
                        if (fogFadeOutStart < time) {
                            fogFadeOutStart = time;
                            fogEnd = Math.max(fogEnd, time + TRANSITION_DURATION);
                            if (!dissipatingMessageSent && mc.player != null) {
                                mc.player.sendSystemMessage(Component.literal("⚠ The darkness fades as dawn breaks...")
                                        .withStyle(style -> style.withColor(net.minecraft.ChatFormatting.YELLOW)));
                                dissipatingMessageSent = true;
                            }
                            System.out.println("[Fog Debug] Evil fog entering fade-out at sunrise (client) @ tick " + time);
                            saveToSavedData(level);
                        }
                    }
                }
                // --- END SUNRISE CHECK ---

                if (!dissipatingMessageSent && mc.player != null) {
                    mc.player.sendSystemMessage(Component.literal("⚠ Fog is dissipating...")
                            .withStyle(style -> style.withColor(net.minecraft.ChatFormatting.YELLOW)));
                    dissipatingMessageSent = true;
                    saveToSavedData(level);
                }

                if (time >= fogEnd + TRANSITION_DURATION) {
                    activeFog = null;
                    dissipatingMessageSent = false;
                    nextFogCheckTime = time + FogCooldownHelper.calculate(random, null, currentIntensity, 20 * 60 * 5, 20 * 60 * 8);
                    stopAllSirens();
                    saveToSavedData(level);
                }
            }

            if (activeFog == null) {
                // Prevent fog before next check time

                if (mc.level != null && mc.level.getGameTime() < fogEnd) return;

                if (time < nextFogCheckTime) return;

                List<FogType> shuffled = new ArrayList<>(FogRegistry.getAll());
                Collections.shuffle(shuffled, new java.util.Random(random.nextLong())); // randomize order per check
                for (FogType type : shuffled) {
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

            // --- SMOOTHLY END EVIL FOG AT SUNRISE (SERVER SIDE) ---
            if (activeFog != null && "evil".equals(activeFog.getId())) {
                // --- UPDATED: Use NightCycleHelper ---
                if (!NightCycleHelper.isNight(time)) {
                    if (fogFadeOutStart < time) {
                        fogFadeOutStart = time;
                        fogEnd = Math.max(fogEnd, time + TRANSITION_DURATION);
                        System.out.println("[Fog Debug] Evil fog entering fade-out at sunrise (server) @ tick " + time);
                        saveToSavedData(level);
                    }
                }
            }
            // --- END SUNRISE CHECK ---

            if (activeFog != null && time >= fogEnd) {

                if (activeFog != null) {
                    FogProbabilityHelper.recordFogResult(activeFog.getId());
                }

                activeFog = null;
                dissipatingMessageSent = false;
                nextFogCheckTime = time + FogCooldownHelper.calculate(random, null, currentIntensity, 20 * 60 * 5, 20 * 60 * 8);


                NetworkHandler.sendFogStateToAll(new FogStateSyncPacket(
                        "", 0, 0, 0, 0, 0
                ));
                saveToSavedData(level);
                return;

            }

            if (activeFog == null) {
                // Prevent fog before next check time

                if (mc.level != null && mc.level.getGameTime() < fogEnd) return;

                if (time < nextFogCheckTime) return;

                List<FogType> allFogs = new ArrayList<>(FogRegistry.getAll());
                FogType chosen = FogProbabilityHelper.selectFog(allFogs, new java.util.Random(random.nextLong()));

                if (chosen != null && chosen.shouldStart(serverLevel, random)) {
                    startFogNow(chosen, serverLevel);
                    nextFogCheckTime = time + FogCooldownHelper.calculate(random, activeFog, currentIntensity, 20 * 60 * 5, 20 * 60 * 8);
                    saveToSavedData(level);

                }
            }
        }
    }


    public static void startFogNow(FogType type, ServerLevel serverLevel) {
        activeFog = type;
        currentIntensity = FogIntensity.values()[random.nextInt(FogIntensity.values().length)];
        long time = serverLevel.getGameTime();

        long duration = type.getDurationTicks(random);

        // --- UPDATED: Use NightCycleHelper to CAP Evil Fog duration to NOT exceed sunrise ---
        if ("evil".equals(type.getId())) {
            long remainingNight = NightCycleHelper.ticksUntilDay(time);

            // Only cap if random duration would EXCEED sunrise
            if (duration > remainingNight) {
                duration = remainingNight;
            }
        }

        fogStart = time;
        fogFadeInEnd = time + TRANSITION_DURATION;
        fogFadeOutStart = time + duration - TRANSITION_DURATION;
        fogEnd = time + duration;
        dissipatingMessageSent = false;
        nextFogCheckTime = fogEnd + FogCooldownHelper.calculate(random, activeFog, currentIntensity, 20 * 60 * 5, 20 * 60 * 8);


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
        nextFogCheckTime = fogEnd + FogCooldownHelper.calculate(random, activeFog, currentIntensity, 20 * 60 * 5, 20 * 60 * 8);

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
            nextFogCheckTime = mc.level.getGameTime() + FogCooldownHelper.calculate(random, null, currentIntensity, 20 * 60 * 5, 20 * 60 * 8);
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
            return Math.min(horizonDistance, target + (horizonDistance - target) * progress);
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