package net.Aziuria.aziuriamod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.registries.BuiltInRegistries;

public class NearbyOreCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("nearbyore")
                .requires(source -> source.hasPermission(2)) // OP-only by default
                .then(Commands.argument("ore", StringArgumentType.string())
                        .then(Commands.argument("radius", IntegerArgumentType.integer(1, 256))
                                .executes(NearbyOreCommand::execute))));
    }

    private static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer player = context.getSource().getPlayerOrException();
        ServerLevel world = player.serverLevel();

        String oreId = StringArgumentType.getString(context, "ore");
        int radius = IntegerArgumentType.getInteger(context, "radius");

        // Parse oreId properly
        ResourceLocation rl = ResourceLocation.tryParse(oreId);
        if (rl == null) {
            context.getSource().sendFailure(Component.literal("Invalid resource location: " + oreId));
            return 0;
        }

        Block targetBlock = BuiltInRegistries.BLOCK.get(rl);
        if (targetBlock == null || targetBlock.defaultBlockState().isAir()) {
            context.getSource().sendFailure(Component.literal("Unknown block: " + oreId));
            return 0;
        }

        BlockPos center = player.blockPosition();
        int count = 0;

        long dxSum = 0;
        long dzSum = 0;

        // Scan spherical radius
        for (BlockPos pos : BlockPos.betweenClosed(
                center.offset(-radius, -radius, -radius),
                center.offset(radius, radius, radius))) {

            double distanceSq = pos.distSqr(center);
            if (distanceSq <= radius * radius) {
                BlockState state = world.getBlockState(pos);
                if (state.is(targetBlock)) {
                    count++;
                    dxSum += pos.getX() - center.getX();
                    dzSum += pos.getZ() - center.getZ();
                }
            }
        }

        String direction = "unknown";
        if (count > 0) {
            double avgDx = (double) dxSum / count;
            double avgDz = (double) dzSum / count;

            double angle = Math.atan2(-avgDz, avgDx); // atan2(y, x) — note Z is north/south
            angle = Math.toDegrees(angle);
            if (angle < 0) angle += 360;

            if (angle >= 337.5 || angle < 22.5) direction = "east";
            else if (angle < 67.5) direction = "northeast";
            else if (angle < 112.5) direction = "north";
            else if (angle < 157.5) direction = "northwest";
            else if (angle < 202.5) direction = "west";
            else if (angle < 247.5) direction = "southwest";
            else if (angle < 292.5) direction = "south";
            else if (angle < 337.5) direction = "southeast";
        }

        // make message final for lambda
        final String message = "Found " + count + " " + oreId + " within " + radius + " blocks"
                + (count > 0 ? " in general direction of " + direction + "." : ".");

        context.getSource().sendSuccess(() -> Component.literal(message), false);

        return count;
    }
}