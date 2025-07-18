package net.Aziuria.aziuriamod.datagen;

import net.Aziuria.aziuriamod.block.ModBlocks;
import net.Aziuria.aziuriamod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class ModDataMapProvider extends DataMapProvider {
    protected ModDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        this.builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(ModItems.RADISH_SEEDS.getId(), new Compostable(0.25f), false)
                .add(ModItems.RADISH.getId(), new Compostable(0.45f), false)
                .add(ModItems.CUCUMBER_SEEDS.getId(), new Compostable(0.25f), false)
                .add(ModItems.CUCUMBER.getId(), new Compostable(0.45f), false)
                .add(ModItems.TOMATO_SEEDS.getId(), new Compostable(0.25f), false)
                .add(ModItems.TOMATO.getId(), new Compostable(0.45f), false)
                .add(ModItems.LETTUCE_SEEDS.getId(), new Compostable(0.25f), false)
                .add(ModItems.LETTUCE.getId(), new Compostable(0.45f), false)
                .add(ModItems.ONION_SEEDS.getId(), new Compostable(0.25f), false)
                .add(ModItems.ONION.getId(), new Compostable(0.45f), false)
                .add(ModItems.SPRING_ONION_SEEDS.getId(), new Compostable(0.25f), false)
                .add(ModItems.SPRING_ONION.getId(), new Compostable(0.45f), false)
                .add(ModItems.CORN_SEEDS.getId(), new Compostable(0.45f), false)
                .add(ModItems.CORN.getId(), new Compostable(0.45f), false)
                .add(ModItems.PINEAPPLE_SEEDS.getId(), new Compostable(0.45f), false)
                .add(ModItems.PINEAPPLE.getId(), new Compostable(0.45f), false)
                .add(ModBlocks.LEAF_LITTER.getId(), new Compostable(0.45f), false)
                .add(ModBlocks.APPLE_LEAVES.getId(), new Compostable(0.45f), false)
                .add(ModBlocks.APPLE_SAPLING.getId(), new Compostable(0.45f), false)
                .add(ModItems.FLAX_FLOWER.getId(), new Compostable(0.45f), false);


    }
}
