package net.Aziuria.aziuriamod.hud.thirst.capability;

import com.mojang.blaze3d.systems.RenderSystem;
import net.Aziuria.aziuriamod.AziuriaMod;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class ThirstHudOverlay {

    private static final ResourceLocation WATER_EMPTY_ICON =
            ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, "textures/gui/water_empty.png");
    private static final ResourceLocation WATER_HALF_ICON =
            ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, "textures/gui/water_half.png");
    private static final ResourceLocation WATER_FULL_ICON =
            ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, "textures/gui/water_full.png");

    private static final int MAX_THIRST = 20;
    private static int lastAirBubbleTick = -1;
    private static final int PRE_SHIFT_TICKS = 5;   // shift before bubbles appear
    private static final int POST_SHIFT_TICKS = 5;  // keep shift after bubbles vanish
    private static int shiftCounter = 0;

    @SubscribeEvent
    public static void onRenderHud(RenderGuiEvent.Pre event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        if (player == null || player.isCreative() || mc.options.hideGui) return;
        if (player == null || player.isCreative() || player.isSpectator() || mc.options.hideGui) return;

        IThirst thirst = player.getCapability(ThirstProvider.THIRST_CAP, null);
        if (thirst == null) return;

        int thirstLevel = thirst.getThirst();

        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();
        int spacing = 10;
        int icons = MAX_THIRST / 2;
        int xStart = screenWidth / 2 + 91 - 81 - 3;
        int y = screenHeight - 52;

        int currentTick = player.tickCount;

        boolean hasWaterBreathing = player.hasEffect(net.minecraft.world.effect.MobEffects.WATER_BREATHING);
        boolean airBubblesVisible = player.isUnderWater() && (
                (player.getAirSupply() < player.getMaxAirSupply() && player.getAirSupply() > 0)
                        || hasWaterBreathing
        );

        // <<< CHANGED: new robust shifting logic
        boolean shouldShift = false;

        // Early shift as soon as air starts dropping
        if (player.getAirSupply() < player.getMaxAirSupply()) {
            shouldShift = true;
        }

        // Or if vanilla bubbles are already visible
        if (airBubblesVisible) {
            shouldShift = true;
        }

        // Manage linger so bar doesn’t snap back too soon
        if (shouldShift) {
            shiftCounter = POST_SHIFT_TICKS;
        } else if (shiftCounter > 0) {
            shiftCounter--;
        }

        if (shouldShift || shiftCounter > 0) {
            y -= 10;
        }

        float scale = 0.8f;

        event.getGuiGraphics().pose().pushPose();
        event.getGuiGraphics().pose().scale(scale, scale, 1.0f);

        // Make icons fully opaque like the armor bar
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        int scaledXStart = (int) (xStart / scale);
        int scaledY = (int) (y / scale);

        int tickCount = player.tickCount;
        int waveCycle = 40;
        int waveDuration = 10;
        int waveTick = tickCount % waveCycle;

        boolean waveActive = (thirstLevel <= 19) && waveTick < waveDuration;
        float waveProgress = waveTick / (float) waveDuration;
        float waveAmplitude = 1.5f;

        // Draw empty icons
        RenderSystem.setShaderTexture(0, WATER_EMPTY_ICON);
        for (int i = 0; i < icons; i++) {
            float iconPos = i / (float)(icons - 1);
            float wobbleY = 0f;

            if (waveActive) {
                float diff = waveProgress - iconPos;
                if (diff >= -0.1f && diff <= 0.1f) {
                    float phase = (diff + 0.1f) / 0.2f * (float) Math.PI;
                    wobbleY = (float) Math.sin(phase) * waveAmplitude;
                }
            }

            int iconX = scaledXStart + i * spacing;
            event.getGuiGraphics().blit(WATER_EMPTY_ICON, iconX, (int)(scaledY + wobbleY), 0f, 0f, 16, 16, 16, 16);
        }

        // Draw full icons
        RenderSystem.setShaderTexture(0, WATER_FULL_ICON);
        for (int i = 0; i < thirstLevel / 2; i++) {
            int iconIndex = icons - 1 - i;
            float iconPos = iconIndex / (float)(icons - 1);
            float wobbleY = 0f;

            if (waveActive) {
                float diff = waveProgress - iconPos;
                if (diff >= -0.1f && diff <= 0.1f) {
                    float phase = (diff + 0.1f) / 0.2f * (float) Math.PI;
                    wobbleY = (float) Math.sin(phase) * waveAmplitude;
                }
            }

            int iconX = scaledXStart + iconIndex * spacing;
            event.getGuiGraphics().blit(WATER_FULL_ICON, iconX, (int)(scaledY + wobbleY), 0f, 0f, 16, 16, 16, 16);
        }

        // Draw half icon
        if (thirstLevel % 2 == 1) {
            int iconIndex = icons - 1 - (thirstLevel / 2);
            float iconPos = iconIndex / (float)(icons - 1);
            float wobbleY = 0f;

            if (waveActive) {
                float diff = waveProgress - iconPos;
                if (diff >= -0.1f && diff <= 0.1f) {
                    float phase = (diff + 0.1f) / 0.2f * (float) Math.PI;
                    wobbleY = (float) Math.sin(phase) * waveAmplitude;
                }
            }

            int iconX = scaledXStart + iconIndex * spacing;
            RenderSystem.setShaderTexture(0, WATER_HALF_ICON);
            event.getGuiGraphics().blit(WATER_HALF_ICON, iconX, (int)(scaledY + wobbleY), 0f, 0f, 16, 16, 16, 16);
        }

        event.getGuiGraphics().pose().popPose();
    }

    // <<< ADDED: reset when switching dimensions so overlay doesn't "stick"
    @SubscribeEvent
    public static void onDimensionChange(PlayerEvent.PlayerChangedDimensionEvent event) {
        lastAirBubbleTick = -1;
    }
}