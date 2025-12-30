package net.Aziuria.aziuriamod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.Aziuria.aziuriamod.handler.WoodMiningRestrictionHandler;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class WoodMiningRestrictionCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("woodmining")
                .then(Commands.argument("mode", StringArgumentType.word())
                        .suggests((ctx, builder) -> {
                            builder.suggest("on");
                            builder.suggest("off");
                            return builder.buildFuture();
                        })
                        .then(Commands.argument("target", EntityArgument.player())
                                .executes(ctx -> toggle(
                                        ctx.getSource(),
                                        StringArgumentType.getString(ctx, "mode"),
                                        EntityArgument.getPlayer(ctx, "target")
                                ))
                        )
                        .executes(ctx -> {
                            ServerPlayer self = ctx.getSource().getPlayerOrException();
                            return toggle(
                                    ctx.getSource(),
                                    StringArgumentType.getString(ctx, "mode"),
                                    self
                            );
                        })
                )
        );
    }

    private static int toggle(CommandSourceStack source, String mode, ServerPlayer target) {
        ServerPlayer executor;
        try {
            executor = source.getPlayerOrException();
        } catch (CommandSyntaxException e) {
            source.sendFailure(Component.literal("Command must be run by a player."));
            return 0;
        }

        boolean canToggleOthers = executor.isCreative() || source.hasPermission(2);

        if (!target.equals(executor) && !canToggleOthers) {
            source.sendFailure(Component.literal("You don't have permission to toggle this for others."));
            return 0;
        }

        if (mode.equalsIgnoreCase("on")) {
            WoodMiningRestrictionHandler.setEnabled(target, true);
            source.sendSuccess(() ->
                            Component.literal("Wood mining restriction ENABLED for " + target.getName().getString()),
                    false
            );
        } else if (mode.equalsIgnoreCase("off")) {
            WoodMiningRestrictionHandler.setEnabled(target, false);
            source.sendSuccess(() ->
                            Component.literal("Wood mining restriction DISABLED for " + target.getName().getString()),
                    false
            );
        } else {
            source.sendFailure(Component.literal("Invalid mode. Use 'on' or 'off'."));
            return 0;
        }

        return 1;
    }
}