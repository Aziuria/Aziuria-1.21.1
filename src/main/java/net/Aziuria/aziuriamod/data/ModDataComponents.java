package net.Aziuria.aziuriamod.data;

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

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<CompoundTag>> SACK_INVENTORY =
            COMPONENTS.register("sack_inventory", () ->
                    DataComponentType.<CompoundTag>builder()
                            .persistent(CompoundTag.CODEC)
                            .build()
            );

    public static void register(IEventBus bus) {
        COMPONENTS.register(bus);
    }
}