package net.Aziuria.aziuriamod.worldgen;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPlacementModifier {

    public static final DeferredRegister<PlacementModifierType<?>> PLACEMENT_MODIFIERS =
            DeferredRegister.create(BuiltInRegistries.PLACEMENT_MODIFIER_TYPE, AziuriaMod.MOD_ID);

    public static final DeferredHolder<PlacementModifierType<?>, PlacementModifierType<?>> NEAR_LOG =
            PLACEMENT_MODIFIERS.register("near_log", () -> NearLogPlacementModifier.TYPE);

}