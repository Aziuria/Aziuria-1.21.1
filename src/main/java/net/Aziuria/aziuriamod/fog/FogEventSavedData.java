package net.Aziuria.aziuriamod.fog;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.core.HolderLookup;

public class FogEventSavedData extends SavedData {

    private static final String DATA_NAME = "aziuria_fog_event";

    private String activeFogId = "";
    private int currentIntensityOrdinal = 0;
    private long fogStart = 0L;
    private long fogEnd = 0L;
    private long fogFadeInEnd = 0L;
    private long fogFadeOutStart = 0L;
    private boolean dissipatingMessageSent = false;
    private long nextFogCheckTime = 0L;
    private boolean fogEnabled = true;

    private int daysFogDenied = 0;

    public FogEventSavedData() {}

    public FogEventSavedData(CompoundTag nbt, HolderLookup.Provider registries) {
        load(nbt);
    }

    @Override
    public CompoundTag save(CompoundTag nbt, HolderLookup.Provider registries) {
        nbt.putString("ActiveFogID", activeFogId);
        nbt.putInt("CurrentIntensityOrdinal", currentIntensityOrdinal);
        nbt.putLong("FogStart", fogStart);
        nbt.putLong("FogEnd", fogEnd);
        nbt.putLong("FogFadeInEnd", fogFadeInEnd);
        nbt.putLong("FogFadeOutStart", fogFadeOutStart);
        nbt.putBoolean("DissipatingMessageSent", dissipatingMessageSent);
        nbt.putLong("NextFogCheckTime", nextFogCheckTime);
        nbt.putInt("DaysFogDenied", daysFogDenied);
        nbt.putBoolean("FogEnabled", fogEnabled);
        return nbt;
    }

    public void load(CompoundTag nbt) {
        if (nbt != null && nbt.contains("ActiveFogID")) {
            activeFogId = nbt.getString("ActiveFogID");
            currentIntensityOrdinal = nbt.getInt("CurrentIntensityOrdinal");
            fogStart = nbt.getLong("FogStart");
            fogEnd = nbt.getLong("FogEnd");
            fogFadeInEnd = nbt.getLong("FogFadeInEnd");
            fogFadeOutStart = nbt.getLong("FogFadeOutStart");
            dissipatingMessageSent = nbt.getBoolean("DissipatingMessageSent");
            nextFogCheckTime = nbt.getLong("NextFogCheckTime");
            daysFogDenied = nbt.contains("DaysFogDenied") ? nbt.getInt("DaysFogDenied") : 0;
            fogEnabled = nbt.contains("FogEnabled") ? nbt.getBoolean("FogEnabled") : true;
        }
    }

    public String getActiveFogId() { return activeFogId; }
    public void setActiveFogId(String id) { this.activeFogId = id; setDirty(); }

    public int getCurrentIntensityOrdinal() { return currentIntensityOrdinal; }
    public void setCurrentIntensityOrdinal(int ordinal) { this.currentIntensityOrdinal = ordinal; setDirty(); }

    public long getFogStart() { return fogStart; }
    public void setFogStart(long val) { this.fogStart = val; setDirty(); }

    public long getFogEnd() { return fogEnd; }
    public void setFogEnd(long val) { this.fogEnd = val; setDirty(); }

    public long getFogFadeInEnd() { return fogFadeInEnd; }
    public void setFogFadeInEnd(long val) { this.fogFadeInEnd = val; setDirty(); }

    public long getFogFadeOutStart() { return fogFadeOutStart; }
    public void setFogFadeOutStart(long val) { this.fogFadeOutStart = val; setDirty(); }

    public boolean getDissipatingMessageSent() { return dissipatingMessageSent; }
    public void setDissipatingMessageSent(boolean val) { this.dissipatingMessageSent = val; setDirty(); }

    public long getNextFogCheckTime() { return nextFogCheckTime; }
    public void setNextFogCheckTime(long val) { this.nextFogCheckTime = val; setDirty(); }

    public int getDaysFogDenied() { return daysFogDenied; }
    public void setDaysFogDenied(int val) { this.daysFogDenied = val; setDirty(); }

    public boolean isFogEnabled() { return fogEnabled; }        // NEW
    public void setFogEnabled(boolean val) { this.fogEnabled = val; setDirty(); } // NEW

    // Correct get method using NeoForge 1.21.1 SavedData.Factory
    public static FogEventSavedData get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(
                new SavedData.Factory<>(
                        FogEventSavedData::new,   // Constructor: (CompoundTag, HolderLookup.Provider) -> FogEventSavedData
                        FogEventSavedData::new    // Constructor: () -> FogEventSavedData
                ),
                DATA_NAME
        );
    }
}