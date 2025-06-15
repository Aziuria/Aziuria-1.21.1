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

            // Custom harvesting for custom crops (balanced with vanilla)
            villager.goalSelector.addGoal(5, new HarvestCropsGoal(villager, 1.0D));

            // Pick up dropped seeds & crops (vanilla-like)
            villager.goalSelector.addGoal(6, new PickupCustomItemsGoal(villager));

            // Store excess crops in chests
            villager.goalSelector.addGoal(7, new StoreCropsInChestGoal(villager, 1.0D));

            // Give starter seeds
            addStartingSeeds(villager);
        }

        if (villager.getVillagerData().getProfession() == VillagerProfession.FISHERMAN) {

            // Custom fishing goal (near water, holds rod)
            villager.goalSelector.addGoal(5, new FishermanFishingGoal(villager, 1.0D));

            // Pick up dropped items (like fishing rod if found)
            villager.goalSelector.addGoal(6, new PickupCustomItemsGoal(villager));

            // Store caught fish in chest
            villager.goalSelector.addGoal(7, new StoreFishInChestGoal(villager, 1.0D));

            // Give starter fishing rod
            addStartingFishingRod(villager);
        }
    }

    private static void addStartingSeeds(Villager villager) {
        villager.getInventory().addItem(new ItemStack(ModItems.CUCUMBER_SEEDS.get(), 8));
        villager.getInventory().addItem(new ItemStack(ModItems.RADISH_SEEDS.get(), 8));
        villager.getInventory().addItem(new ItemStack(ModItems.TOMATO_SEEDS.get(), 8));
    }

    private static void addStartingFishingRod(Villager villager) {
        villager.getInventory().addItem(new ItemStack(Items.FISHING_ROD, 1));
    }
}