package net.Aziuria.aziuriamod.villager;

import net.Aziuria.aziuriamod.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.Blocks;

import java.util.EnumSet;

public class HarvestCropsGoal extends Goal {

    private final Villager villager;
    private final double speed;
    private BlockPos targetCropPos = null;

    public HarvestCropsGoal(Villager villager, double speed) {
        this.villager = villager;
        this.speed = speed;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        targetCropPos = findMatureCrop();
        return targetCropPos != null;
    }

    @Override
    public boolean canContinueToUse() {
        return targetCropPos != null && villager.level().getBlockState(targetCropPos).getBlock() instanceof CropBlock;
    }

    @Override
    public void stop() {
        targetCropPos = null;
        villager.getNavigation().stop();
    }

    @Override
    public void tick() {
        if (targetCropPos == null) return;

        villager.getLookControl().setLookAt(
                targetCropPos.getX() + 0.5,
                targetCropPos.getY() + 0.5,
                targetCropPos.getZ() + 0.5
        );

        if (!villager.getNavigation().isInProgress()) {
            villager.getNavigation().moveTo(
                    targetCropPos.getX() + 0.5,
                    targetCropPos.getY(),
                    targetCropPos.getZ() + 0.5,
                    speed
            );
        }

        if (villager.blockPosition().closerThan(targetCropPos, 1.5)) {
            harvestAndReplant(targetCropPos);
            targetCropPos = null; // Reset for next cycle
        }
    }

    private void harvestAndReplant(BlockPos pos) {
        Level level = villager.level();
        BlockState state = level.getBlockState(pos);
        Block block = state.getBlock();

        IntegerProperty ageProp = null;
        int maxAge = -1;

        // ✅ Custom crops
        if (block instanceof RadishCropBlock radish) {
            ageProp = radish.getPublicAgeProperty();
            maxAge = 3;
        } else if (block instanceof CucumberCropBlock cucumber) {
            ageProp = cucumber.getPublicAgeProperty();
            maxAge = 3;
        } else if (block instanceof TomatoCropBlock tomato) {
            ageProp = tomato.getPublicAgeProperty();
            maxAge = 3;
        } else if (block instanceof LettuceCropBlock lettuce) {
            ageProp = lettuce.getPublicAgeProperty();
            maxAge = 3;
        } else if (block instanceof OnionCropBlock onion) {
            ageProp = onion.getPublicAgeProperty();
            maxAge = 3;
        } else if (block instanceof SpringOnionCropBlock springOnion) {
            ageProp = springOnion.getPublicAgeProperty();
            maxAge = 3;
        } else if (block instanceof LettuceCropBlock lettuce) {
            ageProp = lettuce.getPublicAgeProperty();
            maxAge = 3;


            // ✅ Vanilla crops
        } else if (block == Blocks.WHEAT) {
            ageProp = CropBlock.AGE;
            maxAge = 7;
        } else if (block == Blocks.CARROTS) {
            ageProp = CropBlock.AGE;
            maxAge = 7;
        } else if (block == Blocks.POTATOES) {
            ageProp = CropBlock.AGE;
            maxAge = 7;
        } else if (block instanceof BeetrootBlock) {
            ageProp = BeetrootBlock.AGE;
            maxAge = 3;
        } else {
            return; // unsupported
        }

        int age = state.getValue(ageProp);
        if (age >= maxAge) {
            // ✅ Play crop break sound
            level.playSound(
                    null,
                    pos,
                    block.getSoundType(state, level, pos, villager).getBreakSound(),
                    net.minecraft.sounds.SoundSource.BLOCKS,
                    1.0F,
                    1.0F
            );

            Block.getDrops(state, (ServerLevel) level, pos, null).forEach(stack ->
                    villager.getInventory().addItem(stack)
            );
            level.setBlock(pos, state.setValue(ageProp, 0), 3);
        }
    }

    private BlockPos findMatureCrop() {
        BlockPos origin = villager.blockPosition();
        Level level = villager.level();
        int radius = 6;

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    BlockPos pos = origin.offset(dx, dy, dz);
                    BlockState state = level.getBlockState(pos);
                    Block block = state.getBlock();

                    IntegerProperty ageProp = null;
                    int maxAge = -1;

                    // ✅ Custom crops
                    if (block instanceof RadishCropBlock radish) {
                        ageProp = radish.getPublicAgeProperty();
                        maxAge = 3;
                    } else if (block instanceof CucumberCropBlock cucumber) {
                        ageProp = cucumber.getPublicAgeProperty();
                        maxAge = 3;
                    } else if (block instanceof TomatoCropBlock tomato) {
                        ageProp = tomato.getPublicAgeProperty();
                        maxAge = 3;

                        // ✅ Vanilla crops: check by block instance
                    } else if (block == Blocks.WHEAT) {
                        ageProp = CropBlock.AGE;
                        maxAge = 7;
                    } else if (block == Blocks.CARROTS) {
                        ageProp = CropBlock.AGE;
                        maxAge = 7;
                    } else if (block == Blocks.POTATOES) {
                        ageProp = CropBlock.AGE;
                        maxAge = 7;
                    } else if (block instanceof BeetrootBlock) {
                        ageProp = BeetrootBlock.AGE;
                        maxAge = 3;
                    } else {
                        continue; // unsupported
                    }

                    if (state.getValue(ageProp) >= maxAge) {
                        return pos;
                    }
                }
            }
        }

        return null; // none found
    }
}