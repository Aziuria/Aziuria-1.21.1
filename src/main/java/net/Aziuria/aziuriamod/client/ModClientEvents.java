package net.Aziuria.aziuriamod.client;

import net.Aziuria.aziuriamod.client.screen.ModMenus;
import net.Aziuria.aziuriamod.client.screen.custom.SackScreen;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.IEventBus;

public class ModClientEvents {

    public static void register(IEventBus eventBus) {
        eventBus.register(ModClientEvents.class);
    }

    @SubscribeEvent
    public static void registerMenuScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenus.SACK_MENU.get(), SackScreen::new);
    }


}