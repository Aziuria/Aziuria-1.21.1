package net.Aziuria.aziuriamod.datagen;

import net.Aziuria.aziuriamod.block.ModBlocks;
import net.Aziuria.aziuriamod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class ModDataMapProvider extends DataMapProvider {
    protected ModDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    @SuppressWarnings("deprecation")
    protected void gather(HolderLookup.Provider provider) {
        this.builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(ModItems.RADISH_SEEDS.getId(), new Compostable(0.30f), false)
                .add(ModItems.RADISH.getId(), new Compostable(0.45f), false)
                .add(ModItems.CUCUMBER_SEEDS.getId(), new Compostable(0.30f), false)
                .add(ModItems.CUCUMBER.getId(), new Compostable(0.45f), false)
                .add(ModItems.TOMATO_SEEDS.getId(), new Compostable(0.30f), false)
                .add(ModItems.TOMATO.getId(), new Compostable(0.45f), false)
                .add(ModItems.LETTUCE_SEEDS.getId(), new Compostable(0.30f), false)
                .add(ModItems.LETTUCE.getId(), new Compostable(0.45f), false)
                .add(ModItems.ONION_SEEDS.getId(), new Compostable(0.30f), false)
                .add(ModItems.ONION.getId(), new Compostable(0.45f), false)
                .add(ModItems.SPRING_ONION_SEEDS.getId(), new Compostable(0.30f), false)
                .add(ModItems.SPRING_ONION.getId(), new Compostable(0.45f), false)
                .add(ModItems.CORN_SEEDS.getId(), new Compostable(0.30f), false)
                .add(ModItems.CORN.getId(), new Compostable(0.45f), false)
                .add(ModItems.CORN_KERNELS.getId(), new Compostable(0.45f), false)
                .add(ModItems.BREAD_BAIT.getId(), new Compostable(0.45f), false)
                .add(ModItems.COFFEE_SEEDS.getId(), new Compostable(0.30f), false)
                .add(ModItems.COFFEE_BEANS.getId(), new Compostable(0.45f), false)
                .add(ModItems.TEA_SEEDS.getId(), new Compostable(0.30f), false)
                .add(ModItems.TEA_LEAVES.getId(), new Compostable(0.45f), false)
                .add(ModItems.PINEAPPLE_SEEDS.getId(), new Compostable(0.30f), false)
                .add(ModItems.PINEAPPLE.getId(), new Compostable(0.45f), false)
                .add(ModItems.PEAR.getId(), new Compostable(0.45f), false)
                .add(ModItems.ORANGE.getId(), new Compostable(0.45f), false)
                .add(ModItems.AVOCADO.getId(), new Compostable(0.45f), false)
                .add(ModItems.BANANA.getId(), new Compostable(0.45f), false)
                .add(ModItems.CHERRY.getId(), new Compostable(0.45f), false)
                .add(ModItems.BLACKCURRANT.getId(), new Compostable(0.45f), false)
                .add(ModItems.STRAWBERRY.getId(), new Compostable(0.45f), false)
                .add(ModItems.BLUEBERRY.getId(), new Compostable(0.45f), false)
                .add(ModItems.GOOSEBERRY.getId(), new Compostable(0.45f), false)
                .add(ModItems.BLACKCURRANT_SEEDS.getId(), new Compostable(0.30f), false)
                .add(ModItems.STRAWBERRY_SEEDS.getId(), new Compostable(0.30f), false)
                .add(ModItems.BLUEBERRY_SEEDS.getId(), new Compostable(0.30f), false)
                .add(ModItems.GOOSEBERRY_SEEDS.getId(), new Compostable(0.30f), false)
                .add(ModBlocks.LEAF_LITTER.getId(), new Compostable(0.30f), false)
                .add(ModBlocks.APPLE_LEAVES.getId(), new Compostable(0.30f), false)
                .add(ModBlocks.APPLE_SAPLING.getId(), new Compostable(0.30f), false)
                .add(ModBlocks.CUSTOM_OAK_LEAVES.getId(), new Compostable(0.30f), false)
                .add(ModBlocks.CUSTOM_OAK_SAPLING.getId(), new Compostable(0.30f), false)
                .add(ModBlocks.PEAR_LEAVES.getId(), new Compostable(0.30f), false)
                .add(ModBlocks.PEAR_SAPLING.getId(), new Compostable(0.30f), false)
                .add(ModBlocks.CHERRY_LEAVES.getId(), new Compostable(0.30f), false)
                .add(ModBlocks.CHERRY_SAPLING.getId(), new Compostable(0.30f), false)
                .add(ModBlocks.AVOCADO_LEAVES.getId(), new Compostable(0.30f), false)
                .add(ModBlocks.AVOCADO_SAPLING.getId(), new Compostable(0.30f), false)
                .add(ModBlocks.ORANGE_LEAVES.getId(), new Compostable(0.30f), false)
                .add(ModBlocks.ORANGE_SAPLING.getId(), new Compostable(0.30f), false)
                .add(ModBlocks.BANANA_LEAVES.getId(), new Compostable(0.30f), false)
                .add(ModBlocks.BANANA_SAPLING.getId(), new Compostable(0.30f), false)
                .add(ModItems.YUCCA_PLANT.getId(), new Compostable(0.30f), false)
                .add(ModItems.YUCCA_LEAVES.getId(), new Compostable(0.30f), false)
                .add(Items.POISONOUS_POTATO.builtInRegistryHolder().key(), new Compostable(0.50f), false)
                .add(ModItems.OAK_BARK.getId(), new Compostable(0.25f), false)
                .add(ModItems.BIRCH_BARK.getId(), new Compostable(0.25f), false)
                .add(ModItems.SPRUCE_BARK.getId(), new Compostable(0.25f), false)
                .add(ModItems.JUNGLE_BARK.getId(), new Compostable(0.25f), false)
                .add(ModItems.ACACIA_BARK.getId(), new Compostable(0.25f), false)
                .add(ModItems.DARK_OAK_BARK.getId(), new Compostable(0.25f), false)
                .add(ModItems.MANGROVE_BARK.getId(), new Compostable(0.25f), false)
                .add(ModItems.CHERRY_BARK.getId(), new Compostable(0.25f), false)
                .add(ModItems.DRIED_GRASS.getId(), new Compostable(0.45f), false)
                .add(ModBlocks.STICK_A.getId(), new Compostable(0.30f), false)
                .add(ModBlocks.STICK_B.getId(), new Compostable(0.30f), false)
                .add(ModBlocks.STICK_C.getId(), new Compostable(0.30f), false)
                .add(ModItems.FLAX_FLOWER.getId(), new Compostable(0.30f), false);

        this.builder(NeoForgeDataMaps.FURNACE_FUELS)
                .add(ModItems.DRIED_GRASS.getId(), new FurnaceFuel(100), false)
                .add(ModItems.OAK_BARK.getId(), new FurnaceFuel(120), false)
                .add(ModItems.BIRCH_BARK.getId(), new FurnaceFuel(120), false)
                .add(ModItems.SPRUCE_BARK.getId(), new FurnaceFuel(120), false)
                .add(ModItems.JUNGLE_BARK.getId(), new FurnaceFuel(120), false)
                .add(ModItems.ACACIA_BARK.getId(), new FurnaceFuel(120), false)
                .add(ModItems.DARK_OAK_BARK.getId(), new FurnaceFuel(120), false)
                .add(ModItems.MANGROVE_BARK.getId(), new FurnaceFuel(120), false)
                .add(ModItems.CHERRY_BARK.getId(), new FurnaceFuel(120), false)
                .add(ModItems.WOOD_KNIFE.getId(), new FurnaceFuel(100), false)
                .add(ModItems.MALLET.getId(), new FurnaceFuel(100), false)
                .add(ModBlocks.STICK_A.getId(), new FurnaceFuel(120), false)
                .add(ModBlocks.STICK_B.getId(), new FurnaceFuel(120), false)
                .add(ModBlocks.STICK_C.getId(), new FurnaceFuel(120), false)
                .add(ModBlocks.LEAF_LITTER.getId(), new FurnaceFuel(100), false);

    }
}
