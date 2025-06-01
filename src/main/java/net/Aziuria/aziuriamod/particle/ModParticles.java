package net.Aziuria.aziuriamod.particle;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModParticles {

    // Create the DeferredRegister with Minecraft's built-in particle type registry and your modid
    public static final DeferredRegister<ParticleType<?>> PARTICLES =
            DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, "aziuriamod");

    // Register each particle type by name (names must exactly match texture PNGs without extensions)
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLING_LEAF_1 =
            PARTICLES.register("falling_leaf_1", () -> new SimpleParticleType(false));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLING_LEAF_2 =
            PARTICLES.register("falling_leaf_2", () -> new SimpleParticleType(false));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLING_LEAF_3 =
            PARTICLES.register("falling_leaf_3", () -> new SimpleParticleType(false));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLING_LEAF_4 =
            PARTICLES.register("falling_leaf_4", () -> new SimpleParticleType(false));

    // Call this during mod initialization to register the particles
    public static void register(IEventBus eventBus) {
        PARTICLES.register(eventBus);
    }
}