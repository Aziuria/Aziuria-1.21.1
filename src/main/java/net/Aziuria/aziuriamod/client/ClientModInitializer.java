package net.Aziuria.aziuriamod.client;

import net.Aziuria.aziuriamod.block.ModBlocks;
import net.Aziuria.aziuriamod.block.entity.ModBlockEntities; // Correct import
import net.Aziuria.aziuriamod.client.renderer.ShelfRenderer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientModInitializer {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.OAK_SHELF.get(), RenderType.solid());

            // Register block entity renderers
            registerBlockEntityRenderers();
        });
    }

    public static void registerBlockEntityRenderers() {
        BlockEntityRenderers.register(ModBlockEntities.SHELF_BLOCK_ENTITY.get(), ShelfRenderer::new);
    }
}