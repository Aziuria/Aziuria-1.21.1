package net.Aziuria.aziuriamod.client.screen.custom;

import net.Aziuria.aziuriamod.client.screen.ModMenus;
import net.Aziuria.aziuriamod.handler.SackItemInventoryHandler; // Ensure this is imported
import net.Aziuria.aziuriamod.handler.SackItemInventoryHandler.SackHandler; // Import custom handler
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.SlotItemHandler;

public class SackMenu extends AbstractContainerMenu {
    private final ItemStack sackItem;
    private final Level level;

    // Custom SackHandler for managing the sack's inventory
    private final SackHandler sackHandler;

    public SackMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, inv.player.getMainHandItem());
    }

    public SackMenu(int containerId, Inventory inv, ItemStack sackItem) {
        super(ModMenus.SACK_MENU.get(), containerId);
        this.sackItem = sackItem;
        this.level = inv.player.level();

        // Create and store the SackHandler instance
        this.sackHandler = new SackHandler(sackItem, level.registryAccess());

        // Add custom slots for the sack's inventory (3 slots in this case)
        int customSlotX = 61;
        int customSlotY = 16;
        for (int i = 0; i < 3; i++) {
            this.addSlot(new SlotItemHandler(
                    sackHandler, // Use the persistent SackHandler here
                    i,
                    customSlotX + i * 18,
                    customSlotY
            ));
        }

        // Add player's inventory and hotbar
        addPlayerInventory(inv);
        addPlayerHotbar(inv);
    }

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;

    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;

    private static final int TE_INVENTORY_SLOT_COUNT = 3;

    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT,
                    VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT + TE_INVENTORY_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX,
                    VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }

        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return player.getMainHandItem() == sackItem;
    }

    private void addPlayerInventory(Inventory playerInventory) {
        int startY = 35;
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9,
                        7 + col * 18, startY + row * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 7 + i * 18, 93));
        }
    }
}