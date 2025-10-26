package net.Aziuria.aziuriamod.fog.helper;

import net.Aziuria.aziuriamod.fog.FogType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FogProbabilityHelper {

    private static int consecutiveNormal = 0;
    private static int consecutiveEvil = 0;
    private static final Random random = new Random();

    /**
     * Called whenever a fog event finishes.
     * @param fogId the type of fog that just ended ("normal" or "evil")
     */
    public static void recordFogResult(String fogId) {
        if ("evil".equals(fogId)) {
            consecutiveEvil++;
            consecutiveNormal = 0;
        } else {
            consecutiveNormal++;
            consecutiveEvil = 0;
        }
    }

    /**
     * Returns true if the next fog should be forced evil based on consecutive normals.
     */
    public static boolean shouldForceEvilNext() {
        if (consecutiveNormal >= 6) return true;

        float chance = switch (consecutiveNormal) {
            case 1 -> 0.04f;
            case 2 -> 0.18f;
            case 3 -> 0.36f;
            case 4 -> 0.67f;
            case 5 -> 0.88f;
            default -> 0f;
        };
        return random.nextFloat() < chance;
    }

    /**
     * Returns true if the next fog should be forced normal based on consecutive evils.
     */
    public static boolean shouldForceNormalNext() {
        if (consecutiveEvil >= 3) return true;

        float chance = switch (consecutiveEvil) {
            case 1 -> 0.36f;
            case 2 -> 0.89f;
            default -> 0f;
        };
        return random.nextFloat() < chance;
    }

    /**
     * Chooses the next fog type from the provided list using the probability rules.
     */
    public static FogType selectFog(List<FogType> allFogs, Random random) {
        if (allFogs == null || allFogs.isEmpty()) return null;

        boolean forceEvil = shouldForceEvilNext();
        boolean forceNormal = shouldForceNormalNext();

        // Filter eligible fogs based on forced type
        List<FogType> candidates = new ArrayList<>();
        for (FogType fog : allFogs) {
            String id = fog.getId();
            if (forceEvil && "evil".equals(id)) {
                candidates.add(fog);
            } else if (forceNormal && "normal".equals(id)) {
                candidates.add(fog);
            }
        }

        // If no forced type applies or no candidate found, use all available
        if (candidates.isEmpty()) {
            candidates.addAll(allFogs);
        }

        // Pick one at random
        return candidates.get(random.nextInt(candidates.size()));
    }

    /** Resets counters completely (for world reset, first day, etc.) */
    public static void reset() {
        consecutiveNormal = 0;
        consecutiveEvil = 0;
    }
}