package net.Aziuria.aziuriamod.datagen;

import net.Aziuria.aziuriamod.block.*;
import net.Aziuria.aziuriamod.block.custom.*;
import net.Aziuria.aziuriamod.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.STEEL_BLOCK.get());
        dropSelf(ModBlocks.STEEL_BARS.get());

        dropSelf(ModBlocks.COPPER_BARS.get());
        dropSelf(ModBlocks.EXPOSED_COPPER_BARS.get());
        dropSelf(ModBlocks.WEATHERED_COPPER_BARS.get());
        dropSelf(ModBlocks.OXIDIZED_COPPER_BARS.get());

        dropSelf(ModBlocks.STEEL_CHAIN.get());

        dropSelf(ModBlocks.COPPER_CHAIN.get());
        dropSelf(ModBlocks.EXPOSED_COPPER_CHAIN.get());
        dropSelf(ModBlocks.WEATHERED_COPPER_CHAIN.get());
        dropSelf(ModBlocks.OXIDIZED_COPPER_CHAIN.get());

        dropSelf(ModBlocks.WAXED_COPPER_BARS.get());
        dropSelf(ModBlocks.WAXED_EXPOSED_COPPER_BARS.get());
        dropSelf(ModBlocks.WAXED_WEATHERED_COPPER_BARS.get());
        dropSelf(ModBlocks.WAXED_OXIDIZED_COPPER_BARS.get());

        dropSelf(ModBlocks.WAXED_COPPER_CHAIN.get());
        dropSelf(ModBlocks.WAXED_EXPOSED_COPPER_CHAIN.get());
        dropSelf(ModBlocks.WAXED_WEATHERED_COPPER_CHAIN.get());
        dropSelf(ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN.get());

        dropSelf(ModBlocks.OAK_SHELF.get());
        dropSelf(ModBlocks.ACACIA_SHELF.get());
        dropSelf(ModBlocks.BAMBOO_SHELF.get());
        dropSelf(ModBlocks.BIRCH_SHELF.get());
        dropSelf(ModBlocks.CHERRY_SHELF.get());
        dropSelf(ModBlocks.DARK_OAK_SHELF.get());
        dropSelf(ModBlocks.JUNGLE_SHELF.get());
        dropSelf(ModBlocks.MANGROVE_SHELF.get());
        dropSelf(ModBlocks.SPRUCE_SHELF.get());

        dropSelf(ModBlocks.OAK_STORAGE.get());
        dropSelf(ModBlocks.ACACIA_STORAGE.get());
        dropSelf(ModBlocks.BAMBOO_STORAGE.get());
        dropSelf(ModBlocks.BIRCH_STORAGE.get());
        dropSelf(ModBlocks.CHERRY_STORAGE.get());
        dropSelf(ModBlocks.DARK_OAK_STORAGE.get());
        dropSelf(ModBlocks.JUNGLE_STORAGE.get());
        dropSelf(ModBlocks.MANGROVE_STORAGE.get());
        dropSelf(ModBlocks.SPRUCE_STORAGE.get());

        dropSelf(ModBlocks.OAK_HOOK.get());
        dropSelf(ModBlocks.ACACIA_HOOK.get());
        dropSelf(ModBlocks.BIRCH_HOOK.get());
        dropSelf(ModBlocks.BAMBOO_HOOK.get());
        dropSelf(ModBlocks.CHERRY_HOOK.get());
        dropSelf(ModBlocks.DARK_OAK_HOOK.get());
        dropSelf(ModBlocks.JUNGLE_HOOK.get());
        dropSelf(ModBlocks.MANGROVE_HOOK.get());
        dropSelf(ModBlocks.SPRUCE_HOOK.get());

        dropSelf(ModBlocks.DEMAECATION_POST.get());
        dropSelf(ModBlocks.DEMAECATION_POST_B.get());
        dropSelf(ModBlocks.DEMAECATION_POST_C.get());
        dropSelf(ModBlocks.DEMAECATION_POST_D.get());
        dropSelf(ModBlocks.DEMAECATION_POST_E.get());
        dropSelf(ModBlocks.BLACKSTONE_GRAVESTONE_A.get());
        dropSelf(ModBlocks.BLACKSTONE_GRAVESTONE_B.get());
        dropSelf(ModBlocks.COBBLESTONE_GRAVESTONE_A.get());
        dropSelf(ModBlocks.COBBLESTONE_GRAVESTONE_B.get());
        dropSelf(ModBlocks.SPEAKER.get());
        dropSelf(ModBlocks.WOODCUTTER_BENCH.get());
        dropSelf(ModBlocks.MINER_BENCH.get());

        dropSelf(ModBlocks.STEEL_BARREL_EMPTY.get());
        dropSelf(ModBlocks.IRON_BARREL_EMPTY.get());
        dropSelf(ModBlocks.COPPER_BARREL.get());
        dropSelf(ModBlocks.EXPOSED_COPPER_BARREL.get());
        dropSelf(ModBlocks.WEATHERED_COPPER_BARREL.get());
        dropSelf(ModBlocks.OXIDIZED_COPPER_BARREL.get());
        dropSelf(ModBlocks.WAXED_COPPER_BARREL.get());
        dropSelf(ModBlocks.WAXED_EXPOSED_COPPER_BARREL.get());
        dropSelf(ModBlocks.WAXED_WEATHERED_COPPER_BARREL.get());
        dropSelf(ModBlocks.WAXED_OXIDIZED_COPPER_BARREL.get());

        dropSelf(ModBlocks.LEAF_LITTER.get());
        dropSelf(ModBlocks.APPLE_SAPLING.get());
        dropSelf(ModBlocks.PEAR_SAPLING.get());
        dropSelf(ModBlocks.CHERRY_SAPLING.get());
        dropSelf(ModBlocks.AVOCADO_SAPLING.get());
        dropSelf(ModBlocks.ORANGE_SAPLING.get());
        dropSelf(ModBlocks.BANANA_SAPLING.get());
        dropSelf(ModBlocks.FLAX_FLOWER_BLOCK.get());

        this.add(ModBlocks.APPLE_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.APPLE_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.add(ModBlocks.AVOCADO_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.AVOCADO_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.add(ModBlocks.ORANGE_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.ORANGE_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.add(ModBlocks.BANANA_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.BANANA_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.add(ModBlocks.PEAR_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.PEAR_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.add(ModBlocks.CHERRY_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.CHERRY_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        add(ModBlocks.SULPHUR_ORE.get(),
        block -> createOreDrop(ModBlocks.SULPHUR_ORE.get(), ModItems.SULPHUR.get()));
        add(ModBlocks.DEEPSLATE_SULPHUR_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.DEEPSLATE_SULPHUR_ORE.get(), ModItems.SULPHUR.get(), 2, 5));
        add(ModBlocks.TIN_ORE.get(),
                block -> createOreDrop(ModBlocks.TIN_ORE.get(), ModItems.TIN.get()));
        add(ModBlocks.DEEPSLATE_TIN_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.DEEPSLATE_TIN_ORE.get(), ModItems.TIN.get(), 2, 5));
        add(ModBlocks.POTASSIUM_ORE.get(),
                block -> createOreDrop(ModBlocks.POTASSIUM_ORE.get(), ModItems.POTASSIUM.get()));
        add(ModBlocks.DEEPSLATE_POTASSIUM_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.DEEPSLATE_POTASSIUM_ORE.get(), ModItems.POTASSIUM.get(), 2, 5));
        add(ModBlocks.SPINEL_ORE.get(),
                block -> createOreDrop(ModBlocks.SPINEL_ORE.get(), ModItems.SPINEL.get()));
        add(ModBlocks.DEEPSLATE_SPINEL_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.DEEPSLATE_SPINEL_ORE.get(), ModItems.SPINEL.get(), 1, 2));
        add(ModBlocks.SPECTRAL_ORE.get(),
                block -> createOreDrop(ModBlocks.SPECTRAL_ORE.get(), ModItems.SPECTRAL_DUST.get()));
        add(ModBlocks.DEEPSLATE_SPECTRAL_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.DEEPSLATE_SPECTRAL_ORE.get(), ModItems.SPECTRAL_DUST.get(), 1, 2));

        LootItemCondition.Builder lootItemConditionBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.RADISH_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(RadishCropBlock.AGE, 3));

        this.add(ModBlocks.RADISH_CROP.get(), this.createCropDrops(ModBlocks.RADISH_CROP.get(),
                ModItems.RADISH.get(), ModItems.RADISH_SEEDS.get(), lootItemConditionBuilder));

        LootItemCondition.Builder cucumberCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.CUCUMBER_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CucumberCropBlock.AGE, 3));

        this.add(ModBlocks.CUCUMBER_CROP.get(), this.createCropDrops(ModBlocks.CUCUMBER_CROP.get(),
                ModItems.CUCUMBER.get(), ModItems.CUCUMBER_SEEDS.get(), cucumberCondition));

        LootItemCondition.Builder tomatoCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.TOMATO_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(TomatoCropBlock.AGE, 3));

        this.add(ModBlocks.TOMATO_CROP.get(), this.createCropDrops(ModBlocks.TOMATO_CROP.get(),
                ModItems.TOMATO.get(), ModItems.TOMATO_SEEDS.get(), tomatoCondition));

        // ✅ NEW crops: Lettuce
        LootItemCondition.Builder lettuceCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.LETTUCE_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LettuceCropBlock.AGE, 3));
        this.add(ModBlocks.LETTUCE_CROP.get(), this.createCropDrops(ModBlocks.LETTUCE_CROP.get(),
                ModItems.LETTUCE.get(), ModItems.LETTUCE_SEEDS.get(), lettuceCondition));

