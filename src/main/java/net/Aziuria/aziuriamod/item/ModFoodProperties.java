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
            .nutrition(8)
            .saturationModifier(0.8f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200), 0.3f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 0), 0.2f)
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

    public static final FoodProperties APPLE_JUICE = new FoodProperties.Builder()
            .nutrition(2) // small amount
            .saturationModifier(0.3f)
            .effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 0), 1.0f) // instant heal 1
            .build();

    public static final FoodProperties PINEAPPLE_JUICE = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.35f)
            .effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 0), 0.25f)
            .build();

}