package net.Aziuria.aziuriamod.item.custom.entities;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.Aziuria.aziuriamod.block.projectile.PebbleProjectileEntity;
import net.Aziuria.aziuriamod.island.entity.IslandThrowableEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(Registries.ENTITY_TYPE, AziuriaMod.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<IslandThrowableEntity>> ISLAND_THROWABLE =
            ENTITIES.register("island_throwable", () ->
                    EntityType.Builder.<IslandThrowableEntity>of(IslandThrowableEntity::new, MobCategory.MISC)
                            .sized(0.25f, 0.25f)
                            .clientTrackingRange(4)
                            .updateInterval(10)
                            .build("island_throwable")
            );

    // Already mostly correct in your ModEntities.java
    public static final DeferredHolder<EntityType<?>, EntityType<PebbleProjectileEntity>> PEBBLE_PROJECTILE =
            ENTITIES.register("pebble_projectile", () ->
                    EntityType.Builder.<PebbleProjectileEntity>of(PebbleProjectileEntity::new, MobCategory.MISC)
                            .sized(0.25f, 0.25f)
                            .clientTrackingRange(4)
                            .updateInterval(10)
                            .build("pebble_projectile")
            );
}