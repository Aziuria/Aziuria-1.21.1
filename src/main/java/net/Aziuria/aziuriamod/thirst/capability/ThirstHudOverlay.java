package net.Aziuria.aziuriamod.thirst.capability;

import com.mojang.blaze3d.systems.RenderSystem;
import net.Aziuria.aziuriamod.AziuriaMod;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderGuiEvent;

public class ThirstHudOverlay {

    private static final ResourceLocation WATER_EMPTY_ICON =
            ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, "textures/gui/water_empty.png");
    private static final ResourceLocation WATER_HALF_ICON =
            ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, "textures/gui/water_half.png");
    private static final ResourceLocation WATER_FULL_ICON =
            ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, "textures/gui/water_full.png");

    private static final int MAX_THIRST = 20;
    private static int lastAirBubbleTick = -1;

    @SubscribeEvent
    public static void onRenderHud(RenderGuiEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        if (player == null || player.isCreative() || mc.options.hideGui) return;

        IThirst thirst = player.getCapability(ThirstProvider.THIRST_CAP, null);
        if (thirst == null) return;

        int thirstLevel = thirst.getThirst();

        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();
        int spacing = 10;
        int icons = MAX_THIRST / 2;
        int xStart = screenWidth / 2 + 91 - 81 - 3;
        int y = screenHeight - 53;

        int currentTick = player.tickCount;

        boolean airBubblesVisible = player.isUnderWater() && player.getAirSupply() < player.getMaxAirSupply() && player.getAirSupply() > 0;

        // Update lastAirBubbleTick when air bubbles are visible
        if (airBubblesVisible) {
            lastAirBubbleTick = currentTick;
        }

        // Shift thirst bar up if air bubbles are visible or if less than 20 ticks (1 second) have passed since last visible
        if (airBubblesVisible || (lastAirBubbleTick != -1 && currentTick - lastAirBubbleTick < 20)) {
            y -= 10; // Shift up by 10 pixels to avoid overlap
        }

        float scale = 0.8f;

        event.getGuiGraphics().pose().pushPose();
        event.getGuiGraphics().pose().scale(scale, scale, 1.0f);

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
}