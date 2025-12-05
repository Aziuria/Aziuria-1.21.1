package net.Aziuria.aziuriamod.worldgen.rules.registry;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.Aziuria.aziuriamod.worldgen.rules.DynamicForkingTrunkPlacer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModTrunkPlacerTypes {

    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS =
            DeferredRegister.create(BuiltInRegistries.TRUNK_PLACER_TYPE, AziuriaMod.MOD_ID);

    // Use wildcards instead of trying to specify exact type
    public static final DeferredHolder<TrunkPlacerType<?>, ?> DYNAMIC_FORKING =
            TRUNK_PLACERS.register("dynamic_forking_trunk_placer", () ->
                    new TrunkPlacerType<>(DynamicForkingTrunkPlacer.CODEC)
            );
}