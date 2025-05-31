package net.Aziuria.aziuriamod.fog;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;

public class EvilFogType implements FogType {
    @Override
    public boolean shouldStart(ClientLevel level, RandomSource random) {
        return level.isNight() && random.nextInt(30000) == 0;
    }

    @Override
    public int getDurationTicks(RandomSource random) {
        return 20 * 60 * (3 + random.nextInt(3));
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
        return new Vec3(0.7, 0.7, 0.7); // same as BasicFog
    }

    @Override
    public String getId() {
        return "evil";
    }
}