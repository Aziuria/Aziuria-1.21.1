package net.Aziuria.aziuriamod.island.generation;

import net.Aziuria.aziuriamod.block.ModBlocks;
import net.Aziuria.aziuriamod.island.decoration.DecorationGenerator;
import net.Aziuria.aziuriamod.island.util.DelayedExecutor;
import net.Aziuria.aziuriamod.island.animals.AnimalSpawner;
import net.Aziuria.aziuriamod.island.decoration.TreeGenerator;
import net.Aziuria.aziuriamod.island.decoration.TreeLitterSpawner;
import net.Aziuria.aziuriamod.island.type.IslandBiomeType;
import net.Aziuria.aziuriamod.island.type.IslandType;
import net.Aziuria.aziuriamod.island.util.BlockBatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.synth.SimplexNoise;

import java.util.ArrayList;
import java.util.List;

public class IslandGenerator {

    private static final double FOREST_THRESHOLD = 0.25;
    private static final double TREE_SPAWN_CHANCE = 0.07;

    public static void generateIsland(ServerLevel level, BlockPos center, IslandType type, IslandBiomeType biomeType, long seed) {
        if (level == null || center == null || type == null || biomeType == null) {
            throw new IllegalArgumentException("Level, center, type or biomeType cannot be null");
        }

        long combinedSeed = seed ^ (center.getX() * 341873128712L) ^ (center.getZ() * 132897987541L);
        RandomSource random = RandomSource.create(combinedSeed);

        int width = type.getRandomWidth(random) + 50;
        int length = type.getRandomLength(random) + 50;
        int maxHeight = type.getMaxHeight();

        BlockBatcher batcher = new BlockBatcher(level, 500);
        SimplexNoise noise = new SimplexNoise(random);

        // Store exact sapling candidate spots (must be soil height + 1)
        List<BlockPos> treePositions = new ArrayList<>();

        for (int x = -width / 2; x <= width / 2; x++) {
            for (int z = -length / 2; z <= length / 2; z++) {
                int worldX = center.getX() + x;
                int worldZ = center.getZ() + z;

                int terrainHeight = level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, worldX, worldZ);
                int oceanFloorHeight = level.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, worldX, worldZ);
                int waterHeight = terrainHeight;

                int maxIslandTop = waterHeight + (int) (maxHeight * 0.8);
                int maxIslandHeight = maxIslandTop - oceanFloorHeight;
                if (maxIslandHeight <= 0) continue;

                double nx = x / (double) (width / 2);
                double nz = z / (double) (length / 2);

                // Smooth radial falloff (cubic)
                double dist = Math.sqrt(nx * nx + nz * nz);
                double radialFalloff = Math.max(0, 1 - Math.pow(dist, 3));

                // Multi-octave noise for natural terrain
                double noiseValue = getMultiOctaveNoise(noise, x * 0.05, z * 0.05);

                // Combine radial falloff and noise for shape factor
                double noiseInfluence = 0.15;
                double noiseFade = Math.min(Math.pow(dist * 2, 2), 1.0);  // ---> Noise fades from 0 at center to full at ~1/3 radius
                double shapeFactor = Math.min(Math.max(radialFalloff + noiseValue * noiseInfluence * noiseFade, 0.0), 1.0);

                int columnHeight = Math.max((int) (shapeFactor * maxIslandHeight), 2);
                if (columnHeight < 1) continue;

                int baseY = oceanFloorHeight - 2;
                int topY = baseY + columnHeight;


                //seabed stuff
                double maxLowering = 3.0;
                double seabedLoweringExact = (1 - shapeFactor) * maxLowering;
                double easing = Math.pow(seabedLoweringExact, 3);
                int seabedLowering = (int) Math.round(easing);

                int seabedTopY = baseY - seabedLowering;

// Sample average ocean floor height around (worldX, worldZ)
                int sampleRadius = 2;
                int sumOceanFloor = 0;
                int count = 0;
                for (int dx = -sampleRadius; dx <= sampleRadius; dx++) {
                    for (int dz = -sampleRadius; dz <= sampleRadius; dz++) {
                        int sampleX = worldX + dx;
                        int sampleZ = worldZ + dz;
                        int sampleHeight = level.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, sampleX, sampleZ);
                        sumOceanFloor += sampleHeight;
                        count++;
                    }
                }
                int avgOceanFloor = sumOceanFloor / count;

// Clamp seabedTopY to avoid big cliffs
                seabedTopY = Math.max(seabedTopY, avgOceanFloor - 3);
                seabedTopY = Math.min(seabedTopY, avgOceanFloor + 1);

// Fill seabed blocks smoothly
                for (int y = seabedTopY; y < avgOceanFloor - 3; y++) {
                    BlockPos seabedPos = new BlockPos(worldX, y, worldZ);
                    double blend = random.nextDouble();
                    if (blend < 0.25) batcher.setBlock(seabedPos, Blocks.GRAVEL.defaultBlockState());
                    else if (blend < 0.5) batcher.setBlock(seabedPos, Blocks.SAND.defaultBlockState());
                    else if (blend < 0.75) batcher.setBlock(seabedPos, Blocks.DIRT.defaultBlockState());
                    else batcher.setBlock(seabedPos, Blocks.CLAY.defaultBlockState());
                }

