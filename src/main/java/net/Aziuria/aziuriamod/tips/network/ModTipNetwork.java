package net.Aziuria.aziuriamod.tips.network;

import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

public class ModTipNetwork {

    public static void register(final RegisterPayloadHandlersEvent event) {
        var registrar = event.registrar("aziuriamod").optional();

        registrar.playToClient(
                ShowPebbleTipsPacket.TYPE,
                ShowPebbleTipsPacket.CODEC,
                ShowPebbleTipsPacket::handle
        );
    }
}