// ✅ NEW crops: Onion
        LootItemCondition.Builder onionCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.ONION_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OnionCropBlock.AGE, 3));
        this.add(ModBlocks.ONION_CROP.get(), this.createCropDrops(ModBlocks.ONION_CROP.get(),
                ModItems.ONION.get(), ModItems.ONION_SEEDS.get(), onionCondition));

// ✅ NEW crops: Spring Onion
        LootItemCondition.Builder springOnionCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.SPRING_ONION_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SpringOnionCropBlock.AGE, 3));
        this.add(ModBlocks.SPRING_ONION_CROP.get(), this.createCropDrops(ModBlocks.SPRING_ONION_CROP.get(),
                ModItems.SPRING_ONION.get(), ModItems.SPRING_ONION_SEEDS.get(), springOnionCondition));

        // ✅ NEW crops: Corn
        LootItemCondition.Builder cornCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.CORN_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CornCropBlock.AGE, 3));
        this.add(ModBlocks.CORN_CROP.get(), this.createCropDrops(ModBlocks.CORN_CROP.get(),
                ModItems.CORN.get(), ModItems.CORN_SEEDS.get(), cornCondition));

        // ✅ NEW crops: Coffee
        LootItemCondition.Builder coffeeCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.COFFEE_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CoffeeCropBlock.AGE, 3));
        this.add(ModBlocks.COFFEE_CROP.get(), this.createCropDrops(ModBlocks.COFFEE_CROP.get(),
                ModItems.COFFEE_BEANS.get(), ModItems.COFFEE_SEEDS.get(), coffeeCondition));

        // ✅ NEW crops: Tea
        LootItemCondition.Builder teaCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.TEA_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(TeaCropBlock.AGE, 3));
        this.add(ModBlocks.TEA_CROP.get(), this.createCropDrops(ModBlocks.TEA_CROP.get(),
                ModItems.TEA_LEAVES.get(), ModItems.TEA_SEEDS.get(), teaCondition));

        // ✅ NEW crops: Pineapple
        LootItemCondition.Builder pineappleCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.PINEAPPLE_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PineappleCropBlock.AGE, 3));
        this.add(ModBlocks.PINEAPPLE_CROP.get(), this.createCropDrops(ModBlocks.PINEAPPLE_CROP.get(),
                ModItems.PINEAPPLE.get(), ModItems.PINEAPPLE_SEEDS.get(), pineappleCondition));

        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        this.add(ModBlocks.BLACKCURRANT_BUSH.get(), block -> this.applyExplosionDecay(
                block,LootTable.lootTable().withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.BLACKCURRANT_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 3))
                                ).add(LootItem.lootTableItem(ModItems.BLACKCURRANT.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                ).withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.BLACKCURRANT_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 2))
                                ).add(LootItem.lootTableItem(ModItems.BLACKCURRANT.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )));

        this.add(ModBlocks.STRAWBERRY_BUSH.get(), block -> this.applyExplosionDecay(
                block,LootTable.lootTable().withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.STRAWBERRY_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 3))
                                ).add(LootItem.lootTableItem(ModItems.STRAWBERRY.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                ).withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.STRAWBERRY_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 2))
                                ).add(LootItem.lootTableItem(ModItems.STRAWBERRY.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )));

        // Yucca plant (custom loot)
        this.add(ModBlocks.YUCCA_PLANT_BLOCK.get(),
                block -> createSilkTouchOrShearsDispatchTable(block,
                        this.applyExplosionDecay(block,
                                LootItem.lootTableItem(ModItems.YUCCA_LEAVES.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F)))
                        )
                )
        );


    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

}
