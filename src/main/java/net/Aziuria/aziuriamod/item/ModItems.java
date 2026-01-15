package net.Aziuria.aziuriamod.item;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.Aziuria.aziuriamod.block.ModBlocks;
import net.Aziuria.aziuriamod.entity.ModEntity;
import net.Aziuria.aziuriamod.island.type.IslandBiomeType;
import net.Aziuria.aziuriamod.island.type.IslandType;
import net.Aziuria.aziuriamod.island.item.IslandThrowableItem;
import net.Aziuria.aziuriamod.item.custom.items.BaitedFishingRodItem;
import net.Aziuria.aziuriamod.item.custom.custom.DrinkableItem;
import net.Aziuria.aziuriamod.item.custom.items.MilkBottleItem;
import net.Aziuria.aziuriamod.item.custom.items.SackItem;
import net.Aziuria.aziuriamod.item.custom.items.SmoothieItem;
import net.Aziuria.aziuriamod.item.custom.mineral.SpectralDustItem;
import net.Aziuria.aziuriamod.item.custom.tools.KnifeItem;
import net.Aziuria.aziuriamod.item.custom.tools.MalletItem;
import net.Aziuria.aziuriamod.item.custom.tools.StoneShardItem;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AziuriaMod.MOD_ID);

    public static final DeferredItem<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TIN_INGOT = ITEMS.register("tin_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STEEL_ALLOY_MESH = ITEMS.register("steel_alloy_mesh",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SULPHUR = ITEMS.register("sulphur",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SPINEL = ITEMS.register("spinel",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> POTASSIUM = ITEMS.register("potassium",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TIN = ITEMS.register("raw_tin",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SPECTRAL_DUST = ITEMS.register("spectral_dust",
            () -> new SpectralDustItem(new Item.Properties()));
    public static final DeferredItem<Item> SPECTRAL_SUBSTANCE = ITEMS.register("spectral_substance",
            () -> new SpectralDustItem(new Item.Properties()));
    public static final DeferredItem<Item> SPECTRAL_INGOT = ITEMS.register("spectral_ingot",
            () -> new SpectralDustItem(new Item.Properties()));

    public static final DeferredItem<Item> COPPER_NUGGET = ITEMS.register("copper_nugget",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TIN_NUGGET = ITEMS.register("tin_nugget",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STEEL_NUGGET = ITEMS.register("steel_nugget",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> EMPTY_JAR = ITEMS.register("empty_jar",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> NET = ITEMS.register("net",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> NET_SEGMENT = ITEMS.register("net_segment",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> NAIL = ITEMS.register("nail",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> MALLET_HEAD = ITEMS.register("wooden_mallet_head",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<MalletItem> MALLET = ITEMS.register("wooden_mallet",
            () -> new MalletItem(Tiers.WOOD, new Item.Properties().durability(85)));

    public static final DeferredHolder<Item, Item> SACK_ITEM = ITEMS.register("sack_item", () -> new SackItem(new Item.Properties()));

    public static final DeferredItem<Item> WORM = ITEMS.register("worm",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BREAD_BAIT = ITEMS.register("bread_bait",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> OAK_BARK = ITEMS.register("oak_bark",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ACACIA_BARK = ITEMS.register("acacia_bark",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BIRCH_BARK = ITEMS.register("birch_bark",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CHERRY_BARK = ITEMS.register("cherry_bark",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DARK_OAK_BARK = ITEMS.register("dark_oak_bark",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> JUNGLE_BARK = ITEMS.register("jungle_bark",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> MANGROVE_BARK = ITEMS.register("mangrove_bark",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SPRUCE_BARK = ITEMS.register("spruce_bark",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> DRIED_GRASS = ITEMS.register("dried_grass",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<SwordItem> STEEL_SWORD = ITEMS.register("steel_sword",
            () -> new SwordItem(ModToolTiers.STEEL, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.STEEL, 3, -2.4f))));
    public static final DeferredItem<PickaxeItem> STEEL_PICKAXE = ITEMS.register("steel_pickaxe",
            () -> new PickaxeItem(ModToolTiers.STEEL, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.STEEL, 1.0F, -2.8f))));
    public static final DeferredItem<ShovelItem> STEEL_SHOVEL = ITEMS.register("steel_shovel",
            () -> new ShovelItem(ModToolTiers.STEEL, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.STEEL, 1.5F, -3.0f))));
    public static final DeferredItem<AxeItem> STEEL_AXE = ITEMS.register("steel_axe",
            () -> new AxeItem(ModToolTiers.STEEL, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.STEEL, 6.0F, -3.1f))));
    public static final DeferredItem<HoeItem> STEEL_HOE = ITEMS.register("steel_hoe",
            () -> new HoeItem(ModToolTiers.STEEL, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTiers.STEEL, -2.0F, -2.0f))));

    public static final DeferredItem<KnifeItem> WOOD_KNIFE = ITEMS.register("wooden_knife",
            () -> new KnifeItem(Tiers.WOOD, new Item.Properties().durability(59)));
    public static final DeferredItem<KnifeItem> STONE_KNIFE = ITEMS.register("stone_knife",
            () -> new KnifeItem(Tiers.STONE, new Item.Properties().durability(131)));
    public static final DeferredItem<KnifeItem> COPPER_KNIFE = ITEMS.register("copper_knife",
            () -> new KnifeItem(ModToolTiers.COPPER, new Item.Properties().durability(180)));
    public static final DeferredItem<KnifeItem> IRON_KNIFE = ITEMS.register("iron_knife",
            () -> new KnifeItem(Tiers.IRON, new Item.Properties().durability(250)));
    public static final DeferredItem<KnifeItem> GOLD_KNIFE = ITEMS.register("golden_knife",
            () -> new KnifeItem(Tiers.GOLD, new Item.Properties().durability(32)));
    public static final DeferredItem<KnifeItem> STEEL_KNIFE = ITEMS.register("steel_knife",
            () -> new KnifeItem(ModToolTiers.STEEL, new Item.Properties().durability(550)));
    public static final DeferredItem<KnifeItem> SPINEL_KNIFE = ITEMS.register("spinel_knife",
            () -> new KnifeItem(ModToolTiers.SPINEL, new Item.Properties().durability(750)));
    public static final DeferredItem<KnifeItem> DIAMOND_KNIFE = ITEMS.register("diamond_knife",
            () -> new KnifeItem(Tiers.DIAMOND, new Item.Properties().durability(1561)));
    public static final DeferredItem<KnifeItem> NETHERITE_KNIFE = ITEMS.register("netherite_knife",
            () -> new KnifeItem(Tiers.NETHERITE, new Item.Properties().durability(2031)));

    public static final DeferredItem<SwordItem> SPINEL_SWORD = ITEMS.register("spinel_sword",
            () -> new SwordItem(ModToolTiers.SPINEL, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.SPINEL, 3, -2.4f))));
    public static final DeferredItem<PickaxeItem> SPINEL_PICKAXE = ITEMS.register("spinel_pickaxe",
            () -> new PickaxeItem(ModToolTiers.SPINEL, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.SPINEL, 1.0F, -2.8f))));
    public static final DeferredItem<ShovelItem> SPINEL_SHOVEL = ITEMS.register("spinel_shovel",
            () -> new ShovelItem(ModToolTiers.SPINEL, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.SPINEL, 1.5F, -3.0f))));
    public static final DeferredItem<AxeItem> SPINEL_AXE = ITEMS.register("spinel_axe",
            () -> new AxeItem(ModToolTiers.SPINEL, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.SPINEL, 6.0F, -3.0f))));
    public static final DeferredItem<HoeItem> SPINEL_HOE = ITEMS.register("spinel_hoe",
            () -> new HoeItem(ModToolTiers.SPINEL, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTiers.SPINEL, -1.0F, -1.0f))));

    public static final DeferredItem<SwordItem> COPPER_SWORD = ITEMS.register("copper_sword",
            () -> new SwordItem(ModToolTiers.COPPER, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.COPPER, 3, -2.4f))));
    public static final DeferredItem<PickaxeItem> COPPER_PICKAXE = ITEMS.register("copper_pickaxe",
            () -> new PickaxeItem(ModToolTiers.COPPER, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.COPPER, 1.0F, -2.8f))));
    public static final DeferredItem<ShovelItem> COPPER_SHOVEL = ITEMS.register("copper_shovel",
            () -> new ShovelItem(ModToolTiers.COPPER, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.COPPER, 1.5F, -3.0f))));
    public static final DeferredItem<AxeItem> COPPER_AXE = ITEMS.register("copper_axe",
            () -> new AxeItem(ModToolTiers.COPPER, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.COPPER, 7.0F, -3.2f))));
    public static final DeferredItem<HoeItem> COPPER_HOE = ITEMS.register("copper_hoe",
            () -> new HoeItem(ModToolTiers.COPPER, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTiers.COPPER, -1.0F, -3.0f))));


    public static final DeferredItem<ArmorItem> STEEL_HELMET = ITEMS.register("steel_helmet",
            () -> new ArmorItem(ModArmorMaterials.STEEL_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(20))));
    public static final DeferredItem<ArmorItem> STEEL_CHESTPLATE = ITEMS.register("steel_chestplate",
            () -> new ArmorItem(ModArmorMaterials.STEEL_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(20))));
    public static final DeferredItem<ArmorItem> STEEL_LEGGINGS = ITEMS.register("steel_leggings",
            () -> new ArmorItem(ModArmorMaterials.STEEL_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(20))));
    public static final DeferredItem<ArmorItem> STEEL_BOOTS = ITEMS.register("steel_boots",
            () -> new ArmorItem(ModArmorMaterials.STEEL_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(20))));

    public static final DeferredItem<ArmorItem> SPINEL_HELMET = ITEMS.register("spinel_helmet",
            () -> new ArmorItem(ModArmorMaterials.SPINEL_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(25))));
    public static final DeferredItem<ArmorItem> SPINEL_CHESTPLATE = ITEMS.register("spinel_chestplate",
            () -> new ArmorItem(ModArmorMaterials.SPINEL_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(25))));
    public static final DeferredItem<ArmorItem> SPINEL_LEGGINGS = ITEMS.register("spinel_leggings",
            () -> new ArmorItem(ModArmorMaterials.SPINEL_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(25))));
    public static final DeferredItem<ArmorItem> SPINEL_BOOTS = ITEMS.register("spinel_boots",
            () -> new ArmorItem(ModArmorMaterials.SPINEL_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(17))));

    public static final DeferredItem<ArmorItem> COPPER_HELMET = ITEMS.register("copper_helmet",
            () -> new ArmorItem(ModArmorMaterials.COPPER_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(12))));
    public static final DeferredItem<ArmorItem> COPPER_CHESTPLATE = ITEMS.register("copper_chestplate",
            () -> new ArmorItem(ModArmorMaterials.COPPER_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(12))));
    public static final DeferredItem<ArmorItem> COPPER_LEGGINGS = ITEMS.register("copper_leggings",
            () -> new ArmorItem(ModArmorMaterials.COPPER_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(12))));
    public static final DeferredItem<ArmorItem> COPPER_BOOTS = ITEMS.register("copper_boots",
            () -> new ArmorItem(ModArmorMaterials.COPPER_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(12))));

    public static final DeferredItem<Item> STEEL_HORSE_ARMOR = ITEMS.register("steel_horse_armor",
            () -> new AnimalArmorItem(ModArmorMaterials.STEEL_ARMOR_MATERIAL, AnimalArmorItem.BodyType.EQUESTRIAN,
                    false, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> SPINEL_HORSE_ARMOR = ITEMS.register("spinel_horse_armor",
            () -> new AnimalArmorItem(ModArmorMaterials.SPINEL_ARMOR_MATERIAL, AnimalArmorItem.BodyType.EQUESTRIAN,
                    false, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> COPPER_HORSE_ARMOR = ITEMS.register("copper_horse_armor",
            () -> new AnimalArmorItem(ModArmorMaterials.COPPER_ARMOR_MATERIAL, AnimalArmorItem.BodyType.EQUESTRIAN,
                    false, new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> LASHING = ITEMS.register("lashing",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> EMPTY_CUP = ITEMS.register("empty_cup",
            () -> new Item(new Item.Properties()));

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

    public static final DeferredItem<Item> CORN_KERNELS = ITEMS.register("corn_kernels",
            () -> new Item(new Item.Properties().food(ModFoodProperties.CORN_KERNELS)));

    public static final DeferredItem<Item> COFFEE_BEANS = ITEMS.register("coffee_beans",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COFFEE_SEEDS = ITEMS.register("coffee_seeds",
            () -> new ItemNameBlockItem(ModBlocks.COFFEE_CROP.get(), new Item.Properties()));

    public static final DeferredItem<Item> TEA_LEAVES = ITEMS.register("tea_leaves",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TEA_SEEDS = ITEMS.register("tea_seeds",
            () -> new ItemNameBlockItem(ModBlocks.TEA_CROP.get(), new Item.Properties()));

    public static final DeferredItem<Item> FLAX_FLOWER = ITEMS.register("flax_flower",
            () -> new BlockItem(ModBlocks.FLAX_FLOWER_BLOCK.get(), new Item.Properties()));

    // The placeable block item for the yucca plant
    public static final DeferredItem<Item> YUCCA_PLANT = ITEMS.register("yucca_plant",
            () -> new BlockItem(ModBlocks.YUCCA_PLANT_BLOCK.get(), new Item.Properties()));

    // The harvestable item (drop)
    public static final DeferredItem<Item> YUCCA_LEAVES = ITEMS.register("yucca_leaves",
            () -> new Item(new Item.Properties()));

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
    public static final DeferredItem<Item> AVOCADO = ITEMS.register("avocado",
            () -> new Item(new Item.Properties().food(ModFoodProperties.AVOCADO)));
    public static final DeferredItem<Item> ORANGE = ITEMS.register("orange",
            () -> new Item(new Item.Properties().food(ModFoodProperties.ORANGE)));
    public static final DeferredItem<Item> BANANA = ITEMS.register("banana",
            () -> new Item(new Item.Properties().food(ModFoodProperties.BANANA)));

    public static final DeferredItem<Item> STRAWBERRY = ITEMS.register("strawberry",
            () -> new Item(new Item.Properties().food(ModFoodProperties.STRAWBERRY)));

    public static final DeferredItem<Item> STRAWBERRY_SEEDS = ITEMS.register("strawberry_seeds",
            () -> new ItemNameBlockItem(ModBlocks.STRAWBERRY_BUSH.get(), new Item.Properties()));

    public static final DeferredItem<Item> BLACKCURRANT = ITEMS.register("blackcurrant",
            () -> new Item(new Item.Properties().food(ModFoodProperties.BLACKCURRANT)));

    public static final DeferredItem<Item> BLACKCURRANT_SEEDS = ITEMS.register("blackcurrant_seeds",
            () -> new ItemNameBlockItem(ModBlocks.BLACKCURRANT_BUSH.get(), new Item.Properties()));

    public static final DeferredItem<Item> BLUEBERRY = ITEMS.register("blueberry",
            () -> new Item(new Item.Properties().food(ModFoodProperties.BLUEBERRY)));

    public static final DeferredItem<Item> BLUEBERRY_SEEDS = ITEMS.register("blueberry_seeds",
            () -> new ItemNameBlockItem(ModBlocks.BLUEBERRY_BUSH.get(), new Item.Properties()));

    public static final DeferredItem<Item> GOOSEBERRY = ITEMS.register("gooseberry",
            () -> new Item(new Item.Properties().food(ModFoodProperties.GOOSEBERRY)));

    public static final DeferredItem<Item> GOOSEBERRY_SEEDS = ITEMS.register("gooseberry_seeds",
            () -> new ItemNameBlockItem(ModBlocks.GOOSEBERRY_BUSH.get(), new Item.Properties()));


    public static final DeferredItem<DrinkableItem> ENERGY_DRINK = ITEMS.register("energy_drink",
            () -> new DrinkableItem(
                    new Item.Properties()
                            .food(ModFoodProperties.ENERGY_DRINK)
                            .stacksTo(3)
                            .craftRemainder(Items.GLASS_BOTTLE),
                    32,
                    UseAnim.DRINK));

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

    public static final DeferredItem<DrinkableItem> COFFEE = ITEMS.register("coffee",
            () -> new DrinkableItem(
                    new Item.Properties()
                            .food(ModFoodProperties.COFFEE)
                            .stacksTo(1)
                            .craftRemainder(Items.GLASS_BOTTLE),
                    32,
                    UseAnim.DRINK));

    public static final DeferredItem<DrinkableItem> TEA = ITEMS.register("tea",
            () -> new DrinkableItem(
                    new Item.Properties()
                            .food(ModFoodProperties.TEA)
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

    public static final DeferredItem<DrinkableItem> MELON_JUICE = ITEMS.register("melon_juice",
            () -> new DrinkableItem(
                    new Item.Properties()
                            .food(ModFoodProperties.MELON_JUICE)
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

    public static final DeferredItem<DrinkableItem> AVOCADO_JUICE = ITEMS.register("avocado_juice",
            () -> new DrinkableItem(
                    new Item.Properties()
                            .food(ModFoodProperties.AVOCADO_JUICE)
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

    public static final DeferredItem<DrinkableItem> BLUEBERRY_JUICE = ITEMS.register("blueberry_juice",
            () -> new DrinkableItem(
                    new Item.Properties()
                            .food(ModFoodProperties.BLUEBERRY_JUICE)
                            .stacksTo(1)
                            .craftRemainder(Items.GLASS_BOTTLE),
                    32,
                    UseAnim.DRINK));

    public static final DeferredItem<DrinkableItem> GOOSEBERRY_JUICE = ITEMS.register("gooseberry_juice",
            () -> new DrinkableItem(
                    new Item.Properties()
                            .food(ModFoodProperties.GOOSEBERRY_JUICE)
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

    public static final DeferredItem<DrinkableItem> ORANGE_JUICE = ITEMS.register("orange_juice",
            () -> new DrinkableItem(
                    new Item.Properties()
                            .food(ModFoodProperties.ORANGE_JUICE)
                            .stacksTo(1)
                            .craftRemainder(Items.GLASS_BOTTLE),
                    32,
                    UseAnim.DRINK));

    public static final DeferredItem<DrinkableItem> BANANA_JUICE = ITEMS.register("banana_juice",
            () -> new DrinkableItem(
                    new Item.Properties()
                            .food(ModFoodProperties.BANANA_JUICE)
                            .stacksTo(1)
                            .craftRemainder(Items.GLASS_BOTTLE),
                    32,
                    UseAnim.DRINK));

    public static final DeferredItem<SmoothieItem> BANANA_SMOOTHIE = ITEMS.register("banana_smoothie",
            () -> new SmoothieItem(
                    new Item.Properties()
                            .food(ModFoodProperties.BANANA_SMOOTHIE)
                            .stacksTo(1)
            ));

    public static final DeferredItem<SmoothieItem> APPLE_SMOOTHIE = ITEMS.register("apple_smoothie",
            () -> new SmoothieItem(
                    new Item.Properties()
                            .food(ModFoodProperties.APPLE_SMOOTHIE)
                            .stacksTo(1)
            ));

    public static final DeferredItem<SmoothieItem> AVOCADO_SMOOTHIE = ITEMS.register("avocado_smoothie",
            () -> new SmoothieItem(
                    new Item.Properties()
                            .food(ModFoodProperties.AVOCADO_SMOOTHIE)
                            .stacksTo(1)
            ));

    public static final DeferredItem<SmoothieItem> BLACKCURRANT_SMOOTHIE = ITEMS.register("blackcurrant_smoothie",
            () -> new SmoothieItem(
                    new Item.Properties()
                            .food(ModFoodProperties.BLACKCURRANT_SMOOTHIE)
                            .stacksTo(1)
            ));

    public static final DeferredItem<SmoothieItem> BLUEBERRY_SMOOTHIE = ITEMS.register("blueberry_smoothie",
            () -> new SmoothieItem(
                    new Item.Properties()
                            .food(ModFoodProperties.BLUEBERRY_SMOOTHIE)
                            .stacksTo(1)
            ));

    public static final DeferredItem<SmoothieItem> GOOSEBERRY_SMOOTHIE = ITEMS.register("gooseberry_smoothie",
            () -> new SmoothieItem(
                    new Item.Properties()
                            .food(ModFoodProperties.GOOSEBERRY_SMOOTHIE)
                            .stacksTo(1)
            ));

    public static final DeferredItem<SmoothieItem> CHERRY_SMOOTHIE = ITEMS.register("cherry_smoothie",
            () -> new SmoothieItem(
                    new Item.Properties()
                            .food(ModFoodProperties.CHERRY_SMOOTHIE)
                            .stacksTo(1)
            ));

    public static final DeferredItem<SmoothieItem> GLOWBERRY_SMOOTHIE = ITEMS.register("glowberry_smoothie",
            () -> new SmoothieItem(
                    new Item.Properties()
                            .food(ModFoodProperties.GLOWBERRY_SMOOTHIE)
                            .stacksTo(1)
            ));

    public static final DeferredItem<SmoothieItem> MELON_SMOOTHIE = ITEMS.register("melon_smoothie",
            () -> new SmoothieItem(
                    new Item.Properties()
                            .food(ModFoodProperties.MELON_SMOOTHIE)
                            .stacksTo(1)
            ));

    public static final DeferredItem<SmoothieItem> ORANGE_SMOOTHIE = ITEMS.register("orange_smoothie",
            () -> new SmoothieItem(
                    new Item.Properties()
                            .food(ModFoodProperties.ORANGE_SMOOTHIE)
                            .stacksTo(1)
            ));

    public static final DeferredItem<SmoothieItem> PEAR_SMOOTHIE = ITEMS.register("pear_smoothie",
            () -> new SmoothieItem(
                    new Item.Properties()
                            .food(ModFoodProperties.PEAR_SMOOTHIE)
                            .stacksTo(1)
            ));

    public static final DeferredItem<SmoothieItem> PINEAPPLE_SMOOTHIE = ITEMS.register("pineapple_smoothie",
            () -> new SmoothieItem(
                    new Item.Properties()
                            .food(ModFoodProperties.PINEAPPLE_SMOOTHIE)
                            .stacksTo(1)
            ));

    public static final DeferredItem<SmoothieItem> STRAWBERRY_SMOOTHIE = ITEMS.register("strawberry_smoothie",
            () -> new SmoothieItem(
                    new Item.Properties()
                            .food(ModFoodProperties.STRAWBERRY_SMOOTHIE)
                            .stacksTo(1)
            ));

    public static final DeferredItem<SmoothieItem> SWEETBERRY_SMOOTHIE = ITEMS.register("sweetberry_smoothie",
            () -> new SmoothieItem(
                    new Item.Properties()
                            .food(ModFoodProperties.SWEETBERRY_SMOOTHIE)
                            .stacksTo(1)
            ));

    public static final DeferredItem<MilkBottleItem> MILK_BOTTLE = ITEMS.register("milk_bottle",
            () -> new MilkBottleItem(
                    new Item.Properties()
                            .food(ModFoodProperties.MILK_BOTTLE)
                            .stacksTo(16) // bottles can stack
                            .craftRemainder(Items.GLASS_BOTTLE)
            ));


    public static final DeferredItem<Item> ISLAND_GENERATOR_SMALL = ITEMS.register("island_generator_small",
            () -> new IslandThrowableItem(IslandType.SMALL, IslandBiomeType.PLAINS, new Item.Properties().stacksTo(16)));

    public static final DeferredItem<Item> ISLAND_GENERATOR_MEDIUM = ITEMS.register("island_generator_medium",
            () -> new IslandThrowableItem(IslandType.MEDIUM, IslandBiomeType.PLAINS, new Item.Properties().stacksTo(16)));

    public static final DeferredItem<Item> ISLAND_GENERATOR_LARGE = ITEMS.register("island_generator_large",
            () -> new IslandThrowableItem(IslandType.LARGE, IslandBiomeType.PLAINS, new Item.Properties().stacksTo(16)));

    public static final DeferredItem<Item> WORM_FISHING_ROD = ITEMS.register("worm_fishing_rod",
            () -> new BaitedFishingRodItem(new Item.Properties().stacksTo(1).durability(64)));

    public static final DeferredItem<Item> BREAD_FISHING_ROD = ITEMS.register("bread_fishing_rod",
            () -> new BaitedFishingRodItem(new Item.Properties().stacksTo(1).durability(64)));

    public static final DeferredItem<Item> CORN_FISHING_ROD = ITEMS.register("corn_fishing_rod",
            () -> new BaitedFishingRodItem(new Item.Properties().stacksTo(1).durability(64)));

    public static final DeferredItem<Item> WORM_SPAWN_EGG = ITEMS.register("worm_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntity.WORM, 0x6B4226, 0xD19C77,
                    new Item.Properties()));

    public static final DeferredItem<StoneShardItem> STONE_SHARD = ITEMS.register("stone_shard",
            () -> new StoneShardItem(new Item.Properties()
                    .stacksTo(1)
                    .durability(5))); // 3–6 uses, adjust if needed

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);



    }
}
