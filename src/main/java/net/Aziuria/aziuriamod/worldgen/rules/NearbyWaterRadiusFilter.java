package net.Aziuria.aziuriamod.worldgen.rules;

import com.mojang.serialization.MapCodec;
import net.Aziuria.aziuriamod.worldgen.rules.registry.ModPlacementModifier;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

import java.util.stream.Stream;

public class NearbyWaterRadiusFilter extends PlacementModifier {

    public static final NearbyWaterRadiusFilter INSTANCE = new NearbyWaterRadiusFilter(); // <--- Added
    public static final MapCodec<NearbyWaterRadiusFilter> CODEC = MapCodec.unit(new NearbyWaterRadiusFilter());
    public static final PlacementModifierType<NearbyWaterRadiusFilter> TYPE = () -> CODEC;

    private static final int RADIUS = 48; // 3 chunks

    public NearbyWaterRadiusFilter() {}

    @Override
    public Stream<BlockPos> getPositions(PlacementContext context, RandomSource random, BlockPos pos) {
        if (isWaterNearby(context, pos)) {
            return Stream.of(pos);
        }
        return Stream.empty();
    }

    private boolean isWaterNearby(PlacementContext context, BlockPos pos) {
        LevelReader level = context.getLevel();
        int startX = pos.getX() - RADIUS;
        int endX = pos.getX() + RADIUS;
        int startZ = pos.getZ() - RADIUS;
        int endZ = pos.getZ() + RADIUS;

        for (int x = startX; x <= endX; x++) {
            for (int z = startZ; z <= endZ; z++) {
                int surfaceY = level.getHeight(Heightmap.Types.WORLD_SURFACE, x, z);
                BlockPos checkPos = new BlockPos(x, surfaceY, z);
                BlockState state = level.getBlockState(checkPos);
                if (state.getBlock() == Blocks.WATER) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public PlacementModifierType<?> type() {
        return ModPlacementModifier.NEARBY_WATER_RADIUS_FILTER.get();
    }
}