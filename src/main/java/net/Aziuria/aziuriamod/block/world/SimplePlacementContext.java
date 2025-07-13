package net.Aziuria.aziuriamod.block.world;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementContext;

import java.util.Optional;

public class SimplePlacementContext extends PlacementContext {
    public SimplePlacementContext(WorldGenLevel level, ChunkGenerator generator, Optional<PlacedFeature> feature) {
        super(level, generator, feature);
    }
}