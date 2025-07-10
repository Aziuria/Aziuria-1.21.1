package net.Aziuria.aziuriamod.worldgen.rules;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth; // ← CHANGED: added import for clamping
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

import java.util.stream.Stream;

public class NearLogPlacementModifier extends PlacementModifier {

    public final int radius;
    public final double falloffStrength; // NEW FIELD

    public NearLogPlacementModifier(int radius, double falloffStrength) {
        this.radius = radius;
        this.falloffStrength = Mth.clamp(falloffStrength, 0.1, 10.0); // ← CHANGED: clamp falloffStrength for safety
    }

    public int getRadius() {
        return radius;
    }

    public double getFalloffStrength() {
        return falloffStrength;
    }

    public static NearLogPlacementModifier of(int radius, double falloffStrength) {
        return new NearLogPlacementModifier(radius, falloffStrength);
    }

    public static final MapCodec<NearLogPlacementModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.INT.fieldOf("radius").forGetter(NearLogPlacementModifier::getRadius),
                    Codec.DOUBLE.fieldOf("falloff_strength").orElse(1.0).forGetter(NearLogPlacementModifier::getFalloffStrength)
            ).apply(instance, NearLogPlacementModifier::new)
    );

    public static final PlacementModifierType<NearLogPlacementModifier> TYPE = () -> CODEC;

    @Override
    public PlacementModifierType<?> type() {
        return TYPE;
    }

    @Override
    public Stream<BlockPos> getPositions(PlacementContext context, RandomSource random, BlockPos pos) {
        var level = context.getLevel();

        int verticalRadius = 20;
        double maxDistance = Math.sqrt(radius * radius + verticalRadius * verticalRadius); // ← CHANGED: moved maxDistance outside loops for efficiency

        boolean foundLogs = false;
        boolean foundLeaves = false;

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -verticalRadius; dy <= verticalRadius; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    double dxNorm = dx / (double) radius;
                    double dyNorm = dy / (double) verticalRadius;
                    double dzNorm = dz / (double) radius;

                    if (dxNorm * dxNorm + dyNorm * dyNorm + dzNorm * dzNorm <= 1.0) {
                        BlockPos checkPos = pos.offset(dx, dy, dz);

                        if (level.getBlockState(checkPos).is(BlockTags.PLANKS) || level.getBlockState(checkPos).is(BlockTags.FENCES)) {
                            return Stream.empty();
                        }

                        if (level.getBlockState(checkPos).is(BlockTags.LOGS)) {
                            foundLogs = true;
                        }
                        if (level.getBlockState(checkPos).is(BlockTags.LEAVES)) {
                            foundLeaves = true;
                        }
                        if (foundLogs && foundLeaves) {
                            double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
                            double baseProbability = 1.0 - (distance / maxDistance) + 0.2;
                            baseProbability = Math.min(baseProbability, 1.0);

                            // Apply tunable falloff
                            double adjustedProbability = Math.pow(baseProbability, falloffStrength);

                            if (random.nextDouble() < adjustedProbability) {
                                return Stream.of(pos);
                            } else {
                                return Stream.empty();
                            }
                        }
                    }
                }
            }
        }
        return Stream.empty();
    }
}