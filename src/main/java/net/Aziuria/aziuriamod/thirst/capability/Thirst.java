package net.Aziuria.aziuriamod.thirst.capability;

import net.minecraft.nbt.CompoundTag;

public class Thirst implements IThirst {
    private int thirst = 20;
    private float exhaustion = 0f;

    @Override
    public int getThirst() {
        return thirst;
    }

    @Override
    public void setThirst(int value) {
        this.thirst = Math.max(0, Math.min(value, 20));
    }

    @Override
    public float getExhaustion() {
        return exhaustion;
    }

    @Override
    public void setExhaustion(float value) {
        this.exhaustion = Math.max(0f, value);
    }

    @Override
    public void addExhaustion(float value) {
        setExhaustion(this.exhaustion + value);
    }

    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("Thirst", thirst);
        tag.putFloat("Exhaustion", exhaustion);
        return tag;
    }

    public void deserializeNBT(CompoundTag tag) {
        if (tag.contains("Thirst")) {
            thirst = tag.getInt("Thirst");
        }
        if (tag.contains("Exhaustion")) {
            exhaustion = tag.getFloat("Exhaustion");
        }
    }
}