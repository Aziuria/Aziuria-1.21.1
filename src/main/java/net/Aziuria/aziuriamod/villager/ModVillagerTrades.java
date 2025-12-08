package net.Aziuria.aziuriamod.villager;

import net.Aziuria.aziuriamod.block.ModBlocks;
import net.Aziuria.aziuriamod.item.ModItems;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.BasicItemListing;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

public class ModVillagerTrades {

    public static void onVillagerTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.FARMER) {

            // === LEVEL 1 TRADES (early game, XP = 2) ===
            addBuyTrade(event, 1, ModItems.CUCUMBER.get().getDefaultInstance(), 4, 2,2);
            addSellTrade(event, 1, ModItems.CUCUMBER.get().getDefaultInstance(), 8, 1, 2);

            addBuyTrade(event, 1, Items.WHEAT.getDefaultInstance(), 7, 2,2);
            addSellTrade(event, 1, Items.WHEAT_SEEDS.getDefaultInstance(), 5, 1, 2);

            addBuyTrade(event, 1, Items.WHEAT_SEEDS.getDefaultInstance(), 3, 3,2);
            addSellTrade(event, 1, Items.WHEAT.getDefaultInstance(), 4, 1, 2);

            addBuyTrade(event, 1, Items.WOODEN_HOE.getDefaultInstance(), 6, 1, 2);
            addSellTrade(event, 1, Items.WOODEN_HOE.getDefaultInstance(), 1, 1, 2);

            addBuyTrade(event, 1, ModItems.CUCUMBER_SEEDS.get().getDefaultInstance(), 3, 3,2);
            addSellTrade(event, 1, ModItems.CUCUMBER_SEEDS.get().getDefaultInstance(), 5, 1, 2);

            addBuyTrade(event, 1, ModItems.TOMATO_SEEDS.get().getDefaultInstance(), 3, 3,2);
            addSellTrade(event, 1, ModItems.TOMATO_SEEDS.get().getDefaultInstance(), 5, 1, 2);

            addBuyTrade(event, 1, ModItems.RADISH_SEEDS.get().getDefaultInstance(), 3, 3,2);
            addSellTrade(event, 1, ModItems.RADISH_SEEDS.get().getDefaultInstance(), 5, 1, 2);

            addBuyTrade(event, 1, ModItems.LETTUCE_SEEDS.get().getDefaultInstance(), 3, 3,2);
            addSellTrade(event, 1, ModItems.LETTUCE_SEEDS.get().getDefaultInstance(), 5, 1, 2);

            addBuyTrade(event, 1, ModItems.ONION_SEEDS.get().getDefaultInstance(), 3, 3,2);
            addSellTrade(event, 1, ModItems.ONION_SEEDS.get().getDefaultInstance(), 5, 1, 2);

            addBuyTrade(event, 1, ModItems.SPRING_ONION_SEEDS.get().getDefaultInstance(), 3, 3,2);
            addSellTrade(event, 1, ModItems.SPRING_ONION_SEEDS.get().getDefaultInstance(), 5, 1, 2);

            addBuyTrade(event, 1, ModItems.PINEAPPLE_SEEDS.get().getDefaultInstance(), 3, 3,2);
            addSellTrade(event, 1, ModItems.PINEAPPLE_SEEDS.get().getDefaultInstance(), 5, 1, 2);

            addBuyTrade(event, 1, ModItems.BLACKCURRANT_SEEDS.get().getDefaultInstance(), 3, 3,2);
            addSellTrade(event, 1, ModItems.BLACKCURRANT_SEEDS.get().getDefaultInstance(), 5, 1, 2);

            addBuyTrade(event, 1, ModItems.STRAWBERRY_SEEDS.get().getDefaultInstance(), 3, 3,2);
            addSellTrade(event, 1, ModItems.STRAWBERRY_SEEDS.get().getDefaultInstance(), 5, 1, 2);

            addBuyTrade(event, 1, ModItems.BLUEBERRY_SEEDS.get().getDefaultInstance(), 3, 3,2);
            addSellTrade(event, 1, ModItems.BLUEBERRY_SEEDS.get().getDefaultInstance(), 5, 1, 2);

            addBuyTrade(event, 1, ModItems.GOOSEBERRY_SEEDS.get().getDefaultInstance(), 3, 3,2);
            addSellTrade(event, 1, ModItems.GOOSEBERRY_SEEDS.get().getDefaultInstance(), 5, 1, 2);

            addBuyTrade(event, 1, ModItems.CORN_SEEDS.get().getDefaultInstance(), 3, 3,2);
            addSellTrade(event, 1, ModItems.CORN_SEEDS.get().getDefaultInstance(), 5, 1, 2);

            addBuyTrade(event, 1, ModItems.COFFEE_SEEDS.get().getDefaultInstance(), 4, 3,2);
            addSellTrade(event, 1, ModItems.COFFEE_SEEDS.get().getDefaultInstance(), 6, 1, 2);

            addBuyTrade(event, 1, ModItems.TEA_SEEDS.get().getDefaultInstance(), 4, 3,2);
            addSellTrade(event, 1, ModItems.TEA_SEEDS.get().getDefaultInstance(), 6, 1, 2);

            // === LEVEL 2 TRADES (mid game, XP = 5) ===
            addBuyTrade(event, 2, ModItems.TOMATO.get().getDefaultInstance(), 4, 2, 4);
            addSellTrade(event, 2, ModItems.TOMATO.get().getDefaultInstance(), 7, 1, 4);

            addBuyTrade(event, 2, Items.STONE_HOE.getDefaultInstance(), 8, 1, 4);
            addSellTrade(event, 2, Items.STONE_HOE.getDefaultInstance(), 1, 1, 4);

            addBuyTrade(event, 2, ModItems.LETTUCE.get().getDefaultInstance(), 4, 2, 4);
            addSellTrade(event, 2, ModItems.LETTUCE.get().getDefaultInstance(), 7, 1, 4);

            addBuyTrade(event, 2, ModItems.ONION.get().getDefaultInstance(), 4, 2, 4);
            addSellTrade(event, 2, ModItems.ONION.get().getDefaultInstance(), 7, 1, 4);

            addBuyTrade(event, 2, ModItems.SPRING_ONION.get().getDefaultInstance(), 4, 2, 4);
            addSellTrade(event, 2, ModItems.SPRING_ONION.get().getDefaultInstance(), 7, 1, 4);

            addBuyTrade(event, 2, Items.BEETROOT_SEEDS.getDefaultInstance(), 3, 3,2);
            addSellTrade(event, 2, Items.BEETROOT_SEEDS.getDefaultInstance(), 5, 1, 4);

            addBuyTrade(event, 2, Items.CARROT.getDefaultInstance(), 3, 3,4);
            addSellTrade(event, 2, Items.CARROT.getDefaultInstance(), 5, 1, 4);

            addBuyTrade(event, 2, Items.POTATO.getDefaultInstance(), 3, 3,4);
            addSellTrade(event, 2, Items.POTATO.getDefaultInstance(), 5, 1, 4);

            addBuyTrade(event, 2, Items.APPLE.getDefaultInstance(), 4, 3,4);
            addSellTrade(event, 2, Items.APPLE.getDefaultInstance(), 8, 1, 4);

            addBuyTrade(event, 2, ModItems.ORANGE.get().getDefaultInstance(), 4, 3,4);
            addSellTrade(event, 2, ModItems.ORANGE.get().getDefaultInstance(), 8, 1, 4);

            addBuyTrade(event, 2, ModItems.PEAR.get().getDefaultInstance(), 4, 3,4);
            addSellTrade(event, 2, ModItems.PEAR.get().getDefaultInstance(), 8, 1, 4);

            addBuyTrade(event, 2, ModItems.AVOCADO.get().getDefaultInstance(), 4, 3,4);
            addSellTrade(event, 2, ModItems.AVOCADO.get().getDefaultInstance(), 8, 1, 4);

            addBuyTrade(event, 2, ModItems.CHERRY.get().getDefaultInstance(), 4, 3,4);
            addSellTrade(event, 2, ModItems.CHERRY.get().getDefaultInstance(), 8, 1, 4);

            addBuyTrade(event, 2, ModItems.BLACKCURRANT.get().getDefaultInstance(), 4, 3,4);
            addSellTrade(event, 2, ModItems.BLACKCURRANT.get().getDefaultInstance(), 8, 1, 4);

            addBuyTrade(event, 2, ModItems.STRAWBERRY.get().getDefaultInstance(), 4, 3,4);
            addSellTrade(event, 2, ModItems.STRAWBERRY.get().getDefaultInstance(), 8, 1, 4);

            addBuyTrade(event, 2, ModItems.BLUEBERRY.get().getDefaultInstance(), 4, 3,4);
            addSellTrade(event, 2, ModItems.BLUEBERRY.get().getDefaultInstance(), 8, 1, 4);

            addBuyTrade(event, 2, ModItems.GOOSEBERRY.get().getDefaultInstance(), 4, 3,4);
            addSellTrade(event, 2, ModItems.GOOSEBERRY.get().getDefaultInstance(), 8, 1, 4);

            addBuyTrade(event, 2, Items.SUGAR_CANE.getDefaultInstance(), 3, 2,4);
            addSellTrade(event, 2, Items.SUGAR_CANE.getDefaultInstance(), 6, 1, 4);

            addBuyTrade(event, 2, Items.MELON_SEEDS.getDefaultInstance(), 3, 3,4);
            addSellTrade(event, 2, Items.MELON_SEEDS.getDefaultInstance(), 5, 1, 4);

            addBuyTrade(event, 2, Items.PUMPKIN_SEEDS.getDefaultInstance(), 3, 3,4);
            addSellTrade(event, 2, Items.PUMPKIN_SEEDS.getDefaultInstance(), 5, 1, 4);

            addBuyTrade(event, 2, ModItems.CORN.get().getDefaultInstance(), 4, 2, 5);
            addSellTrade(event, 2, ModItems.CORN.get().getDefaultInstance(), 7, 1, 4);

            addBuyTrade(event, 2, ModItems.PINEAPPLE.get().getDefaultInstance(), 4, 2, 4);
            addSellTrade(event, 2, ModItems.PINEAPPLE.get().getDefaultInstance(), 7, 1, 4);

            addBuyTrade(event, 2, Items.BEETROOT.getDefaultInstance(), 4, 2, 4);
            addSellTrade(event, 2, Items.BEETROOT.getDefaultInstance(), 7, 1, 4);

            addBuyTrade(event, 2, ModItems.FLAX_FLOWER.get().getDefaultInstance(), 4, 2, 4);
            addSellTrade(event, 2, ModItems.FLAX_FLOWER.get().getDefaultInstance(), 7, 1, 4);

            // === LEVEL 3 TRADES ===
            addBuyTrade(event, 3, ModItems.RADISH.get().getDefaultInstance(), 10, 5, 6);
            addSellTrade(event, 3, ModItems.RADISH.get().getDefaultInstance(), 7, 1, 6);

            // === LEVEL 4 TRADES ===
            addBuyTrade(event, 4, Items.IRON_HOE.getDefaultInstance(), 12, 1, 8);
            addSellTrade(event, 4, Items.IRON_HOE.getDefaultInstance(), 1, 2, 8);

            addBuyTrade(event, 4, ModItems.STEEL_HOE.get().getDefaultInstance(), 16, 1, 8);
            addSellTrade(event, 4, ModItems.STEEL_HOE.get().getDefaultInstance(), 1, 3, 8);

            addBuyTrade(event, 4, Items.GOLDEN_HOE.getDefaultInstance(), 18, 1, 8);
            addSellTrade(event, 4, Items.GOLDEN_HOE.getDefaultInstance(), 1, 3, 8);

            // === LEVEL 5 TRADES ===

            addBuyTrade(event, 5, ModItems.COFFEE_BEANS.get().getDefaultInstance(), 9, 1, 10);
            addSellTrade(event, 5, ModItems.COFFEE_BEANS.get().getDefaultInstance(), 1, 3, 10);

            addBuyTrade(event, 5, ModItems.TEA_LEAVES.get().getDefaultInstance(), 9, 1, 10);
            addSellTrade(event, 5, ModItems.TEA_LEAVES.get().getDefaultInstance(), 1, 3, 10);

            addBuyTrade(event, 5, Items.DIAMOND_HOE.getDefaultInstance(), 22, 1, 10);
            addSellTrade(event, 5, Items.DIAMOND_HOE.getDefaultInstance(), 1, 4, 10);

            addBuyTrade(event, 5, Items.NETHERITE_HOE.getDefaultInstance(), 28, 1, 10);
            addSellTrade(event, 5, Items.NETHERITE_HOE.getDefaultInstance(), 1, 5, 10);
        }

        if (event.getType() == ModVillagers.MINER.value()) {
            // === MINER TRADES ===

            // LEVEL 1 - MINER

            addBuyTrade(event, 1, Items.COBBLESTONE.getDefaultInstance(), 4, 2,2);
            addSellTrade(event, 1, Items.COBBLESTONE.getDefaultInstance(), 9, 1, 2);

            addBuyTrade(event, 1, Items.COAL.getDefaultInstance(), 3, 3,2);
            addSellTrade(event, 1, Items.COAL.getDefaultInstance(), 8, 1, 2);

            addBuyTrade(event, 1, Items.COAL_ORE.getDefaultInstance(), 4, 3,2);
            addSellTrade(event, 1, Items.COAL_ORE.getDefaultInstance(), 6, 1, 2);

            addBuyTrade(event, 1, Items.RAW_COPPER.getDefaultInstance(), 4, 3,2);
            addSellTrade(event, 1, Items.RAW_COPPER.getDefaultInstance(), 8, 1, 2);

            addBuyTrade(event, 1, Items.COPPER_ORE.getDefaultInstance(), 4, 3,2);
            addSellTrade(event, 1, Items.COPPER_ORE.getDefaultInstance(), 6, 1, 2);

            addBuyTrade(event, 1, Items.WOODEN_PICKAXE.getDefaultInstance(), 4, 1, 2);
            addSellTrade(event, 1, Items.WOODEN_PICKAXE.getDefaultInstance(), 1, 1, 2);

            // LEVEL 2 - MINER

            addBuyTrade(event, 2, Items.COPPER_INGOT.getDefaultInstance(), 3, 4,4);
            addSellTrade(event, 2, Items.COPPER_INGOT.getDefaultInstance(), 5, 1, 4);

            addBuyTrade(event, 2, Items.IRON_ORE.getDefaultInstance(), 5, 2,4);
            addSellTrade(event, 2, Items.IRON_ORE.getDefaultInstance(), 5, 1, 4);

            addBuyTrade(event, 2, Items.RAW_IRON.getDefaultInstance(), 5, 2,4);
            addSellTrade(event, 2, Items.RAW_IRON.getDefaultInstance(), 5, 1, 4);


            addBuyTrade(event, 2, Items.GOLD_ORE.getDefaultInstance(), 6, 2,4);
            addSellTrade(event, 2, Items.GOLD_ORE.getDefaultInstance(), 7, 1, 4);

            addBuyTrade(event, 2, Items.RAW_GOLD.getDefaultInstance(), 6, 2,4);
            addSellTrade(event, 2, Items.RAW_GOLD.getDefaultInstance(), 7, 1, 4);

            addBuyTrade(event, 2, Items.STONE_PICKAXE.getDefaultInstance(), 8, 1, 4);
            addSellTrade(event, 2, Items.STONE_PICKAXE.getDefaultInstance(), 1, 1, 4);

            // LEVEL 3 - MINER

            addBuyTrade(event, 3, Items.IRON_INGOT.getDefaultInstance(), 7, 2, 6);
            addSellTrade(event, 3, Items.IRON_INGOT.getDefaultInstance(), 8, 2, 6);

            addBuyTrade(event, 3, Items.GOLD_INGOT.getDefaultInstance(), 7, 2, 6);
            addSellTrade(event, 3, Items.GOLD_INGOT.getDefaultInstance(), 9, 1, 6);

            addBuyTrade(event, 3, Items.DIAMOND.getDefaultInstance(), 11, 2, 6);
            addSellTrade(event, 3, Items.DIAMOND.getDefaultInstance(), 7, 3, 6);

            addBuyTrade(event, 3, Items.DIAMOND_ORE.getDefaultInstance(), 10, 2, 6);
            addSellTrade(event, 3, Items.DIAMOND_ORE.getDefaultInstance(), 6, 3, 6);

            // LEVEL 4 - Better tools
            addBuyTrade(event, 4, Items.IRON_PICKAXE.getDefaultInstance(), 12, 1, 8);
            addSellTrade(event, 4, Items.IRON_PICKAXE.getDefaultInstance(), 1, 2, 8);

            addBuyTrade(event, 4, ModItems.STEEL_PICKAXE.get().getDefaultInstance(), 16, 1, 8);
            addSellTrade(event, 4, ModItems.STEEL_PICKAXE.get().getDefaultInstance(), 1, 3, 8);

            addBuyTrade(event, 4, Items.NETHERITE_INGOT.getDefaultInstance(), 22, 1, 8);
            addSellTrade(event, 4, Items.NETHERITE_INGOT.getDefaultInstance(), 3, 9, 8);

            addBuyTrade(event, 4, Items.GOLDEN_PICKAXE.getDefaultInstance(), 11, 1, 8);
            addSellTrade(event, 4, Items.GOLDEN_PICKAXE.getDefaultInstance(), 1, 4, 8);

            // LEVEL 5 - MINER

            addBuyTrade(event, 5, Items.DIAMOND_PICKAXE.getDefaultInstance(), 22, 1, 10);
            addSellTrade(event, 5, Items.DIAMOND_PICKAXE.getDefaultInstance(), 1, 4, 10);

            addBuyTrade(event, 5, Items.NETHERITE_PICKAXE.getDefaultInstance(), 28, 1, 10);
            addSellTrade(event, 5, Items.NETHERITE_PICKAXE.getDefaultInstance(), 1, 5, 10);

            addBuyTrade(event, 5, Items.DIAMOND.getDefaultInstance(), 18, 6, 10);
            addSellTrade(event, 5, Items.DIAMOND.getDefaultInstance(), 10, 6, 10);
        }

        if (event.getType() == ModVillagers.WOODCUTTER.value()) {
            // === WOODCUTTER TRADES ===

            // LEVEL 1 - WOODCUTTER

            addBuyTrade(event, 1, Items.OAK_LOG.getDefaultInstance(), 2, 5,2);
            addSellTrade(event, 1, Items.OAK_LOG.getDefaultInstance(), 6, 1, 2);

            addBuyTrade(event, 1, Items.SPRUCE_LOG.getDefaultInstance(), 2, 5,2);
            addSellTrade(event, 1, Items.SPRUCE_LOG.getDefaultInstance(), 6, 1, 2);

            addBuyTrade(event, 1, Items.BIRCH_LOG.getDefaultInstance(), 2, 4,2);
            addSellTrade(event, 1, Items.BIRCH_LOG.getDefaultInstance(), 6, 1, 2);

            addBuyTrade(event, 1, Items.JUNGLE_LOG.getDefaultInstance(), 2, 4,2);
            addSellTrade(event, 1, Items.JUNGLE_LOG.getDefaultInstance(), 6, 1, 2);

            addBuyTrade(event, 1, Items.ACACIA_LOG.getDefaultInstance(), 2, 4,2);
            addSellTrade(event, 1, Items.ACACIA_LOG.getDefaultInstance(), 6, 1, 2);

            addBuyTrade(event, 1, Items.DARK_OAK_LOG.getDefaultInstance(), 2, 4,2);
            addSellTrade(event, 1, Items.DARK_OAK_LOG.getDefaultInstance(), 6, 1, 2);

            addBuyTrade(event, 1, Items.CHERRY_LOG.getDefaultInstance(), 2, 4,2);
            addSellTrade(event, 1, Items.CHERRY_LOG.getDefaultInstance(), 6, 1, 2);

            addBuyTrade(event, 1, Items.MANGROVE_LOG.getDefaultInstance(), 2, 4,2);
            addSellTrade(event, 1, Items.MANGROVE_LOG.getDefaultInstance(), 6, 1, 2);

            addBuyTrade(event, 1, Items.WOODEN_AXE.getDefaultInstance(), 4, 1, 2);
            addSellTrade(event, 1, Items.WOODEN_AXE.getDefaultInstance(), 1, 1, 2);

            // LEVEL 2 - WOODCUTTER

            addBuyTrade(event, 2, Items.OAK_SAPLING.getDefaultInstance(), 2, 5,4);
            addSellTrade(event, 2, Items.OAK_SAPLING.getDefaultInstance(), 6, 1, 4);

            addBuyTrade(event, 2, Items.SPRUCE_SAPLING.getDefaultInstance(), 2, 5,4);
            addSellTrade(event, 2, Items.SPRUCE_SAPLING.getDefaultInstance(), 6, 1, 4);

            addBuyTrade(event, 2, Items.BIRCH_SAPLING.getDefaultInstance(), 2, 4,4);
            addSellTrade(event, 2, Items.BIRCH_SAPLING.getDefaultInstance(), 6, 1, 4);

            addBuyTrade(event, 2, Items.JUNGLE_SAPLING.getDefaultInstance(), 2, 4,4);
            addSellTrade(event, 2, Items.JUNGLE_SAPLING.getDefaultInstance(), 6, 1, 4);

            addBuyTrade(event, 2, Items.ACACIA_SAPLING.getDefaultInstance(), 2, 4,4);
            addSellTrade(event, 2, Items.ACACIA_SAPLING.getDefaultInstance(), 6, 1, 4);

            addBuyTrade(event, 2, Items.DARK_OAK_SAPLING.getDefaultInstance(), 2, 4,4);
            addSellTrade(event, 2, Items.DARK_OAK_SAPLING.getDefaultInstance(), 6, 1, 4);

            addBuyTrade(event, 2, Items.CHERRY_SAPLING.getDefaultInstance(), 2, 4,4);
            addSellTrade(event, 2, Items.CHERRY_SAPLING.getDefaultInstance(), 6, 1, 4);

            addBuyTrade(event, 2, Items.MANGROVE_PROPAGULE.getDefaultInstance(), 2, 4,4);
            addSellTrade(event, 2, Items.MANGROVE_PROPAGULE.getDefaultInstance(), 6, 1, 4);

            addBuyTrade(event, 2, ModBlocks.APPLE_SAPLING.get().asItem().getDefaultInstance(), 2, 4, 4);
            addSellTrade(event, 2, ModBlocks.APPLE_SAPLING.get().asItem().getDefaultInstance(), 6, 1, 4);

            addBuyTrade(event, 2, ModBlocks.PEAR_SAPLING.get().asItem().getDefaultInstance(), 2, 4, 4);
            addSellTrade(event, 2, ModBlocks.PEAR_SAPLING.get().asItem().getDefaultInstance(), 6, 1, 4);

            addBuyTrade(event, 2, ModBlocks.CHERRY_SAPLING.get().asItem().getDefaultInstance(), 2, 4, 4);
            addSellTrade(event, 2, ModBlocks.CHERRY_SAPLING.get().asItem().getDefaultInstance(), 6, 1, 4);

            addBuyTrade(event, 2, ModBlocks.AVOCADO_SAPLING.get().asItem().getDefaultInstance(), 2, 4, 4);
            addSellTrade(event, 2, ModBlocks.AVOCADO_SAPLING.get().asItem().getDefaultInstance(), 6, 1, 4);

            addBuyTrade(event, 2, Items.STONE_AXE.getDefaultInstance(), 4, 1, 4);
            addSellTrade(event, 2, Items.STONE_AXE.getDefaultInstance(), 1, 1, 4);

            // LEVEL 3 - WOODCUTTER

            addBuyTrade(event, 3, Items.STRIPPED_OAK_LOG.getDefaultInstance(), 2, 6, 6);
            addSellTrade(event, 3, Items.STRIPPED_OAK_LOG.getDefaultInstance(), 6, 1, 6);

            addBuyTrade(event, 3, Items.STRIPPED_SPRUCE_LOG.getDefaultInstance(), 2, 6, 6);
            addSellTrade(event, 3, Items.STRIPPED_SPRUCE_LOG.getDefaultInstance(), 6, 1, 6);

            addBuyTrade(event, 3, Items.STRIPPED_BIRCH_LOG.getDefaultInstance(), 2, 6, 6);
            addSellTrade(event, 3, Items.STRIPPED_BIRCH_LOG.getDefaultInstance(), 6, 1, 6);

            addBuyTrade(event, 3, Items.STRIPPED_JUNGLE_LOG.getDefaultInstance(), 2, 6, 6);
            addSellTrade(event, 3, Items.STRIPPED_JUNGLE_LOG.getDefaultInstance(), 6, 1, 6);

            addBuyTrade(event, 3, Items.STRIPPED_ACACIA_LOG.getDefaultInstance(), 2, 6, 6);
            addSellTrade(event, 3, Items.STRIPPED_ACACIA_LOG.getDefaultInstance(), 6, 1, 6);

            addBuyTrade(event, 3, Items.STRIPPED_DARK_OAK_LOG.getDefaultInstance(), 2, 6, 6);
            addSellTrade(event, 3, Items.STRIPPED_DARK_OAK_LOG.getDefaultInstance(), 6, 1, 6);

            addBuyTrade(event, 3, Items.STRIPPED_CHERRY_LOG.getDefaultInstance(), 2, 6, 6);
            addSellTrade(event, 3, Items.STRIPPED_CHERRY_LOG.getDefaultInstance(), 6, 1, 6);

            addBuyTrade(event, 3, Items.STRIPPED_MANGROVE_LOG.getDefaultInstance(), 2, 6, 6);
            addSellTrade(event, 3, Items.STRIPPED_MANGROVE_LOG.getDefaultInstance(), 6, 1, 6);

            // LEVEL 4 - WOODCUTTER

            addBuyTrade(event, 4, Items.OAK_PLANKS.getDefaultInstance(), 2, 6, 8);
            addSellTrade(event, 4, Items.OAK_PLANKS.getDefaultInstance(), 6, 1, 8);

            addBuyTrade(event, 4, Items.SPRUCE_PLANKS.getDefaultInstance(), 2, 6, 8);
            addSellTrade(event, 4, Items.SPRUCE_PLANKS.getDefaultInstance(), 6, 1, 8);

            addBuyTrade(event, 4, Items.BIRCH_PLANKS.getDefaultInstance(), 2, 6, 8);
            addSellTrade(event, 4, Items.BIRCH_PLANKS.getDefaultInstance(), 6, 1, 8);

            addBuyTrade(event, 4, Items.JUNGLE_PLANKS.getDefaultInstance(), 2, 6, 8);
            addSellTrade(event, 4, Items.JUNGLE_PLANKS.getDefaultInstance(), 6, 1, 8);

            addBuyTrade(event, 4, Items.ACACIA_PLANKS.getDefaultInstance(), 2, 6, 8);
            addSellTrade(event, 4, Items.ACACIA_PLANKS.getDefaultInstance(), 6, 1, 8);

            addBuyTrade(event, 4, Items.DARK_OAK_PLANKS.getDefaultInstance(), 2, 6, 8);
            addSellTrade(event, 4, Items.DARK_OAK_PLANKS.getDefaultInstance(), 6, 1, 8);

            addBuyTrade(event, 4, Items.CHERRY_PLANKS.getDefaultInstance(), 2, 6, 8);
            addSellTrade(event, 4, Items.CHERRY_PLANKS.getDefaultInstance(), 6, 1, 8);

            addBuyTrade(event, 4, Items.MANGROVE_PLANKS.getDefaultInstance(), 2, 6, 8);
            addSellTrade(event, 4, Items.MANGROVE_PLANKS.getDefaultInstance(), 6, 1, 8);

            addBuyTrade(event, 4, Items.IRON_AXE.getDefaultInstance(), 12, 1, 8);
            addSellTrade(event, 4, Items.IRON_AXE.getDefaultInstance(), 1, 2, 8);

            addBuyTrade(event, 4, ModItems.STEEL_AXE.get().getDefaultInstance(), 16, 1, 8);
            addSellTrade(event, 4, ModItems.STEEL_AXE.get().getDefaultInstance(), 1, 3, 8);

            addBuyTrade(event, 4, Items.GOLDEN_AXE.getDefaultInstance(), 11, 1, 8);
            addSellTrade(event, 4, Items.GOLDEN_AXE.getDefaultInstance(), 1, 4, 8);


            // LEVEL 5 - Advanced or custom axe

            addBuyTrade(event, 4, Items.DIAMOND_AXE.getDefaultInstance(), 25, 1, 10);
            addSellTrade(event, 4, Items.DIAMOND_AXE.getDefaultInstance(), 1, 10, 10);

            addBuyTrade(event, 5, Items.NETHERITE_AXE.getDefaultInstance(), 40, 1, 10);
            addSellTrade(event, 5, Items.NETHERITE_AXE.getDefaultInstance(), 1, 15, 10);
        }

        if (event.getType() == VillagerProfession.FISHERMAN) {
            // === LEVEL 1 - FISHERMAN ===

            // Villager sells a Fishing Rod to the player
            addBuyTrade(event, 1, Items.FISHING_ROD.getDefaultInstance(), 5, 1, 2);
            addSellTrade(event, 1, Items.FISHING_ROD.getDefaultInstance(), 1, 3, 2);

            // Villager sells worms (bait) to the player
            addBuyTrade(event, 1, ModItems.WORM.get().getDefaultInstance(), 3, 5, 2);
            addSellTrade(event, 1, ModItems.WORM.get().getDefaultInstance(), 5, 1, 2);
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