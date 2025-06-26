package net.Aziuria.aziuriamod.fog;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.GlobalPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

import java.util.List;

public class FogVillagerAI {

    @SubscribeEvent
    public void onEntityTick(EntityTickEvent.Post event) {
        if (!(event.getEntity() instanceof Villager villager)) return;
        if (villager.level().isClientSide()) return;

        if (FogEventManager.getActiveFog() != null) {
            BlockPos home = villager.getBrain().getMemory(MemoryModuleType.HOME)
                    .map(globalPos -> globalPos.pos())
                    .orElse(null);

            if (home == null) return;

            ServerLevel level = (ServerLevel) villager.level();
            double distanceSq = villager.distanceToSqr(Vec3.atCenterOf(home));

            // Find nearby zombies within 12 blocks
            List<?> nearbyZombies = level.getEntitiesOfClass(net.minecraft.world.entity.monster.Zombie.class, villager.getBoundingBox().inflate(12));

            if (!nearbyZombies.isEmpty()) {
                // Calculate average flee direction away from all nearby zombies
                Vec3 fleeDir = Vec3.ZERO;
                for (Object entity : nearbyZombies) {
                    Vec3 zombiePos = ((net.minecraft.world.entity.Entity) entity).position();
                    Vec3 away = villager.position().subtract(zombiePos).normalize();
                    fleeDir = fleeDir.add(away);
                }
                fleeDir = fleeDir.normalize();

                if (!fleeDir.equals(Vec3.ZERO)) {
                    Vec3 fleeTarget = villager.position().add(fleeDir.scale(8));

                    // Set fleeing villager's target and speed boost
                    BehaviorUtils.setWalkAndLookTargetMemories(villager, new BlockPos((int) fleeTarget.x, (int) fleeTarget.y, (int) fleeTarget.z), 1.5F, 2);
                    villager.getBrain().setMemory(MemoryModuleType.HIDING_PLACE, GlobalPos.of(villager.level().dimension(), home));
                    if (!villager.hasEffect(MobEffects.MOVEMENT_SPEED)) {
                        villager.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 1));
                    }

                    // ALERT OTHER VILLAGERS TO FLEE
                    List<Villager> nearbyVillagers = level.getEntitiesOfClass(Villager.class, villager.getBoundingBox().inflate(15));
                    for (Villager otherVillager : nearbyVillagers) {
                        if (otherVillager != villager) {
                            BehaviorUtils.setWalkAndLookTargetMemories(otherVillager, new BlockPos((int) fleeTarget.x, (int) fleeTarget.y, (int) fleeTarget.z), 1.5F, 2);
                            otherVillager.getBrain().setMemory(MemoryModuleType.HIDING_PLACE, GlobalPos.of(otherVillager.level().dimension(), home));
                            if (!otherVillager.hasEffect(MobEffects.MOVEMENT_SPEED)) {
                                otherVillager.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 1));
                            }
                        }
                    }
                    return; // flee takes priority, skip rest
                }
            }

            // No zombies nearby, proceed with normal home logic
            if (distanceSq > 4.0D) {
                if (!villager.getBrain().hasMemoryValue(MemoryModuleType.WALK_TARGET)) {
                    BehaviorUtils.setWalkAndLookTargetMemories(villager, home, 1.0F, 2);
                }
                villager.getBrain().setMemory(MemoryModuleType.HIDING_PLACE, GlobalPos.of(villager.level().dimension(), home));
                if (!villager.hasEffect(MobEffects.MOVEMENT_SPEED)) {
                    villager.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 1));
                }
            } else if (distanceSq <= 2.25D) { // At or near home
                // Close nearby doors to keep zombies out
                for (Direction dir : Direction.Plane.HORIZONTAL) {
                    BlockPos doorPos = home.relative(dir);
                    BlockState state = level.getBlockState(doorPos);

                    if (state.getBlock() instanceof DoorBlock) {
                        boolean isOpen = state.getValue(BlockStateProperties.OPEN);
                        if (isOpen) {
                            level.setBlock(doorPos, state.setValue(BlockStateProperties.OPEN, false), 3);
                            level.playSound(null, doorPos, SoundEvents.WOODEN_DOOR_CLOSE, SoundSource.BLOCKS, 1.0F, 1.0F);
                        }
                    }
                }
            }
        }
    }
}