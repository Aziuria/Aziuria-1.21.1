package net.Aziuria.aziuriamod.block.custom.vegetation.flower;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.block.Block;

public class FlaxFlowerBlock extends FlowerBlock {
    protected static final VoxelShape SHAPE = Block.box(2, 0, 2, 14, 13, 14);

    public FlaxFlowerBlock(BlockBehaviour.Properties properties) {
        super(MobEffects.LUCK, 5, properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}