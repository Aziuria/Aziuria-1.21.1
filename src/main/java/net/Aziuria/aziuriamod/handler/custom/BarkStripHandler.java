package net.Aziuria.aziuriamod.handler.custom;

import net.Aziuria.aziuriamod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.bus.api.SubscribeEvent;

import java.util.Random;

public class BarkStripHandler {

    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        if (level.isClientSide()) return; // Only server

        BlockPos pos = event.getPos();
        var player = event.getEntity();
        ItemStack stack = event.getItemStack();
        BlockState state = level.getBlockState(pos);
        Block block = state.getBlock();

        // Only axes
        if (!(stack.getItem() instanceof AxeItem)) return;

// Only handle specific logs + wood
        if (!(block == Blocks.OAK_LOG || block == Blocks.OAK_WOOD ||
                block == Blocks.BIRCH_LOG || block == Blocks.BIRCH_WOOD ||
                block == Blocks.SPRUCE_LOG || block == Blocks.SPRUCE_WOOD ||
                block == Blocks.JUNGLE_LOG || block == Blocks.JUNGLE_WOOD ||
                block == Blocks.ACACIA_LOG || block == Blocks.ACACIA_WOOD ||
                block == Blocks.DARK_OAK_LOG || block == Blocks.DARK_OAK_WOOD ||
                block == Blocks.MANGROVE_LOG || block == Blocks.MANGROVE_WOOD ||
                block == Blocks.CHERRY_LOG || block == Blocks.CHERRY_WOOD)) return;

        // Let vanilla handle the actual stripping (do not setBlock here)
        // Just spawn 2–4 bark
        ItemStack barkDrop = getBark(block);
        if (!barkDrop.isEmpty()) {
            barkDrop.setCount(2 + new Random().nextInt(3)); // 2–4
            level.addFreshEntity(new ItemEntity(level,
                    pos.getX() + 0.5,
                    pos.getY() + 0.5,
                    pos.getZ() + 0.5,
                    barkDrop
            ));
        }

        // Do NOT cancel the event, so vanilla AxeItem.useOn() runs and strips the log
        event.setCancellationResult(InteractionResult.PASS);
    }

    private static ItemStack getBark(Block block) {
        if (block == Blocks.OAK_LOG || block == Blocks.OAK_WOOD)
            return new ItemStack(ModItems.OAK_BARK.get());

        if (block == Blocks.BIRCH_LOG || block == Blocks.BIRCH_WOOD)
            return new ItemStack(ModItems.BIRCH_BARK.get());

        if (block == Blocks.SPRUCE_LOG || block == Blocks.SPRUCE_WOOD)
            return new ItemStack(ModItems.SPRUCE_BARK.get());

        if (block == Blocks.JUNGLE_LOG || block == Blocks.JUNGLE_WOOD)
            return new ItemStack(ModItems.JUNGLE_BARK.get());

        if (block == Blocks.ACACIA_LOG || block == Blocks.ACACIA_WOOD)
            return new ItemStack(ModItems.ACACIA_BARK.get());

        if (block == Blocks.DARK_OAK_LOG || block == Blocks.DARK_OAK_WOOD)
            return new ItemStack(ModItems.DARK_OAK_BARK.get());

        if (block == Blocks.MANGROVE_LOG || block == Blocks.MANGROVE_WOOD)
            return new ItemStack(ModItems.MANGROVE_BARK.get());

        if (block == Blocks.CHERRY_LOG || block == Blocks.CHERRY_WOOD)
            return new ItemStack(ModItems.CHERRY_BARK.get());

        return ItemStack.EMPTY;
    }
}