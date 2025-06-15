package net.Aziuria.aziuriamod.villager;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BarrelBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;

public class StoreFishInChestGoal extends MoveToBlockGoal {

    private final Villager villager;

    public StoreFishInChestGoal(Villager villager, double speed) {
        super(villager, speed, 8);
        this.villager = villager;
    }

    @Override
    protected boolean isValidTarget(LevelReader level, BlockPos pos) {
        BlockEntity be = level.getBlockEntity(pos);
        return be instanceof ChestBlockEntity || be instanceof BarrelBlockEntity;
    }

    @Override
    public boolean canUse() {
        // Only run if the villager actually has fish to store
        for (ItemStack stack : villager.getInventory().getItems()) {
            if (!stack.isEmpty() && isFish(stack)) {
                return super.canUse();
            }
        }
        return false;
    }

    private boolean isFish(ItemStack stack) {
        return stack.getItem() == Items.COD
                || stack.getItem() == Items.SALMON
                || stack.getItem() == Items.PUFFERFISH
                || stack.getItem() == Items.TROPICAL_FISH;
    }

    @Override
    public void tick() {
        super.tick();

        if (isReachedTarget()) {
            BlockEntity be = villager.level().getBlockEntity(blockPos);
            Container container = null;

            if (be instanceof ChestBlockEntity chest) {
                container = chest;
            } else if (be instanceof BarrelBlockEntity barrel) {
                container = barrel;
            }

            if (container != null) {
                var inventory = villager.getInventory().getItems();

                // Iterate safely by index
                for (int i = 0; i < inventory.size(); i++) {
                    ItemStack stack = inventory.get(i);

                    if (stack.isEmpty()) continue;
                    if (!isFish(stack)) continue;

                    // Deposit whole stack, one by one
                    while (!stack.isEmpty()) {
                        ItemStack toInsert = stack.copy();
                        toInsert.setCount(1);

                        ItemStack leftover = tryInsertItem(container, toInsert);

                        if (leftover.isEmpty()) {
                            stack.shrink(1);
                            System.out.println("Villager stored a fish in chest/barrel at " + blockPos);
                            if (stack.isEmpty()) {
                                inventory.set(i, ItemStack.EMPTY);
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }

    private ItemStack tryInsertItem(Container container, ItemStack stack) {
        for (int slot = 0; slot < container.getContainerSize(); slot++) {
            ItemStack slotStack = container.getItem(slot);

            if (slotStack.isEmpty()) {
                container.setItem(slot, stack.copy());
                container.setChanged();
                return ItemStack.EMPTY;
            } else if (ItemStack.isSameItem(slotStack, stack) && slotStack.getCount() < slotStack.getMaxStackSize()) {
                int space = slotStack.getMaxStackSize() - slotStack.getCount();
                int toTransfer = Math.min(space, stack.getCount());

                slotStack.grow(toTransfer);
                stack.shrink(toTransfer);

                container.setItem(slot, slotStack);
                container.setChanged();

                if (stack.isEmpty()) {
                    return ItemStack.EMPTY;
                }
            }
        }
        return stack;
    }
}