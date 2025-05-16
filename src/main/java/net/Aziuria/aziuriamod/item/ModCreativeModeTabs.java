package net.Aziuria.aziuriamod.item;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.Aziuria.aziuriamod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
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
                        output.accept(ModItems.STEEL_ALLOY_MESH);
                        output.accept(ModItems.STEEL_INGOT);
                        output.accept(ModItems.POTASSIUM);
                        output.accept(ModItems.SULPHUR);
                        output.accept(ModItems.STEEL_PICKAXE);
                        output.accept(ModItems.STEEL_AXE);
                        output.accept(ModItems.STEEL_SWORD);
                        output.accept(ModItems.STEEL_SHOVEL);
                        output.accept(ModItems.STEEL_HOE);
                        output.accept(ModItems.STEEL_HELMET);
                        output.accept(ModItems.STEEL_BOOTS);
                        output.accept(ModItems.STEEL_LEGGINGS);
                        output.accept(ModItems.STEEL_CHESTPLATE);
                        output.accept(ModBlocks.POTASSIUM_ORE);
                        output.accept(ModBlocks.SULPHUR_ORE);
                        output.accept(ModBlocks.DEEPSLATE_POTASSIUM_ORE);
                        output.accept(ModBlocks.DEEPSLATE_SULPHUR_ORE);
                        output.accept(ModBlocks.STEEL_BLOCK);
                        output.accept(ModBlocks.UNBREAKABLE_GLASS);
                        output.accept(ModBlocks.OAK_SHELF);
                        output.accept(ModBlocks.BIRCH_SHELF);
                        output.accept(ModBlocks.SPRUCE_SHELF);
                        output.accept(ModBlocks.JUNGLE_SHELF);
                        output.accept(ModBlocks.DARK_OAK_SHELF);
                        output.accept(ModBlocks.BAMBOO_SHELF);
                        output.accept(ModBlocks.ACACIA_SHELF);
                        output.accept(ModBlocks.CHERRY_SHELF);
                        output.accept(ModBlocks.MANGROVE_SHELF);
                        output.accept(ModBlocks.ACACIA_STORAGE);
                        output.accept(ModBlocks.BAMBOO_STORAGE);
                        output.accept(ModBlocks.BIRCH_STORAGE);
                        output.accept(ModBlocks.CHERRY_STORAGE);
                        output.accept(ModBlocks.DARK_OAK_STORAGE);
                        output.accept(ModBlocks.JUNGLE_STORAGE);
                        output.accept(ModBlocks.MANGROVE_STORAGE);
                        output.accept(ModBlocks.OAK_STORAGE);
                        output.accept(ModBlocks.SPRUCE_STORAGE);;
                        output.accept(ModBlocks.STEEL_BARREL_EMPTY);;
                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);


    }
}
