package net.Aziuria.aziuriamod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.Aziuria.aziuriamod.block.entity.ShelfBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;

public class ShelfRenderer implements BlockEntityRenderer<ShelfBlockEntity> {

    private final ItemRenderer itemRenderer;

    // Constructor
    public ShelfRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = Minecraft.getInstance().getItemRenderer(); // Get the ItemRenderer instance
    }

    @Override
    public void render(ShelfBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {
        poseStack.pushPose(); // Save the current pose (transformations)

        // Render each item in the shelf
        for (int i = 0; i < 6; i++) {
            ItemStack itemStack = blockEntity.getItem(i);
            if (!itemStack.isEmpty()) { // Check if the item slot isn't empty
                poseStack.pushPose(); // Save the current pose for this item

                // Positioning: adjust X and Z based on slot
                float xOffset = (i % 3) * 0.3f + 0.1f; // 3 columns
                float zOffset = (i / 3) * 0.4f + 0.1f; // 2 rows

                poseStack.translate(xOffset, 0.2f, zOffset); // Apply the translation (positioning)
                poseStack.scale(0.4f, 0.4f, 0.4f); // Scale down the item to fit on the shelf

                // Render the item using the renderStatic method
// Render the item using the correct display context (e.g., GROUND or FIXED)
                if (blockEntity.getLevel() != null) {
                    itemRenderer.renderStatic(
                            itemStack,
                            ItemDisplayContext.FIXED,
                            combinedLight,
                            combinedOverlay,
                            poseStack,
                            bufferSource,
                            blockEntity.getLevel(),
                            0
                    );
                }
                poseStack.popPose();                    // Revert the pose after rendering the item
            }
        }

        // Render the shelf model (the block itself)
        BlockState blockState = blockEntity.getBlockState(); // Get the block state for the shelf
        Block block = blockState.getBlock(); // Get the actual block

        // Render the block model of the shelf (the shelf itself)
        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(blockState, poseStack, bufferSource, combinedLight, combinedOverlay);

        poseStack.popPose(); // Final pop of the pose stack (finish rendering the shelf block)
    }

    @Override
    public int getViewDistance() {
        return 100; // Define the maximum view distance for rendering the block entity
    }
}