package net.Aziuria.aziuriamod.exhaustion.capability;

import net.minecraft.nbt.CompoundTag;

public interface Iexhaustion {

    float getExhaustion();
    void  setExhaustion (float value);
    void addExhaustion(float value);

    CompoundTag serializeNBT();

    void deserializeNBT(CompoundTag tag);
}
