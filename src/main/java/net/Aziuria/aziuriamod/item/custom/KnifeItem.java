package net.Aziuria.aziuriamod.item.custom;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;

public class KnifeItem extends SwordItem {

    public KnifeItem(Properties properties) {
        super(Tiers.WOOD, properties);
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack stack) {
        ItemStack copy = stack.copy();
        copy.setDamageValue(copy.getDamageValue() + 1);
        if (copy.getDamageValue() >= copy.getMaxDamage()) {
            return ItemStack.EMPTY; // break when fully damaged
        }
        return copy;
    }
}