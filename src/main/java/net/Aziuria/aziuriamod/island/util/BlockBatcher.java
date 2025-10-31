package net.Aziuria.aziuriamod.island.util;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

import java.util.*;

public class BlockBatcher {

    private static final Map<ServerLevel, List<BlockBatcher>> ACTIVE_BATCHERS = new HashMap<>();

    private final ServerLevel level;
    private final int blocksPerTick;
    private final Queue<BlockPlacement> queue = new LinkedList<>();
    private boolean isRunning = false;

    public BlockBatcher(ServerLevel level, int blocksPerTick) {
        this.level = level;
        this.blocksPerTick = blocksPerTick;
    }

    public void setBlock(BlockPos pos, BlockState state) {
        queue.add(new BlockPlacement(pos, state));
    }

    public BlockState getBlockState(BlockPos pos, ServerLevel level) {
        for (BlockPlacement placement : queue) {
            if (placement.pos.equals(pos)) return placement.state;
        }
        return level.getBlockState(pos);
    }

    public void flush() {
        if (!isRunning && !queue.isEmpty()) {
            isRunning = true;

            ACTIVE_BATCHERS.computeIfAbsent(level, k -> new ArrayList<>()).add(this);

        }
    }


    private void processQueue() {
        int count = 0;

        while (!queue.isEmpty() && count < blocksPerTick) {
            BlockPlacement placement = queue.poll();
            if (placement != null) {
                level.setBlockAndUpdate(placement.pos, placement.state);
                count++;
            }
        }

        if (queue.isEmpty()) {
            isRunning = false;
            ACTIVE_BATCHERS.getOrDefault(level, Collections.emptyList()).remove(this);
        }

    }

    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event) {
        Level level = event.getLevel(); // ✅ CORRECT: access public field directly
        if (!(level instanceof ServerLevel serverLevel)) return;

        List<BlockBatcher> batchers = ACTIVE_BATCHERS.get(serverLevel);
        if (batchers != null && !batchers.isEmpty()) {
            for (BlockBatcher batcher : new ArrayList<>(batchers)) {
                batcher.processQueue();
            }
        }
    }

    private static class BlockPlacement {
        final BlockPos pos;
        final BlockState state;

        BlockPlacement(BlockPos pos, BlockState state) {
            this.pos = pos;
            this.state = state;
        }
    }
}