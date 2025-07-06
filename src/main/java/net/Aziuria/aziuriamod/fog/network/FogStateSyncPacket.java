package net.Aziuria.aziuriamod.fog.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.ClientPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class FogStateSyncPacket implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<FogStateSyncPacket> TYPE =
            new CustomPacketPayload.Type<>(new Identifier("aziuriamod", "fog_state_sync").toResourceLocation());

    private final String activeFogId;
    private final int currentIntensityOrdinal;
    private final long fogStart, fogEnd, fogFadeInEnd, fogFadeOutStart;

    // Constructor used by codec to decode packet from RegistryFriendlyByteBuf
    public FogStateSyncPacket(RegistryFriendlyByteBuf buf) {
        this.activeFogId = buf.readUtf();
        this.currentIntensityOrdinal = buf.readInt();
        this.fogStart = buf.readLong();
        this.fogEnd = buf.readLong();
        this.fogFadeInEnd = buf.readLong();
        this.fogFadeOutStart = buf.readLong();
    }

    // Convenience constructor for FriendlyByteBuf
    public FogStateSyncPacket(FriendlyByteBuf buf) {
        this((RegistryFriendlyByteBuf) buf);
    }

    // Constructor to create packet to send
    public FogStateSyncPacket(String id, int intensity, long start, long end, long fadeIn, long fadeOut) {
        this.activeFogId = id;
        this.currentIntensityOrdinal = intensity;
        this.fogStart = start;
        this.fogEnd = end;
        this.fogFadeInEnd = fadeIn;
        this.fogFadeOutStart = fadeOut;
    }

    // Write method (DO NOT use @Override since superclass has no write method)
    public void write(FriendlyByteBuf buf) {
        write((RegistryFriendlyByteBuf) buf);
    }

    // Actual write implementation
    public void write(RegistryFriendlyByteBuf buf) {
        buf.writeUtf(activeFogId);
        buf.writeInt(currentIntensityOrdinal);
        buf.writeLong(fogStart);
        buf.writeLong(fogEnd);
        buf.writeLong(fogFadeInEnd);
        buf.writeLong(fogFadeOutStart);
    }

    @Override
    public CustomPacketPayload.Type<FogStateSyncPacket> type() {
        return TYPE;
    }

    // Static encoder function with correct parameter order (buffer first, then packet)
    private static void encode(RegistryFriendlyByteBuf buf, FogStateSyncPacket packet) {
        packet.write(buf);
    }

    // StreamCodec for registration (encoder first, then decoder)
    public static final StreamCodec<RegistryFriendlyByteBuf, FogStateSyncPacket> CODEC =
            StreamCodec.of(
                    FogStateSyncPacket::encode,
                    FogStateSyncPacket::new
            );

    // Packet handler on client side
    public static void handle(FogStateSyncPacket packet, IPayloadContext ctx) {
        if (ctx instanceof ClientPayloadContext clientCtx) {
            clientCtx.enqueueWork(() -> {
                ClientFogState.setFogState(
                        packet.activeFogId,
                        packet.currentIntensityOrdinal,
                        packet.fogStart,
                        packet.fogEnd,
                        packet.fogFadeInEnd,
                        packet.fogFadeOutStart
                );
            });
        }
    }
}