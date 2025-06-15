package net.Aziuria.aziuriamod.villager;

import net.Aziuria.aziuriamod.item.ModItems;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

public class VillagerAIHandler {

    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof Villager villager)) return;

        if (villager.getVillagerData().getProfession() == VillagerProfession.FARMER) {

            villager.goalSelector.addGoal(5, new HarvestCropsGoal(villager, 1.0D));
            villager.goalSelector.addGoal(6, new PickupCustomItemsGoal(villager));
            villager.goalSelector.addGoal(7, new StoreCropsInChestGoal(villager, 1.0D));

            addStartingSeeds(villager);
        }

        if (villager.getVillagerData().getProfession() == VillagerProfession.FISHERMAN) {

            villager.goalSelector.addGoal(5, new FishermanFishingGoal(villager, 1.0D));
            villager.goalSelector.addGoal(6, new PickupCustomItemsGoal(villager));
            villager.goalSelector.addGoal(7, new StoreFishInChestGoal(villager, 1.0D));

            addStartingFishingRod(villager);
        }
    }

    private static void addStartingSeeds(Villager villager) {
        // Add seeds to inventory
        villager.getInventory().addItem(new ItemStack(ModItems.CUCUMBER_SEEDS.get(), 16));
        villager.getInventory().addItem(new ItemStack(ModItems.RADISH_SEEDS.get(), 16));
        villager.getInventory().addItem(new ItemStack(ModItems.TOMATO_SEEDS.get(), 16));
    }

    private static void addStartingFishingRod(Villager villager) {
        // Add a fishing rod so they can start fishing immediately
        villager.getInventory().addItem(new ItemStack(Items.FISHING_ROD, 1));
    }
}