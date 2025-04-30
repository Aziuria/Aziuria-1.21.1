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

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_PICKAXE.get())
                .pattern("BBB")
                .pattern(" K ")
                .pattern(" K ")
                .define('K', Items.STICK)
                .define('B', ModItems.STEEL_INGOT.get())
                .unlockedBy("has_raw_iron", has(ModItems.STEEL_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_AXE.get())
                .pattern("BB ")
                .pattern("BK ")
                .pattern(" K ")
                .define('K', Items.STICK)
                .define('B', ModItems.STEEL_INGOT.get())
                .unlockedBy("has_raw_iron", has(ModItems.STEEL_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_SHOVEL.get())
                .pattern(" B ")
                .pattern(" K ")
                .pattern(" K ")
                .define('K', Items.STICK)
                .define('B', ModItems.STEEL_INGOT.get())
                .unlockedBy("has_raw_iron", has(ModItems.STEEL_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_SWORD.get())
                .pattern(" B ")
                .pattern(" B ")
                .pattern(" K ")
                .define('K', Items.STICK)
                .define('B', ModItems.STEEL_INGOT.get())
                .unlockedBy("has_raw_iron", has(ModItems.STEEL_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_HOE.get())
                .pattern("BB ")
                .pattern(" K ")
                .pattern(" K ")
                .define('K', Items.STICK)
                .define('B', ModItems.STEEL_INGOT.get())
                .unlockedBy("has_raw_iron", has(ModItems.STEEL_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.GUNPOWDER)
                .pattern("PSP")
                .pattern("SPS")
                .pattern("PSP")
                .define('P', ModItems.POTASSIUM.get())
                .define('S', ModItems.SULPHUR.get())
                .unlockedBy("has_potassium", has(ModItems.POTASSIUM.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SACK.get())
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

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.STEEL_INGOT.get(), 9)
                .requires(ModBlocks.STEEL_BLOCK)
                .unlockedBy("has_steel_block", has(ModBlocks.STEEL_BLOCK)).save(recipeOutput);

        oreSmelting(recipeOutput, STEEL_ALLOY_MESH_SMELTABLES, RecipeCategory.MISC, ModItems.STEEL_INGOT.get(), 0.25f, 200, "steel_ingot");
        oreBlasting(recipeOutput, STEEL_ALLOY_MESH_SMELTABLES, RecipeCategory.MISC, ModItems.STEEL_INGOT.get(), 0.25f, 100, "steel_ingot");
        oreSmelting(recipeOutput, SULPHUR, RecipeCategory.MISC, ModItems.SULPHUR.get(), 0.25f, 200, "sulphur");
        oreBlasting(recipeOutput, SULPHUR, RecipeCategory.MISC, ModItems.SULPHUR.get(), 0.25f, 100, "sulphur");
        oreSmelting(recipeOutput, POTASSIUM, RecipeCategory.MISC, ModItems.POTASSIUM.get(), 0.25f, 200, "potassium");
        oreBlasting(recipeOutput, POTASSIUM, RecipeCategory.MISC, ModItems.POTASSIUM.get(), 0.25f, 100, "potassium");


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
