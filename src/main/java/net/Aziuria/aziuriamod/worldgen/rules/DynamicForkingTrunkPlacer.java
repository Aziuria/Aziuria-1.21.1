package net.Aziuria.aziuriamod.worldgen.rules;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.Aziuria.aziuriamod.worldgen.rules.registry.ModTrunkPlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.*;
import java.util.function.BiConsumer;

public class DynamicForkingTrunkPlacer extends TrunkPlacer {

    private final int minorBranches;
    private final int branchLength;
    private final int branchStartOffset;
    private final int baseBranchSpread;
    private final int minVerticalSpacing = 4;

    public static final MapCodec<DynamicForkingTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(
            inst -> trunkPlacerParts(inst).apply(inst,
                    (baseHeight, heightRandA, heightRandB) ->
                            new DynamicForkingTrunkPlacer(baseHeight, heightRandA, heightRandB, 1, 6, 4, 2))
    );

    public DynamicForkingTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        this(baseHeight, heightRandA, heightRandB, 1, 6, 4, 2);
    }

    public DynamicForkingTrunkPlacer(int baseHeight, int heightRandA, int heightRandB, int minorBranches) {
        this(baseHeight, heightRandA, heightRandB, minorBranches, 6, 4, 4);
    }

    public DynamicForkingTrunkPlacer(int baseHeight, int extraHeight, int baseBranchSpread, int minorBranches,
                                     int branchLength, int branchStartOffset) {
        this(baseHeight, extraHeight, 0, minorBranches, branchLength, branchStartOffset, baseBranchSpread);
    }

    public DynamicForkingTrunkPlacer(int baseHeight, int heightRandA, int heightRandB,
                                     int minorBranches, int branchLength, int branchStartOffset, int baseBranchSpread) {
        super(baseHeight, heightRandA, heightRandB);
        this.minorBranches = minorBranches;
        this.branchLength = branchLength;
        this.branchStartOffset = branchStartOffset;
        this.baseBranchSpread = baseBranchSpread;
    }

    public int getMinorBranches() { return minorBranches; }
    public int getBranchLength() { return branchLength; }
    public int getBranchStartOffset() { return branchStartOffset; }
    public int getBaseBranchSpread() { return baseBranchSpread; }

    @Override
    protected TrunkPlacerType<? extends TrunkPlacer> type() {
        return ModTrunkPlacerTypes.DYNAMIC_FORKING.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level,
                                                            BiConsumer<BlockPos, BlockState> blockSetter,
                                                            RandomSource random,
                                                            int freeTreeHeight,
                                                            BlockPos pos,
                                                            TreeConfiguration config) {
        setDirtAt(level, blockSetter, random, pos.below(), config);
        List<FoliagePlacer.FoliageAttachment> foliage = new ArrayList<>();
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

        int trunkX = pos.getX();
        int trunkZ = pos.getZ();
        OptionalInt trunkTop = OptionalInt.empty();

        Map<Direction, Integer> lastBranchY = new HashMap<>();
        for (Direction dir : Direction.Plane.HORIZONTAL) lastBranchY.put(dir, -minVerticalSpacing);

        double heightModifier = random.nextDouble();
        int actualTrunkHeight = (int) Math.round(Mth.lerp(heightModifier, freeTreeHeight, freeTreeHeight + 1));

        for (int y = 0; y < actualTrunkHeight; y++) {
            int currentY = pos.getY() + y;

            // Place trunk log
            if (this.placeLog(level, blockSetter, random,
                    mutablePos.set(trunkX, currentY, trunkZ),
                    config, Direction.Axis.Y)) {
                trunkTop = OptionalInt.of(currentY + 1);
            }

            // Place minor branch (max 1 per level, leave top 3 blocks free)
            if (y >= branchStartOffset && y < actualTrunkHeight - 3) {
                List<Direction> validDirs = new ArrayList<>();
                for (Direction dir : Direction.Plane.HORIZONTAL) {
                    if (currentY - lastBranchY.get(dir) >= minVerticalSpacing) {
                        validDirs.add(dir);
                    }
                }

                if (!validDirs.isEmpty()) {
                    Direction branchDir = validDirs.get(random.nextInt(validDirs.size()));
                    lastBranchY.put(branchDir, currentY);

                    placeMinorBranchCurvy(
                            level, blockSetter, random, mutablePos,
                            trunkX, currentY, trunkZ,
                            branchDir, branchLength,
                            config, foliage
                    );
                }
            }
        }

        if (trunkTop.isPresent()) {
            foliage.add(new FoliagePlacer.FoliageAttachment(
                    new BlockPos(trunkX, trunkTop.getAsInt(), trunkZ),
                    1, false
            ));
        }

        return foliage;
    }

    private void placeMinorBranchCurvy(LevelSimulatedReader level,
                                       BiConsumer<BlockPos, BlockState> blockSetter,
                                       RandomSource random,
                                       BlockPos.MutableBlockPos mutablePos,
                                       int startX, int startY, int startZ,
                                       Direction direction,
                                       int length,
                                       TreeConfiguration config,
                                       List<FoliagePlacer.FoliageAttachment> foliage) {

        int x = startX;
        int y = startY;
        int z = startZ;
        OptionalInt branchTop = OptionalInt.empty();

        int remainingLength = length;
        Direction currentDir = direction;

        while (remainingLength > 0) {
            int segmentLength = 1 + random.nextInt(2);
            for (int i = 0; i < segmentLength && remainingLength > 0; i++, remainingLength--) {
                // Move in main direction
                x += currentDir.getStepX();
                z += currentDir.getStepZ();

                // Random sideways wiggle
                if (random.nextFloat() < 0.3f) {
                    Direction side = random.nextBoolean() ? currentDir.getClockWise() : currentDir.getCounterClockWise();
                    x += side.getStepX();
                    z += side.getStepZ();
                }

                // Random vertical movement
                if (random.nextFloat() < 0.5f) y += 1;
                else if (random.nextFloat() < 0.2f) y -= 1;

                if (this.placeLog(level, blockSetter, random, mutablePos.set(x, y, z), config, currentDir.getAxis())) {
                    branchTop = OptionalInt.of(y + 1);
                }

                // Random tiny offshoots
                if (random.nextFloat() < 0.25f) {
                    Direction twigDir = Direction.Plane.HORIZONTAL.getRandomDirection(random);
                    placeMinorBranchCurvy(level, blockSetter, random, mutablePos, x, y, z, twigDir, 1, config, foliage);
                }
            }

            // Possibly change main direction for next segment
            if (random.nextFloat() < 0.5f) {
                currentDir = random.nextBoolean() ? currentDir.getClockWise() : currentDir.getCounterClockWise();
            }
        }

        if (branchTop.isPresent()) {
            foliage.add(new FoliagePlacer.FoliageAttachment(
                    new BlockPos(x, branchTop.getAsInt(), z),
                    0, false
            ));
        }
    }

    protected boolean placeLog(LevelSimulatedReader level,
                               BiConsumer<BlockPos, BlockState> blockSetter,
                               RandomSource random,
                               BlockPos pos,
                               TreeConfiguration config,
                               Direction.Axis axis) {
        BlockState logState = config.trunkProvider.getState(random, pos)
                .setValue(RotatedPillarBlock.AXIS, axis);
        blockSetter.accept(pos, logState);
        return true;
    }
}