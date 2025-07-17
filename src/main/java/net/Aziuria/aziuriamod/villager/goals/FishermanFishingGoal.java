package net.Aziuria.aziuriamod.villager.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BarrelBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;

import java.util.EnumSet;
import java.util.Random;

public class FishermanFishingGoal extends Goal {

    private final Villager villager;
    private static final double SPEED = 0.5D;
    private static final int FISHING_RANGE = 10;
    private static final int MAX_INVENTORY_SLOTS = 36;

    private BlockPos fishingSpot = null;
    private BlockPos waterPos = null;
    private BlockPos storagePos = null;

    private enum Phase {MOVING, FISHING, FINDING_STORAGE, STORING}

    private Phase phase = null;
    private int fishingTime = 0;
    private int equipRetryCooldown = 0;

    // Track fish caught this session
    private int fishCaughtCount = 0;

    public FishermanFishingGoal(Villager villager) {
        this.villager = villager;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!ensureFishingRodEquipped()) {
            if (equipRetryCooldown > 0) {
                equipRetryCooldown--;
                return false;
            }
            equipRetryCooldown = 40;
            return false;
        }

        if (phase == null) {
            fishingSpot = findNearbyWaterSpot();
            if (fishingSpot == null) {
                return false;
            }
            phase = Phase.MOVING;
        }
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        return villager.isAlive() && phase != null;
    }

    @Override
    public void stop() {
        villager.getNavigation().stop();
        fishingTime = 0;
        equipRetryCooldown = 0;
        fishCaughtCount = 0;
        phase = null;
        fishingSpot = null;
        waterPos = null;
        storagePos = null;
    }

    @Override
    public void tick() {
        if (!ensureFishingRodEquipped()) {
            equipRetryCooldown = 40;
            stop();
            return;
        }

        switch (phase) {
            case MOVING -> {
                double dist = villager.blockPosition().distSqr(fishingSpot);
                if (dist > 2.0) {
                    if (villager.getNavigation().isDone() || !isNavigatingTo(fishingSpot)) {
                        villager.getNavigation().moveTo(
                                fishingSpot.getX() + 0.5,
                                fishingSpot.getY(),
                                fishingSpot.getZ() + 0.5,
                                SPEED
                        );
                    }
                } else {
                    villager.getNavigation().stop();
                    phase = Phase.FISHING;
                    fishingTime = 0;
                    fishCaughtCount = 0;
                    playSound(SoundEvents.FISHING_BOBBER_THROW, villager.blockPosition());
                }
            }

            case FISHING -> {
                villager.getNavigation().stop();
                fishingTime++;

                if (waterPos != null) {
                    villager.getLookControl().setLookAt(
                            waterPos.getX() + 0.5,
                            waterPos.getY() + 0.5,
                            waterPos.getZ() + 0.5
                    );
                }

                if (fishingTime >= 200 + villager.level().random.nextInt(200)) {
                    if (!isFishSlotAvailable()) {
                        phase = Phase.FINDING_STORAGE;
                    } else {
                        catchFish();
                        fishCaughtCount++;
                        if (fishCaughtCount >= 5) {
                            phase = Phase.FINDING_STORAGE;
                        }
                    }
                    fishingTime = 0;
                }
            }

            case FINDING_STORAGE -> {
                storagePos = findNearbyChestOrBarrel();
                if (storagePos != null) {
                    phase = Phase.STORING;
                } else {
                    phase = Phase.FISHING;
                }
            }

            case STORING -> {
                double dist = villager.blockPosition().distSqr(storagePos);
                if (dist > 2.0) {
                    if (villager.getNavigation().isDone() || !isNavigatingTo(storagePos)) {
                        villager.getNavigation().moveTo(
                                storagePos.getX() + 0.5,
                                storagePos.getY(),
                                storagePos.getZ() + 0.5,
                                SPEED
                        );
                    }
                } else {
                    villager.getLookControl().setLookAt(
                            storagePos.getX() + 0.5,
                            storagePos.getY() + 0.5,
                            storagePos.getZ() + 0.5
                    );
                    storeFishInContainer();
                    fishCaughtCount = 0;
                    phase = null;
                }
            }
        }
    }

    private boolean isNavigatingTo(BlockPos pos) {
        if (!villager.getNavigation().isInProgress()) return false;

        double targetX = villager.getNavigation().getTargetPos().getX();
        double targetY = villager.getNavigation().getTargetPos().getY();
        double targetZ = villager.getNavigation().getTargetPos().getZ();

        double dx = Math.abs(targetX - (pos.getX() + 0.5));
        double dy = Math.abs(targetY - pos.getY());
        double dz = Math.abs(targetZ - (pos.getZ() + 0.5));

        return dx < 0.1 && dy < 0.5 && dz < 0.1;
    }

    private boolean ensureFishingRodEquipped() {
        ItemStack main = villager.getMainHandItem();
        if (main.getItem() instanceof FishingRodItem) return true;

        // Instantly give a new fishing rod without checking inventory
        villager.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.FISHING_ROD));
        return true;
    }

    private BlockPos findNearbyWaterSpot() {
        Level level = villager.level();
        BlockPos origin = villager.blockPosition();

        for (int dx = -FISHING_RANGE; dx <= FISHING_RANGE; dx++) {
            for (int dz = -FISHING_RANGE; dz <= FISHING_RANGE; dz++) {
                for (int dy = -3; dy <= 3; dy++) {
                    BlockPos pos = origin.offset(dx, dy, dz);
                    if (level.getFluidState(pos).is(FluidTags.WATER)) {
                        BlockPos stand = findStandableSpotBesideWater(level, pos);
                        if (stand != null) {
                            waterPos = pos;
                            return stand;
                        }
                    }
                }
            }
        }
        return null;
    }

    private BlockPos findStandableSpotBesideWater(Level level, BlockPos water) {
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            BlockPos side = water.relative(dir);
            if (level.getBlockState(side).isFaceSturdy(level, side, Direction.UP)
                    && level.isEmptyBlock(side.above())) {
                return side.above();
            }
        }
        return null;
    }

    private BlockPos findNearbyChestOrBarrel() {
        Level level = villager.level();
        BlockPos origin = villager.blockPosition();

        for (BlockPos pos : BlockPos.betweenClosed(origin.offset(-10, -3, -10), origin.offset(10, 3, 10))) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof ChestBlockEntity || be instanceof BarrelBlockEntity) {
                return pos.immutable();
            }
        }
        return null;
    }

    private void catchFish() {
        ItemStack fish;
        int roll = new Random().nextInt(100);
        if (roll < 50) fish = new ItemStack(Items.COD);
        else if (roll < 90) fish = new ItemStack(Items.SALMON);
        else if (roll < 95) fish = new ItemStack(Items.PUFFERFISH);
        else fish = new ItemStack(Items.TROPICAL_FISH);

        ItemStack leftover = villager.getInventory().addItem(fish);
        if (leftover.isEmpty()) {
            playSound(SoundEvents.FISHING_BOBBER_RETRIEVE, villager.blockPosition());
        } else {
            phase = Phase.FINDING_STORAGE;
        }
    }

    private boolean isFishSlotAvailable() {
        for (ItemStack slot : villager.getInventory().getItems()) {
            if (slot.isEmpty()) return true;
            if (isFish(slot) && slot.getCount() < slot.getMaxStackSize()) return true;
        }
        return false;
    }

    private boolean isFish(ItemStack stack) {
        return stack.getItem() == Items.COD
                || stack.getItem() == Items.SALMON
                || stack.getItem() == Items.PUFFERFISH
                || stack.getItem() == Items.TROPICAL_FISH;
    }

    private void storeFishInContainer() {
        BlockEntity be = villager.level().getBlockEntity(storagePos);
        if (!(be instanceof Container container)) {
            return;
        }

        playSound(SoundEvents.CHEST_OPEN, storagePos);

        var inventory = villager.getInventory().getItems();
        boolean storedAny = false;

        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.get(i);

            if (stack.isEmpty() || !isFish(stack)) continue;

            while (!stack.isEmpty()) {
                ItemStack toInsert = stack.copy();
                toInsert.setCount(1);

                ItemStack leftover = tryInsertItem(container, toInsert);

                if (leftover.isEmpty()) {
                    stack.shrink(1);
                    storedAny = true;
                } else {
                    break;
                }
            }

            if (stack.isEmpty()) {
                inventory.set(i, ItemStack.EMPTY);
            }
        }

        playSound(SoundEvents.CHEST_CLOSE, storagePos);

        ensureFishingRodEquipped();
    }

    private ItemStack tryInsertItem(Container container, ItemStack stack) {
        for (int i = 0; i < container.getContainerSize(); i++) {
            ItemStack slot = container.getItem(i);
            if (slot.isEmpty()) {
                container.setItem(i, stack.copy());
                return ItemStack.EMPTY;
            } else if (ItemStack.isSameItem(slot, stack) && slot.getCount() < slot.getMaxStackSize()) {
                int space = slot.getMaxStackSize() - slot.getCount();
                int toMove = Math.min(space, stack.getCount());
                slot.grow(toMove);
                stack.shrink(toMove);
                if (stack.isEmpty()) {
                    return ItemStack.EMPTY;
                }
            }
        }
        return stack;
    }

    private void playSound(SoundEvent sound, BlockPos pos) {
        villager.level().playSound(
                null,
                pos,
                sound,
                SoundSource.NEUTRAL,
                1.0F,
                1.0F
        );
    }
}