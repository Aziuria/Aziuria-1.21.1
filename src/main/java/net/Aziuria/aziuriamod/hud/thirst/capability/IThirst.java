package net.Aziuria.aziuriamod.hud.thirst.capability;

import net.minecraft.nbt.CompoundTag;

public interface IThirst {
    int getThirst();
    void setThirst(int value);

    float getExhaustion();
    void setExhaustion(float value);
    void addExhaustion(float value);

    CompoundTag serializeNBT();
    void deserializeNBT(CompoundTag tag);
}