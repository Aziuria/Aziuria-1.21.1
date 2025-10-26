package net.Aziuria.aziuriamod.fog;

import net.Aziuria.aziuriamod.fog.helper.NightCycleHelper;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class EvilFogType implements FogType {

    @Override
    public boolean shouldStart(ClientLevel level, RandomSource random) {
        return calculateDynamicChance(level, random);
    }

    @Override
    public boolean shouldStart(ServerLevel level, RandomSource random) {
        return calculateDynamicChance(level, random);
    }

    private boolean calculateDynamicChance(Object level, RandomSource random) {
        if (FogEventManager.getActiveFog() != null) return false;

        Level worldLevel;
        long gameTime;

        if (level instanceof ClientLevel cl) {
            worldLevel = cl;
            gameTime = cl.getGameTime();
        } else if (level instanceof ServerLevel sl) {
            worldLevel = sl;
            gameTime = sl.getGameTime();
        } else return false;

        // Only allow nighttime using NightCycleHelper
        if (!NightCycleHelper.isNight(worldLevel)) {
            return false;
        }

        long timeOfDay = worldLevel.getDayTime() % 24000L;
        float nightProgress = (timeOfDay >= NightCycleHelper.NIGHT_START)
                ? (timeOfDay - NightCycleHelper.NIGHT_START) / (float)(NightCycleHelper.NIGHT_END - NightCycleHelper.NIGHT_START)
                : 0f;

        int baseChance = 2100;
        double surpriseFactor = 0.5 + random.nextDouble();
        int adjustedChance = Math.max(1, (int)(baseChance * (1.0f - nightProgress * 0.6f) * surpriseFactor));
        return random.nextInt(adjustedChance) == 0;
    }

    @Override
    public int getDurationTicks(RandomSource random) {
        return 20 * 60 * (3 + random.nextInt(3)); // 3–5 minutes
    }

    @Override
    public float getFogStart() {
        return 0.0f;
    }

    @Override
    public float getFogEnd(FogIntensity intensity) {
        return switch (intensity) {
            case LOW -> 30.0f;
            case MEDIUM -> 15.0f;
            case HIGH -> 5.0f;
        };
    }

    @Override
    public Vec3 getFogColor() {
        return new Vec3(0.7, 0.7, 0.7);
    }

    @Override
    public String getId() {
        return "evil";
    }
}