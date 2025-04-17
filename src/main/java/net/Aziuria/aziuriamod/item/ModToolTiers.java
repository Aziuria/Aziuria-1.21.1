package net.Aziuria.aziuriamod.item;

import net.Aziuria.aziuriamod.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {

    public static final Tier STEEL = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_STEEL_TOOL,
            700, 7f, 3f, 12, () -> Ingredient.of(ModItems.STEEL_INGOT));

}
