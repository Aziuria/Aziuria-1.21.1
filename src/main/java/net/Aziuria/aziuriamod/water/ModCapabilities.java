package net.Aziuria.aziuriamod.water;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class ModCapabilities {

    // Capability ID
    public static final ResourceLocation WATER_ID =
            ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, "water");

    public static final EntityCapability<PlayerWaterCapability, Void> WATER_CAP =
            EntityCapability.create(WATER_ID, PlayerWaterCapability.class, Void.class);

    public static void register(RegisterCapabilitiesEvent event) {
        event.registerEntity(
                WATER_CAP,
                EntityType.PLAYER,
                (player, ctx) -> new PlayerWaterCapability()  // Factory constructor only
        );
    }
}