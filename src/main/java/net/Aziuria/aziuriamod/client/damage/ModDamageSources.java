package net.Aziuria.aziuriamod.client.damage;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.core.registries.Registries;


public class ModDamageSources {

    public static DamageSource dehydration(ServerLevel level) {
        ResourceKey<DamageType> key = ResourceKey.create(Registries.DAMAGE_TYPE, ModDamageTypes.dehydrationId());

        Holder<DamageType> holder = level.registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolder(key)
                .orElseThrow(() -> new IllegalStateException("Dehydration damage type not found!"));

        return new DamageSource(holder);
    }
}