package net.Aziuria.aziuriamod.island.util;

import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DelayedExecutor {

    private static final List<ScheduledTask> TASKS = new LinkedList<>();

    public static void schedule(ServerLevel level, long delayTicks, Runnable task) {
        if (level == null) {
            System.err.println("[DelayedExecutor] ServerLevel is null, cannot schedule task.");
            return;
        }

        if (delayTicks < 0) {
            System.err.println("[DelayedExecutor] Negative delayTicks provided, running task immediately.");
            delayTicks = 0;
        }

        long executeTick = level.getGameTime() + delayTicks;

        synchronized (TASKS) {
            TASKS.add(new ScheduledTask(level, executeTick, task));
        }
    }

    /** NEW: Schedule with chunk safety to prevent too-early tree/litter/animal spawning */
    public static void scheduleWithChunkSafety(ServerLevel level, long delayTicks, Runnable task) {
        if (level == null) return;

        // Estimate chunk load and add dynamic safety
        long safetyBuffer = estimateWorldLoad(level) * 3L; // ~3 ticks per loaded chunk
        long executeTick = level.getGameTime() + delayTicks + safetyBuffer;

        synchronized (TASKS) {
            TASKS.add(new ScheduledTask(level, executeTick, task));
        }
        System.out.println("[DelayedExecutor] Task scheduled with chunk safety for tick " + executeTick);
    }

    /** Estimate world load to add safety buffer */
    private static int estimateWorldLoad(ServerLevel level) {
        try {
            ServerChunkCache chunkCache = level.getChunkSource();
            ChunkMap chunkMap = chunkCache.chunkMap;
            int loaded = chunkCache.getLoadedChunksCount();
            int ticking = chunkCache.getTickingGenerated();
            return Math.min(Math.max(loaded, ticking), 200); // clamp
        } catch (Exception e) {
            System.err.println("[DelayedExecutor] Failed to estimate world load, using fallback.");
            return 50; // fallback
        }
    }

    /**
     * Call this method on each server tick from your NeoForge tick event listener.
     */
    public static void tick(ServerLevel level) {
        synchronized (TASKS) {
            Iterator<ScheduledTask> iterator = TASKS.iterator();
            long currentTick = level.getGameTime();

            while (iterator.hasNext()) {
                ScheduledTask scheduled = iterator.next();
                if (scheduled.level == level && currentTick >= scheduled.executeTick) {
                    try {
                        scheduled.task.run();
                    } catch (Exception e) {
                        System.err.println("[DelayedExecutor] Exception during delayed task execution:");
                        e.printStackTrace();
                    }
                    iterator.remove();
                }
            }
        }
    }

    private static class ScheduledTask {
        final ServerLevel level;
        final long executeTick;
        final Runnable task;

        ScheduledTask(ServerLevel level, long executeTick, Runnable task) {
            this.level = level;
            this.executeTick = executeTick;
            this.task = task;
        }
    }
}