package net.Aziuria.aziuriamod.island.entity;

import net.Aziuria.aziuriamod.island.type.IslandBiomeType;
import net.Aziuria.aziuriamod.island.generation.IslandGenerator;
import net.Aziuria.aziuriamod.island.type.IslandType;
import net.Aziuria.aziuriamod.item.entities.ModEntities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.HitResult;

public class IslandThrowableEntity extends ThrowableItemProjectile {

    private IslandType islandType = IslandType.SMALL; // Default
    private IslandBiomeType biomeType = IslandBiomeType.PLAINS;

    public IslandThrowableEntity(EntityType<? extends IslandThrowableEntity> type, Level level) {
        super(type, level);
    }

    public IslandThrowableEntity(Level level, IslandType type, IslandBiomeType biomeType) {
        super(ModEntities.ISLAND_THROWABLE.get(), level);
        this.islandType = type;
        this.biomeType = biomeType; // ✅ Correct assignment
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);                                          // ← original
        if (!level().isClientSide()) {
            IslandGenerator.generateIsland(                           // ← changed
                    (ServerLevel) level(),                                // ← original
                    blockPosition(),                                      // ← original
                    islandType,                                           // ← original
                    biomeType,                                            // ← added
                    ((ServerLevel) level()).getSeed()                     // ← original
            );
            discard();                                                // ← original
        }
    }

    @Override
    protected Item getDefaultItem() {
        return Items.AIR; // Visual only, no pickup item
    }

    @Override
    public void tick() {
        super.tick();
        if (level().isClientSide()) {
            for (int i = 0; i < 2; ++i) {
                level().addParticle(ParticleTypes.CLOUD, getX(), getY(), getZ(),
                        (random.nextDouble() - 0.5) * 0.2,
                        (random.nextDouble() - 0.5) * 0.2,
                        (random.nextDouble() - 0.5) * 0.2);
            }
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putString("IslandType", islandType.name());
        tag.putString("IslandBiomeType", biomeType.name());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("IslandType")) {
            islandType = IslandType.valueOf(tag.getString("IslandType"));
        }
        if (tag.contains("IslandBiomeType")) {                        // ← added
            biomeType = IslandBiomeType.valueOf(tag.getString("IslandBiomeType"));
        }
    }

    public void setIslandType(IslandType type) {
        this.islandType = type;
    }

    public IslandType getIslandType() {
        return this.islandType;
    }

    public void setBiomeType(IslandBiomeType biomeType) {            // ← added
        this.biomeType = biomeType;                                   // ← added
    }

    public IslandBiomeType getBiomeType() {                          // ← added
        return biomeType;
    }
}