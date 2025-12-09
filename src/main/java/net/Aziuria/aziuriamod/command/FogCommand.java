package net.Aziuria.aziuriamod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.Aziuria.aziuriamod.fog.FogEventManager;
import net.Aziuria.aziuriamod.fog.FogRegistry;
import net.Aziuria.aziuriamod.fog.FogType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;

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
                                    builder.suggest("stop");
                                    builder.suggest("disable"); // NEW: suggest disable
                                    builder.suggest("enable");  // NEW: suggest enable
                                    return builder.buildFuture();
                                })
                                .executes(ctx -> {
                                    String id = StringArgumentType.getString(ctx, "type");

                                    if ("stop".equalsIgnoreCase(id)) {
                                        FogEventManager.stopFogNow();
                                        ctx.getSource().sendSuccess(() -> Component.literal("Fog stopped."), false);
                                        return 1;
                                    }

                                    // --- NEW: DISABLE fog ---
                                    if ("disable".equalsIgnoreCase(id)) {
                                        FogEventManager.setFogEnabled(false);
                                        ctx.getSource().sendSuccess(() -> Component.literal("Fog has been disabled."), false);
                                        return 1;
                                    }

                                    // --- NEW: ENABLE fog ---
                                    if ("enable".equalsIgnoreCase(id)) {
                                        FogEventManager.setFogEnabled(true);
                                        ctx.getSource().sendSuccess(() -> Component.literal("Fog has been enabled."), false);
                                        return 1;
                                    }

                                    FogType match = FogRegistry.getAll().stream()
                                            .filter(f -> f.getId().equals(id))
                                            .findFirst()
                                            .orElse(null);

                                    if (match != null) {
                                        // Check if fog is enabled
                                        if (!FogEventManager.isFogEnabled()) {
                                            ctx.getSource().sendFailure(Component.literal("Fog is currently disabled."));
                                            return 0;
                                        }

                                        // --- Prevent evil fog during daytime ---
                                        if ("evil".equals(match.getId())) {
                                            if (ctx.getSource().getLevel() instanceof ServerLevel serverLevel) {
                                                long time = serverLevel.getDayTime() % 24000L;
                                                if (time < 13000L) { // before night
                                                    ctx.getSource().sendFailure(Component.literal("Evil fog cannot be started during the day."));
                                                    return 0;
                                                }
                                            }
                                        }

                                        // Start the fog
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