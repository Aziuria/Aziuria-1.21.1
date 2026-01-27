package net.Aziuria.aziuriamod.tips.network;

import net.Aziuria.aziuriamod.tips.ModTips;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.ClientPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ShowPebbleTipsPacket implements CustomPacketPayload {

    public static final Type<ShowPebbleTipsPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath("aziuriamod", "show_pebble_tips"));

    // ===== EMPTY PACKET =====
    public ShowPebbleTipsPacket() {}

    // Decoder
    public ShowPebbleTipsPacket(RegistryFriendlyByteBuf buf) {
        // nothing to read
    }

    public ShowPebbleTipsPacket(FriendlyByteBuf buf) {
        this((RegistryFriendlyByteBuf) buf);
    }

    // Encoder
    public void write(RegistryFriendlyByteBuf buf) {
        // nothing to write
    }

    public void write(FriendlyByteBuf buf) {
        write((RegistryFriendlyByteBuf) buf);
    }

    @Override
    public Type<ShowPebbleTipsPacket> type() {
        return TYPE;
    }

    // ===== STREAM CODEC =====
    private static void encode(RegistryFriendlyByteBuf buf, ShowPebbleTipsPacket packet) {
        packet.write(buf);
    }

    public static final StreamCodec<RegistryFriendlyByteBuf, ShowPebbleTipsPacket> CODEC =
            StreamCodec.of(ShowPebbleTipsPacket::encode, ShowPebbleTipsPacket::new);

    // ===== HANDLER =====
    public static void handle(ShowPebbleTipsPacket packet, IPayloadContext ctx) {
        if (ctx instanceof ClientPayloadContext clientCtx) {
            clientCtx.enqueueWork(() -> {
                ModTips.showPebbleMiningTip();
                ModTips.showPebbleCombatTip();
            });
        }
    }
}