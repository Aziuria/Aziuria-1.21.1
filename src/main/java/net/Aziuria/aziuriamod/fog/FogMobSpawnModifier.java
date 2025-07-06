package net.Aziuria.aziuriamod.fog;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.npc.Villager;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;

public class FogMobSpawnModifier {

    @SubscribeEvent
    public void onMobSpawnCheck(MobSpawnEvent.SpawnPlacementCheck event) {
        // Check if the level is an instance of Level
        if (!(event.getLevel() instanceof Level level)) {
            return; // Exit if not a Level instance
        }

        if (!level.isClientSide() && FogEventManager.isEvilFogActive()) {
            if (event.getEntityType() == EntityType.ZOMBIE) {
                BlockPos pos = event.getPos();

                // Spawn point validity check: must have two empty blocks (pos and pos.above())
                if (!level.isEmptyBlock(pos) || !level.isEmptyBlock(pos.above())) {
                    return; // Unsafe spawn position, cancel spawning
                }

                event.setResult(MobSpawnEvent.SpawnPlacementCheck.Result.FAIL);

                // Spawn and buff the zombie manually
                Zombie zombie = new Zombie(level);

                // Mark zombie as fog-spawned for later replacement
                zombie.getPersistentData().putBoolean("SpawnedByFog", true);
                zombie.getPersistentData().putInt("FogFadeTimer", 200); // 10 seconds countdown (20 ticks per sec)

                zombie.moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0.0F, 0.0F);
                zombie.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20 * 60, 1));  // Speed II
                zombie.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 20 * 60, 0));   // Strength I

                // Add additional fog zombie targeting, priority order:
                // Player (1), IronGolem (2), Villager (3), then animals (4+)
                zombie.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(zombie, Player.class, true));
                zombie.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(zombie, IronGolem.class, true));
                zombie.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(zombie, Villager.class, true));
                zombie.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(zombie, Animal.class, 10, true, false,
                        animal -> animal instanceof Sheep || animal instanceof Cow || animal instanceof Pig || animal instanceof Chicken));
                zombie.goalSelector.addGoal(0, new TargetRottenFleshGoal(zombie, 1.0D));

                level.addFreshEntity(zombie);
            }
        }
    }

}