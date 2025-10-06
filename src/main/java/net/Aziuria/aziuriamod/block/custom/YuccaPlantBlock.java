package net.Aziuria.aziuriamod.block.custom;

import net.Aziuria.aziuriamod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;
import java.util.ArrayList;

public class YuccaPlantBlock extends Block {
    protected static final VoxelShape SHAPE = Block.box(3, 0, 3, 13, 16, 13);

    public YuccaPlantBlock(BlockBehaviour.Properties properties) {
        super(properties
                .noCollission()
                .instabreak()
                .noOcclusion()
        );
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
        if (facing == Direction.DOWN) {
            BlockState groundState = world.getBlockState(currentPos.below());
            // Break the plant if the block below is not grass, sand, or red sand
            if (!groundState.is(Blocks.SAND) && !groundState.is(Blocks.RED_SAND) && !groundState.is(Blocks.GRASS_BLOCK)) {
                return Blocks.AIR.defaultBlockState();
            }
        }
        return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        BlockState groundState = context.getLevel().getBlockState(pos.below());
        // Allow placement only on grass, sand, or red sand
        if (!groundState.is(Blocks.SAND) && !groundState.is(Blocks.RED_SAND) && !groundState.is(Blocks.GRASS_BLOCK)) {
            return null; // prevents placement
        }
        return this.defaultBlockState();
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        RandomSource random = builder.getLevel().random;

        // Check if shears are used; if so, drop the block itself
        if (builder.getOptionalParameter(LootContextParams.TOOL) != null &&
                builder.getOptionalParameter(LootContextParams.TOOL).getItem() == Items.SHEARS) {
            return List.of(new ItemStack(this));
        }

        // Normal drops: leaves and sticks
        int leafAmount = 1 + random.nextInt(5); // 1–5 leaves
        int stickAmount = random.nextInt(3);    // 0–2 sticks

        List<ItemStack> drops = new ArrayList<>();
        drops.add(new ItemStack(ModItems.YUCCA_LEAVES.get(), leafAmount));

        if (stickAmount > 0) {
            drops.add(new ItemStack(Items.STICK, stickAmount));
        }

        return drops;
    }
}