package net.Aziuria.aziuriamod.worldgen.biomes;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBiomes {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(Registries.BIOME, AziuriaMod.MOD_ID);

    // ResourceKey for JSON-defined biome
    public static final ResourceKey<Biome> SPECTRAL_SOULBOUND_FOREST = register("spectral_soulbound_forest");

    // TagKey for cleaner biome grouping
    public static final TagKey<Biome> SPECTRAL_SOULBOUND_FOREST_TAG = tag("spectral_soulbound_forest");

    // Helper if needed for other biomes
    private static ResourceKey<Biome> register(String name) {
        return ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, name));
    }

    private static TagKey<Biome> tag(String name) {
        return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, name));
    }

    public static void register(IEventBus eventBus) {
        BIOMES.register(eventBus);
    }
}