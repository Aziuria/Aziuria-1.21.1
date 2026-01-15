package net.Aziuria.aziuriamod.item.custom.tools;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class MalletItem extends SwordItem {

    public MalletItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack stack) {
        ItemStack copy = stack.copy();
        copy.setDamageValue(copy.getDamageValue() + 1);
        if (copy.getDamageValue() >= copy.getMaxDamage()) {
            return ItemStack.EMPTY;
        }
        return copy;
    }

//    @Override
//    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
//        if (!level.isClientSide && isPoundable(state.getBlock())) {
//            // Drop bits instead of full block
//            Block bitBlock = getBitForBlock(state.getBlock());
//            if (bitBlock != null) {
//                Block.popResource(level, pos, new ItemStack(bitBlock, 1));
//            }
//
//            // Apply durability damage — same as KnifeItem
//            stack.hurtAndBreak(1, entity, EquipmentSlot.MAINHAND);
//
//            // Remove the original block
//            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
//
//            return true; // block handled
//        }
//
//        return super.mineBlock(stack, level, state, pos, entity);
//    }
//
//    private boolean isPoundable(Block block) {
//        return block == Blocks.SANDSTONE
//                || block == Blocks.RED_SANDSTONE
//                || block == Blocks.STONE
//                || block == ModBlocks.SPECTRAL_ORE.get();
//    }
//
//    private Block getBitForBlock(Block block) {
//        if (block == Blocks.SANDSTONE) return ModBlocks.SANDSTONE_BIT.get();
//        if (block == Blocks.RED_SANDSTONE) return ModBlocks.RED_SANDSTONE_BIT.get();
//        if (block == Blocks.STONE) return ModBlocks.STONE_BIT.get();
//        if (block == ModBlocks.SPECTRAL_ORE.get()) return ModBlocks.SPECTRAL_ORE_BIT.get();
//        return null;
//    }
}