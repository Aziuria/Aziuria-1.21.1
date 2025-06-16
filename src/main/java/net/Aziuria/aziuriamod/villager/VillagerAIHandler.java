package net.Aziuria.aziuriamod.villager;

import net.Aziuria.aziuriamod.item.ModItems;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

public class VillagerAIHandler {

    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof Villager villager)) return;

        System.out.println("[DEBUG] Villager spawned with profession: " + villager.getVillagerData().getProfession());

        if (villager.getVillagerData().getProfession() == VillagerProfession.FARMER) {

            villager.goalSelector.addGoal(2, new PanicGoal(villager, 1.5D));
            villager.goalSelector.addGoal(3, new AvoidEntityGoal<>(villager, Monster.class, 8.0F, 1.5D, 1.2D));
            villager.goalSelector.addGoal(5, new HarvestCropsGoal(villager, 1.2D));
            villager.goalSelector.addGoal(6, new LookAtPlayerGoal(villager, Player.class, 6.0F));
            villager.goalSelector.addGoal(7, new PickupCustomItemsGoal(villager));
            villager.goalSelector.addGoal(8, new StoreCropsInChestGoal(villager, 1.2D));
            addStartingSeeds(villager);
        }

        if (villager.getVillagerData().getProfession() == VillagerProfession.FISHERMAN) {

            villager.goalSelector.addGoal(2, new PanicGoal(villager, 1.5D));
            villager.goalSelector.addGoal(3, new AvoidEntityGoal<>(villager, Monster.class, 8.0F, 1.5D, 1.2D));
            villager.goalSelector.addGoal(5, new FishermanFishingGoal(villager, 1.0D));
            villager.goalSelector.addGoal(6, new LookAtPlayerGoal(villager, Player.class, 6.0F));
            villager.goalSelector.addGoal(7, new PickupCustomItemsGoal(villager));
            villager.goalSelector.addGoal(8, new StoreFishInChestGoal(villager, 1.0D));
            addStartingFishingRod(villager);

            System.out.println("[DEBUG] Fisherman fishing rod added. Inventory: " + villager.getInventory().getItems());


        }
    }

    private static void addStartingSeeds(Villager villager) {
        villager.getInventory().addItem(new ItemStack(ModItems.CUCUMBER_SEEDS.get(), 1));
        villager.getInventory().addItem(new ItemStack(ModItems.RADISH_SEEDS.get(), 1));
        villager.getInventory().addItem(new ItemStack(ModItems.TOMATO_SEEDS.get(), 1));
    }

    private static void addStartingFishingRod(Villager villager) {
        villager.getInventory().addItem(new ItemStack(Items.FISHING_ROD, 1));
    }
}