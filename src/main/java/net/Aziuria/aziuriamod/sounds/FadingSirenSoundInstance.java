package net.Aziuria.aziuriamod.sounds;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;
import net.minecraft.client.resources.sounds.SoundInstance.Attenuation;

public class FadingSirenSoundInstance extends AbstractTickableSoundInstance {

    private static final int MAX_DISTANCE = 150;
    private static final int SOUND_DURATION_TICKS = 20 * 11;  // 11 seconds duration
    private static final int FADE_DURATION_TICKS = 20 * 3;    // 3 seconds fade-in/out
    private static final float MAX_BASE_VOLUME = 1.0f;        // Max volume cap

    private final Minecraft mc = Minecraft.getInstance();
    private final BlockPos sourcePos;

    private float internalVolume = 0.1f;  // Start softly to avoid pop
    private boolean fadingIn = true;
    private int ticksLived = 0;

    public FadingSirenSoundInstance(SoundEvent sound, BlockPos pos) {
        super(sound, SoundSource.BLOCKS, SoundInstance.createUnseededRandom());
        this.sourcePos = pos;

        this.looping = false;
        this.delay = 0;

        this.x = pos.getX() + 0.5;
        this.y = pos.getY() + 0.5;
        this.z = pos.getZ() + 0.5;

        this.volume = internalVolume;      // Start at 0.1f volume
        this.relative = false;             // Positional sound
        this.attenuation = Attenuation.NONE; // Disable built-in culling
    }

    @Override
    public void tick() {
        ticksLived++;

        Level level = mc.level;
        LocalPlayer player = mc.player;

        if (level == null || player == null) {
            this.stop();
            return;
        }

        double distanceSqr = player.position().distanceToSqr(x, y, z);
        if (distanceSqr > MAX_DISTANCE * MAX_DISTANCE) {
            this.stop();
            return;
        }

        float distanceBlocks = (float) Math.sqrt(distanceSqr);

        // Distance attenuation factor, quadratic falloff
        float distVolumeFactor = 1f / (1f + 0.0044f * distanceBlocks * distanceBlocks);
        distVolumeFactor = Mth.clamp(distVolumeFactor, 0f, 1f);

        // Fade-in logic: increase internalVolume smoothly from 0.1f to 1f over FADE_DURATION_TICKS
        if (fadingIn) {
            float fadeStep = (1.0f - 0.1f) / FADE_DURATION_TICKS;
            internalVolume = Mth.clamp(internalVolume + fadeStep, 0.1f, 1f);
            if (internalVolume >= 1f) {
                fadingIn = false;
            }
        }

        // Fade-out logic after fadeOutStart tick count
        int fadeOutStart = SOUND_DURATION_TICKS - FADE_DURATION_TICKS;
        if (ticksLived >= fadeOutStart) {
            float fadeStep = (1.0f - 0.1f) / FADE_DURATION_TICKS;
            internalVolume = Mth.clamp(internalVolume - fadeStep, 0.1f, 1f);
        }

        // Calculate final volume
        this.volume = internalVolume * distVolumeFactor * MAX_BASE_VOLUME;
        this.volume = Mth.clamp(this.volume, 0f, 1f);

        if (ticksLived >= SOUND_DURATION_TICKS || this.volume <= 0f) {
            this.stop();
        }
    }
}