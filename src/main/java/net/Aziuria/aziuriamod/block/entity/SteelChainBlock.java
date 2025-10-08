package net.Aziuria.aziuriamod.block.entity;

import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class SteelChainBlock extends ChainBlock implements SimpleWaterloggedBlock {

    public SteelChainBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

}