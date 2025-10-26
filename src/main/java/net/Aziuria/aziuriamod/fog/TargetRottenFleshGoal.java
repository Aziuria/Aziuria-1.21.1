package net.Aziuria.aziuriamod.fog;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.Items;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

public class TargetRottenFleshGoal extends Goal {
    private final Zombie zombie;
    private ItemEntity targetItem;
    private final double speedModifier;
    private final double searchRange;
    private final Predicate<ItemEntity> filter = item -> item.getItem().getItem() == Items.ROTTEN_FLESH;

    private int cooldownTicks = 0;  // cooldown timer in ticks

    public TargetRottenFleshGoal(Zombie zombie, double speed) {
        this.zombie = zombie;
        this.speedModifier = speed;
        this.searchRange = 16.0D; // How far to look for rotten flesh
        setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (cooldownTicks > 0) {
            cooldownTicks--;
            return false;
        }

        List<ItemEntity> items = zombie.level().getEntitiesOfClass(ItemEntity.class,
                zombie.getBoundingBox().inflate(searchRange), filter);

        if (items.isEmpty()) {
            return false;
        }

        this.targetItem = items.stream()
                .min(Comparator.comparingDouble(zombie::distanceToSqr))
                .orElse(null);

        return this.targetItem != null;
    }

    @Override
    public boolean canContinueToUse() {
        return this.targetItem != null && this.targetItem.isAlive() &&
                zombie.distanceToSqr(targetItem) <= searchRange * searchRange;
    }

    @Override
    public void stop() {
        this.targetItem = null;
        zombie.getNavigation().stop();
    }

    @Override
    public void tick() {
        if (targetItem == null) return;

        double distance = zombie.distanceTo(targetItem);

        if (distance < 1.5D) {
            targetItem.remove(Entity.RemovalReason.KILLED);

            zombie.heal(4.0F);
            zombie.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 200, 0));

            cooldownTicks = 600;

            this.stop();
        } else {
            var path = zombie.getNavigation().createPath(targetItem, 0);
            if (path != null) {
                zombie.getNavigation().moveTo(targetItem, speedModifier);
            } else {
                zombie.getNavigation().stop();
            }
        }
    }
}