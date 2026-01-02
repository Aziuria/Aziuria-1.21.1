package net.Aziuria.aziuriamod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {

    // Fruits & Vegetables (light snacks, minor buffs)
    public static final FoodProperties PINEAPPLE = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(0.4f)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 80, 0), 0.2f)
            .build();

    public static final FoodProperties RADISH = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.25f)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 200, 0), 0.25f)
            .build();

    public static final FoodProperties CORN = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.25f)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 200, 0), 0.25f)
            .build();

    public static final FoodProperties CORN_KERNELS = new FoodProperties.Builder()
            .nutrition(1)                // small hunger refill
            .saturationModifier(0.1f)    // barely fills saturation
            .alwaysEdible()              // can eat even if not hungry
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 100, 0), 0.1f) // small chance for minor buff
            .build();

    public static final FoodProperties CUCUMBER = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.25f)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 200, 0), 0.25f)
            .build();

    public static final FoodProperties TOMATO = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.25f)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 200, 0), 0.25f)
            .build();

    public static final FoodProperties LETTUCE = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.25f)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 200, 0), 0.25f)
            .build();

    public static final FoodProperties ONION = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.25f)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 200, 0), 0.25f)
            .build();

    public static final FoodProperties SPRING_ONION = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.25f)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 200, 0), 0.25f)
            .build();

    // Light processed foods
    public static final FoodProperties CHICKEN_NUGGETS = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(0.6f)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 200, 0), 0.3f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 80, 0), 0.15f)
            .build();

    public static final FoodProperties FRENCH_FRIES = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.3f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 150, 0), 0.2f)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 100, 0), 0.1f)
            .build();

    public static final FoodProperties PANCAKE = new FoodProperties.Builder()
            .nutrition(5)
            .saturationModifier(0.65f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 150, 0), 0.2f)
            .effect(() -> new MobEffectInstance(MobEffects.JUMP, 150, 0), 0.1f)
            .build();

    // Premium meals (heavier, more nutrition, slight rewards)
    public static final FoodProperties BEEF_BURGER = new FoodProperties.Builder()
            .nutrition(12)
            .saturationModifier(0.85f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 150, 0), 0.2f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 80, 0), 0.1f)
            .build();

    public static final FoodProperties CHEESEBURGER = new FoodProperties.Builder()
            .nutrition(10)
            .saturationModifier(0.85f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 150, 0), 0.2f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 80, 0), 0.1f)
            .build();

    public static final FoodProperties PORKCHOP_BURGER = new FoodProperties.Builder()
            .nutrition(11)
            .saturationModifier(0.85f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 80, 0), 0.1f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 80, 0), 0.1f)
            .build();

    public static final FoodProperties CHEESE = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.7f)
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 150, 0), 0.15f)
            .build();

    // Fruits & Berries (light, small buffs)
    public static final FoodProperties PEAR = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(0.4f)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 40, 0), 0.1f)
            .build();

    public static final FoodProperties CHERRY = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.3f)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 40, 0), 0.1f)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 60, 0), 0.05f)
            .build();

    public static final FoodProperties AVOCADO = new FoodProperties.Builder()
            .nutrition(5)
            .saturationModifier(0.7f)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 80, 0), 0.15f)
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 150, 0), 0.1f)
            .build();

    public static final FoodProperties BLACKCURRANT = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.3f)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 60, 0), 0.15f)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 60, 0), 0.05f)
            .build();

    public static final FoodProperties STRAWBERRY = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.3f)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 40, 0), 0.1f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 60, 0), 0.05f)
            .build();

    // Juices (light, small immediate reward)
    public static final FoodProperties APPLE_JUICE = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.3f)
            .build();

    public static final FoodProperties PINEAPPLE_JUICE = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.35f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 80, 0), 0.1f)
            .build();

    public static final FoodProperties PEAR_JUICE = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.35f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 40, 0), 0.1f)
            .build();

    public static final FoodProperties CHERRY_JUICE = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.3f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 60, 0), 0.15f)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 60, 0), 0.05f)
            .build();

    public static final FoodProperties AVOCADO_JUICE = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.5f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 60, 0), 0.1f)
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 150, 0), 0.1f)
            .build();

    public static final FoodProperties BLACKCURRANT_JUICE = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.35f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 60, 0), 0.1f)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 60, 0), 0.05f)
            .build();

    public static final FoodProperties SWEETBERRY_JUICE = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.3f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 50, 0), 0.1f)
            .build();

    public static final FoodProperties GLOWBERRY_JUICE = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.3f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 40, 0), 0.1f)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 80, 0), 0.1f)
            .build();

    public static final FoodProperties STRAWBERRY_JUICE = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.3f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 60, 0), 0.1f)
            .build();

    public static final FoodProperties TOMATO_JUICE = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.35f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 50, 0), 0.1f)
            .build();

    public static final FoodProperties MILK_BOTTLE = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.3f)
            .build();

    public static final FoodProperties COFFEE = new FoodProperties.Builder()
            .nutrition(1)
            .saturationModifier(0.2f)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20 * 25, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 20 * 20, 0), 0.8f)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 20 * 8, 0), 0.05f)
            .build();

    public static final FoodProperties TEA = new FoodProperties.Builder()
            .nutrition(2) // light drink
            .saturationModifier(0.35f)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20 * 15, 0), 0.8f) // gentle speed boost (15s)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 20 * 8, 0), 0.4f)   // minor regen (8s)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 20 * 6, 0), 0.01f)
            .build();

    // Orange fruit (snack)
    public static final FoodProperties ORANGE = new FoodProperties.Builder()
            .nutrition(4)               // medium-light snack
            .saturationModifier(0.4f)  // keeps player fuller for a bit
            .alwaysEdible()            // can eat even if not hungry
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 40, 0), 0.15f) // slight healing
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 60, 0), 0.1f) // minor energy boost
            .build();

    public static final FoodProperties MELON_JUICE = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.35f)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 20, 0), 0.2f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20, 0), 0.15f)
            .build();

    // Orange juice (drink)
    public static final FoodProperties ORANGE_JUICE = new FoodProperties.Builder()
            .nutrition(3)               // light drink, less than whole fruit
            .saturationModifier(0.35f) // quenches thirst a little, keeps hunger stable
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 0), 0.15f) // light refresh boost
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 40, 0), 0.1f)     // slight healing
            .build();

    // Banana fruit (snack)
    public static final FoodProperties BANANA = new FoodProperties.Builder()
            .nutrition(5)               // slightly higher than orange, gives decent energy
            .saturationModifier(0.5f)  // fills hunger efficiently
            .alwaysEdible()            // can eat even if not hungry
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 60, 0), 0.2f)  // slight energy boost
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 40, 0), 0.1f)   // minor healing
            .build();

    // Banana smoothie (rich, nutritious drink)
    public static final FoodProperties BANANA_SMOOTHIE = new FoodProperties.Builder()
            .nutrition(6)               // hearty smoothie, more filling
            .saturationModifier(0.7f)   // excellent hunger refill
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 20 * 10, 0), 0.4f)     // gentle healing (10s)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20 * 10, 0), 0.2f) // feels strong/energized
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20 * 8, 0), 0.25f)   // mild sugar boost (8s)
            .build();

    // Banana juice (light drink)
    public static final FoodProperties BANANA_JUICE = new FoodProperties.Builder()
            .nutrition(3)               // light drink
            .saturationModifier(0.35f)  // refreshing, moderate satiation
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20, 0), 0.25f)  // mild energy rush (10s)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 20, 0), 0.15f)     // light healing (5s)
            .build();

    public static final FoodProperties APPLE_SMOOTHIE = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.7f)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 20 * 8, 0), 0.4f)
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 20 * 10, 0), 0.25f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20 * 6, 0), 0.2f)
            .build();

    public static final FoodProperties AVOCADO_SMOOTHIE = new FoodProperties.Builder()
            .nutrition(7)
            .saturationModifier(0.8f)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 20 * 10, 0), 0.4f)
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 20 * 15, 1), 0.25f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20 * 10, 0), 0.2f)
            .build();

    public static final FoodProperties BLACKCURRANT_SMOOTHIE = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.65f)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 20 * 8, 0), 0.4f)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 20 * 12, 0), 0.3f)
            .build();

    public static final FoodProperties CHERRY_SMOOTHIE = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.65f)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 20 * 8, 0), 0.4f)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 20 * 8, 0), 0.2f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20 * 6, 0), 0.15f)
            .build();

    public static final FoodProperties GLOWBERRY_SMOOTHIE = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.6f)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 20 * 12, 0), 0.35f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 20 * 8, 0), 0.3f)
            .build();

    public static final FoodProperties MELON_SMOOTHIE = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.7f)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 20 * 8, 0), 0.35f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20 * 8, 0), 0.2f)
            .build();

    public static final FoodProperties ORANGE_SMOOTHIE = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.7f)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20 * 10, 0), 0.3f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 20 * 8, 0), 0.2f)
            .build();

    public static final FoodProperties PEAR_SMOOTHIE = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.7f)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 20 * 8, 0), 0.3f)
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 20 * 8, 0), 0.2f)
            .build();

    public static final FoodProperties PINEAPPLE_SMOOTHIE = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.7f)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20 * 12, 0), 0.35f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 20 * 8, 0), 0.25f)
            .build();

    public static final FoodProperties STRAWBERRY_SMOOTHIE = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.7f)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 20 * 8, 0), 0.35f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20 * 6, 0), 0.2f)
            .build();

    public static final FoodProperties SWEETBERRY_SMOOTHIE = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.7f)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 20 * 8, 0), 0.35f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20 * 6, 0), 0.2f)
            .build();

    public static final FoodProperties BLUEBERRY = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.3f)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 50, 0), 0.15f)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 60, 0), 0.05f) // antioxidant eye-health vibe
            .build();

    public static final FoodProperties BLUEBERRY_JUICE = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.3f)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 50, 0), 0.15f)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 60, 0), 0.05f)
            .build();

    public static final FoodProperties BLUEBERRY_SMOOTHIE = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.7f)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 20 * 8, 0), 0.4f)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 20 * 12, 0), 0.25f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20 * 6, 0), 0.15f)
            .build();

    public static final FoodProperties GOOSEBERRY = new FoodProperties.Builder()
            .nutrition(3)                     // light fruit snack
            .saturationModifier(0.3f)         // similar to strawberry
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 50, 0), 0.15f)  // tangy boost
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 40, 0), 0.05f) // “fortifying” properties
            .build();

    public static final FoodProperties GOOSEBERRY_JUICE = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.3f)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 40, 0), 0.2f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 40, 0), 0.05f)
            .build();

    public static final FoodProperties GOOSEBERRY_SMOOTHIE = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.7f)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 20 * 8, 0), 0.4f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20 * 10, 0), 0.25f) // fortified feeling
            .build();

    public static final FoodProperties ENERGY_DRINK = new FoodProperties.Builder()
            .nutrition(2)                 // light hunger refill
            .saturationModifier(0.35f)    // minor saturation
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20 * 20, 0), 1.0f)   // 20s speed boost
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 20 * 20, 0), 0.8f)        // 20s mining boost
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 20 * 2, 0), 0.05f)       // minor jitter
            .build();
}