package net.Aziuria.aziuriamod.client;

import net.Aziuria.aziuriamod.block.ModBlocks;
import net.Aziuria.aziuriamod.block.entity.ModBlockEntities;
import net.Aziuria.aziuriamod.block.entity.renderer.ShelfRenderer;
import net.Aziuria.aziuriamod.block.entity.renderer.StorageRenderer;
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
        });
    }

}