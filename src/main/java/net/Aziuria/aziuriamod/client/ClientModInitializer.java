package net.Aziuria.aziuriamod.client;

import net.Aziuria.aziuriamod.block.ModBlocks;
import net.Aziuria.aziuriamod.block.entity.ModBlockEntities;
import net.Aziuria.aziuriamod.block.entity.renderer.*;
import net.Aziuria.aziuriamod.fog.FogRendererHook;

import net.Aziuria.aziuriamod.item.custom.entities.IslandThrowableRenderer;
import net.Aziuria.aziuriamod.item.custom.entities.ModEntities;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.common.NeoForge;

public class ClientModInitializer {

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(ClientModInitializer::onClientSetup);

        NeoForge.EVENT_BUS.register(new FogRendererHook());
        ModClientEvents.register(modEventBus);
    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            BlockEntityRenderers.register(ModBlockEntities.SHELF_BLOCK_ENTITY.get(), ShelfRenderer::new);
            BlockEntityRenderers.register(ModBlockEntities.STORAGE_BLOCK_ENTITY.get(), StorageRenderer::new);
            BlockEntityRenderers.register(ModBlockEntities.STEEL_BARREL_BLOCK_ENTITY.get(), SteelBarrelRenderer::new);
            BlockEntityRenderers.register(ModBlockEntities.WOODCUTTER_BENCH.get(), WoodcutterBenchRenderer::new);
            BlockEntityRenderers.register(ModBlockEntities.MINER_BENCH.get(), MinerBenchRenderer::new);

            ItemBlockRenderTypes.setRenderLayer(ModBlocks.OAK_SHELF.get(), RenderType.solid());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.UNBREAKABLE_GLASS.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.STEEL_BARREL_EMPTY.get(), RenderType.translucent());

            ItemBlockRenderTypes.setRenderLayer(ModBlocks.STEEL_BARS.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.FLAX_FLOWER_BLOCK.get(), RenderType.cutout());

            EntityRenderers.register(ModEntities.ISLAND_THROWABLE.get(), IslandThrowableRenderer::new);

        });

    }
}