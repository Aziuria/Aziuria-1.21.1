package net.Aziuria.aziuriamod.handler;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FastLeafDecayHandler {

    private static final Map<ServerLevel, Map<BlockPos, Integer>> LEAF_TICK_MAP = new HashMap<>();
    private static final int DECAY_TIME_TICKS = 20;

    public static void queueLeafForDecay(ServerLevel level, BlockPos pos) {
        LEAF_TICK_MAP.computeIfAbsent(level, l -> new HashMap<>()).put(pos.immutable(), 0);
    }

    public static void onServerTick(ServerTickEvent.Post event) {
        if (event.getServer() == null) return;

        for (ServerLevel level : event.getServer().getAllLevels()) {
            Map<BlockPos, Integer> map = LEAF_TICK_MAP.get(level);
            if (map == null || map.isEmpty()) continue;

            // Limit how many leaves decay per tick to spread decay smoothly
            int batchLimit = Math.max(1, map.size() / 10); // Adjust divisor for smoothness
            int removedCount = 0;

            List<BlockPos> toRemove = new ArrayList<>();
            Iterator<Map.Entry<BlockPos, Integer>> iterator = map.entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry<BlockPos, Integer> entry = iterator.next();
                BlockPos pos = entry.getKey();
                int ticks = entry.getValue() + 1;

                if (ticks >= DECAY_TIME_TICKS && removedCount < batchLimit) {
                    BlockState state = level.getBlockState(pos);
                    if (state.getBlock() instanceof LeavesBlock) {
                        if (state.getValue(BlockStateProperties.DISTANCE) >= 7 &&
                                !state.getValue(BlockStateProperties.PERSISTENT)) {
                            // Remove block silently: no sound or particles
                            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);

                            // Play leaf break sound quietly (10% volume)
                            level.playSound(null, pos, SoundEvents.GRASS_BREAK, SoundSource.BLOCKS, 0.0f, 1.0f);
                        }
                    }
                    toRemove.add(pos);
                    removedCount++;
                } else {
                    entry.setValue(ticks);
                }
            }

            // Remove decayed leaves from the map after iteration
            toRemove.forEach(map::remove);
        }
    }
}