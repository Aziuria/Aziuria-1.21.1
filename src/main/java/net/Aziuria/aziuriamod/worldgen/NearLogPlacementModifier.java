package net.Aziuria.aziuriamod.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

import java.util.stream.Stream;

public class NearLogPlacementModifier extends PlacementModifier {

    public final int radius;

    public NearLogPlacementModifier(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public static NearLogPlacementModifier of(int radius) {
        return new NearLogPlacementModifier(radius);
    }

    public static final MapCodec<NearLogPlacementModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.INT.fieldOf("radius").forGetter(NearLogPlacementModifier::getRadius)
            ).apply(instance, NearLogPlacementModifier::new)
    );

    public static final PlacementModifierType<NearLogPlacementModifier> TYPE = () -> CODEC;

    @Override
    public PlacementModifierType<?> type() {
        return TYPE;
    }

    @Override
    public Stream<BlockPos> getPositions(PlacementContext context, RandomSource random, BlockPos pos) {
        // Use context to get the level
        var level = context.getLevel();

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -radius; dy <= radius; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    BlockPos checkPos = pos.offset(dx, dy, dz);
                    if (level.getBlockState(checkPos).is(Blocks.OAK_LOG) ||
                            level.getBlockState(checkPos).is(Blocks.BIRCH_LOG) ||
                            level.getBlockState(checkPos).is(Blocks.SPRUCE_LOG) ||
                            level.getBlockState(checkPos).is(Blocks.JUNGLE_LOG) ||
                            level.getBlockState(checkPos).is(Blocks.DARK_OAK_LOG) ||
                            level.getBlockState(checkPos).is(Blocks.ACACIA_LOG)) {
                        return Stream.of(pos);
                    }
                }
            }
        }
        return Stream.empty();
    }
}