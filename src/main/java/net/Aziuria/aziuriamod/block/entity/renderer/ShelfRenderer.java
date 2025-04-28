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

        // Loop through the inventory slots (2 for top, 2 for bottom)
        for (int i = 0; i < 4; i++) {
            ItemStack itemStack = blockEntity.getItems().get(i);
            if (!itemStack.isEmpty()) {
                poseStack.pushPose();


// Center the entire shelf space (keep this translation if needed)
                poseStack.translate(0.25f, 0.2f, 0.25f);

// Calculate X and Z offsets for the items
                float xOffset = 0.0f;  // Default value for left
                float zOffset = 0.0f;  // Default value for front

// Define a shift for all items to move to the right
                float shiftRight = 0.25f;  // Increase this value to shift further to the right

// Set the Y offset for top and bottom
                float yOffset;
                if (i < 2) {  // For top section (items 0 and 1)
                    yOffset = 0.5f;  // Top of the shelf (near 16 height)
                    // Adjust Z offset for left (-0.25f) and right (+0.25f) sides in the top section
                    zOffset = (i == 0) ? -0.25f : 0.25f;  // Slightly smaller offset to bring items closer to the center
                } else {  // For bottom section (items 2 and 3)
                    yOffset = 0.1f;  // Bottom of the shelf (near 0 height)
                    // Adjust Z offset for left (-0.25f) and right (+0.25f) sides in the bottom section
                    zOffset = (i == 2) ? -0.25f : 0.25f;  // Slightly smaller offset to bring items closer to the center
                }

// Apply the right shift to Z offset (to move all items to the right)
                zOffset += shiftRight;  // Shift everything to the right

// Adjust X offset for the left and right sides
                if (i == 0 || i == 2) {  // Left-hand side items
                    xOffset = 0.25f;  // Fine-tune left-hand items (adjusting by 4 pixels)
                } else {  // Right-hand side items
                    xOffset = 0.25f;  // Keep right-hand items at this offset
                }



// Apply the translation to the pose stack
                poseStack.translate(xOffset, yOffset, zOffset);

                // Rotate the items based on facing direction
                float rotation = switch (facing) {
                    case NORTH -> 180f;
                    case EAST  -> -90f;
                    case SOUTH -> 0f;
                    case WEST  -> 90f;
                    default    -> 0f;
                };
                poseStack.mulPose(Axis.YP.rotationDegrees(rotation));

                poseStack.scale(0.4f, 0.4f, 0.4f);

                itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, combinedLight, combinedOverlay, poseStack, bufferSource, blockEntity.getLevel(), 0);

                poseStack.popPose();
            }
        }

        poseStack.popPose();
    }
}