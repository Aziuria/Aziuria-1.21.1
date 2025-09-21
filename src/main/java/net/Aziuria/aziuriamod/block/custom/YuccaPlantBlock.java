package net.Aziuria.aziuriamod.block.custom;

import net.Aziuria.aziuriamod.item.ModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.phys.shapes.CollisionContext;

public class YuccaPlantBlock extends Block {
    // Slightly taller and narrower shape than flax, adjust as needed
    protected static final VoxelShape SHAPE = Block.box(3, 0, 3, 13, 16, 13);

    public YuccaPlantBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

//    @Override
//    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
//        return new ItemStack(ModItems.YUCCA_LEAVES.get());
//    }

}