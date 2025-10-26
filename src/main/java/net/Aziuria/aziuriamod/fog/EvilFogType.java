package net.Aziuria.aziuriamod.fog;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;

public class EvilFogType implements FogType {

    public static final int NIGHT_END = 23000;
    public static final int NIGHT_START = 13000;

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

        long timeOfDay;
        long gameTime;

        if (level instanceof ClientLevel cl) {
            gameTime = cl.getGameTime();
            timeOfDay = gameTime % 24000;
        } else if (level instanceof ServerLevel sl) {
            gameTime = sl.getGameTime();
            timeOfDay = gameTime % 24000;
        } else return false;

        // Only allow night-time
        boolean isNight = timeOfDay >= NIGHT_START && timeOfDay <= NIGHT_END;
        if (!isNight) {
            System.out.println("[Fog Debug] Evil fog prevented during daytime: timeOfDay=" + timeOfDay);
            return false;
        }

        float nightProgress = (timeOfDay >= NIGHT_START)
                ? (timeOfDay - NIGHT_START) / (float)(NIGHT_END - NIGHT_START)
                : 0f; // you can adjust if you want wrap-around for early morning

        int baseChance = 2000;
        double surpriseFactor = 0.5 + random.nextDouble();
        int adjustedChance = Math.max(1, (int)(baseChance * (1.0f - nightProgress * 0.6f) * surpriseFactor));
        boolean trigger = random.nextInt(adjustedChance) == 0;

        if (trigger) {
            System.out.println("[Fog Trigger] Evil fog triggered @ tick " + gameTime +
                    " | TimeOfDay=" + timeOfDay +
                    " | nightProgress=" + String.format("%.2f", nightProgress) +
                    " | adjustedChance=" + adjustedChance);
        }

        return trigger;
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