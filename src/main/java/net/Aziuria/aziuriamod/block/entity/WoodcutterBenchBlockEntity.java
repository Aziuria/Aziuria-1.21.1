package net.Aziuria.aziuriamod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class WoodcutterBenchBlockEntity extends BlockEntity {

    public WoodcutterBenchBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.WOODCUTTER_BENCH.get(), pos, state);
    }

    // No inventory or NBT needed here
}