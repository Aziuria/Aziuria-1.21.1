package net.Aziuria.aziuriamod.client;

import net.Aziuria.aziuriamod.fog.FogEventManager;
import net.minecraft.client.Minecraft;
import net.neoforged.neoforge.client.event.ClientTickEvent.Post;
import net.neoforged.bus.api.SubscribeEvent;

public class ModClientCommonBusEvents {

    @SubscribeEvent
    public static void onClientTick(Post event) {
        if (Minecraft.getInstance().level != null) {
            FogEventManager.tick(Minecraft.getInstance().level); // ✅ pass level here
        }
    }
}