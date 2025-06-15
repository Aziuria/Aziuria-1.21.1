package net.Aziuria.aziuriamod.villager;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

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

    public FishermanFishingGoal(Villager villager, double speed) {
        this.villager = villager;
        this.speed = speed;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!hasFishingRod()) {
            if (!equipFishingRodFromInventory()) {
                return false;
            }
        }

        fishingSpot = findNearbyFishingSpot();
        return fishingSpot != null;
    }

    @Override
    public boolean canContinueToUse() {
        return fishingSpot != null && villager.isAlive() && hasFishingRod();
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

        double distance = villager.distanceToSqr(fishingSpot.getX() + 0.5, fishingSpot.getY(), fishingSpot.getZ() + 0.5);

        if (distance > 2.5 * 2.5) {
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

        villager.getNavigation().stop();

        if (!isFishing) {
            isFishing = true;
            fishingCastTime = 0;
            holdFishingRod();
            playCastSound();
        } else {
            fishingCastTime++;
            if (fishingCastTime >= 100) {
                if (villager.level().random.nextFloat() < 0.5f) {
                    catchFish();
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
            villager.setItemSlot(EquipmentSlot.MAINHAND, rod.copy());
        }
    }

    private void unequipFishingRod() {
        ItemStack held = villager.getMainHandItem();
        if (!held.isEmpty() && held.getItem() instanceof FishingRodItem) {
            villager.getInventory().addItem(held);
        }
        villager.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
    }

    private boolean hasFishingRod() {
        ItemStack mainHand = villager.getMainHandItem();
        return mainHand.getItem() instanceof FishingRodItem;
    }

    private boolean equipFishingRodFromInventory() {
        ItemStack rod = getFishingRodInInventory();
        if (rod != null) {
            villager.setItemSlot(EquipmentSlot.MAINHAND, rod.copy());
            return true;
        }
        return false;
    }

    private ItemStack getFishingRodInInventory() {
        var inventory = villager.getInventory().getItems();
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.get(i);
            if (!stack.isEmpty() && stack.getItem() instanceof FishingRodItem) {
                return stack;
            }
        }
        return null;
    }

    private BlockPos findNearbyFishingSpot() {
        Level level = villager.level();
        BlockPos origin = villager.blockPosition();
        int radius = fishingRange;

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                for (int dy = -1; dy <= 1; dy++) {
                    BlockPos pos = origin.offset(dx, dy, dz);
                    if (level.getFluidState(pos).is(FluidTags.WATER)) {
                        BlockPos ground = pos.above();
                        if (level.getBlockState(ground).isAir() &&
                                level.getBlockState(ground.below()).isFaceSturdy(level, ground.below(), Direction.UP)) {
                            if (!level.getFluidState(ground).is(FluidTags.WATER)) {
                                return ground;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    private void playCastSound() {
        villager.level().playSound(
                null,
                villager.blockPosition(),
                SoundEvents.FISHING_BOBBER_THROW,
                SoundSource.NEUTRAL,
                1.0F,
                1.0F
        );
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

        // Play splash sound
        Level level = villager.level();
        level.playSound(
                null,
                fishingSpot,
                SoundEvents.FISHING_BOBBER_SPLASH,
                SoundSource.NEUTRAL,
                1.0F,
                1.0F
        );

        // ✅ Send splash particles only if server side
        if (level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(
                    net.minecraft.core.particles.ParticleTypes.SPLASH,
                    fishingSpot.getX() + 0.5,
                    fishingSpot.getY(),
                    fishingSpot.getZ() + 0.5,
                    5,
                    0.2, 0.1, 0.2, 0.0
            );
        }
    }
}