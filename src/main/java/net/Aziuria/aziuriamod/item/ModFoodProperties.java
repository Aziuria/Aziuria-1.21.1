package net.Aziuria.aziuriamod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {

    public static final FoodProperties PINEAPPLE = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(0.4f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 80, 0), 0.2f)
            .build();

    public static final FoodProperties RADISH = new FoodProperties.Builder().nutrition(3).saturationModifier(0.25f)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 400), 0.35f).build();

    public static final FoodProperties CORN = new FoodProperties.Builder().nutrition(3).saturationModifier(0.25f)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 400), 0.35f).build();

    public static final FoodProperties CUCUMBER = new FoodProperties.Builder().nutrition(3).saturationModifier(0.25f)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 400), 0.35f).build();

    public static final FoodProperties TOMATO = new FoodProperties.Builder().nutrition(3).saturationModifier(0.25f)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 400), 0.35f).build();

    public static final FoodProperties LETTUCE = new FoodProperties.Builder().nutrition(3).saturationModifier(0.25f)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 400), 0.35f).build();

    public static final FoodProperties ONION = new FoodProperties.Builder().nutrition(3).saturationModifier(0.25f)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 400), 0.35f).build();

    public static final FoodProperties SPRING_ONION = new FoodProperties.Builder().nutrition(3).saturationModifier(0.25f)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 400), 0.35f).build();

    public static final FoodProperties CHICKEN_NUGGETS = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(0.60f)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 400), 0.55f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 0.25f)
            .build();

    public static final FoodProperties BEEF_BURGER = new FoodProperties.Builder()
            .nutrition(12)
            .saturationModifier(0.85f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200), 0.3f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 0), 0.2f)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 200), 0.15f) // optional bonus for premium food
            .build();

    public static final FoodProperties CHEESEBURGER = new FoodProperties.Builder()
            .nutrition(10)
            .saturationModifier(0.85f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200), 0.3f) // beef strength
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 0), 0.2f) // energy boost
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200), 0.15f) // cheese shielding
            .build();

    public static final FoodProperties PORKCHOP_BURGER = new FoodProperties.Builder()
            .nutrition(11)
            .saturationModifier(0.85f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 0.15f) // slight regen
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 0), 0.15f) // small speed boost
            .build();

    public static final FoodProperties CHEESE = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.7f)
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200), 0.2f)
            .build();

    public static final FoodProperties FRENCH_FRIES = new FoodProperties.Builder()
            .nutrition(2) // less hunger restored than nuggets
            .saturationModifier(0.3f) // lower saturation because fries are mostly carbs/fats
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 0), 0.3f) // small speed boost
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 100, 0), 0.1f) // slight chance to cause hunger (fried food effect)
            .build();

    public static final FoodProperties PANCAKE = new FoodProperties.Builder()
            .nutrition(5) // satisfying, but not heavy
            .saturationModifier(0.65f) // decent carbs/fats
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 0), 0.25f) // slight energy boost
            .effect(() -> new MobEffectInstance(MobEffects.JUMP, 200, 0), 0.15f) // pancakes give you "bounce"
            .build();

    public static final FoodProperties PEAR = new FoodProperties.Builder()
            .nutrition(4) // similar to apple
            .saturationModifier(0.4f) // moderate
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 60, 0), 0.15f)
            .build();

    public static final FoodProperties CHERRY = new FoodProperties.Builder()
            .nutrition(3) // small, light snack
            .saturationModifier(0.3f) // moderate for a berry
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 60, 0), 0.15f)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 100, 0), 0.05f) // rare thematic bonus
            .build();

    public static final FoodProperties AVOCADO = new FoodProperties.Builder()
            .nutrition(5) // more filling than most fruits
            .saturationModifier(0.7f) // high healthy fat content
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 0.25f) // moderate regen chance
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200), 0.15f) // small bonus shielding
            .build();

    public static final FoodProperties BLACKCURRANT = new FoodProperties.Builder()
            .nutrition(3) // light snack like cherry
            .saturationModifier(0.3f) // moderate for berries
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 80, 0), 0.20f) // higher regen chance
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 100, 0), 0.05f) // rare bonus
            .build();

    public static final FoodProperties STRAWBERRY = new FoodProperties.Builder()
            .nutrition(3) // light snack
            .saturationModifier(0.3f) // modest berry saturation
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 60, 0), 0.15f) // mild regen chance
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 60, 0), 0.10f) // slight speed boost
            .build();

    public static final FoodProperties APPLE_JUICE = new FoodProperties.Builder()
            .nutrition(2) // small amount
            .saturationModifier(0.3f)
            .effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 0), 1.0f)
            .build();

    public static final FoodProperties PINEAPPLE_JUICE = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.35f)
            .effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 0), 0.25f)
            .build();

    public static final FoodProperties PEAR_JUICE = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.35f)
            .effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 60, 0), 0.15f)
            .build();

    public static final FoodProperties CHERRY_JUICE = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.3f)
            .effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 0.20f)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 100, 0), 0.10f)
            .build();

    public static final FoodProperties AVOCADO_JUICE = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.5f)
            .effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200), 0.20f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 0.15f)
            .build();

    public static final FoodProperties BLACKCURRANT_JUICE = new FoodProperties.Builder()
            .nutrition(2) // juice amount
            .saturationModifier(0.35f)
            .effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 0.25f) // better regen chance
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 100, 0), 0.07f) // rare thematic
            .build();

    public static final FoodProperties SWEETBERRY_JUICE = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.3f)
            .effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 80, 0), 0.20f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 0), 0.10f) // rare energy boost
            .build();

    public static final FoodProperties GLOWBERRY_JUICE = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.3f)
            .effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 60, 0), 0.15f)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 200, 0), 0.20f) // higher glow theme
            .build();

    public static final FoodProperties STRAWBERRY_JUICE = new FoodProperties.Builder()
            .nutrition(2) // small juice value
            .saturationModifier(0.3f)
            .effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 0), 1.0f) // instant small heal
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 0.25f) // stronger regen chance
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 60, 0), 0.10f) // light energy boost
            .build();

    public static final FoodProperties TOMATO_JUICE = new FoodProperties.Builder()
            .nutrition(2) // light drink
            .saturationModifier(0.35f) // slightly higher for savory juice
            .effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 0), 1.0f) // instant small heal
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 80, 0), 0.20f) // mild regen
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 0), 0.10f) // slight resistance
            .build();

}