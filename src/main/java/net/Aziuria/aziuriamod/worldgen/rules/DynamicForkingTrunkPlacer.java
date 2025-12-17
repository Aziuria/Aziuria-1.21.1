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

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.BiConsumer;

public class DynamicForkingTrunkPlacer extends TrunkPlacer {

    private final int minorBranches;
    private final int branchLength;
    private final int branchStartOffset;
    private final int baseBranchSpread;

    // Codec for data-driven generation (default minorBranches=1)
    public static final MapCodec<DynamicForkingTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(
            inst -> trunkPlacerParts(inst).apply(inst, (baseHeight, heightRandA, heightRandB) ->
                    new DynamicForkingTrunkPlacer(baseHeight, heightRandA, heightRandB, 1, 2, 4, 2))
    );

    // Old 3-arg constructor
    public DynamicForkingTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        this(baseHeight, heightRandA, heightRandB, 1, 2, 4, 2);
    }

    // 4-arg constructor
    public DynamicForkingTrunkPlacer(int baseHeight, int heightRandA, int heightRandB, int minorBranches) {
        this(baseHeight, heightRandA, heightRandB, minorBranches, 4, 5, 4);
    }

    // 6-arg convenience constructor for registration
    public DynamicForkingTrunkPlacer(int baseHeight, int extraHeight, int baseBranchSpread, int minorBranches,
                                     int branchLength, int branchStartOffset) {
        this(baseHeight, extraHeight, 0, minorBranches, branchLength, branchStartOffset, baseBranchSpread);
    }

    // Full 7-arg constructor
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

        // Main trunk height variation
        double heightModifier = random.nextDouble();
        int actualTrunkHeight = (int) Math.round(Mth.lerp(heightModifier, freeTreeHeight, freeTreeHeight + 1));

        for (int y = 0; y < actualTrunkHeight; y++) {
            int currentY = pos.getY() + y;

            // Main trunk log (vertical)
            if (this.placeLog(level, blockSetter, random, mutablePos.set(trunkX, currentY, trunkZ), config, Direction.Axis.Y)) {
                trunkTop = OptionalInt.of(currentY + 1);
            }

            // Minor branches start above branchStartOffset
            if (y >= branchStartOffset && y < actualTrunkHeight - 1) {
                int branchCount = random.nextInt(this.minorBranches + 1);
                for (int b = 0; b <= branchCount; b++) {
                    Direction branchDir = Direction.Plane.HORIZONTAL.getRandomDirection(random);
                    placeMinorBranch(level, blockSetter, random, mutablePos,
                            trunkX, currentY, trunkZ, branchDir, branchLength, config, foliage);
                }
            }
        }

        if (trunkTop.isPresent()) {
            foliage.add(new FoliagePlacer.FoliageAttachment(
                    new BlockPos(trunkX, trunkTop.getAsInt(), trunkZ),
                    1, false
            ));
        }

        // Extra fork like vanilla ForkingTrunkPlacer
        int forkHeight = actualTrunkHeight / 2 + random.nextInt(2);
        Direction forkDir = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        int forkX = trunkX;
        int forkZ = trunkZ;
        OptionalInt forkTop = OptionalInt.empty();
        int forkLength = 1 + random.nextInt(3);

        for (int y = forkHeight; y < actualTrunkHeight && forkLength > 0; y++, forkLength--) {
            int currentY = pos.getY() + y;
            forkX += forkDir.getStepX();
            forkZ += forkDir.getStepZ();

            if (this.placeLog(level, blockSetter, random, mutablePos.set(forkX, currentY, forkZ), config,
                    forkDir.getAxis())) {
                forkTop = OptionalInt.of(currentY + 1);
            }
        }

        if (forkTop.isPresent()) {
            foliage.add(new FoliagePlacer.FoliageAttachment(
                    new BlockPos(forkX, forkTop.getAsInt(), forkZ),
                    0, false
            ));
        }

        return foliage;
    }

    private void placeMinorBranch(LevelSimulatedReader level,
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

        for (int i = 0; i < length; i++) {
            x += direction.getStepX();
            z += direction.getStepZ();
            if (i % 2 == 0) y += 1;

            Direction.Axis axis = direction.getAxis();

            if (this.placeLog(level, blockSetter, random, mutablePos.set(x, y, z), config, axis)) {
                branchTop = OptionalInt.of(y + 1);
            }
        }

        if (branchTop.isPresent()) {
            foliage.add(new FoliagePlacer.FoliageAttachment(
                    new BlockPos(x, branchTop.getAsInt(), z),
                    0, false
            ));
        }
    }

    // Overloaded placeLog to handle axis orientation
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