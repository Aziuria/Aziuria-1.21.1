package net.Aziuria.aziuriamod.datagen;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.Aziuria.aziuriamod.block.ModBlocks;
import net.Aziuria.aziuriamod.item.ModItems;
import net.Aziuria.aziuriamod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> STEEL_ALLOY_MESH_SMELTABLES = List.of(ModItems.STEEL_ALLOY_MESH);
        List<ItemLike> TIN_SMELTABLES = List.of(ModItems.TIN, ModBlocks.TIN_ORE, ModBlocks.DEEPSLATE_TIN_ORE);
        List<ItemLike> SPECTRAL_SMELTABLES = List.of(ModItems.SPECTRAL_SUBSTANCE, ModBlocks.SPECTRAL_ORE, ModBlocks.DEEPSLATE_SPECTRAL_ORE);
        List<ItemLike> SULPHUR = List.of(ModBlocks.SULPHUR_ORE, ModBlocks.DEEPSLATE_SULPHUR_ORE);
        List<ItemLike> POTASSIUM = List.of(ModBlocks.POTASSIUM_ORE, ModBlocks.DEEPSLATE_POTASSIUM_ORE);
        List<ItemLike> SPINEL = List.of(ModBlocks.SPINEL_ORE, ModBlocks.DEEPSLATE_SPINEL_ORE);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_ALLOY_MESH.get())
                .pattern("   ")
                .pattern("CIC")
                .pattern("   ")
                .define('C', Items.COAL)
                .define('I', Items.RAW_IRON)
                .unlockedBy("has_raw_iron", has(Items.RAW_IRON)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.NET.get())
                .pattern("I I")
                .pattern(" I ")
                .pattern("I I")
                .define('I', ModItems.LASHING.get())
                .unlockedBy("has_lashing", has(ModItems.LASHING.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.EMPTY_JAR.get(), 3)
                .pattern("III")
                .pattern("F F")
                .pattern("FFF")
                .define('I', ModItems.TIN_INGOT.get())
                .define('F', Items.GLASS)
                .unlockedBy("has_tin_ingot", has(ModItems.TIN_INGOT.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.GLASS_BOTTLE, 3)
                .pattern(" I ")
                .pattern("F F")
                .pattern(" F ")
                .define('I', ModItems.TIN_INGOT.get())
                .define('F', Items.GLASS)
                .unlockedBy("has_tin_ingot", has(ModItems.TIN_INGOT.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.NAIL.get(), 8)
                .pattern("I")
                .pattern("F")
                .define('I', ModItems.TIN_INGOT.get())
                .define('F', ModItems.TIN_NUGGET.get())
                .unlockedBy("has_tin_ingot", has(ModItems.TIN_INGOT.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MALLET.get())
                .pattern(" I ")
                .pattern("AUA")
                .pattern(" U ")
                .define('I', ModItems.MALLET_HEAD.get())
                .define('A', ModItems.LASHING.get())
                .define('U', Items.STICK)
                .unlockedBy("has_stick", has(Items.STICK)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MALLET_HEAD.get())
                .pattern("UUU")
                .pattern("U U")
                .define('U', ItemTags.PLANKS)
                .unlockedBy("has_planks", has(ItemTags.PLANKS)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.NET_SEGMENT.get())
                .pattern("FLF")
                .pattern("FIF")
                .pattern("A A")
                .define('I', ModItems.NET.get())
                .define('A', ModItems.NAIL.get())
                .define('F', Items.STICK)
                .define('L', ModTags.KNIVES)
                .unlockedBy("has_stick", has(Items.STICK)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.OAK_FISH_TRAP.get())
                .pattern("FIF")
                .pattern("ILI")
                .pattern("FOF")
                .define('I', ModItems.NET_SEGMENT.get())
                .define('O', Blocks.OAK_LOG)
                .define('F', ModItems.LASHING.get())
                .define('L', ModTags.KNIVES)
                .unlockedBy("has_lashing", has(ModItems.LASHING.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BIRCH_FISH_TRAP.get())
                .pattern("FIF")
                .pattern("ILI")
                .pattern("FOF")
                .define('I', ModItems.NET_SEGMENT.get())
                .define('O', Blocks.BIRCH_LOG)
                .define('F', ModItems.LASHING.get())
                .define('L', ModTags.KNIVES)
                .unlockedBy("has_lashing", has(ModItems.LASHING.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SPRUCE_FISH_TRAP.get())
                .pattern("FIF")
                .pattern("ILI")
                .pattern("FOF")
                .define('I', ModItems.NET_SEGMENT.get())
                .define('O', Blocks.SPRUCE_LOG)
                .define('F', ModItems.LASHING.get())
                .define('L', ModTags.KNIVES)
                .unlockedBy("has_lashing", has(ModItems.LASHING.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.JUNGLE_FISH_TRAP.get())
                .pattern("FIF")
                .pattern("ILI")
                .pattern("FOF")
                .define('I', ModItems.NET_SEGMENT.get())
                .define('O', Blocks.JUNGLE_LOG)
                .define('F', ModItems.LASHING.get())
                .define('L', ModTags.KNIVES)
                .unlockedBy("has_lashing", has(ModItems.LASHING.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DARK_OAK_FISH_TRAP.get())
                .pattern("FIF")
                .pattern("ILI")
                .pattern("FOF")
                .define('I', ModItems.NET_SEGMENT.get())
                .define('O', Blocks.DARK_OAK_LOG)
                .define('F', ModItems.LASHING.get())
                .define('L', ModTags.KNIVES)
                .unlockedBy("has_lashing", has(ModItems.LASHING.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BAMBOO_FISH_TRAP.get())
                .pattern("FIF")
                .pattern("ILI")
                .pattern("FOF")
                .define('I', ModItems.NET_SEGMENT.get())
                .define('O', Blocks.BAMBOO_BLOCK)
                .define('F', ModItems.LASHING.get())
                .define('L', ModTags.KNIVES)
                .unlockedBy("has_lashing", has(ModItems.LASHING.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CHERRY_FISH_TRAP.get())
                .pattern("FIF")
                .pattern("ILI")
                .pattern("FOF")
                .define('I', ModItems.NET_SEGMENT.get())
                .define('O', Blocks.CHERRY_LOG)
                .define('F', ModItems.LASHING.get())
                .define('L', ModTags.KNIVES)
                .unlockedBy("has_lashing", has(ModItems.LASHING.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MANGROVE_FISH_TRAP.get())
                .pattern("FIF")
                .pattern("ILI")
                .pattern("FOF")
                .define('I', ModItems.NET_SEGMENT.get())
                .define('O', Blocks.MANGROVE_LOG)
                .define('F', ModItems.LASHING.get())
                .define('L', ModTags.KNIVES)
                .unlockedBy("has_lashing", has(ModItems.LASHING.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ACACIA_FISH_TRAP.get())
                .pattern("FIF")
                .pattern("ILI")
                .pattern("FOF")
                .define('I', ModItems.NET_SEGMENT.get())
                .define('O', Blocks.ACACIA_LOG)
                .define('F', ModItems.LASHING.get())
                .define('L', ModTags.KNIVES)
                .unlockedBy("has_lashing", has(ModItems.LASHING.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_NUGGET.get(), 9)
                .pattern("I")
                .define('I', Items.COPPER_INGOT)
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.STICK, 2)
                .pattern("I")
                .define('I', ModBlocks.STICK_A.get())
                .unlockedBy("has_stick_a", has(ModBlocks.STICK_A.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("aziuriamod", "stick_from_stick_a"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.STICK, 2)
                .pattern("I")
                .define('I', ModBlocks.STICK_B.get())
                .unlockedBy("has_stick_b", has(ModBlocks.STICK_B.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("aziuriamod", "stick_from_stick_b"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.STICK, 2)
                .pattern("I")
                .define('I', ModBlocks.STICK_C.get())
                .unlockedBy("has_stick_c", has(ModBlocks.STICK_C.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("aziuriamod", "stick_from_stick_c"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.COPPER_INGOT)
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.COPPER_NUGGET.get())
                .unlockedBy("has_copper_nugget", has(ModItems.COPPER_NUGGET.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("aziuriamod", "copper_from_nugget"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COPPER_CHAIN.get())
                .pattern(" C ")
                .pattern(" I ")
                .pattern(" C ")
                .define('C', ModItems.COPPER_NUGGET)
                .define('I', Items.COPPER_INGOT)
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_NUGGET.get(), 9)
                .pattern("I")
                .define('I', ModItems.STEEL_INGOT)
                .unlockedBy("has_steel_ingot", has(ModItems.STEEL_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SPECTRAL_SUBSTANCE.get())
                .pattern("IU")
                .define('I', ModItems.SPECTRAL_DUST)
                .define('U', Items.WATER_BUCKET)
                .unlockedBy("has_spectral_dust", has(ModItems.SPECTRAL_DUST)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_INGOT.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.STEEL_NUGGET.get())
                .unlockedBy("has_steel_nugget", has(ModItems.STEEL_NUGGET.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("aziuriamod", "steel_from_nugget"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.STEEL_CHAIN.get())
                .pattern(" C ")
                .pattern(" I ")
                .pattern(" C ")
                .define('C', ModItems.STEEL_NUGGET)
                .define('I', ModItems.STEEL_INGOT.get())
                .unlockedBy("has_steel_ingot", has(ModItems.STEEL_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.NAME_TAG)
                .pattern("C  ")
                .pattern("FIF")
                .pattern(" F ")
                .define('C', Items.PAPER)
                .define('F', Items.IRON_NUGGET)
                .define('I', Items.STRING)
                .unlockedBy("has_string", has(Items.STRING)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Blocks.COBWEB)
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', Items.STRING)
                .unlockedBy("has_string", has(Items.STRING)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.SADDLE)
                .pattern("ACA")
                .pattern("CFC")
                .define('C', Items.LEATHER)
                .define('A', ModItems.NAIL.get())
                .define('F', Items.IRON_BARS)
                .unlockedBy("has_leather", has(Items.LEATHER)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.GOLDEN_APPLE)
                .pattern("FFF")
                .pattern("FCF")
                .pattern("FFF")
                .define('C', Items.APPLE)
                .define('F', Items.GOLD_INGOT)
                .unlockedBy("has_apple", has(Items.APPLE)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.STEEL_BARS.get(), 16)
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.STEEL_INGOT.get())
                .unlockedBy("has_steel_ingot", has(ModItems.STEEL_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.COPPER_BARS.get(), 16)
                .pattern("SSS")
                .pattern("SSS")
                .define('S', Items.COPPER_INGOT)
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.APPLE_SAPLING.get(), 1)
                .pattern(" A ")
                .pattern(" D ")
                .pattern(" B ")
                .define('A', Items.APPLE)
                .define('B', Items.BUCKET)
                .define('D', Blocks.DIRT)
                .unlockedBy("has_apple", has(Items.APPLE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.PEAR_SAPLING.get(), 1)
                .pattern(" A ")
                .pattern(" D ")
                .pattern(" B ")
                .define('A', ModItems.PEAR.get())
                .define('B', Items.BUCKET)
                .define('D', Blocks.DIRT)
                .unlockedBy("has_pear", has(ModItems.PEAR.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.BANANA_SAPLING.get(), 1)
                .pattern(" A ")
                .pattern(" D ")
                .pattern(" B ")
                .define('A', ModItems.BANANA.get())
                .define('B', Items.BUCKET)
                .define('D', Blocks.DIRT)
                .unlockedBy("has_banana", has(ModItems.BANANA.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.CHERRY_SAPLING.get(), 1)
                .pattern(" A ")
                .pattern(" D ")
                .pattern(" B ")
                .define('A', ModItems.CHERRY.get())
                .define('B', Items.BUCKET)
                .define('D', Blocks.DIRT)
                .unlockedBy("has_cherry", has(ModItems.CHERRY.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.AVOCADO_SAPLING.get(), 1)
                .pattern(" A ")
                .pattern(" D ")
                .pattern(" B ")
                .define('A', ModItems.AVOCADO.get())
                .define('B', Items.BUCKET)
                .define('D', Blocks.DIRT)
                .unlockedBy("has_avocado", has(ModItems.AVOCADO.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.ORANGE_SAPLING.get(), 1)
                .pattern(" A ")
                .pattern(" D ")
                .pattern(" B ")
                .define('A', ModItems.ORANGE.get())
                .define('B', Items.BUCKET)
                .define('D', Blocks.DIRT)
                .unlockedBy("has_orange", has(ModItems.ORANGE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.FLOUR.get(), 12)
                .pattern("SSS")
                .pattern("SSS")
                .define('S', Items.WHEAT)
                .unlockedBy("has_wheat", has(Items.WHEAT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.DICED_CHICKEN.get(), 3)
                .pattern("SA")
                .define('S', Items.CHICKEN)
                .define('A', ModTags.KNIVES)
                .unlockedBy("has_chicken", has(Items.CHICKEN))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModItems.EMPTY_CUP.get(), 1)
                .pattern("S S")
                .pattern("SSS")
                .define('S', Items.PAPER)
                .unlockedBy("has_paper", has(Items.PAPER))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.BANANA_SMOOTHIE.get(), 1)
                .pattern("BMB")
                .pattern("SCI")
                .define('B', ModItems.BANANA.get())
                .define('M', ModItems.MILK_BOTTLE.get())
                .define('S', Items.SUGAR)
                .define('I', Items.ICE)
                .define('C', ModItems.EMPTY_CUP.get())
                .unlockedBy("has_empty_cup", has(ModItems.EMPTY_CUP.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.APPLE_SMOOTHIE.get(), 1)
                .pattern("BMB")
                .pattern("SCI")
                .define('B', Items.APPLE)
                .define('M', ModItems.MILK_BOTTLE.get())
                .define('S', Items.SUGAR)
                .define('I', Items.ICE)
                .define('C', ModItems.EMPTY_CUP.get())
                .unlockedBy("has_empty_cup", has(ModItems.EMPTY_CUP.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.AVOCADO_SMOOTHIE.get(), 1)
                .pattern("BMB")
                .pattern("SCI")
                .define('B', ModItems.AVOCADO.get())
                .define('M', ModItems.MILK_BOTTLE.get())
                .define('S', Items.SUGAR)
                .define('I', Items.ICE)
                .define('C', ModItems.EMPTY_CUP.get())
                .unlockedBy("has_empty_cup", has(ModItems.EMPTY_CUP.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.BLACKCURRANT_SMOOTHIE.get(), 1)
                .pattern("BMB")
                .pattern("SCI")
                .define('B', ModItems.BLACKCURRANT.get())
                .define('M', ModItems.MILK_BOTTLE.get())
                .define('S', Items.SUGAR)
                .define('I', Items.ICE)
                .define('C', ModItems.EMPTY_CUP.get())
                .unlockedBy("has_empty_cup", has(ModItems.EMPTY_CUP.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.BLUEBERRY_SMOOTHIE.get(), 1)
                .pattern("BMB")
                .pattern("SCI")
                .define('B', ModItems.BLUEBERRY.get())
                .define('M', ModItems.MILK_BOTTLE.get())
                .define('S', Items.SUGAR)
                .define('I', Items.ICE)
                .define('C', ModItems.EMPTY_CUP.get())
                .unlockedBy("has_empty_cup", has(ModItems.EMPTY_CUP.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.GOOSEBERRY_SMOOTHIE.get(), 1)
                .pattern("BMB")
                .pattern("SCI")
                .define('B', ModItems.GOOSEBERRY.get())
                .define('M', ModItems.MILK_BOTTLE.get())
                .define('S', Items.SUGAR)
                .define('I', Items.ICE)
                .define('C', ModItems.EMPTY_CUP.get())
                .unlockedBy("has_empty_cup", has(ModItems.EMPTY_CUP.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.CHERRY_SMOOTHIE.get(), 1)
                .pattern("BMB")
                .pattern("SCI")
                .define('B', ModItems.CHERRY.get())
                .define('M', ModItems.MILK_BOTTLE.get())
                .define('S', Items.SUGAR)
                .define('I', Items.ICE)
                .define('C', ModItems.EMPTY_CUP.get())
                .unlockedBy("has_empty_cup", has(ModItems.EMPTY_CUP.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.GLOWBERRY_SMOOTHIE.get(), 1)
                .pattern("BMB")
                .pattern("SCI")
                .define('B', Items.GLOW_BERRIES)
                .define('M', ModItems.MILK_BOTTLE.get())
                .define('S', Items.SUGAR)
                .define('I', Items.ICE)
                .define('C', ModItems.EMPTY_CUP.get())
                .unlockedBy("has_empty_cup", has(ModItems.EMPTY_CUP.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.MELON_SMOOTHIE.get(), 1)
                .pattern("BMB")
                .pattern("SCI")
                .define('B', Items.MELON_SLICE)
                .define('M', ModItems.MILK_BOTTLE.get())
                .define('S', Items.SUGAR)
                .define('I', Items.ICE)
                .define('C', ModItems.EMPTY_CUP.get())
                .unlockedBy("has_empty_cup", has(ModItems.EMPTY_CUP.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.ORANGE_SMOOTHIE.get(), 1)
                .pattern("BMB")
                .pattern("SCI")
                .define('B', ModItems.ORANGE.get())
                .define('M', ModItems.MILK_BOTTLE.get())
                .define('S', Items.SUGAR)
                .define('I', Items.ICE)
                .define('C', ModItems.EMPTY_CUP.get())
                .unlockedBy("has_empty_cup", has(ModItems.EMPTY_CUP.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.PEAR_SMOOTHIE.get(), 1)
                .pattern("BMB")
                .pattern("SCI")
                .define('B', ModItems.PEAR.get())
                .define('M', ModItems.MILK_BOTTLE.get())
                .define('S', Items.SUGAR)
                .define('I', Items.ICE)
                .define('C', ModItems.EMPTY_CUP.get())
                .unlockedBy("has_empty_cup", has(ModItems.EMPTY_CUP.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.PINEAPPLE_SMOOTHIE.get(), 1)
                .pattern("BMB")
                .pattern("SCI")
                .define('B', ModItems.PINEAPPLE.get())
                .define('M', ModItems.MILK_BOTTLE.get())
                .define('S', Items.SUGAR)
                .define('I', Items.ICE)
                .define('C', ModItems.EMPTY_CUP.get())
                .unlockedBy("has_empty_cup", has(ModItems.EMPTY_CUP.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.STRAWBERRY_SMOOTHIE.get(), 1)
                .pattern("BMB")
                .pattern("SCI")
                .define('B', ModItems.STRAWBERRY.get())
                .define('M', ModItems.MILK_BOTTLE.get())
                .define('S', Items.SUGAR)
                .define('I', Items.ICE)
                .define('C', ModItems.EMPTY_CUP.get())
                .unlockedBy("has_empty_cup", has(ModItems.EMPTY_CUP.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.SWEETBERRY_SMOOTHIE.get(), 1)
                .pattern("BMB")
                .pattern("SCI")
                .define('B', Items.SWEET_BERRIES)
                .define('M', ModItems.MILK_BOTTLE.get())
                .define('S', Items.SUGAR)
                .define('I', Items.ICE)
                .define('C', ModItems.EMPTY_CUP.get())
                .unlockedBy("has_empty_cup", has(ModItems.EMPTY_CUP.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.BATTERED_CHICKEN.get(), 3)
                .pattern("SSS")
                .pattern(" A ")
                .pattern(" F ")
                .define('S', ModItems.DICED_CHICKEN.get())
                .define('A', ModItems.FLOUR.get())
                .define('F', Items.WATER_BUCKET)
                .unlockedBy("has_diced_chicken", has(ModItems.DICED_CHICKEN.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.BEEF_BURGER.get(), 1)
                .pattern("QAR")
                .pattern("SOF")
                .pattern("CAH")
                .define('S', ModItems.TOMATO.get())
                .define('R', ModTags.KNIVES)
                .define('O', ModItems.LETTUCE.get())
                .define('Q', ModItems.CHEESE.get())
                .define('H', ModItems.ONION.get())
                .define('C', ModItems.CUCUMBER.get())
                .define('A', Items.BREAD)
                .define('F', Items.COOKED_BEEF)
                .unlockedBy("has_bread", has(Items.BREAD))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.CHEESEBURGER.get(), 1)
                .pattern(" AR")
                .pattern(" QF")
                .pattern(" A ")
                .define('R', ModTags.KNIVES)
                .define('Q', ModItems.CHEESE.get())
                .define('A', Items.BREAD)
                .define('F', Items.COOKED_BEEF)
                .unlockedBy("has_bread", has(Items.BREAD))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.PORKCHOP_BURGER.get(), 1)
                .pattern(" AR")
                .pattern(" QF")
                .pattern(" A ")
                .define('R', ModTags.KNIVES)
                .define('Q', ModItems.LETTUCE.get())
                .define('A', Items.BREAD)
                .define('F', Items.COOKED_PORKCHOP)
                .unlockedBy("has_bread", has(Items.BREAD))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.PANCAKE_DOUGH.get(), 6)
                .pattern("SA ")
                .pattern("FQ ")
                .pattern("   ")
                .define('S', ModItems.FLOUR.get())
                .define('A', Items.EGG)
                .define('F', Items.MILK_BUCKET)
                .define('Q', Items.SUGAR)
                .unlockedBy("has_sugar", has(Items.SUGAR))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.CHEESE.get(), 4)
                .pattern(" F ")
                .define('F', Items.MILK_BUCKET)
                .unlockedBy("has_milk_bucket", has(Items.MILK_BUCKET))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.FRENCH_FRIES.get(), 1)
                .pattern("SF")
                .define('S', Items.BAKED_POTATO)
                .define('F', ModTags.KNIVES)
                .unlockedBy("has_baked_potato", has(Items.BAKED_POTATO))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WOOD_KNIFE.get())
                .pattern("  A")
                .pattern(" A ")
                .pattern("FS ")
                .define('A', ItemTags.PLANKS)
                .define('S', ModItems.NAIL.get())
                .define('F', Items.STICK)
                .unlockedBy("has_stick", has(Items.STICK))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("aziuriamod", "wooden_knife_from_planks"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STONE_KNIFE.get())
                .pattern("  A")
                .pattern(" A ")
                .pattern("FS ")
                .define('A', Items.COBBLESTONE)
                .define('F', Items.STICK)
                .define('S', ModItems.NAIL.get())
                .unlockedBy("has_stick", has(Items.STICK))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("aziuriamod", "stone_knife_from_cobble"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_KNIFE.get())
                .pattern("  A")
                .pattern(" A ")
                .pattern("FS ")
                .define('A', Items.COPPER_INGOT)
                .define('F', Items.STICK)
                .define('S', ModItems.NAIL.get())
                .unlockedBy("has_stick", has(Items.STICK))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("aziuriamod", "copper_knife_from_copper"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_KNIFE.get())
                .pattern("  A")
                .pattern(" A ")
                .pattern("FS ")
                .define('A', Items.IRON_INGOT)
                .define('F', Items.STICK)
                .define('S', ModItems.NAIL.get())
                .unlockedBy("has_stick", has(Items.STICK))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("aziuriamod", "iron_knife_from_iron"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GOLD_KNIFE.get())
                .pattern("  A")
                .pattern(" A ")
                .pattern("FS ")
                .define('A', Items.GOLD_INGOT)
                .define('F', Items.STICK)
                .define('S', ModItems.NAIL.get())
                .unlockedBy("has_stick", has(Items.STICK))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("aziuriamod", "golden_knife_from_gold"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_KNIFE.get())
                .pattern("  A")
                .pattern(" A ")
                .pattern("FS ")
                .define('A', ModItems.STEEL_INGOT.get())
                .define('F', Items.STICK)
                .define('S', ModItems.NAIL.get())
                .unlockedBy("has_stick", has(Items.STICK))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("aziuriamod", "steel_knife_from_steel"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SPINEL_KNIFE.get())
                .pattern("  S")
                .pattern(" S ")
                .pattern("FA ")
                .define('S', ModItems.SPINEL.get())
                .define('F', Items.STICK)
                .define('A', ModItems.NAIL.get())
                .unlockedBy("has_stick", has(Items.STICK))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("aziuriamod", "spinel_knife_from_spinel"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DIAMOND_KNIFE.get())
                .pattern("  A")
                .pattern(" A ")
                .pattern("FS ")
                .define('A', Items.DIAMOND)
                .define('F', Items.STICK)
                .define('S', ModItems.NAIL.get())
                .unlockedBy("has_stick", has(Items.STICK))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("aziuriamod", "diamond_knife_from_diamond"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.NETHERITE_KNIFE.get())
                .pattern("  A")
                .pattern(" A ")
                .pattern("FS ")
                .define('A', Items.NETHERITE_INGOT)
                .define('F', Items.STICK)
                .define('S', ModItems.NAIL.get())
                .unlockedBy("has_stick", has(Items.STICK))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("aziuriamod", "netherite_knife_from_netherite"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MINER_BENCH.get())
                .pattern("SSA")
                .pattern("FOF")
                .pattern("FOF")
                .define('S', ModBlocks.STEEL_BLOCK.get())
                .define('F', Items.OAK_FENCE)
                .define('O', Items.OAK_LOG)
                .define('A', ModItems.STEEL_PICKAXE.get())
                .unlockedBy("has_steel_block", has(ModBlocks.STEEL_BLOCK.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.WOODCUTTER_BENCH.get())
                .pattern("SSD")
                .pattern("FOF")
                .pattern("FOF")
                .define('S', ModBlocks.STEEL_BLOCK.get())
                .define('F', Items.OAK_FENCE)
                .define('O', Items.OAK_LOG)
                .define('D', ModItems.STEEL_AXE.get())
                .unlockedBy("has_steel_block", has(ModBlocks.STEEL_BLOCK.get()))
                .save(recipeOutput);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_PICKAXE.get())
                .pattern("BBB")
                .pattern(" K ")
                .pattern(" K ")
                .define('K', Items.STICK)
                .define('B', ModItems.STEEL_INGOT.get())
                .unlockedBy("has_steel_ingot", has(ModItems.STEEL_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_PICKAXE.get())
                .pattern("BBB")
                .pattern(" K ")
                .pattern(" K ")
                .define('K', Items.STICK)
                .define('B', Items.COPPER_INGOT)
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SPINEL_PICKAXE.get())
                .pattern("BBB")
                .pattern(" K ")
                .pattern(" K ")
                .define('K', Items.STICK)
                .define('B', ModItems.SPINEL.get())
                .unlockedBy("has_spinel", has(ModItems.SPINEL)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DEMAECATION_POST.get())
                .pattern(" W ")
                .pattern(" B ")
                .pattern("GGG")
                .define('G', Blocks.GRAY_CONCRETE)
                .define('W', Blocks.WHITE_CONCRETE)
                .define('B', Blocks.BLACK_CONCRETE)
                .unlockedBy("has_gray_concrete", has(Blocks.GRAY_CONCRETE)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DEMAECATION_POST_B.get())
                .pattern(" R ")
                .pattern(" B ")
                .pattern("GGG")
                .define('G', Blocks.GRAY_CONCRETE)
                .define('R', Blocks.RED_CONCRETE)
                .define('B', Blocks.GREEN_CONCRETE)
                .unlockedBy("has_gray_concrete", has(Blocks.GRAY_CONCRETE)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DEMAECATION_POST_C.get())
                .pattern(" W ")
                .pattern(" Y ")
                .pattern("GGG")
                .define('G', Blocks.GRAY_CONCRETE)
                .define('W', Blocks.LIGHT_BLUE_CONCRETE)
                .define('Y', Blocks.YELLOW_CONCRETE)
                .unlockedBy("has_gray_concrete", has(Blocks.GRAY_CONCRETE)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DEMAECATION_POST_D.get())
                .pattern(" W ")
                .pattern(" S ")
                .pattern("GGG")
                .define('G', Blocks.GRAY_CONCRETE)
                .define('W', Blocks.WHITE_CONCRETE)
                .define('S', Blocks.RED_CONCRETE)
                .unlockedBy("has_gray_concrete", has(Blocks.GRAY_CONCRETE)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DEMAECATION_POST_E.get())
                .pattern(" W ")
                .pattern(" Y ")
                .pattern("GGG")
                .define('G', Blocks.GRAY_CONCRETE)
                .define('W', Blocks.LIGHT_BLUE_CONCRETE)
                .define('Y', Blocks.WHITE_CONCRETE)
                .unlockedBy("has_gray_concrete", has(Blocks.GRAY_CONCRETE)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLACKSTONE_GRAVESTONE_A.get())
                .pattern(" G ")
                .pattern("GGG")
                .pattern("GGG")
                .define('G', Blocks.BLACKSTONE)
                .unlockedBy("has_blackstone", has(Blocks.BLACKSTONE)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLACKSTONE_GRAVESTONE_B.get())
                .pattern(" G ")
                .pattern("GGG")
                .pattern(" G ")
                .define('G', Blocks.BLACKSTONE)
                .unlockedBy("has_blackstone", has(Blocks.BLACKSTONE)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COBBLESTONE_GRAVESTONE_A.get())
                .pattern(" G ")
                .pattern("GGG")
                .pattern("GGG")
                .define('G', Blocks.COBBLESTONE)
                .unlockedBy("has_cobblestone", has(Blocks.COBBLESTONE)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COBBLESTONE_GRAVESTONE_B.get())
                .pattern(" G ")
                .pattern("GGG")
                .pattern(" G ")
                .define('G', Blocks.COBBLESTONE)
                .unlockedBy("has_cobblestone", has(Blocks.COBBLESTONE)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_AXE.get())
                .pattern("BB")
                .pattern("BK")
                .pattern(" K")
                .define('K', Items.STICK)
                .define('B', ModItems.STEEL_INGOT.get())
                .unlockedBy("has_steel_ingot", has(ModItems.STEEL_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_AXE.get())
                .pattern("BB")
                .pattern("BK")
                .pattern(" K")
                .define('K', Items.STICK)
                .define('B', Items.COPPER_INGOT)
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SPINEL_AXE.get())
                .pattern("BB")
                .pattern("BK")
                .pattern(" K")
                .define('K', Items.STICK)
                .define('B', ModItems.SPINEL.get())
                .unlockedBy("has_spinel", has(ModItems.SPINEL)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_SHOVEL.get())
                .pattern("B")
                .pattern("K")
                .pattern("K")
                .define('K', Items.STICK)
                .define('B', ModItems.STEEL_INGOT.get())
                .unlockedBy("has_steel_ingot", has(ModItems.STEEL_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_SHOVEL.get())
                .pattern("B")
                .pattern("K")
                .pattern("K")
                .define('K', Items.STICK)
                .define('B', Items.COPPER_INGOT)
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SPINEL_SHOVEL.get())
                .pattern("B")
                .pattern("K")
                .pattern("K")
                .define('K', Items.STICK)
                .define('B', ModItems.SPINEL.get())
                .unlockedBy("has_spinel", has(ModItems.SPINEL)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_SWORD.get())
                .pattern("B")
                .pattern("B")
                .pattern("K")
                .define('K', Items.STICK)
                .define('B', ModItems.STEEL_INGOT.get())
                .unlockedBy("has_steel_ingot", has(ModItems.STEEL_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_SWORD.get())
                .pattern("B")
                .pattern("B")
                .pattern("K")
                .define('K', Items.STICK)
                .define('B', Items.COPPER_INGOT)
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SPINEL_SWORD.get())
                .pattern("B")
                .pattern("B")
                .pattern("K")
                .define('K', Items.STICK)
                .define('B', ModItems.SPINEL.get())
                .unlockedBy("has_spinel", has(ModItems.SPINEL)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_HOE.get())
                .pattern("BB")
                .pattern(" K")
                .pattern(" K")
                .define('K', Items.STICK)
                .define('B', ModItems.STEEL_INGOT.get())
                .unlockedBy("has_steel_ingot", has(ModItems.STEEL_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_HOE.get())
                .pattern("BB")
                .pattern(" K")
                .pattern(" K")
                .define('K', Items.STICK)
                .define('B', Items.COPPER_INGOT)
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SPINEL_HOE.get())
                .pattern("BB")
                .pattern(" K")
                .pattern(" K")
                .define('K', Items.STICK)
                .define('B', ModItems.SPINEL.get())
                .unlockedBy("has_spinel", has(ModItems.SPINEL)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_HELMET.get())
                .pattern("BBB")
                .pattern("B B")
                .define('B', ModItems.STEEL_INGOT.get())
                .unlockedBy("has_steel_ingot", has(ModItems.STEEL_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_CHESTPLATE.get())
                .pattern("B B")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.STEEL_INGOT.get())
                .unlockedBy("has_steel_ingot", has(ModItems.STEEL_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_LEGGINGS.get())
                .pattern("BBB")
                .pattern("B B")
                .pattern("B B")
                .define('B', ModItems.STEEL_INGOT.get())
                .unlockedBy("has_steel_ingot", has(ModItems.STEEL_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_BOOTS.get())
                .pattern("B B")
                .pattern("B B")
                .define('B', ModItems.STEEL_INGOT.get())
                .unlockedBy("has_steel_ingot", has(ModItems.STEEL_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SPINEL_HELMET.get())
                .pattern("BBB")
                .pattern("B B")
                .define('B', ModItems.SPINEL.get())
                .unlockedBy("has_spinel", has(ModItems.SPINEL)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SPINEL_CHESTPLATE.get())
                .pattern("B B")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.SPINEL.get())
                .unlockedBy("has_spinel", has(ModItems.SPINEL)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SPINEL_LEGGINGS.get())
                .pattern("BBB")
                .pattern("B B")
                .pattern("B B")
                .define('B', ModItems.SPINEL.get())
                .unlockedBy("has_spinel", has(ModItems.SPINEL)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SPINEL_BOOTS.get())
                .pattern("B B")
                .pattern("B B")
                .define('B', ModItems.SPINEL.get())
                .unlockedBy("has_spinel", has(ModItems.SPINEL)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_HELMET.get())
                .pattern("BBB")
                .pattern("B B")
                .define('B', Items.COPPER_INGOT)
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_CHESTPLATE.get())
                .pattern("B B")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', Items.COPPER_INGOT)
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_LEGGINGS.get())
                .pattern("BBB")
                .pattern("B B")
                .pattern("B B")
                .define('B', Items.COPPER_INGOT)
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_BOOTS.get())
                .pattern("B B")
                .pattern("B B")
                .define('B', Items.COPPER_INGOT)
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.GUNPOWDER)
                .pattern("PSP")
                .pattern("SPS")
                .pattern("PSP")
                .define('P', ModItems.POTASSIUM.get())
                .define('S', ModItems.SULPHUR.get())
                .unlockedBy("has_potassium", has(ModItems.POTASSIUM.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.LASHING.get(), 2)
                .pattern("SSS")
                .define('S', ModItems.FLAX_FLOWER.get())
                .unlockedBy("has_flax_flower", has(ModItems.FLAX_FLOWER.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.LASHING.get(), 2)
                .pattern("BBB")
                .define('B', ModItems.YUCCA_LEAVES)
                .unlockedBy("has_yucca_leaves", has(ModItems.YUCCA_LEAVES.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("aziuriamod", "lashing_from_yucca"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.STRING, 2)
                .pattern("SSS")
                .define('S', ModItems.LASHING.get())
                .unlockedBy("has_lashing", has(ModItems.LASHING.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SACK_ITEM.get())
                .pattern("L L")
                .pattern("LLL")
                .define('L', Items.LEATHER)
                .unlockedBy("has_leather", has(Items.LEATHER)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.STEEL_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.STEEL_INGOT.get())
                .unlockedBy("has_steel_ingot", has(ModItems.STEEL_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Blocks.SPAWNER)
                .pattern("WWW")
                .pattern("W W")
                .pattern("WWW")
                .define('W', ModBlocks.STEEL_BARS)
                .unlockedBy("has_steel_bars", has(ModBlocks.STEEL_BARS))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.ALLAY_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.LIGHT_BLUE_DYE)
                .define('B', Items.BLUE_DYE)
                .define('H', Items.ENDER_PEARL)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModItems.WORM_SPAWN_EGG.get())
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.BROWN_DYE)
                .define('B', Items.ORANGE_DYE)
                .define('H', ModItems.WORM.get())
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.ARMADILLO_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.BROWN_DYE)
                .define('B', Items.PINK_DYE)
                .define('H', Items.ARMADILLO_SCUTE)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.AXOLOTL_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.YELLOW_DYE)
                .define('B', Items.BLUE_DYE)
                .define('H', Items.KELP)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.BAT_SPAWN_EGG)
                .pattern("B G")
                .pattern(" W ")
                .pattern("G B")
                .define('W', Items.EGG)
                .define('G', Items.GRAY_DYE)
                .define('B', Items.BLACK_DYE)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.BEE_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.YELLOW_DYE)
                .define('B', Items.BLACK_DYE)
                .define('H', Items.HONEY_BOTTLE)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.BLAZE_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.YELLOW_DYE)
                .define('B', Items.LIGHT_GRAY_DYE)
                .define('H', Items.BLAZE_ROD)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.BOGGED_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.BROWN_DYE)
                .define('B', Items.YELLOW_DYE)
                .define('H', Items.BONE)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.BREEZE_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.WHITE_DYE)
                .define('B', Items.LIGHT_GRAY_DYE)
                .define('H', Items.BREEZE_ROD)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.CAMEL_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.BROWN_DYE)
                .define('B', Items.YELLOW_DYE)
                .define('H', Items.SADDLE)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.CAT_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.YELLOW_DYE)
                .define('B', Items.BROWN_DYE)
                .define('H', Items.SALMON)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.CAVE_SPIDER_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.RED_DYE)
                .define('B', Items.BLACK_DYE)
                .define('H', Items.SPIDER_EYE)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.CHICKEN_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.RED_DYE)
                .define('B', Items.GRAY_DYE)
                .define('H', Items.CHICKEN)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.COD_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.YELLOW_DYE)
                .define('B', Items.ORANGE_DYE)
                .define('H', Items.COD)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.COW_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.GRAY_DYE)
                .define('B', Items.BROWN_DYE)
                .define('H', Items.BEEF)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.CREEPER_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.GREEN_DYE)
                .define('B', Items.BROWN_DYE)
                .define('H', Items.GUNPOWDER)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.DOLPHIN_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.GREEN_DYE)
                .define('B', Items.BLUE_DYE)
                .define('H', Items.KELP)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.DONKEY_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.YELLOW_DYE)
                .define('B', Items.BROWN_DYE)
                .define('H', Items.WHEAT)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.DROWNED_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.GREEN_DYE)
                .define('B', Items.LIGHT_BLUE_DYE)
                .define('H', Items.NAUTILUS_SHELL)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.ELDER_GUARDIAN_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.GRAY_DYE)
                .define('B', Items.LIGHT_GRAY_DYE)
                .define('H', Items.SPONGE)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.ENDERMAN_SPAWN_EGG)
                .pattern("BHB")
                .pattern("HWH")
                .pattern("BHB")
                .define('W', Items.EGG)
                .define('B', Items.BLACK_DYE)
                .define('H', Items.ENDER_PEARL)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.ENDERMITE_SPAWN_EGG)
                .pattern("BFG")
                .pattern("HWH")
                .pattern("GFB")
                .define('W', Items.EGG)
                .define('G', Items.GRAY_DYE)
                .define('B', Items.BLACK_DYE)
                .define('H', Items.ENDER_PEARL)
                .define('F', Items.LEATHER)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.EVOKER_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.GRAY_DYE)
                .define('B', Items.BLACK_DYE)
                .define('H', Items.TOTEM_OF_UNDYING)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.FOX_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.YELLOW_DYE)
                .define('B', Items.ORANGE_DYE)
                .define('H', Items.SNOWBALL)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.FROG_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.YELLOW_DYE)
                .define('B', Items.BROWN_DYE)
                .define('H', Items.LILY_PAD)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.GHAST_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.WHITE_DYE)
                .define('B', Items.LIGHT_GRAY_DYE)
                .define('H', Items.GHAST_TEAR)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.GOAT_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.BROWN_DYE)
                .define('B', Items.LIGHT_GRAY_DYE)
                .define('H', Items.GOAT_HORN)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.GLOW_SQUID_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.BLUE_DYE)
                .define('B', Items.GREEN_DYE)
                .define('H', Items.GLOW_INK_SAC)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.GUARDIAN_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.ORANGE_DYE)
                .define('B', Items.GREEN_DYE)
                .define('H', Items.PRISMARINE_SHARD)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.HOGLIN_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.BROWN_DYE)
                .define('B', Items.BLACK_DYE)
                .define('H', Items.COOKED_PORKCHOP)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.HORSE_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.YELLOW_DYE)
                .define('B', Items.BROWN_DYE)
                .define('H', Items.LEATHER)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.HUSK_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.GRAY_DYE)
                .define('B', Items.BROWN_DYE)
                .define('H', Items.ROTTEN_FLESH)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.IRON_GOLEM_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.GREEN_DYE)
                .define('B', Items.LIGHT_GRAY_DYE)
                .define('H', Items.IRON_INGOT)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.LLAMA_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.LIGHT_GRAY_DYE)
                .define('B', Items.BROWN_DYE)
                .define('H', Items.LEATHER)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.MAGMA_CUBE_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.YELLOW_DYE)
                .define('B', Items.BLACK_DYE)
                .define('H', Items.MAGMA_BLOCK)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.MOOSHROOM_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.RED_DYE)
                .define('B', Items.GRAY_DYE)
                .define('H', Items.RED_MUSHROOM)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.MULE_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.BROWN_DYE)
                .define('B', Items.BLACK_DYE)
                .define('H', Items.SADDLE)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.OCELOT_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.YELLOW_DYE)
                .define('B', Items.BLACK_DYE)
                .define('H', Items.SALMON)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.PANDA_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.WHITE_DYE)
                .define('B', Items.BLACK_DYE)
                .define('H', Items.BAMBOO)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.PARROT_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.RED_DYE)
                .define('B', Items.GREEN_DYE)
                .define('H', Items.WHEAT_SEEDS)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.PHANTOM_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.BLUE_DYE)
                .define('B', Items.GREEN_DYE)
                .define('H', Items.PHANTOM_MEMBRANE)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.PIG_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.PINK_DYE)
                .define('B', Items.RED_DYE)
                .define('H', Items.PORKCHOP)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.PIGLIN_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.PINK_DYE)
                .define('B', Items.GREEN_DYE)
                .define('H', Items.COOKED_PORKCHOP)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.PIGLIN_BRUTE_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.YELLOW_DYE)
                .define('B', Items.BROWN_DYE)
                .define('H', Items.COOKED_PORKCHOP)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.PILLAGER_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.GRAY_DYE)
                .define('B', Items.BROWN_DYE)
                .define('H', Items.BOW)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.POLAR_BEAR_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.GRAY_DYE)
                .define('B', Items.WHITE_DYE)
                .define('H', Items.SNOWBALL)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.PUFFERFISH_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.YELLOW_DYE)
                .define('B', Items.BLUE_DYE)
                .define('H', Items.PUFFERFISH)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.RABBIT_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.BROWN_DYE)
                .define('B', Items.BLACK_DYE)
                .define('H', Items.RABBIT)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.RAVAGER_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.GRAY_DYE)
                .define('B', Items.BROWN_DYE)
                .define('H', Items.SADDLE)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.SALMON_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.RED_DYE)
                .define('B', Items.GRAY_DYE)
                .define('H', Items.SALMON)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.SHEEP_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.LIGHT_GRAY_DYE)
                .define('B', Items.PINK_DYE)
                .define('H', Items.MUTTON)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.SHULKER_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.PINK_DYE)
                .define('B', Items.PURPLE_DYE)
                .define('H', Items.SHULKER_SHELL)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.SILVERFISH_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.GRAY_DYE)
                .define('B', Items.BLACK_DYE)
                .define('H', Items.RAW_IRON)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.SKELETON_HORSE_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.LIGHT_GRAY_DYE)
                .define('B', Items.BROWN_DYE)
                .define('H', Items.BONE)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.SKELETON_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.WHITE_DYE)
                .define('B', Items.BLACK_DYE)
                .define('H', Items.BONE)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.SLIME_SPAWN_EGG)
                .pattern("GHG")
                .pattern("HWH")
                .pattern("GHG")
                .define('W', Items.EGG)
                .define('G', Items.GREEN_DYE)
                .define('H', Items.SLIME_BALL)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.SNIFFER_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.GREEN_DYE)
                .define('B', Items.RED_DYE)
                .define('H', Items.SNIFFER_EGG)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.SNOW_GOLEM_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.WHITE_DYE)
                .define('B', Items.LIGHT_BLUE_DYE)
                .define('H', Items.SNOW)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.SPIDER_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.RED_DYE)
                .define('B', Items.BLACK_DYE)
                .define('H', Items.COBWEB)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.SQUID_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.BLUE_DYE)
                .define('B', Items.BLACK_DYE)
                .define('H', Items.INK_SAC)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.STRAY_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.BLUE_DYE)
                .define('B', Items.LIGHT_GRAY_DYE)
                .define('H', Items.FERMENTED_SPIDER_EYE)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.STRIDER_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.RED_DYE)
                .define('B', Items.BLACK_DYE)
                .define('H', Items.STRING)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.TADPOLE_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.BLACK_DYE)
                .define('B', Items.BROWN_DYE)
                .define('H', Items.LILY_PAD)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.TRADER_LLAMA_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.BLUE_DYE)
                .define('B', Items.YELLOW_DYE)
                .define('H', Items.LEAD)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.TROPICAL_FISH_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.GRAY_DYE)
                .define('B', Items.ORANGE_DYE)
                .define('H', Items.TROPICAL_FISH)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.TURTLE_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.BLUE_DYE)
                .define('B', Items.LIGHT_GRAY_DYE)
                .define('H', Items.TURTLE_EGG)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.VEX_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.GRAY_DYE)
                .define('B', Items.LIGHT_GRAY_DYE)
                .define('H', Items.IRON_INGOT)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.VILLAGER_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.BROWN_DYE)
                .define('B', Items.ORANGE_DYE)
                .define('H', Items.BREAD)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.VINDICATOR_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.BLUE_DYE)
                .define('B', Items.GRAY_DYE)
                .define('H', Items.WOODEN_AXE)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.WANDERING_TRADER_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.BLUE_DYE)
                .define('B', Items.YELLOW_DYE)
                .define('H', Items.LEAD)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.WITCH_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.GREEN_DYE)
                .define('B', Items.BROWN_DYE)
                .define('H', Items.POTION)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.WITHER_SKELETON_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.GRAY_DYE)
                .define('B', Items.BLACK_DYE)
                .define('H', Items.WITHER_ROSE)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.WITHER_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.LIGHT_GRAY_DYE)
                .define('B', Items.BLACK_DYE)
                .define('H', Items.WITHER_SKELETON_SKULL)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.WOLF_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.WHITE_DYE)
                .define('B', Items.BROWN_DYE)
                .define('H', Items.BONE)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.WARDEN_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.BLUE_DYE)
                .define('B', Items.LIGHT_BLUE_DYE)
                .define('H', Items.SCULK)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.ZOMBIE_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.LIGHT_BLUE_DYE)
                .define('B', Items.GREEN_DYE)
                .define('H', Items.ROTTEN_FLESH)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.ZOGLIN_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.LIGHT_BLUE_DYE)
                .define('B', Items.BLACK_DYE)
                .define('H', Items.PORKCHOP)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.ZOMBIE_HORSE_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.BROWN_DYE)
                .define('B', Items.GREEN_DYE)
                .define('H', Items.ROTTEN_FLESH)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.ZOMBIFIED_PIGLIN_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.PINK_DYE)
                .define('B', Items.GREEN_DYE)
                .define('H', Items.ROTTEN_FLESH)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.ZOMBIE_VILLAGER_SPAWN_EGG)
                .pattern("BHG")
                .pattern("HWH")
                .pattern("GHB")
                .define('W', Items.EGG)
                .define('G', Items.BROWN_DYE)
                .define('B', Items.GREEN_DYE)
                .define('H', Items.ROTTEN_FLESH)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(recipeOutput);


        // OAK Shelf
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.OAK_SHELF.get())
                .pattern("WSW")
                .pattern("WSW")
                .pattern("WSW")
                .define('W', Items.OAK_LOG)
                .define('S', Items.OAK_SLAB)
                .unlockedBy("has_oak_slab", has(Items.OAK_SLAB))
                .save(recipeOutput);

