package net.Aziuria.aziuriamod.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.Aziuria.aziuriamod.block.entity.custom.ShelfBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class ShelfRenderer implements BlockEntityRenderer<ShelfBlockEntity> {

    private final ItemRenderer itemRenderer;

    public ShelfRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = Minecraft.getInstance().getItemRenderer();
    }

    @Override
    public void render(ShelfBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {
        BlockState blockState = blockEntity.getBlockState();
        Direction facing = blockState.getValue(net.minecraft.world.level.block.HorizontalDirectionalBlock.FACING);

        poseStack.pushPose();

        for (int i = 0; i < 4; i++) {
            ItemStack itemStack = blockEntity.getItems().get(i);
            if (!itemStack.isEmpty()) {
                poseStack.pushPose();

                // Center in the middle of the block
                poseStack.translate(0.5f, 0.2f, 0.5f);

                // Determine Y position: top or bottom shelf
                float yOffset = (i < 2) ? 0.5f : 0.1f;

                // Define base spacing offsets (based on SOUTH-facing as base)
                float baseX = 0.25f;
                float baseZ = (i == 0 || i == 2) ? -0.25f : 0.25f;

                float xOffset = 0f;
                float zOffset = 0f;

                switch (facing) {
                    case NORTH -> {
                        xOffset = baseZ;
                        zOffset = -baseX;
                        poseStack.translate(0f, 0f, 0.5f);
                    }
                    case SOUTH -> {
                        xOffset = -baseZ;
                        zOffset = baseX;
                        poseStack.translate(0f, 0f, -0.5f);
                    }
                    case EAST -> {
                        xOffset = baseX;
                        zOffset = baseZ;
                        poseStack.translate(-0.5f, 0f, 0f);
                    }
                    case WEST -> {
                        xOffset = -baseX;
                        zOffset = -baseZ;
                        poseStack.translate(0.5f, 0f, 0f);
                    }
                }

                poseStack.translate(xOffset, yOffset, zOffset);

                float blockRotation = switch (facing) {
                    case EAST -> 0f;
                    case WEST -> 180f;
                    case NORTH -> 90f;
                    case SOUTH -> -90f;
                    default -> 0f;
                };
                poseStack.mulPose(Axis.YP.rotationDegrees(blockRotation));

                poseStack.mulPose(Axis.YP.rotationDegrees(90f));

                float forwardOffset = 0.00f;

                switch (facing) {
                    case NORTH -> poseStack.translate(0f, 0f, -forwardOffset);
                    case SOUTH -> poseStack.translate(0f, 0f, forwardOffset);
                    case EAST -> poseStack.translate(forwardOffset, 0f, 0f);
                    case WEST -> poseStack.translate(-forwardOffset, 0f, 0f);
                }

                poseStack.scale(0.4f, 0.4f, 0.4f);

                itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, combinedLight, combinedOverlay, poseStack, bufferSource, blockEntity.getLevel(), 0);

                poseStack.popPose();
            }
        }

        poseStack.popPose();
    }
}