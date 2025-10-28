package net.Aziuria.aziuriamod.item.custom;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;



public class KnifeItem extends SwordItem {

    public KnifeItem(Tier tier, Properties properties) {
        super(tier, properties);
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
            return ItemStack.EMPTY;
        }
        return copy;
    }

}