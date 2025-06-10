package net.Aziuria.aziuriamod.item.custom.entities;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;


public class IslandThrowableRenderer extends ThrownItemRenderer<IslandThrowableEntity> {
    public IslandThrowableRenderer(EntityRendererProvider.Context context) {
        super(context, 1.0F, true);
    }
}