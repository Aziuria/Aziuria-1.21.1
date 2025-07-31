package net.Aziuria.aziuriamod.client.damage;


import net.Aziuria.aziuriamod.AziuriaMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DeathMessageType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModDamageTypes {

    public static final DeferredRegister<DamageType> DAMAGE_TYPES =
            DeferredRegister.create(Registries.DAMAGE_TYPE, AziuriaMod.MOD_ID);

    public static final DeferredHolder<DamageType, DamageType> DEHYDRATION =
            DAMAGE_TYPES.register("dehydration", () ->
                    new DamageType(
                            "dehydration",
                            DamageScaling.NEVER,
                            0.1F,
                            DamageEffects.HURT,  // No knockback here
                            DeathMessageType.DEFAULT
                    )
            );

    public static void register(net.neoforged.bus.api.IEventBus eventBus) {
        DAMAGE_TYPES.register(eventBus);
    }

    public static ResourceLocation dehydrationId() {
        return ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, "dehydration");
    }
}