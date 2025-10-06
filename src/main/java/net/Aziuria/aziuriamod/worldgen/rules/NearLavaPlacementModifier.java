package net.Aziuria.aziuriamod.worldgen.rules;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

import java.util.stream.Stream;

/**
 * Custom placement modifier that biases ore generation near lava pools.
 * Ideal for semi-rare ores like Orichalcum or Spinel.
 */
public class NearLavaPlacementModifier extends PlacementModifier {

    private final int radius;
    private final double falloffStrength;

    public NearLavaPlacementModifier(int radius, double falloffStrength) {
        this.radius = radius;
        this.falloffStrength = Mth.clamp(falloffStrength, 0.1, 10.0);
    }

    public int getRadius() {
        return radius;
    }

    public double getFalloffStrength() {
        return falloffStrength;
    }

    public static NearLavaPlacementModifier of(int radius, double falloffStrength) {
        return new NearLavaPlacementModifier(radius, falloffStrength);
    }

    public static final MapCodec<NearLavaPlacementModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.INT.fieldOf("radius").forGetter(NearLavaPlacementModifier::getRadius),
                    Codec.DOUBLE.fieldOf("falloff_strength").orElse(1.0).forGetter(NearLavaPlacementModifier::getFalloffStrength)
            ).apply(instance, NearLavaPlacementModifier::new)
    );

    public static final PlacementModifierType<NearLavaPlacementModifier> TYPE = () -> CODEC;

    @Override
    public PlacementModifierType<?> type() {
        return TYPE;
    }

    @Override
    public Stream<BlockPos> getPositions(PlacementContext context, RandomSource random, BlockPos pos) {
        var level = context.getLevel();

        int verticalRadius = Math.max(4, radius / 2);
        double maxDistance = Math.sqrt(radius * radius + verticalRadius * verticalRadius);

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -verticalRadius; dy <= verticalRadius; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {

                    double dxNorm = dx / (double) radius;
                    double dyNorm = dy / (double) verticalRadius;
                    double dzNorm = dz / (double) radius;

                    if (dxNorm * dxNorm + dyNorm * dyNorm + dzNorm * dzNorm <= 1.0) {
                        BlockPos checkPos = pos.offset(dx, dy, dz);
                        FluidState fluid = level.getFluidState(checkPos);

                        // Check for both flowing and source lava
                        if (fluid.is(FluidTags.LAVA) || level.getBlockState(checkPos).is(Blocks.LAVA)) {
                            double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
                            double baseProbability = 1.0 - (distance / maxDistance) + 0.1;
                            baseProbability = Math.min(baseProbability, 1.0);

                            // Apply falloff exponent
                            double adjustedProbability = Math.pow(baseProbability, falloffStrength);

                            if (random.nextDouble() < adjustedProbability) {
                                return Stream.of(pos);
                            }
                        }
                    }
                }
            }
        }

        // No lava nearby → skip generation
        return Stream.empty();
    }
}