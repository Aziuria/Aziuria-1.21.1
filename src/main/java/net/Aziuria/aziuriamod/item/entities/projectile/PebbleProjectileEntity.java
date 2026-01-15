package net.Aziuria.aziuriamod.item.entities.projectile;

import net.Aziuria.aziuriamod.block.ModBlocks;
import net.Aziuria.aziuriamod.item.ModItems;
import net.Aziuria.aziuriamod.item.entities.ModEntities;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class PebbleProjectileEntity extends ThrowableItemProjectile {

    public static final float DAMAGE = 2.0f; // 1 heart

    public PebbleProjectileEntity(EntityType<? extends PebbleProjectileEntity> type, Level level) {
        super(type, level);
    }

    public PebbleProjectileEntity(Level level, LivingEntity owner) {
        super(ModEntities.PEBBLE_PROJECTILE.get(), owner, level);
    }

    @Override
    protected Item getDefaultItem() {
        Item item = ModBlocks.PEBBLE_BLOCK.get().asItem();
        if (item instanceof BlockItem) {
            return item;
        }
        throw new IllegalStateException("PebbleBlock does not have a BlockItem!");
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);

        if (!level().isClientSide) {
            if (result instanceof EntityHitResult entityHit) {
                Entity target = entityHit.getEntity();
                if (target instanceof LivingEntity living) {
                    Entity owner = this.getOwner();

                    // ✅ Correct 1.21.1 way to create a thrown damage source
                    Holder<DamageType> thrownHolder = level().registryAccess()
                            .registryOrThrow(Registries.DAMAGE_TYPE)
                            .getHolderOrThrow(DamageTypes.THROWN);

                    DamageSource source = owner != null
                            ? new DamageSource(thrownHolder, this, owner)
                            : new DamageSource(thrownHolder, this, null);

                    living.hurt(source, DAMAGE);
                }
            }

            // Random drops
            float roll = level().random.nextFloat();
            if (roll < 0.15f) {
                spawnAtLocation(ModItems.STONE_SHARD.get());
            } else if (roll < 0.70f) {
                spawnAtLocation(ModBlocks.PEBBLE_BLOCK.get().asItem());
            }

            discard();
        }
    }
}