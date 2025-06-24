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

            // === LEVEL 1 TRADES (early game, XP = 2) ===

            // Cucumbers
            event.getTrades().get(1).add(new BasicItemListing(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(ModItems.CUCUMBER.get(), 2),
                    8, 2, 0.05F
            ));
            event.getTrades().get(1).add(new BasicItemListing(
                    new ItemStack(ModItems.CUCUMBER.get(), 8),
                    new ItemStack(Items.EMERALD, 1),
                    8, 2, 0.05F
            ));

            // Cucumber Seeds
            event.getTrades().get(1).add(new BasicItemListing(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(ModItems.CUCUMBER_SEEDS.get(), 2),
                    8, 2, 0.05F
            ));
            event.getTrades().get(1).add(new BasicItemListing(
                    new ItemStack(ModItems.CUCUMBER_SEEDS.get(), 10),
                    new ItemStack(Items.EMERALD, 1),
                    8, 2, 0.05F
            ));

            // Tomato Seeds
            event.getTrades().get(1).add(new BasicItemListing(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(ModItems.TOMATO_SEEDS.get(), 2),
                    8, 2, 0.05F
            ));
            event.getTrades().get(1).add(new BasicItemListing(
                    new ItemStack(ModItems.TOMATO_SEEDS.get(), 10),
                    new ItemStack(Items.EMERALD, 1),
                    8, 2, 0.05F
            ));

            // Radish Seeds
            event.getTrades().get(1).add(new BasicItemListing(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(ModItems.RADISH_SEEDS.get(), 2),
                    8, 2, 0.05F
            ));
            event.getTrades().get(1).add(new BasicItemListing(
                    new ItemStack(ModItems.RADISH_SEEDS.get(), 10),
                    new ItemStack(Items.EMERALD, 1),
                    8, 2, 0.05F
            ));

            // Lettuce Seeds
            event.getTrades().get(1).add(new BasicItemListing(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(ModItems.LETTUCE_SEEDS.get(), 2),
                    8, 2, 0.05F
            ));
            event.getTrades().get(1).add(new BasicItemListing(
                    new ItemStack(ModItems.LETTUCE_SEEDS.get(), 10),
                    new ItemStack(Items.EMERALD, 1),
                    8, 2, 0.05F
            ));

            // Onion Seeds
            event.getTrades().get(1).add(new BasicItemListing(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(ModItems.ONION_SEEDS.get(), 2),
                    8, 2, 0.05F
            ));
            event.getTrades().get(1).add(new BasicItemListing(
                    new ItemStack(ModItems.ONION_SEEDS.get(), 10),
                    new ItemStack(Items.EMERALD, 1),
                    8, 2, 0.05F
            ));

            // Spring Onion Seeds
            event.getTrades().get(1).add(new BasicItemListing(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(ModItems.SPRING_ONION_SEEDS.get(), 2),
                    8, 2, 0.05F
            ));
            event.getTrades().get(1).add(new BasicItemListing(
                    new ItemStack(ModItems.SPRING_ONION_SEEDS.get(), 10),
                    new ItemStack(Items.EMERALD, 1),
                    8, 2, 0.05F
            ));

            // === LEVEL 2 TRADES (mid game, XP = 5) ===

            // Tomatoes
            event.getTrades().get(2).add(new BasicItemListing(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(ModItems.TOMATO.get(), 2),
                    8, 5, 0.05F
            ));
            event.getTrades().get(2).add(new BasicItemListing(
                    new ItemStack(ModItems.TOMATO.get(), 7),
                    new ItemStack(Items.EMERALD, 1),
                    8, 5, 0.05F
            ));

            // Lettuce
            event.getTrades().get(2).add(new BasicItemListing(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(ModItems.LETTUCE.get(), 2),
                    8, 5, 0.05F
            ));
            event.getTrades().get(2).add(new BasicItemListing(
                    new ItemStack(ModItems.LETTUCE.get(), 7),
                    new ItemStack(Items.EMERALD, 1),
                    8, 5, 0.05F
            ));

            // Onion
            event.getTrades().get(2).add(new BasicItemListing(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(ModItems.ONION.get(), 2),
                    8, 5, 0.05F
            ));
            event.getTrades().get(2).add(new BasicItemListing(
                    new ItemStack(ModItems.ONION.get(), 7),
                    new ItemStack(Items.EMERALD, 1),
                    8, 5, 0.05F
            ));

            // Spring Onion
            event.getTrades().get(2).add(new BasicItemListing(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(ModItems.SPRING_ONION.get(), 2),
                    8, 5, 0.05F
            ));
            event.getTrades().get(2).add(new BasicItemListing(
                    new ItemStack(ModItems.SPRING_ONION.get(), 7),
                    new ItemStack(Items.EMERALD, 1),
                    8, 5, 0.05F
            ));

            // === LEVEL 3 TRADES (late game, XP = 10) ===

            // Radish
            event.getTrades().get(3).add(new BasicItemListing(
                    new ItemStack(Items.EMERALD, 5),
                    new ItemStack(ModItems.RADISH.get(), 3),
                    6, 10, 0.05F
            ));
            event.getTrades().get(3).add(new BasicItemListing(
                    new ItemStack(ModItems.RADISH.get(), 9),
                    new ItemStack(Items.EMERALD, 1),
                    6, 10, 0.05F
            ));
        }
    }
}