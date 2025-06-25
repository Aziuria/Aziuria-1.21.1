package net.Aziuria.aziuriamod.sounds;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, AziuriaMod.MOD_ID);

    public static final Supplier<SoundEvent> SIREN = SOUND_EVENTS.register("siren",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, "siren")));

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}