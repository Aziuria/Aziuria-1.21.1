package net.Aziuria.aziuriamod.worldgen.biomes.surface;

import net.Aziuria.aziuriamod.worldgen.biomes.ModBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class ModSurfaceRules {

    // ===== Vanilla blocks =====
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource STONE = makeStateRule(Blocks.STONE);

    public static SurfaceRules.RuleSource makeRules() {

        // ===== Condition for topmost surface block =====
        SurfaceRules.ConditionSource isSurface = SurfaceRules.ON_FLOOR;

        // ===== Custom biome surface rule =====
        SurfaceRules.RuleSource spectralSurface = SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiomes.SPECTRAL_SOULBOUND_FOREST),
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(isSurface, GRASS_BLOCK),       // Top block = Grass
                        SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DIRT) // Only below top = Dirt
                )
        );

        // ===== Default surface for other biomes =====
        SurfaceRules.RuleSource defaultSurface = SurfaceRules.sequence(
                SurfaceRules.ifTrue(isSurface, GRASS_BLOCK),           // Top block = Grass
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DIRT)   // Only below top = Dirt
        );

        // ===== Full sequence =====
        return SurfaceRules.sequence(
                spectralSurface,  // Custom biome first
                defaultSurface,   // Default for others
                STONE             // Fallback for all deeper underground blocks (includes ores)
        );
    }

    // ===== Helper to convert Block to SurfaceRules.RuleSource =====
    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}