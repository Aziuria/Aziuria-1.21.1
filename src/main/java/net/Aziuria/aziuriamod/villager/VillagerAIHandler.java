package net.Aziuria.aziuriamod.villager;

import net.Aziuria.aziuriamod.villager.goals.*;
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

        if (event.getLevel().isClientSide()) return;
        if (!(event.getEntity() instanceof Villager villager)) return;

        VillagerProfession prof = villager.getVillagerData().getProfession();

        if (prof == VillagerProfession.NONE) {
            pendingVillagers.add(villager);
            return;
        }

        setupVillagerGoalsAndItems(villager, prof);
    }

    public static void setupVillagerGoalsAndItems(Villager villager, VillagerProfession prof) {
        // Remove unwanted defaults
        villager.goalSelector.removeAllGoals(goal ->
                goal instanceof PanicGoal || goal instanceof AvoidEntityGoal<?>);

        if (prof == VillagerProfession.FARMER) {
            villager.goalSelector.addGoal(1, new HarvestCropsGoal(villager, 0.6D));
            villager.goalSelector.addGoal(2, new StoreCropsInChestGoal(villager, 0.6D));
            villager.goalSelector.addGoal(3, new PickupCustomItemsGoal(villager));
            addStartingSeeds(villager);
        }

        if (prof == VillagerProfession.FISHERMAN) {
            // Use the fixed single goal only
            villager.goalSelector.addGoal(1, new FishermanFishingGoal(villager));
            villager.goalSelector.addGoal(2, new PickupCustomItemsGoal(villager));
            addStartingFishingRod(villager);
        }

        // Add Woodcutter profession and goals here
        if (prof == ModVillagers.WOODCUTTER.value()) {
            villager.goalSelector.addGoal(1, new CutTreeGoal(villager, 0.6D));  // Your tree cutting goal (you'll need to implement this)
            villager.goalSelector.addGoal(2, new StoreWoodInChestGoal(villager, 0.6D));  // The storing goal you provided
            villager.goalSelector.addGoal(3, new PickupCustomItemsGoal(villager));  // Assuming you want this too
            addStartingAxe(villager);
        }

        if (prof == ModVillagers.MINER.value()) {
            villager.goalSelector.addGoal(1, new MineBlockGoal(villager, 0.6D)); // you'll create this goal
            villager.goalSelector.addGoal(2, new StoreOreInChestGoal(villager, 0.6D)); // you can create this storing goal too
            villager.goalSelector.addGoal(3, new PickupCustomItemsGoal(villager));
            addStartingPickaxe(villager);
        }
    }

    private static void addStartingSeeds(Villager villager) {
      //  villager.getInventory().addItem(new ItemStack(ModItems.CUCUMBER_SEEDS.get(), 1));
      //  villager.getInventory().addItem(new ItemStack(ModItems.RADISH_SEEDS.get(), 1));
      //  villager.getInventory().addItem(new ItemStack(ModItems.TOMATO_SEEDS.get(), 1));
    }

    private static void addStartingFishingRod(Villager villager) {
        villager.getInventory().addItem(new ItemStack(Items.FISHING_ROD, 1));
    }

    private static void addStartingAxe(Villager villager) {
        villager.getInventory().addItem(new ItemStack(Items.IRON_AXE, 1));
    }

    private static void addStartingPickaxe(Villager villager) {
        villager.getInventory().addItem(new ItemStack(Items.IRON_PICKAXE, 1));
    }

}