package net.Aziuria.aziuriamod.villager;

import net.Aziuria.aziuriamod.block.CucumberCropBlock;
import net.Aziuria.aziuriamod.block.RadishCropBlock;
import net.Aziuria.aziuriamod.block.TomatoCropBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BeetrootBlock;
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
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        targetCropPos = findMatureCrop();
        return targetCropPos != null;
    }

    @Override
    public boolean canContinueToUse() {
        if (targetCropPos == null) return false;
        BlockState state = villager.level().getBlockState(targetCropPos);
        return state.getBlock() instanceof CropBlock;
    }

    @Override
    public void stop() {
        targetCropPos = null;
        villager.getNavigation().stop();
    }

    @Override
    public void tick() {
        if (targetCropPos == null) return;

        if (villager.getNavigation().isDone()) {
            villager.getNavigation().moveTo(targetCropPos.getX() + 0.5, targetCropPos.getY(), targetCropPos.getZ() + 0.5, speed);
        }

        if (villager.blockPosition().closerThan(targetCropPos, 1.5)) {
            Level level = villager.level();
            BlockState cropState = level.getBlockState(targetCropPos);
            Block block = cropState.getBlock();

            if (block instanceof CropBlock) {
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
                    // Unsupported crop block — skip harvesting
                    return;
                }

                int age = cropState.getValue(ageProp);

                if (age >= MAX_AGE) {
                    // Get drops from the crop
                    var drops = Block.getDrops(cropState, (ServerLevel) level, targetCropPos, null);

                    // Add drops to villager inventory
                    for (ItemStack drop : drops) {
                        villager.getInventory().addItem(drop);
                    }

                    // Replant by resetting age to 0
                    level.setBlock(targetCropPos, cropState.setValue(ageProp, 0), 3);

                    // Reset target so next tick searches new crop
                    targetCropPos = null;
                }
            }
        }
    }

    private BlockPos findMatureCrop() {
        int searchRadius = 5;
        BlockPos villagerPos = villager.blockPosition();
        Level level = villager.level();

        for (int dx = -searchRadius; dx <= searchRadius; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -searchRadius; dz <= searchRadius; dz++) {
                    BlockPos checkPos = villagerPos.offset(dx, dy, dz);
                    BlockState state = level.getBlockState(checkPos);
                    Block block = state.getBlock();

                    if (block instanceof CropBlock) {
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
                            continue; // skip unsupported crops
                        }

                        int age = state.getValue(ageProp);

                        if (age >= MAX_AGE) {
                            return checkPos;
                        }
                    }
                }
            }
        }
        return null;
    }
}