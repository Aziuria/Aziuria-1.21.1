package net.Aziuria.aziuriamod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.Aziuria.aziuriamod.handler.VeinMinerHandler;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;

public class VeinMinerCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("veinminer")
                .then(Commands.argument("mode", StringArgumentType.word())
                        .suggests((ctx, builder) -> {
                            builder.suggest("on");
                            builder.suggest("off");
                            return builder.buildFuture();
                        })
                        // Optional player argument
                        .then(Commands.argument("target", EntityArgument.player())
                                .executes(ctx -> {
                                    return toggleVeinMiner(ctx.getSource(),
                                            StringArgumentType.getString(ctx, "mode"),
                                            EntityArgument.getPlayer(ctx, "target"));
                                })
                        )
                        // Without target - toggles for self
                        .executes(ctx -> {
                            ServerPlayer player = ctx.getSource().getPlayerOrException();
                            return toggleVeinMiner(ctx.getSource(), StringArgumentType.getString(ctx, "mode"), player);
                        })
                )
        );
    }

    private static int toggleVeinMiner(CommandSourceStack source, String mode, ServerPlayer target) {
        ServerPlayer executor;
        try {
            executor = source.getPlayerOrException();
        } catch (CommandSyntaxException e) {
            source.sendFailure(Component.literal("Command must be run by a player."));
            return 0;
        }

        // Permission check:
        boolean canToggleOthers = executor.isCreative() || source.hasPermission(2);

        if (!target.equals(executor) && !canToggleOthers) {
            source.sendFailure(Component.literal("You don't have permission to toggle vein miner for other players."));
            return 0;
        }

        if (mode.equalsIgnoreCase("on")) {
            VeinMinerHandler.setEnabled(target, true);
            source.sendSuccess(() -> Component.literal("Vein miner ENABLED for " + target.getName().getString()), false);
        } else if (mode.equalsIgnoreCase("off")) {
            VeinMinerHandler.setEnabled(target, false);
            source.sendSuccess(() -> Component.literal("Vein miner DISABLED for " + target.getName().getString()), false);
        } else {
            source.sendFailure(Component.literal("Invalid mode. Use 'on' or 'off'."));
            return 0;
        }

        return 1;
    }
}