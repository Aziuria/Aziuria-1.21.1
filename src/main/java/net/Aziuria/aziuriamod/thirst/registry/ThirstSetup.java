package net.Aziuria.aziuriamod.thirst.registry;

import net.Aziuria.aziuriamod.item.ModItems;
import net.minecraft.world.item.Items;

public class ThirstSetup {

    public static void registerThirstItems() {
        // === LIQUIDS & POTIONS ===
        ThirstRegistry.register(Items.POTION, 5);
        ThirstRegistry.register(Items.MILK_BUCKET, 4);
        ThirstRegistry.register(Items.HONEY_BOTTLE, 3);

        // === FRUITS ===
        ThirstRegistry.register(Items.APPLE, 3);
        ThirstRegistry.register(Items.GOLDEN_APPLE, 4);
        ThirstRegistry.register(Items.ENCHANTED_GOLDEN_APPLE, 4);
        ThirstRegistry.register(Items.CHORUS_FRUIT, 2);
        ThirstRegistry.register(ModItems.PINEAPPLE.get(), 3);
        ThirstRegistry.register(ModItems.PEAR.get(), 4);
        ThirstRegistry.register(ModItems.CHERRY.get(), 2);
        ThirstRegistry.register(ModItems.BLACKCURRANT.get(), 2);
        ThirstRegistry.register(ModItems.STRAWBERRY.get(), 2);

        // === VEGETABLES ===
        ThirstRegistry.register(Items.CARROT, 2);
        ThirstRegistry.register(Items.GOLDEN_CARROT, 3);
        ThirstRegistry.register(Items.BEETROOT, 2);
        ThirstRegistry.register(ModItems.RADISH.get(), 2);
        ThirstRegistry.register(ModItems.CORN.get(), 1);
        ThirstRegistry.register(ModItems.CUCUMBER.get(), 3);
        ThirstRegistry.register(ModItems.TOMATO.get(), 3);
        ThirstRegistry.register(ModItems.LETTUCE.get(), 3);
        ThirstRegistry.register(ModItems.ONION.get(), 1);
        ThirstRegistry.register(ModItems.SPRING_ONION.get(), 1);

        // === BERRIES ===
        ThirstRegistry.register(Items.SWEET_BERRIES, 2);
        ThirstRegistry.register(Items.GLOW_BERRIES, 2);

        // === MELONS & GOURDS ===
        ThirstRegistry.register(Items.MELON_SLICE, 4);
        ThirstRegistry.register(Items.PUMPKIN_PIE, -1);

        // === STEWS & SOUPS ===
        ThirstRegistry.register(Items.BEETROOT_SOUP, 4);
        ThirstRegistry.register(Items.SUSPICIOUS_STEW, 3);
        ThirstRegistry.register(Items.MUSHROOM_STEW, 3);
        ThirstRegistry.register(Items.RABBIT_STEW, 4);

        // === DRY/HEAVY FOODS (BAKED/PROCESSED) ===
        ThirstRegistry.register(Items.BREAD, -3);
        ThirstRegistry.register(Items.BAKED_POTATO, -1);
        ThirstRegistry.register(Items.DRIED_KELP, -3);
        ThirstRegistry.register(Items.COOKIE, -2);
        ThirstRegistry.register(Items.CAKE, -1);
        ThirstRegistry.register(ModItems.CHEESE.get(), -1);
        ThirstRegistry.register(ModItems.FRENCH_FRIES.get(), -3);
        ThirstRegistry.register(ModItems.PANCAKE.get(), -2);

        // === MEATS ===
        ThirstRegistry.register(Items.COOKED_BEEF, -3);
        ThirstRegistry.register(Items.COOKED_PORKCHOP, -3);
        ThirstRegistry.register(Items.COOKED_CHICKEN, -3);
        ThirstRegistry.register(Items.COOKED_MUTTON, -3);
        ThirstRegistry.register(Items.COOKED_RABBIT, -3);
        ThirstRegistry.register(ModItems.CHICKEN_NUGGETS.get(), -2);
        ThirstRegistry.register(ModItems.BEEF_BURGER.get(), -4);
        ThirstRegistry.register(ModItems.CHEESEBURGER.get(), -4);
        ThirstRegistry.register(ModItems.PORKCHOP_BURGER.get(), -4);

        // === FISH & SEAFOOD ===
        ThirstRegistry.register(Items.COOKED_COD, -2);
        ThirstRegistry.register(Items.COOKED_SALMON, -2);
        ThirstRegistry.register(Items.PUFFERFISH, -2);
        ThirstRegistry.register(Items.TROPICAL_FISH, -1);

        // === ROTTEN / POISONOUS ===
        ThirstRegistry.register(Items.SPIDER_EYE, -1);
        ThirstRegistry.register(Items.POISONOUS_POTATO, -2);
        ThirstRegistry.register(Items.ROTTEN_FLESH, -2);

        // === JUICES ===
        ThirstRegistry.register(ModItems.APPLE_JUICE.get(), 5);
        ThirstRegistry.register(ModItems.PINEAPPLE_JUICE.get(), 5);
        ThirstRegistry.register(ModItems.PEAR_JUICE.get(), 5);
        ThirstRegistry.register(ModItems.CHERRY_JUICE.get(), 5);
        ThirstRegistry.register(ModItems.BLACKCURRANT_JUICE.get(), 5);
        ThirstRegistry.register(ModItems.SWEETBERRY_JUICE.get(), 5);
        ThirstRegistry.register(ModItems.GLOWBERRY_JUICE.get(), 5);
        ThirstRegistry.register(ModItems.STRAWBERRY_JUICE.get(), 5);
        ThirstRegistry.register(ModItems.TOMATO_JUICE.get(), 5);
    }
}