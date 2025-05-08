package net.Aziuria.aziuriamod.client.screen.custom;

import net.Aziuria.aziuriamod.client.screen.ModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.SlotItemHandler;

public class SackMenu extends AbstractContainerMenu {
    private final ItemStack sackItem;  // Reference to the Sack item (not a BlockEntity)
    private final Level level;

    // Constructor for when opening the SackMenu
    public SackMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, inv.player.getMainHandItem());  // Get the item in the player's hand
    }

    // Constructor that takes the Sack item directly
    public SackMenu(int containerId, Inventory inv, ItemStack sackItem) {
        super(ModMenus.SACK_MENU.get(), containerId);
        this.sackItem = sackItem;  // Set the Sack item
        this.level = inv.player.level();

        addPlayerInventory(inv);  // Add player inventory to the menu
        addPlayerHotbar(inv);  // Add the hotbar slots to the menu

        // Add the Sack's 3 inventory slots
        for (int i = 0; i < 3; i++) {
            this.addSlot(new SlotItemHandler(net.Aziuria.aziuriamod.handler.SackItemInventoryHandler.getInventory(sackItem, null), i, 80, 35 + i * 18));  // Adjust position based on the slot index
        }
    }

    // Slots for player inventory
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;

    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;

    private static final int TE_INVENTORY_SLOT_COUNT = 3;  // Sack inventory has 3 slots

    // Quick Move functionality (for transferring items)
    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  // EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot, so we move items into the Sack inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT + TE_INVENTORY_SLOT_COUNT) {
            // This is a Sack inventory slot, so we move items into the player's inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }

        // If stack size is 0, we clear the slot content
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    // Validates if the player can interact with this container
    @Override
    public boolean stillValid(Player player) {
        return player.getMainHandItem() == sackItem;  // Ensure the player is holding the Sack item
    }

    // Add slots for player inventory
    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    // Add slots for player hotbar
    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}