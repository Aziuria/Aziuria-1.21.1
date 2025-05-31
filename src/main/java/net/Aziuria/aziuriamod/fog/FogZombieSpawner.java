package net.Aziuria.aziuriamod.fog;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

public class FogZombieSpawner {

    private static final RandomSource random = RandomSource.create();
    private static int tickCooldown = 0;

    @SubscribeEvent
    public void onWorldTick(LevelTickEvent.Post event) {
        if (!(event.getLevel() instanceof ServerLevel level)) return;
        if (!FogEventManager.isEvilFogActive()) return;
        if (level.players().isEmpty()) return;

        // Countdown cooldown timer
        if (tickCooldown > 0) {
            tickCooldown--;
            return;
        }

        // Reset cooldown to 200 ticks (10 seconds)
        tickCooldown = 200;

        // 15% chance every 10 seconds to spawn zombies
        if (random.nextInt(100) < 15) {
            int zombiesToSpawn = 3 + random.nextInt(6); // 3 to 8 zombies

            for (int i = 0; i < zombiesToSpawn; i++) {
                var player = level.players().get(random.nextInt(level.players().size()));

                BlockPos pos = player.blockPosition().offset(
                        random.nextInt(101) - 50,    // -50 to +50
                        random.nextInt(13) - 6,      // -6 to +6
                        random.nextInt(101) - 50     // -50 to +50
                );

                if (level.getWorldBorder().isWithinBounds(pos) && level.isEmptyBlock(pos)) {
                    Zombie zombie = EntityType.ZOMBIE.create(level);
                    if (zombie != null) {
                        zombie.moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0, 0);
                        zombie.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(40.0D);
                        zombie.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20 * 60, 1));
                        zombie.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 20 * 60, 0));
                        level.addFreshEntity(zombie);
                    }
                }
            }
        }
    }
}