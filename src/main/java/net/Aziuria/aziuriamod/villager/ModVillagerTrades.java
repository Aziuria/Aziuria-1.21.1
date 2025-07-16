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

            addBuyTrade(event, 1, Items.WHEAT.getDefaultInstance(), 7, 2);
            addSellTrade(event, 1, Items.WHEAT_SEEDS.getDefaultInstance(), 8, 1, 2);

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

            // === LEVEL 4 TRADES (late game, XP = 10) ===
            addBuyTrade(event, 4, Items.WOODEN_HOE.getDefaultInstance(), 5, 1, 10);
            addSellTrade(event, 4, Items.IRON_HOE.getDefaultInstance(), 1, 1, 10);

            addBuyTrade(event, 4, Items.IRON_HOE.getDefaultInstance(), 7, 1, 15);
            addSellTrade(event, 4, Items.DIAMOND_HOE.getDefaultInstance(), 1, 1, 16);

            // === LEVEL 5 TRADES (late game, XP = 10) ===
            addBuyTrade(event, 5, Items.DIAMOND_HOE.getDefaultInstance(), 15, 1, 20);
            addSellTrade(event, 5, Items.NETHERITE_HOE.getDefaultInstance(), 1, 4, 18);

            addBuyTrade(event, 4, Items.NETHERITE_HOE.getDefaultInstance(), 25, 1, 25);
            addSellTrade(event, 4, ModItems.STEEL_HOE.get().getDefaultInstance(), 1, 3, 16);
        }

        if (event.getType() == ModVillagers.MINER.value()) {
            // === MINER TRADES ===

            // LEVEL 1 - Raw ores
            addBuyTrade(event, 1, Items.COAL.getDefaultInstance(), 4, 3);
            addBuyTrade(event, 1, Items.IRON_ORE.getDefaultInstance(), 5, 2);
            addBuyTrade(event, 1, Items.GOLD_ORE.getDefaultInstance(), 6, 2);


            addSellTrade(event, 1, Items.COAL.getDefaultInstance(), 5, 1, 2);
            addSellTrade(event, 1, Items.IRON_ORE.getDefaultInstance(), 6, 1, 2);
            addSellTrade(event, 1, Items.GOLD_ORE.getDefaultInstance(), 7, 1, 2);

            // LEVEL 2 - Ingots
            addBuyTrade(event, 2, Items.IRON_INGOT.getDefaultInstance(), 6, 2, 5);
            addBuyTrade(event, 2, Items.GOLD_INGOT.getDefaultInstance(), 7, 2, 5);
            addSellTrade(event, 2, Items.IRON_INGOT.getDefaultInstance(), 8, 1, 5);
            addSellTrade(event, 2, Items.GOLD_INGOT.getDefaultInstance(), 9, 1, 5);

            // LEVEL 3 - Pickaxes
            addBuyTrade(event, 3, Items.STONE_PICKAXE.getDefaultInstance(), 10, 1, 8);
            addBuyTrade(event, 3, Items.IRON_PICKAXE.getDefaultInstance(), 15, 1, 8);
            addSellTrade(event, 3, Items.STONE_PICKAXE.getDefaultInstance(), 1, 3, 8);
            addSellTrade(event, 3, Items.IRON_PICKAXE.getDefaultInstance(), 1, 5, 8);

            // LEVEL 4 - Better tools
            addBuyTrade(event, 4, Items.DIAMOND_PICKAXE.getDefaultInstance(), 30, 1, 12);
            addSellTrade(event, 4, Items.DIAMOND_PICKAXE.getDefaultInstance(), 1, 10, 12);

            addBuyTrade(event, 4, Items.GOLDEN_PICKAXE.getDefaultInstance(), 15, 1, 12);
            addSellTrade(event, 4, Items.GOLDEN_PICKAXE.getDefaultInstance(), 1, 6, 12);

            // LEVEL 5 - Advanced or custom tools
            addBuyTrade(event, 5, Items.NETHERITE_PICKAXE.getDefaultInstance(), 50, 1, 20);
            addSellTrade(event, 5, Items.NETHERITE_PICKAXE.getDefaultInstance(), 1, 15, 20);
        }

        if (event.getType() == ModVillagers.WOODCUTTER.value()) {
            // === WOODCUTTER TRADES ===

            // LEVEL 1 - Logs
            addBuyTrade(event, 1, Items.OAK_LOG.getDefaultInstance(), 4, 4);
            addBuyTrade(event, 1, Items.SPRUCE_LOG.getDefaultInstance(), 4, 4);
            addBuyTrade(event, 1, Items.BIRCH_LOG.getDefaultInstance(), 4, 4);

            addSellTrade(event, 1, Items.OAK_LOG.getDefaultInstance(), 6, 1, 2);
            addSellTrade(event, 1, Items.SPRUCE_LOG.getDefaultInstance(), 6, 1, 2);

            // LEVEL 2 - Planks
            addBuyTrade(event, 2, Items.OAK_PLANKS.getDefaultInstance(), 2, 6, 5);
            addBuyTrade(event, 2, Items.SPRUCE_PLANKS.getDefaultInstance(), 2, 6, 5);
            addSellTrade(event, 2, Items.OAK_PLANKS.getDefaultInstance(), 4, 1, 5);
            addSellTrade(event, 2, Items.SPRUCE_PLANKS.getDefaultInstance(), 4, 1, 5);

            // LEVEL 3 - Axes
            addBuyTrade(event, 3, Items.STONE_AXE.getDefaultInstance(), 8, 1, 8);
            addBuyTrade(event, 3, Items.IRON_AXE.getDefaultInstance(), 12, 1, 8);
            addSellTrade(event, 3, Items.STONE_AXE.getDefaultInstance(), 1, 3, 8);
            addSellTrade(event, 3, Items.IRON_AXE.getDefaultInstance(), 1, 5, 8);

            // LEVEL 4 - Better axes
            addBuyTrade(event, 4, Items.DIAMOND_AXE.getDefaultInstance(), 25, 1, 12);
            addSellTrade(event, 4, Items.DIAMOND_AXE.getDefaultInstance(), 1, 10, 12);

            // LEVEL 5 - Advanced or custom axe
            addBuyTrade(event, 5, Items.NETHERITE_AXE.getDefaultInstance(), 40, 1, 20);
            addSellTrade(event, 5, Items.NETHERITE_AXE.getDefaultInstance(), 1, 15, 20);
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