package net.Aziuria.aziuriamod.worldgen.rules.registry;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.Aziuria.aziuriamod.worldgen.rules.NearLavaPlacementModifier;
import net.Aziuria.aziuriamod.worldgen.rules.NearLogPlacementModifier;
import net.Aziuria.aziuriamod.worldgen.rules.NearbyWaterRadiusFilter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPlacementModifier {

    public static final DeferredRegister<PlacementModifierType<?>> PLACEMENT_MODIFIERS =
            DeferredRegister.create(BuiltInRegistries.PLACEMENT_MODIFIER_TYPE, AziuriaMod.MOD_ID);

    public static final DeferredHolder<PlacementModifierType<?>, PlacementModifierType<?>> NEAR_LOG =
            PLACEMENT_MODIFIERS.register("near_log", () -> NearLogPlacementModifier.TYPE);

    public static final DeferredHolder<PlacementModifierType<?>, PlacementModifierType<?>> NEARBY_WATER_RADIUS_FILTER =
            PLACEMENT_MODIFIERS.register("nearby_water_radius_filter", () -> NearbyWaterRadiusFilter.TYPE);

    // 🔥 Register new Near Lava placement modifier
    public static final DeferredHolder<PlacementModifierType<?>, PlacementModifierType<?>> NEAR_LAVA =
            PLACEMENT_MODIFIERS.register("near_lava", () -> NearLavaPlacementModifier.TYPE);
}