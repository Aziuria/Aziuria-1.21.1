package net.Aziuria.aziuriamod.fog;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;

public interface FogType {
    boolean shouldStart(ClientLevel level, RandomSource random);
    int getDurationTicks(RandomSource random);
    float getFogStart();
    float getFogEnd(FogIntensity intensity);
    Vec3 getFogColor();
    String getId();
}