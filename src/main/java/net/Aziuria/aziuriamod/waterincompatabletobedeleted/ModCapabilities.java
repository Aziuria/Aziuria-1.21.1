//package net.Aziuria.aziuriamod.water;
//
//import net.Aziuria.aziuriamod.AziuriaMod;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.entity.EntityType;
//import net.minecraft.world.entity.player.Player;
//import net.neoforged.neoforge.capabilities.EntityCapability;
//import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
//
//public class ModCapabilities {
//
//    public static final ResourceLocation WATER_ID =
//            ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, "water");
//
//    public static final EntityCapability<PlayerWaterCapability, Void> WATER_CAP =
//            EntityCapability.create(WATER_ID, PlayerWaterCapability.class, Void.class);
//
//    public static void register(RegisterCapabilitiesEvent event) {
//        event.registerEntity(
//                WATER_CAP,
//                EntityType.PLAYER,
//                (player, ctx) -> {
//                    PlayerWaterCapability capability = new PlayerWaterCapability();
//                    capability.setOnWaterLevelChanged(() -> WaterNetworkHandler.syncWaterLevel(player));
//                    return capability;
//                }
//        );
//    }
//
//    // Optional: debug or fallback check
//    public static PlayerWaterCapability get(Player player) {
//        return player.getCapability(WATER_CAP);
//    }
//}