package net.Aziuria.aziuriamod.worldgen.rules;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.Aziuria.aziuriamod.worldgen.rules.registry.ModTrunkPlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class FallenLogTrunkPlacer extends TrunkPlacer {

    private final int minLength;
    private final int maxLength;

    public static final MapCodec<FallenLogTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    RecordCodecBuilder.point(3),
                    RecordCodecBuilder.point(7)
            ).apply(instance, FallenLogTrunkPlacer::new)
    );

    public FallenLogTrunkPlacer(int minLength, int maxLength) {
        super(0, 0, 0);
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.FALLEN_LOG.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level,
                                                            BiConsumer<BlockPos, BlockState> blockSetter,
                                                            RandomSource random,
                                                            int freeTreeHeight,
                                                            BlockPos startPos,
                                                            TreeConfiguration config) {

        int length = minLength + random.nextInt(maxLength - minLength + 1);
        Direction dir = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        boolean reverse = random.nextBoolean();

        if (!canPlaceFallenLog(level, startPos, dir, length, reverse)) {
            return new ArrayList<>();
        }

        // PLACE STUMP
        if (isValidGround(level, startPos.below())) {
            BlockState stumpState = config.trunkProvider.getState(random, startPos);
            if (stumpState.hasProperty(RotatedPillarBlock.AXIS)) {
                stumpState = stumpState.setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y);
            }
            blockSetter.accept(startPos, stumpState);
        }

        // PLACE LOGS WITH 1-BLOCK GAP
        for (int i = 1; i <= length; i++) {
            int offset = reverse ? -i - 1 : i + 1;
            BlockPos pos = startPos.relative(dir, offset);
            BlockState state = config.trunkProvider.getState(random, pos);
            if (state.hasProperty(RotatedPillarBlock.AXIS)) {
                state = state.setValue(RotatedPillarBlock.AXIS, dir.getAxis());
            }
            blockSetter.accept(pos, state);
        }

        return new ArrayList<>();
    }

    private boolean isValidGround(LevelSimulatedReader level, BlockPos pos) {
        return level.isStateAtPosition(pos, bs -> bs.getBlock() == Blocks.GRASS_BLOCK || bs.getBlock() == Blocks.DIRT);
    }

    private boolean isAir(LevelSimulatedReader level, BlockPos pos) {
        return level.isStateAtPosition(pos, bs -> bs.isAir());
    }

    // Flat ground + 3x3 neighborhood + log space check
    private boolean canPlaceFallenLog(LevelSimulatedReader level, BlockPos startPos, Direction dir, int length, boolean reverse) {
        int baseY = startPos.getY();

        for (int i = 1; i <= length + 1; i++) {
            int offset = reverse ? -i - 1 : i + 1;
            BlockPos pos = startPos.relative(dir, offset);

            // 1️⃣ Ground flatness check
            if (!isValidGround(level, pos.below()) || pos.getY() != baseY) return false;

            // 2️⃣ 3x3 neighborhood below
            for (int dx = -1; dx <= 1; dx++) {
                for (int dz = -1; dz <= 1; dz++) {
                    BlockPos neighbor = pos.offset(dx, -1, dz);
                    if (!isValidGround(level, neighbor) || neighbor.getY() != baseY - 1) return false;
                }
            }

            // 3️⃣ Check that log space is air (prevents buried logs)
            if (!isAir(level, pos)) return false;
        }

        return true;
    }
}