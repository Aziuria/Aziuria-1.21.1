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

        // Debugging output to verify if items are being read
        System.out.println("Inventory size: " + blockEntity.getItems().size());
        for (int i = 0; i < blockEntity.getItems().size(); i++) {
            ItemStack stack = blockEntity.getItems().get(i);
            System.out.println("Slot " + i + ": " + stack);
        }



        poseStack.pushPose();

        for (int i = 0; i < 2; i++) {  // Looping for 2 slots instead of 4
            ItemStack itemStack = blockEntity.getItems().get(i);
            if (!itemStack.isEmpty()) {
                poseStack.pushPose();

                // Center in the middle of the block
                poseStack.translate(0.5f, 0.25f, 0.5f);  // Set items 4 pixels above the ground (0.25f Y offset)

                // Define Y position: top or bottom shelf (i.e., top slot = 0.25f, bottom slot = 0.65f)
                float yOffset = (i == 0) ? 0.25f : 0.65f;

                // Define the base X and Z offsets (based on the SOUTH-facing default)
                float baseX = 0.25f;
                float baseZ = (i == 0) ? -0.25f : 0.25f;

                // Correct offsets for each facing direction
                float xOffset = 0f;
                float zOffset = 0f;

                switch (facing) {
                    case NORTH -> {
                        xOffset = baseZ;  // Positive X offset for right
                        zOffset = -baseX;  // Negative Z offset for left
                    }
                    case SOUTH -> {
                        xOffset = -baseZ;  // Negative X offset for left
                        zOffset = baseX;  // Positive Z offset for right
                    }
                    case EAST -> {
                        xOffset = baseX;  // Positive X offset for right
                        zOffset = baseZ;  // Positive Z offset for right
                    }
                    case WEST -> {
                        xOffset = -baseX;  // Negative X offset for left
                        zOffset = -baseZ;  // Negative Z offset for left
                    }
                }

                // Apply final translation
                poseStack.translate(xOffset, yOffset, zOffset);

                // Rotate the items based on facing direction
                float blockRotation = switch (facing) {
                    case EAST -> 180f;
                    case WEST -> 0f;
                    case NORTH -> 90f;
                    case SOUTH -> -90f;
                    default -> 0f;
                };
                poseStack.mulPose(Axis.YP.rotationDegrees(blockRotation));

                // Rotate item to show side view (90 degrees)
                poseStack.mulPose(Axis.XP.rotationDegrees(90f));

                // Move item forward slightly (this can be adjusted to match the intended positioning)
                float forwardOffset = 0.00f;  // Adjust this value for how far forward you want the items

                // Adjust for North/South direction:
                switch (facing) {
                    case NORTH -> poseStack.translate(0f, 0f, -forwardOffset);  // Move forward if facing north
                    case SOUTH -> poseStack.translate(0f, 0f, forwardOffset);   // Move forward if facing south
                    case EAST -> poseStack.translate(forwardOffset, 0f, 0f);   // Move forward on X axis for East
                    case WEST -> poseStack.translate(-forwardOffset, 0f, 0f);  // Move forward on X axis for West
                }

                // Scale down the item for better fit
                poseStack.scale(0.4f, 0.4f, 0.4f);

                // Render the item
                itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, combinedLight, combinedOverlay, poseStack, bufferSource, blockEntity.getLevel(), 0);

                poseStack.popPose();
            }
        }

        poseStack.popPose();
    }
}