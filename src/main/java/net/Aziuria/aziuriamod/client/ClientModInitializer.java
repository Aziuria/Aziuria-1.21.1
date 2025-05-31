package net.Aziuria.aziuriamod.client;

import net.Aziuria.aziuriamod.block.ModBlocks;
import net.Aziuria.aziuriamod.block.entity.ModBlockEntities;
import net.Aziuria.aziuriamod.block.entity.renderer.ShelfRenderer;
import net.Aziuria.aziuriamod.block.entity.renderer.SteelBarrelRenderer;
import net.Aziuria.aziuriamod.block.entity.renderer.StorageRenderer;
import net.Aziuria.aziuriamod.fog.FogRendererHook;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.common.NeoForge;


public class ClientModInitializer {

    public static void register(IEventBus eventBus) {
        eventBus.addListener(ClientModInitializer::onClientSetup);
        ModClientEvents.register(eventBus);

    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            // Register the shelf block entity renderer
            BlockEntityRenderers.register(ModBlockEntities.SHELF_BLOCK_ENTITY.get(), ShelfRenderer::new);
            BlockEntityRenderers.register(ModBlockEntities.STORAGE_BLOCK_ENTITY.get(), StorageRenderer::new);
            BlockEntityRenderers.register(ModBlockEntities.STEEL_BARREL_BLOCK_ENTITY.get(), SteelBarrelRenderer::new);

            // Set render layer (optional, depends if shelf is transparent, solid is fine for wood)
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.OAK_SHELF.get(), RenderType.solid());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.UNBREAKABLE_GLASS.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.STEEL_BARREL_EMPTY.get(), RenderType.translucent());

            NeoForge.EVENT_BUS.register(new FogRendererHook());


        });
    }

}