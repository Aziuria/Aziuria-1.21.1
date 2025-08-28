package net.Aziuria.aziuriamod.thirst.handler;

import net.Aziuria.aziuriamod.thirst.capability.IThirst;
import net.Aziuria.aziuriamod.thirst.capability.ThirstProvider;
import net.Aziuria.aziuriamod.thirst.network.ThirstNetworkHandler;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.storage.LevelResource;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.io.File;

public class PlayerJoinsHandler {

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        // Clear network cache
        ThirstNetworkHandler.removePlayerCache(player);

        // Only reset thirst if player has no saved thirst file (new world)
        File playerDir = player.getServer().getWorldPath(LevelResource.PLAYER_DATA_DIR).toFile();
        File thirstFile = new File(playerDir, player.getUUID() + ".thirst");

        IThirst thirstCap = player.getCapability(ThirstProvider.THIRST_CAP, null);
        if (thirstCap != null && !thirstFile.exists()) {
            thirstCap.setThirst(20);
            thirstCap.setExhaustion(0f);
        }


        // Ensure thirst is synced visually
        ThirstNetworkHandler.syncThirstLevel(player);

        // Also forcibly clean up effects on join
        ThirstDebuffHandler.removeThirstModifiers(player);
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        ThirstNetworkHandler.syncThirstLevel(player);
        ThirstDebuffHandler.removeThirstModifiers(player);
    }

    @SubscribeEvent
    public static void onPlayerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        ThirstNetworkHandler.syncThirstLevel(player);
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        IThirst oldThirstCap = event.getOriginal().getCapability(ThirstProvider.THIRST_CAP, null);
        IThirst newThirstCap = event.getEntity().getCapability(ThirstProvider.THIRST_CAP, null);

        if (newThirstCap == null) return;

        // Check if cloning is due to death
        if (event.isWasDeath()) {
            // Reset thirst to full on respawn after death
            newThirstCap.setThirst(20);
            newThirstCap.setExhaustion(0f);
        } else if (oldThirstCap != null) {
            // Copy thirst when cloning without death (dimension change, teleport)
            newThirstCap.setThirst(oldThirstCap.getThirst());
            newThirstCap.setExhaustion(oldThirstCap.getExhaustion());
        } else {
            // Default fallback
            newThirstCap.setThirst(20);
            newThirstCap.setExhaustion(0f);
        }

        if (event.getEntity() instanceof ServerPlayer player) {
            ThirstNetworkHandler.syncThirstLevel(player);
        }
    }
}