package net.Aziuria.aziuriamod.command;


import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BreakRegionCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("breakregion")
                .then(Commands.argument("x1", IntegerArgumentType.integer())
                        .then(Commands.argument("y1", IntegerArgumentType.integer())
                                .then(Commands.argument("z1", IntegerArgumentType.integer())
                                        .then(Commands.argument("x2", IntegerArgumentType.integer())
                                                .then(Commands.argument("y2", IntegerArgumentType.integer())
                                                        .then(Commands.argument("z2", IntegerArgumentType.integer())
                                                                .executes(ctx -> {
                                                                    ServerLevel level = ctx.getSource().getLevel();
                                                                    ServerPlayer player = ctx.getSource().getPlayerOrException();

                                                                    BlockPos pos1 = new BlockPos(
                                                                            IntegerArgumentType.getInteger(ctx, "x1"),
                                                                            IntegerArgumentType.getInteger(ctx, "y1"),
                                                                            IntegerArgumentType.getInteger(ctx, "z1")
                                                                    );
                                                                    BlockPos pos2 = new BlockPos(
                                                                            IntegerArgumentType.getInteger(ctx, "x2"),
                                                                            IntegerArgumentType.getInteger(ctx, "y2"),
                                                                            IntegerArgumentType.getInteger(ctx, "z2")
                                                                    );

                                                                    breakBlocksWithDrops(level, pos1, pos2, player);
                                                                    return 1;
                                                                }))))))));
    }

    private static void breakBlocksWithDrops(ServerLevel level, BlockPos start, BlockPos end, ServerPlayer player) {
        int x1 = Math.min(start.getX(), end.getX());
        int y1 = Math.min(start.getY(), end.getY());
        int z1 = Math.min(start.getZ(), end.getZ());

        int x2 = Math.max(start.getX(), end.getX());
        int y2 = Math.max(start.getY(), end.getY());
        int z2 = Math.max(start.getZ(), end.getZ());

        BlockPos.betweenClosedStream(new BlockPos(x1, y1, z1), new BlockPos(x2, y2, z2)).forEach(pos -> {
            BlockState state = level.getBlockState(pos);
            if (!state.isAir()) {
                BlockEntity be = level.getBlockEntity(pos);
                state.onDestroyedByPlayer(level, pos, player, true, level.getFluidState(pos));
                state.getBlock().playerDestroy(level, player, pos, state, be, player.getMainHandItem());
            }
        });
    }
}