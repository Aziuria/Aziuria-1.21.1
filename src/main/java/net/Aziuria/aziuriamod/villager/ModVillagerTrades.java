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
            addBuyTrade(event, 1, ModItems.CUCUMBER.get().getDefaultInstance(), 3, 2);
            addSellTrade(event, 1, ModItems.CUCUMBER.get().getDefaultInstance(), 8, 1, 2);

            addBuyTrade(event, 1, ModItems.CUCUMBER_SEEDS.get().getDefaultInstance(), 3, 2);
            addSellTrade(event, 1, ModItems.CUCUMBER_SEEDS.get().getDefaultInstance(), 10, 1, 2);

            addBuyTrade(event, 1, ModItems.TOMATO_SEEDS.get().getDefaultInstance(), 3, 2);
            addSellTrade(event, 1, ModItems.TOMATO_SEEDS.get().getDefaultInstance(), 10, 1, 2);

            addBuyTrade(event, 1, ModItems.RADISH_SEEDS.get().getDefaultInstance(), 3, 2);
            addSellTrade(event, 1, ModItems.RADISH_SEEDS.get().getDefaultInstance(), 10, 1, 2);

            addBuyTrade(event, 1, ModItems.LETTUCE_SEEDS.get().getDefaultInstance(), 3, 2);
            addSellTrade(event, 1, ModItems.LETTUCE_SEEDS.get().getDefaultInstance(), 10, 1, 2);

            addBuyTrade(event, 1, ModItems.ONION_SEEDS.get().getDefaultInstance(), 3, 2);
            addSellTrade(event, 1, ModItems.ONION_SEEDS.get().getDefaultInstance(), 10, 1, 2);

            addBuyTrade(event, 1, ModItems.SPRING_ONION_SEEDS.get().getDefaultInstance(), 3, 2);
            addSellTrade(event, 1, ModItems.SPRING_ONION_SEEDS.get().getDefaultInstance(), 10, 1, 2);

            // === NEW LEVEL 1 SEED TRADES ===
            addBuyTrade(event, 1, ModItems.PINEAPPLE_SEEDS.get().getDefaultInstance(), 3, 2);
            addSellTrade(event, 1, ModItems.PINEAPPLE_SEEDS.get().getDefaultInstance(), 10, 1, 2);

            addBuyTrade(event, 1, ModItems.CORN_SEEDS.get().getDefaultInstance(), 3, 2);
            addSellTrade(event, 1, ModItems.CORN_SEEDS.get().getDefaultInstance(), 10, 1, 2);

            // === LEVEL 2 TRADES (mid game, XP = 5) ===
            addBuyTrade(event, 2, ModItems.TOMATO.get().getDefaultInstance(), 4, 2, 5);
            addSellTrade(event, 2, ModItems.TOMATO.get().getDefaultInstance(), 7, 1, 5);

            addBuyTrade(event, 2, ModItems.LETTUCE.get().getDefaultInstance(), 4, 2, 5);
            addSellTrade(event, 2, ModItems.LETTUCE.get().getDefaultInstance(), 7, 1, 5);

            addBuyTrade(event, 2, ModItems.ONION.get().getDefaultInstance(), 4, 2, 5);
            addSellTrade(event, 2, ModItems.ONION.get().getDefaultInstance(), 7, 1, 5);

            addBuyTrade(event, 2, ModItems.SPRING_ONION.get().getDefaultInstance(), 4, 2, 5);
            addSellTrade(event, 2, ModItems.SPRING_ONION.get().getDefaultInstance(), 7, 1, 5);

            // === NEW LEVEL 2 CROP TRADES ===
            addBuyTrade(event, 2, ModItems.CORN.get().getDefaultInstance(), 4, 2, 5);
            addSellTrade(event, 2, ModItems.CORN.get().getDefaultInstance(), 7, 1, 5);

            addBuyTrade(event, 2, ModItems.PINEAPPLE.get().getDefaultInstance(), 4, 2, 5);
            addSellTrade(event, 2, ModItems.PINEAPPLE.get().getDefaultInstance(), 7, 1, 5);

            // === LEVEL 3 TRADES (late game, XP = 10) ===
            addBuyTrade(event, 3, ModItems.RADISH.get().getDefaultInstance(), 5, 3, 10);
            addSellTrade(event, 3, ModItems.RADISH.get().getDefaultInstance(), 9, 1, 10);
        }
    }

    private static void addBuyTrade(VillagerTradesEvent event, int level, ItemStack sellingItem, int price, int count) {
        event.getTrades().get(level).add(new BasicItemListing(
                price,
                sellingItem.copyWithCount(count),
                Integer.MAX_VALUE,
                2,
                0.05F
        ));
    }

    private static void addBuyTrade(VillagerTradesEvent event, int level, ItemStack sellingItem, int price, int count, int xp) {
        event.getTrades().get(level).add(new BasicItemListing(
                price,
                sellingItem.copyWithCount(count),
                Integer.MAX_VALUE,
                xp,
                0.05F
        ));
    }

    private static void addSellTrade(VillagerTradesEvent event, int level, ItemStack buyingItem, int count, int emeraldsReceived, int xp) {
        event.getTrades().get(level).add(new BasicItemListing(
                buyingItem.copyWithCount(count),
                new ItemStack(Items.EMERALD, emeraldsReceived),
                Integer.MAX_VALUE,
                xp,
                0.05F
        ));
    }
}