package net.Aziuria.aziuriamod.fog;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;

public class BasicFogType implements FogType {
    @Override
    public boolean shouldStart(ClientLevel level, RandomSource random) {
        return random.nextInt(12000) == 0;
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