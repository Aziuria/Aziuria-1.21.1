package net.Aziuria.aziuriamod.worldgen.biomes.surface;

import net.Aziuria.aziuriamod.worldgen.biomes.ModBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class ModSurfaceRules {

    private static final SurfaceRules.RuleSource GRASS_BLOCK = state(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource DIRT = state(Blocks.DIRT);
    private static final SurfaceRules.RuleSource STONE = state(Blocks.STONE);
    private static final SurfaceRules.RuleSource DEEPSLATE = state(Blocks.DEEPSLATE);

    public static SurfaceRules.RuleSource makeRules() {

        // ===== PLAINS SURFACE (EXACT LOGIC) =====
        SurfaceRules.RuleSource plainsSurface =
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.waterBlockCheck(0, 0),
                                        GRASS_BLOCK
                                ),
                                DIRT
                        )
                );

        // ===== BIOME OVERRIDE =====
        SurfaceRules.RuleSource spectralSurface =
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(ModBiomes.SPECTRAL_SOULBOUND_FOREST),
                        plainsSurface
                );

        // ===== DEEPSLATE (VANILLA GRADIENT) =====
        SurfaceRules.RuleSource deepslate =
                SurfaceRules.ifTrue(
                        SurfaceRules.verticalGradient(
                                "deepslate",
                                VerticalAnchor.absolute(0),
                                VerticalAnchor.absolute(8)
                        ),
                        DEEPSLATE
                );

        // ===== FINAL ORDER (MATCHES VANILLA) =====
        return SurfaceRules.sequence(
                spectralSurface,   // biome surface
                plainsSurface,     // default plains surface
                deepslate,         // deepslate transition
                STONE              // stone fallback
        );
    }

    private static SurfaceRules.RuleSource state(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}