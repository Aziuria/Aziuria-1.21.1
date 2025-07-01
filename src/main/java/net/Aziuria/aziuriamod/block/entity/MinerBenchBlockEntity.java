package net.Aziuria.aziuriamod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class MinerBenchBlockEntity extends BlockEntity {

    public MinerBenchBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MINER_BENCH.get(), pos, state);
    }

    // No inventory or NBT needed here
}