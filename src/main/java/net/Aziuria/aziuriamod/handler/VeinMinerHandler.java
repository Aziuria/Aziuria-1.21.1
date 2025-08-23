package net.Aziuria.aziuriamod.handler;

import net.Aziuria.aziuriamod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundBlockDestructionPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

import java.util.*;

public class VeinMinerHandler {

    private static final int MAX_VEIN_SIZE = 25;

    // Hardcoded set of ore blocks - add your mod ores here
    private static final Set<Block> ORE_BLOCKS = Set.of(
            Blocks.COAL_ORE,
            Blocks.IRON_ORE,
            Blocks.COPPER_ORE,
            Blocks.GOLD_ORE,
            Blocks.REDSTONE_ORE,
            Blocks.LAPIS_ORE,
            Blocks.DIAMOND_ORE,
            Blocks.EMERALD_ORE,

            // Deepslate variants
            Blocks.DEEPSLATE_COAL_ORE,
            Blocks.DEEPSLATE_IRON_ORE,
            Blocks.DEEPSLATE_COPPER_ORE,
            Blocks.DEEPSLATE_GOLD_ORE,
            Blocks.DEEPSLATE_REDSTONE_ORE,
            Blocks.DEEPSLATE_LAPIS_ORE,
            Blocks.DEEPSLATE_DIAMOND_ORE,
            Blocks.DEEPSLATE_EMERALD_ORE,
            Blocks.GLOWSTONE,
            Blocks.QUARTZ_BLOCK,

            // Modded ores
            ModBlocks.DEEPSLATE_SULPHUR_ORE.get(),
            ModBlocks.SULPHUR_ORE.get(),
            ModBlocks.DEEPSLATE_POTASSIUM_ORE.get(),
            ModBlocks.POTASSIUM_ORE.get()
    );

    private static final Set<UUID> enabledPlayers = new HashSet<>();

    public static void setEnabled(ServerPlayer player, boolean enabled) {
        if (enabled) {
            enabledPlayers.add(player.getUUID());
        } else {
            enabledPlayers.remove(player.getUUID());
        }
    }

    public static boolean isEnabled(ServerPlayer player) {
        return enabledPlayers.contains(player.getUUID());
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (!(event.getLevel() instanceof ServerLevel level)) return;
        if (!(event.getPlayer() instanceof ServerPlayer player)) return;

        if (!isEnabled(player)) {
            return;
        }

        BlockPos origin = event.getPos();
        BlockState originState = level.getBlockState(origin);
        Block originBlock = originState.getBlock();

        if (!ORE_BLOCKS.contains(originBlock)) {
            return;
        }

        ItemStack tool = player.getMainHandItem();
        if (tool.isEmpty()) {
            return;
        }

        Set<BlockPos> vein = findVein(level, origin, originBlock, MAX_VEIN_SIZE);

        final int breakerEntityId = player.getId();

        for (BlockPos pos : vein) {
            if (pos.equals(origin)) continue;

            BlockState state = level.getBlockState(pos);
            Block block = state.getBlock();

            block.playerDestroy(level, player, pos, state, level.getBlockEntity(pos), tool);
            level.removeBlock(pos, false);

            tool.hurtAndBreak(1, player, net.minecraft.world.entity.EquipmentSlot.MAINHAND);

            ClientboundBlockDestructionPacket packet = new ClientboundBlockDestructionPacket(
                    breakerEntityId,
                    pos,
                    10
            );
            level.getServer().getPlayerList().broadcast(
                    player,
                    pos.getX(), pos.getY(), pos.getZ(),
                    64.0D,
                    level.dimension(),
                    packet
            );

            if (tool.isEmpty()) {
                break;
            }
        }
    }

    private static Set<BlockPos> findVein(ServerLevel level, BlockPos start, Block targetBlock, int maxSize) {
        Set<BlockPos> result = new HashSet<>();
        Queue<BlockPos> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty() && result.size() < maxSize) {
            BlockPos current = queue.poll();
            if (result.contains(current)) continue;

            BlockState state = level.getBlockState(current);
            if (!state.is(targetBlock)) continue;

            result.add(current);

            for (BlockPos offset : BlockPos.betweenClosed(current.offset(-1, -1, -1), current.offset(1, 1, 1))) {
                if (!result.contains(offset) && level.getBlockState(offset).is(targetBlock)) {
                    queue.add(offset.immutable());
                }
            }
        }

        return result;
    }
}