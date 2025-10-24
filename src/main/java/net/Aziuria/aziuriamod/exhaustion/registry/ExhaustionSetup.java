package net.Aziuria.aziuriamod.exhaustion.registry;

import net.Aziuria.aziuriamod.item.ModItems;
import net.minecraft.world.item.Items;

public class ExhaustionSetup {

    public static void registerExhaustionItems() {

        // === LIQUIDS & POTIONS (quick energy, low digestion cost) ===
        ExhaustionRegistry.register(Items.POTION, 1);        // hydration + minor boost
        ExhaustionRegistry.register(ModItems.COFFEE.get(), 5); // stimulant, fatigue reducer
        ExhaustionRegistry.register(ModItems.TEA.get(), 4);   // mild energy boost
      //  ExhaustionRegistry.register(ModItems.ENERGY_DRINK.get(), 5); // sugar + caffeine

        // === FRUITS (light energy, easy digestion) ===
        ExhaustionRegistry.register(Items.APPLE, 2);
        ExhaustionRegistry.register(Items.GOLDEN_APPLE, 2);   // high sugar, better boost
        ExhaustionRegistry.register(ModItems.PEAR.get(), 2);
        ExhaustionRegistry.register(ModItems.BANANA.get(), 3);
        ExhaustionRegistry.register(ModItems.ORANGE.get(), 2);
        ExhaustionRegistry.register(ModItems.AVOCADO.get(), 0); // heavy, small energy
        ExhaustionRegistry.register(ModItems.CHERRY.get(), 1);
        ExhaustionRegistry.register(ModItems.BLACKCURRANT.get(), 1);
        ExhaustionRegistry.register(ModItems.STRAWBERRY.get(), 1);
        ExhaustionRegistry.register(ModItems.PINEAPPLE.get(), 2);

        // === VEGETABLES (low energy, easy digestion) ===
        ExhaustionRegistry.register(Items.CARROT, 1);
        ExhaustionRegistry.register(Items.GOLDEN_CARROT, 2);  // high sugar, slight boost
        ExhaustionRegistry.register(Items.BEETROOT, 1);
        ExhaustionRegistry.register(ModItems.RADISH.get(), 1);
        ExhaustionRegistry.register(ModItems.CORN.get(), 1);
        ExhaustionRegistry.register(ModItems.CUCUMBER.get(), 1);
        ExhaustionRegistry.register(ModItems.TOMATO.get(), 1);
        ExhaustionRegistry.register(ModItems.LETTUCE.get(), 1);

        // === BERRIES (very small energy, easy to digest) ===
        ExhaustionRegistry.register(Items.SWEET_BERRIES, 1);
        ExhaustionRegistry.register(Items.GLOW_BERRIES, 1);

        // === MELONS & PIES ===
        ExhaustionRegistry.register(Items.MELON_SLICE, 1);     // hydration + minor boost
        ExhaustionRegistry.register(Items.PUMPKIN_PIE, -1);     // sugar spike but digestion cost

        // === STEWS & SOUPS (moderate meals, low fatigue reduction) ===
        ExhaustionRegistry.register(Items.BEETROOT_SOUP, 1);   // light meal
        ExhaustionRegistry.register(Items.MUSHROOM_STEW, 1);
        ExhaustionRegistry.register(Items.RABBIT_STEW, 2);     // hearty meal
        ExhaustionRegistry.register(Items.SUSPICIOUS_STEW, 1); // assume mild effect

        // === DRY/PROCESSED FOODS (heavy, may increase fatigue) ===
        ExhaustionRegistry.register(Items.BREAD, -1);         // filling but tiring
        ExhaustionRegistry.register(Items.BAKED_POTATO, 0);   // neutral
        ExhaustionRegistry.register(Items.DRIED_KELP, -2);    // low energy
        ExhaustionRegistry.register(Items.COOKIE, 0);         // sugar spike, temporary
        ExhaustionRegistry.register(Items.CAKE, -1);          // heavy dessert
        ExhaustionRegistry.register(ModItems.CHEESE.get(), -1);
        ExhaustionRegistry.register(ModItems.FRENCH_FRIES.get(), -2);
        ExhaustionRegistry.register(ModItems.PANCAKE.get(), -1);

        // === MEATS (slow energy, taxing digestion) ===
        ExhaustionRegistry.register(Items.COOKED_BEEF, 1);
        ExhaustionRegistry.register(Items.COOKED_PORKCHOP, 1);
        ExhaustionRegistry.register(Items.COOKED_CHICKEN, 1);
        ExhaustionRegistry.register(Items.COOKED_MUTTON, 1);
        ExhaustionRegistry.register(Items.COOKED_RABBIT, 1);
        ExhaustionRegistry.register(ModItems.CHICKEN_NUGGETS.get(), 0);
        ExhaustionRegistry.register(ModItems.BEEF_BURGER.get(), -1);
        ExhaustionRegistry.register(ModItems.CHEESEBURGER.get(), -1);
        ExhaustionRegistry.register(ModItems.PORKCHOP_BURGER.get(), -1);

        // === FISH & SEAFOOD (light protein, low digestion cost) ===
        ExhaustionRegistry.register(Items.COOKED_COD, 1);
        ExhaustionRegistry.register(Items.COOKED_SALMON, 1);
        ExhaustionRegistry.register(Items.PUFFERFISH, -1);   // risky, may cause fatigue
        ExhaustionRegistry.register(Items.TROPICAL_FISH, 1);

        // === JUICES (hydrating, minor boost) ===
        ExhaustionRegistry.register(ModItems.APPLE_JUICE.get(), 2);
        ExhaustionRegistry.register(ModItems.MELON_JUICE.get(), 2);
        ExhaustionRegistry.register(ModItems.PINEAPPLE_JUICE.get(), 1);
        ExhaustionRegistry.register(ModItems.PEAR_JUICE.get(), 1);
        ExhaustionRegistry.register(ModItems.ORANGE_JUICE.get(), 1);
        ExhaustionRegistry.register(ModItems.CHERRY_JUICE.get(), 1);
        ExhaustionRegistry.register(ModItems.AVOCADO_JUICE.get(), 0);
        ExhaustionRegistry.register(ModItems.BLACKCURRANT_JUICE.get(), 1);
        ExhaustionRegistry.register(ModItems.SWEETBERRY_JUICE.get(), 1);
        ExhaustionRegistry.register(ModItems.GLOWBERRY_JUICE.get(), 1);
        ExhaustionRegistry.register(ModItems.STRAWBERRY_JUICE.get(), 1);
        ExhaustionRegistry.register(ModItems.TOMATO_JUICE.get(), 1);
        ExhaustionRegistry.register(ModItems.BANANA_JUICE.get(), 1);
        ExhaustionRegistry.register(ModItems.BANANA_SMOOTHIE.get(), 2);
        ExhaustionRegistry.register(ModItems.APPLE_SMOOTHIE.get(), 2);      // steady sugar energy
        ExhaustionRegistry.register(ModItems.AVOCADO_SMOOTHIE.get(), 3);    // fat/protein-rich, slow energy
        ExhaustionRegistry.register(ModItems.BLACKCURRANT_SMOOTHIE.get(), 2); // antioxidant sugar, mild rush
        ExhaustionRegistry.register(ModItems.CHERRY_SMOOTHIE.get(), 2);
        ExhaustionRegistry.register(ModItems.GLOWBERRY_SMOOTHIE.get(), 2);
        ExhaustionRegistry.register(ModItems.MELON_SMOOTHIE.get(), 2);
        ExhaustionRegistry.register(ModItems.ORANGE_SMOOTHIE.get(), 2);
        ExhaustionRegistry.register(ModItems.PEAR_SMOOTHIE.get(), 2);
        ExhaustionRegistry.register(ModItems.PINEAPPLE_SMOOTHIE.get(), 2);
        ExhaustionRegistry.register(ModItems.STRAWBERRY_SMOOTHIE.get(), 2);
        ExhaustionRegistry.register(ModItems.SWEETBERRY_SMOOTHIE.get(), 2);
    }
}