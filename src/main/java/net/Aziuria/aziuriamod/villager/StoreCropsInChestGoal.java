package net.Aziuria.aziuriamod.villager;

import net.Aziuria.aziuriamod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;

public class StoreCropsInChestGoal extends MoveToBlockGoal {
    private final Villager villager;

    public StoreCropsInChestGoal(Villager villager, double speed) {
        super(villager, speed, 8);
        this.villager = villager;
    }

    @Override
    protected boolean isValidTarget(LevelReader level, BlockPos pos) {
        BlockEntity be = level.getBlockEntity(pos);
        return be instanceof ChestBlockEntity;
    }

    @Override
    public boolean canUse() {
        // Only run if the villager actually has storable items
        for (ItemStack stack : villager.getInventory().getItems()) {
            if (!stack.isEmpty() && isStorableItem(stack)) {
                return super.canUse();
            }
        }
        return false;
    }

    private boolean isStorableItem(ItemStack stack) {
        return stack.getItem() == ModItems.CUCUMBER.get()
                || stack.getItem() == ModItems.RADISH.get()
                || stack.getItem() == ModItems.TOMATO.get()
                || stack.getItem() == Items.CARROT
                || stack.getItem() == Items.POTATO
                || stack.getItem() == Items.WHEAT
                || stack.getItem() == Items.BEETROOT
                || stack.getItem() == ModItems.CUCUMBER_SEEDS.get()
                || stack.getItem() == ModItems.RADISH_SEEDS.get()
                || stack.getItem() == ModItems.TOMATO_SEEDS.get();
    }

    @Override
    public void tick() {
        super.tick();

        if (isReachedTarget()) {
            BlockEntity be = villager.level().getBlockEntity(blockPos);
            if (be instanceof ChestBlockEntity chest) {
                Container container = chest;
                var inventory = villager.getInventory().getItems();

                // Iterate by index so we can modify inventory safely
                for (int i = 0; i < inventory.size(); i++) {
                    ItemStack stack = inventory.get(i);

                    if (stack.isEmpty()) continue;
                    if (!isStorableItem(stack)) continue;

                    // Deposit all of this stack, one by one
                    while (!stack.isEmpty()) {
                        ItemStack toInsert = stack.copy();
                        toInsert.setCount(1);

                        ItemStack leftover = tryInsertItem(container, toInsert);

                        if (leftover.isEmpty()) {
                            stack.shrink(1);
                            if (stack.isEmpty()) {
                                inventory.set(i, ItemStack.EMPTY);
                                break;
                            }
                        } else {
                            // Chest is full for this item
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * Tries to insert the given ItemStack into the container.
     * Returns leftover if not all could be inserted.
     */
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