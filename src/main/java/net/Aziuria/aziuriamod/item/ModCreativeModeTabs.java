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
                        output.accept(ModItems.SACK);
                        output.accept(ModItems.STEEL_ALLOY_MESH);
                        output.accept(ModItems.STEEL_INGOT);
                        output.accept(ModItems.POTASSIUM);
                        output.accept(ModItems.SULPHUR);
                        output.accept(ModBlocks.POTASSIUM_ORE);
                        output.accept(ModBlocks.SULPHUR_ORE);
                        output.accept(ModBlocks.DEEPSLATE_POTASSIUM_ORE);
                        output.accept(ModBlocks.DEEPSLATE_SULPHUR_ORE);
                        output.accept(ModBlocks.STEEL_BLOCK);
                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);


    }
}
