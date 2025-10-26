package net.Aziuria.aziuriamod.fog;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;

public class BasicFogType implements FogType {

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

        long gameTime;
        if (level instanceof ClientLevel cl) {
            gameTime = cl.getGameTime();
        } else if (level instanceof ServerLevel sl) {
            gameTime = sl.getGameTime();
        } else return false;

        int baseChance = 1750; // average 2–3 days
        double surpriseFactor = 0.7 + random.nextDouble() * 0.6; // 0.7–1.3
        int adjustedChance = (int)(baseChance * surpriseFactor);

        return random.nextInt(Math.max(1, adjustedChance)) == 0;
    }

    @Override
    public int getDurationTicks(RandomSource random) {
        return 20 * 60 * (1 + random.nextInt(5)); // 1–5 minutes
    }

    @Override
    public float getFogStart() {
        return 0.0f;
    }

    @Override
    public float getFogEnd(FogIntensity intensity) {
        return switch (intensity) {
            case LOW -> 60.0f;
            case MEDIUM -> 30.0f;
            case HIGH -> 10.0f;
        };
    }

    @Override
    public Vec3 getFogColor() {
        return new Vec3(0.7, 0.7, 0.7);
    }

    @Override
    public String getId() {
        return "normal";
    }
}