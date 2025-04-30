package net.Aziuria.aziuriamod.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.Aziuria.aziuriamod.block.entity.ShelfBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
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

        // Debugging output to verify if items are being read
        System.out.println("Inventory size: " + blockEntity.getItems().size());
        for (int i = 0; i < blockEntity.getItems().size(); i++) {
            ItemStack stack = blockEntity.getItems().get(i);
            System.out.println("Slot " + i + ": " + stack);
        }



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
                float baseX = 0.25f; // Side spacing
                float baseZ = (i == 0 || i == 2) ? -0.25f : 0.25f; // Front/back spacing

                float xOffset = 0f;
                float zOffset = 0f;

                // Corrected offsets for each facing direction:
                switch (facing) {
                    case NORTH -> {
                        // Fix offsets for NORTH: Swap xOffset and zOffset.
                        xOffset = baseZ; // Positive X offset should be used for right
                        zOffset = -baseX; // Negative Z offset for left
                        poseStack.translate(0f, 0f, 0.5f);                     }
                    case SOUTH -> {
                        // Fix offsets for SOUTH: Swap xOffset and zOffset.
                        xOffset = -baseZ; // Negative X offset for left
                        zOffset = baseX;  // Positive Z offset for right
                        poseStack.translate(0f, 0f, -0.5f);                    }
                    case EAST -> {
                        // Fix offsets for EAST: Swap xOffset and zOffset.
                        xOffset = baseX; // Positive X offset for right
                        zOffset = baseZ; // Positive Z offset for right
                        poseStack.translate(-0.5f, 0f, 0f);  // Move back by half a block on X axis (opposite direction)
                    }
                    case WEST -> {
                        // Fix offsets for WEST: Swap xOffset and zOffset.
                        xOffset = -baseX; // Negative X offset for left
                        zOffset = -baseZ; // Negative Z offset for left
                        poseStack.translate(0.5f, 0f, 0f); // Move back by half a block on X axis (opposite direction)
                    }
                }

                // Apply final translation
                poseStack.translate(xOffset, yOffset, zOffset);

                // Rotate the items based on facing direction
                float blockRotation = switch (facing) {
                    case EAST -> 0f; // Rotate by 180 degrees for East-facing items
                    case WEST -> 180f; // Rotate by 180 degrees for West-facing items
                    case NORTH -> 90f;
                    case SOUTH -> -90f;
                    default -> 0f;
                };
                poseStack.mulPose(Axis.YP.rotationDegrees(blockRotation));

                // Rotate item to show side view
                poseStack.mulPose(Axis.YP.rotationDegrees(90f));

                // Move item forward a bit (on Z axis for North/South, on X axis for East/West)
                float forwardOffset = 0.00f;  // Adjust this value for how far forward you want the items

                // Adjust for North/South direction:
                switch (facing) {
                    case NORTH -> poseStack.translate(0f, 0f, -forwardOffset);  // Move forward if facing north
                    case SOUTH -> poseStack.translate(0f, 0f, forwardOffset);   // Move forward if facing south
                    case EAST  -> poseStack.translate(forwardOffset, 0f, 0f);   // Move forward on X axis for East
                    case WEST  -> poseStack.translate(-forwardOffset, 0f, 0f);  // Move forward on X axis for West
                }

                // Scale down the item
                poseStack.scale(0.4f, 0.4f, 0.4f);

                itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, combinedLight, combinedOverlay, poseStack, bufferSource, blockEntity.getLevel(), 0);

                poseStack.popPose();
            }
        }

        poseStack.popPose();
    }
}