                Block topBlock = Blocks.GRASS_BLOCK;

                // Column (from baseY to topY)
                for (int y = baseY; y <= topY; y++) {
                    BlockPos pos = new BlockPos(worldX, y, worldZ);
                    if (y == topY) {
                        if (shapeFactor < 0.15 || y <= waterHeight - 1) {
                            topBlock = Blocks.SAND;
                        } else {
                            switch (biomeType) {
                                case DESERT, BEACH -> topBlock = Blocks.SAND;
                                case SNOWY -> topBlock = Blocks.SNOW_BLOCK;
                                case SWAMP -> topBlock = Blocks.DIRT;
                                case MUSHROOM -> topBlock = Blocks.MYCELIUM;
                                default -> topBlock = Blocks.GRASS_BLOCK;
                            }
                        }
                        batcher.setBlock(pos, topBlock.defaultBlockState());
                    } else if (y > topY - 3) {
                        batcher.setBlock(pos, Blocks.DIRT.defaultBlockState());
                    } else {
                        if (random.nextDouble() < 0.005) { // 0.5% per column chance
                            Block oreBlock;
                            double oreTypeRoll = random.nextDouble();
                            int veinSize = 4; // default vein size

                            if (oreTypeRoll < 0.3) { // 30%
                                oreBlock = Blocks.COAL_ORE;
                                veinSize = 8 + random.nextInt(5); // 8–12
                            } else if (oreTypeRoll < 0.5) { // 20%
                                oreBlock = Blocks.IRON_ORE;
                                veinSize = 4 + random.nextInt(6); // 4–9
                            } else if (oreTypeRoll < 0.6) { // 10%
                                oreBlock = Blocks.COPPER_ORE;
                            } else if (oreTypeRoll < 0.7) { // 10%
                                oreBlock = Blocks.REDSTONE_ORE;
                                veinSize = 4 + random.nextInt(3); // 4–6
                            } else if (oreTypeRoll < 0.75) { // 5%
                                oreBlock = Blocks.LAPIS_ORE;
                            } else if (oreTypeRoll < 0.8) { // 5%
                                oreBlock = Blocks.GOLD_ORE;
                            } else if (oreTypeRoll < 0.82) { // 2%
                                oreBlock = Blocks.DIAMOND_ORE;
                                veinSize = 2; // fixed
                            } else if (oreTypeRoll < 0.84) { // 2%
                                oreBlock = ModBlocks.SULPHUR_ORE.get();
                                veinSize = 2; // fixed
                            } else if (oreTypeRoll < 0.86) { // 2%
                                oreBlock = ModBlocks.POTASSIUM_ORE.get();
                                veinSize = 2; // fixed
                            } else if (oreTypeRoll < 0.91) { // 5%
                                oreBlock = Blocks.EMERALD_ORE;
                                veinSize = 1; // single block
                            } else { // 9%
                                oreBlock = Blocks.STONE;
                            }

                            // Starting position for vein
                            BlockPos veinPos = pos;

                            for (int i = 0; i < veinSize; i++) {
                                if (veinPos.getY() < topY - 3 && veinPos.getY() >= baseY) {
                                    batcher.setBlock(veinPos, oreBlock.defaultBlockState());
                                }

                                // Move veinPos by 1 block in a random direction (X, Y, or Z)
                                int direction = random.nextInt(6); // 0-5 for 6 directions
                                switch (direction) {
                                    case 0 -> veinPos = veinPos.offset(1, 0, 0);   // +X
                                    case 1 -> veinPos = veinPos.offset(-1, 0, 0);  // -X
                                    case 2 -> veinPos = veinPos.offset(0, 1, 0);   // +Y
                                    case 3 -> veinPos = veinPos.offset(0, -1, 0);  // -Y
                                    case 4 -> veinPos = veinPos.offset(0, 0, 1);   // +Z
                                    case 5 -> veinPos = veinPos.offset(0, 0, -1);  // -Z
                                }
                            }
                        } else {
                            batcher.setBlock(pos, Blocks.STONE.defaultBlockState());
                        }
                    }
                }

