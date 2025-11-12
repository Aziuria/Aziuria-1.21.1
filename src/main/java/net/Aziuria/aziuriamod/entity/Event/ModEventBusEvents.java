package net.Aziuria.aziuriamod.entity.Event;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.Aziuria.aziuriamod.entity.ModEntity;
import net.Aziuria.aziuriamod.entity.client.worm.WormModel;
import net.Aziuria.aziuriamod.entity.custom.WormEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = AziuriaMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(WormModel.LAYER_LOCATION, WormModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntity.WORM.get(), WormEntity.createAttributes().build());
    }
}