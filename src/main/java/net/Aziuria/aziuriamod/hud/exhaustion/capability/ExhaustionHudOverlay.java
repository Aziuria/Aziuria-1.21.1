package net.Aziuria.aziuriamod.hud.exhaustion.capability;

import com.mojang.blaze3d.systems.RenderSystem;
import net.Aziuria.aziuriamod.AziuriaMod;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderGuiEvent;

public class ExhaustionHudOverlay {

    private static final ResourceLocation EXHAUST_FULL =
            ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, "textures/gui/exhaust_progress.png"); // full bar
    private static final ResourceLocation EXHAUST_EMPTY =
            ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, "textures/gui/exhaust_background.png"); // empty overlay

    private static final float MAX_EXHAUST = 20f;
    private static float lastExhaustion = -1f; // for smooth sliding

    @SubscribeEvent
    public static void onRenderHud(RenderGuiEvent.Pre event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        if (player == null || player.isCreative() || player.isSpectator() || mc.options.hideGui) return;

        Iexhaustion exhaustionCap = player.getCapability(ExhaustionProvider.EXHAUSTION_CAP, null);
        if (exhaustionCap == null) return;

        float exhaustionLevel = exhaustionCap.getExhaustion();

        if (lastExhaustion < 0f) lastExhaustion = exhaustionLevel;

        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();
        int barWidth = 81;
        int barHeight = 6;

        int x = screenWidth / 2 - 91; // align with hunger
        int y = screenHeight - 20 - barHeight;

        float slideSpeed = 0.2f;
        lastExhaustion += (exhaustionLevel - lastExhaustion) * slideSpeed;

        int filledWidth = (int)((1 - (lastExhaustion / MAX_EXHAUST)) * barWidth);
        int overlayX = x + (barWidth - filledWidth); // right-to-left

        event.getGuiGraphics().pose().pushPose();

        // Center + shrink vertically by half
        event.getGuiGraphics().pose().translate(x, y + (barHeight / 3f), 0); // move pivot down so it centers
        event.getGuiGraphics().pose().scale(2.25f, 0.5f, 1.0f);               // shrink vertically
        event.getGuiGraphics().pose().translate(-x, -(y + (barHeight / 3f)), 0);


        // --- DYNAMIC COLOR FOR FULL BAR ONLY ---
        float exhaustionRatio = lastExhaustion / MAX_EXHAUST; // 0 = empty, 1 = full
        float r, g;

        // full = green, empty = red
        if (exhaustionRatio > 0.5f) { // YELLOW → GREEN
            r = (1f - exhaustionRatio) * 2f;
            g = 1f;
        } else { // RED → YELLOW
            r = 1f;
            g = exhaustionRatio * 2f;
        }

// --- ADD STRONG PULSING GLOW ---
        long time = System.currentTimeMillis();
        float pulse = (float) ((Math.sin(time * 0.01) + 1) / 2f); // 0 → 1 sinus wave
        float glowAmount = 0.5f * pulse; // pulse from 0 to 0.5

        r = Math.min(r + glowAmount, 1f);
        g = Math.min(g + glowAmount, 1f);

        RenderSystem.setShaderColor(r, g, 0f, 1f);
        event.getGuiGraphics().blit(EXHAUST_FULL, x, y, 0, 0, barWidth, barHeight, 81, 6);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);

        float overlayAlpha = 0.3f + 0.2f * pulse; // pulsing transparency
        RenderSystem.setShaderColor(1f, 1f, 1f, overlayAlpha);
        event.getGuiGraphics().blit(EXHAUST_FULL, x, y, 0, 0, barWidth, barHeight, 81, 6);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);

        // --- EMPTY OVERLAY ---
        if (filledWidth > 0) {
            int uOffset = barWidth - filledWidth;
            event.getGuiGraphics().blit(EXHAUST_EMPTY, overlayX, y, uOffset, 0, filledWidth, barHeight, 81, 6);
        }

        event.getGuiGraphics().pose().popPose();
    }
}