package net.Aziuria.aziuriamod.item.custom;

import net.Aziuria.aziuriamod.data.ModDataComponents;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;

import java.util.Random;

public class BaitedFishingRodItem extends FishingRodItem {

    private static final Random RANDOM = new Random();

    public BaitedFishingRodItem(Properties properties) {
        super(properties);
    }

    // --- Keep crafting damage retention active ---
    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true; // Treat as container: Minecraft will call getCraftingRemainingItem
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack stack) {
        ItemStack copy = stack.copy(); // Critical: copies damage & NBT

        // Initialize persistent components if missing
        if (!copy.has(ModDataComponents.ROD_CAUGHT_COUNT.get())) {
            copy.set(ModDataComponents.ROD_CAUGHT_COUNT.get(), 0);
        }
        if (!copy.has(ModDataComponents.ROD_MAX_BEFORE_REVERT.get())) {
            copy.set(ModDataComponents.ROD_MAX_BEFORE_REVERT.get(), RANDOM.nextInt(4) + 1);
        }

        return copy;
    }
}