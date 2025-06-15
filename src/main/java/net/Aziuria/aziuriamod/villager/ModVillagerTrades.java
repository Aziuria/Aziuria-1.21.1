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

            // LEVEL 1 TRADES
            // Buy 4 cucumbers for 1 emerald
            event.getTrades().get(1).add(new BasicItemListing(
                    new ItemStack(ModItems.CUCUMBER.get(), 4),
                    new ItemStack(Items.EMERALD, 1),
                    10, 2, 0.05F
            ));

            // Sell 4 cucumber seeds for 1 emerald
            event.getTrades().get(1).add(new BasicItemListing(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(ModItems.CUCUMBER_SEEDS.get(), 4),
                    10, 2, 0.05F
            ));

            // Sell 4 tomato seeds for 1 emerald
            event.getTrades().get(1).add(new BasicItemListing(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(ModItems.TOMATO_SEEDS.get(), 4),
                    10, 2, 0.05F
            ));

            // Sell 4 radish seeds for 1 emerald
            event.getTrades().get(1).add(new BasicItemListing(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(ModItems.RADISH_SEEDS.get(), 4),
                    10, 2, 0.05F
            ));

            // LEVEL 2 TRADES
            // Sell 2 tomatoes for 1 emerald
            event.getTrades().get(2).add(new BasicItemListing(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(ModItems.TOMATO.get(), 2),
                    10, 5, 0.05F
            ));

            // Buy 3 tomatoes for 1 emerald (optional, to let player sell back tomatoes)
            event.getTrades().get(2).add(new BasicItemListing(
                    new ItemStack(ModItems.TOMATO.get(), 3),
                    new ItemStack(Items.EMERALD, 1),
                    10, 5, 0.05F
            ));

            // LEVEL 3 TRADES
            // Buy 5 radishes for 1 emerald
            event.getTrades().get(3).add(new BasicItemListing(
                    new ItemStack(ModItems.RADISH.get(), 5),
                    new ItemStack(Items.EMERALD, 1),
                    8, 10, 0.05F
            ));

            // Sell 3 radishes for 1 emerald
            event.getTrades().get(3).add(new BasicItemListing(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(ModItems.RADISH.get(), 3),
                    8, 10, 0.05F
            ));
        }
    }
}