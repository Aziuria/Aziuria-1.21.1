package net.Aziuria.aziuriamod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.Aziuria.aziuriamod.worldgen.ModConfiguredFeatures;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class SpawnTreeVariantCommand {  // Renamed class

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("spawn_tree_variant")  // Updated command name here
                .then(Commands.argument("variant", IntegerArgumentType.integer(1, 10)) // variants 1-10 allowed
                        .executes(ctx -> {
                            int variant = IntegerArgumentType.getInteger(ctx, "variant");
                            return spawnTreeVariant(ctx.getSource(), variant);  // Updated method call
                        }))
        );
    }

    private static int spawnTreeVariant(CommandSourceStack source, int variant) {  // Renamed method
        ServerLevel level = source.getLevel();
        BlockPos pos;

        try {
            pos = source.getPlayerOrException().blockPosition();
        } catch (com.mojang.brigadier.exceptions.CommandSyntaxException e) {
            source.sendFailure(Component.literal("Player not found"));
            return 0;
        }

        ConfiguredFeature<?, ?> feature = getTreeVariantFeature(variant, level);  // Renamed method
        if (feature == null) {
            source.sendFailure(Component.literal("Invalid tree variant: " + variant));  // Generic message now
            return 0;
        }

        boolean placed = feature.place(level, level.getChunkSource().getGenerator(), level.getRandom(), pos);
        if (placed) {
            source.sendSuccess(() -> Component.literal("Spawned tree variant " + variant + " at " + pos), true);
            return 1;
        } else {
            source.sendFailure(Component.literal("Failed to spawn tree variant " + variant));
            return 0;
        }
    }

    private static ConfiguredFeature<?, ?> getTreeVariantFeature(int variant, ServerLevel level) {  // Renamed method
        switch (variant) {
            case 1 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.APPLE_KEY_VARIANT_1);
            }
            case 2 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.APPLE_KEY_VARIANT_2);
            }
            case 3 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.APPLE_KEY_VARIANT_3);
            }
            case 4 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.APPLE_KEY_VARIANT_4);
            }
            case 5 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.APPLE_KEY_VARIANT_5);
            }
            case 6 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.APPLE_KEY_VARIANT_6);
            }
            case 7 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.APPLE_KEY_VARIANT_7);
            }
            case 8 -> { // dark oak variant 1
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.DARK_OAK_VARIANT_1);
            }
            case 9 -> { // dark oak variant 2
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.DARK_OAK_VARIANT_2);
            }
            case 10 -> { // birch variant 1
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.BIRCH_VARIANT_1);
            }
            default -> {
                return null;
            }
        }
    }
}