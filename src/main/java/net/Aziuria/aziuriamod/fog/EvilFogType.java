package net.Aziuria.aziuriamod.fog;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
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
        // Prevent overlap
        if (FogEventManager.getActiveFog() != null) return false;

        long timeOfDay;
        if (level instanceof ClientLevel cl) timeOfDay = cl.getGameTime() % 24000;
        else if (level instanceof ServerLevel sl) timeOfDay = sl.getGameTime() % 24000;
        else return false;

        // Night only
        boolean isNight = timeOfDay >= 13000 || timeOfDay <= 7000;
        if (!isNight) return false;

        // Night progress (0=start, 1=end)
        float nightProgress = (timeOfDay - 13000f) / 10000f;

        // Base chance for rare fog (~once a week)
        int baseChance = 600;
        // Surprise factor allows occasional double/triple events
        double surpriseFactor = 0.5 + random.nextDouble(); // 0.5 → 1.5
        int adjustedChance = Math.max(1, (int)(baseChance * (1.0f - nightProgress * 0.5f) * surpriseFactor));

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