// BIRCH Shelf
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.BIRCH_SHELF.get())
                .pattern("WSW")
                .pattern("WSW")
                .pattern("WSW")
                .define('W', Items.BIRCH_LOG)
                .define('S', Items.BIRCH_SLAB)
                .unlockedBy("has_birch_slab", has(Items.BIRCH_SLAB))
                .save(recipeOutput);

// SPRUCE Shelf
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.SPRUCE_SHELF.get())
                .pattern("WSW")
                .pattern("WSW")
                .pattern("WSW")
                .define('W', Items.SPRUCE_LOG)
                .define('S', Items.SPRUCE_SLAB)
                .unlockedBy("has_spruce_slab", has(Items.SPRUCE_SLAB))
                .save(recipeOutput);

// JUNGLE Shelf
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.JUNGLE_SHELF.get())
                .pattern("WSW")
                .pattern("WSW")
                .pattern("WSW")
                .define('W', Items.JUNGLE_LOG)
                .define('S', Items.JUNGLE_SLAB)
                .unlockedBy("has_jungle_slab", has(Items.JUNGLE_SLAB))
                .save(recipeOutput);

// DARK OAK Shelf
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.DARK_OAK_SHELF.get())
                .pattern("WSW")
                .pattern("WSW")
                .pattern("WSW")
                .define('W', Items.DARK_OAK_LOG)
                .define('S', Items.DARK_OAK_SLAB)
                .unlockedBy("has_dark_oak_slab", has(Items.DARK_OAK_SLAB))
                .save(recipeOutput);

