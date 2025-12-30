package net.Aziuria.aziuriamod.datagen;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.Aziuria.aziuriamod.block.ModBlocks;
import net.Aziuria.aziuriamod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {

    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();

    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AziuriaMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.SULPHUR.get());
        basicItem(ModItems.POTASSIUM.get());
        basicItem(ModItems.TIN.get());
        basicItem(ModItems.SPINEL.get());
        basicItem(ModItems.SPECTRAL_DUST.get());
        basicItem(ModItems.SPECTRAL_SUBSTANCE.get());
        basicItem(ModItems.SPECTRAL_INGOT.get());
        basicItem(ModItems.COPPER_NUGGET.get());
        basicItem(ModItems.STEEL_NUGGET.get());
        basicItem(ModItems.TIN_NUGGET.get());
        basicItem(ModItems.EMPTY_JAR.get());
        basicItem(ModItems.STONE_SHARD.get());
        basicItem(ModItems.STEEL_INGOT.get());
        basicItem(ModItems.TIN_INGOT.get());
        basicItem(ModItems.SACK_ITEM.get());
        basicItem(ModItems.WORM.get());
        basicItem(ModItems.EMPTY_CUP.get());
        basicItem(ModItems.STEEL_ALLOY_MESH.get());

        basicItem(ModItems.OAK_BARK.get());
        basicItem(ModItems.DARK_OAK_BARK.get());
        basicItem(ModItems.ACACIA_BARK.get());
        basicItem(ModItems.MANGROVE_BARK.get());
        basicItem(ModItems.JUNGLE_BARK.get());
        basicItem(ModItems.SPRUCE_BARK.get());
        basicItem(ModItems.CHERRY_BARK.get());
        basicItem(ModItems.BIRCH_BARK.get());
        basicItem(ModItems.DRIED_GRASS.get());

        basicItem(ModItems.RADISH_SEEDS.get());
        basicItem(ModItems.CUCUMBER_SEEDS.get());
        basicItem(ModItems.TOMATO_SEEDS.get());
        basicItem(ModItems.LETTUCE_SEEDS.get());
        basicItem(ModItems.ONION_SEEDS.get());
        basicItem(ModItems.FLOUR.get());
        basicItem(ModItems.PANCAKE_DOUGH.get());
        basicItem(ModItems.PANCAKE.get());
        basicItem(ModItems.CHICKEN_NUGGETS.get());
        basicItem(ModItems.BEEF_BURGER.get());
        basicItem(ModItems.CHEESEBURGER.get());
        basicItem(ModItems.PORKCHOP_BURGER.get());
        basicItem(ModItems.CHEESE.get());
        basicItem(ModItems.FRENCH_FRIES.get());
        basicItem(ModItems.PINEAPPLE.get());
        basicItem(ModItems.PINEAPPLE_SEEDS.get());
        basicItem(ModItems.CORN.get());
        basicItem(ModItems.CORN_KERNELS.get());
        basicItem(ModItems.BREAD_BAIT.get());
        basicItem(ModItems.CORN_SEEDS.get());
        basicItem(ModItems.COFFEE_BEANS.get());
        basicItem(ModItems.COFFEE_SEEDS.get());
        basicItem(ModItems.TEA_LEAVES.get());
        basicItem(ModItems.TEA_SEEDS.get());
        basicItem(ModItems.CHERRY.get());
        basicItem(ModItems.ORANGE.get());
        basicItem(ModItems.BLACKCURRANT.get());
        basicItem(ModItems.STRAWBERRY.get());
        basicItem(ModItems.BLUEBERRY.get());
        basicItem(ModItems.GOOSEBERRY.get());
        basicItem(ModItems.BLACKCURRANT_SEEDS.get());
        basicItem(ModItems.STRAWBERRY_SEEDS.get());
        basicItem(ModItems.BLUEBERRY_SEEDS.get());
        basicItem(ModItems.GOOSEBERRY_SEEDS.get());
        basicItem(ModItems.PEAR.get());
        basicItem(ModItems.AVOCADO.get());
        basicItem(ModItems.BANANA.get());
        basicItem(ModItems.APPLE_JUICE.get());
        basicItem(ModItems.ORANGE_JUICE.get());
        basicItem(ModItems.BANANA_JUICE.get());
        basicItem(ModItems.PEAR_JUICE.get());
        basicItem(ModItems.MELON_JUICE.get());
        basicItem(ModItems.CHERRY_JUICE.get());
        basicItem(ModItems.AVOCADO_JUICE.get());
        basicItem(ModItems.PINEAPPLE_JUICE.get());
        basicItem(ModItems.BLACKCURRANT_JUICE.get());
        basicItem(ModItems.BLUEBERRY_JUICE.get());
        basicItem(ModItems.GOOSEBERRY_JUICE.get());
        basicItem(ModItems.STRAWBERRY_JUICE.get());
        basicItem(ModItems.SWEETBERRY_JUICE.get());
        basicItem(ModItems.GLOWBERRY_JUICE.get());
        basicItem(ModItems.SWEETBERRY_JUICE.get());
        basicItem(ModItems.TOMATO_JUICE.get());
        basicItem(ModItems.BANANA_SMOOTHIE.get());
        basicItem(ModItems.APPLE_SMOOTHIE.get());
        basicItem(ModItems.AVOCADO_SMOOTHIE.get());
        basicItem(ModItems.BLACKCURRANT_SMOOTHIE.get());
        basicItem(ModItems.BLUEBERRY_SMOOTHIE.get());
        basicItem(ModItems.GOOSEBERRY_SMOOTHIE.get());
        basicItem(ModItems.CHERRY_SMOOTHIE.get());
        basicItem(ModItems.GLOWBERRY_SMOOTHIE.get());
        basicItem(ModItems.MELON_SMOOTHIE.get());
        basicItem(ModItems.ORANGE_SMOOTHIE.get());
        basicItem(ModItems.PEAR_SMOOTHIE.get());
        basicItem(ModItems.PINEAPPLE_SMOOTHIE.get());
        basicItem(ModItems.STRAWBERRY_SMOOTHIE.get());
        basicItem(ModItems.SWEETBERRY_SMOOTHIE.get());
        basicItem(ModItems.YUCCA_LEAVES.get());
        basicItem(ModItems.COFFEE.get());
        basicItem(ModItems.TEA.get());
        basicItem(ModItems.MILK_BOTTLE.get());
        basicItem(ModItems.DICED_CHICKEN.get());
        basicItem(ModItems.BATTERED_CHICKEN.get());
        basicItem(ModItems.SPRING_ONION_SEEDS.get());
        basicItem(ModItems.NET.get());
        basicItem(ModItems.NET_SEGMENT.get());
        basicItem(ModItems.NAIL.get());
        basicItem(ModItems.MALLET_HEAD.get());

        basicItem(ModItems.ISLAND_GENERATOR_SMALL.get());
        basicItem(ModItems.ISLAND_GENERATOR_MEDIUM.get());
        basicItem(ModItems.ISLAND_GENERATOR_LARGE.get());

        handheldItem(ModItems.STEEL_SWORD);
        handheldItem(ModItems.STEEL_PICKAXE);
        handheldItem(ModItems.STEEL_SHOVEL);
        handheldItem(ModItems.STEEL_AXE);
        handheldItem(ModItems.STEEL_HOE);
        handheldItem(ModItems.SPINEL_SWORD);
        handheldItem(ModItems.SPINEL_PICKAXE);
        handheldItem(ModItems.SPINEL_SHOVEL);
        handheldItem(ModItems.SPINEL_AXE);
        handheldItem(ModItems.SPINEL_HOE);
        handheldItem(ModItems.COPPER_SWORD);
        handheldItem(ModItems.COPPER_PICKAXE);
        handheldItem(ModItems.COPPER_SHOVEL);
        handheldItem(ModItems.COPPER_AXE);
        handheldItem(ModItems.COPPER_HOE);
        handheldItem(ModItems.MALLET);
     //   handheldItem(ModItems.KNIFE);

        trimmedArmorItem(ModItems.STEEL_HELMET);
        trimmedArmorItem(ModItems.STEEL_CHESTPLATE);
        trimmedArmorItem(ModItems.STEEL_LEGGINGS);
        trimmedArmorItem(ModItems.STEEL_BOOTS);
        trimmedArmorItem(ModItems.SPINEL_HELMET);
        trimmedArmorItem(ModItems.SPINEL_CHESTPLATE);
        trimmedArmorItem(ModItems.SPINEL_LEGGINGS);
        trimmedArmorItem(ModItems.SPINEL_BOOTS);
        trimmedArmorItem(ModItems.COPPER_HELMET);
        trimmedArmorItem(ModItems.COPPER_CHESTPLATE);
        trimmedArmorItem(ModItems.COPPER_LEGGINGS);
        trimmedArmorItem(ModItems.COPPER_BOOTS);

        basicItem(ModItems.SPINEL_HORSE_ARMOR.get());
        basicItem(ModItems.COPPER_HORSE_ARMOR.get());
        basicItem(ModItems.STEEL_HORSE_ARMOR.get());

        withExistingParent(ModItems.WORM_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));

        saplingItem(ModBlocks.APPLE_SAPLING);
        saplingItem(ModBlocks.PEAR_SAPLING);
        saplingItem(ModBlocks.CHERRY_SAPLING);
        saplingItem(ModBlocks.AVOCADO_SAPLING);
        saplingItem(ModBlocks.ORANGE_SAPLING);
        saplingItem(ModBlocks.BANANA_SAPLING);
    }

    private ItemModelBuilder saplingItem(DeferredBlock<Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID,"block/" + item.getId().getPath()));

    }

    // Shoutout to El_Redstoniano for making this
    private void trimmedArmorItem(DeferredItem<ArmorItem> itemDeferredItem) {
        final String MOD_ID = AziuriaMod.MOD_ID; // Change this to your mod id

        if (itemDeferredItem.get() instanceof ArmorItem armorItem) {
            trimMaterials.forEach((trimMaterial, value) -> {
                float trimValue = value;

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = armorItem.toString();
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = ResourceLocation.parse(armorItemPath);
                ResourceLocation trimResLoc = ResourceLocation.parse(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = ResourceLocation.parse(currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc.getNamespace() + ":item/" + armorItemResLoc.getPath())
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemDeferredItem.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc.getNamespace() + ":item/" + trimNameResLoc.getPath()))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                ResourceLocation.fromNamespaceAndPath(MOD_ID,
                                        "item/" + itemDeferredItem.getId().getPath()));
            });
        }
    }


    private ItemModelBuilder handheldItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, "item/" + item.getId().getPath()));
    }


}
