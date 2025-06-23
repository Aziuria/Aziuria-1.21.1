package net.Aziuria.aziuriamod.datagen;

import net.Aziuria.aziuriamod.block.CucumberCropBlock;
import net.Aziuria.aziuriamod.block.ModBlocks;
import net.Aziuria.aziuriamod.block.RadishCropBlock;
import net.Aziuria.aziuriamod.block.TomatoCropBlock;
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
        dropSelf(ModBlocks.DEMAECATION_POST.get());
        dropSelf(ModBlocks.DEMAECATION_POST_B.get());
        dropSelf(ModBlocks.DEMAECATION_POST_C.get());
        dropSelf(ModBlocks.DEMAECATION_POST_D.get());

        dropSelf(ModBlocks.STEEL_BARREL_EMPTY.get());
        dropSelf(ModBlocks.IRON_BARREL_EMPTY.get());

        dropSelf(ModBlocks.LEAF_LITTER.get());
        dropSelf(ModBlocks.FLAX_FLOWER_BLOCK.get());

        add(ModBlocks.SULPHUR_ORE.get(),
        block -> createOreDrop(ModBlocks.SULPHUR_ORE.get(), ModItems.SULPHUR.get()));
        add(ModBlocks.DEEPSLATE_SULPHUR_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.DEEPSLATE_SULPHUR_ORE.get(), ModItems.SULPHUR.get(), 2, 5));
        add(ModBlocks.POTASSIUM_ORE.get(),
                block -> createOreDrop(ModBlocks.POTASSIUM_ORE.get(), ModItems.POTASSIUM.get()));
        add(ModBlocks.DEEPSLATE_POTASSIUM_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.DEEPSLATE_POTASSIUM_ORE.get(), ModItems.POTASSIUM.get(), 2, 5));

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
