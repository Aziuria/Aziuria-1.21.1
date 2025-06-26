package net.Aziuria.aziuriamod.fog;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;

public interface FogType {
    // Existing client-side method (unchanged)
    boolean shouldStart(ClientLevel level, RandomSource random);

    // New server-side method to be implemented for server calls
    default boolean shouldStart(ServerLevel level, RandomSource random) {
        // Default implementation: call client version with null or throw
        // or provide a basic default behavior (can be overridden)
        return false;
    }

    int getDurationTicks(RandomSource random);
    float getFogStart();
    float getFogEnd(FogIntensity intensity);
    Vec3 getFogColor();
    String getId();
}