package net.Aziuria.aziuriamod.exhaustion.network;

import net.Aziuria.aziuriamod.exhaustion.capability.ExhaustionProvider;
import net.Aziuria.aziuriamod.exhaustion.capability.Iexhaustion;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.ClientPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ExhaustionSyncPacket implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<ExhaustionSyncPacket> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("aziuriamod", "exhaustion_sync"));

    private final float exhaustion;

    // Decoder constructor
    public ExhaustionSyncPacket(RegistryFriendlyByteBuf buf) {
        this.exhaustion = buf.readFloat();
    }

    // Convenience constructor
    public ExhaustionSyncPacket(FriendlyByteBuf buf) {
        this((RegistryFriendlyByteBuf) buf);
    }

    // Constructor for sending
    public ExhaustionSyncPacket(float exhaustion) {
        this.exhaustion = exhaustion;
    }

    public float getExhaustion() {
        return exhaustion;
    }

    public void write(FriendlyByteBuf buf) {
        write((RegistryFriendlyByteBuf) buf);
    }

    public void write(RegistryFriendlyByteBuf buf) {
        buf.writeFloat(exhaustion);
    }

    @Override
    public CustomPacketPayload.Type<ExhaustionSyncPacket> type() {
        return TYPE;
    }

    // Encoder
    private static void encode(RegistryFriendlyByteBuf buf, ExhaustionSyncPacket packet) {
        packet.write(buf);
    }

    // Codec
    public static final StreamCodec<RegistryFriendlyByteBuf, ExhaustionSyncPacket> CODEC =
            StreamCodec.of(ExhaustionSyncPacket::encode, ExhaustionSyncPacket::new);

    // Packet handler
    public static void handle(ExhaustionSyncPacket packet, IPayloadContext ctx) {
        if (ctx instanceof ClientPayloadContext clientCtx) {
            clientCtx.enqueueWork(() -> {
                var mc = net.minecraft.client.Minecraft.getInstance();
                var player = mc.player;
                if (player != null) {
                    Iexhaustion cap = player.getCapability(ExhaustionProvider.EXHAUSTION_CAP, null);
                    if (cap != null) {
                        cap.setExhaustion(packet.exhaustion);
                    }
                }
            });
        }
    }
}