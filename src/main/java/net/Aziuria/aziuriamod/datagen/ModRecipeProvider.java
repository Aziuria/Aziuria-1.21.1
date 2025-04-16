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

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.STEEL_INGOT.get(), 9)
                .requires(ModBlocks.STEEL_BLOCK)
                .unlockedBy("has_bismuth_block", has(ModBlocks.STEEL_BLOCK)).save(recipeOutput);

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
