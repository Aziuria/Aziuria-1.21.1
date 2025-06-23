package net.Aziuria.aziuriamod.block.world;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.TickTask;

public class DelayedExecutor {

    /**
     * Schedules a task to run after a delay in server ticks.
     *
     * @param level      The server level where the task will run.
     * @param delayTicks Delay in ticks before running the task.
     * @param task       The Runnable task to execute.
     */
    public static void schedule(ServerLevel level, long delayTicks, Runnable task) {
        MinecraftServer server = level.getServer();
        if (server == null) {
            System.err.println("[DelayedExecutor] Server instance is null, cannot schedule task.");
            return;
        }

        if (delayTicks < 0) {
            System.err.println("[DelayedExecutor] Negative delayTicks provided, running task immediately.");
            delayTicks = 0;
        }

        // Calculate the tick to run the task on
        int executeTick = server.getTickCount() + (int) delayTicks;

        server.tell(new TickTask(executeTick, () -> {
            try {
                System.out.println("[DelayedExecutor] Running delayed task at server tick " + executeTick);
                task.run();
            } catch (Exception e) {
                System.err.println("[DelayedExecutor] Exception during delayed task execution:");
                e.printStackTrace();
            }
        }));

        // --- ▲▲▲ Scheduled delayed task at tick: " + executeTick + " ▲▲▲ ---
    }
}