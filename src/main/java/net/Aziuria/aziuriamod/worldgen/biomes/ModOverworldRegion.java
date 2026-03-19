package net.Aziuria.aziuriamod.worldgen.biomes;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.VanillaParameterOverlayBuilder;

import java.util.function.Consumer;

import static terrablender.api.ParameterUtils.*;

public class ModOverworldRegion extends Region {

    public ModOverworldRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(
            Registry<Biome> registry,
            Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper
    ) {
        VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();

        new ParameterPointListBuilder()
                .temperature(Temperature.WARM, Temperature.HOT) // (this and humidity below really helped alter size of biome)
                .humidity(Humidity.ARID, Humidity.ARID)          // very dry only
                .continentalness(Continentalness.INLAND, Continentalness.MID_INLAND)
                .erosion(Erosion.EROSION_0, Erosion.EROSION_3)
                .depth(Depth.SURFACE, Depth.FLOOR)
                .weirdness(Weirdness.MID_SLICE_NORMAL_DESCENDING)
                .build()
                .forEach(point -> builder.add(point, ModBiomes.SPECTRAL_SOULBOUND_FOREST));

        builder.build().forEach(mapper);
    }
}