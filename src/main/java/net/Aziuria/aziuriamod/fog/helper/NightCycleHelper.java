package net.Aziuria.aziuriamod.fog.helper;

import net.minecraft.world.level.Level;

public class NightCycleHelper {
    public static final long NIGHT_START = 13000L;
    public static final long NIGHT_END = 23000L;

    /**
     * Returns true if current time-of-day is considered "night".
     * Works correctly even after sleeping or skipping time.
     */
    public static boolean isNight(Level level) {
        if (level == null) return false;
        long timeOfDay = level.getDayTime() % 24000L;
        return timeOfDay >= NIGHT_START && timeOfDay <= NIGHT_END;
    }

    /**
     * Returns true if the given timestamp (world gameTime) is during night.
     */
    public static boolean isNight(long gameTime) {
        long timeOfDay = gameTime % 24000L;
        return timeOfDay >= NIGHT_START && timeOfDay <= NIGHT_END;
    }

    /**
     * Returns the number of ticks remaining until sunrise (end of night).
     * If it’s already daytime, returns how long until next night starts.
     */
    public static long ticksUntilDay(long gameTime) {
        long timeOfDay = gameTime % 24000L;
        if (timeOfDay < NIGHT_START) return NIGHT_START - timeOfDay; // before night
        if (timeOfDay < NIGHT_END) return NIGHT_END - timeOfDay;     // during night
        return (24000L - timeOfDay) + NIGHT_START;                   // after night
    }
}