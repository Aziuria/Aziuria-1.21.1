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

    private int rodBreakCooldown = 0;  // You can remove this field too if you want

    public FishermanFishingGoal(Villager villager, double speed) {
        this.villager = villager;
        this.speed = speed;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (rodBreakCooldown > 0) {
            rodBreakCooldown--;
            return false;
        }

        if (!hasFishingRod()) {
            if (!equipFishingRodFromInventory()) {
                return false;
            }
        }

        fishingSpot = findNearbyWater();
        return fishingSpot != null;
    }

    @Override
    public boolean canContinueToUse() {
        return fishingSpot != null && villager.isAlive() && hasFishingRod() && rodBreakCooldown == 0;
    }

    @Override
    public void stop() {
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
            isFishing = true;
            fishingCastTime = 0;
            holdFishingRod();
        } else {
            fishingCastTime++;
            if (fishingCastTime >= 100) {
                if (villager.level().random.nextFloat() < 0.5f) {
                    catchFish();
                }
                // Removed rod damage/breaking logic
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
            villager.setItemInHand(InteractionHand.MAIN_HAND, rod);
        }
    }

    private void unequipFishingRod() {
        villager.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
    }

    private boolean hasFishingRod() {
        ItemStack mainHand = villager.getMainHandItem();
        return mainHand.getItem() instanceof FishingRodItem;
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
                return rod;
            }
        }
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
    }

}