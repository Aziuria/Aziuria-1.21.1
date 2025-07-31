//package net.Aziuria.aziuriamod.water;
//
//import com.mojang.blaze3d.systems.RenderSystem;
//import net.Aziuria.aziuriamod.AziuriaMod;
//import net.minecraft.client.Minecraft;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.entity.player.Player;
//import net.neoforged.bus.api.SubscribeEvent;
//import net.neoforged.neoforge.client.event.RenderGuiEvent;
//
//public class WaterHudOverlay {
//
//    private static final ResourceLocation WATER_EMPTY_ICON =
//            ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, "textures/gui/water_empty.png");
//    private static final ResourceLocation WATER_HALF_ICON =
//            ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, "textures/gui/water_half.png");
//    private static final ResourceLocation WATER_FULL_ICON =
//            ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, "textures/gui/water_full.png");
//
//    private static final int MAX_WATER = 20;
//
//    @SubscribeEvent
//    public static void onRenderHud(RenderGuiEvent.Post event) {
//        Minecraft mc = Minecraft.getInstance();
//        Player player = mc.player;
//        if (player == null) return;
//
//        int waterLevel = ClientWaterState.getWaterLevel();
//
//        int x = 10;
//        int y = 10;
//
//        RenderSystem.setShaderTexture(0, WATER_EMPTY_ICON);
//
//        int icons = MAX_WATER / 2; // 10 icons for max 20 water
//        for (int i = 0; i < icons; i++) {
//            int iconX = x + i * 10;
//            event.getGuiGraphics().blit(WATER_EMPTY_ICON, iconX, y, 0, 0, 9, 9);
//        }
//
//        for (int i = 0; i < waterLevel / 2; i++) {
//            int iconX = x + i * 10;
//            event.getGuiGraphics().blit(WATER_FULL_ICON, iconX, y, 0, 0, 9, 9);
//        }
//
//        if (waterLevel % 2 == 1) {
//            int iconX = x + (waterLevel / 2) * 10;
//            event.getGuiGraphics().blit(WATER_HALF_ICON, iconX, y, 0, 0, 9, 9);
//        }
//    }
//}