// BAMBOO Shelf (note: bamboo uses bamboo blocks and slabs)
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.BAMBOO_SHELF.get())
                .pattern("WSW")
                .pattern("WSW")
                .pattern("WSW")
                .define('W', Items.BAMBOO_BLOCK)
                .define('S', Items.BAMBOO_SLAB)
                .unlockedBy("has_bamboo_slab", has(Items.BAMBOO_SLAB))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.CHERRY_SHELF.get())
                .pattern("WSW")
                .pattern("WSW")
                .pattern("WSW")
                .define('W', Items.CHERRY_LOG)
                .define('S', Items.CHERRY_SLAB)
                .unlockedBy("has_cherry_slab", has(Items.CHERRY_SLAB))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.ACACIA_SHELF.get())
                .pattern("WSW")
                .pattern("WSW")
                .pattern("WSW")
                .define('W', Items.ACACIA_LOG)
                .define('S', Items.ACACIA_SLAB)
                .unlockedBy("has_acacia_slab", has(Items.ACACIA_SLAB))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.MANGROVE_SHELF.get())
                .pattern("WSW")
                .pattern("WSW")
                .pattern("WSW")
                .define('W', Items.MANGROVE_LOG)
                .define('S', Items.MANGROVE_SLAB)
                .unlockedBy("has_mangrove_slab", has(Items.MANGROVE_SLAB))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.OAK_STORAGE.get())
                .pattern("A A")
                .pattern("WTW")
                .pattern("WSW")
                .define('W', Items.OAK_LOG)
                .define('S', Items.OAK_SLAB)
                .define('A', ModItems.NAIL.get())
                .define('T', Items.STICK)
                .unlockedBy("has_oak_slab", has(Items.OAK_SLAB))
                .save(recipeOutput);

        // Birch Storage
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.BIRCH_STORAGE.get())
                .pattern("A A")
                .pattern("WTW")
                .pattern("WSW")
                .define('W', Items.BIRCH_LOG)
                .define('A', ModItems.NAIL.get())
                .define('S', Items.BIRCH_SLAB)
                .define('T', Items.STICK)
                .unlockedBy("has_birch_slab", has(Items.BIRCH_SLAB))
                .save(recipeOutput);

