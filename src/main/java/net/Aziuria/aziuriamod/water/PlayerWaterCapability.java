package net.Aziuria.aziuriamod.water;

import net.minecraft.nbt.CompoundTag;

public class PlayerWaterCapability {

    private int waterLevel = 20;

    public int getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(int level) {
        this.waterLevel = Math.max(0, Math.min(20, level));
    }

    public void addWater(int amount) {
        setWaterLevel(this.waterLevel + amount);
    }

    public void drainWater(int amount) {
        setWaterLevel(this.waterLevel - amount);
    }

    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("WaterLevel", waterLevel);
        return tag;
    }

    public void load(CompoundTag tag) {
        if (tag.contains("WaterLevel")) {
            this.waterLevel = tag.getInt("WaterLevel");
        }
    }
}