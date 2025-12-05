package net.Aziuria.aziuriamod.worldgen.rules;

import com.google.common.collect.Lists;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.Aziuria.aziuriamod.worldgen.rules.registry.ModTrunkPlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
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

    public static final MapCodec<DynamicForkingTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(
            inst -> trunkPlacerParts(inst).apply(inst, (baseHeight, heightRandA, heightRandB) ->
                    new DynamicForkingTrunkPlacer(baseHeight, heightRandA, heightRandB, 1))
    );

    // Old 3-arg constructor (default minorBranches = 1)
    public DynamicForkingTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        this(baseHeight, heightRandA, heightRandB, 1);
    }

    // New 4-arg constructor
    public DynamicForkingTrunkPlacer(int baseHeight, int heightRandA, int heightRandB, int minorBranches) {
        super(baseHeight, heightRandA, heightRandB);
        this.minorBranches = minorBranches;
    }

    public int getMinorBranches() {
        return minorBranches;
    }

    @Override
    protected TrunkPlacerType<? extends TrunkPlacer> type() {
        return ModTrunkPlacerTypes.DYNAMIC_FORKING.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(
            LevelSimulatedReader level,
            BiConsumer<BlockPos, BlockState> blockSetter,
            RandomSource random,
            int freeTreeHeight,
            BlockPos pos,
            TreeConfiguration config
    ) {
        setDirtAt(level, blockSetter, random, pos.below(), config);
        List<FoliagePlacer.FoliageAttachment> foliage = new ArrayList<>();
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

        int trunkX = pos.getX();
        int trunkZ = pos.getZ();
        OptionalInt trunkTop = OptionalInt.empty();

        // --- Main trunk with slight random height variation ---
        double heightModifier = random.nextDouble();
        int actualTrunkHeight = (int) Math.round(Mth.lerp(heightModifier, freeTreeHeight, freeTreeHeight + 1));

        for (int y = 0; y < actualTrunkHeight; y++) {
            int currentY = pos.getY() + y;

            // place main trunk log
            if (this.placeLog(level, blockSetter, random, mutablePos.set(trunkX, currentY, trunkZ), config)) {
                trunkTop = OptionalInt.of(currentY + 1);
            }

            // --- Probabilistic minor branches ---
            if (y > 1 && y < actualTrunkHeight - 1) {
                int branchCount = random.nextInt(this.minorBranches + 1); // 0..minorBranches
                for (int b = 0; b <= branchCount; b++) {
                    Direction branchDir = Direction.Plane.HORIZONTAL.getRandomDirection(random);
                    placeMinorBranch(level, blockSetter, random, mutablePos, trunkX, currentY, trunkZ, branchDir, 1 + random.nextInt(2), config, foliage);
                }
            }
        }

        if (trunkTop.isPresent()) {
            foliage.add(new FoliagePlacer.FoliageAttachment(
                    new BlockPos(trunkX, trunkTop.getAsInt(), trunkZ),
                    1,
                    false
            ));
        }

        // --- Extra fork as in vanilla ForkingTrunkPlacer ---
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

            if (this.placeLog(level, blockSetter, random, mutablePos.set(forkX, currentY, forkZ), config)) {
                forkTop = OptionalInt.of(currentY + 1);
            }
        }

        if (forkTop.isPresent()) {
            foliage.add(new FoliagePlacer.FoliageAttachment(
                    new BlockPos(forkX, forkTop.getAsInt(), forkZ),
                    0,
                    false
            ));
        }

        return foliage;
    }

    // --- Helper: places a small minor branch ---
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

            if (this.placeLog(level, blockSetter, random, mutablePos.set(x, y, z), config)) {
                branchTop = OptionalInt.of(y + 1);
            }
        }

        if (branchTop.isPresent()) {
            foliage.add(new FoliagePlacer.FoliageAttachment(
                    new BlockPos(x, branchTop.getAsInt(), z),
                    0,
                    false
            ));
        }
    }
}