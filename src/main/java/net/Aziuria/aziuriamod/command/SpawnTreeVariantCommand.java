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
        dispatcher.register(Commands.literal("spawn_tree_variant")  // Command name
                .then(Commands.argument("variant", IntegerArgumentType.integer(1, 45)) // Allow variants 1-24
                        .executes(ctx -> {
                            int variant = IntegerArgumentType.getInteger(ctx, "variant");
                            return spawnTreeVariant(ctx.getSource(), variant);  // Spawn method call
                        }))
        );
    }

    private static int spawnTreeVariant(CommandSourceStack source, int variant) {  // Spawn method
        ServerLevel level = source.getLevel();
        BlockPos pos;

        try {
            pos = source.getPlayerOrException().blockPosition();
        } catch (com.mojang.brigadier.exceptions.CommandSyntaxException e) {
            source.sendFailure(Component.literal("Player not found"));
            return 0;
        }

        ConfiguredFeature<?, ?> feature = getTreeVariantFeature(variant, level);  // Fetch feature
        if (feature == null) {
            source.sendFailure(Component.literal("Invalid tree variant: " + variant));
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

    private static ConfiguredFeature<?, ?> getTreeVariantFeature(int variant, ServerLevel level) {  // Variant fetcher
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
            case 11 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.PEAR_KEY_VARIANT_1);
            }
            case 12 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.PEAR_KEY_VARIANT_2);
            }
            case 13 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.PEAR_KEY_VARIANT_3);
            }
            case 14 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.PEAR_KEY_VARIANT_4);
            }
            case 15 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.PEAR_KEY_VARIANT_5);
            }
            case 16 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.PEAR_KEY_VARIANT_6);
            }
            case 17 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.PEAR_KEY_VARIANT_7);
            }
            case 18 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.CHERRY_KEY_VARIANT_1);
            }
            case 19 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.CHERRY_KEY_VARIANT_2);
            }
            case 20 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.CHERRY_KEY_VARIANT_3);
            }
            case 21 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.CHERRY_KEY_VARIANT_4);
            }
            case 22 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.CHERRY_KEY_VARIANT_5);
            }
            case 23 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.CHERRY_KEY_VARIANT_6);
            }
            case 24 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.CHERRY_KEY_VARIANT_7);
            }
            // New avocado variants added here
            case 25 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.AVOCADO_KEY_VARIANT_1);
            }
            case 26 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.AVOCADO_KEY_VARIANT_2);
            }
            case 27 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.AVOCADO_KEY_VARIANT_3);
            }
            case 28 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.AVOCADO_KEY_VARIANT_4);
            }
            case 29 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.AVOCADO_KEY_VARIANT_5);
            }
            case 30 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.AVOCADO_KEY_VARIANT_6);
            }
            case 31 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.AVOCADO_KEY_VARIANT_7);
            }

            // New orange variants added here
            case 32 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.ORANGE_KEY_VARIANT_1);
            }
            case 33 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.ORANGE_KEY_VARIANT_2);
            }
            case 34 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.ORANGE_KEY_VARIANT_3);
            }
            case 35 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.ORANGE_KEY_VARIANT_4);
            }
            case 36 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.ORANGE_KEY_VARIANT_5);
            }
            case 37 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.ORANGE_KEY_VARIANT_6);
            }
            case 38 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.ORANGE_KEY_VARIANT_7);
            }

            // New banana variants added here
            case 39 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.BANANA_KEY_VARIANT_1);
            }
            case 40 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.BANANA_KEY_VARIANT_2);
            }
            case 41 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.BANANA_KEY_VARIANT_3);
            }
            case 42 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.BANANA_KEY_VARIANT_4);
            }
            case 43 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.BANANA_KEY_VARIANT_5);
            }
            case 44 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.BANANA_KEY_VARIANT_6);
            }
            case 45 -> {
                return level.registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                        .get(ModConfiguredFeatures.BANANA_KEY_VARIANT_7);
            }

            default -> {
                return null;
            }
        }
    }
}