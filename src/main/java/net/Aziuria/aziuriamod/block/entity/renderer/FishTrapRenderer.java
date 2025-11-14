package net.Aziuria.aziuriamod.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.Aziuria.aziuriamod.block.entity.FishTrapBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class FishTrapRenderer implements BlockEntityRenderer<FishTrapBlockEntity> {

    private final ItemRenderer itemRenderer;

    public FishTrapRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = Minecraft.getInstance().getItemRenderer();
    }

    @Override
    public void render(FishTrapBlockEntity blockEntity, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {

        if (blockEntity.getLevel() == null) return;

        Direction facing = blockEntity.getBlockState().getValue(net.minecraft.world.level.block.HorizontalDirectionalBlock.FACING);

        poseStack.pushPose();

        // Loop through 4 slots
        for (int i = 0; i < 4; i++) {
            ItemStack itemStack = blockEntity.getItems().get(i);
            if (itemStack.isEmpty()) continue;

            poseStack.pushPose();

            // Base position in block center
            poseStack.translate(0.5f, 0.25f, 0.5f);

            // Offset X/Z for 4 slots
            float offset = 0.2f;
            float xOffset = (i % 2 == 0 ? -offset : offset);
            float zOffset = (i < 2 ? -offset : offset);

            // Pull items back toward north by ~4 pixels (0.25 / 16 ≈ 0.25 block)
            zOffset -= 0.15f;  // adjust south/north alignment

            poseStack.translate(xOffset, 0f, zOffset);

            // Rotate according to block facing
            float rotation = switch (facing) {
                case NORTH -> 0f;
                case SOUTH -> 180f;
                case WEST  -> 90f;
                case EAST  -> -90f;
                default -> 0f;
            };
            poseStack.mulPose(Axis.YP.rotationDegrees(rotation));
            poseStack.mulPose(Axis.XP.rotationDegrees(90f)); // lay flat

            // Adjust height slightly
            poseStack.translate(0f, -0.15f, 0f);

            // Scale for visual clarity
            poseStack.scale(0.3f, 0.3f, 0.3f);

            // Render the item
            itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, combinedLight, combinedOverlay,
                    poseStack, bufferSource, blockEntity.getLevel(), 0);

            poseStack.popPose();
        }

        poseStack.popPose();
    }
}