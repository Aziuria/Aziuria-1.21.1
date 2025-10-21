package net.Aziuria.aziuriamod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class NearbyOreCommand {

    private static final SuggestionProvider<CommandSourceStack> ORE_SUGGESTIONS = (context, builder) -> {
        for (Block block : BuiltInRegistries.BLOCK) {
            ResourceLocation id = BuiltInRegistries.BLOCK.getKey(block);
            if (id != null && isOreLike(id)) {
                builder.suggest(id.getPath());
            }
        }
        return builder.buildFuture();
    };

    private static boolean isOreLike(ResourceLocation id) {
        String path = id.getPath();
        return (path.contains("ore") ||
                path.contains("deepslate_") ||
                path.contains("ancient_debris") ||
                path.contains("nether_gold_ore") ||
                path.contains("nether_quartz_ore") ||
                path.contains("glowstone"))
                && !(path.contains("core") ||
                path.contains("spore") ||
                path.contains("blossom") ||
                path.contains("slab") ||
                path.contains("stairs") ||
                path.contains("wall") ||
                path.contains("brick") ||
                path.contains("bricks") ||
                path.contains("tile"));
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("nearbyore")
                .requires(source -> source.hasPermission(2))
                .then(Commands.argument("ore", StringArgumentType.string())
                        .suggests(ORE_SUGGESTIONS)
                        .then(Commands.argument("distance", StringArgumentType.string())
                                .suggests((context, builder) -> builder
                                        .suggest("25")
                                        .suggest("50")
                                        .suggest("75")
                                        .suggest("100")
                                        .buildFuture())
                                .executes(NearbyOreCommand::execute))));
    }

    private static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer player = context.getSource().getPlayerOrException();
        ServerLevel world = player.serverLevel();

        String oreId = StringArgumentType.getString(context, "ore");
        String distanceStr = StringArgumentType.getString(context, "distance");

        int radius;
        String distanceBand;
        switch (distanceStr) {
            case "25" -> { radius = 25; distanceBand = "in range of 25 blocks"; }
            case "50" -> { radius = 50; distanceBand = "in range of 50 blocks"; }
            case "75" -> { radius = 75; distanceBand = "in range of 75 blocks"; }
            case "100" -> { radius = 100; distanceBand = "in range of 100 blocks"; }
            default -> { radius = 50; distanceBand = "in range of 50 blocks"; }
        }

        // MOD-FRIENDLY block lookup by path
        Block targetBlock = null;
        for (Block block : BuiltInRegistries.BLOCK) {
            ResourceLocation id = BuiltInRegistries.BLOCK.getKey(block);
            if (id != null && id.getPath().equalsIgnoreCase(oreId)) {
                targetBlock = block;
                break;
            }
        }

        if (targetBlock == null || targetBlock.defaultBlockState().isAir()) {
            context.getSource().sendFailure(Component.literal("Unknown ore block: " + oreId));
            return 0;
        }

        BlockPos center = player.blockPosition();
        int count = 0;

        // Sectors: N, NE, E, SE, S, SW, W, NW
        int[] sectorCounts = new int[8];
        String[] sectorNames = {"North", "Northeast", "East", "Southeast", "South", "Southwest", "West", "Northwest"};

        for (BlockPos pos : BlockPos.betweenClosed(
                center.offset(-radius, -radius, -radius),
                center.offset(radius, radius, radius))) {

            double distanceSq = pos.distSqr(center);
            if (distanceSq <= radius * radius) {
                BlockState state = world.getBlockState(pos);
                if (state.is(targetBlock) && isOreLike(BuiltInRegistries.BLOCK.getKey(state.getBlock()))) {
                    count++;

                    // Determine sector for this ore
                    double dx = pos.getX() - center.getX();
                    double dz = pos.getZ() - center.getZ();
                    double angle = Math.toDegrees(Math.atan2(-dz, dx));
                    if (angle < 0) angle += 360;
                    int sectorIndex = (int)Math.floor(((angle + 22.5) % 360) / 45);
                    sectorCounts[sectorIndex]++;
                }
            }
        }

        // Format ore name
        String oreName = oreId.replace("_", " ");
        String[] words = oreName.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].substring(0,1).toUpperCase() + words[i].substring(1);
        }
        oreName = String.join(" ", words);
        final String finalOreName = oreName;
        String plural = count == 1 ? "" : "s";

        if (count > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Found ").append(count).append(" ").append(finalOreName).append(plural)
                    .append(" ").append(distanceBand).append(":\n");
            for (int i = 0; i < 8; i++) {
                if (sectorCounts[i] > 0) {
                    sb.append("- ").append(sectorCounts[i]).append(" toward ").append(sectorNames[i]).append("\n");
                }
            }
            context.getSource().sendSuccess(() -> Component.literal(sb.toString()), false);
        } else {
            context.getSource().sendSuccess(() -> Component.literal("No " + finalOreName + " detected within the selected range."), false);
        }

        return count;
    }
}