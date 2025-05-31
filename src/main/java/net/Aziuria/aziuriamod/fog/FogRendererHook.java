package net.Aziuria.aziuriamod.fog;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ViewportEvent;

public class FogRendererHook {
    @SubscribeEvent
    public void onRenderFog(ViewportEvent.RenderFog event) {
        FogType fog = FogEventManager.getActiveFog();
        if (fog == null) return;

        event.setNearPlaneDistance(fog.getFogStart());
        event.setFarPlaneDistance(FogEventManager.getFogEndDistance());
        event.setCanceled(true);
    }

    @SubscribeEvent
    public void onFogColor(ViewportEvent.ComputeFogColor event) {
        FogType fog = FogEventManager.getActiveFog();
        if (fog == null) return;

        event.setRed((float) fog.getFogColor().x);
        event.setGreen((float) fog.getFogColor().y);
        event.setBlue((float) fog.getFogColor().z);
    }
}