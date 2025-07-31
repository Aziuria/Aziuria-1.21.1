package net.Aziuria.aziuriamod.thirst.network;

import net.Aziuria.aziuriamod.thirst.capability.IThirst;
import net.Aziuria.aziuriamod.thirst.capability.ThirstProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.ClientPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ThirstSyncPacket implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<ThirstSyncPacket> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("aziuriamod", "thirst_sync"));

    private final int thirst;

    // Decoder constructor (called when reading packet)
    public ThirstSyncPacket(RegistryFriendlyByteBuf buf) {
        this.thirst = buf.readInt();
    }

    // Convenience constructor
    public ThirstSyncPacket(FriendlyByteBuf buf) {
        this((RegistryFriendlyByteBuf) buf);
    }

    // Constructor for sending
    public ThirstSyncPacket(int thirst) {
        this.thirst = thirst;
    }

    public int getThirst() {
        return thirst;
    }

    // Write data to buffer before sending
    public void write(FriendlyByteBuf buf) {
        write((RegistryFriendlyByteBuf) buf);
    }

    public void write(RegistryFriendlyByteBuf buf) {
        buf.writeInt(thirst);
    }

    @Override
    public CustomPacketPayload.Type<ThirstSyncPacket> type() {
        return TYPE;
    }

    // Encoder for the StreamCodec
    private static void encode(RegistryFriendlyByteBuf buf, ThirstSyncPacket packet) {
        packet.write(buf);
    }

    // Codec to register with NeoForge networking
    public static final StreamCodec<RegistryFriendlyByteBuf, ThirstSyncPacket> CODEC =
            StreamCodec.of(ThirstSyncPacket::encode, ThirstSyncPacket::new);

    // Packet handler called on receipt
    public static void handle(ThirstSyncPacket packet, IPayloadContext ctx) {
        if (ctx instanceof ClientPayloadContext clientCtx) {
            clientCtx.enqueueWork(() -> {
                var mc = net.minecraft.client.Minecraft.getInstance();
                var player = mc.player;
                if (player != null) {
                    IThirst thirstCap = player.getCapability(ThirstProvider.THIRST_CAP, null);
                    if (thirstCap != null) {
                        thirstCap.setThirst(packet.thirst);
                        System.out.println("[ThirstSyncPacket] Thirst updated on client: " + packet.thirst);
                    }
                }
            });
        }
    }
}