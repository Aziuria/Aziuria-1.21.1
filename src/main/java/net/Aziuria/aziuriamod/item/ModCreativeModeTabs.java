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

                        output.accept(ModItems.SACK_ITEM.get());
                        output.accept(ModItems.LASHING);
                        output.accept(ModItems.STEEL_ALLOY_MESH);
                        output.accept(ModItems.STEEL_INGOT);
                        output.accept(ModItems.POTASSIUM);
                        output.accept(ModItems.SULPHUR);
                        output.accept(ModItems.SPINEL);
                        output.accept(ModBlocks.POTASSIUM_ORE);
                        output.accept(ModBlocks.SULPHUR_ORE);
                        output.accept(ModBlocks.SPINEL_ORE);
                        output.accept(ModBlocks.SPECTRAL_ORE);
                        output.accept(ModBlocks.DEEPSLATE_POTASSIUM_ORE);
                        output.accept(ModBlocks.DEEPSLATE_SULPHUR_ORE);
                        output.accept(ModBlocks.DEEPSLATE_SPINEL_ORE);
                        output.accept(ModBlocks.DEEPSLATE_SPECTRAL_ORE);
                        output.accept(ModBlocks.STEEL_BLOCK);
                        output.accept(ModBlocks.UNBREAKABLE_GLASS);
                        output.accept(ModItems.KNIFE);
                        output.accept(ModItems.COPPER_PICKAXE);
                        output.accept(ModItems.COPPER_AXE);
                        output.accept(ModItems.COPPER_SWORD);
                        output.accept(ModItems.COPPER_SHOVEL);
                        output.accept(ModItems.COPPER_HOE);
                        output.accept(ModItems.STEEL_PICKAXE);
                        output.accept(ModItems.STEEL_AXE);
                        output.accept(ModItems.STEEL_SWORD);
                        output.accept(ModItems.STEEL_SHOVEL);
                        output.accept(ModItems.STEEL_HOE);
                        output.accept(ModItems.SPINEL_PICKAXE);
                        output.accept(ModItems.SPINEL_AXE);
                        output.accept(ModItems.SPINEL_SWORD);
                        output.accept(ModItems.SPINEL_SHOVEL);
                        output.accept(ModItems.SPINEL_HOE);
                        output.accept(ModItems.COPPER_HELMET);
                        output.accept(ModItems.COPPER_BOOTS);
                        output.accept(ModItems.COPPER_LEGGINGS);
                        output.accept(ModItems.COPPER_CHESTPLATE);
                        output.accept(ModItems.STEEL_HELMET);
                        output.accept(ModItems.STEEL_BOOTS);
                        output.accept(ModItems.STEEL_LEGGINGS);
                        output.accept(ModItems.STEEL_CHESTPLATE);
                        output.accept(ModItems.SPINEL_HELMET);
                        output.accept(ModItems.SPINEL_BOOTS);
                        output.accept(ModItems.SPINEL_LEGGINGS);
                        output.accept(ModItems.SPINEL_CHESTPLATE);
                        output.accept(ModBlocks.OAK_SHELF);
                        output.accept(ModBlocks.BIRCH_SHELF);
                        output.accept(ModBlocks.SPRUCE_SHELF);
                        output.accept(ModBlocks.JUNGLE_SHELF);
                        output.accept(ModBlocks.DARK_OAK_SHELF);
                        output.accept(ModBlocks.BAMBOO_SHELF);
                        output.accept(ModBlocks.ACACIA_SHELF);
                        output.accept(ModBlocks.CHERRY_SHELF);
                        output.accept(ModBlocks.MANGROVE_SHELF);
                        output.accept(ModBlocks.OAK_STORAGE);
                        output.accept(ModBlocks.BIRCH_STORAGE);
                        output.accept(ModBlocks.SPRUCE_STORAGE);
                        output.accept(ModBlocks.JUNGLE_STORAGE);
                        output.accept(ModBlocks.DARK_OAK_STORAGE);
                        output.accept(ModBlocks.BAMBOO_STORAGE);
                        output.accept(ModBlocks.ACACIA_STORAGE);
                        output.accept(ModBlocks.CHERRY_STORAGE);
                        output.accept(ModBlocks.MANGROVE_STORAGE);
                        output.accept(ModBlocks.OAK_HOOK);
                        output.accept(ModBlocks.BIRCH_HOOK);
                        output.accept(ModBlocks.SPRUCE_HOOK);
                        output.accept(ModBlocks.JUNGLE_HOOK);
                        output.accept(ModBlocks.DARK_OAK_HOOK);
                        output.accept(ModBlocks.BAMBOO_HOOK);
                        output.accept(ModBlocks.ACACIA_HOOK);
                        output.accept(ModBlocks.CHERRY_HOOK);
                        output.accept(ModBlocks.MANGROVE_HOOK);
                        output.accept(ModBlocks.DEMAECATION_POST);
                        output.accept(ModBlocks.DEMAECATION_POST_B);
                        output.accept(ModBlocks.DEMAECATION_POST_C);
                        output.accept(ModBlocks.DEMAECATION_POST_D);
                        output.accept(ModBlocks.DEMAECATION_POST_E);
                        output.accept(ModBlocks.SPEAKER);
                        output.accept(ModBlocks.WOODCUTTER_BENCH);
                        output.accept(ModBlocks.MINER_BENCH);
                        output.accept(ModBlocks.STEEL_BARS);
                        output.accept(ModBlocks.STEEL_BARREL_EMPTY);
                        output.accept(ModBlocks.IRON_BARREL_EMPTY);
                        output.accept(Blocks.SPAWNER);
                        output.accept(ModItems.ISLAND_GENERATOR_SMALL);
                        output.accept(ModItems.ISLAND_GENERATOR_MEDIUM);
                        output.accept(ModItems.ISLAND_GENERATOR_LARGE);
                        output.accept(ModItems.FLOUR);
                        output.accept(ModItems.CHEESE);
                        output.accept(ModItems.BATTERED_CHICKEN);
                        output.accept(ModItems.DICED_CHICKEN);
                        output.accept(ModItems.CHICKEN_NUGGETS);
                        output.accept(ModItems.FRENCH_FRIES);
                        output.accept(ModItems.BEEF_BURGER);
                        output.accept(ModItems.CHEESEBURGER);
                        output.accept(ModItems.PORKCHOP_BURGER);
                        output.accept(ModItems.PANCAKE_DOUGH);
                        output.accept(ModItems.PANCAKE);
                        output.accept(ModItems.MILK_BOTTLE);
                        output.accept(ModItems.APPLE_JUICE);
                        output.accept(ModItems.PINEAPPLE_JUICE);
                        output.accept(ModItems.PEAR_JUICE);
                        output.accept(ModItems.CHERRY_JUICE);
                        output.accept(ModItems.AVOCADO_JUICE);
                        output.accept(ModItems.BLACKCURRANT_JUICE);
                        output.accept(ModItems.SWEETBERRY_JUICE);
                        output.accept(ModItems.GLOWBERRY_JUICE);
                        output.accept(ModItems.STRAWBERRY_JUICE);
                        output.accept(ModItems.TOMATO_JUICE);
                        output.accept(ModItems.COFFEE);
                        output.accept(ModItems.TEA);
                        output.accept(ModItems.RADISH);
                        output.accept(ModItems.CUCUMBER);
                        output.accept(ModItems.TOMATO);
                        output.accept(ModItems.LETTUCE);
                        output.accept(ModItems.ONION);
                        output.accept(ModItems.SPRING_ONION);
                        output.accept(ModItems.CORN);
                        output.accept(ModItems.PINEAPPLE);
                        output.accept(ModItems.PEAR);
                        output.accept(ModItems.CHERRY);
                        output.accept(ModItems.AVOCADO);
                        output.accept(ModItems.BLACKCURRANT);
                        output.accept(ModItems.STRAWBERRY);
                        output.accept(ModItems.COFFEE_BEANS);
                        output.accept(ModItems.TEA_LEAVES);
                        output.accept(ModItems.RADISH_SEEDS);
                        output.accept(ModItems.TOMATO_SEEDS);
                        output.accept(ModItems.CUCUMBER_SEEDS);
                        output.accept(ModItems.LETTUCE_SEEDS);
                        output.accept(ModItems.PINEAPPLE_SEEDS);
                        output.accept(ModItems.CORN_SEEDS);
                        output.accept(ModItems.ONION_SEEDS);
                        output.accept(ModItems.SPRING_ONION_SEEDS);
                        output.accept(ModItems.BLACKCURRANT_SEEDS);
                        output.accept(ModItems.STRAWBERRY_SEEDS);
                        output.accept(ModItems.COFFEE_SEEDS);
                        output.accept(ModItems.TEA_SEEDS);
                        output.accept(ModBlocks.APPLE_SAPLING);
                        output.accept(ModBlocks.PEAR_SAPLING);
                        output.accept(ModBlocks.CHERRY_SAPLING);
                        output.accept(ModBlocks.AVOCADO_SAPLING);
                        output.accept(ModBlocks.APPLE_LEAVES);
                        output.accept(ModBlocks.PEAR_LEAVES);
                        output.accept(ModBlocks.CHERRY_LEAVES);
                        output.accept(ModBlocks.AVOCADO_LEAVES);
                        output.accept(ModItems.YUCCA_LEAVES);
                        output.accept(ModBlocks.YUCCA_PLANT_BLOCK);
                        output.accept(ModItems.FLAX_FLOWER);
                        output.accept(ModBlocks.LEAF_LITTER);
                        output.accept(ModBlocks.BLACKSTONE_GRAVESTONE_A);
                        output.accept(ModBlocks.BLACKSTONE_GRAVESTONE_B);
                        output.accept(ModBlocks.COBBLESTONE_GRAVESTONE_A);
                        output.accept(ModBlocks.COBBLESTONE_GRAVESTONE_B);
                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);


    }
}
