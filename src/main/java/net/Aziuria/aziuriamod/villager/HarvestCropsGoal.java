package net.Aziuria.aziuriamod.villager;

import net.Aziuria.aziuriamod.block.CucumberCropBlock;
import net.Aziuria.aziuriamod.block.RadishCropBlock;
import net.Aziuria.aziuriamod.block.TomatoCropBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

import java.util.EnumSet;

public class HarvestCropsGoal extends Goal {

    private final Villager villager;
    private final double speed;
    private BlockPos targetCropPos = null;
    private static final int MAX_AGE = 3;

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

        IntegerProperty ageProp;
        if (block instanceof RadishCropBlock radish) {
            ageProp = radish.getPublicAgeProperty();
        } else if (block instanceof CucumberCropBlock cucumber) {
            ageProp = cucumber.getPublicAgeProperty();
        } else if (block instanceof TomatoCropBlock tomato) {
            ageProp = tomato.getPublicAgeProperty();
        } else if (block instanceof BeetrootBlock) {
            ageProp = BeetrootBlock.AGE;
        } else {
            return; // skip unsupported
        }

        int age = state.getValue(ageProp);
        if (age >= MAX_AGE) {
            // Drop + inventory like vanilla
            Block.getDrops(state, (ServerLevel) level, pos, null).forEach(stack ->
                    villager.getInventory().addItem(stack)
            );
            // Replant: set age to zero
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
                    if (block instanceof RadishCropBlock radish) {
                        ageProp = radish.getPublicAgeProperty();
                    } else if (block instanceof CucumberCropBlock cucumber) {
                        ageProp = cucumber.getPublicAgeProperty();
                    } else if (block instanceof TomatoCropBlock tomato) {
                        ageProp = tomato.getPublicAgeProperty();
                    } else if (block instanceof BeetrootBlock) {
                        ageProp = BeetrootBlock.AGE;
                    } else {
                        continue;
                    }

                    if (state.getValue(ageProp) >= MAX_AGE) {
                        return pos;
                    }
                }
            }
        }

        return null; // nothing found
    }
}