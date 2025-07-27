package net.Aziuria.aziuriamod.water;

import com.mojang.blaze3d.systems.RenderSystem;
import net.Aziuria.aziuriamod.AziuriaMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderGuiEvent;

public class WaterHudOverlay {

    private static final ResourceLocation WATER_EMPTY_ICON =
            ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, "textures/gui/water_empty.png");
    private static final ResourceLocation WATER_HALF_ICON =
            ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, "textures/gui/water_half.png");
    private static final ResourceLocation WATER_FULL_ICON =
            ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, "textures/gui/water_full.png");

    @SubscribeEvent
    public static void onRenderGui(RenderGuiEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null || mc.options.hideGui) return;

        GuiGraphics guiGraphics = event.getGuiGraphics();
        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();

        int waterLevel = ClientWaterState.getWaterLevel(); // 0–20

        int iconSpacing = 9; // match vanilla
        int iconWidth = 9;
        int iconHeight = 9;
        int totalIcons = 9;

        // Matches vanilla food bar exactly
        int xStart = screenWidth / 2 + 91 - (totalIcons * iconSpacing);
        int y = screenHeight - 50; // 1 pixel above food bar

        for (int i = 0; i < totalIcons; i++) {
            int value = (i + 1) * 2;
            ResourceLocation icon;

            if (waterLevel >= value) {
                icon = WATER_FULL_ICON;
            } else if (waterLevel == value - 1) {
                icon = WATER_HALF_ICON;
            } else {
                icon = WATER_EMPTY_ICON;
            }

            RenderSystem.setShaderTexture(0, icon);
            guiGraphics.blit(icon, xStart + i * iconSpacing, y, 0, 0, iconWidth, iconHeight, iconWidth, iconHeight);
        }
    }
}