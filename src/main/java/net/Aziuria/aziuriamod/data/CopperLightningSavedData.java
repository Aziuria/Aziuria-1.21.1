package net.Aziuria.aziuriamod.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.core.HolderLookup;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CopperLightningSavedData extends SavedData {

    private static final String DATA_NAME = "aziuria_copper_lightning";
    private static final String TAG_MAP = "PlayerCooldowns";

    private final Map<UUID, Long> cooldowns = new HashMap<>();

    public CopperLightningSavedData() {}

    public CopperLightningSavedData(CompoundTag nbt, HolderLookup.Provider registries) {
        load(nbt);
    }

    @Override
    public CompoundTag save(CompoundTag nbt, HolderLookup.Provider registries) {
        CompoundTag mapTag = new CompoundTag();
        for (Map.Entry<UUID, Long> entry : cooldowns.entrySet()) {
            mapTag.putLong(entry.getKey().toString(), entry.getValue());
        }
        nbt.put(TAG_MAP, mapTag);
        return nbt;
    }

    public void load(CompoundTag nbt) {
        if (nbt.contains(TAG_MAP)) {
            CompoundTag mapTag = nbt.getCompound(TAG_MAP);
            for (String key : mapTag.getAllKeys()) {
                cooldowns.put(UUID.fromString(key), mapTag.getLong(key));
            }
        }
    }

    public long getLastStrike(UUID playerId) {
        return cooldowns.getOrDefault(playerId, 0L);
    }

    public void setLastStrike(UUID playerId, long timestamp) {
        cooldowns.put(playerId, timestamp);
        setDirty();
    }

    public static CopperLightningSavedData get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(
                new SavedData.Factory<>(
                        CopperLightningSavedData::new,   // (CompoundTag, HolderLookup.Provider) -> CopperLightningSavedData
                        CopperLightningSavedData::new    // () -> CopperLightningSavedData
                ),
                DATA_NAME
        );
    }
}