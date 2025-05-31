package net.Aziuria.aziuriamod.fog;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

public class FogVillagerAI {

    @SubscribeEvent
    public void onEntityTick(EntityTickEvent.Post event) {
        if (!(event.getEntity() instanceof Villager villager)) return;

        if (villager.level().isClientSide()) return;

        if (FogEventManager.getActiveFog() != null) {
            BlockPos home = villager.getBrain().getMemory(MemoryModuleType.HOME)
                    .map(globalPos -> globalPos.pos())
                    .orElse(null);

            if (home != null && villager.distanceToSqr(Vec3.atCenterOf(home)) > 4.0D) {
                BehaviorUtils.setWalkAndLookTargetMemories(villager, home, 1.0F, 2);
            }
        }
    }
}