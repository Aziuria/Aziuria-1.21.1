package net.Aziuria.aziuriamod.water;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.ClientPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class WaterStateSyncPacket implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<WaterStateSyncPacket> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("aziuriamod", "water_state_sync"));

    private final int waterLevel;

    // Decoder constructor
    public WaterStateSyncPacket(RegistryFriendlyByteBuf buf) {
        this.waterLevel = buf.readInt();
    }

    // Convenience constructor for FriendlyByteBuf
    public WaterStateSyncPacket(FriendlyByteBuf buf) {
        this((RegistryFriendlyByteBuf) buf);
    }

    // Constructor to create packet from water level
    public WaterStateSyncPacket(int waterLevel) {
        this.waterLevel = waterLevel;
    }

    // Write method
    public void write(FriendlyByteBuf buf) {
        write((RegistryFriendlyByteBuf) buf);
    }

    public void write(RegistryFriendlyByteBuf buf) {
        buf.writeInt(waterLevel);
    }

    @Override
    public CustomPacketPayload.Type<WaterStateSyncPacket> type() {
        return TYPE;
    }

    // Encoder function for codec
    private static void encode(RegistryFriendlyByteBuf buf, WaterStateSyncPacket packet) {
        packet.write(buf);
    }

    // StreamCodec for registration
    public static final StreamCodec<RegistryFriendlyByteBuf, WaterStateSyncPacket> CODEC =
            StreamCodec.of(WaterStateSyncPacket::encode, WaterStateSyncPacket::new);

    // Handler on client side to apply the synced water level
    public static void handle(WaterStateSyncPacket packet, IPayloadContext ctx) {
        if (ctx instanceof ClientPayloadContext clientCtx) {
            clientCtx.enqueueWork(() -> {
                ClientWaterState.setWaterLevel(packet.waterLevel);
            });
        }
    }
}