package net.Aziuria.aziuriamod.fog;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ViewportEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;

public class FogRendererHook {
    private static final Minecraft mc = Minecraft.getInstance();

    // --- Smoothing storage ---
    private static float smoothedNear = -1f; // uninitialized
    private static float smoothedFar = -1f;
    private static final float SMOOTH_FACTOR = 0.05f; // smaller = smoother

    // --- Horizon start distance (blocks) ---
    private static final float HORIZON_DISTANCE = 800f; // 50 chunks

    @SubscribeEvent
    public void onRenderFog(ViewportEvent.RenderFog event) {
        FogType fog = FogEventManager.getActiveFog();
        if (fog == null || mc.player == null || mc.level == null) return;

        BlockPos playerPos = mc.player.blockPosition();


        // --- Terrain awareness ---
        BlockPos topPos = mc.level.getHeightmapPos(
                net.minecraft.world.level.levelgen.Heightmap.Types.WORLD_SURFACE,
                playerPos
        );
        double terrainOffset = topPos.getY() - playerPos.getY();

        // --- Random vertical sway ---
        double sway = (Math.random() - 0.5); // ±0.5 block

        // --- Get distances from FogEventManager ---
        float targetFar = FogEventManager.getFogEndDistance();
        float targetNear = fog.getFogStart() * (targetFar / fog.getFogEnd(FogEventManager.getIntensity()));


        // --- Initialize smoothed values on first frame ---
        if (smoothedFar < 0f) {
            smoothedFar = targetFar;
            smoothedNear = targetNear;
        }

        // --- Smooth transitions ---
        smoothedNear += (targetNear - smoothedNear) * SMOOTH_FACTOR;
        smoothedFar  += (targetFar  - smoothedFar)  * SMOOTH_FACTOR;

        // --- Apply to renderer ---
        event.setNearPlaneDistance(smoothedNear);
        event.setFarPlaneDistance(smoothedFar);
        event.setCanceled(true);
    }

    @SubscribeEvent
    public void onFogColor(ViewportEvent.ComputeFogColor event) {
        FogType fog = FogEventManager.getActiveFog();
        if (fog == null || mc.player == null || mc.level == null) return;

        float grayValue = 0.7f;
        event.setRed(grayValue);
        event.setGreen(grayValue);
        event.setBlue(grayValue);

    }

    private static float clamp(float v) {
        return Math.max(0f, Math.min(1f, v));
    }
}