package net.Aziuria.aziuriamod.island.renderer;

import net.Aziuria.aziuriamod.island.entity.IslandThrowableEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;


public class IslandThrowableRenderer extends ThrownItemRenderer<IslandThrowableEntity> {
    public IslandThrowableRenderer(EntityRendererProvider.Context context) {
        super(context, 1.0F, true);
    }
}