                // Fill gaps below baseY to oceanFloorHeight with stone if empty
                for (int y = baseY - 1; y > oceanFloorHeight - 5; y--) {
                    BlockPos fillPos = new BlockPos(worldX, y, worldZ);
                    if (level.isEmptyBlock(fillPos)) {
                        batcher.setBlock(fillPos, Blocks.STONE.defaultBlockState());
                    }
                }

                batcher.flush();

                /// --- Decorations and tree candidate collection fixed ---
                BlockPos soilPos = new BlockPos(worldX, topY, worldZ); // soil
                BlockPos treePos = soilPos.above();                    // tree trunk starts above soil

// Decorations can use all soil types
                if (DecorationGenerator.isSoil(topBlock)) {
                    DecorationGenerator.placeDecorations(level, soilPos, random, biomeType, batcher, topBlock);
                }

// Trees only spawn on "good soil" (exclude sand)
                boolean isGoodSoil = topBlock == Blocks.GRASS_BLOCK
                        || topBlock == Blocks.DIRT
                        || topBlock == Blocks.COARSE_DIRT
                        || topBlock == Blocks.PODZOL
                        || topBlock == Blocks.MYCELIUM;

                if (isGoodSoil) {
                    double forestNoise = noise.getValue(worldX * 0.02, worldZ * 0.02, 0);
                    if (forestNoise > FOREST_THRESHOLD && random.nextDouble() < TREE_SPAWN_CHANCE) {
                        treePositions.add(treePos); // trunk starts above soil
                    }
                }
            }
        }

        batcher.flush();

        batcher.flush();

        long delayTicks;
        switch (type) {
            case SMALL -> delayTicks = 160L;   // Small islands spawn trees faster
            case MEDIUM -> delayTicks = 600L;  // Medium islands moderate delay
            case LARGE -> delayTicks = 5100L;   // Large islands spawn trees slower
            default -> delayTicks = 600L;
        }

// Schedule tree generation after delay based on island size
        DelayedExecutor.schedule(level, delayTicks, () -> {
            for (BlockPos pos : treePositions) {
                TreeGenerator.generateTree(level, pos, biomeType, random);
                TreeLitterSpawner.spawnLitterAroundTree(level, level.getChunkSource().getGenerator(), pos, random, biomeType);
            }
        });


        DelayedExecutor.schedule(level, delayTicks, () -> {
            Player player = level.getNearestPlayer(center.getX(), center.getY(), center.getZ(), 40, false);
            if (player instanceof ServerPlayer serverPlayer) {
                AnimalSpawner.spawnAnimalsNearPlayer(
                        level,
                        serverPlayer,
                        new java.util.Random(random.nextLong()),
                        type   // Use the instance field here
                );
            }
        });

    }

    private static double getMultiOctaveNoise(SimplexNoise noise, double x, double z) {
        double total = 0;
        double frequency = 1.0;
        double amplitude = 1.0;
        double maxValue = 0;

        for (int octave = 0; octave < 4; octave++) {
            total += noise.getValue(x * frequency, z * frequency, 0) * amplitude;
            maxValue += amplitude;
            amplitude *= 0.5;
            frequency *= 2;
        }
        return total / maxValue;
    }

}
