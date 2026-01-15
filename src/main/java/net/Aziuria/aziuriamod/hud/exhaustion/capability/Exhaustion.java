package net.Aziuria.aziuriamod.hud.exhaustion.capability;

import net.minecraft.nbt.CompoundTag;

public class Exhaustion implements Iexhaustion {

    private float exhaustion = 0f;
    public  static final float MAX_EXHAUSTION = 20f;

    @Override
    public float getExhaustion() {
        return exhaustion;
    }

    @Override
    public void setExhaustion(float value) {
        this.exhaustion = Math.max(0f, Math.min(value, MAX_EXHAUSTION));
    }

    @Override
    public void addExhaustion (float value) {
        setExhaustion(this.exhaustion + value);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putFloat("Exhaustion", exhaustion);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        if (tag.contains("Exhaustion")) {
            exhaustion = tag.getFloat("Exhaustion");

        }
    }
}
