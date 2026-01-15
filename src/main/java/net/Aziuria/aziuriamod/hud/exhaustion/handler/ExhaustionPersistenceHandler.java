package net.Aziuria.aziuriamod.hud.exhaustion.handler;

import net.Aziuria.aziuriamod.hud.exhaustion.capability.Exhaustion;
import net.Aziuria.aziuriamod.hud.exhaustion.capability.ExhaustionProvider;
import net.Aziuria.aziuriamod.hud.exhaustion.capability.Iexhaustion;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class ExhaustionPersistenceHandler {

    @SubscribeEvent
    public static void onPlayerSaveToFile(PlayerEvent.SaveToFile event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        Iexhaustion exhaustion = player.getCapability(ExhaustionProvider.EXHAUSTION_CAP, null);
        if (exhaustion == null) return;

        CompoundTag tag = new CompoundTag();
        tag.putFloat("exhaustion", exhaustion.getExhaustion());

        File file = event.getPlayerFile("exhaustion");
        try (FileOutputStream fos = new FileOutputStream(file);
             GZIPOutputStream gos = new GZIPOutputStream(fos);
             DataOutputStream dos = new DataOutputStream(gos)) {
            NbtIo.write(tag, dos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SubscribeEvent
    public static void onPlayerLoadFromFile(PlayerEvent.LoadFromFile event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        File file = event.getPlayerFile("exhaustion");
        Iexhaustion exhaustion = player.getCapability(ExhaustionProvider.EXHAUSTION_CAP, null);
        if (exhaustion == null) return;

        if (!file.exists()) {
            // Mark this as a brand new world
            player.getPersistentData().putBoolean("AziuriaNewWorldExhaustion", true);
            exhaustion.setExhaustion(Exhaustion.MAX_EXHAUSTION);
            return;
        }

        try (FileInputStream fis = new FileInputStream(file);
             GZIPInputStream gis = new GZIPInputStream(fis);
             DataInputStream dis = new DataInputStream(gis)) {
            CompoundTag tag = NbtIo.read(dis);
            exhaustion.setExhaustion(tag.getFloat("exhaustion"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}