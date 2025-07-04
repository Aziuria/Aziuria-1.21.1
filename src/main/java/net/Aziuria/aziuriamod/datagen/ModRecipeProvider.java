package net.Aziuria.aziuriamod.datagen;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.Aziuria.aziuriamod.block.ModBlocks;
import net.Aziuria.aziuriamod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
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
        List<ItemLike> SULPHUR = List.of(ModBlocks.SULPHUR_ORE, ModBlocks.DEEPSLATE_SULPHUR_ORE);
        List<ItemLike> POTASSIUM = List.of(ModBlocks.POTASSIUM_ORE, ModBlocks.DEEPSLATE_POTASSIUM_ORE);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_ALLOY_MESH.get())
                .pattern("   ")
                .pattern("CIC")
                .pattern("   ")
                .define('C', Items.COAL)
                .define('I', Items.RAW_IRON)
                .unlockedBy("has_raw_iron", has(Items.RAW_IRON)).save(recipeOutput);

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
                .pattern(" C ")
                .pattern("CFC")
                .pattern("   ")
                .define('C', Items.LEATHER)
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

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.FLOUR.get(), 9)
                .pattern("SSS")
                .define('S', Items.WHEAT)
                .unlockedBy("has_wheat", has(Items.WHEAT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.DICED_CHICKEN.get(), 3)
                .pattern("SA")
                .define('S', Items.CHICKEN)
                .define('A', ModItems.KNIFE.get())
                .unlockedBy("has_chicken", has(Items.CHICKEN))
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
                .pattern(" AH")
                .define('S', ModItems.TOMATO.get())
                .define('R', ModItems.KNIFE.get())
                .define('O', ModItems.LETTUCE.get())
                .define('Q', ModItems.CHEESE.get())
                .define('H', ModItems.ONION.get())
                .define('A', Items.BREAD)
                .define('F', Items.COOKED_BEEF)
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
                .define('F', ModItems.KNIFE.get())
                .unlockedBy("has_baked_potato", has(Items.BAKED_POTATO))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.KNIFE.get())
                .pattern("  S")
                .pattern(" A ")
                .pattern("F  ")
                .define('S', ModItems.STEEL_INGOT.get())
                .define('A', Items.IRON_INGOT)
                .define('F', Items.STICK)
                .unlockedBy("has_stick", has(Items.STICK))
                .save(recipeOutput);

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

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_AXE.get())
                .pattern("BB ")
                .pattern("BK ")
                .pattern(" K ")
                .define('K', Items.STICK)
                .define('B', ModItems.STEEL_INGOT.get())
                .unlockedBy("has_steel_ingot", has(ModItems.STEEL_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_SHOVEL.get())
                .pattern(" B ")
                .pattern(" K ")
                .pattern(" K ")
                .define('K', Items.STICK)
                .define('B', ModItems.STEEL_INGOT.get())
                .unlockedBy("has_steel_ingot", has(ModItems.STEEL_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_SWORD.get())
                .pattern(" B ")
                .pattern(" B ")
                .pattern(" K ")
                .define('K', Items.STICK)
                .define('B', ModItems.STEEL_INGOT.get())
                .unlockedBy("has_steel_ingot", has(ModItems.STEEL_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_HOE.get())
                .pattern("BB ")
                .pattern(" K ")
                .pattern(" K ")
                .define('K', Items.STICK)
                .define('B', ModItems.STEEL_INGOT.get())
                .unlockedBy("has_steel_ingot", has(ModItems.STEEL_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_HELMET.get())
                .pattern("BBB")
                .pattern("B B")
                .pattern("   ")
                .define('B', ModItems.STEEL_INGOT.get())
                .unlockedBy("has_steel_ingot", has(ModItems.STEEL_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_CHESTPLATE.get())
                .pattern("B B")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.STEEL_INGOT.get())
                .unlockedBy("has_steel_ingot", has(ModItems.STEEL_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_LEGGINGS.get())
                .pattern("   ")
                .pattern("BBB")
                .pattern("B B")
                .define('B', ModItems.STEEL_INGOT.get())
                .unlockedBy("has_steel_ingot", has(ModItems.STEEL_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_BOOTS.get())
                .pattern("   ")
                .pattern("B B")
                .pattern("B B")
                .define('B', ModItems.STEEL_INGOT.get())
                .unlockedBy("has_steel_ingot", has(ModItems.STEEL_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.GUNPOWDER)
                .pattern("PSP")
                .pattern("SPS")
                .pattern("PSP")
                .define('P', ModItems.POTASSIUM.get())
                .define('S', ModItems.SULPHUR.get())
                .unlockedBy("has_potassium", has(ModItems.POTASSIUM.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.STRING, 4)
                .pattern("SSS")
                .define('S', ModItems.FLAX_FLOWER.get())
                .unlockedBy("has_flax_flower", has(ModItems.FLAX_FLOWER.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SACK_ITEM.get())
                .pattern("   ")
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
                .pattern("   ")
                .pattern("WTW")
                .pattern("WSW")
                .define('W', Items.OAK_LOG)
                .define('S', Items.OAK_SLAB)
                .define('T', Items.STICK)
                .unlockedBy("has_oak_slab", has(Items.OAK_SLAB))
                .save(recipeOutput);

        // Birch Storage
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.BIRCH_STORAGE.get())
                .pattern("   ")
                .pattern("WTW")
                .pattern("WSW")
                .define('W', Items.BIRCH_LOG)
                .define('S', Items.BIRCH_SLAB)
                .define('T', Items.STICK)
                .unlockedBy("has_birch_slab", has(Items.BIRCH_SLAB))
                .save(recipeOutput);

// Spruce Storage
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.SPRUCE_STORAGE.get())
                .pattern("   ")
                .pattern("WTW")
                .pattern("WSW")
                .define('W', Items.SPRUCE_LOG)
                .define('S', Items.SPRUCE_SLAB)
                .define('T', Items.STICK)
                .unlockedBy("has_spruce_slab", has(Items.SPRUCE_SLAB))
                .save(recipeOutput);

// Jungle Storage
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.JUNGLE_STORAGE.get())
                .pattern("   ")
                .pattern("WTW")
                .pattern("WSW")
                .define('W', Items.JUNGLE_LOG)
                .define('S', Items.JUNGLE_SLAB)
                .define('T', Items.STICK)
                .unlockedBy("has_jungle_slab", has(Items.JUNGLE_SLAB))
                .save(recipeOutput);

// Dark Oak Storage
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.DARK_OAK_STORAGE.get())
                .pattern("   ")
                .pattern("WTW")
                .pattern("WSW")
                .define('W', Items.DARK_OAK_LOG)
                .define('S', Items.DARK_OAK_SLAB)
                .define('T', Items.STICK)
                .unlockedBy("has_dark_oak_slab", has(Items.DARK_OAK_SLAB))
                .save(recipeOutput);

// Acacia Storage
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.ACACIA_STORAGE.get())
                .pattern("   ")
                .pattern("WTW")
                .pattern("WSW")
                .define('W', Items.ACACIA_LOG)
                .define('S', Items.ACACIA_SLAB)
                .define('T', Items.STICK)
                .unlockedBy("has_acacia_slab", has(Items.ACACIA_SLAB))
                .save(recipeOutput);

        // Cherry Storage
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.CHERRY_STORAGE.get())
                .pattern("   ")
                .pattern("WTW")
                .pattern("WSW")
                .define('W', Items.CHERRY_LOG)
                .define('S', Items.CHERRY_SLAB)
                .define('T', Items.STICK)
                .unlockedBy("has_cherry_slab", has(Items.CHERRY_SLAB))
                .save(recipeOutput);

        // Mangrove Storage
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.MANGROVE_STORAGE.get())
                .pattern("   ")
                .pattern("WTW")
                .pattern("WSW")
                .define('W', Items.MANGROVE_LOG)
                .define('S', Items.MANGROVE_SLAB)
                .define('T', Items.STICK)
                .unlockedBy("has_mangrove_slab", has(Items.MANGROVE_SLAB))
                .save(recipeOutput);

        // Bamboo Storage (assuming custom bamboo slab exists)
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.BAMBOO_STORAGE.get())
                .pattern("   ")
                .pattern("WTW")
                .pattern("WSW")
                .define('W', Items.BAMBOO_BLOCK)
                .define('S', Items.BAMBOO_SLAB) // Assuming you have a BAMBOO_SLAB item in your ModItems
                .define('T', Items.STICK)
                .unlockedBy("has_bamboo_slab", has(Items.BAMBOO_SLAB))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.STEEL_BARREL_EMPTY.get())
                .pattern("B B")
                .pattern("B B")
                .pattern("SSS")
                .define('B', ModItems.STEEL_INGOT.get())
                .define('S', ModBlocks.STEEL_BLOCK.get())
                .unlockedBy("has_steel_ingot", has(ModItems.STEEL_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SPEAKER.get())
                .pattern(" BB")
                .pattern("BS ")
                .pattern(" BB")
                .define('B', ModItems.STEEL_INGOT.get())
                .define('S', Items.IRON_INGOT)
                .unlockedBy("has_steel_ingot", has(ModItems.STEEL_INGOT)).save(recipeOutput);

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

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.APPLE_JUICE.get(), 1)
                .requires(Items.APPLE)
                .requires(Items.POTION)  // Vanilla water bottle (glass bottle + water)
                .unlockedBy("has_apple", has(Items.APPLE))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.PINEAPPLE_JUICE.get(), 1)
                .requires(ModItems.PINEAPPLE.get())
                .requires(Items.POTION)  // Vanilla water bottle (glass bottle + water)
                .unlockedBy("has_pineapple", has(ModItems.PINEAPPLE.get()))
                .save(recipeOutput);


        oreSmelting(recipeOutput, STEEL_ALLOY_MESH_SMELTABLES, RecipeCategory.MISC, ModItems.STEEL_INGOT.get(), 0.25f, 200, "steel_ingot");
        oreBlasting(recipeOutput, STEEL_ALLOY_MESH_SMELTABLES, RecipeCategory.MISC, ModItems.STEEL_INGOT.get(), 0.25f, 100, "steel_ingot");

        oreSmelting(recipeOutput, SULPHUR, RecipeCategory.MISC, ModItems.SULPHUR.get(), 0.25f, 200, "sulphur");
        oreBlasting(recipeOutput, SULPHUR, RecipeCategory.MISC, ModItems.SULPHUR.get(), 0.25f, 100, "sulphur");

        oreSmelting(recipeOutput, POTASSIUM, RecipeCategory.MISC, ModItems.POTASSIUM.get(), 0.25f, 200, "potassium");
        oreBlasting(recipeOutput, POTASSIUM, RecipeCategory.MISC, ModItems.POTASSIUM.get(), 0.25f, 100, "potassium");

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
