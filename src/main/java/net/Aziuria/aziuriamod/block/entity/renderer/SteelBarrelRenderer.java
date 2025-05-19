package net.Aziuria.aziuriamod.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.Aziuria.aziuriamod.block.entity.SteelBarrelBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.util.Mth;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;

public class SteelBarrelRenderer implements BlockEntityRenderer<SteelBarrelBlockEntity> {

    private static final int CAPACITY = 10000;
    private static final double FLUID_MIN_Y = 3 / 16.0;
    private static final double FLUID_MAX_Y = 15 / 16.0;
    private static final double FLUID_HEIGHT_RANGE = FLUID_MAX_Y - FLUID_MIN_Y;

    public SteelBarrelRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(SteelBarrelBlockEntity barrel, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int light, int overlay) {

        if (barrel.getTank().isEmpty()) {
            return;
        }

        var fluidStack = barrel.getTank().getFluid();
        double fluidAmount = fluidStack.getAmount();
        double height = FLUID_MIN_Y + Mth.lerp(fluidAmount / (double) CAPACITY, 0, FLUID_HEIGHT_RANGE);
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

        FluidRenderer.renderFluidFace(vertexConsumer, poseStack, new Vec3[]{
                new Vec3(14 / 16f, height, 14 / 16f),
                new Vec3(14 / 16f, height, 2 / 16f),
                new Vec3(2 / 16f, height, 2 / 16f),
                new Vec3(2 / 16f, height, 14 / 16f),
        }, color, light);
    }
}