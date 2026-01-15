package net.Aziuria.aziuriamod.hud.exhaustion.handler;

import net.Aziuria.aziuriamod.hud.exhaustion.capability.Exhaustion;
import net.Aziuria.aziuriamod.hud.exhaustion.capability.ExhaustionProvider;
import net.Aziuria.aziuriamod.hud.exhaustion.capability.Iexhaustion;
import net.Aziuria.aziuriamod.hud.exhaustion.network.ExhaustionNetworkHandler;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.storage.LevelResource;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.io.File;

public class ExhaustionPlayerHandler {

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        // Clear network cache
        ExhaustionNetworkHandler.removePlayerCache(player);

        // Only reset exhaustion if player has no saved exhaustion file (new world)
        File playerDir = player.getServer().getWorldPath(LevelResource.PLAYER_DATA_DIR).toFile();
        File exhaustionFile = new File(playerDir, player.getUUID() + ".exhaustion");

        Iexhaustion exhaustionCap = player.getCapability(ExhaustionProvider.EXHAUSTION_CAP, null);
        if (exhaustionCap != null && !exhaustionFile.exists()) {
            exhaustionCap.setExhaustion(Exhaustion.MAX_EXHAUSTION);
        }

        // Remove any lingering exhaustion debuffs
        ExhaustionDebuffHandler.removeExhaustionModifiers(player);

        // Sync exhaustion visually
        ExhaustionNetworkHandler.syncExhaustionLevel(player);
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        ExhaustionNetworkHandler.syncExhaustionLevel(player);
    }

    @SubscribeEvent
    public static void onPlayerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        ExhaustionNetworkHandler.syncExhaustionLevel(player);
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (!(event.getEntity() instanceof ServerPlayer newPlayer &&
                event.getOriginal() instanceof ServerPlayer oldPlayer)) return;

        Iexhaustion oldExhaustion = oldPlayer.getCapability(ExhaustionProvider.EXHAUSTION_CAP, null);
        Iexhaustion newExhaustion = newPlayer.getCapability(ExhaustionProvider.EXHAUSTION_CAP, null);

        if (newExhaustion == null) return;

        if (event.isWasDeath()) {
            // Reset exhaustion on death
            newExhaustion.setExhaustion(Exhaustion.MAX_EXHAUSTION);
        } else if (oldExhaustion != null) {
            // Copy exhaustion on dimension change / teleport
            newExhaustion.setExhaustion(oldExhaustion.getExhaustion());
        } else {
            // Default fallback
            newExhaustion.setExhaustion(Exhaustion.MAX_EXHAUSTION);
        }

        ExhaustionNetworkHandler.syncExhaustionLevel(newPlayer);
    }
}