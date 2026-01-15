package net.Aziuria.aziuriamod.hud.thirst.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

import org.jetbrains.annotations.Nullable;

public class ThirstProvider implements ICapabilityProvider<Entity, Direction, IThirst> {

    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath("aziuriamod", "thirst");
    public static final EntityCapability<IThirst, Direction> THIRST_CAP = EntityCapability.createSided(ID, IThirst.class);

    private final IThirst thirst = new Thirst();


    @Override
    public @Nullable IThirst getCapability(Entity entity, Direction side) {
        return thirst;
    }

    public CompoundTag serializeNBT() {
        return thirst.serializeNBT();
    }

    public void deserializeNBT(CompoundTag nbt) {
        thirst.deserializeNBT(nbt);
    }


    public static void register(RegisterCapabilitiesEvent event) {
        event.registerEntity(THIRST_CAP, EntityType.PLAYER, new ThirstProvider());
    }
}