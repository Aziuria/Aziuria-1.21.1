package net.Aziuria.aziuriamod.block.entity.custom;

import net.Aziuria.aziuriamod.block.entity.ModBlockEntities;
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
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class HookBlockEntity extends BlockEntity {
    public final NonNullList<ItemStack> inventory;

    public HookBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.HOOK_BLOCK_ENTITY.get(), pos, state);
        this.inventory = NonNullList.withSize(1, ItemStack.EMPTY); // CHANGED: 4 ➔ 1 slot
    }

    public static int getClickedSlot(Vec3 hit, Direction facing) {
        // We now have only 1 slot, always return 0
        return 0;
    }

    public InteractionResult onRightClick(Level level, BlockPos pos, Player player, BlockState state, ItemStack heldItem, int slot) {
        if (slot != 0) return InteractionResult.PASS; // Single slot only

        // Check if held item is one of the allowed tools
        if (!heldItem.isEmpty() && !(heldItem.getItem() instanceof SwordItem
                || heldItem.getItem() instanceof PickaxeItem
                || heldItem.getItem() instanceof HoeItem
                || heldItem.getItem() instanceof ShovelItem
                || heldItem.getItem() instanceof AxeItem)) {
            // Not a tool we allow, so reject it
            return InteractionResult.PASS;
        }

        ItemStack slotStack = inventory.get(0);

        if (slotStack.isEmpty()) {
            if (!heldItem.isEmpty()) {
                inventory.set(0, heldItem.copy());
                heldItem.setCount(0);
                setChanged();
                level.sendBlockUpdated(pos, state, state, 3);
                return InteractionResult.CONSUME;
            }
        } else {
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
            if (!player.addItem(slotStack)) {
                player.drop(slotStack, false);
            }
            inventory.set(0, ItemStack.EMPTY);
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
        return Component.literal("Oak Hook");
    }

    public NonNullList<ItemStack> getItems() {
        return inventory;
    }
}