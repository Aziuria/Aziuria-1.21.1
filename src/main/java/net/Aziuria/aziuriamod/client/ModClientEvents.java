package net.Aziuria.aziuriamod.client;

import net.Aziuria.aziuriamod.client.screen.ModMenus;
import net.Aziuria.aziuriamod.client.screen.custom.SackScreen;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.VillagerRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.Villager;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.IEventBus;

import java.lang.reflect.Field;
import java.util.List;

public class ModClientEvents {

    public static void register(IEventBus eventBus) {
        eventBus.register(ModClientEvents.class);
    }

    @SubscribeEvent
    public static void registerMenuScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenus.SACK_MENU.get(), SackScreen::new);
    }


    @SubscribeEvent
    public static void onAddLayers(EntityRenderersEvent.AddLayers event) {
        EntityRenderer<? extends Villager> renderer = event.getRenderer(EntityType.VILLAGER);

        if (renderer instanceof VillagerRenderer villagerRenderer) {
            try {
                Field layersField = LivingEntityRenderer.class.getDeclaredField("layers");
                layersField.setAccessible(true);

                List<RenderLayer<?, ?>> layers = (List<RenderLayer<?, ?>>) layersField.get(villagerRenderer);

                RenderLayer<Villager, VillagerModel<Villager>> vanillaProfessionLayer = null;

                // Find vanilla profession layer
                for (RenderLayer<?, ?> layer : layers) {
                    if (layer.getClass().getSimpleName().equals("VillagerProfessionLayer")) {
                        vanillaProfessionLayer = (RenderLayer<Villager, VillagerModel<Villager>>) layer;
                        break;
                    }
                }

                if (vanillaProfessionLayer != null) {
                    layers.remove(vanillaProfessionLayer);
                    villagerRenderer.addLayer(new CustomProfessionLayer(villagerRenderer, vanillaProfessionLayer));
                }
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }
        }
    }
}