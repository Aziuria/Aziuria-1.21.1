package net.Aziuria.aziuriamod.item;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.Aziuria.aziuriamod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AziuriaMod.MOD_ID);

    public static final Supplier<CreativeModeTab> USEFUL_ITEMS_AND_BLOCKS = CREATIVE_MODE_TAB.register("useful_items_and_tools",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.STEEL_INGOT.get()))
                    .title(Component.translatable("creativetab.aziuriamod.useful_items_and_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {

                        // ======= 🔧 UTILITY & MATERIALS =======
                        output.accept(ModItems.SACK_ITEM.get());
                        output.accept(ModItems.EMPTY_CUP.get());
                        output.accept(ModItems.LASHING);
                        output.accept(ModItems.STEEL_ALLOY_MESH);
                        output.accept(ModItems.STEEL_INGOT);
                        output.accept(ModItems.COPPER_NUGGET);
                        output.accept(ModItems.STEEL_NUGGET);
                        output.accept(ModItems.POTASSIUM);
                        output.accept(ModItems.SULPHUR);
                        output.accept(ModItems.SPINEL);

                        // ======= ⛏️ ORES & MINERALS =======
                        output.accept(ModBlocks.POTASSIUM_ORE);
                        output.accept(ModBlocks.DEEPSLATE_POTASSIUM_ORE);
                        output.accept(ModBlocks.SULPHUR_ORE);
                        output.accept(ModBlocks.DEEPSLATE_SULPHUR_ORE);
                        output.accept(ModBlocks.SPINEL_ORE);
                        output.accept(ModBlocks.DEEPSLATE_SPINEL_ORE);
                        output.accept(ModBlocks.SPECTRAL_ORE);
                        output.accept(ModBlocks.DEEPSLATE_SPECTRAL_ORE);

                        // ======= 🧱 BLOCKS & METALS =======
                        output.accept(ModBlocks.STEEL_BLOCK);
                        output.accept(ModBlocks.UNBREAKABLE_GLASS);

                        // ======= ⚒️ TOOLS =======
                        // Copper
                        output.accept(ModItems.COPPER_PICKAXE);
                        output.accept(ModItems.COPPER_AXE);
                        output.accept(ModItems.COPPER_SWORD);
                        output.accept(ModItems.COPPER_SHOVEL);
                        output.accept(ModItems.COPPER_HOE);

                        // Steel
                        output.accept(ModItems.STEEL_PICKAXE);
                        output.accept(ModItems.STEEL_AXE);
                        output.accept(ModItems.STEEL_SWORD);
                        output.accept(ModItems.STEEL_SHOVEL);
                        output.accept(ModItems.STEEL_HOE);

                        // Spinel
                        output.accept(ModItems.SPINEL_PICKAXE);
                        output.accept(ModItems.SPINEL_AXE);
                        output.accept(ModItems.SPINEL_SWORD);
                        output.accept(ModItems.SPINEL_SHOVEL);
                        output.accept(ModItems.SPINEL_HOE);

                        // Misc
                        output.accept(ModItems.KNIFE);

                        // ======= 🛡️ ARMOR =======
                        // Copper
                        output.accept(ModItems.COPPER_HELMET);
                        output.accept(ModItems.COPPER_CHESTPLATE);
                        output.accept(ModItems.COPPER_LEGGINGS);
                        output.accept(ModItems.COPPER_BOOTS);

                        // Steel
                        output.accept(ModItems.STEEL_HELMET);
                        output.accept(ModItems.STEEL_CHESTPLATE);
                        output.accept(ModItems.STEEL_LEGGINGS);
                        output.accept(ModItems.STEEL_BOOTS);

                        // Spinel
                        output.accept(ModItems.SPINEL_HELMET);
                        output.accept(ModItems.SPINEL_CHESTPLATE);
                        output.accept(ModItems.SPINEL_LEGGINGS);
                        output.accept(ModItems.SPINEL_BOOTS);

                        // ======= 🪵 DECORATIVE BLOCKS =======
                        // Shelves
                        output.accept(ModBlocks.OAK_SHELF);
                        output.accept(ModBlocks.BIRCH_SHELF);
                        output.accept(ModBlocks.SPRUCE_SHELF);
                        output.accept(ModBlocks.JUNGLE_SHELF);
                        output.accept(ModBlocks.DARK_OAK_SHELF);
                        output.accept(ModBlocks.BAMBOO_SHELF);
                        output.accept(ModBlocks.ACACIA_SHELF);
                        output.accept(ModBlocks.CHERRY_SHELF);
                        output.accept(ModBlocks.MANGROVE_SHELF);

                        // Storage
                        output.accept(ModBlocks.OAK_STORAGE);
                        output.accept(ModBlocks.BIRCH_STORAGE);
                        output.accept(ModBlocks.SPRUCE_STORAGE);
                        output.accept(ModBlocks.JUNGLE_STORAGE);
                        output.accept(ModBlocks.DARK_OAK_STORAGE);
                        output.accept(ModBlocks.BAMBOO_STORAGE);
                        output.accept(ModBlocks.ACACIA_STORAGE);
                        output.accept(ModBlocks.CHERRY_STORAGE);
                        output.accept(ModBlocks.MANGROVE_STORAGE);

                        // Hooks
                        output.accept(ModBlocks.OAK_HOOK);
                        output.accept(ModBlocks.BIRCH_HOOK);
                        output.accept(ModBlocks.SPRUCE_HOOK);
                        output.accept(ModBlocks.JUNGLE_HOOK);
                        output.accept(ModBlocks.DARK_OAK_HOOK);
                        output.accept(ModBlocks.BAMBOO_HOOK);
                        output.accept(ModBlocks.ACACIA_HOOK);
                        output.accept(ModBlocks.CHERRY_HOOK);
                        output.accept(ModBlocks.MANGROVE_HOOK);

                        // ======= 🪙 STRUCTURAL & INDUSTRIAL BLOCKS =======
                        output.accept(ModBlocks.DEMAECATION_POST);
                        output.accept(ModBlocks.DEMAECATION_POST_B);
                        output.accept(ModBlocks.DEMAECATION_POST_C);
                        output.accept(ModBlocks.DEMAECATION_POST_D);
                        output.accept(ModBlocks.DEMAECATION_POST_E);
                        output.accept(ModBlocks.SPEAKER);
                        output.accept(ModBlocks.WOODCUTTER_BENCH);
                        output.accept(ModBlocks.MINER_BENCH);

                        // Bars
                        output.accept(ModBlocks.STEEL_BARS);
                        output.accept(ModBlocks.COPPER_BARS);
                        output.accept(ModBlocks.EXPOSED_COPPER_BARS);
                        output.accept(ModBlocks.WEATHERED_COPPER_BARS);
                        output.accept(ModBlocks.OXIDIZED_COPPER_BARS);
                        output.accept(ModBlocks.WAXED_COPPER_BARS);
                        output.accept(ModBlocks.WAXED_EXPOSED_COPPER_BARS);
                        output.accept(ModBlocks.WAXED_WEATHERED_COPPER_BARS);
                        output.accept(ModBlocks.WAXED_OXIDIZED_COPPER_BARS);

                        // Chains
                        output.accept(ModBlocks.STEEL_CHAIN);
                        output.accept(ModBlocks.COPPER_CHAIN);
                        output.accept(ModBlocks.EXPOSED_COPPER_CHAIN);
                        output.accept(ModBlocks.WEATHERED_COPPER_CHAIN);
                        output.accept(ModBlocks.OXIDIZED_COPPER_CHAIN);
                        output.accept(ModBlocks.WAXED_COPPER_CHAIN);
                        output.accept(ModBlocks.WAXED_EXPOSED_COPPER_CHAIN);
                        output.accept(ModBlocks.WAXED_WEATHERED_COPPER_CHAIN);
                        output.accept(ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN);


                        // Barrels
                        output.accept(ModBlocks.IRON_BARREL_EMPTY);
                        output.accept(ModBlocks.STEEL_BARREL_EMPTY);
                        output.accept(ModBlocks.COPPER_BARREL);
                        output.accept(ModBlocks.EXPOSED_COPPER_BARREL);
                        output.accept(ModBlocks.WEATHERED_COPPER_BARREL);
                        output.accept(ModBlocks.OXIDIZED_COPPER_BARREL);
                        output.accept(ModBlocks.WAXED_COPPER_BARREL);
                        output.accept(ModBlocks.WAXED_EXPOSED_COPPER_BARREL);
                        output.accept(ModBlocks.WAXED_WEATHERED_COPPER_BARREL);
                        output.accept(ModBlocks.WAXED_OXIDIZED_COPPER_BARREL);


                        output.accept(Blocks.SPAWNER);

                        // ======= 🌍 ISLAND GENERATORS =======
                        output.accept(ModItems.ISLAND_GENERATOR_SMALL);
                        output.accept(ModItems.ISLAND_GENERATOR_MEDIUM);
                        output.accept(ModItems.ISLAND_GENERATOR_LARGE);

                        // ======= 🥘 FOOD & DRINK =======
                        // Ingredients
                        output.accept(ModItems.FLOUR);

                        // Edible ingredients
                        output.accept(ModItems.CHEESE);

                        // Raw Foods
                        output.accept(ModItems.PANCAKE_DOUGH);
                        output.accept(ModItems.DICED_CHICKEN);
                        output.accept(ModItems.BATTERED_CHICKEN);

                        // Prepared foods
                        output.accept(ModItems.CHICKEN_NUGGETS);
                        output.accept(ModItems.FRENCH_FRIES);
                        output.accept(ModItems.PANCAKE);

                        // Burgers
                        output.accept(ModItems.BEEF_BURGER);
                        output.accept(ModItems.CHEESEBURGER);
                        output.accept(ModItems.PORKCHOP_BURGER);

                        // ======= 🥛 DRINKS (MILK, JUICES, SMOOTHIES, ETC.) =======
                        output.accept(ModItems.COFFEE);
                        output.accept(ModItems.MILK_BOTTLE);
                        output.accept(ModItems.TEA);

                        // Juices (alphabetical)
                        output.accept(ModItems.APPLE_JUICE);
                        output.accept(ModItems.AVOCADO_JUICE);
                        output.accept(ModItems.BANANA_JUICE);
                        output.accept(ModItems.BLACKCURRANT_JUICE);
                        output.accept(ModItems.CHERRY_JUICE);
                        output.accept(ModItems.GLOWBERRY_JUICE);
                        output.accept(ModItems.MELON_JUICE);
                        output.accept(ModItems.ORANGE_JUICE);
                        output.accept(ModItems.PEAR_JUICE);
                        output.accept(ModItems.PINEAPPLE_JUICE);
                        output.accept(ModItems.STRAWBERRY_JUICE);
                        output.accept(ModItems.SWEETBERRY_JUICE);
                        output.accept(ModItems.TOMATO_JUICE);

                        // Smoothies (alphabetical)
                        output.accept(ModItems.APPLE_SMOOTHIE);
                        output.accept(ModItems.AVOCADO_SMOOTHIE);
                        output.accept(ModItems.BANANA_SMOOTHIE);
                        output.accept(ModItems.BLACKCURRANT_SMOOTHIE);
                        output.accept(ModItems.CHERRY_SMOOTHIE);
                        output.accept(ModItems.GLOWBERRY_SMOOTHIE);
                        output.accept(ModItems.MELON_SMOOTHIE);
                        output.accept(ModItems.ORANGE_SMOOTHIE);
                        output.accept(ModItems.PEAR_SMOOTHIE);
                        output.accept(ModItems.PINEAPPLE_SMOOTHIE);
                        output.accept(ModItems.STRAWBERRY_SMOOTHIE);
                        output.accept(ModItems.SWEETBERRY_SMOOTHIE);

                        // ======= 🌿 PLANTS =======
                        output.accept(ModItems.COFFEE_BEANS);
                        output.accept(ModItems.CORN);
                        output.accept(ModItems.CUCUMBER);
                        output.accept(ModItems.LETTUCE);
                        output.accept(ModItems.ONION);
                        output.accept(ModItems.RADISH);
                        output.accept(ModItems.SPRING_ONION);
                        output.accept(ModItems.TEA_LEAVES);
                        output.accept(ModItems.TOMATO);

                        // ======= 🍓 FRUITS & BERRIES =======
                        output.accept(ModItems.AVOCADO);
                        output.accept(ModItems.BANANA);
                        output.accept(ModItems.ORANGE);
                        output.accept(ModItems.PEAR);
                        output.accept(ModItems.PINEAPPLE);
                        output.accept(ModItems.BLACKCURRANT);
                        output.accept(ModItems.CHERRY);
                        output.accept(ModItems.STRAWBERRY);

                        // ======= 🌱 SEEDS & SAPLINGS =======
                        output.accept(ModItems.COFFEE_SEEDS);
                        output.accept(ModItems.CORN_SEEDS);
                        output.accept(ModItems.CUCUMBER_SEEDS);
                        output.accept(ModItems.LETTUCE_SEEDS);
                        output.accept(ModItems.ONION_SEEDS);
                        output.accept(ModItems.RADISH_SEEDS);
                        output.accept(ModItems.SPRING_ONION_SEEDS);
                        output.accept(ModItems.TEA_SEEDS);
                        output.accept(ModItems.TOMATO_SEEDS);
                        output.accept(ModItems.BLACKCURRANT_SEEDS);
                        output.accept(ModItems.PINEAPPLE_SEEDS);
                        output.accept(ModItems.STRAWBERRY_SEEDS);

                        // Saplings
                        output.accept(ModBlocks.APPLE_SAPLING);
                        output.accept(ModBlocks.AVOCADO_SAPLING);
                        output.accept(ModBlocks.BANANA_SAPLING);
                        output.accept(ModBlocks.CHERRY_SAPLING);
                        output.accept(ModBlocks.ORANGE_SAPLING);
                        output.accept(ModBlocks.PEAR_SAPLING);

                        // Leaves
                        output.accept(ModBlocks.APPLE_LEAVES);
                        output.accept(ModBlocks.AVOCADO_LEAVES);
                        output.accept(ModBlocks.BANANA_LEAVES);
                        output.accept(ModBlocks.CHERRY_LEAVES);
                        output.accept(ModBlocks.ORANGE_LEAVES);
                        output.accept(ModBlocks.PEAR_LEAVES);

                        // Extra plants
                        output.accept(ModItems.YUCCA_LEAVES);
                        output.accept(ModBlocks.YUCCA_PLANT_BLOCK);
                        output.accept(ModItems.FLAX_FLOWER);
                        output.accept(ModBlocks.LEAF_LITTER);

                        // ======= ⚰️ GRAVESTONES =======
                        output.accept(ModBlocks.BLACKSTONE_GRAVESTONE_A);
                        output.accept(ModBlocks.BLACKSTONE_GRAVESTONE_B);
                        output.accept(ModBlocks.COBBLESTONE_GRAVESTONE_A);
                        output.accept(ModBlocks.COBBLESTONE_GRAVESTONE_B);
                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);


    }
}
