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
                    ShelfBlockEntity::new, ModBlocks.OAK_SHELF.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}