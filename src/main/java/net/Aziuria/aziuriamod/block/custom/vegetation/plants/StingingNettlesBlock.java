package net.Aziuria.aziuriamod.block.custom.vegetation.plants;

import net.Aziuria.aziuriamod.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity; // ✅ FIXED
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class StingingNettlesBlock extends FlowerBlock {
    protected static final VoxelShape SHAPE = Block.box(2, 0, 2, 14, 13, 14);

    public StingingNettlesBlock(Properties properties) {
        super(MobEffects.LUCK, 5, properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity livingEntity) {

            // slow movement (important)
            entity.makeStuckInBlock(state, new Vec3(0.65D, 0.75D, 0.65D));

            double dx = Math.abs(entity.getX() - entity.xOld);
            double dz = Math.abs(entity.getZ() - entity.zOld);

            if (!level.isClientSide && (dx > 0.002 || dz > 0.002)) {

                // 50/50 chance to apply sting effects + damage
                if (level.random.nextBoolean()) {

                    // slow periodic damage
                    if (livingEntity.tickCount % 20 == 0) {
                        livingEntity.hurt(level.damageSources().cactus(), 1.0F);
                    }

                    // apply sting effect (refresh duration instead of stacking immunity)
                    livingEntity.addEffect(new MobEffectInstance(
                            ModEffects.STING_EFFECT,
                            100, // short refresh so it stays active while inside
                            0
                    ));
                }
            }
        }

        super.entityInside(state, level, pos, entity);
    }
}