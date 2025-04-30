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

public class StorageBlockEntity extends BlockEntity {
    public final NonNullList<ItemStack> inventory;

    public StorageBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.STORAGE_BLOCK_ENTITY.get(), pos, state);
        this.inventory = NonNullList.withSize(2, ItemStack.EMPTY);
    }

    public static int getClickedSlot(Vec3 hit, Direction facing) {
        double x = hit.x;
        double z = hit.z;

        // Determine slot based on horizontal division (left/right from player view)
        return switch (facing) {
            case NORTH -> x > 0.5 ? 1 : 0; // Right is slot 1, Left is slot 0
            case SOUTH -> x < 0.5 ? 1 : 0;
            case WEST  -> z < 0.5 ? 1 : 0;
            case EAST  -> z > 0.5 ? 1 : 0;
            default -> 0;
        };
    }

    public InteractionResult onRightClick(Level level, BlockPos pos, Player player, BlockState state, ItemStack heldItem, int slot) {
        if (slot < 0 || slot >= inventory.size()) return InteractionResult.PASS;

        if (inventory.get(slot).isEmpty()) {
            if (!heldItem.isEmpty()) {
                inventory.set(slot, heldItem.copyWithCount(1));
                heldItem.shrink(1);
                setChanged();
                level.sendBlockUpdated(pos, state, state, 3);
                return InteractionResult.CONSUME;
            }
        } else {
            if (!player.addItem(inventory.get(slot))) {
                player.drop(inventory.get(slot), false);
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