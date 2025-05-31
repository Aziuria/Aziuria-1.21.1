package net.Aziuria.aziuriamod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.Aziuria.aziuriamod.fog.FogEventManager;
import net.Aziuria.aziuriamod.fog.FogRegistry;
import net.Aziuria.aziuriamod.fog.FogType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class FogCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("triggerfog")
                        .requires(source -> source.hasPermission(2))
                        .then(Commands.argument("type", StringArgumentType.word())
                                .suggests((context, builder) -> {
                                    for (FogType type : FogRegistry.getAll()) {
                                        builder.suggest(type.getId());
                                    }
                                    builder.suggest("stop");  // ← Add "stop" to suggestions
                                    return builder.buildFuture();
                                })
                                .executes(ctx -> {
                                    String id = StringArgumentType.getString(ctx, "type");

                                    if ("stop".equalsIgnoreCase(id)) {  // ← Handle stopping fog command
                                        FogEventManager.stopFogNow();
                                        ctx.getSource().sendSuccess(() -> Component.literal("Fog stopped."), false);
                                        return 1;
                                    }

                                    FogType match = FogRegistry.getAll().stream()
                                            .filter(f -> f.getId().equals(id))
                                            .findFirst()
                                            .orElse(null);

                                    if (match != null) {
                                        FogEventManager.startFogNow(match);
                                        ctx.getSource().sendSuccess(() -> Component.literal("Started fog: " + id), false);
                                        return 1;
                                    } else {
                                        ctx.getSource().sendFailure(Component.literal("No fog type found with ID: " + id));
                                        return 0;
                                    }
                                })
                        )
        );
    }
}