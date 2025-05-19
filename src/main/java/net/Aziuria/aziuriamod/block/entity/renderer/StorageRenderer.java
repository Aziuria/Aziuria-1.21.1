package net.Aziuria.aziuriamod.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.Aziuria.aziuriamod.block.entity.StorageBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class StorageRenderer implements BlockEntityRenderer<StorageBlockEntity> {

    private final ItemRenderer itemRenderer;

    public StorageRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = Minecraft.getInstance().getItemRenderer();
    }

    @Override
    public void render(StorageBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {
        BlockState blockState = blockEntity.getBlockState();
        Direction facing = blockState.getValue(net.minecraft.world.level.block.HorizontalDirectionalBlock.FACING);

        poseStack.pushPose();

        for (int i = 0; i < 2; i++) {
            ItemStack itemStack = blockEntity.getItems().get(i);
            if (!itemStack.isEmpty()) {
                poseStack.pushPose();

                poseStack.translate(0.5f, 0.25f, 0.5f); // Center above ground

                float offset = 0.2f;
                float xOffset = 0f;
                float zOffset = 0f;

                switch (facing) {
                    case NORTH -> xOffset = (i == 0 ? -offset : offset);
                    case SOUTH -> xOffset = (i == 0 ? offset : -offset);
                    case EAST  -> zOffset = (i == 0 ? -offset : offset);
                    case WEST  -> zOffset = (i == 0 ? offset : -offset);
                }

                poseStack.translate(xOffset, 0f, zOffset);

                float rotation = switch (facing) {
                    case NORTH -> 0f;
                    case SOUTH -> 180f;
                    case WEST  -> 90f;
                    case EAST  -> -90f;
                    default -> 0f;
                };
                poseStack.mulPose(Axis.YP.rotationDegrees(rotation));
                poseStack.mulPose(Axis.XP.rotationDegrees(90f));

                poseStack.translate(0f, -0.15f, 0f);

                poseStack.scale(0.4f, 0.4f, 0.4f);
                itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, combinedLight, combinedOverlay, poseStack, bufferSource, blockEntity.getLevel(), 0);

                poseStack.popPose();
            }
        }

        poseStack.popPose();
    }
}