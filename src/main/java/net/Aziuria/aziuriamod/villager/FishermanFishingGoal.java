package net.Aziuria.aziuriamod.villager;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
            System.out.println("[FishermanGoal] No fishing rod found, retrying in 40 ticks");
            return false;
        }

        if (phase == null) {
            fishingSpot = findNearbyWaterSpot();
            if (fishingSpot == null) {
                System.out.println("[FishermanGoal] No water spot found nearby, cannot start fishing");
                return false;
            }
            phase = Phase.MOVING;
            System.out.println("[FishermanGoal] Starting fishing cycle. Moving to fishing spot at " + fishingSpot);
        }
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        boolean cont = villager.isAlive() && phase != null;
        if (!cont)
            System.out.println("[FishermanGoal] Cannot continue: villager alive=" + villager.isAlive() + ", phase=" + phase);
        return cont;
    }

    @Override
    public void stop() {
        villager.getNavigation().stop();
        fishingTime = 0;
        equipRetryCooldown = 0;
        fishCaughtCount = 0; // reset counter
        System.out.println("[FishermanGoal] Stopping fishing goal");
        phase = null;
        fishingSpot = null;
        waterPos = null;
        storagePos = null;
    }

    @Override
    public void tick() {
        if (!ensureFishingRodEquipped()) {
            equipRetryCooldown = 40;
            System.out.println("[FishermanGoal] Lost fishing rod during tick, stopping goal and retrying later");
            stop();
            return;
        }

        switch (phase) {
            case MOVING -> {
                double dist = villager.blockPosition().distSqr(fishingSpot);
                if (dist > 2.0) {
                    if (villager.getNavigation().isDone() || !isNavigatingTo(fishingSpot)) {
                        System.out.println("[FishermanGoal] Moving to fishing spot: " + fishingSpot);
                        villager.getNavigation().moveTo(
                                fishingSpot.getX() + 0.5,
                                fishingSpot.getY(),
                                fishingSpot.getZ() + 0.5,
                                SPEED
                        );
                    }
                    // No repeated logging here while navigation in progress
                } else {
                    System.out.println("[FishermanGoal] Arrived at fishing spot, starting to fish");
                    villager.getNavigation().stop();
                    phase = Phase.FISHING;
                    fishingTime = 0;
                    fishCaughtCount = 0; // reset when starting to fish
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

                if (fishingTime % 40 == 0) {
                    System.out.println("[FishermanGoal] Fishing... time: " + fishingTime);
                }

                if (fishingTime >= 200 + villager.level().random.nextInt(200)) {
                    if (!isFishSlotAvailable()) {
                        System.out.println("[FishermanGoal] Cannot catch fish: no space for fish items");
                        phase = Phase.FINDING_STORAGE;
                    } else {
                        catchFish();
                        fishCaughtCount++;
                        if (fishCaughtCount >= 10) {
                            System.out.println("[FishermanGoal] Caught 10 fish, going to store them");
                            phase = Phase.FINDING_STORAGE;
                        }
                    }
                    fishingTime = 0;
                }
            }

            case FINDING_STORAGE -> {
                storagePos = findNearbyChestOrBarrel();
                if (storagePos != null) {
                    System.out.println("[FishermanGoal] Found storage at " + storagePos + ", moving to store fish");
                    phase = Phase.STORING;
                } else {
                    System.out.println("[FishermanGoal] No storage found nearby, continuing to fish");
                    phase = Phase.FISHING;
                }
            }

            case STORING -> {
                double dist = villager.blockPosition().distSqr(storagePos);
                if (dist > 2.0) {
                    if (villager.getNavigation().isDone() || !isNavigatingTo(storagePos)) {
                        System.out.println("[FishermanGoal] Moving to storage at " + storagePos);
                        villager.getNavigation().moveTo(
                                storagePos.getX() + 0.5,
                                storagePos.getY(),
                                storagePos.getZ() + 0.5,
                                SPEED
                        );
                    }
                    // No repeated logging here while navigation in progress
                } else {
                    villager.getLookControl().setLookAt(
                            storagePos.getX() + 0.5,
                            storagePos.getY() + 0.5,
                            storagePos.getZ() + 0.5
                    );
                    System.out.println("[FishermanGoal] Arrived at storage, storing fish");
                    storeFishInContainer();
                    fishCaughtCount = 0; // reset after storing
                    phase = null; // will restart next tick
                }
            }
        }
    }

    /**
     * Helper method to check if villager is already navigating to a given target position.
     */
    private boolean isNavigatingTo(BlockPos pos) {
        if (!villager.getNavigation().isInProgress()) return false;

        double targetX = villager.getNavigation().getTargetPos().getX();
        double targetY = villager.getNavigation().getTargetPos().getY();
        double targetZ = villager.getNavigation().getTargetPos().getZ();

        double dx = Math.abs(targetX - (pos.getX() + 0.5));
        double dy = Math.abs(targetY - pos.getY());
        double dz = Math.abs(targetZ - (pos.getZ() + 0.5));

        // Use a small tolerance to consider floating point rounding errors
        return dx < 0.1 && dy < 0.5 && dz < 0.1;
    }

    private boolean ensureFishingRodEquipped() {
        ItemStack main = villager.getMainHandItem();
        if (main.getItem() instanceof FishingRodItem) return true;

        if (equipRetryCooldown > 0) {
            equipRetryCooldown--;
            return false;
        }

        for (ItemStack stack : villager.getInventory().getItems()) {
            if (stack.getItem() instanceof FishingRodItem) {
                villager.setItemInHand(InteractionHand.MAIN_HAND, stack.copy());
                // DO NOT shrink or remove from inventory
                System.out.println("[FishermanGoal] Equipped fishing rod from inventory (without removing)");
                equipRetryCooldown = 40;
                return true;
            }
        }

        equipRetryCooldown = 40;
        return false;
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
                            System.out.println("[FishermanGoal] Found water at " + waterPos + " with standable spot at " + stand);
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
                System.out.println("[FishermanGoal] Found storage block at " + pos);
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
            System.out.println("[FishermanGoal] Caught fish: " + fish.getItem().getName(fish).getString());
        } else {
            System.out.println("[FishermanGoal] Failed to add fish to inventory (full), finding storage");
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
            System.out.println("[FishermanGoal] Storage at " + storagePos + " is not a valid container");
            return;
        }

        var inventory = villager.getInventory().getItems();
        boolean storedAny = false;

        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.get(i);

            if (stack.isEmpty() || !isFish(stack)) continue;

            System.out.println("[FishermanGoal] Storing fish stack: " + stack.getCount() + "x " + stack.getItem().getName(stack).getString());

            while (!stack.isEmpty()) {
                ItemStack toInsert = stack.copy();
                toInsert.setCount(1);

                ItemStack leftover = tryInsertItem(container, toInsert);

                if (leftover.isEmpty()) {
                    stack.shrink(1);
                    storedAny = true;
                } else {
                    System.out.println("[FishermanGoal] Storage container is full, cannot store more fish");
                    break;
                }
            }

            if (stack.isEmpty()) {
                inventory.set(i, ItemStack.EMPTY);
            }
        }

        if (storedAny) {
            System.out.println("[FishermanGoal] Finished storing fish in container at " + storagePos);
        } else {
            System.out.println("[FishermanGoal] No fish were stored (storage full or no fish)");
        }

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
}