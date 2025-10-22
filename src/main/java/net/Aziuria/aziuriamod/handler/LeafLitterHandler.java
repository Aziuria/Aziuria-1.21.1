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
            int placements = 3 + random.nextInt(8); // 3 to 10 placements

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

                // Check for nearby leaves instead of logs
                if (isNearLeaves(level, surfacePos, 6)) {
                    if (chance < 1.0f) {
                        level.setBlock(surfacePos, ModBlocks.LEAF_LITTER.get().defaultBlockState(), 3);
                    }
                }
            }
        });
    }

    private static boolean isNearLeaves(ServerLevel level, BlockPos pos, int radius) {
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -4; dy <= 9; dy++) {  // Changed from -2..5 to -4..9
                for (int dz = -radius; dz <= radius; dz++) {
                    BlockPos checkPos = pos.offset(dx, dy, dz);
                    BlockState state = level.getBlockState(checkPos);

                    if (isLeafBlock(state)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isLeafBlock(BlockState state) {
        return state.is(Blocks.OAK_LEAVES)
                || state.is(Blocks.BIRCH_LEAVES)
                || state.is(Blocks.SPRUCE_LEAVES)
                || state.is(Blocks.JUNGLE_LEAVES)
                || state.is(Blocks.ACACIA_LEAVES)
                || state.is(Blocks.DARK_OAK_LEAVES)
                || state.is(Blocks.MANGROVE_LEAVES)
                || state.is(Blocks.CHERRY_LEAVES)
                // --- Aziuria Modded Leaves ---
                || state.is(ModBlocks.APPLE_LEAVES.get())
                || state.is(ModBlocks.PEAR_LEAVES.get())
                || state.is(ModBlocks.AVOCADO_LEAVES.get())
                || state.is(ModBlocks.CHERRY_LEAVES.get())
                || state.is(ModBlocks.BANANA_LEAVES.get())
                || state.is(ModBlocks.ORANGE_LEAVES.get());
    }
}