package net.Aziuria.aziuriamod.villager;

import com.google.common.collect.ImmutableSet;
import net.Aziuria.aziuriamod.AziuriaMod;
import net.Aziuria.aziuriamod.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(BuiltInRegistries.POINT_OF_INTEREST_TYPE, AziuriaMod.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(BuiltInRegistries.VILLAGER_PROFESSION, AziuriaMod.MOD_ID);

    public static final Holder<PoiType> WOODCUTTER_POI = POI_TYPES.register("woodcutter_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.WOODCUTTER_BENCH.get().getStateDefinition().getPossibleStates()), 1, 1));

    public static final Holder<PoiType> MINER_POI = POI_TYPES.register("miner_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.MINER_BENCH.get().getStateDefinition().getPossibleStates()), 1, 1));

    public static final Holder<VillagerProfession> WOODCUTTER = VILLAGER_PROFESSIONS.register("woodcutter",
            () -> new VillagerProfession("woodcutter", holder -> holder.value() == WOODCUTTER_POI.value(),
                    poiTypeHolder -> poiTypeHolder.value() == WOODCUTTER_POI.value(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_TOOLSMITH));

    public static final Holder<VillagerProfession> MINER = VILLAGER_PROFESSIONS.register("miner",
            () -> new VillagerProfession("miner", holder -> holder.value() == MINER_POI.value(),
                    poiTypeHolder -> poiTypeHolder.value() == MINER_POI.value(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_TOOLSMITH));


    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}