package net.Aziuria.aziuriamod.events;

import net.Aziuria.aziuriamod.island.util.DelayedExecutor;
import net.Aziuria.aziuriamod.handler.blocks.FastLeafDecayHandler;
import net.Aziuria.aziuriamod.handler.blocks.LeafLitterHandler;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.Aziuria.aziuriamod.handler.blocks.VegetationGrowthHandler;

public class ModEvents {

    private static int tickCounter = 0;

    @SubscribeEvent
    public static void onWorldTick(LevelTickEvent.Post event) {
        if (!(event.getLevel() instanceof ServerLevel level)) return;

        tickCounter++;

        DelayedExecutor.tick(level);

        if (tickCounter % 500 == 0) {
            VegetationGrowthHandler.spreadPlants(level);
            VegetationGrowthHandler.spreadUnderwaterPlants(level);
            VegetationGrowthHandler.spreadSugarCane(level);
        }

        if (tickCounter % 400 == 0) {
            LeafLitterHandler.spreadLeafLitter(level);
        }


    }


    @SubscribeEvent
    public static void onNeighborNotify(BlockEvent.NeighborNotifyEvent event) {
        if (!(event.getLevel() instanceof ServerLevel level)) return;

        BlockState state = level.getBlockState(event.getPos());
        if (state.getBlock() instanceof LeavesBlock) {
            if (!state.getValue(BlockStateProperties.PERSISTENT)) {
                FastLeafDecayHandler.queueLeafForDecay(level, event.getPos());
            }
        }
    }
}
