package net.Aziuria.aziuriamod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.Aziuria.aziuriamod.handler.FastLeafDecayHandler;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class DecayCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("decay")
                        .requires(source -> source.hasPermission(2))
                        .then(Commands.literal("rate")
                                .then(Commands.argument("seconds", IntegerArgumentType.integer(1, 120))
                                        .executes(ctx -> {
                                            int seconds = IntegerArgumentType.getInteger(ctx, "seconds");
                                            FastLeafDecayHandler.setDecayTimeSeconds(seconds);
                                            ctx.getSource().sendSuccess(
                                                    () -> Component.literal("Leaf decay rate set to " + seconds + " seconds."),
                                                    false
                                            );
                                            return 1;
                                        })
                                )
                        )
                        .then(Commands.literal("rate")
                                .executes(ctx -> {
                                    int current = FastLeafDecayHandler.getDecayTimeSeconds();
                                    ctx.getSource().sendSuccess(
                                            () -> Component.literal("Current leaf decay rate: " + current + " seconds."),
                                            false
                                    );
                                    return 1;
                                })
                        )
        );
    }
}