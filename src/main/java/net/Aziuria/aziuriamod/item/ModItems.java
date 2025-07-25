package net.Aziuria.aziuriamod.item;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.Aziuria.aziuriamod.block.ModBlocks;
import net.Aziuria.aziuriamod.block.world.IslandBiomeType;
import net.Aziuria.aziuriamod.block.world.IslandType;
import net.Aziuria.aziuriamod.item.custom.DrinkableItem;
import net.Aziuria.aziuriamod.item.custom.FuelItem;
import net.Aziuria.aziuriamod.item.custom.IslandThrowableItem;
import net.Aziuria.aziuriamod.item.custom.KnifeItem;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AziuriaMod.MOD_ID);

    public static final DeferredItem<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STEEL_ALLOY_MESH = ITEMS.register("steel_alloy_mesh",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SULPHUR = ITEMS.register("sulphur",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> POTASSIUM = ITEMS.register("potassium",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> SACK_ITEM = ITEMS.register("sack_item", () -> new SackItem(new Item.Properties()));


    public static final DeferredItem<SwordItem> STEEL_SWORD = ITEMS.register("steel_sword",
            () -> new SwordItem(ModToolTiers.STEEL, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.STEEL, 4, -2.4f))));
    public static final DeferredItem<PickaxeItem> STEEL_PICKAXE = ITEMS.register("steel_pickaxe",
            () -> new PickaxeItem(ModToolTiers.STEEL, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.STEEL, 1.0F, -2.8f))));
    public static final DeferredItem<ShovelItem> STEEL_SHOVEL = ITEMS.register("steel_shovel",
            () -> new ShovelItem(ModToolTiers.STEEL, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.STEEL, 1.5F, -3.0f))));
    public static final DeferredItem<AxeItem> STEEL_AXE = ITEMS.register("steel_axe",
            () -> new AxeItem(ModToolTiers.STEEL, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.STEEL, 6.0F, -2.8f))));
    public static final DeferredItem<HoeItem> STEEL_HOE = ITEMS.register("steel_hoe",
            () -> new HoeItem(ModToolTiers.STEEL, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTiers.STEEL, 1F, -3.0f))));
    public static final DeferredItem<KnifeItem> KNIFE = ITEMS.register("knife",
            () -> new KnifeItem(new Item.Properties().durability(59)));


    public static final DeferredItem<ArmorItem> STEEL_HELMET = ITEMS.register("steel_helmet",
            () -> new ArmorItem(ModArmorMaterials.STEEL_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(19))));
    public static final DeferredItem<ArmorItem> STEEL_CHESTPLATE = ITEMS.register("steel_chestplate",
            () -> new ArmorItem(ModArmorMaterials.STEEL_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(19))));
    public static final DeferredItem<ArmorItem> STEEL_LEGGINGS = ITEMS.register("steel_leggings",
            () -> new ArmorItem(ModArmorMaterials.STEEL_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(19))));
    public static final DeferredItem<ArmorItem> STEEL_BOOTS = ITEMS.register("steel_boots",
            () -> new ArmorItem(ModArmorMaterials.STEEL_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(19))));

    public static final DeferredItem<Item> RADISH = ITEMS.register("radish",
            () -> new Item(new Item.Properties().food(ModFoodProperties.RADISH)));
    public static final DeferredItem<Item> RADISH_SEEDS = ITEMS.register("radish_seeds",
            () -> new ItemNameBlockItem(ModBlocks.RADISH_CROP.get(), new Item.Properties()));

    public static final DeferredItem<Item> CUCUMBER = ITEMS.register("cucumber",
            () -> new Item(new Item.Properties().food(ModFoodProperties.CUCUMBER)));
    public static final DeferredItem<Item> CUCUMBER_SEEDS = ITEMS.register("cucumber_seeds",
            () -> new ItemNameBlockItem(ModBlocks.CUCUMBER_CROP.get(), new Item.Properties()));

    public static final DeferredItem<Item> TOMATO = ITEMS.register("tomato",
            () -> new Item(new Item.Properties().food(ModFoodProperties.TOMATO)));
    public static final DeferredItem<Item> TOMATO_SEEDS = ITEMS.register("tomato_seeds",
            () -> new ItemNameBlockItem(ModBlocks.TOMATO_CROP.get(), new Item.Properties()));

    public static final DeferredItem<Item> ONION = ITEMS.register("onion",
            () -> new Item(new Item.Properties().food(ModFoodProperties.ONION)));
    public static final DeferredItem<Item> ONION_SEEDS = ITEMS.register("onion_seeds",
            () -> new ItemNameBlockItem(ModBlocks.ONION_CROP.get(), new Item.Properties()));

    public static final DeferredItem<Item> LETTUCE = ITEMS.register("lettuce",
            () -> new Item(new Item.Properties().food(ModFoodProperties.LETTUCE)));
    public static final DeferredItem<Item> LETTUCE_SEEDS = ITEMS.register("lettuce_seeds",
            () -> new ItemNameBlockItem(ModBlocks.LETTUCE_CROP.get(), new Item.Properties()));

    public static final DeferredItem<Item> SPRING_ONION = ITEMS.register("spring_onion",
            () -> new Item(new Item.Properties().food(ModFoodProperties.SPRING_ONION)));
    public static final DeferredItem<Item> SPRING_ONION_SEEDS = ITEMS.register("spring_onion_seeds",
            () -> new ItemNameBlockItem(ModBlocks.SPRING_ONION_CROP.get(), new Item.Properties()));

    public static final DeferredItem<Item> PINEAPPLE = ITEMS.register("pineapple",
            () -> new Item(new Item.Properties().food(ModFoodProperties.PINEAPPLE)));
    public static final DeferredItem<Item> PINEAPPLE_SEEDS = ITEMS.register("pineapple_seeds",
            () -> new ItemNameBlockItem(ModBlocks.PINEAPPLE_CROP.get(), new Item.Properties()));

    public static final DeferredItem<Item> CORN = ITEMS.register("corn",
            () -> new Item(new Item.Properties().food(ModFoodProperties.CORN)));
    public static final DeferredItem<Item> CORN_SEEDS = ITEMS.register("corn_seeds",
            () -> new ItemNameBlockItem(ModBlocks.CORN_CROP.get(), new Item.Properties()));

    public static final DeferredItem<Item> FLAX_FLOWER = ITEMS.register("flax_flower",
            () -> new BlockItem(ModBlocks.FLAX_FLOWER_BLOCK.get(), new Item.Properties()));

    public static final DeferredItem<Item> FLOUR = ITEMS.register("flour",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PANCAKE_DOUGH = ITEMS.register("pancake_dough",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BATTERED_CHICKEN = ITEMS.register("battered_chicken",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DICED_CHICKEN = ITEMS.register("diced_chicken",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CHICKEN_NUGGETS = ITEMS.register("chicken_nuggets",
            () -> new Item(new Item.Properties().food(ModFoodProperties.CHICKEN_NUGGETS)));
    public static final DeferredItem<Item> FRENCH_FRIES = ITEMS.register("french_fries",
            () -> new Item(new Item.Properties().food(ModFoodProperties.FRENCH_FRIES)));
    public static final DeferredItem<Item> BEEF_BURGER = ITEMS.register("beef_burger",
            () -> new Item(new Item.Properties().food(ModFoodProperties.BEEF_BURGER)));
    public static final DeferredItem<Item> CHEESEBURGER = ITEMS.register("cheeseburger",
            () -> new Item(new Item.Properties().food(ModFoodProperties.CHEESEBURGER)));
    public static final DeferredItem<Item> PORKCHOP_BURGER = ITEMS.register("porkchop_burger",
            () -> new Item(new Item.Properties().food(ModFoodProperties.PORKCHOP_BURGER)));
    public static final DeferredItem<Item> CHEESE = ITEMS.register("cheese",
            () -> new Item(new Item.Properties().food(ModFoodProperties.CHEESE)));
    public static final DeferredItem<Item> PANCAKE = ITEMS.register("pancake",
            () -> new Item(new Item.Properties().food(ModFoodProperties.PANCAKE)));
    public static final DeferredItem<Item> PEAR = ITEMS.register("pear",
            () -> new Item(new Item.Properties().food(ModFoodProperties.PEAR)));
    public static final DeferredItem<Item> CHERRY = ITEMS.register("cherry",
            () -> new Item(new Item.Properties().food(ModFoodProperties.CHERRY)));

    public static final DeferredItem<Item> STRAWBERRY = ITEMS.register("strawberry",
            () -> new Item(new Item.Properties().food(ModFoodProperties.STRAWBERRY)));
    public static final DeferredItem<Item> STRAWBERRY_SEEDS = ITEMS.register("strawberry_seeds",
            () -> new ItemNameBlockItem(ModBlocks.STRAWBERRY_BUSH.get(), new Item.Properties()));

    public static final DeferredItem<Item> BLACKCURRANT = ITEMS.register("blackcurrant",
            () -> new Item(new Item.Properties().food(ModFoodProperties.BLACKCURRANT)));

    public static final DeferredItem<Item> BLACKCURRANT_SEEDS = ITEMS.register("blackcurrant_seeds",
            () -> new ItemNameBlockItem(ModBlocks.BLACKCURRANT_BUSH.get(), new Item.Properties()));

    public static final DeferredItem<DrinkableItem> APPLE_JUICE = ITEMS.register("apple_juice",
            () -> new DrinkableItem(
                    new Item.Properties()
                            .food(ModFoodProperties.APPLE_JUICE)
                            .stacksTo(1)
                            .craftRemainder(Items.GLASS_BOTTLE),
                    32,
                    UseAnim.DRINK));

    public static final DeferredItem<DrinkableItem> PINEAPPLE_JUICE = ITEMS.register("pineapple_juice",
            () -> new DrinkableItem(
                    new Item.Properties()
                            .food(ModFoodProperties.PINEAPPLE_JUICE)
                            .stacksTo(1)
                            .craftRemainder(Items.GLASS_BOTTLE),
                    32,
                    UseAnim.DRINK));

    public static final DeferredItem<DrinkableItem> PEAR_JUICE = ITEMS.register("pear_juice",
            () -> new DrinkableItem(
                    new Item.Properties()
                            .food(ModFoodProperties.PEAR_JUICE)
                            .stacksTo(1)
                            .craftRemainder(Items.GLASS_BOTTLE),
                    32,
                    UseAnim.DRINK));

    public static final DeferredItem<DrinkableItem> CHERRY_JUICE = ITEMS.register("cherry_juice",
            () -> new DrinkableItem(
                    new Item.Properties()
                            .food(ModFoodProperties.CHERRY_JUICE)
                            .stacksTo(1)
                            .craftRemainder(Items.GLASS_BOTTLE),
                    32,
                    UseAnim.DRINK));

    public static final DeferredItem<DrinkableItem> BLACKCURRANT_JUICE = ITEMS.register("blackcurrant_juice",
            () -> new DrinkableItem(
                    new Item.Properties()
                            .food(ModFoodProperties.BLACKCURRANT_JUICE)
                            .stacksTo(1)
                            .craftRemainder(Items.GLASS_BOTTLE),
                    32,
                    UseAnim.DRINK));

    public static final DeferredItem<DrinkableItem> SWEETBERRY_JUICE = ITEMS.register("sweetberry_juice",
            () -> new DrinkableItem(
                    new Item.Properties()
                            .food(ModFoodProperties.SWEETBERRY_JUICE)
                            .stacksTo(1)
                            .craftRemainder(Items.GLASS_BOTTLE),
                    32,
                    UseAnim.DRINK));

    public static final DeferredItem<DrinkableItem> GLOWBERRY_JUICE = ITEMS.register("glowberry_juice",
            () -> new DrinkableItem(
                    new Item.Properties()
                            .food(ModFoodProperties.GLOWBERRY_JUICE)
                            .stacksTo(1)
                            .craftRemainder(Items.GLASS_BOTTLE),
                    32,
                    UseAnim.DRINK));

    public static final DeferredItem<DrinkableItem> TOMATO_JUICE = ITEMS.register("tomato_juice",
            () -> new DrinkableItem(
                    new Item.Properties()
                            .food(ModFoodProperties.TOMATO_JUICE)
                            .stacksTo(1)
                            .craftRemainder(Items.GLASS_BOTTLE),
                    32,
                    UseAnim.DRINK));

    public static final DeferredItem<DrinkableItem> STRAWBERRY_JUICE = ITEMS.register("strawberry_juice",
            () -> new DrinkableItem(
                    new Item.Properties()
                            .food(ModFoodProperties.STRAWBERRY_JUICE)
                            .stacksTo(1)
                            .craftRemainder(Items.GLASS_BOTTLE),
                    32,
                    UseAnim.DRINK));

    public static final DeferredItem<Item> ISLAND_GENERATOR_SMALL = ITEMS.register("island_generator_small",
            () -> new IslandThrowableItem(IslandType.SMALL, IslandBiomeType.PLAINS, new Item.Properties().stacksTo(16)));

    public static final DeferredItem<Item> ISLAND_GENERATOR_MEDIUM = ITEMS.register("island_generator_medium",
            () -> new IslandThrowableItem(IslandType.MEDIUM, IslandBiomeType.PLAINS, new Item.Properties().stacksTo(16)));

    public static final DeferredItem<Item> ISLAND_GENERATOR_LARGE = ITEMS.register("island_generator_large",
            () -> new IslandThrowableItem(IslandType.LARGE, IslandBiomeType.PLAINS, new Item.Properties().stacksTo(16)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);



    }
}
