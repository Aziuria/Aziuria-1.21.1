package net.Aziuria.aziuriamod.worldgen;

import net.Aziuria.aziuriamod.worldgen.rules.NearLavaPlacementModifier;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModOrePlacement {

    public static List<PlacementModifier> orePlacement(PlacementModifier pCountPlacement, PlacementModifier pHeightRange) {
        return List.of(pCountPlacement, InSquarePlacement.spread(), pHeightRange, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int pCount, PlacementModifier pHeightRange) {
        return orePlacement(CountPlacement.of(pCount), pHeightRange);
    }

    public static List<PlacementModifier> rareOrePlacement(int pChance, PlacementModifier pHeightRange) {
        return orePlacement(RarityFilter.onAverageOnceEvery(pChance), pHeightRange);
    }

    // 🔥 New: Lava-biased ore placement (uses custom NearLavaPlacementModifier)
    public static List<PlacementModifier> lavaBiasedOrePlacement(int pCount, PlacementModifier pHeightRange) {
        return List.of(
                CountPlacement.of(pCount),
                InSquarePlacement.spread(),
                pHeightRange,
                NearLavaPlacementModifier.of(8, 2.5),  // ← use factory method instead of INSTANCE
                BiomeFilter.biome()
        );
    }

}
