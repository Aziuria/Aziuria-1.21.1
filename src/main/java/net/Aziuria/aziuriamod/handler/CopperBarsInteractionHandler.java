package net.Aziuria.aziuriamod.handler;

import net.Aziuria.aziuriamod.datamaps.ModDataMapHooks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent.RightClickBlock;

@EventBusSubscriber(modid = "aziuriamod")
public class CopperBarsInteractionHandler {

    @SubscribeEvent
    public static void onRightClickBlock(RightClickBlock event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        Player player = event.getEntity();
        InteractionHand hand = event.getHand();
        ItemStack heldItem = player.getItemInHand(hand);
        BlockState state = level.getBlockState(pos);
        Block block = state.getBlock();

        // Map InteractionHand to EquipmentSlot for hurtAndBreak
        EquipmentSlot slot = hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;

        // --- Waxing: Right-click with honeycomb ---
        if (heldItem.is(Items.HONEYCOMB)) {
            Block waxed = ModDataMapHooks.getWaxed(block);
            if (waxed != block) {
                BlockState newState = copyBarProperties(waxed.defaultBlockState(), state);
                level.setBlock(pos, newState, 3);

                level.levelEvent(3003, pos, 0);
                level.playSound(null, pos, SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1.0F, 1.0F);
                player.swing(hand, true);

                if (!player.isCreative()) heldItem.shrink(1);

                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
                return;
            }
        }

        // --- Axe interaction: Unwaxing or Scraping ---
        if (heldItem.getItem() instanceof AxeItem) {

            // --- Unwaxing ---
            Block unwaxed = ModDataMapHooks.getUnwaxed(block);
            if (unwaxed != block) {
                BlockState newState = copyBarProperties(unwaxed.defaultBlockState(), state);
                level.setBlock(pos, newState, 3);

                if (!player.isCreative()) {
                    heldItem.hurtAndBreak(1, player, slot);
                }

                level.levelEvent(3004, pos, 0);
                level.playSound(null, pos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
                player.swing(hand, true);

                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
                return;
            }

            // --- Scraping (oxidized versions) ---
            Block scraped = ModDataMapHooks.getScraped(block);
            if (scraped != block) {
                BlockState newState = copyBarProperties(scraped.defaultBlockState(), state);
                level.setBlock(pos, newState, 3);

                if (!player.isCreative()) {
                    heldItem.hurtAndBreak(1, player, slot);
                }

                level.levelEvent(3005, pos, 0);
                level.playSound(null, pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
                player.swing(hand, true);

                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
            }
        }
    }

    /** Utility: Copy bar connection properties from one state to another */
    private static BlockState copyBarProperties(BlockState target, BlockState original) {
        if (original.hasProperty(BlockStateProperties.AXIS))  target = target.setValue(BlockStateProperties.AXIS, original.getValue(BlockStateProperties.AXIS));
        if (original.hasProperty(BlockStateProperties.NORTH)) target = target.setValue(BlockStateProperties.NORTH, original.getValue(BlockStateProperties.NORTH));
        if (original.hasProperty(BlockStateProperties.EAST))  target = target.setValue(BlockStateProperties.EAST, original.getValue(BlockStateProperties.EAST));
        if (original.hasProperty(BlockStateProperties.SOUTH)) target = target.setValue(BlockStateProperties.SOUTH, original.getValue(BlockStateProperties.SOUTH));
        if (original.hasProperty(BlockStateProperties.WEST))  target = target.setValue(BlockStateProperties.WEST, original.getValue(BlockStateProperties.WEST));
        if (original.hasProperty(BlockStateProperties.UP))    target = target.setValue(BlockStateProperties.UP, original.getValue(BlockStateProperties.UP));
        if (original.hasProperty(BlockStateProperties.WATERLOGGED)) target = target.setValue(BlockStateProperties.WATERLOGGED, original.getValue(BlockStateProperties.WATERLOGGED));
        return target;
    }
}