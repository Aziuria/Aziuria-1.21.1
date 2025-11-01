package net.Aziuria.aziuriamod.client;

import net.Aziuria.aziuriamod.block.ModBlocks;
import net.Aziuria.aziuriamod.block.entity.ModBlockEntities;
import net.Aziuria.aziuriamod.block.entity.renderer.*;
import net.Aziuria.aziuriamod.fog.FogRendererHook;
import net.minecraft.client.Minecraft;
import net.Aziuria.aziuriamod.island.renderer.IslandThrowableRenderer;
import net.Aziuria.aziuriamod.item.ModItems;
import net.Aziuria.aziuriamod.item.custom.entities.ModEntities;
import net.Aziuria.aziuriamod.item.renderer.SpectralDustRenderer;
import net.Aziuria.aziuriamod.util.RenderLayerUtil;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
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
            BlockEntityRenderers.register(ModBlockEntities.HOOK_BLOCK_ENTITY.get(), HookRenderer::new);
            BlockEntityRenderers.register(ModBlockEntities.STEEL_BARREL_BLOCK_ENTITY.get(), SteelBarrelRenderer::new);
            BlockEntityRenderers.register(ModBlockEntities.WOODCUTTER_BENCH.get(), WoodcutterBenchRenderer::new);
            BlockEntityRenderers.register(ModBlockEntities.MINER_BENCH.get(), MinerBenchRenderer::new);
            RenderLayerUtil.setRenderLayer(ModBlocks.COPPER_BARS.get(), RenderType.cutout());
            RenderLayerUtil.setRenderLayer(ModBlocks.EXPOSED_COPPER_BARS.get(), RenderType.cutout());
            RenderLayerUtil.setRenderLayer(ModBlocks.WEATHERED_COPPER_BARS.get(), RenderType.cutout());
            RenderLayerUtil.setRenderLayer(ModBlocks.OXIDIZED_COPPER_BARS.get(), RenderType.cutout());
            RenderLayerUtil.setRenderLayer(ModBlocks.WAXED_COPPER_BARS.get(), RenderType.cutout());
            RenderLayerUtil.setRenderLayer(ModBlocks.WAXED_EXPOSED_COPPER_BARS.get(), RenderType.cutout());
            RenderLayerUtil.setRenderLayer(ModBlocks.WAXED_WEATHERED_COPPER_BARS.get(), RenderType.cutout());
            RenderLayerUtil.setRenderLayer(ModBlocks.WAXED_OXIDIZED_COPPER_BARS.get(), RenderType.cutout());
            RenderLayerUtil.setRenderLayer(ModBlocks.STEEL_CHAIN.get(), RenderType.cutout());
            RenderLayerUtil.setRenderLayer(ModBlocks.COPPER_CHAIN.get(), RenderType.cutout());
            RenderLayerUtil.setRenderLayer(ModBlocks.EXPOSED_COPPER_CHAIN.get(), RenderType.cutout());
            RenderLayerUtil.setRenderLayer(ModBlocks.WEATHERED_COPPER_CHAIN.get(), RenderType.cutout());
            RenderLayerUtil.setRenderLayer(ModBlocks.OXIDIZED_COPPER_CHAIN.get(), RenderType.cutout());
            RenderLayerUtil.setRenderLayer(ModBlocks.WAXED_COPPER_CHAIN.get(), RenderType.cutout());
            RenderLayerUtil.setRenderLayer(ModBlocks.WAXED_EXPOSED_COPPER_CHAIN.get(), RenderType.cutout());
            RenderLayerUtil.setRenderLayer(ModBlocks.WAXED_WEATHERED_COPPER_CHAIN.get(), RenderType.cutout());
            RenderLayerUtil.setRenderLayer(ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN.get(), RenderType.cutout());


            // instead of SetRenderLayer, use RenderLayerUtil, which is wrapped to get rid of annoying warnings of depreciation's

            RenderLayerUtil.setRenderLayer(ModBlocks.OAK_SHELF.get(), RenderType.solid());
            RenderLayerUtil.setRenderLayer(ModBlocks.UNBREAKABLE_GLASS.get(), RenderType.translucent());
            RenderLayerUtil.setRenderLayer(ModBlocks.STEEL_BARREL_EMPTY.get(), RenderType.translucent());
            RenderLayerUtil.setRenderLayer(ModBlocks.COPPER_BARREL.get(), RenderType.translucent());
            RenderLayerUtil.setRenderLayer(ModBlocks.EXPOSED_COPPER_BARREL.get(), RenderType.translucent());
            RenderLayerUtil.setRenderLayer(ModBlocks.WEATHERED_COPPER_BARREL.get(), RenderType.translucent());
            RenderLayerUtil.setRenderLayer(ModBlocks.OXIDIZED_COPPER_BARREL.get(), RenderType.translucent());
            RenderLayerUtil.setRenderLayer(ModBlocks.WAXED_COPPER_BARREL.get(), RenderType.translucent());
            RenderLayerUtil.setRenderLayer(ModBlocks.WAXED_EXPOSED_COPPER_BARREL.get(), RenderType.translucent());
            RenderLayerUtil.setRenderLayer(ModBlocks.WAXED_WEATHERED_COPPER_BARREL.get(), RenderType.translucent());
            RenderLayerUtil.setRenderLayer(ModBlocks.WAXED_OXIDIZED_COPPER_BARREL.get(), RenderType.translucent());
            RenderLayerUtil.setRenderLayer(ModBlocks.STEEL_BARS.get(), RenderType.cutout());
            RenderLayerUtil.setRenderLayer(ModBlocks.FLAX_FLOWER_BLOCK.get(), RenderType.cutout());

            EntityRenderers.register(ModEntities.ISLAND_THROWABLE.get(), IslandThrowableRenderer::new);


        });

    }

    // 🔥 Here’s the part that registers your Spectral Dust item renderer
    @SubscribeEvent
    public static void onRegisterClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new SpectralDustRenderer(
                        Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                        Minecraft.getInstance().getEntityModels()
                );
            }
        }, ModItems.SPECTRAL_DUST.get());

        // Spectral Substance
        event.registerItem(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new SpectralDustRenderer(
                        Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                        Minecraft.getInstance().getEntityModels()
                );
            }
        }, ModItems.SPECTRAL_SUBSTANCE.get());
    }

}