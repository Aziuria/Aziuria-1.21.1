package net.Aziuria.aziuriamod;

import com.mojang.logging.LogUtils;
import net.Aziuria.aziuriamod.block.ModBlocks;
import net.Aziuria.aziuriamod.entity.ModEntity;
import net.Aziuria.aziuriamod.entity.client.worm.WormRenderer;
import net.Aziuria.aziuriamod.island.util.BlockBatcher;
import net.Aziuria.aziuriamod.client.ModClientCommonBusEvents;
import net.Aziuria.aziuriamod.client.damage.ModDamageTypes;
import net.Aziuria.aziuriamod.command.*;
import net.Aziuria.aziuriamod.datamaps.ModDataMapHooks;
import net.Aziuria.aziuriamod.exhaustion.capability.ExhaustionHudOverlay;
import net.Aziuria.aziuriamod.exhaustion.capability.ExhaustionProvider;
import net.Aziuria.aziuriamod.exhaustion.handler.*;
import net.Aziuria.aziuriamod.exhaustion.network.ExhaustionNetworkHandler;
import net.Aziuria.aziuriamod.exhaustion.registry.ExhaustionSetup;
import net.Aziuria.aziuriamod.fog.*;
import net.Aziuria.aziuriamod.fog.handler.FogZombieTickHandler;
import net.Aziuria.aziuriamod.handler.*;
import net.Aziuria.aziuriamod.fog.handler.FogEventWorldLoadHandler;
import net.Aziuria.aziuriamod.block.entity.ModBlockEntities;
import net.Aziuria.aziuriamod.client.ClientModInitializer;
import net.Aziuria.aziuriamod.client.screen.ModMenus;
import net.Aziuria.aziuriamod.data.ModDataComponents;
import net.Aziuria.aziuriamod.events.ModEvents;
import net.Aziuria.aziuriamod.item.ModCreativeModeTabs;
import net.Aziuria.aziuriamod.item.ModItems;
import net.Aziuria.aziuriamod.item.custom.entities.ModEntities;
import net.Aziuria.aziuriamod.fog.network.NetworkHandler;
import net.Aziuria.aziuriamod.fog.network.PlayerJoinHandler;
//import net.Aziuria.aziuriamod.water.PlayerJoinsHandler;
import net.Aziuria.aziuriamod.loot.ModLootModifiers;
import net.Aziuria.aziuriamod.particle.FallingLeafParticle;
import net.Aziuria.aziuriamod.particle.ModParticles;
import net.Aziuria.aziuriamod.sounds.ModSounds;
import net.Aziuria.aziuriamod.thirst.capability.ThirstHudOverlay;
import net.Aziuria.aziuriamod.thirst.capability.ThirstProvider;
import net.Aziuria.aziuriamod.thirst.handler.*;
import net.Aziuria.aziuriamod.thirst.network.ThirstNetworkHandler;
import net.Aziuria.aziuriamod.thirst.registry.ThirstSetup;
import net.Aziuria.aziuriamod.villager.ModVillagerTrades;
import net.Aziuria.aziuriamod.villager.ModVillagers;
import net.Aziuria.aziuriamod.villager.VillagerAIHandler;
import net.Aziuria.aziuriamod.villager.VillagerProfessionTickHandler;
import net.Aziuria.aziuriamod.worldgen.rules.registry.ModPlacementModifier;
import net.minecraft.client.renderer.entity.EntityRenderers;
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
        modEventBus.addListener(NetworkHandler::register);

        modEventBus.addListener(ThirstNetworkHandler::register);
        modEventBus.addListener(ExhaustionNetworkHandler::register);


        if (FMLEnvironment.dist == Dist.CLIENT) {
            ClientModInitializer.register(modEventBus);
        }

        NeoForge.EVENT_BUS.register(this);
        NeoForge.EVENT_BUS.register(BlockDropHandler.class);
        NeoForge.EVENT_BUS.register(BarkStripHandler.class);
        NeoForge.EVENT_BUS.register(ModEvents.class);
        NeoForge.EVENT_BUS.register(LeafParticleHandler.class);
        NeoForge.EVENT_BUS.register(MilkBucketHandler.class);
        NeoForge.EVENT_BUS.register(FishingRodHandler.class);
        NeoForge.EVENT_BUS.register(FogEventWorldLoadHandler.class);
        NeoForge.EVENT_BUS.register(BaitedFishingHandler.class);
        NeoForge.EVENT_BUS.register(FishingRodCraftingHandler.class);
        NeoForge.EVENT_BUS.register(PlayerJoinHandler.class);


        NeoForge.EVENT_BUS.register(ThirstTickHandler.class);
        NeoForge.EVENT_BUS.register(PlayerJoinsHandler.class);
        NeoForge.EVENT_BUS.register(ThirstDrinkHandler.class);
        NeoForge.EVENT_BUS.register(ThirstPersistenceHandler.class);
        NeoForge.EVENT_BUS.register(ThirstDebuffHandler.class);
        NeoForge.EVENT_BUS.register(ThirstHudOverlay.class);


        NeoForge.EVENT_BUS.register(ExhaustionHudOverlay.class);
        NeoForge.EVENT_BUS.register(ExhaustHandler.class);
        NeoForge.EVENT_BUS.register(CopperArmorLightningHandler.class);
        NeoForge.EVENT_BUS.register(ExhaustionPersistenceHandler.class);
        NeoForge.EVENT_BUS.register(ExhaustionTickHandler.class);
        NeoForge.EVENT_BUS.register(ExhaustionPlayerHandler.class);
        NeoForge.EVENT_BUS.register(CopperBarsInteractionHandler.class);
      //  NeoForge.EVENT_BUS.register(ExhaustionDebuffHandler.class);


        NeoForge.EVENT_BUS.register(VeinMinerHandler.class);



        // ** Added registrations for fog features **
        FogRegistry.init();                                           // Initialize fog system
        NeoForge.EVENT_BUS.addListener(this::onRegisterCommands);     // Command setup listener
        NeoForge.EVENT_BUS.register(new FogVillagerAI());             // AI handler
        NeoForge.EVENT_BUS.register(new FogMobSpawnModifier());
        NeoForge.EVENT_BUS.register(new FogZombieTickHandler());
        NeoForge.EVENT_BUS.register(ModClientCommonBusEvents.class);
        NeoForge.EVENT_BUS.register(new FogZombieSpawner());
        NeoForge.EVENT_BUS.register(new FogCreeperSpawner());
        NeoForge.EVENT_BUS.register(BlockBatcher.class);
        NeoForge.EVENT_BUS.register(VillagerAIHandler.class);
        NeoForge.EVENT_BUS.register(VillagerProfessionTickHandler.class);
        NeoForge.EVENT_BUS.addListener(ModVillagerTrades::onVillagerTrades);
        NeoForge.EVENT_BUS.addListener(FastLeafDecayHandler::onServerTick);


        // Register all mod content
        ModCreativeModeTabs.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        ModItems.register(modEventBus);
        ModEntity.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModDamageTypes.register(modEventBus);
        ModSounds.register(modEventBus);
        ModVillagers.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenus.register(modEventBus);
        ModDataComponents.register(modEventBus);
        ModParticles.register(modEventBus);
        ModEntities.ENTITIES.register(modEventBus);
        ModPlacementModifier.PLACEMENT_MODIFIERS.register(modEventBus);


        // Config registration
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        LOGGER.info("AziuriaMod has loaded successfully.");
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Running common setup...");

        // ✅ Safe to call here — not during datagen
        event.enqueueWork(() -> {
            ModDataMapHooks.register();
            LOGGER.info("Registered copper bar wax/scrape data maps.");
        });
    }
    private void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                Capabilities.FluidHandler.BLOCK,
                ModBlockEntities.STEEL_BARREL_BLOCK_ENTITY.get(),
                (barrelEntity, direction) -> barrelEntity.getTank()
        );

//        ModCapabilities.register(event);
        ThirstProvider.register(event);
        ExhaustionProvider.register(event);
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
        SpawnTreeVariantCommand.register(event.getDispatcher());
        BreakRegionCommand.register(event.getDispatcher());
        VeinMinerCommand.register(event.getDispatcher());
        RefreshGravityBlocksCommand.register(event.getDispatcher());
        NearbyOreCommand.register(event.getDispatcher());
    }


    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> LOGGER.info("Client setup complete."));
            ThirstSetup.registerThirstItems();
            ExhaustionSetup.registerExhaustionItems();

            EntityRenderers.register(ModEntity.WORM.get(), WormRenderer::new);

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