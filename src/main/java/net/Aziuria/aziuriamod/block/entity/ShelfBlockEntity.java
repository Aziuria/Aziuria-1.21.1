package net.Aziuria.aziuriamod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;

public class ShelfBlockEntity extends BlockEntity {
    public final NonNullList<ItemStack> inventory;

    public ShelfBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SHELF_BLOCK_ENTITY.get(), pos, state);
        this.inventory = NonNullList.withSize(4, ItemStack.EMPTY);
    }

    public static int getClickedSlot(Vec3 hit, Direction facing) {
        double x = hit.x;
        double y = hit.y;
        double z = hit.z;

        switch (facing) {
            case NORTH -> { return y > 0.5 ? (x > 0.5 ? 1 : 0) : (x > 0.5 ? 3 : 2); }
            case SOUTH -> { return y > 0.5 ? (x < 0.5 ? 1 : 0) : (x < 0.5 ? 3 : 2); }
            case WEST  -> { return y > 0.5 ? (z < 0.5 ? 1 : 0) : (z < 0.5 ? 3 : 2); }
            case EAST  -> { return y > 0.5 ? (z > 0.5 ? 1 : 0) : (z > 0.5 ? 3 : 2); }
            default -> { return 0; }
        }
    }

    public InteractionResult onRightClick(Level level, BlockPos pos, Player player, BlockState state, ItemStack heldItem, int slot) {
        if (slot < 0 || slot >= inventory.size()) return InteractionResult.PASS;

        ItemStack slotStack = inventory.get(slot);

        // === MODIFIED LOGIC START ===
        if (slotStack.isEmpty()) {
            if (!heldItem.isEmpty()) {
                // Place entire stack into the empty slot
                inventory.set(slot, heldItem.copy());
                heldItem.setCount(0);
                setChanged();
                level.sendBlockUpdated(pos, state, state, 3);
                return InteractionResult.CONSUME;
            }
        } else {
            // Try stacking if same item and compatible
            if (ItemStack.matches(slotStack, heldItem)) {
                int space = slotStack.getMaxStackSize() - slotStack.getCount();
                if (space > 0) {
                    int transfer = Math.min(space, heldItem.getCount());
                    slotStack.grow(transfer);
                    heldItem.shrink(transfer);
                    setChanged();
                    level.sendBlockUpdated(pos, state, state, 3);
                    return InteractionResult.CONSUME;
                }
            }

            // If not stackable or full, return the item to player
            if (!player.addItem(slotStack)) {
                player.drop(slotStack, false);
            }
            inventory.set(slot, ItemStack.EMPTY);
            setChanged();
            level.sendBlockUpdated(pos, state, state, 3);
            return InteractionResult.CONSUME;
        }

        return InteractionResult.PASS;
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registryLookup) {
        return this.saveWithoutMetadata(registryLookup);
    }

    @Override
    public void loadAdditional(CompoundTag nbt, HolderLookup.Provider registryLookup) {
        super.loadAdditional(nbt, registryLookup);
        this.inventory.clear();
        ContainerHelper.loadAllItems(nbt, this.inventory, registryLookup);
        this.setChanged();
    }

    @Override
    public void saveAdditional(CompoundTag nbt, HolderLookup.Provider registryLookup) {
        super.saveAdditional(nbt, registryLookup);
        ContainerHelper.saveAllItems(nbt, this.inventory, registryLookup);
    }

    public Component getDisplayName() {
        return Component.literal("Oak Shelf");

    }

    public NonNullList<ItemStack> getItems() {
        return inventory;
    }
}