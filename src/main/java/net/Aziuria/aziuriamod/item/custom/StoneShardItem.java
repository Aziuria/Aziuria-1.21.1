package net.Aziuria.aziuriamod.item.custom;

import net.Aziuria.aziuriamod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class StoneShardItem extends Item {

    public StoneShardItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true; // durability applies in crafting too
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack stack) {
        ItemStack copy = stack.copy();
        copy.setDamageValue(copy.getDamageValue() + 1);
        if (copy.getDamageValue() >= copy.getMaxDamage()) {
            return ItemStack.EMPTY; // broken
        }
        return copy;
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        if (!level.isClientSide) {
            if (isPrimitiveMineable(state)) {
                // Apply durability damage
                stack.hurtAndBreak(1, entity, EquipmentSlot.MAINHAND);
            } else {
                // Optional: prevent breaking other blocks
                return false;
            }
        }
        return super.mineBlock(stack, level, state, pos, entity);
    }

    private boolean isPrimitiveMineable(BlockState state) {
        // This is what StoneShard can “mine”
        return state.is(Blocks.OAK_LOG)
                || state.is(Blocks.BIRCH_LOG)
                || state.is(Blocks.SPRUCE_LOG)
                || state.is(Blocks.JUNGLE_LOG)
                || state.is(Blocks.DARK_OAK_LOG)
                || state.is(Blocks.ACACIA_LOG)
                || state.is(Blocks.MANGROVE_LOG)
                || state.is(Blocks.CHERRY_LOG);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if (state.is(BlockTags.LOGS)) {
            return 0.5f; // slow but usable on logs
        }
        return 0.6f; // can mine other blocks slowly
    }
}