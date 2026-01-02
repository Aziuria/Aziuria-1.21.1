package net.Aziuria.aziuriamod.block;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.Aziuria.aziuriamod.block.custom.*;
import net.Aziuria.aziuriamod.block.entity.CopperBarsBlock;
import net.Aziuria.aziuriamod.block.entity.CopperChainBlock;
import net.Aziuria.aziuriamod.block.entity.SteelBarsBlock;
import net.Aziuria.aziuriamod.block.entity.SteelChainBlock;
import net.Aziuria.aziuriamod.item.ModItems;
import net.Aziuria.aziuriamod.item.custom.FuelItem;
import net.Aziuria.aziuriamod.item.custom.PebbleItem;
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

    public static final DeferredBlock<Block> TIN_ORE = registerBlock("tin_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> POTASSIUM_ORE = registerBlock("potassium_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> SPINEL_ORE = registerBlock("spinel_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> SPECTRAL_ORE = registerBlock("spectral_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> DEEPSLATE_SULPHUR_ORE = registerBlock("deepslate_sulphur_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> DEEPSLATE_TIN_ORE = registerBlock("deepslate_tin_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> DEEPSLATE_POTASSIUM_ORE = registerBlock("deepslate_potassium_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> DEEPSLATE_SPINEL_ORE = registerBlock("deepslate_spinel_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> DEEPSLATE_SPECTRAL_ORE = registerBlock("deepslate_spectral_ore",
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

    public static final DeferredBlock<FishTrapBlock> OAK_FISH_TRAP = registerBlock("oak_fish_trap",
            () -> new FishTrapBlock(BlockBehaviour.Properties.of()
                    .strength(0.6f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()
            ));

    public static final DeferredBlock<FishTrapBlock> BIRCH_FISH_TRAP = registerBlock("birch_fish_trap",
            () -> new FishTrapBlock(BlockBehaviour.Properties.of()
                    .strength(0.6f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()
            ));

    public static final DeferredBlock<FishTrapBlock> BAMBOO_FISH_TRAP = registerBlock("bamboo_fish_trap",
            () -> new FishTrapBlock(BlockBehaviour.Properties.of()
                    .strength(0.6f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()
            ));

    public static final DeferredBlock<FishTrapBlock> CHERRY_FISH_TRAP = registerBlock("cherry_fish_trap",
            () -> new FishTrapBlock(BlockBehaviour.Properties.of()
                    .strength(0.6f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()
            ));

    public static final DeferredBlock<FishTrapBlock> MANGROVE_FISH_TRAP = registerBlock("mangrove_fish_trap",
            () -> new FishTrapBlock(BlockBehaviour.Properties.of()
                    .strength(0.6f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()
            ));

    public static final DeferredBlock<FishTrapBlock> SPRUCE_FISH_TRAP = registerBlock("spruce_fish_trap",
            () -> new FishTrapBlock(BlockBehaviour.Properties.of()
                    .strength(0.6f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()
            ));

    public static final DeferredBlock<FishTrapBlock> JUNGLE_FISH_TRAP = registerBlock("jungle_fish_trap",
            () -> new FishTrapBlock(BlockBehaviour.Properties.of()
                    .strength(0.6f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()
            ));

    public static final DeferredBlock<FishTrapBlock> DARK_OAK_FISH_TRAP = registerBlock("dark_oak_fish_trap",
            () -> new FishTrapBlock(BlockBehaviour.Properties.of()
                    .strength(0.6f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()
            ));

    public static final DeferredBlock<FishTrapBlock> ACACIA_FISH_TRAP = registerBlock("acacia_fish_trap",
            () -> new FishTrapBlock(BlockBehaviour.Properties.of()
                    .strength(0.6f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()
            ));

    public static final DeferredBlock<HookBlock> OAK_HOOK = registerBlock("oak_hook",
            () -> new HookBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<HookBlock> BIRCH_HOOK = registerBlock("birch_hook",
            () -> new HookBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<HookBlock> SPRUCE_HOOK = registerBlock("spruce_hook",
            () -> new HookBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<HookBlock> JUNGLE_HOOK = registerBlock("jungle_hook",
            () -> new HookBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<HookBlock> DARK_OAK_HOOK = registerBlock("dark_oak_hook",
            () -> new HookBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<HookBlock> BAMBOO_HOOK = registerBlock("bamboo_hook",
            () -> new HookBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<HookBlock> ACACIA_HOOK = registerBlock("acacia_hook",
            () -> new HookBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<HookBlock> CHERRY_HOOK = registerBlock("cherry_hook",
            () -> new HookBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<HookBlock> MANGROVE_HOOK = registerBlock("mangrove_hook",
            () -> new HookBlock(BlockBehaviour.Properties.of()
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

    // COPPER BARREL (unaﬀected)
    public static final DeferredBlock<CopperBarrelBlock> COPPER_BARREL = registerBlock("copper_barrel_empty",
            () -> new CopperBarrelBlock(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.METAL)
                    .noOcclusion()
                    .randomTicks())); // enables oxidation

    // EXPOSED COPPER BARREL
    public static final DeferredBlock<CopperBarrelBlock> EXPOSED_COPPER_BARREL = registerBlock("exposed_copper_barrel_empty",
            () -> new CopperBarrelBlock(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.METAL)
                    .noOcclusion()
                    .randomTicks()));

    // WEATHERED COPPER BARREL
    public static final DeferredBlock<CopperBarrelBlock> WEATHERED_COPPER_BARREL = registerBlock("weathered_copper_barrel_empty",
            () -> new CopperBarrelBlock(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.METAL)
                    .noOcclusion()
                    .randomTicks()));

    // OXIDIZED COPPER BARREL (final stage — no random ticks)
    public static final DeferredBlock<CopperBarrelBlock> OXIDIZED_COPPER_BARREL = registerBlock("oxidized_copper_barrel_empty",
            () -> new CopperBarrelBlock(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.METAL)
                    .noOcclusion()));

    // WAXED COPPER BARREL (unaﬀected)
    public static final DeferredBlock<CopperBarrelBlock> WAXED_COPPER_BARREL = registerBlock("waxed_copper_barrel_empty",
            () -> new CopperBarrelBlock(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.METAL)
                    .noOcclusion())); // no random ticks — waxing prevents oxidation

    // WAXED EXPOSED COPPER BARREL
    public static final DeferredBlock<CopperBarrelBlock> WAXED_EXPOSED_COPPER_BARREL = registerBlock("waxed_exposed_copper_barrel_empty",
            () -> new CopperBarrelBlock(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.METAL)
                    .noOcclusion()));

    // WAXED WEATHERED COPPER BARREL
    public static final DeferredBlock<CopperBarrelBlock> WAXED_WEATHERED_COPPER_BARREL = registerBlock("waxed_weathered_copper_barrel_empty",
            () -> new CopperBarrelBlock(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.METAL)
                    .noOcclusion()));

    // WAXED OXIDIZED COPPER BARREL (final stage)
    public static final DeferredBlock<CopperBarrelBlock> WAXED_OXIDIZED_COPPER_BARREL = registerBlock("waxed_oxidized_copper_barrel_empty",
            () -> new CopperBarrelBlock(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.METAL)
                    .noOcclusion()));

    public static final DeferredBlock<SteelBarsBlock> STEEL_BARS = registerBlock("steel_bars",
            () -> new SteelBarsBlock(BlockBehaviour.Properties.of()
                    .strength(5.0f)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()));

    // COPPER BARS (can age)
    public static final DeferredBlock<CopperBarsBlock> COPPER_BARS = registerBlock("copper_bars",
            () -> new CopperBarsBlock(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.of()
                    .strength(4.0f)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .randomTicks()));

    // EXPOSED COPPER BARS (can age)
    public static final DeferredBlock<CopperBarsBlock> EXPOSED_COPPER_BARS = registerBlock("exposed_copper_bars",
            () -> new CopperBarsBlock(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.of()
                    .strength(4.0f)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .randomTicks()));

    // WEATHERED COPPER BARS (can age)
    public static final DeferredBlock<CopperBarsBlock> WEATHERED_COPPER_BARS = registerBlock("weathered_copper_bars",
            () -> new CopperBarsBlock(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.of()
                    .strength(4.0f)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .randomTicks()));

    public static final DeferredBlock<CopperBarsBlock> WAXED_COPPER_BARS =
            registerBlock("waxed_copper_bars",
                    () -> new CopperBarsBlock(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.of()
                            .strength(4.0f)
                            .requiresCorrectToolForDrops()
                            .noOcclusion()));

    public static final DeferredBlock<CopperBarsBlock> WAXED_EXPOSED_COPPER_BARS =
            registerBlock("waxed_exposed_copper_bars",
                    () -> new CopperBarsBlock(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.of()
                            .strength(4.0f)
                            .requiresCorrectToolForDrops()
                            .noOcclusion()));

    public static final DeferredBlock<CopperBarsBlock> WAXED_WEATHERED_COPPER_BARS =
            registerBlock("waxed_weathered_copper_bars",
                    () -> new CopperBarsBlock(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.of()
                            .strength(4.0f)
                            .requiresCorrectToolForDrops()
                            .noOcclusion()));

    public static final DeferredBlock<CopperBarsBlock> WAXED_OXIDIZED_COPPER_BARS =
            registerBlock("waxed_oxidized_copper_bars",
                    () -> new CopperBarsBlock(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.of()
                            .strength(4.0f)
                            .requiresCorrectToolForDrops()
                            .noOcclusion()));

    // COPPER CHAIN (can age)
    public static final DeferredBlock<CopperChainBlock> COPPER_CHAIN = registerBlock("copper_chain",
            () -> new CopperChainBlock(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.of()
                    .strength(4.0f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.CHAIN)
                    .noOcclusion()
                    .randomTicks()));

    // EXPOSED COPPER CHAIN (can age)
    public static final DeferredBlock<CopperChainBlock> EXPOSED_COPPER_CHAIN = registerBlock("exposed_copper_chain",
            () -> new CopperChainBlock(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.of()
                    .strength(4.0f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.CHAIN)
                    .noOcclusion()
                    .randomTicks()));

    // WEATHERED COPPER CHAIN (can age)
    public static final DeferredBlock<CopperChainBlock> WEATHERED_COPPER_CHAIN = registerBlock("weathered_copper_chain",
            () -> new CopperChainBlock(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.of()
                    .strength(4.0f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.CHAIN)
                    .noOcclusion()
                    .randomTicks()));

    // OXIDIZED COPPER CHAIN (final stage — no random ticks)
    public static final DeferredBlock<CopperChainBlock> OXIDIZED_COPPER_CHAIN = registerBlock("oxidized_copper_chain",
            () -> new CopperChainBlock(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.of()
                    .strength(4.0f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.CHAIN)
                    .noOcclusion()));

    public static final DeferredBlock<CopperChainBlock> WAXED_COPPER_CHAIN =
            registerBlock("waxed_copper_chain",
                    () -> new CopperChainBlock(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.of()
                            .strength(4.0f)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.CHAIN)
                            .noOcclusion()));

    public static final DeferredBlock<CopperChainBlock> WAXED_EXPOSED_COPPER_CHAIN =
            registerBlock("waxed_exposed_copper_chain",
                    () -> new CopperChainBlock(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.of()
                            .strength(4.0f)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.CHAIN)
                            .noOcclusion()));

    public static final DeferredBlock<CopperChainBlock> WAXED_WEATHERED_COPPER_CHAIN =
            registerBlock("waxed_weathered_copper_chain",
                    () -> new CopperChainBlock(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.of()
                            .strength(4.0f)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.CHAIN)
                            .noOcclusion()));

    public static final DeferredBlock<CopperChainBlock> WAXED_OXIDIZED_COPPER_CHAIN =
            registerBlock("waxed_oxidized_copper_chain",
                    () -> new CopperChainBlock(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.of()
                            .strength(4.0f)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.CHAIN)
                            .noOcclusion()));

    public static final DeferredBlock<SteelChainBlock> STEEL_CHAIN =
            registerBlock("steel_chain",
                    () -> new SteelChainBlock(BlockBehaviour.Properties.of()
                            .strength(5.0f)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.CHAIN)
                            .noOcclusion()));

    // OXIDIZED COPPER BARS (final stage — no random ticks)
    public static final DeferredBlock<CopperBarsBlock> OXIDIZED_COPPER_BARS = registerBlock("oxidized_copper_bars",
            () -> new CopperBarsBlock(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.of()
                    .strength(4.0f)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()));

    public static final DeferredBlock<LeafLitterBlock> LEAF_LITTER = registerBlock("leaf_litter",
            () -> new LeafLitterBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .noOcclusion()
                    .isViewBlocking((s, r, p) -> false)
                    .isSuffocating((s, r, p) -> false)),
            block -> new FuelItem(block, new Item.Properties(), 100));

    public static final DeferredBlock<StickBlock> STICK_A = registerBlock("stick_a",
            () -> new StickBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .instabreak()
                    .sound(SoundType.WOOD)
                    .noOcclusion()
                    .isViewBlocking((s, r, p) -> false)
                    .isSuffocating((s, r, p) -> false)),
            block -> new FuelItem(block, new Item.Properties(), 120));

    public static final DeferredBlock<StickBlock> STICK_B = registerBlock("stick_b",
            () -> new StickBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .instabreak()
                    .sound(SoundType.WOOD)
                    .noOcclusion()
                    .isViewBlocking((s, r, p) -> false)
                    .isSuffocating((s, r, p) -> false)),
            block -> new FuelItem(block, new Item.Properties(), 120));

    public static final DeferredBlock<StickBlock> STICK_C = registerBlock("stick_c",
            () -> new StickBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .instabreak()
                    .sound(SoundType.WOOD)
                    .noOcclusion()
                    .isViewBlocking((s, r, p) -> false)
                    .isSuffocating((s, r, p) -> false)),
            block -> new FuelItem(block, new Item.Properties(), 120));

    public static final DeferredBlock<DemarcationPostBlock> DEMAECATION_POST = registerBlock("demarcation_post_a",
            () -> new DemarcationPostBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.STONE)
                    .noOcclusion()));

    public static final DeferredBlock<DemarcationPostBlock> DEMAECATION_POST_B = registerBlock("demarcation_post_b",
            () -> new DemarcationPostBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.STONE)
                    .noOcclusion()));

    public static final DeferredBlock<DemarcationPostBlock> DEMAECATION_POST_C = registerBlock("demarcation_post_c",
            () -> new DemarcationPostBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.STONE)
                    .noOcclusion()));

    public static final DeferredBlock<DemarcationPostBlock> DEMAECATION_POST_D = registerBlock("demarcation_post_d",
            () -> new DemarcationPostBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.STONE)
                    .noOcclusion()));

    public static final DeferredBlock<DemarcationPostBlock> DEMAECATION_POST_E = registerBlock("demarcation_post_e",
            () -> new DemarcationPostBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.STONE)
                    .noOcclusion()));

    public static final DeferredBlock<GravestoneBlock> BLACKSTONE_GRAVESTONE_A = registerBlock("blackstone_gravestone_a",
            () -> new GravestoneBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.STONE)
                    .noOcclusion()));

    public static final DeferredBlock<GravestoneBlock> BLACKSTONE_GRAVESTONE_B = registerBlock("blackstone_gravestone_b",
            () -> new GravestoneBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.STONE)
                    .noOcclusion()));

    public static final DeferredBlock<GravestoneBlock> COBBLESTONE_GRAVESTONE_A = registerBlock("cobblestone_gravestone_a",
            () -> new GravestoneBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .sound(SoundType.STONE)
                    .noOcclusion()));

    public static final DeferredBlock<GravestoneBlock> COBBLESTONE_GRAVESTONE_B = registerBlock("cobblestone_gravestone_b",
            () -> new GravestoneBlock(BlockBehaviour.Properties.of()
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
    public static final DeferredBlock<Block> COFFEE_CROP = BLOCKS.register("coffee_crop",
            () -> new CoffeeCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));
    public static final DeferredBlock<Block> TEA_CROP = BLOCKS.register("tea_crop",
            () -> new TeaCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));

    public static final DeferredBlock<Block> FLAX_FLOWER_BLOCK = BLOCKS.register("flax_flower",
            () -> new FlaxFlowerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)));

    public static final DeferredBlock<Block> YUCCA_PLANT_BLOCK = BLOCKS.register("yucca_plant",
            () -> new YuccaPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)));

    public static final DeferredBlock<Block> CUSTOM_OAK_SAPLING = registerBlock("oak_sapling",
            () -> new ModOakSaplingBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    public static final DeferredBlock<Block> CUSTOM_OAK_LEAVES = registerBlock("oak_leaves",
            () -> new ModOakLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

            });

    public static final DeferredBlock<Block> APPLE_LEAVES = registerBlock("apple_leaves",
            () -> new ModAppleLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

            });

    public static final DeferredBlock<Block> APPLE_SAPLING = registerBlock("apple_sapling",
            () -> new ModAppleSaplingBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    public static final DeferredBlock<Block> AVOCADO_LEAVES = registerBlock("avocado_leaves",
            () -> new ModAvocadoLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_LEAVES)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

            });

    public static final DeferredBlock<Block> AVOCADO_SAPLING = registerBlock("avocado_sapling",
            () -> new ModAvocadoSaplingBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    public static final DeferredBlock<Block> ORANGE_LEAVES = registerBlock("orange_leaves",
            () -> new ModOrangeLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_LEAVES)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

            });

    public static final DeferredBlock<Block> ORANGE_SAPLING = registerBlock("orange_sapling",
            () -> new ModOrangeSaplingBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    public static final DeferredBlock<Block> BANANA_LEAVES = registerBlock("banana_leaves",
            () -> new ModBananaLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_LEAVES)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

            });

    public static final DeferredBlock<Block> BANANA_SAPLING = registerBlock("banana_sapling",
            () -> new ModBananaSaplingBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    public static final DeferredBlock<Block> PEAR_LEAVES = registerBlock("pear_leaves",
            () -> new ModPearLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }
            });

    public static final DeferredBlock<Block> PEAR_SAPLING = registerBlock("pear_sapling",
            () -> new ModPearSaplingBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    public static final DeferredBlock<Block> CHERRY_LEAVES = registerBlock("cherry_leaves",
            () -> new ModCherryLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }
            });

    public static final DeferredBlock<Block> CHERRY_SAPLING = registerBlock("cherry_sapling",
            () -> new ModCherrySaplingBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    public static final DeferredBlock<Block> BLACKCURRANT_BUSH = BLOCKS.register("blackcurrant_bush",
            () -> new BlackcurrantBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));

    public static final DeferredBlock<Block> BLUEBERRY_BUSH = BLOCKS.register("blueberry_bush",
            () -> new BlueberryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));

    public static final DeferredBlock<Block> GOOSEBERRY_BUSH = BLOCKS.register("gooseberry_bush",
            () -> new GooseberryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));

    public static final DeferredBlock<Block> STRAWBERRY_BUSH = BLOCKS.register("strawberry_bush",
            () -> new StrawberryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));

    public static final DeferredBlock<PebbleBlock> PEBBLE_BLOCK = registerBlock(
            "pebble",
            () -> new PebbleBlock(BlockBehaviour.Properties.of().strength(0.5f).noOcclusion()),
            block -> new PebbleItem(block, new Item.Properties().stacksTo(16)) // ✅ custom throwable BlockItem
    );


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