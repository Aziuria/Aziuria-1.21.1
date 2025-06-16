package net.Aziuria.aziuriamod.villager;

import net.Aziuria.aziuriamod.item.ModItems;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.BasicItemListing;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

public class ModVillagerTrades {

    public static void onVillagerTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.FARMER) {

            // LEVEL 1 TRADES (early game, XP = 2)

            // Villager sells 2 cucumbers for 3 emeralds (player buys cucumbers)
            event.getTrades().get(1).add(new BasicItemListing(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(ModItems.CUCUMBER.get(), 2),
                    8, 2, 0.05F
            ));

            // Player sells 8 cucumbers for 1 emerald (villager buys cucumbers)
            event.getTrades().get(1).add(new BasicItemListing(
                    new ItemStack(ModItems.CUCUMBER.get(), 8),
                    new ItemStack(Items.EMERALD, 1),
                    8, 2, 0.05F
            ));

            // Villager sells 2 cucumber seeds for 3 emeralds
            event.getTrades().get(1).add(new BasicItemListing(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(ModItems.CUCUMBER_SEEDS.get(), 2),
                    8, 2, 0.05F
            ));

            // Player sells 10 cucumber seeds for 1 emerald
            event.getTrades().get(1).add(new BasicItemListing(
                    new ItemStack(ModItems.CUCUMBER_SEEDS.get(), 10),
                    new ItemStack(Items.EMERALD, 1),
                    8, 2, 0.05F
            ));

            // Villager sells 2 tomato seeds for 3 emeralds
            event.getTrades().get(1).add(new BasicItemListing(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(ModItems.TOMATO_SEEDS.get(), 2),
                    8, 2, 0.05F
            ));

            // Player sells 10 tomato seeds for 1 emerald
            event.getTrades().get(1).add(new BasicItemListing(
                    new ItemStack(ModItems.TOMATO_SEEDS.get(), 10),
                    new ItemStack(Items.EMERALD, 1),
                    8, 2, 0.05F
            ));

            // Villager sells 2 radish seeds for 3 emeralds
            event.getTrades().get(1).add(new BasicItemListing(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(ModItems.RADISH_SEEDS.get(), 2),
                    8, 2, 0.05F
            ));

            // Player sells 10 radish seeds for 1 emerald
            event.getTrades().get(1).add(new BasicItemListing(
                    new ItemStack(ModItems.RADISH_SEEDS.get(), 10),
                    new ItemStack(Items.EMERALD, 1),
                    8, 2, 0.05F
            ));

            // LEVEL 2 TRADES (mid game, XP = 5)

            // Villager sells 2 tomatoes for 4 emeralds
            event.getTrades().get(2).add(new BasicItemListing(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(ModItems.TOMATO.get(), 2),
                    8, 5, 0.05F
            ));

            // Player sells 7 tomatoes for 1 emerald
            event.getTrades().get(2).add(new BasicItemListing(
                    new ItemStack(ModItems.TOMATO.get(), 7),
                    new ItemStack(Items.EMERALD, 1),
                    8, 5, 0.05F
            ));

            // LEVEL 3 TRADES (late game, XP = 10)

            // Villager sells 3 radishes for 5 emeralds
            event.getTrades().get(3).add(new BasicItemListing(
                    new ItemStack(Items.EMERALD, 5),
                    new ItemStack(ModItems.RADISH.get(), 3),
                    6, 10, 0.05F
            ));

            // Player sells 9 radishes for 1 emerald
            event.getTrades().get(3).add(new BasicItemListing(
                    new ItemStack(ModItems.RADISH.get(), 9),
                    new ItemStack(Items.EMERALD, 1),
                    6, 10, 0.05F
            ));
        }
    }
}