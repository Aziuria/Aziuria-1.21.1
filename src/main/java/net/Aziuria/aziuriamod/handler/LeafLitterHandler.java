package net.Aziuria.aziuriamod.handler;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.Heightmap;
import net.Aziuria.aziuriamod.block.ModBlocks; // Ensure LEAF_LITTER is registered properly

public class LeafLitterHandler {

    public static void spreadLeafLitter(ServerLevel level) {
        RandomSource random = level.getRandom();

        level.players().forEach(player -> {
            int placements = 1 + random.nextInt(5); // 1 to 5 placements

            for (int i = 0; i < placements; i++) {
                // Choose a random nearby position within 100 blocks
                BlockPos randomOffset = player.blockPosition().offset(
                        random.nextInt(-100, 101),
                        0,
                        random.nextInt(-100, 101)
                );

                // Get the surface position at this location
                BlockPos surfacePos = level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, randomOffset);
                BlockState groundState = level.getBlockState(surfacePos.below());
                BlockState surfaceState = level.getBlockState(surfacePos);

                // Only allow if air on top and grass block below
                if (!surfaceState.isAir() || !groundState.is(Blocks.GRASS_BLOCK)) {
                    continue;
                }

                // Validate biome
                ResourceKey<Biome> biomeKey = level.getBiome(surfacePos).unwrapKey().orElse(null);
                if (biomeKey == null) continue;

                ResourceLocation biomeID = biomeKey.location();
                String biomePath = biomeID.getPath();
                if (biomePath.contains("nether") || biomePath.contains("end")) continue;

                float chance = random.nextFloat();

                // Check for nearby trees
                if (isNearTree(level, surfacePos, 9)) {
                    if (chance < 1.0f) {
                        level.setBlock(surfacePos, ModBlocks.LEAF_LITTER.get().defaultBlockState(), 3);
                    }
                }
            }
        });
    }

    private static boolean isNearTree(ServerLevel level, BlockPos pos, int radius) {
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -2; dy <= 5; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    BlockPos checkPos = pos.offset(dx, dy, dz);
                    BlockState state = level.getBlockState(checkPos);

                    if (isLogBlock(state)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isLogBlock(BlockState state) {
        return state.is(Blocks.OAK_LOG)
                || state.is(Blocks.BIRCH_LOG)
                || state.is(Blocks.SPRUCE_LOG)
                || state.is(Blocks.JUNGLE_LOG)
                || state.is(Blocks.ACACIA_LOG)
                || state.is(Blocks.DARK_OAK_LOG)
                || state.is(Blocks.MANGROVE_LOG)
                || state.is(Blocks.CHERRY_LOG);
    }
}