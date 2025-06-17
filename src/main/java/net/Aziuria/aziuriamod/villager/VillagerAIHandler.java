package net.Aziuria.aziuriamod.villager;

import net.Aziuria.aziuriamod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class VillagerAIHandler {

    public static final Set<Villager> pendingVillagers = ConcurrentHashMap.newKeySet();

    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof Villager villager)) return;

        VillagerProfession prof = villager.getVillagerData().getProfession();
        System.out.println("[DEBUG] Villager spawned with profession: " + prof);

        if (prof == VillagerProfession.NONE) {
            pendingVillagers.add(villager);
            return;
        }

        setupVillagerGoalsAndItems(villager, prof);
    }

    public static void setupVillagerGoalsAndItems(Villager villager, VillagerProfession prof) {
        // ✅ Remove unwanted defaults
        villager.goalSelector.removeAllGoals(goal ->
                goal instanceof PanicGoal || goal instanceof AvoidEntityGoal<?>);

        if (prof == VillagerProfession.FARMER) {
            villager.goalSelector.addGoal(1, new HarvestCropsGoal(villager, 1.2D));
            villager.goalSelector.addGoal(2, new StoreCropsInChestGoal(villager, 1.2D));
            villager.goalSelector.addGoal(3, new PickupCustomItemsGoal(villager));
            addStartingSeeds(villager);
        }

        if (prof == VillagerProfession.FISHERMAN) {
            // ✅ USE THE FIXED SINGLE GOAL ONLY
            villager.goalSelector.addGoal(1, new FishermanFishingGoal(villager));
            villager.goalSelector.addGoal(2, new PickupCustomItemsGoal(villager));
            addStartingFishingRod(villager);

            System.out.println("[DEBUG] Fisherman AI setup complete. Inventory: " + villager.getInventory().getItems());
        }
    }

    private static void addStartingSeeds(Villager villager) {
        villager.getInventory().addItem(new ItemStack(ModItems.CUCUMBER_SEEDS.get(), 1));
        villager.getInventory().addItem(new ItemStack(ModItems.RADISH_SEEDS.get(), 1));
        villager.getInventory().addItem(new ItemStack(ModItems.TOMATO_SEEDS.get(), 1));
    }

    private static void addStartingFishingRod(Villager villager) {
        villager.getInventory().addItem(new ItemStack(Items.FISHING_ROD, 1));
        System.out.println("[DEBUG] Added fishing rod to villager inventory on spawn.");
    }
}