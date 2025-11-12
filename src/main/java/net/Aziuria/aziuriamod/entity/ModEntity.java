package net.Aziuria.aziuriamod.entity;


import net.Aziuria.aziuriamod.AziuriaMod;
import net.Aziuria.aziuriamod.entity.custom.WormEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntity {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, AziuriaMod.MOD_ID);

    public static final Supplier<EntityType<WormEntity>> WORM =
            ENTITY_TYPES.register("worm", () -> EntityType.Builder.of(WormEntity::new, MobCategory.CREATURE)
                    .sized(0.30f, 0.15f).build("worm"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}