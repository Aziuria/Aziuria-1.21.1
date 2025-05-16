package net.Aziuria.aziuriamod.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.Aziuria.aziuriamod.AziuriaMod;
import net.Aziuria.aziuriamod.block.entity.renderer.FluidRenderer;
import net.Aziuria.aziuriamod.block.entity.SteelBarrelBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.util.Mth;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;

public class SteelBarrelRenderer implements BlockEntityRenderer<SteelBarrelBlockEntity> {

    // Barrel capacity: 10 buckets = 10,000 mB
    private static final int CAPACITY = 10000;

    // Barrel fluid surface max height in blocks (22 pixels / 16)
    private static final double BARREL_HEIGHT = 22 / 16.0;

    public SteelBarrelRenderer(BlockEntityRendererProvider.Context context) {
        // No special setup required for now
    }

    @Override
    public void render(SteelBarrelBlockEntity barrel, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int light, int overlay) {

        AziuriaMod.LOGGER.info("Rendering SteelBarrel at pos {} with fluid tank {}", barrel.getBlockPos(), barrel.getTank());

        if (barrel.getTank().isEmpty()) {
            AziuriaMod.LOGGER.info("SteelBarrel tank is empty, skipping rendering.");
            return;
        }

        var fluidStack = barrel.getTank().getFluid();
        double fluidAmount = fluidStack.getAmount();
        AziuriaMod.LOGGER.info("Fluid amount: {}, Fluid type: {}", fluidAmount, fluidStack.getFluid());

        double height = Mth.lerp(fluidAmount / (double) CAPACITY, 0, BARREL_HEIGHT);
        AziuriaMod.LOGGER.info("Calculated fluid surface height: {}", height);

        FluidState fluidState = fluidStack.getFluid().defaultFluidState();

        VertexConsumer vertexConsumer = FluidRenderer.getFluidSpriteBuffer(
                barrel.getLevel(),
                barrel.getBlockPos(),
                fluidStack,
                bufferSource,
                RenderType.translucent(),
                FluidRenderer.FluidSpriteType.STILL
        ).getRight();

        int color = FluidRenderer.getFluidColor(barrel.getLevel(), barrel.getBlockPos(), fluidState);
        AziuriaMod.LOGGER.info("Fluid color tint: {}", color);

        FluidRenderer.renderFluidFace(vertexConsumer, poseStack, new Vec3[]{
                new Vec3(14 / 16f, height, 14 / 16f),
                new Vec3(14 / 16f, height, 2 / 16f),
                new Vec3(2 / 16f, height, 2 / 16f),
                new Vec3(2 / 16f, height, 14 / 16f),
        }, color, light);
    }
}