package net.Aziuria.aziuriamod.block;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.Aziuria.aziuriamod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(AziuriaMod.MOD_ID);

    // Define block properties (You can modify the properties as needed)
    public static final BlockBehaviour.Properties SHELF_PROPERTIES = BlockBehaviour.Properties.of()
            .mapColor(MapColor.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.5F)
            .sound(SoundType.WOOD)
            .noOcclusion();


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

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}