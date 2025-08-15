package net.Aziuria.aziuriamod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.FallingBlock;

public class RefreshGravityBlocksCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("refreshGravityBlocks")
                .requires(source -> source.hasPermission(2)) // OP only
                .executes(RefreshGravityBlocksCommand::execute));
    }

    private static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer player = context.getSource().getPlayerOrException();
        ServerLevel world = player.serverLevel();

        BlockPos center = player.blockPosition();
        int radius = 64;
        int count = 0;

        BlockPos min = center.offset(-radius, -radius, -radius);
        BlockPos max = center.offset(radius, radius, radius);

        for (BlockPos pos : BlockPos.betweenClosed(min, max)) {
            double distanceSq = pos.distSqr(center);
            if (distanceSq <= radius * radius) { // Sphere check
                if (world.getBlockState(pos).getBlock() instanceof FallingBlock) {
                    world.scheduleTick(pos, world.getBlockState(pos).getBlock(), 1);
                    count++;
                }
            }
        }

        final int refreshedCount = count;
        context.getSource().sendSuccess(
                () -> Component.literal("Refreshed " + refreshedCount + " gravity blocks within " + radius + " blocks."),
                false
        );

        return refreshedCount;
    }
}