// Spruce Storage
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.SPRUCE_STORAGE.get())
                .pattern("A A")
                .pattern("WTW")
                .pattern("WSW")
                .define('W', Items.SPRUCE_LOG)
                .define('S', Items.SPRUCE_SLAB)
                .define('A', ModItems.NAIL.get())
                .define('T', Items.STICK)
                .unlockedBy("has_spruce_slab", has(Items.SPRUCE_SLAB))
                .save(recipeOutput);

// Jungle Storage
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.JUNGLE_STORAGE.get())
                .pattern("A A")
                .pattern("WTW")
                .pattern("WSW")
                .define('W', Items.JUNGLE_LOG)
                .define('S', Items.JUNGLE_SLAB)
                .define('A', ModItems.NAIL.get())
                .define('T', Items.STICK)
                .unlockedBy("has_jungle_slab", has(Items.JUNGLE_SLAB))
                .save(recipeOutput);

// Dark Oak Storage
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.DARK_OAK_STORAGE.get())
                .pattern("A A")
                .pattern("WTW")
                .pattern("WSW")
                .define('W', Items.DARK_OAK_LOG)
                .define('S', Items.DARK_OAK_SLAB)
                .define('A', ModItems.NAIL.get())
                .define('T', Items.STICK)
                .unlockedBy("has_dark_oak_slab", has(Items.DARK_OAK_SLAB))
                .save(recipeOutput);

