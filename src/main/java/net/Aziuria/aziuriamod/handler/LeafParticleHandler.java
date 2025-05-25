package net.Aziuria.aziuriamod.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
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
        if (mc.level == null || mc.player == null) return;

        RandomSource random = mc.level.getRandom();
        int particleCount = 1 + random.nextInt(5);

        for (int i = 0; i < particleCount; i++) {
            int dx = random.nextInt(21) - 10; // ±10 blocks X
            int dz = random.nextInt(21) - 10; // ±10 blocks Z

            BlockPos pos = mc.player.blockPosition().offset(dx, 0, dz);
            BlockState state = mc.level.getBlockState(pos);

            if (isLeafBlock(state)) {
                double px = pos.getX() + random.nextDouble();
                double py = pos.getY() + 0.1; // slightly above block
                double pz = pos.getZ() + random.nextDouble();

                // Add sideways motion for natural falling
                double vx = (random.nextDouble() - 0.5) * 0.02; // small sideways velocity X
                double vy = -0.002 - random.nextDouble() * 0.001; // slow downward velocity Y
                double vz = (random.nextDouble() - 0.5) * 0.02; // small sideways velocity Z

                mc.level.addParticle(new BlockParticleOption(ParticleTypes.FALLING_DUST, state), px, py, pz, vx, vy, vz);
            }
        }
    }

    private static boolean isLeafBlock(BlockState state) {
        return state.is(Blocks.OAK_LEAVES)
                || state.is(Blocks.BIRCH_LEAVES)
                || state.is(Blocks.SPRUCE_LEAVES)
                || state.is(Blocks.JUNGLE_LEAVES)
                || state.is(Blocks.ACACIA_LEAVES)
                || state.is(Blocks.DARK_OAK_LEAVES)
                || state.is(Blocks.MANGROVE_LEAVES)
                || state.is(Blocks.CHERRY_LEAVES);
    }
}