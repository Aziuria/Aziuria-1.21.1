package net.Aziuria.aziuriamod.fog;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.EntityType;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;

public class FogMobSpawnModifier {

    @SubscribeEvent
    public void onMobSpawnCheck(MobSpawnEvent.SpawnPlacementCheck event) {
        Level level = (Level) event.getLevel();

        if (!level.isClientSide() && FogEventManager.isEvilFogActive()) {
            if (event.getEntityType() == EntityType.ZOMBIE) {
                event.setResult(MobSpawnEvent.SpawnPlacementCheck.Result.SUCCEED);

                // Spawn and buff the zombie manually
                BlockPos pos = event.getPos();
                Zombie zombie = new Zombie(level);
                zombie.moveTo(pos, 0.0F, 0.0F);
                zombie.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20 * 60, 1));  // Speed II
                zombie.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 20 * 60, 0));   // Strength I

                level.addFreshEntity(zombie);
            }
        }
    }
}