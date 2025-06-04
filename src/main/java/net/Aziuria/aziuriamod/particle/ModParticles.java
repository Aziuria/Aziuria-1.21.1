package net.Aziuria.aziuriamod.particle;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModParticles {
    public static final DeferredRegister <ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, AziuriaMod.MOD_ID);

public static final Supplier<SimpleParticleType> FALLING_LEAF_PARTICLE =
        PARTICLE_TYPES.register("falling_leaf_particle", () -> new SimpleParticleType(true));

    public static void  register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }

}