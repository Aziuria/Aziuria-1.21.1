package net.Aziuria.aziuriamod.worldgen.biomes;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public class ModBiomes {

    public static final ResourceKey<Biome> SPECTRAL_SOULBOUND_FOREST = register("spectral_soulbound_forest");

    // helper if needed for other biomes
    private static ResourceKey<Biome> register(String name) {
        return ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, name));
    }
}