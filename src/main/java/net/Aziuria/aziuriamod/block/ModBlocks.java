package net.Aziuria.aziuriamod.block;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.Aziuria.aziuriamod.block.custom.*;
import net.Aziuria.aziuriamod.block.entity.SteelBarsBlock;
import net.Aziuria.aziuriamod.item.ModItems;
import net.Aziuria.aziuriamod.item.custom.FuelItem;
import net.Aziuria.aziuriamod.worldgen.tree.ModTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(AziuriaMod.MOD_ID);


    public static final DeferredBlock<Block> SULPHUR_ORE = registerBlock("sulphur_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> POTASSIUM_ORE = registerBlock("potassium_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> DEEPSLATE_SULPHUR_ORE = registerBlock("deepslate_sulphur_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> DEEPSLATE_POTASSIUM_ORE = registerBlock("deepslate_potassium_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> STEEL_BLOCK = registerBlock("steel_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));

    public static final DeferredBlock<Block> UNBREAKABLE_GLASS = registerBlock("unbreakable_glass",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(-1.0F, 3600000.0F).sound(SoundType.GLASS)
                    .noOcclusion()
                    .isViewBlocking((s, l, p) -> false)
                    .isSuffocating((s, l, p) -> false)
                    .noLootTable().requiresCorrectToolForDrops()
                    .isRedstoneConductor((s, l, p) -> false)));

    // WOODEN SHELVES


    public static final DeferredBlock<ShelfBlock> OAK_SHELF = registerBlock("oak_shelf",
            () -> new ShelfBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<ShelfBlock> BIRCH_SHELF = registerBlock("birch_shelf",
            () -> new ShelfBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<ShelfBlock> SPRUCE_SHELF = registerBlock("spruce_shelf",
            () -> new ShelfBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<ShelfBlock> JUNGLE_SHELF = registerBlock("jungle_shelf",
            () -> new ShelfBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<ShelfBlock> DARK_OAK_SHELF = registerBlock("dark_oak_shelf",
            () -> new ShelfBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<ShelfBlock> ACACIA_SHELF = registerBlock("acacia_shelf",
            () -> new ShelfBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<ShelfBlock> CHERRY_SHELF = registerBlock("cherry_shelf",
            () -> new ShelfBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<ShelfBlock> MANGROVE_SHELF = registerBlock("mangrove_shelf",
            () -> new ShelfBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<ShelfBlock> BAMBOO_SHELF = registerBlock("bamboo_shelf",
            () -> new ShelfBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.BAMBOO)
                    .noOcclusion()));

    // WOODEN STORAGES


    public static final DeferredBlock<StorageBlock> OAK_STORAGE = registerBlock("oak_storage",
            () -> new StorageBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<StorageBlock> ACACIA_STORAGE = registerBlock("acacia_storage",
            () -> new StorageBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<StorageBlock> BAMBOO_STORAGE = registerBlock("bamboo_storage",
            () -> new StorageBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.BAMBOO)
                    .noOcclusion()));

    public static final DeferredBlock<StorageBlock> BIRCH_STORAGE = registerBlock("birch_storage",
            () -> new StorageBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<StorageBlock> CHERRY_STORAGE = registerBlock("cherry_storage",
            () -> new StorageBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<StorageBlock> DARK_OAK_STORAGE = registerBlock("dark_oak_storage",
            () -> new StorageBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<StorageBlock> JUNGLE_STORAGE = registerBlock("jungle_storage",
            () -> new StorageBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<StorageBlock> MANGROVE_STORAGE = registerBlock("mangrove_storage",
            () -> new StorageBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<StorageBlock> SPRUCE_STORAGE = registerBlock("spruce_storage",
            () -> new StorageBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<SteelBarrelBlock> STEEL_BARREL_EMPTY = registerBlock("steel_barrel_empty",
            () -> new SteelBarrelBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.METAL)
                    .noOcclusion()));

    public static final DeferredBlock<SteelBarrelBlock> IRON_BARREL_EMPTY = registerBlock("iron_barrel_empty",
            () -> new SteelBarrelBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.METAL)
                    .noOcclusion()));

    public static final DeferredBlock<SteelBarsBlock> STEEL_BARS = registerBlock("steel_bars",
            () -> new SteelBarsBlock(BlockBehaviour.Properties.of()
                    .strength(5.0f)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()));

    public static final DeferredBlock<LeafLitterBlock> LEAF_LITTER = registerBlock("leaf_litter",
            () -> new LeafLitterBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .strength(0.2f)
                    .noOcclusion()
                    .isViewBlocking((s, r, p) -> false)
                    .isSuffocating((s, r, p) -> false)),
            block -> new FuelItem(block, new Item.Properties(), 100));

    public static final DeferredBlock<DemarcationPostBlock> DEMAECATION_POST = registerBlock("demarcation_post_a",
            () -> new DemarcationPostBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.STONE)
                    .noOcclusion()));

    public static final DeferredBlock<DemarcationPostBlock> DEMAECATION_POST_B = registerBlock("demarcation_post_b",
            () -> new DemarcationPostBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.STONE)
                    .noOcclusion()));

    public static final DeferredBlock<DemarcationPostBlock> DEMAECATION_POST_C = registerBlock("demarcation_post_c",
            () -> new DemarcationPostBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.STONE)
                    .noOcclusion()));

    public static final DeferredBlock<DemarcationPostBlock> DEMAECATION_POST_D = registerBlock("demarcation_post_d",
            () -> new DemarcationPostBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.STONE)
                    .noOcclusion()));

    public static final DeferredBlock<DemarcationPostBlock> DEMAECATION_POST_E = registerBlock("demarcation_post_e",
            () -> new DemarcationPostBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.STONE)
                    .noOcclusion()));

    public static final DeferredBlock<SpeakerBlock> SPEAKER = registerBlock("speaker",
            () -> new SpeakerBlock(BlockBehaviour.Properties.of()
                    .strength(2.0f)
                    .sound(SoundType.METAL)
                    .noOcclusion()));

    public static final DeferredBlock<WoodcutterBenchBlock> WOODCUTTER_BENCH = registerBlock("woodcutter_bench",
            () -> new WoodcutterBenchBlock(BlockBehaviour.Properties.of()
                    .strength(2.0f)
                    .sound(SoundType.METAL)
                    .noOcclusion()));

    public static final DeferredBlock<MinerBenchBlock> MINER_BENCH = registerBlock("miner_bench",
            () -> new MinerBenchBlock(BlockBehaviour.Properties.of()
                    .strength(2.0f)
                    .sound(SoundType.METAL)
                    .noOcclusion()));

    public static final DeferredBlock<Block> RADISH_CROP = BLOCKS.register("radish_crop",
            () -> new RadishCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)));
    public static final DeferredBlock<Block> CUCUMBER_CROP = BLOCKS.register("cucumber_crop",
            () -> new CucumberCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)));
    public static final DeferredBlock<Block> TOMATO_CROP = BLOCKS.register("tomato_crop",
            () -> new TomatoCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)));
    public static final DeferredBlock<Block> LETTUCE_CROP = BLOCKS.register("lettuce_crop",
            () -> new LettuceCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)));
    public static final DeferredBlock<Block> ONION_CROP = BLOCKS.register("onion_crop",
            () -> new OnionCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)));
    public static final DeferredBlock<Block> SPRING_ONION_CROP = BLOCKS.register("spring_onion_crop",
            () -> new SpringOnionCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)));
    public static final DeferredBlock<Block> PINEAPPLE_CROP = BLOCKS.register("pineapple_crop",
            () -> new PineappleCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)));
    public static final DeferredBlock<Block> CORN_CROP = BLOCKS.register("corn_crop",
            () -> new CornCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));

    public static final DeferredBlock<Block> FLAX_FLOWER_BLOCK = BLOCKS.register("flax_flower",
            () -> new FlaxFlowerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)));

    public static final DeferredBlock<Block> APPLE_LEAVES = registerBlock("apple_leaves",
            () -> new ModAppleLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

            });

    public static final DeferredBlock<Block> APPLE_SAPLING = registerBlock("apple_sapling",
            () -> new ModAppleSaplingBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));





    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> new BlockItem(toReturn.get(), new Item.Properties()));
        return toReturn;
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(
            String name,
            Supplier<T> blockSupplier,
            Function<T, ? extends Item> itemFactory
    ) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, blockSupplier);
        ModItems.ITEMS.register(name, () -> itemFactory.apply(toReturn.get()));
        return toReturn;
    }



    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}