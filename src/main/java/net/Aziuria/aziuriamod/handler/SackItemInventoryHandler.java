package net.Aziuria.aziuriamod.handler;

import net.Aziuria.aziuriamod.data.ModDataComponents;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.minecraft.nbt.CompoundTag;

public class SackItemInventoryHandler {

    public static ItemStackHandler getInventory(ItemStack stack, HolderLookup.Provider registries) {
        CompoundTag tag = stack.getOrDefault(ModDataComponents.SACK_INVENTORY.get(), new CompoundTag());
        ItemStackHandler handler = new ItemStackHandler(3);
        handler.deserializeNBT(registries, tag);  // ✅ Correct usage
        return handler;
    }

    public static void saveInventory(ItemStack stack, ItemStackHandler handler, HolderLookup.Provider provider) {
        CompoundTag tag = handler.serializeNBT(provider); // <-- pass provider here
        stack.set(ModDataComponents.SACK_INVENTORY.get(), tag);

    }
}