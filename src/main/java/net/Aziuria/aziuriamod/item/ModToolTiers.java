package net.Aziuria.aziuriamod.item;

import net.Aziuria.aziuriamod.util.ModTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {

    public static final Tier STEEL = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_STEEL_TOOL,
            550, 6.5f, 2.0f, 14, () -> Ingredient.of(ModItems.STEEL_INGOT));

    public static final Tier SPINEL = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_SPINEL_TOOL,
            750, 8.0f, 3.0f, 12, () -> Ingredient.of(ModItems.SPINEL));

    public static final Tier COPPER = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_COPPER_TOOL,
            180, 5.0f, 1.5f, 10, () -> Ingredient.of(Items.COPPER_INGOT));

}
