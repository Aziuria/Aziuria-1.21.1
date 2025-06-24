package net.Aziuria.aziuriamod.villager;

import net.Aziuria.aziuriamod.item.ModItems;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.EnumSet;
import java.util.List;

public class PickupCustomItemsGoal extends Goal {

    private final Villager villager;
    private static final double PICKUP_RADIUS = 5.0D;

    public PickupCustomItemsGoal(Villager villager) {
        this.villager = villager;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        // Don’t run for babies and only run if at least one target item is nearby
        return !villager.isBaby() && !getNearbyItems().isEmpty();
    }

    @Override
    public void tick() {
        List<ItemEntity> items = getNearbyItems();

        if (items.isEmpty()) return;


        ItemEntity nearest = null;
        double closestDistance = Double.MAX_VALUE;

        for (ItemEntity entity : items) {
            double dist = villager.distanceToSqr(entity);
            if (dist < closestDistance) {
                closestDistance = dist;
                nearest = entity;
            }
        }

        if (nearest != null) {
            // Move towards it
            villager.getNavigation().moveTo(nearest, 1.0D);

            // Pickup if close enough
            if (closestDistance < 2.0D) {
                ItemStack stack = nearest.getItem();
                villager.getInventory().addItem(stack.copy());
                nearest.discard();
            }
        }
    }

    private List<ItemEntity> getNearbyItems() {
        return villager.level().getEntitiesOfClass(
                ItemEntity.class,
                villager.getBoundingBox().inflate(PICKUP_RADIUS),
                entity -> {
                    Item item = entity.getItem().getItem();
                    VillagerProfession profession = villager.getVillagerData().getProfession();
                    if (profession == VillagerProfession.FARMER) {
                        return isFarmerItem(item);
                    } else if (profession == VillagerProfession.FISHERMAN) {
                        return isFishermanItem(item);
                    }
                    return false;
                }
        );
    }

    private boolean isFarmerItem(Item item) {
        return item == ModItems.CUCUMBER_SEEDS.get()
                || item == ModItems.RADISH_SEEDS.get()
                || item == ModItems.LETTUCE_SEEDS.get()
                || item == ModItems.ONION_SEEDS.get()
                || item == ModItems.SPRING_ONION_SEEDS.get()
                || item == ModItems.TOMATO_SEEDS.get()
                || item == ModItems.CUCUMBER.get()
                || item == ModItems.LETTUCE.get()
                || item == ModItems.ONION.get()
                || item == ModItems.SPRING_ONION.get()
                || item == ModItems.RADISH.get()
                || item == ModItems.TOMATO.get();
    }

    private boolean isFishermanItem(Item item) {
        return item == Items.FISHING_ROD
                || item == Items.COD
                || item == Items.SALMON
                || item == Items.PUFFERFISH
                || item == Items.TROPICAL_FISH;
    }
}