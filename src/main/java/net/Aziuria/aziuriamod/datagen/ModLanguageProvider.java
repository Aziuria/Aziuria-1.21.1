package net.Aziuria.aziuriamod.datagen;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.Aziuria.aziuriamod.block.ModBlocks;
import net.Aziuria.aziuriamod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {

    public ModLanguageProvider(PackOutput output) {
        super(output, AziuriaMod.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {

        // ===================== ITEMS =====================
        add(ModItems.STEEL_INGOT.get(), "Steel Ingot");
        add(ModItems.EGG_SHELL.get(), "Egg Shells");
        add(ModItems.SPECTRAL_INGOT.get(), "Spectral Ingot");
        add(ModItems.TIN_INGOT.get(), "Tin Ingot");
        add(ModItems.TIN_NUGGET.get(), "Tin Nugget");
        add(ModItems.STEEL_ALLOY_MESH.get(), "Steel Alloy Mesh");
        add(ModItems.SULPHUR.get(), "Sulphur");
        add(ModItems.POTASSIUM.get(), "Potassium");
        add(ModItems.TIN.get(), "Raw Tin");
        add(ModItems.SPECTRAL_DUST.get(), "Spectral Dust");
        add(ModItems.SPECTRAL_SUBSTANCE.get(), "Spectral Substance");
        add(ModItems.COPPER_NUGGET.get(), "Copper Nugget");
        add(ModItems.STEEL_NUGGET.get(), "Steel Nugget");
        add(ModItems.SPINEL.get(), "Spinel Gem");
        add(ModItems.SACK_ITEM.get(), "Sack");

        // ---- TOOLS & WEAPONS ----
        add(ModItems.STEEL_SWORD.get(), "Steel Sword");
        add(ModItems.STEEL_AXE.get(), "Steel Axe");
        add(ModItems.STEEL_PICKAXE.get(), "Steel Pickaxe");
        add(ModItems.STEEL_SHOVEL.get(), "Steel Shovel");
        add(ModItems.STEEL_HOE.get(), "Steel Hoe");

        add(ModItems.SPINEL_SWORD.get(), "Spinel Sword");
        add(ModItems.SPINEL_AXE.get(), "Spinel Axe");
        add(ModItems.SPINEL_PICKAXE.get(), "Spinel Pickaxe");
        add(ModItems.SPINEL_SHOVEL.get(), "Spinel Shovel");
        add(ModItems.SPINEL_HOE.get(), "Spinel Hoe");

        add(ModItems.COPPER_SWORD.get(), "Copper Sword");
        add(ModItems.COPPER_AXE.get(), "Copper Axe");
        add(ModItems.COPPER_PICKAXE.get(), "Copper Pickaxe");
        add(ModItems.COPPER_SHOVEL.get(), "Copper Shovel");
        add(ModItems.COPPER_HOE.get(), "Copper Hoe");

        add(ModItems.WOOD_KNIFE.get(), "Wooden Knife");
        add(ModItems.STONE_KNIFE.get(), "Stone Knife");
        add(ModItems.COPPER_KNIFE.get(), "Copper Knife");
        add(ModItems.IRON_KNIFE.get(), "Iron Knife");
        add(ModItems.GOLD_KNIFE.get(), "Golden Knife");
        add(ModItems.STEEL_KNIFE.get(), "Steel Knife");
        add(ModItems.SPINEL_KNIFE.get(), "Spinel Knife");
        add(ModItems.DIAMOND_KNIFE.get(), "Diamond Knife");
        add(ModItems.NETHERITE_KNIFE.get(), "Netherite Knife");

        add(ModItems.MALLET.get(), "Mallet");
        add(ModItems.MALLET_HEAD.get(), "Mallet Head");

        // ---- ARMOR ----
        add(ModItems.STEEL_HELMET.get(), "Steel Helmet");
        add(ModItems.STEEL_CHESTPLATE.get(), "Steel Chestplate");
        add(ModItems.STEEL_LEGGINGS.get(), "Steel Leggings");
        add(ModItems.STEEL_BOOTS.get(), "Steel Boots");

        add(ModItems.SPINEL_HELMET.get(), "Spinel Helmet");
        add(ModItems.SPINEL_CHESTPLATE.get(), "Spinel Chestplate");
        add(ModItems.SPINEL_LEGGINGS.get(), "Spinel Leggings");
        add(ModItems.SPINEL_BOOTS.get(), "Spinel Boots");

        add(ModItems.COPPER_HELMET.get(), "Copper Helmet");
        add(ModItems.COPPER_CHESTPLATE.get(), "Copper Chestplate");
        add(ModItems.COPPER_LEGGINGS.get(), "Copper Leggings");
        add(ModItems.COPPER_BOOTS.get(), "Copper Boots");

        // ---- HORSE ARMOR ----
        add(ModItems.STEEL_HORSE_ARMOR.get(), "Steel Horse Armor");
        add(ModItems.COPPER_HORSE_ARMOR.get(), "Copper Horse Armor");
        add(ModItems.SPINEL_HORSE_ARMOR.get(), "Spinel Horse Armor");

        add(ModItems.LASHING.get(), "Lashing");

        // ===================== ITEMS =====================
        add(ModItems.STONE_SHARD.get(), "Stone Shard");
        add(ModItems.DRIED_GRASS.get(), "Dried Grass");
        add(ModItems.FLOUR.get(), "Flour");
        add(ModItems.EMPTY_CUP.get(), "Empty Cup");
        add(ModItems.EMPTY_JAR.get(), "Empty Jar");
        add(ModItems.NAIL.get(), "Nail");

        // ===================== BLOCKS =====================
        add(ModBlocks.SULPHUR_ORE.get(), "Sulphur Ore");
        add(ModBlocks.POTASSIUM_ORE.get(), "Potassium Ore");
        add(ModBlocks.SPINEL_ORE.get(), "Spinel Ore");
        add(ModBlocks.SPECTRAL_ORE.get(), "Spectral Ore");
        add(ModBlocks.TIN_ORE.get(), "Tin Ore");

        add(ModBlocks.DEEPSLATE_SULPHUR_ORE.get(), "Deepslate Sulphur Ore");
        add(ModBlocks.DEEPSLATE_TIN_ORE.get(), "Deepslate Tin Ore");
        add(ModBlocks.DEEPSLATE_POTASSIUM_ORE.get(), "Deepslate Potassium Ore");
        add(ModBlocks.DEEPSLATE_SPINEL_ORE.get(), "Deepslate Spinel Ore");
        add(ModBlocks.DEEPSLATE_SPECTRAL_ORE.get(), "Deepslate Spectral Ore");

        add(ModBlocks.STEEL_BLOCK.get(), "Steel Block");
        add(ModBlocks.UNBREAKABLE_GLASS.get(), "Unbreakable Glass");

        // ===================== SHELVES =====================
        add(ModBlocks.OAK_SHELF.get(), "Oak Shelf");
        add(ModBlocks.BAMBOO_SHELF.get(), "Bamboo Shelf");
        add(ModBlocks.BIRCH_SHELF.get(), "Birch Shelf");
        add(ModBlocks.DARK_OAK_SHELF.get(), "Dark Oak Shelf");
        add(ModBlocks.JUNGLE_SHELF.get(), "Jungle Shelf");
        add(ModBlocks.SPRUCE_SHELF.get(), "Spruce Shelf");
        add(ModBlocks.ACACIA_SHELF.get(), "Acacia Shelf");
        add(ModBlocks.CHERRY_SHELF.get(), "Cherry Shelf");
        add(ModBlocks.MANGROVE_SHELF.get(), "Mangrove Shelf");

// ===================== STORAGE =====================
        add(ModBlocks.OAK_STORAGE.get(), "Oak Storage");
        add(ModBlocks.BAMBOO_STORAGE.get(), "Bamboo Storage");
        add(ModBlocks.ACACIA_STORAGE.get(), "Acacia Storage");
        add(ModBlocks.BIRCH_STORAGE.get(), "Birch Storage");
        add(ModBlocks.CHERRY_STORAGE.get(), "Cherry Storage");
        add(ModBlocks.DARK_OAK_STORAGE.get(), "Dark Oak Storage");
        add(ModBlocks.JUNGLE_STORAGE.get(), "Jungle Storage");
        add(ModBlocks.MANGROVE_STORAGE.get(), "Mangrove Storage");
        add(ModBlocks.SPRUCE_STORAGE.get(), "Spruce Storage");

// ===================== HOOKS =====================
        add(ModBlocks.OAK_HOOK.get(), "Oak Hook");
        add(ModBlocks.ACACIA_HOOK.get(), "Acacia Hook");
        add(ModBlocks.BAMBOO_HOOK.get(), "Bamboo Hook");
        add(ModBlocks.BIRCH_HOOK.get(), "Birch Hook");
        add(ModBlocks.CHERRY_HOOK.get(), "Cherry Hook");
        add(ModBlocks.DARK_OAK_HOOK.get(), "Dark Oak Hook");
        add(ModBlocks.JUNGLE_HOOK.get(), "Jungle Hook");
        add(ModBlocks.MANGROVE_HOOK.get(), "Mangrove Hook");
        add(ModBlocks.SPRUCE_HOOK.get(), "Spruce Hook");

// ===================== BARRELS =====================
        add(ModBlocks.STEEL_BARREL_EMPTY.get(), "Steel Barrel");
        add(ModBlocks.IRON_BARREL_EMPTY.get(), "Iron Barrel");
        add(ModBlocks.COPPER_BARREL.get(), "Copper Barrel");
        add(ModBlocks.EXPOSED_COPPER_BARREL.get(), "Exposed Copper Barrel");
        add(ModBlocks.WEATHERED_COPPER_BARREL.get(), "Weathered Copper Barrel");
        add(ModBlocks.OXIDIZED_COPPER_BARREL.get(), "Oxidized Copper Barrel");
        add(ModBlocks.WAXED_COPPER_BARREL.get(), "Waxed Copper Barrel");
        add(ModBlocks.WAXED_EXPOSED_COPPER_BARREL.get(), "Waxed Exposed Copper Barrel");
        add(ModBlocks.WAXED_WEATHERED_COPPER_BARREL.get(), "Waxed Weathered Copper Barrel");
        add(ModBlocks.WAXED_OXIDIZED_COPPER_BARREL.get(), "Waxed Oxidized Copper Barrel");

// ===================== CHAINS =====================
        add(ModBlocks.STEEL_CHAIN.get(), "Steel Chain");
        add(ModBlocks.COPPER_CHAIN.get(), "Copper Chain");
        add(ModBlocks.EXPOSED_COPPER_CHAIN.get(), "Exposed Copper Chain");
        add(ModBlocks.WEATHERED_COPPER_CHAIN.get(), "Weathered Copper Chain");
        add(ModBlocks.OXIDIZED_COPPER_CHAIN.get(), "Oxidized Copper Chain");
        add(ModBlocks.WAXED_COPPER_CHAIN.get(), "Waxed Copper Chain");
        add(ModBlocks.WAXED_EXPOSED_COPPER_CHAIN.get(), "Waxed Exposed Copper Chain");
        add(ModBlocks.WAXED_WEATHERED_COPPER_CHAIN.get(), "Waxed Weathered Copper Chain");
        add(ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN.get(), "Waxed Oxidized Copper Chain");

// ===================== BARS =====================
        add(ModBlocks.STEEL_BARS.get(), "Steel Bars");
        add(ModBlocks.COPPER_BARS.get(), "Copper Bars");
        add(ModBlocks.EXPOSED_COPPER_BARS.get(), "Exposed Copper Bars");
        add(ModBlocks.WEATHERED_COPPER_BARS.get(), "Weathered Copper Bars");
        add(ModBlocks.OXIDIZED_COPPER_BARS.get(), "Oxidized Copper Bars");
        add(ModBlocks.WAXED_COPPER_BARS.get(), "Waxed Copper Bars");
        add(ModBlocks.WAXED_EXPOSED_COPPER_BARS.get(), "Waxed Exposed Copper Bars");
        add(ModBlocks.WAXED_WEATHERED_COPPER_BARS.get(), "Waxed Weathered Copper Bars");
        add(ModBlocks.WAXED_OXIDIZED_COPPER_BARS.get(), "Waxed Oxidized Copper Bars");

        // ===================== FISH TRAPS =====================
        add(ModBlocks.OAK_FISH_TRAP.get(), "Oak Fish Trap");
        add(ModBlocks.BIRCH_FISH_TRAP.get(), "Birch Fish Trap");
        add(ModBlocks.BAMBOO_FISH_TRAP.get(), "Bamboo Fish Trap");
        add(ModBlocks.SPRUCE_FISH_TRAP.get(), "Spruce Fish Trap");
        add(ModBlocks.JUNGLE_FISH_TRAP.get(), "Jungle Fish Trap");
        add(ModBlocks.ACACIA_FISH_TRAP.get(), "Acacia Fish Trap");
        add(ModBlocks.DARK_OAK_FISH_TRAP.get(), "Dark Oak Fish Trap");
        add(ModBlocks.CHERRY_FISH_TRAP.get(), "Cherry Fish Trap");
        add(ModBlocks.MANGROVE_FISH_TRAP.get(), "Mangrove Fish Trap");

// ===================== BARK =====================
        add(ModItems.OAK_BARK.get(), "Oak Bark");
        add(ModItems.BIRCH_BARK.get(), "Birch Bark");
        add(ModItems.SPRUCE_BARK.get(), "Spruce Bark");
        add(ModItems.JUNGLE_BARK.get(), "Jungle Bark");
        add(ModItems.ACACIA_BARK.get(), "Acacia Bark");
        add(ModItems.DARK_OAK_BARK.get(), "Dark Oak Bark");
        add(ModItems.CHERRY_BARK.get(), "Cherry Bark");
        add(ModItems.MANGROVE_BARK.get(), "Mangrove Bark");

        // ===================== MISC BLOCKS =====================
        add(ModBlocks.SPEAKER.get(), "Speaker");
        add(ModBlocks.STICK_A.get(), "Stick");
        add(ModBlocks.STICK_B.get(), "Stick");
        add(ModBlocks.STICK_C.get(), "Stick");
        add(ModBlocks.PEBBLE_BLOCK.get(), "Pebble");

        add(ModBlocks.DEMAECATION_POST.get(), "Demarcation Post");
        add(ModBlocks.DEMAECATION_POST_B.get(), "Demarcation Post");
        add(ModBlocks.DEMAECATION_POST_C.get(), "Demarcation Post");
        add(ModBlocks.DEMAECATION_POST_D.get(), "Demarcation Post");
        add(ModBlocks.DEMAECATION_POST_E.get(), "Demarcation Post");

        add(ModBlocks.LEAF_LITTER.get(), "Leaf Litter");
        add(ModBlocks.WOODCUTTER_BENCH.get(), "Woodcutter Bench");
        add(ModBlocks.MINER_BENCH.get(), "Miner Bench");

        // ---- CROPS ----
        add(ModItems.RADISH_SEEDS.get(), "Radish Seeds");
        add(ModBlocks.RADISH_CROP.get(), "Radish Crop");
        add(ModItems.LETTUCE_SEEDS.get(), "Lettuce Seeds");
        add(ModBlocks.LETTUCE_CROP.get(), "Lettuce Crop");
        add(ModItems.ONION_SEEDS.get(), "Onion Seeds");
        add(ModBlocks.ONION_CROP.get(), "Onion Crop");
        add(ModItems.SPRING_ONION_SEEDS.get(), "Spring Onion Seeds");
        add(ModBlocks.SPRING_ONION_CROP.get(), "Spring Onion Crop");
        add(ModItems.CUCUMBER_SEEDS.get(), "Cucumber Seeds");
        add(ModBlocks.CUCUMBER_CROP.get(), "Cucumber Crop");
        add(ModItems.TOMATO_SEEDS.get(), "Tomato Seeds");
        add(ModBlocks.TOMATO_CROP.get(), "Tomato Crop");
        add(ModItems.CORN_SEEDS.get(), "Corn Seeds");
        add(ModBlocks.CORN_CROP.get(), "Corn Crop");
        add(ModItems.PINEAPPLE_SEEDS.get(), "Pineapple Seeds");
        add(ModBlocks.PINEAPPLE_CROP.get(), "Pineapple Crop");
        add(ModItems.COFFEE_SEEDS.get(), "Coffee Seeds");
        add(ModBlocks.COFFEE_CROP.get(), "Coffee Crop");
        add(ModItems.TEA_SEEDS.get(), "Tea Seeds");
        add(ModBlocks.TEA_CROP.get(), "Tea Crop");

        // ---- PLANTS ----
        add(ModBlocks.FLAX_FLOWER_BLOCK.get(), "Flax");
        add(ModBlocks.YUCCA_PLANT_BLOCK.get(), "Yucca Plant");

        // ===================== FOOD ITEMS =====================
        add(ModItems.LETTUCE.get(), "Lettuce");
        add(ModItems.PINEAPPLE.get(), "Pineapple");
        add(ModItems.CORN.get(), "Corncob");
        add(ModItems.CORN_KERNELS.get(), "Corn");
        add(ModItems.BREAD_BAIT.get(), "Bread Crumbs");
        add(ModItems.ONION.get(), "Onion");
        add(ModItems.SPRING_ONION.get(), "Spring Onion");
        add(ModItems.RADISH.get(), "Radish");
        add(ModItems.CUCUMBER.get(), "Cucumber");
        add(ModItems.TOMATO.get(), "Tomato");
        add(ModItems.PEAR.get(), "Pear");
        add(ModItems.CHERRY.get(), "Cherry");
        add(ModItems.AVOCADO.get(), "Avocado");
        add(ModItems.ORANGE.get(), "Orange");
        add(ModItems.BANANA.get(), "Banana");
        add(ModItems.BLACKCURRANT.get(), "Blackberry");
        add(ModItems.BLUEBERRY.get(), "Blueberry");
        add(ModItems.GOOSEBERRY.get(), "Gooseberry");
        add(ModItems.STRAWBERRY.get(), "Strawberry");

        add(ModItems.COFFEE_BEANS.get(), "Coffee Beans");
        add(ModItems.YUCCA_LEAVES.get(), "Yucca Leaves");
        add(ModItems.TEA_LEAVES.get(), "Tea Leaves");

        // ===================== MEALS =====================
        add(ModItems.DICED_CHICKEN.get(), "Diced Chicken");
        add(ModItems.BATTERED_CHICKEN.get(), "Raw Chicken Nuggets");
        add(ModItems.CHICKEN_NUGGETS.get(), "Chicken Nuggets");
        add(ModItems.FRENCH_FRIES.get(), "French Fries");
        add(ModItems.BEEF_BURGER.get(), "Beef Burger");
        add(ModItems.CHEESEBURGER.get(), "Cheese Burger");
        add(ModItems.PORKCHOP_BURGER.get(), "Porkchop Burger");
        add(ModItems.CHEESE.get(), "Cheese");
        add(ModItems.PANCAKE_DOUGH.get(), "Pancake Dough");
        add(ModItems.PANCAKE.get(), "Pancake");

        // ===================== DRINKS =====================
        add(ModItems.APPLE_JUICE.get(), "Apple Juice");
        add(ModItems.PEAR_JUICE.get(), "Pear Juice");
        add(ModItems.CHERRY_JUICE.get(), "Cherry Juice");
        add(ModItems.AVOCADO_JUICE.get(), "Avocado Juice");
        add(ModItems.PINEAPPLE_JUICE.get(), "Pineapple Juice");
        add(ModItems.BLACKCURRANT_JUICE.get(), "Blackcurrant Juice");
        add(ModItems.BLUEBERRY_JUICE.get(), "Blueberry Juice");
        add(ModItems.STRAWBERRY_JUICE.get(), "Strawberry Juice");
        add(ModItems.SWEETBERRY_JUICE.get(), "Sweetberry Juice");
        add(ModItems.GLOWBERRY_JUICE.get(), "Glowberry Juice");
        add(ModItems.GOOSEBERRY_JUICE.get(), "Gooseberry Juice");
        add(ModItems.TOMATO_JUICE.get(), "Tomato Juice");
        add(ModItems.ORANGE_JUICE.get(), "Orange Juice");
        add(ModItems.MELON_JUICE.get(), "Melon Juice");
        add(ModItems.BANANA_JUICE.get(), "Banana Juice");

        add(ModItems.ENERGY_DRINK.get(), "Energy Drink");
        add(ModItems.MILK_BOTTLE.get(), "Bottle Of Milk");
        add(ModItems.COFFEE.get(), "Coffee");
        add(ModItems.TEA.get(), "Tea");

        // ===================== SMOOTHIES =====================
        add(ModItems.BANANA_SMOOTHIE.get(), "Banana Smoothie");
        add(ModItems.APPLE_SMOOTHIE.get(), "Apple Smoothie");
        add(ModItems.AVOCADO_SMOOTHIE.get(), "Avocado Smoothie");
        add(ModItems.BLACKCURRANT_SMOOTHIE.get(), "Blackcurrant Smoothie");
        add(ModItems.BLUEBERRY_SMOOTHIE.get(), "Blueberry Smoothie");
        add(ModItems.CHERRY_SMOOTHIE.get(), "Cherry Smoothie");
        add(ModItems.GLOWBERRY_SMOOTHIE.get(), "Glowberry Smoothie");
        add(ModItems.GOOSEBERRY_SMOOTHIE.get(), "Gooseberry Smoothie");
        add(ModItems.MELON_SMOOTHIE.get(), "Melon Smoothie");
        add(ModItems.ORANGE_SMOOTHIE.get(), "Orange Smoothie");
        add(ModItems.PEAR_SMOOTHIE.get(), "Pear Smoothie");
        add(ModItems.PINEAPPLE_SMOOTHIE.get(), "Pineapple Smoothie");
        add(ModItems.STRAWBERRY_SMOOTHIE.get(), "Strawberry Smoothie");
        add(ModItems.SWEETBERRY_SMOOTHIE.get(), "Sweetberry Smoothie");

        // ===================== BERRIES / BUSHES =====================
        add(ModItems.BLACKCURRANT_SEEDS.get(), "Blackberry Seeds");
        add(ModBlocks.BLACKCURRANT_BUSH.get(), "Blackberry Bush");
        add(ModItems.BLUEBERRY_SEEDS.get(), "Blueberry Seeds");
        add(ModBlocks.BLUEBERRY_BUSH.get(), "Blueberry Bush");
        add(ModItems.GOOSEBERRY_SEEDS.get(), "Gooseberry Seeds");
        add(ModBlocks.GOOSEBERRY_BUSH.get(), "Gooseberry Bush");
        add(ModItems.STRAWBERRY_SEEDS.get(), "Strawberry Seeds");
        add(ModBlocks.STRAWBERRY_BUSH.get(), "Strawberry Bush");

// ===================== FRUIT TREES =====================
        add(ModBlocks.APPLE_LEAVES.get(), "Apple Tree Leaves");
        add(ModBlocks.APPLE_SAPLING.get(), "Apple Tree Sapling");
        add(ModBlocks.BANANA_LEAVES.get(), "Banana Tree Leaves");
        add(ModBlocks.BANANA_SAPLING.get(), "Banana Tree Sapling");
        add(ModBlocks.PEAR_LEAVES.get(), "Pear Tree Leaves");
        add(ModBlocks.PEAR_SAPLING.get(), "Pear Tree Sapling");
        add(ModBlocks.CHERRY_LEAVES.get(), "Cherry Tree Leaves");
        add(ModBlocks.CHERRY_SAPLING.get(), "Cherry Tree Sapling");
        add(ModBlocks.AVOCADO_LEAVES.get(), "Avocado Tree Leaves");
        add(ModBlocks.AVOCADO_SAPLING.get(), "Avocado Tree Sapling");
        add(ModBlocks.ORANGE_LEAVES.get(), "Orange Tree Leaves");
        add(ModBlocks.ORANGE_SAPLING.get(), "Orange Tree Sapling");
        add(ModBlocks.CUSTOM_OAK_LEAVES.get(), "Oak Tree Leaves");
        add(ModBlocks.CUSTOM_OAK_SAPLING.get(), "Oak Tree Sapling");
        add(ModBlocks.CUSTOM_BIRCH_LEAVES.get(), "Birch Tree Leaves");
        add(ModBlocks.CUSTOM_BIRCH_SAPLING.get(), "Birch Tree Sapling");

        // ===================== TOOLS / MISC =====================
        add(ModItems.WORM_SPAWN_EGG.get(), "Worm Spawn Egg");
        add(ModItems.WORM_FISHING_ROD.get(), "Worm Baited Fishing Rod");
        add(ModItems.CORN_FISHING_ROD.get(), "Corn Baited Fishing Rod");
        add(ModItems.BREAD_FISHING_ROD.get(), "Bread Baited Fishing Rod");
        add(ModItems.NET.get(), "Net");
        add(ModItems.NET_SEGMENT.get(), "Fish Trap Segment");

// ===================== ISLAND GENERATORS =====================
        add(ModItems.ISLAND_GENERATOR_SMALL.get(), "Small Island Generator");
        add(ModItems.ISLAND_GENERATOR_MEDIUM.get(), "Medium Island Generator");
        add(ModItems.ISLAND_GENERATOR_LARGE.get(), "Large Island Generator");

        // ===================== GRAVESTONES =====================
        add(ModBlocks.BLACKSTONE_GRAVESTONE_A.get(), "Blackstone Gravestone A");
        add(ModBlocks.BLACKSTONE_GRAVESTONE_B.get(), "Blackstone Gravestone B");
        add(ModBlocks.COBBLESTONE_GRAVESTONE_A.get(), "Cobblestone Gravestone A");
        add(ModBlocks.COBBLESTONE_GRAVESTONE_B.get(), "Cobblestone Gravestone B");

        // ===================== ENTITIES / MISC =====================
        add("entity.minecraft.villager.aziuriamod.woodcutter", "Woodcutter");
        add("entity.minecraft.villager.aziuriamod.miner", "Miner");
        add("entity.aziuriamod.worm", "Worm");
        add("item.aziuriamod.worm", "Worm");

        add("death.attack.dehydration", "%1$s died of dehydration");
        add("creativetab.aziuriamod.useful_items_and_blocks", "Useful Items and Blocks");
        add("biome.aziuriamod.spectral_soulbound_forest", "Spectral Soulbound Forest");
    }
}