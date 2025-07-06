package net.Aziuria.aziuriamod.fog;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;

public class BasicFogType implements FogType {
    @Override
    public boolean shouldStart(ClientLevel level, RandomSource random) {
        // Client-side logic for fog spawning chance
        return random.nextInt(30000) == 0;
    }

    @Override
    public boolean shouldStart(ServerLevel level, RandomSource random) {
        // Server-side logic for fog spawning chance - can be the same or adjusted
        return random.nextInt(30000) == 0;
    }

    @Override
    public int getDurationTicks(RandomSource random) {
        return 20 * 60 * (1 + random.nextInt(5));
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