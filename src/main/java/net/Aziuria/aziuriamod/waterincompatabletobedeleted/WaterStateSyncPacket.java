//package net.Aziuria.aziuriamod.water;
//
//import net.minecraft.network.FriendlyByteBuf;
//import net.minecraft.network.RegistryFriendlyByteBuf;
//import net.minecraft.network.codec.StreamCodec;
//import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
//import net.minecraft.resources.ResourceLocation;
//import net.neoforged.neoforge.network.handling.ClientPayloadContext;
//import net.neoforged.neoforge.network.handling.IPayloadContext;
//
//public class WaterStateSyncPacket implements CustomPacketPayload {
//
//    public static final CustomPacketPayload.Type<WaterStateSyncPacket> TYPE =
//            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("aziuriamod", "water_state_sync"));
//
//    private final int waterLevel;
//
//    // Decoder constructor: called when packet is received and data needs to be read
//    public WaterStateSyncPacket(RegistryFriendlyByteBuf buf) {
//        this.waterLevel = buf.readInt();
//    }
//
//    // Convenience constructor from FriendlyByteBuf (cast internally)
//    public WaterStateSyncPacket(FriendlyByteBuf buf) {
//        this((RegistryFriendlyByteBuf) buf);
//    }
//
//    // Constructor to create a new packet instance with water level to send
//    public WaterStateSyncPacket(int waterLevel) {
//        this.waterLevel = waterLevel;
//    }
//
//    public int getWaterLevel() {
//        return waterLevel;
//    }
//
//    // Write methods to serialize data into buffer before sending
//    public void write(FriendlyByteBuf buf) {
//        write((RegistryFriendlyByteBuf) buf);
//    }
//
//    public void write(RegistryFriendlyByteBuf buf) {
//        buf.writeInt(waterLevel);
//    }
//
//    @Override
//    public CustomPacketPayload.Type<WaterStateSyncPacket> type() {
//        return TYPE;
//    }
//
//    // Encoder function used by StreamCodec to write packet data
//    private static void encode(RegistryFriendlyByteBuf buf, WaterStateSyncPacket packet) {
//        packet.write(buf);
//    }
//
//    // StreamCodec instance used to register this packet in NeoForge networking
//    public static final StreamCodec<RegistryFriendlyByteBuf, WaterStateSyncPacket> CODEC =
//            StreamCodec.of(WaterStateSyncPacket::encode, WaterStateSyncPacket::new);
//
//    // Packet handler, called on packet receive
//    public static void handle(WaterStateSyncPacket packet, IPayloadContext ctx) {
//        System.out.println("[WaterStateSyncPacket] Handle on thread: " + Thread.currentThread().getName());
//        System.out.println("[WaterStateSyncPacket] Packet handle method called. Is client ctx? " + (ctx instanceof ClientPayloadContext));
//        if (ctx instanceof ClientPayloadContext clientCtx) {
//            clientCtx.enqueueWork(() -> {
//                System.out.println("[WaterStateSyncPacket] Received water level update on client: " + packet.waterLevel);
//                ClientWaterState.setWaterLevel(packet.waterLevel);
//            });
//        } else {
//            System.out.println("[WaterStateSyncPacket] Received on server or unknown context");
//        }
//    }
//}