// Acacia Storage
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.ACACIA_STORAGE.get())
                .pattern("A A")
                .pattern("WTW")
                .pattern("WSW")
                .define('W', Items.ACACIA_LOG)
                .define('S', Items.ACACIA_SLAB)
                .define('A', ModItems.NAIL.get())
                .define('T', Items.STICK)
                .unlockedBy("has_acacia_slab", has(Items.ACACIA_SLAB))
                .save(recipeOutput);

        // Cherry Storage
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.CHERRY_STORAGE.get())
                .pattern("A A")
                .pattern("WTW")
                .pattern("WSW")
                .define('W', Items.CHERRY_LOG)
                .define('S', Items.CHERRY_SLAB)
                .define('A', ModItems.NAIL.get())
                .define('T', Items.STICK)
                .unlockedBy("has_cherry_slab", has(Items.CHERRY_SLAB))
                .save(recipeOutput);

        // Mangrove Storage
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.MANGROVE_STORAGE.get())
                .pattern("A A")
                .pattern("WTW")
                .pattern("WSW")
                .define('W', Items.MANGROVE_LOG)
                .define('S', Items.MANGROVE_SLAB)
                .define('A', ModItems.NAIL.get())
                .define('T', Items.STICK)
                .unlockedBy("has_mangrove_slab", has(Items.MANGROVE_SLAB))
                .save(recipeOutput);

        // Bamboo Storage (assuming custom bamboo slab exists)
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.BAMBOO_STORAGE.get())
                .pattern("A A")
                .pattern("WTW")
                .pattern("WSW")
                .define('W', Items.BAMBOO_BLOCK)
                .define('A', ModItems.NAIL.get())
                .define('S', Items.BAMBOO_SLAB) // Assuming you have a BAMBOO_SLAB item in your ModItems
                .define('T', Items.STICK)
                .unlockedBy("has_bamboo_slab", has(Items.BAMBOO_SLAB))
                .save(recipeOutput);

        // OAK Hook
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.OAK_HOOK.get())
                .pattern("   ")
                .pattern("SAS")
                .pattern("SWS")
                .define('W', Items.OAK_LOG)
                .define('A', ModItems.NAIL.get())
                .define('S', Items.STICK)
                .unlockedBy("has_stick", has(Items.STICK))
                .save(recipeOutput);

        // Acacia Hook
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.ACACIA_HOOK.get())
                .pattern("   ")
                .pattern("SAS")
                .pattern("SWS")
                .define('W', Items.ACACIA_LOG)
                .define('A', ModItems.NAIL.get())
                .define('S', Items.STICK)
                .unlockedBy("has_stick", has(Items.STICK))
                .save(recipeOutput);

        // OAK Hook
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.BAMBOO_HOOK.get())
                .pattern("   ")
                .pattern("SAS")
                .pattern("SWS")
                .define('W', Items.BAMBOO_BLOCK)
                .define('A', ModItems.NAIL.get())
                .define('S', Items.STICK)
                .unlockedBy("has_stick", has(Items.STICK))
                .save(recipeOutput);

        // OAK Hook
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.BIRCH_HOOK.get())
                .pattern("   ")
                .pattern("SAS")
                .pattern("SWS")
                .define('W', Items.BIRCH_LOG)
                .define('A', ModItems.NAIL.get())
                .define('S', Items.STICK)
                .unlockedBy("has_stick", has(Items.STICK))
                .save(recipeOutput);

        // OAK Hook
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.CHERRY_HOOK.get())
                .pattern("   ")
                .pattern("SAS")
                .pattern("SWS")
                .define('W', Items.CHERRY_LOG)
                .define('A', ModItems.NAIL.get())
                .define('S', Items.STICK)
                .unlockedBy("has_stick", has(Items.STICK))
                .save(recipeOutput);

        // OAK Hook
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.DARK_OAK_HOOK.get())
                .pattern("   ")
                .pattern("SAS")
                .pattern("SWS")
                .define('W', Items.DARK_OAK_LOG)
                .define('A', ModItems.NAIL.get())
                .define('S', Items.STICK)
                .unlockedBy("has_stick", has(Items.STICK))
                .save(recipeOutput);

        // OAK Hook
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.JUNGLE_HOOK.get())
                .pattern("   ")
                .pattern("SAS")
                .pattern("SWS")
                .define('W', Items.JUNGLE_LOG)
                .define('A', ModItems.NAIL.get())
                .define('S', Items.STICK)
                .unlockedBy("has_stick", has(Items.STICK))
                .save(recipeOutput);

        // OAK Hook
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.MANGROVE_HOOK.get())
                .pattern("   ")
                .pattern("SAS")
                .pattern("SWS")
                .define('W', Items.MANGROVE_LOG)
                .define('A', ModItems.NAIL.get())
                .define('S', Items.STICK)
                .unlockedBy("has_stick", has(Items.STICK))
                .save(recipeOutput);

        // OAK Hook
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.SPRUCE_HOOK.get())
                .pattern("   ")
                .pattern("SAS")
                .pattern("SWS")
                .define('W', Items.SPRUCE_LOG)
                .define('A', ModItems.NAIL.get())
                .define('S', Items.STICK)
                .unlockedBy("has_stick", has(Items.STICK))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.STEEL_BARREL_EMPTY.get())
                .pattern("B B")
                .pattern("B B")
                .pattern("SSS")
                .define('B', ModItems.STEEL_INGOT.get())
                .define('S', ModBlocks.STEEL_BLOCK.get())
                .unlockedBy("has_steel_ingot", has(ModItems.STEEL_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COPPER_BARREL.get())
                .pattern("B B")
                .pattern("B B")
                .pattern("SSS")
                .define('B', Items.COPPER_INGOT)
                .define('S', Blocks.COPPER_BLOCK)
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SPEAKER.get())
                .pattern(" BB")
                .pattern("SA ")
                .pattern(" BB")
                .define('B', ModItems.STEEL_INGOT.get())
                .define('S', Items.IRON_INGOT)
                .define('A', Items.REDSTONE_BLOCK)
                .unlockedBy("has_steel_ingot", has(ModItems.STEEL_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MILK_BOTTLE.get(),3)
                .pattern("   ")
                .pattern("BSB")
                .pattern(" B ")
                .define('B', Items.GLASS_BOTTLE)
                .define('S', Items.MILK_BUCKET)
                .unlockedBy("has_glass_bottle", has(Items.GLASS_BOTTLE)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.IRON_BARREL_EMPTY.get())
                .pattern("B B")
                .pattern("B B")
                .pattern("SSS")
                .define('B', Items.IRON_INGOT)
                .define('S', Items.IRON_BLOCK)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT)).save(recipeOutput);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.STEEL_INGOT.get(), 9)
                .requires(ModBlocks.STEEL_BLOCK)
                .unlockedBy("has_steel_block", has(ModBlocks.STEEL_BLOCK)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CUCUMBER_SEEDS.get(), 3)
                .requires(ModItems.CUCUMBER.get())
                .unlockedBy("has_cucumber", has(ModItems.CUCUMBER.get())).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TOMATO_SEEDS.get(), 3)
                .requires(ModItems.TOMATO.get())
                .unlockedBy("has_tomato", has(ModItems.TOMATO.get())).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BLACKCURRANT_SEEDS.get(), 1)
                .requires(ModItems.BLACKCURRANT.get())
                .unlockedBy("has_blackcurrant", has(ModItems.BLACKCURRANT.get())).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.STRAWBERRY_SEEDS.get(), 1)
                .requires(ModItems.STRAWBERRY.get())
                .unlockedBy("has_strawberry", has(ModItems.STRAWBERRY.get())).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BLUEBERRY_SEEDS.get(), 1)
                .requires(ModItems.BLUEBERRY.get())
                .unlockedBy("has_blueberry", has(ModItems.BLUEBERRY.get())).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.GOOSEBERRY_SEEDS.get(), 1)
                .requires(ModItems.GOOSEBERRY.get())
                .unlockedBy("has_gooseberry", has(ModItems.GOOSEBERRY.get())).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RADISH_SEEDS.get(), 3)
                .requires(ModItems.RADISH.get())
                .unlockedBy("has_radish", has(ModItems.RADISH.get())).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ONION_SEEDS.get(), 3)
                .requires(ModItems.ONION.get())
                .unlockedBy("has_onion", has(ModItems.ONION.get())).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SPRING_ONION_SEEDS.get(), 3)
                .requires(ModItems.SPRING_ONION.get())
                .unlockedBy("has_spring_onion", has(ModItems.SPRING_ONION.get())).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LETTUCE_SEEDS.get(), 3)
                .requires(ModItems.LETTUCE.get())
                .unlockedBy("has_lettuce", has(ModItems.LETTUCE.get())).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.BREAD_BAIT.get(), 8)
                .requires(Items.BREAD)
                .requires(Items.POTION)  // Vanilla water bottle (glass bottle + water)
                .unlockedBy("has_potion", has(Items.POTION))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.APPLE_JUICE.get(), 1)
                .requires(Items.APPLE)
                .requires(Items.SUGAR)
                .requires(Items.POTION)  // Vanilla water bottle (glass bottle + water)
                .unlockedBy("has_apple", has(Items.APPLE))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.MELON_JUICE.get(), 1)
                .requires(Items.MELON_SLICE)
                .requires(Items.SUGAR)
                .requires(Items.POTION)  // Vanilla water bottle (glass bottle + water)
                .unlockedBy("has_melon_slice", has(Items.MELON_SLICE))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.GLOWBERRY_JUICE.get(), 1)
                .requires(Items.GLOW_BERRIES)
                .requires(Items.SUGAR)
                .requires(Items.POTION)  // Vanilla water bottle (glass bottle + water)
                .unlockedBy("has_potion", has(Items.POTION))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.SWEETBERRY_JUICE.get(), 1)
                .requires(Items.SWEET_BERRIES)
                .requires(Items.SUGAR)
                .requires(Items.POTION)  // Vanilla water bottle (glass bottle + water)
                .unlockedBy("has_potion", has(Items.POTION))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.PEAR_JUICE.get(), 1)
                .requires(ModItems.PEAR.get())
                .requires(Items.SUGAR)
                .requires(Items.POTION)  // Vanilla water bottle (glass bottle + water)
                .unlockedBy("has_pear", has(ModItems.PEAR.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.ORANGE_JUICE.get(), 1)
                .requires(ModItems.ORANGE.get())
                .requires(Items.SUGAR)
                .requires(Items.POTION)  // Vanilla water bottle (glass bottle + water)
                .unlockedBy("has_orange", has(ModItems.ORANGE.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.BANANA_JUICE.get(), 1)
                .requires(ModItems.BANANA.get())
                .requires(Items.SUGAR)
                .requires(Items.POTION)  // Vanilla water bottle (glass bottle + water)
                .unlockedBy("has_banana", has(ModItems.BANANA.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.TOMATO_JUICE.get(), 1)
                .requires(ModItems.TOMATO.get())
                .requires(Items.POTION)  // Vanilla water bottle (glass bottle + water)
                .unlockedBy("has_tomato", has(ModItems.TOMATO.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.STRAWBERRY_JUICE.get(), 1)
                .requires(ModItems.STRAWBERRY.get())
                .requires(Items.SUGAR)
                .requires(Items.POTION)  // Vanilla water bottle (glass bottle + water)
                .unlockedBy("has_strawberry", has(ModItems.STRAWBERRY.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CHERRY_JUICE.get(), 1)
                .requires(ModItems.CHERRY.get())
                .requires(Items.SUGAR)
                .requires(Items.POTION)  // Vanilla water bottle (glass bottle + water)
                .unlockedBy("has_cherry", has(ModItems.CHERRY.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.AVOCADO_JUICE.get(), 1)
                .requires(ModItems.AVOCADO.get())
                .requires(Items.SUGAR)
                .requires(Items.POTION)  // Vanilla water bottle (glass bottle + water)
                .unlockedBy("has_avocado", has(ModItems.AVOCADO.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.PINEAPPLE_JUICE.get(), 1)
                .requires(ModItems.PINEAPPLE.get())
                .requires(Items.SUGAR)
                .requires(Items.POTION)  // Vanilla water bottle (glass bottle + water)
                .unlockedBy("has_pineapple", has(ModItems.PINEAPPLE.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.COFFEE.get(), 1)
                .requires(ModItems.COFFEE_BEANS.get())
                .requires(Items.SUGAR)
                .requires(Items.POTION)  // Vanilla water bottle (glass bottle + water)
                .unlockedBy("has_coffee_beans", has(ModItems.COFFEE_BEANS.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.TEA.get(), 1)
                .requires(ModItems.TEA_LEAVES.get())
                .requires(Items.SUGAR)
                .requires(Items.POTION)  // Vanilla water bottle (glass bottle + water)
                .unlockedBy("has_tea_leaves", has(ModItems.TEA_LEAVES.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CORN_KERNELS.get(), 8)
                .requires(ModItems.CORN.get())
                .requires(ModTags.KNIVES)  // Vanilla water bottle (glass bottle + water)
                .unlockedBy("has_corn", has(ModItems.CORN.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.BLACKCURRANT_JUICE.get(), 1)
                .requires(ModItems.BLACKCURRANT.get())
                .requires(Items.SUGAR)
                .requires(Items.POTION)  // Vanilla water bottle (glass bottle + water)
                .unlockedBy("has_blackcurrant", has(ModItems.BLACKCURRANT.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.BLUEBERRY_JUICE.get(), 1)
                .requires(ModItems.BLUEBERRY.get())
                .requires(Items.SUGAR)
                .requires(Items.POTION)  // Vanilla water bottle (glass bottle + water)
                .unlockedBy("has_blueberry", has(ModItems.BLUEBERRY.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.GOOSEBERRY_JUICE.get(), 1)
                .requires(ModItems.GOOSEBERRY.get())
                .requires(Items.SUGAR)
                .requires(Items.POTION)  // Vanilla water bottle (glass bottle + water)
                .unlockedBy("has_gooseberry", has(ModItems.GOOSEBERRY.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.BREAD_FISHING_ROD.get(), 1)
                .requires(ModItems.BREAD_BAIT.get())
                .requires(Items.FISHING_ROD)  // Vanilla water bottle (glass bottle + water)
                .unlockedBy("has_fishing_rod", has(Items.FISHING_ROD))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CORN_FISHING_ROD.get(), 1)
                .requires(ModItems.CORN_KERNELS.get())
                .requires(Items.FISHING_ROD)  // Vanilla water bottle (glass bottle + water)
                .unlockedBy("has_fishing_rod", has(Items.FISHING_ROD))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.WORM_FISHING_ROD.get(), 1)
                .requires(ModItems.WORM.get())
                .requires(Items.FISHING_ROD)  // Vanilla water bottle (glass bottle + water)
                .unlockedBy("has_fishing_rod", has(Items.FISHING_ROD))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.GREEN_DYE)
                .requires(Items.KELP)
                .requires(ModTags.KNIVES)
                .unlockedBy("has_kelp", has(Items.KELP))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("aziuriamod", "green_dye_from_kelp"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.BROWN_DYE, 2)
                .requires(Items.BLACK_DYE)
                .requires(Items.ORANGE_DYE)
                .unlockedBy("has_orange_dye", has(Items.ORANGE_DYE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("aziuriamod", "brown_dye_from_orange_dye"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.GREEN_DYE)
                .requires(Items.LILY_PAD)
                .requires(ModTags.KNIVES)
                .unlockedBy("has_lily_pad", has(Items.LILY_PAD))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("aziuriamod", "green_dye_from_seagrass"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.BLACK_DYE)
                .requires(ModItems.BLACKCURRANT.get())
                .requires(ModTags.KNIVES)
                .unlockedBy("has_blackcurrant", has(ModItems.BLACKCURRANT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("aziuriamod", "black_dye_from_blackcurrant"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.BLUE_DYE)
                .requires(ModItems.BLUEBERRY.get())
                .requires(ModTags.KNIVES)
                .unlockedBy("has_blueberry", has(ModItems.BLUEBERRY.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("aziuriamod", "blue_dye_from_blueberry"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.RED_DYE)
                .requires(ModItems.STRAWBERRY.get())
                .requires(ModTags.KNIVES)
                .unlockedBy("has_strawberry", has(ModItems.STRAWBERRY.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("aziuriamod", "red_dye_from_strawberry"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.GREEN_DYE)
                .requires(ModItems.GOOSEBERRY.get())
                .requires(ModTags.KNIVES)
                .unlockedBy("has_gooseberry", has(ModItems.GOOSEBERRY.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("aziuriamod", "green_dye_from_gooseberry"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.STONE_SHARD.get())
                .requires(ModBlocks.PEBBLE_BLOCK.get())
                .unlockedBy("has_pebble_block", has(ModBlocks.PEBBLE_BLOCK.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("aziuriamod", "stone_shard_from_pebble_block"));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.END_PORTAL_FRAME)
                .pattern("OOO")
                .pattern("ENE")
                .pattern("SBS")
                .define('O', Items.OBSIDIAN)
                .define('E', Items.ENDER_EYE)
                .define('S', Items.SCULK_CATALYST)
                .define('N', Items.NETHER_STAR)
                .define('B', Items.NETHERITE_BLOCK)
                .unlockedBy("has_obsidian", has(Items.OBSIDIAN))
                .save(recipeOutput);


        oreSmelting(recipeOutput, STEEL_ALLOY_MESH_SMELTABLES, RecipeCategory.MISC, ModItems.STEEL_INGOT.get(), 0.25f, 200, "steel_ingot");
        oreBlasting(recipeOutput, STEEL_ALLOY_MESH_SMELTABLES, RecipeCategory.MISC, ModItems.STEEL_INGOT.get(), 0.25f, 100, "steel_ingot");

        oreSmelting(recipeOutput, TIN_SMELTABLES, RecipeCategory.MISC, ModItems.TIN_INGOT.get(), 0.25f, 200, "steel_ingot");
        oreBlasting(recipeOutput, TIN_SMELTABLES, RecipeCategory.MISC, ModItems.TIN_INGOT.get(), 0.25f, 100, "steel_ingot");

        oreSmelting(recipeOutput, SPECTRAL_SMELTABLES, RecipeCategory.MISC, ModItems.SPECTRAL_INGOT.get(), 0.25f, 200, "spectral_ingot");
        oreBlasting(recipeOutput, SPECTRAL_SMELTABLES, RecipeCategory.MISC, ModItems.SPECTRAL_INGOT.get(), 0.25f, 100, "spectral_ingot");

        oreSmelting(recipeOutput, SULPHUR, RecipeCategory.MISC, ModItems.SULPHUR.get(), 0.25f, 200, "sulphur");
        oreBlasting(recipeOutput, SULPHUR, RecipeCategory.MISC, ModItems.SULPHUR.get(), 0.25f, 100, "sulphur");

        oreSmelting(recipeOutput, POTASSIUM, RecipeCategory.MISC, ModItems.POTASSIUM.get(), 0.25f, 200, "potassium");
        oreBlasting(recipeOutput, POTASSIUM, RecipeCategory.MISC, ModItems.POTASSIUM.get(), 0.25f, 100, "potassium");

        oreSmelting(recipeOutput, SPINEL, RecipeCategory.MISC, ModItems.SPINEL.get(), 0.25f, 200, "spinel");
        oreBlasting(recipeOutput, SPINEL, RecipeCategory.MISC, ModItems.SPINEL.get(), 0.25f, 100, "spinel");

        // === Chicken Nuggets from Battered Chicken ===
        oreSmelting(recipeOutput,
                List.of(ModItems.BATTERED_CHICKEN.get()),
                RecipeCategory.FOOD,
                ModItems.CHICKEN_NUGGETS.get(),
                0.35f, // experience
                200,   // cook time
                "chicken_nuggets");

        oreBlasting(recipeOutput,
                List.of(ModItems.BATTERED_CHICKEN.get()),
                RecipeCategory.FOOD,
                ModItems.CHICKEN_NUGGETS.get(),
                0.35f, // experience
                100,   // cook time
                "chicken_nuggets");


        // === Pancakes from Pancake Dough ===
        oreSmelting(recipeOutput,
                List.of(ModItems.PANCAKE_DOUGH.get()),
                RecipeCategory.FOOD,
                ModItems.PANCAKE.get(),
                0.35f, // experience
                200,   // cook time
                "pancake");

        oreBlasting(recipeOutput,
                List.of(ModItems.PANCAKE_DOUGH.get()),
                RecipeCategory.FOOD,
                ModItems.PANCAKE.get(),
                0.35f, // experience
                100,   // cook time
                "pancake");
    }
    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, AziuriaMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
