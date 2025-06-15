package net.Aziuria.aziuriamod.villager;

import net.Aziuria.aziuriamod.item.ModItems;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

public class VillagerAIHandler {

    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof Villager villager)) return;

        if (villager.getVillagerData().getProfession() == VillagerProfession.FARMER) {

            /*
             * Goal priority order:
             * Lower number = higher priority!
             *
             * Vanilla farmers:
             * - Plant & harvest have medium priority (typically 5)
             * - Pickup items: low priority (after other farming work)
             * - Storing crops: lowest priority so they do it when they have time
             */

            // Custom harvesting for custom crops (balanced with vanilla)
            villager.goalSelector.addGoal(5, new HarvestCropsGoal(villager, 1.0D));

            // Pick up dropped seeds & crops (vanilla-like)
            villager.goalSelector.addGoal(6, new PickupCustomItemsGoal(villager));

            // Store excess crops in chests (lower priority: do when free)
            villager.goalSelector.addGoal(7, new StoreCropsInChestGoal(villager, 1.0D));

            // Give starter seeds so they can plant them
            addStartingSeeds(villager);
        }
    }

    private static void addStartingSeeds(Villager villager) {
        villager.getInventory().addItem(new ItemStack(ModItems.CUCUMBER_SEEDS.get(), 8));
        villager.getInventory().addItem(new ItemStack(ModItems.RADISH_SEEDS.get(), 8));
        villager.getInventory().addItem(new ItemStack(ModItems.TOMATO_SEEDS.get(), 8));
    }
}