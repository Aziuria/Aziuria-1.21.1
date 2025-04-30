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


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}