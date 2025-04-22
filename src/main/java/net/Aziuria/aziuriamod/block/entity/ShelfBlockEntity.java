package net.Aziuria.aziuriamod.block.entity;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.NonNullList;
import org.jetbrains.annotations.Nullable;

public class ShelfBlockEntity extends BlockEntity {
    public static final Codec<ShelfBlockEntity> CODEC = Codec.unit(() -> new ShelfBlockEntity(BlockPos.ZERO, null));

    private final NonNullList<ItemStack> items = NonNullList.withSize(4, ItemStack.EMPTY); // 4-slot shelf

    public ShelfBlockEntity(BlockPos pos, @Nullable BlockState state) {
        super(ModBlockEntities.SHELF_BLOCK_ENTITY.get(), pos, state);
    }

    public static int getClickedSlot(Vec3 hit, Direction facing) {
        double x = hit.x;
        double y = hit.y;
        double z = hit.z;

        switch (facing) {
            case NORTH -> {
                if (y > 0.5) return x > 0.5 ? 1 : 0;
                else return x > 0.5 ? 3 : 2;
            }
            case SOUTH -> {
                if (y > 0.5) return x < 0.5 ? 1 : 0;
                else return x < 0.5 ? 3 : 2;
            }
            case WEST -> {
                if (y > 0.5) return z < 0.5 ? 1 : 0;
                else return z < 0.5 ? 3 : 2;
            }
            case EAST -> {
                if (y > 0.5) return z > 0.5 ? 1 : 0;
                else return z > 0.5 ? 3 : 2;
            }
            default -> {
                return 0;
            }
        }
    }

    public InteractionResult onRightClick(Level level, BlockPos pos, Player player, BlockState state, ItemStack heldItem, int slot) {
        if (slot < 0 || slot >= items.size()) return InteractionResult.PASS;

        if (items.get(slot).isEmpty()) {
            if (!heldItem.isEmpty()) {
                items.set(slot, heldItem.copyWithCount(1));
                heldItem.shrink(1);
                setChanged();
                level.sendBlockUpdated(pos, state, state, 3);
                return InteractionResult.SUCCESS;
            }
        }
        if (!items.get(slot).isEmpty()) {
            if (!player.addItem(items.get(slot))) {
                player.drop(items.get(slot), false);
            }
            items.set(slot, ItemStack.EMPTY);
            setChanged();
            level.sendBlockUpdated(pos, state, state, 3);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        for (int i = 0; i < items.size(); i++) {
            if (tag.contains("Item" + i)) {
                CompoundTag itemTag = tag.getCompound("Item" + i);
                items.set(i, ItemStack.parse(registries, itemTag).orElse(ItemStack.EMPTY));
            }
        }
    }

    @Override
    public void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.saveAdditional(tag, provider);

        for (int i = 0; i < items.size(); i++) {
            if (!items.get(i).isEmpty()) {
                CompoundTag itemTag = new CompoundTag();
                items.get(i).save(provider, itemTag);
                tag.put("Item" + i, itemTag);
            }
        }
    }

    public NonNullList<ItemStack> getItems() {
        return items;
    }
}