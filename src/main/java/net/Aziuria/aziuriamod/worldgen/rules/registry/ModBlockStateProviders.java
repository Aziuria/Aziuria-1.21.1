package net.Aziuria.aziuriamod.worldgen.rules.registry;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.Aziuria.aziuriamod.worldgen.rules.RandomFacingStateProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockStateProviders {

    public static final DeferredRegister<BlockStateProviderType<?>> STATE_PROVIDERS =
            DeferredRegister.create(BuiltInRegistries.BLOCKSTATE_PROVIDER_TYPE, AziuriaMod.MOD_ID);

    public static final DeferredHolder<BlockStateProviderType<?>, BlockStateProviderType<?>> RANDOM_FACING =
            STATE_PROVIDERS.register("random_facing", () ->
                    new BlockStateProviderType<>(RandomFacingStateProvider.CODEC)
            );
}