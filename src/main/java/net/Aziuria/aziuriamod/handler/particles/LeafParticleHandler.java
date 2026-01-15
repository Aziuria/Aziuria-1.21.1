package net.Aziuria.aziuriamod.handler.particles;

import net.Aziuria.aziuriamod.particle.particles.FallingLeafParticle;
import net.Aziuria.aziuriamod.particle.ModParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

@EventBusSubscriber(modid = "aziuriamod", value = Dist.CLIENT)
public class LeafParticleHandler {

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        ClientLevel level = mc.level;
        LocalPlayer player = mc.player;

        if (level == null || player == null) return;

        RandomSource random = level.random;

        BlockPos.betweenClosedStream(
                player.blockPosition().offset(-18, -6, -18),
                player.blockPosition().offset(18, 6, 18)
        ).forEach(pos -> {
            BlockState state = level.getBlockState(pos);
            if (state.getBlock() instanceof LeavesBlock && random.nextFloat() < 0.0025f) {
                double px = pos.getX() + random.nextDouble();
                double py = pos.getY() + random.nextDouble();
                double pz = pos.getZ() + random.nextDouble();

                // Add random sideways drift velocity between -0.01 and 0.01 on X and Z
                double vx = (random.nextDouble() - 0.5) * 0.02;
                double vy = -0.03; // consistent gentle fall speed
                double vz = (random.nextDouble() - 0.5) * 0.02;

                SimpleParticleType type = switch (random.nextInt(4)) {
                    case 0 -> ModParticles.FALLING_LEAF_1.get();
                    case 1 -> ModParticles.FALLING_LEAF_2.get();
                    case 2 -> ModParticles.FALLING_LEAF_3.get();
                    default -> ModParticles.FALLING_LEAF_4.get();
                };

                int color = BiomeColors.getAverageFoliageColor(level, pos);
                float r = (color >> 16 & 255) / 255.0f;
                float g = (color >> 8 & 255) / 255.0f;
                float b = (color & 255) / 255.0f;

                Particle particle = Minecraft.getInstance().particleEngine.createParticle(
                        type, px, py, pz, vx, vy, vz);

                if (particle instanceof FallingLeafParticle leafParticle) {
                    leafParticle.setColor(r, g, b);
                }
            }
        });
    }
}