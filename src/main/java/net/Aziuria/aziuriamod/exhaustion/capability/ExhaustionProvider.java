package net.Aziuria.aziuriamod.exhaustion.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import org.jetbrains.annotations.Nullable;

public class ExhaustionProvider implements ICapabilityProvider<Entity, Direction, Iexhaustion> {

    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath("aziuriamod", "exhaustion");
    public static final EntityCapability<Iexhaustion, Direction> EXHAUSTION_CAP =
            EntityCapability.createSided(ID, Iexhaustion.class);

    private final Iexhaustion exhaustion = new Exhaustion();

    public ExhaustionProvider() {
        exhaustion.setExhaustion(Exhaustion.MAX_EXHAUSTION); // start full
    }

    @Override
    public @Nullable Iexhaustion getCapability(Entity entity, Direction side) {
        return exhaustion;
    }

    public CompoundTag serializeNBT() {
        return exhaustion.serializeNBT();
    }

    public void deserializeNBT(CompoundTag nbt) {
        exhaustion.deserializeNBT(nbt);
    }

    public static void register(RegisterCapabilitiesEvent event) {
        event.registerEntity(EXHAUSTION_CAP, EntityType.PLAYER, new ExhaustionProvider());
    }
}