package net.Aziuria.aziuriamod.block.entity;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.Aziuria.aziuriamod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, AziuriaMod.MOD_ID);

    public static final Supplier<BlockEntityType<ShelfBlockEntity>> SHELF_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("shelf_block_entity", () -> BlockEntityType.Builder.of(
                    ShelfBlockEntity::new,
                    ModBlocks.OAK_SHELF.get(),
                    ModBlocks.BIRCH_SHELF.get(),
                    ModBlocks.SPRUCE_SHELF.get(),
                    ModBlocks.JUNGLE_SHELF.get(),
                    ModBlocks.DARK_OAK_SHELF.get(),
                    ModBlocks.BAMBOO_SHELF.get(),
                    ModBlocks.ACACIA_SHELF.get(),
                    ModBlocks.CHERRY_SHELF.get(),
                    ModBlocks.MANGROVE_SHELF.get()
            ).build(null));

    public static final Supplier<BlockEntityType<StorageBlockEntity>> STORAGE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("storage_block_entity", () -> BlockEntityType.Builder.of(
                    StorageBlockEntity::new,
                    ModBlocks.OAK_STORAGE.get(),
                    ModBlocks.BAMBOO_STORAGE.get(),
                    ModBlocks.BIRCH_STORAGE.get(),
                    ModBlocks.SPRUCE_STORAGE.get(),
                    ModBlocks.DARK_OAK_STORAGE.get(),
                    ModBlocks.JUNGLE_STORAGE.get(),
                    ModBlocks.MANGROVE_STORAGE.get(),
                    ModBlocks.CHERRY_STORAGE.get(),
                    ModBlocks.ACACIA_STORAGE.get()

            ).build(null));

    public static final Supplier<BlockEntityType<HookBlockEntity>> HOOK_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("hook_block_entity", () -> BlockEntityType.Builder.of(
                    HookBlockEntity::new,
                    ModBlocks.OAK_HOOK.get(),
                    ModBlocks.BIRCH_HOOK.get(),
                    ModBlocks.SPRUCE_HOOK.get(),
                    ModBlocks.JUNGLE_HOOK.get(),
                    ModBlocks.DARK_OAK_HOOK.get(),
                    ModBlocks.BAMBOO_HOOK.get(),
                    ModBlocks.ACACIA_HOOK.get(),
                    ModBlocks.CHERRY_HOOK.get(),
                    ModBlocks.MANGROVE_HOOK.get()
            ).build(null));

    public static final Supplier<BlockEntityType<SteelBarrelBlockEntity>> STEEL_BARREL_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("steel_barrel_block_entity", () ->
                    BlockEntityType.Builder.of(
                            SteelBarrelBlockEntity::new,
                            ModBlocks.STEEL_BARREL_EMPTY.get(),
                            ModBlocks.IRON_BARREL_EMPTY.get()
                    ).build(null)

            );

    public static final Supplier<BlockEntityType<SpeakerBlockEntity>> SPEAKER =
            BLOCK_ENTITIES.register("speaker", () ->
                    BlockEntityType.Builder.of(
                            SpeakerBlockEntity::new,
                            ModBlocks.SPEAKER.get()
                    ).build(null)
            );

    public static final Supplier<BlockEntityType<WoodcutterBenchBlockEntity>> WOODCUTTER_BENCH =
            BLOCK_ENTITIES.register("woodcutter_bench", () ->
                    BlockEntityType.Builder.of(
                            WoodcutterBenchBlockEntity::new,
                            ModBlocks.WOODCUTTER_BENCH.get()
                    ).build(null)
            );

    public static final Supplier<BlockEntityType<MinerBenchBlockEntity>> MINER_BENCH =
            BLOCK_ENTITIES.register("miner_bench", () ->
                    BlockEntityType.Builder.of(
                            MinerBenchBlockEntity::new,
                            ModBlocks.MINER_BENCH.get()
                    ).build(null)
            );


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}