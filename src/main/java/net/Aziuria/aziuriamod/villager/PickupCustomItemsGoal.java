package net.Aziuria.aziuriamod.villager;

import net.Aziuria.aziuriamod.item.ModItems;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.EnumSet;
import java.util.List;

public class PickupCustomItemsGoal extends Goal {

    private final Villager villager;
    private static final double PICKUP_RADIUS = 5.0D;

    public PickupCustomItemsGoal(Villager villager) {
        this.villager = villager;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        return !villager.isBaby() && !getNearbyItems().isEmpty();
    }

    @Override
    public void tick() {
        List<ItemEntity> items = getNearbyItems();

        for (ItemEntity itemEntity : items) {
            if (!itemEntity.isAlive() || itemEntity.hasPickUpDelay()) continue;

            villager.getNavigation().moveTo(itemEntity, 1.0D);

            if (villager.distanceToSqr(itemEntity) < 2.0D) {
                ItemStack stack = itemEntity.getItem();
                villager.getInventory().addItem(stack.copy());
                itemEntity.discard();
            }
        }
    }

    private List<ItemEntity> getNearbyItems() {
        return villager.level().getEntitiesOfClass(ItemEntity.class, villager.getBoundingBox().inflate(PICKUP_RADIUS),
                entity -> isCustomItem(entity.getItem().getItem()));
    }

    private boolean isCustomItem(Item item) {
        return item == ModItems.CUCUMBER_SEEDS.get()
                || item == ModItems.RADISH_SEEDS.get()
                || item == ModItems.TOMATO_SEEDS.get()
                || item == ModItems.CUCUMBER.get()
                || item == ModItems.RADISH.get()
                || item == ModItems.TOMATO.get();
    }
}