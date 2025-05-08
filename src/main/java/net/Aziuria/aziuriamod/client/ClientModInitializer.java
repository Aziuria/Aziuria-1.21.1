package net.Aziuria.aziuriamod.client;

import com.mojang.blaze3d.platform.ScreenManager;
import net.Aziuria.aziuriamod.block.ModBlocks;
import net.Aziuria.aziuriamod.block.entity.ModBlockEntities;
import net.Aziuria.aziuriamod.block.entity.renderer.ShelfRenderer;
import net.Aziuria.aziuriamod.block.entity.renderer.StorageRenderer;
import net.Aziuria.aziuriamod.client.screen.ModMenus;
import net.Aziuria.aziuriamod.client.screen.custom.SackScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;


public class ClientModInitializer {

    public static void register(IEventBus eventBus) {
        eventBus.addListener(ClientModInitializer::onClientSetup);
    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            // Register the shelf block entity renderer
            BlockEntityRenderers.register(ModBlockEntities.SHELF_BLOCK_ENTITY.get(), ShelfRenderer::new);
            BlockEntityRenderers.register(ModBlockEntities.STORAGE_BLOCK_ENTITY.get(), StorageRenderer::new);

            // Set render layer (optional, depends if shelf is transparent, solid is fine for wood)
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.OAK_SHELF.get(), RenderType.solid());

            MenuScreens.register(ModMenus.SACK_MENU.get(), SackScreen::new);

        });
    }

}