package net.Aziuria.aziuriamod.block.entity.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import net.Aziuria.aziuriamod.block.entity.ModBlockEntities;
import net.Aziuria.aziuriamod.sounds.FadingSirenSoundInstance;
import net.Aziuria.aziuriamod.sounds.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SpeakerBlockEntity extends BlockEntity {

    private static final Set<SpeakerBlockEntity> LOADED_SPEAKERS =
            Collections.newSetFromMap(new ConcurrentHashMap<>());

    private static long lastSirenTime = 0L;
    private static final long SIREN_COOLDOWN_MS = 2 * 60 * 1000;

    public SpeakerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SPEAKER.get(), pos, state);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (level != null && level.isClientSide) {
            LOADED_SPEAKERS.add(this);
        }
    }

    @Override
    public void setRemoved() {
        if (level != null && level.isClientSide) {
            LOADED_SPEAKERS.remove(this);
        }
        super.setRemoved();
    }

    public static void playSirenOnAllSpeakers() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastSirenTime < SIREN_COOLDOWN_MS) return;

        Minecraft mc = Minecraft.getInstance();
        Level level = mc.level;
        if (level == null || !level.isClientSide) return;

        for (SpeakerBlockEntity speaker : LOADED_SPEAKERS) {
            final BlockPos pos = speaker.getBlockPos();

            // Only play for nearby players
            for (Player player : level.players()) {
                double distanceSq = player.distanceToSqr(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);

                if (distanceSq <= 100 * 100) {
                    RenderSystem.recordRenderCall(() -> {
                        mc.getSoundManager().play(new FadingSirenSoundInstance(ModSounds.SIREN.get(), pos));
                    });
                    break;
                }
            }
        }

        lastSirenTime = currentTime;
    }

}