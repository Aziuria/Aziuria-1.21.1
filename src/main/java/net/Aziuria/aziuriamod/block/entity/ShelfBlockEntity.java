package net.Aziuria.aziuriamod.block.entity;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ShelfBlockEntity extends BlockEntity {
    public static final Codec<ShelfBlockEntity> CODEC = Codec.unit(() -> new ShelfBlockEntity(BlockPos.ZERO, null));

    private final ItemStack[] items = new ItemStack[6];

    public ShelfBlockEntity(BlockPos pos, @Nullable BlockState state) {
        super(ModBlockEntities.SHELF_BLOCK_ENTITY.get(), pos, state);

        for (int i = 0; i < items.length; i++) {
            items[i] = ItemStack.EMPTY;
        }
    }

    public ItemStack getItem(int index) {
        return items[index];
    }

    public void setItem(int index, ItemStack item) {
        items[index] = item;
        setChanged();
    }

    public InteractionResult onRightClick(Level level, BlockPos pos, Player player, BlockState state, ItemStack stack) {
        if (level.isClientSide) return InteractionResult.SUCCESS;

        // Add item
        if (!stack.isEmpty()) {
            for (int i = 0; i < items.length; i++) {
                if (items[i].isEmpty()) {
                    items[i] = stack.split(1);
                    setChanged();
                    return InteractionResult.CONSUME;
                }
            }
        }
        // Remove item
        else {
            for (int i = 0; i < items.length; i++) {
                if (!items[i].isEmpty()) {
                    player.addItem(items[i].copy());
                    items[i] = ItemStack.EMPTY;
                    setChanged();
                    return InteractionResult.CONSUME;
                }
            }
        }

        return InteractionResult.PASS;
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        for (int i = 0; i < items.length; i++) {
            if (tag.contains("Item" + i)) {
                CompoundTag itemTag = tag.getCompound("Item" + i);
                items[i] = ItemStack.parse(registries, itemTag).orElse(ItemStack.EMPTY);
            }
        }
    }

    @Override
    public void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.saveAdditional(tag, provider);

        for (int i = 0; i < items.length; i++) {
            if (!items[i].isEmpty()) {
                CompoundTag itemTag = new CompoundTag();
                items[i].save(provider, itemTag);
                tag.put("Item" + i, itemTag);
            }
        }
    }
}