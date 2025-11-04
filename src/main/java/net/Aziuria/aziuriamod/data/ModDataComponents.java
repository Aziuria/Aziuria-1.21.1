package net.Aziuria.aziuriamod.data;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.Aziuria.aziuriamod.AziuriaMod;

public class ModDataComponents {

    public static final DeferredRegister<DataComponentType<?>> COMPONENTS =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, AziuriaMod.MOD_ID);

    // --- Existing Component ---
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<CompoundTag>> SACK_INVENTORY =
            COMPONENTS.register("sack_inventory", () ->
                    DataComponentType.<CompoundTag>builder()
                            .persistent(CompoundTag.CODEC)
                            .build()
            );

    // --- New Components for Baited Fishing Rods ---
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> ROD_CAUGHT_COUNT =
            COMPONENTS.register("rod_caught_count", () ->
                    DataComponentType.<Integer>builder()
                            .persistent(Codec.INT)
                            .build()
            );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> ROD_MAX_BEFORE_REVERT =
            COMPONENTS.register("rod_max_before_revert", () ->
                    DataComponentType.<Integer>builder()
                            .persistent(Codec.INT)
                            .build()
            );

    public static void register(IEventBus bus) {
        COMPONENTS.register(bus);
    }
}