package net.Aziuria.aziuriamod.villager.goals;

import net.Aziuria.aziuriamod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.Container;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;

public class StoreWoodInChestGoal extends MoveToBlockGoal {
    private final Villager villager;
    private boolean hasPlayedOpenSound = false;

    public StoreWoodInChestGoal(Villager villager, double speed) {
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
        for (ItemStack stack : villager.getInventory().getItems()) {
            if (!stack.isEmpty() && isStorableItem(stack)) {
                return super.canUse();
            }
        }
        return false;
    }

    private boolean isStorableItem(ItemStack stack) {
        return stack.is(ItemTags.LOGS)
                || stack.is(ItemTags.SAPLINGS)
                || stack.getItem() == ModItems.PEAR.get()
                || stack.getItem() == ModItems.CHERRY.get()
                || stack.getItem() == ModItems.AVOCADO.get()
                || stack.getItem() == Items.APPLE
                || stack.getItem() == Items.STICK;
    }

    @Override
    public void tick() {
        super.tick();

        if (isReachedTarget()) {
            if (!hasPlayedOpenSound) {
                villager.level().playSound(
                        null, blockPos, SoundEvents.CHEST_OPEN, SoundSource.BLOCKS,
                        1.0F, 1.0F
                );
                hasPlayedOpenSound = true;
            }

            BlockEntity be = villager.level().getBlockEntity(blockPos);
            if (be instanceof ChestBlockEntity chest) {
                Container container = chest;
                var inventory = villager.getInventory().getItems();

                for (int i = 0; i < inventory.size(); i++) {
                    ItemStack stack = inventory.get(i);

                    if (stack.isEmpty()) continue;
                    if (!isStorableItem(stack)) continue;

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
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void stop() {
        super.stop();
        if (hasPlayedOpenSound) {
            villager.level().playSound(
                    null, blockPos, SoundEvents.CHEST_CLOSE, SoundSource.BLOCKS,
                    1.0F, 1.0F
            );
        }
        hasPlayedOpenSound = false;
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