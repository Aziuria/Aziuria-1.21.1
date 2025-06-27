package net.Aziuria.aziuriamod;

import com.mojang.logging.LogUtils;
import net.Aziuria.aziuriamod.block.ModBlocks;
import net.Aziuria.aziuriamod.block.world.BlockBatcher;
import net.Aziuria.aziuriamod.client.ModClientCommonBusEvents;
import net.Aziuria.aziuriamod.command.DecayCommand;
import net.Aziuria.aziuriamod.command.FogCommand;
import net.Aziuria.aziuriamod.fog.*;
import net.Aziuria.aziuriamod.handler.FastLeafDecayHandler;
import net.Aziuria.aziuriamod.handler.LeafParticleHandler;
import net.Aziuria.aziuriamod.block.entity.ModBlockEntities;
import net.Aziuria.aziuriamod.client.ClientModInitializer;
import net.Aziuria.aziuriamod.client.screen.ModMenus;
import net.Aziuria.aziuriamod.data.ModDataComponents;
import net.Aziuria.aziuriamod.events.ModEvents;
import net.Aziuria.aziuriamod.handler.BlockDropHandler;
import net.Aziuria.aziuriamod.item.ModCreativeModeTabs;
import net.Aziuria.aziuriamod.item.ModItems;
import net.Aziuria.aziuriamod.item.custom.entities.ModEntities;
import net.Aziuria.aziuriamod.particle.FallingLeafParticle;
import net.Aziuria.aziuriamod.particle.ModParticles;
import net.Aziuria.aziuriamod.sounds.ModSounds;
import net.Aziuria.aziuriamod.villager.ModVillagerTrades;
import net.Aziuria.aziuriamod.villager.VillagerAIHandler;
import net.Aziuria.aziuriamod.villager.VillagerProfessionTickHandler;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import org.slf4j.Logger;

@Mod(AziuriaMod.MOD_ID)
public class AziuriaMod {
    public static final String MOD_ID = "aziuriamod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public AziuriaMod(IEventBus modEventBus, ModContainer modContainer) {
        // Setup events
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::registerCapabilities); // <-- Register capabilities
        modEventBus.addListener(this::addCreative);

        if (FMLEnvironment.dist == Dist.CLIENT) {
            ClientModInitializer.register(modEventBus);
        }

        NeoForge.EVENT_BUS.register(this);
        NeoForge.EVENT_BUS.register(BlockDropHandler.class);
        NeoForge.EVENT_BUS.register(ModEvents.class);
        NeoForge.EVENT_BUS.register(LeafParticleHandler.class);



        // ** Added registrations for fog features **
        FogRegistry.init();                                           // Initialize fog system
        NeoForge.EVENT_BUS.addListener(this::onRegisterCommands);     // Command setup listener
        NeoForge.EVENT_BUS.register(new FogVillagerAI());             // AI handler
        NeoForge.EVENT_BUS.register(new FogMobSpawnModifier());
        NeoForge.EVENT_BUS.register(ModClientCommonBusEvents.class);
        NeoForge.EVENT_BUS.register(new FogZombieSpawner());
        NeoForge.EVENT_BUS.register(BlockBatcher.class);
        NeoForge.EVENT_BUS.register(VillagerAIHandler.class);
        NeoForge.EVENT_BUS.register(VillagerProfessionTickHandler.class);
        NeoForge.EVENT_BUS.addListener(ModVillagerTrades::onVillagerTrades);
        NeoForge.EVENT_BUS.addListener(FastLeafDecayHandler::onServerTick);

        // Register all mod content
        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModSounds.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenus.register(modEventBus);
        ModDataComponents.register(modEventBus);
        ModParticles.register(modEventBus);
        ModEntities.ENTITIES.register(modEventBus);



        // Config registration
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        LOGGER.info("AziuriaMod has loaded successfully.");
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Running common setup...");
    }

    private void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                Capabilities.FluidHandler.BLOCK,
                ModBlockEntities.STEEL_BARREL_BLOCK_ENTITY.get(),
                (barrelEntity, direction) -> barrelEntity.getTank()
        );
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.STEEL_INGOT);
            event.accept(ModItems.STEEL_ALLOY_MESH);
            event.accept(ModItems.SULPHUR);
            event.accept(ModItems.POTASSIUM);
        }

        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.SULPHUR_ORE);
            event.accept(ModBlocks.POTASSIUM_ORE);
            event.accept(ModBlocks.DEEPSLATE_SULPHUR_ORE);
            event.accept(ModBlocks.DEEPSLATE_POTASSIUM_ORE);
            event.accept(ModBlocks.STEEL_BLOCK);
            event.accept(ModBlocks.UNBREAKABLE_GLASS);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Server is starting...");
    }

    private void onRegisterCommands(RegisterCommandsEvent event) {
        FogCommand.register(event.getDispatcher());
        DecayCommand.register(event.getDispatcher());
    }


    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> LOGGER.info("Client setup complete."));


        }

        @SubscribeEvent
        public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
            event.registerSpriteSet(ModParticles.FALLING_LEAF_1.get(), FallingLeafParticle.Provider::new);
            event.registerSpriteSet(ModParticles.FALLING_LEAF_2.get(), FallingLeafParticle.Provider::new);
            event.registerSpriteSet(ModParticles.FALLING_LEAF_3.get(), FallingLeafParticle.Provider::new);
            event.registerSpriteSet(ModParticles.FALLING_LEAF_4.get(), FallingLeafParticle.Provider::new);
        }

    }

}