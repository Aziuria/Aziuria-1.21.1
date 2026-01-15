package net.Aziuria.aziuriamod.datagen;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.Aziuria.aziuriamod.block.ModBlocks;
import net.Aziuria.aziuriamod.item.ModItems;
import net.Aziuria.aziuriamod.loot.modifier.AddItemModifier;
import net.Aziuria.aziuriamod.loot.modifier.RemoveItemModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, AziuriaMod.MOD_ID);
    }

    @Override
    protected void start() {
//        this.add("radish_seeds_to_short_grass",
//                new AddItemModifier(new LootItemCondition[] {
//                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.SHORT_GRASS).build(),
//                        LootItemRandomChanceCondition.randomChance(0.25f).build() }, ModItems.RADISH_SEEDS.get()));
//        this.add("radish_seeds_to_tall_grass",
//                new AddItemModifier(new LootItemCondition[] {
//                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.TALL_GRASS).build(),
//                        LootItemRandomChanceCondition.randomChance(0.25f).build() }, ModItems.RADISH_SEEDS.get()));
//
        this.add("bucket_from_plains_house",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/village/village_plains_house")).build(),
                        LootItemRandomChanceCondition.randomChance(1.0f).build()  // 75% chance

                }, Items.BUCKET, 1, 2)); // gives 1–2 buckets
        this.add("iron_ingot_from_plains_house",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/village/village_plains_house")).build(),
                        LootItemRandomChanceCondition.randomChance(0.5f).build()  // 50% chance
                }, Items.IRON_INGOT, 3, 8)); // gives 1–3 iron ingots

        this.add("apple_tree_from_plains_house",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/village/village_plains_house")).build()
                }, ModBlocks.APPLE_SAPLING.get()));

        this.add("speaker_from_plains_house",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/village/village_plains_house")).build()
                }, ModBlocks.SPEAKER.get(), 1, 1));

        this.add("demarcation_post_from_plains_house",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/village/village_plains_house")).build()
                }, ModBlocks.DEMAECATION_POST_B.get(), 3, 12));

        this.add("blackcurrant_seeds_from_plains_house",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/village/village_plains_house")).build(),
                        LootItemRandomChanceCondition.randomChance(0.9f).build()  // 40% chance
                }, ModItems.BLACKCURRANT_SEEDS.get(), 1, 4)); // gives 1–4 seeds

        this.add("potion_from_plains_house",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/village/village_plains_house")).build(),
                        LootItemRandomChanceCondition.randomChance(0.5f).build()  // 50% chance
                }, Items.POTION, 1, 3)); // gives 1–3 iron ingots

        this.add("worms_from_grass",
                new AddItemModifier(new LootItemCondition[] {
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.GRASS_BLOCK).build(),
                        LootItemRandomChanceCondition.randomChance(0.15f).build() // 3% chance
                }, ModItems.WORM.get(), 0, 5)); // gives 0–5 worms

        this.add("worms_from_dirt",
                new AddItemModifier(new LootItemCondition[] {
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.DIRT).build(),
                        LootItemRandomChanceCondition.randomChance(0.15f).build() // 3% chance
                }, ModItems.WORM.get(), 0, 5)); // gives 0–5 worms

//        this.add("remove_cobblestone_from_stone",
//                new RemoveItemModifier(new LootItemCondition[] {
//                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.STONE).build()
//                }, Items.COBBLESTONE));
//
//        this.add("stone_drops_shards",
//                new AddItemModifier(new LootItemCondition[] {
//                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.STONE).build()
//                }, ModItems.STONE_SHARD.get(), 3, 8));
//
//        this.add("berry_from_creeper",
//                new AddItemModifier(new LootItemCondition[] {
//                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("entities/creeper")).build()
//                }, ModItems.GOJI_BERRIES.get()));

        this.add("remove_vanilla_oak_sapling_from_oak_leaves",
                new RemoveItemModifier(new LootItemCondition[] {
                        LootItemBlockStatePropertyCondition
                                .hasBlockStateProperties(Blocks.OAK_LEAVES)
                                .build()
                }, Items.OAK_SAPLING)
        );

        this.add("add_custom_oak_sapling_to_oak_leaves",
                new AddItemModifier(new LootItemCondition[] {
                        LootItemBlockStatePropertyCondition
                                .hasBlockStateProperties(Blocks.OAK_LEAVES)
                                .build(),
                        LootItemRandomChanceCondition.randomChance(0.15f).build() // vanilla-like chance
                }, ModBlocks.CUSTOM_OAK_SAPLING.get(), 1, 1)
        );

        this.add("remove_vanilla_birch_sapling_from_oak_leaves",
                new RemoveItemModifier(new LootItemCondition[] {
                        LootItemBlockStatePropertyCondition
                                .hasBlockStateProperties(Blocks.BIRCH_LEAVES)
                                .build()
                }, Items.BIRCH_SAPLING)
        );

        this.add("add_custom_birch_sapling_to_oak_leaves",
                new AddItemModifier(new LootItemCondition[] {
                        LootItemBlockStatePropertyCondition
                                .hasBlockStateProperties(Blocks.BIRCH_LEAVES)
                                .build(),
                        LootItemRandomChanceCondition.randomChance(0.15f).build() // vanilla-like chance
                }, ModBlocks.CUSTOM_BIRCH_SAPLING.get(), 1, 1)
        );


    }
}