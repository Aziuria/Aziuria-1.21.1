package net.Aziuria.aziuriamod.thirst.handler;

import net.Aziuria.aziuriamod.thirst.capability.IThirst;
import net.Aziuria.aziuriamod.thirst.capability.ThirstProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class ThirstPersistenceHandler {

    private static final String THIRST_NBT_KEY = "AziuriaThirstData";

    @SubscribeEvent
    public static void onPlayerSaveToFile(PlayerEvent.SaveToFile event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        IThirst thirst = player.getCapability(ThirstProvider.THIRST_CAP, null);
        if (thirst == null) return;

        CompoundTag thirstTag = thirst.serializeNBT();

        File thirstFile = event.getPlayerFile("thirst");
        try (FileOutputStream fos = new FileOutputStream(thirstFile);
             GZIPOutputStream gos = new GZIPOutputStream(fos);
             DataOutputStream dos = new DataOutputStream(gos)) {
            NbtIo.write(thirstTag, dos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SubscribeEvent
    public static void onPlayerLoadFromFile(PlayerEvent.LoadFromFile event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        File thirstFile = event.getPlayerFile("thirst");
        if (!thirstFile.exists()) return;

        try (FileInputStream fis = new FileInputStream(thirstFile);
             GZIPInputStream gis = new GZIPInputStream(fis);
             DataInputStream dis = new DataInputStream(gis)) {
            CompoundTag thirstTag = NbtIo.read(dis);
            IThirst thirst = player.getCapability(ThirstProvider.THIRST_CAP, null);
            if (thirst != null) {
                thirst.deserializeNBT(thirstTag);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}