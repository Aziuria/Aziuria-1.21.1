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

    @SubscribeEvent
    public static void onRenderHud(RenderGuiEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        AziuriaMod.LOGGER.info("Thirst HUD render event fired.");

        if (player == null) {
            AziuriaMod.LOGGER.warn("Player is null during thirst HUD rendering.");
            return;
        }

        if (mc.options.hideGui) {
            AziuriaMod.LOGGER.info("GUI is hidden, skipping thirst HUD rendering.");
            return;
        }

        IThirst thirst = player.getCapability(ThirstProvider.THIRST_CAP, null);
        if (thirst == null) {
            AziuriaMod.LOGGER.warn("Thirst capability missing, skipping thirst bar rendering.");
            return;
        }

        int thirstLevel = thirst.getThirst();
        AziuriaMod.LOGGER.info("Rendering thirst bar. Current thirst level: {}", thirstLevel);

        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();
        int spacing = 10;
        int icons = MAX_THIRST / 2;
        int xStart = screenWidth / 2 + 91 - 81 - 3;
        int y = screenHeight - 53;

        // Scale factor (e.g. 0.5 = 8x8 size)
        float scale = 0.8f;

        // Prepare pose stack for scaling
        event.getGuiGraphics().pose().pushPose();
        event.getGuiGraphics().pose().scale(scale, scale, 1.0f);

        int scaledXStart = (int) (xStart / scale);
        int scaledY = (int) (y / scale);

        // Draw empty icons (left to right)
        RenderSystem.setShaderTexture(0, WATER_EMPTY_ICON);
        for (int i = 0; i < icons; i++) {
            int iconX = scaledXStart + i * spacing;
            event.getGuiGraphics().blit(WATER_EMPTY_ICON, iconX, scaledY, 0f, 0f, 16, 16, 16, 16);
        }

        // Draw full icons (right to left)
        RenderSystem.setShaderTexture(0, WATER_FULL_ICON);
        for (int i = 0; i < thirstLevel / 2; i++) {
            int iconX = scaledXStart + (icons - 1 - i) * spacing;
            event.getGuiGraphics().blit(WATER_FULL_ICON, iconX, scaledY, 0f, 0f, 16, 16, 16, 16);
        }

        // Draw half icon if needed
        if (thirstLevel % 2 == 1) {
            int iconX = scaledXStart + (icons - 1 - (thirstLevel / 2)) * spacing;
            RenderSystem.setShaderTexture(0, WATER_HALF_ICON);
            event.getGuiGraphics().blit(WATER_HALF_ICON, iconX, scaledY, 0f, 0f, 16, 16, 16, 16);
        }

        // Restore pose stack
        event.getGuiGraphics().pose().popPose();
    }
}