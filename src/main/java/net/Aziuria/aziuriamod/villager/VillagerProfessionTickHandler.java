package net.Aziuria.aziuriamod.villager;

import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.util.Iterator;

public class VillagerProfessionTickHandler {

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Pre event) {
        Iterator<Villager> iterator = VillagerAIHandler.pendingVillagers.iterator();
        while (iterator.hasNext()) {
            Villager villager = iterator.next();

            if (villager.isRemoved()) {
                iterator.remove();
                continue;
            }

            VillagerProfession prof = villager.getVillagerData().getProfession();
            if (prof != VillagerProfession.NONE) {
                VillagerAIHandler.setupVillagerGoalsAndItems(villager, prof);
                iterator.remove();
                System.out.println("[DEBUG] Villager profession fixed in tick handler: " + prof);
            }
        }
    }
}