package net.Aziuria.aziuriamod.villager;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.EnumSet;
import java.util.Random;

public class FishermanFishingGoal extends Goal {

    private final Villager villager;
    private final double speed;
    private final int fishingRange = 10;

    private BlockPos fishingSpot = null;
    private boolean isFishing = false;
    private int fishingCastTime = 0;
    private int fishingCooldown = 0;

    private int rodBreakCooldown = 0;

    public FishermanFishingGoal(Villager villager, double speed) {
        this.villager = villager;
        this.speed = speed;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (rodBreakCooldown > 0) {
            rodBreakCooldown--;
            System.out.println("[DEBUG] Rod break cooldown active: " + rodBreakCooldown);
            return false;
        }

        if (!hasFishingRod()) {
            System.out.println("[DEBUG] No fishing rod equipped, trying to equip...");
            if (!equipFishingRodFromInventory()) {
                System.out.println("[DEBUG] No fishing rod in inventory.");
                return false;
            } else {
                System.out.println("[DEBUG] Fishing rod equipped from inventory.");
            }
        }

        fishingSpot = findNearbyWater();
        System.out.println("[DEBUG] Found fishing spot at: " + fishingSpot);
        return fishingSpot != null;
    }

    @Override
    public boolean canContinueToUse() {
        boolean canContinue = fishingSpot != null && villager.isAlive() && hasFishingRod() && rodBreakCooldown == 0;
        System.out.println("[DEBUG] canContinueToUse: " + canContinue);
        return canContinue;
    }

    @Override
    public void stop() {
        System.out.println("[DEBUG] Stopping fishing.");
        fishingSpot = null;
        isFishing = false;
        fishingCastTime = 0;
        fishingCooldown = 0;
        villager.getNavigation().stop();
        unequipFishingRod();
    }

    @Override
    public void tick() {
        if (fishingSpot == null) return;

        if (!villager.blockPosition().closerThan(fishingSpot, 2.0)) {
            if (villager.getNavigation().isDone()) {
                System.out.println("[DEBUG] Moving to fishing spot...");
                villager.getNavigation().moveTo(
                        fishingSpot.getX() + 0.5,
                        fishingSpot.getY(),
                        fishingSpot.getZ() + 0.5,
                        speed
                );
            }
            return;
        }

        if (!isFishing) {
            System.out.println("[DEBUG] Starting fishing.");
            isFishing = true;
            fishingCastTime = 0;
            holdFishingRod();
        } else {
            fishingCastTime++;
            if (fishingCastTime >= 100) {
                if (villager.level().random.nextFloat() < 0.5f) {
                    System.out.println("[DEBUG] Catching fish now!");
                    catchFish();
                } else {
                    System.out.println("[DEBUG] Fishing attempt failed.");
                }
                fishingCooldown = 60 + villager.level().random.nextInt(40);
                isFishing = false;
            }
        }

        if (fishingCooldown > 0) {
            fishingCooldown--;
        }
    }

    private void holdFishingRod() {
        ItemStack rod = getFishingRodInInventory();
        if (rod != null) {
            System.out.println("[DEBUG] Holding fishing rod.");
            villager.setItemInHand(InteractionHand.MAIN_HAND, rod);
        }
    }

    private void unequipFishingRod() {
        System.out.println("[DEBUG] Unequipping fishing rod.");
        villager.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
    }

    private boolean hasFishingRod() {
        ItemStack mainHand = villager.getMainHandItem();
        boolean hasRod = mainHand.getItem() instanceof FishingRodItem;
        System.out.println("[DEBUG] Has fishing rod in main hand: " + hasRod);
        return hasRod;
    }

    private boolean equipFishingRodFromInventory() {
        ItemStack rod = getFishingRodInInventory();
        if (rod != null) {
            villager.setItemInHand(InteractionHand.MAIN_HAND, rod);
            return true;
        }
        return false;
    }

    private ItemStack getFishingRodInInventory() {
        var inventory = villager.getInventory().getItems();
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.get(i);
            if (!stack.isEmpty() && stack.getItem() instanceof FishingRodItem) {
                ItemStack rod = stack.split(1);
                if (stack.isEmpty()) inventory.set(i, ItemStack.EMPTY);
                System.out.println("[DEBUG] Found fishing rod in inventory at slot " + i);
                return rod;
            }
        }
        System.out.println("[DEBUG] No fishing rod found in inventory.");
        return null;
    }

    private BlockPos findNearbyWater() {
        Level level = villager.level();
        BlockPos origin = villager.blockPosition();
        int radius = fishingRange;

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                for (int dy = -1; dy <= 1; dy++) {
                    BlockPos checkPos = origin.offset(dx, dy, dz);
                    if (level.getFluidState(checkPos).is(FluidTags.WATER)) {
                        BlockPos above = checkPos.above();
                        if (level.getBlockState(above).canBeReplaced()) {
                            return above;
                        }
                    }
                }
            }
        }
        System.out.println("[DEBUG] No water found nearby for fishing.");
        return null;
    }

    private void catchFish() {
        Random rand = new Random();
        ItemStack fish;
        int roll = rand.nextInt(100);
        if (roll < 50) fish = new ItemStack(Items.COD);
        else if (roll < 90) fish = new ItemStack(Items.SALMON);
        else if (roll < 95) fish = new ItemStack(Items.PUFFERFISH);
        else fish = new ItemStack(Items.TROPICAL_FISH);
        villager.getInventory().addItem(fish);

        System.out.println("[DEBUG] Villager caught fish: " + fish.getItem().toString());
    }

}