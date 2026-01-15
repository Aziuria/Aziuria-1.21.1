package net.Aziuria.aziuriamod.loot;

import com.mojang.serialization.MapCodec;
import net.Aziuria.aziuriamod.AziuriaMod;
import net.Aziuria.aziuriamod.loot.modifier.AddItemModifier;
import net.Aziuria.aziuriamod.loot.modifier.RemoveItemModifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModLootModifiers {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, AziuriaMod.MOD_ID);

    // ✅ Registers the AddItemModifier
    public static final Supplier<MapCodec<? extends IGlobalLootModifier>> ADD_ITEM =
            LOOT_MODIFIER_SERIALIZERS.register("add_item", () -> AddItemModifier.CODEC);

    // ✅ Registers the RemoveItemModifier
    public static final Supplier<MapCodec<? extends IGlobalLootModifier>> REMOVE_ITEM =
            LOOT_MODIFIER_SERIALIZERS.register("remove_item", () -> RemoveItemModifier.CODEC);

    public static void register(IEventBus eventBus) {
        LOOT_MODIFIER_SERIALIZERS.register(eventBus);
    }
}