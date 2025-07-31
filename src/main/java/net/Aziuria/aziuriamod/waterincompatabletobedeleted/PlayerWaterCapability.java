//package net.Aziuria.aziuriamod.water;
//
//import net.minecraft.nbt.CompoundTag;
//import net.minecraft.server.level.ServerPlayer;
//
//public class PlayerWaterCapability {
//
//    private static final int MAX_WATER = 20;
//    private int waterLevel = MAX_WATER;
//    private Runnable onWaterLevelChanged;
//
//    // Constructor no longer needs ServerPlayer
//    public PlayerWaterCapability() {
//    }
//
//    /** Get current water level */
//    public int getWaterLevel() {
//        System.out.println("[WaterCapability] getWaterLevel() called, current: " + waterLevel);
//        return waterLevel;
//    }
//
//    /** Set water level and clamp between 0 and MAX_WATER */
//    public void setWaterLevel(int level) {
//        int clamped = Math.max(0, Math.min(MAX_WATER, level));
//        System.out.println("[WaterCapability] setWaterLevel() called with: " + level + ", clamped to: " + clamped);
//        if (clamped != waterLevel) {
//            System.out.println("[WaterCapability] Water level changed from " + waterLevel + " to " + clamped);
//            waterLevel = clamped;
//            if (onWaterLevelChanged != null) {
//                System.out.println("[WaterCapability] Triggering onWaterLevelChanged callback");
//                onWaterLevelChanged.run();  // This will sync the water level via registered callback
//            } else {
//                System.out.println("[WaterCapability] onWaterLevelChanged callback is null");
//            }
//        } else {
//            System.out.println("[WaterCapability] Water level unchanged");
//        }
//    }
//
//    /** Add water, ignoring non-positive amounts */
//    public void addWater(int amount) {
//        System.out.println("[WaterCapability] addWater() called with amount: " + amount);
//        if (amount > 0) {
//            setWaterLevel(waterLevel + amount);
//            System.out.println("[WaterCapability] Added water: +" + amount);
//        } else {
//            System.out.println("[WaterCapability] Ignored addWater with non-positive amount");
//        }
//    }
//
//    /** Drain water, ignoring non-positive amounts */
//    public void drainWater(int amount) {
//        System.out.println("[WaterCapability] drainWater() called with amount: " + amount);
//        if (amount > 0) {
//            setWaterLevel(waterLevel - amount);
//            System.out.println("[WaterCapability] Drained water: -" + amount);
//        } else {
//            System.out.println("[WaterCapability] Ignored drainWater with non-positive amount");
//        }
//    }
//
//    /** Set callback to run when water level changes */
//    public void setOnWaterLevelChanged(Runnable callback) {
//        System.out.println("[WaterCapability] setOnWaterLevelChanged() called");
//        this.onWaterLevelChanged = callback;
//    }
//
//    /** Save water level to NBT */
//    public CompoundTag save() {
//        System.out.println("[WaterCapability] Saving water level: " + waterLevel);
//        CompoundTag tag = new CompoundTag();
//        tag.putInt("WaterLevel", waterLevel);
//        return tag;
//    }
//
//    /** Load water level from NBT */
//    public void load(CompoundTag tag) {
//        if (tag != null && tag.contains("WaterLevel")) {
//            int loadedLevel = tag.getInt("WaterLevel");
//            System.out.println("[WaterCapability] Loading water level from NBT: " + loadedLevel);
//            setWaterLevel(loadedLevel); // will trigger callback and sync
//        } else {
//            System.out.println("[WaterCapability] No water level found in NBT");
//        }
//    }
//
//    // Optional for capability persistence
//    public CompoundTag serializeNBT() {
//        System.out.println("[WaterCapability] serializeNBT() called");
//        return save();
//    }
//
//    public void deserializeNBT(CompoundTag tag) {
//        System.out.println("[WaterCapability] deserializeNBT() called");
//        load(tag);
//    }
//}