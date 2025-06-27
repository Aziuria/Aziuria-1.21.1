package net.Aziuria.aziuriamod.handler;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.util.*;

public class FastLeafDecayHandler {

    private static final Map<ServerLevel, Map<BlockPos, Integer>> LEAF_TICK_MAP = new HashMap<>();
    private static final int DECAY_TIME_TICKS = 10; // shorter for smoother visible decay
    private static final int MAX_DECAY_PER_TICK = 5; // max leaves removed per tick per level

    public static void queueLeafForDecay(ServerLevel level, BlockPos pos) {
        LEAF_TICK_MAP.computeIfAbsent(level, l -> new HashMap<>()).put(pos.immutable(), 0);
    }

    public static void onServerTick(ServerTickEvent.Post event) {
        if (event.getServer() == null) return;

        for (ServerLevel level : event.getServer().getAllLevels()) {
            Map<BlockPos, Integer> map = LEAF_TICK_MAP.get(level);
            if (map == null || map.isEmpty()) continue;

            int removedCount = 0;
            List<BlockPos> toRemove = new ArrayList<>();
            List<BlockPos> toQueueNext = new ArrayList<>();

            Iterator<Map.Entry<BlockPos, Integer>> iterator = map.entrySet().iterator();
            while (iterator.hasNext() && removedCount < MAX_DECAY_PER_TICK) {
                Map.Entry<BlockPos, Integer> entry = iterator.next();
                BlockPos pos = entry.getKey();
                int ticks = entry.getValue() + 1;

                if (ticks >= DECAY_TIME_TICKS) {
                    BlockState state = level.getBlockState(pos);
                    if (state.getBlock() instanceof LeavesBlock) {
                        if (state.getValue(BlockStateProperties.DISTANCE) >= 7 &&
                                !state.getValue(BlockStateProperties.PERSISTENT)) {

                            // Spawn crumble particles before removal
                            level.sendParticles(
                                    new BlockParticleOption(ParticleTypes.BLOCK, state),
                                    pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                                    8, // particle count
                                    0.25, 0.25, 0.25, // x, y, z spread
                                    0.02 // speed
                            );

                            // Remove block
                            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);

                            // Play leaf break sound quietly (0.02f or 0.0f)
                            level.playSound(null, pos, SoundEvents.GRASS_BREAK, SoundSource.BLOCKS, 0.0f, 1.0f);

                            // Spread decay to adjacent leaves
                            for (BlockPos nearby : BlockPos.betweenClosed(pos.offset(-1, -1, -1), pos.offset(1, 1, 1))) {
                                if (nearby.equals(pos)) continue;
                                BlockState nearbyState = level.getBlockState(nearby);
                                if (nearbyState.getBlock() instanceof LeavesBlock &&
                                        !nearbyState.getValue(BlockStateProperties.PERSISTENT) &&
                                        nearbyState.getValue(BlockStateProperties.DISTANCE) >= 7) {
                                    toQueueNext.add(nearby.immutable());
                                }
                            }

                            removedCount++;
                        }
                    }
                    toRemove.add(pos);
                } else {
                    entry.setValue(ticks);
                }
            }

            toRemove.forEach(map::remove);
            toQueueNext.forEach(p -> queueLeafForDecay(level, p));
        }
    }
}