package net.Aziuria.aziuriamod.effect;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.Aziuria.aziuriamod.effect.custom.StingEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, AziuriaMod.MOD_ID);

    public static final Holder<MobEffect> STING_EFFECT = MOB_EFFECTS.register("nettled",
            () -> new StingEffect(MobEffectCategory.HARMFUL, 0x88FF88));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}