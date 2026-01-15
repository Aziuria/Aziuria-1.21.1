package net.Aziuria.aziuriamod.fog;

import net.Aziuria.aziuriamod.block.entity.custom.SpeakerBlockEntity;
import net.Aziuria.aziuriamod.fog.helper.*;
import net.Aziuria.aziuriamod.fog.network.FogStateSyncPacket;
import net.Aziuria.aziuriamod.fog.network.NetworkHandler;
import net.Aziuria.aziuriamod.sounds.FadingSirenSoundInstance;
import net.Aziuria.aziuriamod.sounds.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
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
    private static boolean evilFogFadeOutTriggered = false;

    private static int daysFogDenied = 0; // tracks number of full days fog is denied
    private static final int FOG_DENIAL_DAYS = 3; // now 3

    private static boolean fogForceStopping = false;

    private static boolean fogEnabled = true;

    // --- LOAD ---
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

        daysFogDenied = data.getDaysFogDenied(); // new method in FogEventSavedData
        fogEnabled = data.isFogEnabled();

        NetworkHandler.sendFogStateToAll(new FogStateSyncPacket(
                activeFog == null ? "" : activeFog.getId(),
                currentIntensity == null ? 0 : currentIntensity.ordinal(),
                fogStart, fogEnd, fogFadeInEnd, fogFadeOutStart
        ));
    }

    // --- SAVE ---
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
        data.setDaysFogDenied(daysFogDenied); // <- add this
        data.setFogEnabled(fogEnabled);
    }

    // --- TICK ---
    public static void tick(Level level) {
        long time = level.getGameTime();

        // FIRST-DAY DENIAL
        if (daysFogDenied < FOG_DENIAL_DAYS) {
            long dayNumber = level.getDayTime() / 24000L;
            if (dayNumber < FOG_DENIAL_DAYS || NightCycleHelper.isNight(time)) return;
            daysFogDenied = FOG_DENIAL_DAYS; // now allow fog
            FogProbabilityHelper.reset();
        }

        if (level.isClientSide()) {
            tickClient((ClientLevel) level, time);
        } else {
            tickServer((ServerLevel) level, time);
        }
    }

    private static void tickClient(ClientLevel level, long time) {
        if (mc.isPaused()) return;

        if (activeFog != null) {
            handleActiveFogClient(level, time);
        } else if (time >= nextFogCheckTime) {
            attemptFogStartClient(level);
        }
    }

    private static void handleActiveFogClient(ClientLevel level, long time) {
        // Evil fog sunrise fade
        if (isEvilFogActive() && !evilFogFadeOutTriggered && !NightCycleHelper.isNight(time)) {
            fogFadeOutStart = time;
            fogEnd = Math.max(fogEnd, time + TRANSITION_DURATION);
            evilFogFadeOutTriggered = true;
            if (mc.player != null && !dissipatingMessageSent) {
                mc.player.sendSystemMessage(Component.literal("⚠ The darkness fades as dawn breaks...")
                        .withStyle(s -> s.withColor(net.minecraft.ChatFormatting.YELLOW)));
                dissipatingMessageSent = true;
            }
            saveToSavedData(level);
        }

        if (time >= fogEnd) {
            if (!dissipatingMessageSent && mc.player != null) {
                mc.player.sendSystemMessage(Component.literal("⚠ Fog is dissipating...")
                        .withStyle(s -> s.withColor(net.minecraft.ChatFormatting.YELLOW)));
                dissipatingMessageSent = true;
            }
            if (time >= fogEnd + TRANSITION_DURATION) {
                // Fully clear fog state
                activeFog = null;
                dissipatingMessageSent = false;

                // Reset stop flags
                fogForceStopping = false;
                evilFogFadeOutTriggered = false;

                // Queue next fog naturally
                nextFogCheckTime = mc.level.getGameTime() +
                        FogCooldownHelper.calculate(random, null, currentIntensity, 20 * 60 * 5, 20 * 60 * 8);

                stopAllSirens();
            }
            saveToSavedData(level);
        }
    }

    private static void attemptFogStartClient(ClientLevel level) {
        if (!isFogEnabled()) return;
        List<FogType> shuffled = new ArrayList<>(FogRegistry.getAll());
        Collections.shuffle(shuffled, new java.util.Random(random.nextLong()));

        for (FogType type : shuffled) {
            if (type.shouldStart(level, random)) {
                if (mc.player != null) {
                    mc.player.sendSystemMessage(Component.literal("⚠ Fog is approaching...")
                            .withStyle(s -> s.withColor(net.minecraft.ChatFormatting.RED)));
                }
                startFogNow(type);
                saveToSavedData(level);
                break;
            }
        }
    }

    private static void tickServer(ServerLevel level, long time) {


        if (!fogEnabled) {
            if (activeFog != null) {
                activeFog = null;
                dissipatingMessageSent = false;
                fogStart = 0;
                fogEnd = 0;
                fogFadeInEnd = 0;
                fogFadeOutStart = 0;
                nextFogCheckTime = time + FogCooldownHelper.calculate(random, null, currentIntensity, 20*60*5, 20*60*8);

                NetworkHandler.sendFogStateToAll(new FogStateSyncPacket("", 0, 0, 0, 0, 0));
                saveToSavedData(level);
            }
            return;
        }


        if (activeFog != null) {
            // Evil fog sunrise fade
            if (isEvilFogActive() && !evilFogFadeOutTriggered && !NightCycleHelper.isNight(time)) {
                fogFadeOutStart = time;
                fogEnd = Math.max(fogEnd, time + TRANSITION_DURATION);
                evilFogFadeOutTriggered = true;
                saveToSavedData(level);
            }

            if (time >= fogEnd) {
                // Record fog event
                FogProbabilityHelper.recordFogResult(activeFog.getId());

                // Fully remove fog
                activeFog = null;
                dissipatingMessageSent = false;

                // Reset force stop and sunrise fade flags
                fogForceStopping = false;
                evilFogFadeOutTriggered = false;

                // Normal cooldown scheduling
                nextFogCheckTime = time +
                        FogCooldownHelper.calculate(random, null, currentIntensity, 20 * 60 * 5, 20 * 60 * 8);

                // Sync removal to client
                NetworkHandler.sendFogStateToAll(
                        new FogStateSyncPacket(
                                "",                // no active fog
                                -1,                // no intensity
                                time,              // start time irrelevant
                                time,              // fog end
                                time,              // fade in end
                                time               // fade out start
                        )
                );

                saveToSavedData(level);
                return;
            }
        }

        // Prevent fog on first day
        if (time < 24000 && activeFog == null) {
            nextFogCheckTime = 24000;
            return;
        }

        if (activeFog == null  && fogEnabled && time >= nextFogCheckTime) {
            List<FogType> allFogs = new ArrayList<>(FogRegistry.getAll());
            FogType chosen = FogProbabilityHelper.selectFog(allFogs, new java.util.Random(random.nextLong()));
            if (chosen != null && chosen.shouldStart(level, random)) {
                startFogNow(chosen, level);
                nextFogCheckTime = time + FogCooldownHelper.calculate(random, activeFog, currentIntensity, 20*60*5, 20*60*8);
                saveToSavedData(level);
            }
        }
    }

    // --- START FOG ---
    public static void startFogNow(FogType type, ServerLevel level) {
        activeFog = type;
        evilFogFadeOutTriggered = false;
        currentIntensity = FogIntensity.values()[random.nextInt(FogIntensity.values().length)];

        long time = level.getGameTime();
        long duration = type.getDurationTicks(random);

        // Cap evil fog at sunrise
        if (isEvilFogActive()) {
            long remainingNight = NightCycleHelper.ticksUntilDay(time);
            if (duration > remainingNight) duration = remainingNight;
        }

        fogStart = time;
        fogFadeInEnd = time + TRANSITION_DURATION;
        fogFadeOutStart = time + duration - TRANSITION_DURATION;
        fogEnd = time + duration;
        dissipatingMessageSent = false;
        nextFogCheckTime = fogEnd + FogCooldownHelper.calculate(random, activeFog, currentIntensity, 20*60*5, 20*60*8);

        NetworkHandler.sendFogStateToAll(new FogStateSyncPacket(
                activeFog.getId(), currentIntensity.ordinal(), fogStart, fogEnd, fogFadeInEnd, fogFadeOutStart
        ));
    }

    public static void startFogNow(FogType type) {
        if (mc.level == null || mc.player == null) return;

        activeFog = type;
        evilFogFadeOutTriggered = false;
        currentIntensity = FogIntensity.values()[random.nextInt(FogIntensity.values().length)];

        long time = mc.level.getGameTime();
        long duration = type.getDurationTicks(random);

        fogStart = time;
        fogFadeInEnd = time + TRANSITION_DURATION;
        fogFadeOutStart = time + duration - TRANSITION_DURATION;
        fogEnd = time + duration;
        dissipatingMessageSent = false;
        nextFogCheckTime = fogEnd + FogCooldownHelper.calculate(random, activeFog, currentIntensity, 20*60*5, 20*60*8);

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

    // --- STOP ---
    public static void stopFogNow() {
        // If nothing active or already force stopping, ignore
        if (activeFog == null || fogForceStopping) return;

        fogForceStopping = true; // prevents repeated stop requests

        long currentTime = 0;
        if (mc.level != null) {
            currentTime = mc.level.getGameTime();
        }

        // Trigger fade-out instead of deleting instantly
        fogFadeOutStart = currentTime;
        fogEnd = currentTime + TRANSITION_DURATION;  // transition fade

        evilFogFadeOutTriggered = true; // prevent sunrise logic retriggering
        dissipatingMessageSent = false; // reset so message can show

        // Optional message — fade-out visual feedback
        if (mc.player != null) {
            mc.player.sendSystemMessage(
                    Component.literal("⚠ Fog is dissipating...")
                            .withStyle(style -> style.withColor(net.minecraft.ChatFormatting.YELLOW))
            );
            dissipatingMessageSent = true;
        }

        // Stop sounds immediately
        stopAllSirens();

        // Write to save and schedule cooldown
        if (mc.level != null) {
            nextFogCheckTime = mc.level.getGameTime() +
                    FogCooldownHelper.calculate(random, null, currentIntensity, 20 * 60 * 5, 20 * 60 * 8);
            saveToSavedData(mc.level);
        }
    }

    private static void stopAllSirens() {
        if (mc == null || mc.getSoundManager() == null) return;
        SoundManager sm = mc.getSoundManager();
        sm.stop(ModSounds.SIREN.get().getLocation(), SoundSource.BLOCKS);
    }

    // NEW: Fog enable/disable methods
    public static void setFogEnabled(boolean enabled) {
        fogEnabled = enabled;
        if (!enabled) stopFogNow(); // stop active fog immediately
    }

    public static boolean isFogEnabled() {
        return fogEnabled;
    }

    // --- HELPERS ---
    public static FogType getActiveFog() { return activeFog; }
    public static FogIntensity getIntensity() { return currentIntensity; }
    public static boolean isEvilFogActive() { return activeFog != null && "evil".equals(activeFog.getId()); }

    public static long getFogStart() { return fogStart; }
    public static long getFogEnd() { return fogEnd; }
    public static long getFogFadeInEnd() { return fogFadeInEnd; }
    public static long getFogFadeOutStart() { return fogFadeOutStart; }

    public static float getFogEndDistance() {
        if (activeFog == null || mc.level == null) return 0f;

        long time = mc.level.getGameTime();
        float fullDistance = activeFog.getFogEnd(currentIntensity);
        float horizonDistance = 800f;

        if (time <= fogFadeInEnd) {
            float progress = clamp((time - fogStart) / (float)(fogFadeInEnd - fogStart));
            progress = (float)Math.pow(progress, 2.5f);
            return horizonDistance - (horizonDistance - fullDistance) * progress;
        }
        if (time >= fogFadeOutStart) {
            float progress = clamp((time - fogFadeOutStart) / (float)(fogEnd - fogFadeOutStart));
            progress = (float)Math.pow(progress, 1.4f);
            return fullDistance + (horizonDistance - fullDistance) * progress;
        }
        return fullDistance;
    }

    private static float clamp(float v) { return Math.max(0f, Math.min(1f, v)); }
}