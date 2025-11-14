package net.Aziuria.aziuriamod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
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

public class FishTrapBlockEntity extends BlockEntity {

    public final NonNullList<ItemStack> inventory;

    public FishTrapBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FISH_TRAP_BLOCK_ENTITY.get(), pos, state);
        this.inventory = NonNullList.withSize(4, ItemStack.EMPTY);
    }

    /**
     * Determines which slot (0-3) was clicked based on hit position and block facing.
     * Layout:
     * 0 1
     * 2 3
     */
    public static int getClickedSlot(Vec3 hit, Direction facing) {
        double x = hit.x;
        double z = hit.z;

        return switch (facing) {
            case SOUTH -> (z > 0.5 ? 2 : 0) + (x > 0.5 ? 1 : 0);
            case NORTH -> (z < 0.5 ? 2 : 0) + (x < 0.5 ? 1 : 0);
            case EAST  -> (x < 0.5 ? 2 : 0) + (z < 0.5 ? 1 : 0);
            case WEST  -> (x > 0.5 ? 2 : 0) + (z > 0.5 ? 1 : 0);
            default -> 0;
        };
    }

    /**
     * Handles right-click interactions on the fish trap.
     */
    public InteractionResult onRightClick(Level level, BlockPos pos, Player player, ItemStack heldItem, int slot) {
        if (slot < 0 || slot >= inventory.size()) return InteractionResult.PASS;

        ItemStack slotStack = inventory.get(slot);

        // Place item if empty
        if (slotStack.isEmpty()) {
            if (!heldItem.isEmpty()) {
                inventory.set(slot, heldItem.copyWithCount(1));
                heldItem.shrink(1);
                setChanged();
                level.sendBlockUpdated(pos, getBlockState(), getBlockState(), 3);
                return InteractionResult.CONSUME;
            }
        } else {
            // Try stacking with existing item
            if (ItemStack.matches(slotStack, heldItem) && slotStack.getCount() < slotStack.getMaxStackSize()) {
                int space = slotStack.getMaxStackSize() - slotStack.getCount();
                int add = Math.min(heldItem.getCount(), space);
                slotStack.grow(add);
                heldItem.shrink(add);
                setChanged();
                level.sendBlockUpdated(pos, getBlockState(), getBlockState(), 3);
                return InteractionResult.CONSUME;
            }

            // Drop item if cannot stack
            if (!player.addItem(slotStack)) {
                player.drop(slotStack, false);
            }
            inventory.set(slot, ItemStack.EMPTY);
            setChanged();
            level.sendBlockUpdated(pos, getBlockState(), getBlockState(), 3);
            return InteractionResult.CONSUME;
        }

        return InteractionResult.PASS;
    }

    public NonNullList<ItemStack> getItems() {
        return inventory;
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
        return Component.literal("Fish Trap");
    }
}