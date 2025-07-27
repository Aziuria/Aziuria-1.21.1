package net.Aziuria.aziuriamod.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.Aziuria.aziuriamod.block.entity.HookBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.state.BlockState;

public class HookRenderer implements BlockEntityRenderer<HookBlockEntity> {

    private final ItemRenderer itemRenderer;

    public HookRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = Minecraft.getInstance().getItemRenderer();
    }

    @Override
    public void render(HookBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {
        BlockState blockState = blockEntity.getBlockState();
        Direction facing = blockState.getValue(net.minecraft.world.level.block.HorizontalDirectionalBlock.FACING);

        ItemStack itemStack = blockEntity.getItems().get(0);
        if (itemStack.isEmpty()) return;

        poseStack.pushPose();

        // Center X, set Y = 0.75 (12 pixels height)
        poseStack.translate(0.5, 0.75, 0.5);


// Move inward and slightly adjust per tool type
        if (itemStack.getItem() instanceof SwordItem) {
            switch (facing) {
                case NORTH -> poseStack.translate(-0.03125, 0, 0.3);
                case SOUTH -> poseStack.translate(0.03125, 0, -0.3);
                case EAST  -> poseStack.translate(-0.3, 0, -0.03125);
                case WEST  -> poseStack.translate(0.3, 0, 0.03125);
            }
        } else if (itemStack.getItem() instanceof PickaxeItem) {
            switch (facing) {
                case NORTH -> poseStack.translate(-0.07875, 0, 0.3);
                case SOUTH -> poseStack.translate(0.07875, 0, -0.3);
                case EAST  -> poseStack.translate(-0.3, 0, -0.0);
                case WEST  -> poseStack.translate(0.3, 0, 0.0);
            }
        } else if (itemStack.getItem() instanceof HoeItem) {
            switch (facing) {
                case NORTH -> poseStack.translate(-0.07875, 0, 0.3);
                case SOUTH -> poseStack.translate(0.07875, 0, -0.3);
                case EAST  -> poseStack.translate(-0.3, 0, -0.0);
                case WEST  -> poseStack.translate(0.3, 0, 0.0);
            }
        } else if (itemStack.getItem() instanceof ShovelItem) {
            switch (facing) {
                case NORTH -> poseStack.translate(-0.07875, 0, 0.3);
                case SOUTH -> poseStack.translate(0.07875, 0, -0.3);
                case EAST  -> poseStack.translate(-0.3, 0, -0.0);
                case WEST  -> poseStack.translate(0.3, 0, 0.0);
            }
        } else if (itemStack.getItem() instanceof AxeItem) {
            switch (facing) {
                case NORTH -> poseStack.translate(-0.07875, 0, 0.3);
                case SOUTH -> poseStack.translate(0.07875, 0, -0.3);
                case EAST  -> poseStack.translate(-0.3, 0, -0.0);
                case WEST  -> poseStack.translate(0.3, 0, 0.0);
            }

        }

        // Rotate to face forward
        float rotation = switch (facing) {
            case NORTH -> 180f;
            case SOUTH -> 0f;
            case EAST -> -90f;
            case WEST -> 90f;
            default -> 0f;
        };
        poseStack.mulPose(Axis.YP.rotationDegrees(rotation));


        // Now apply custom rotation based on tool type
        if (itemStack.getItem() instanceof SwordItem) {
            poseStack.translate(0, -0.125, 0);
            poseStack.mulPose(Axis.ZP.rotationDegrees(135f));
        } else if (itemStack.getItem() instanceof PickaxeItem) {
            poseStack.translate(0, -0.125, 0);
            poseStack.mulPose(Axis.ZP.rotationDegrees(-45f));
        } else if (itemStack.getItem() instanceof HoeItem) {
            poseStack.translate(0, -0.125, 0);
            poseStack.mulPose(Axis.ZP.rotationDegrees(-45f));
        } else if (itemStack.getItem() instanceof ShovelItem) {
            poseStack.translate(0, -0.125, 0);
            poseStack.mulPose(Axis.ZP.rotationDegrees(-45f));
        } else if (itemStack.getItem() instanceof AxeItem) {
            poseStack.translate(0, -0.080, 0);
            poseStack.mulPose(Axis.ZP.rotationDegrees(-45f));
        }


        // Scale down for hook appearance
        poseStack.scale(0.9f, 0.9f, 0.9f);

        itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, combinedLight, combinedOverlay, poseStack, bufferSource, blockEntity.getLevel(), 0);

        poseStack.popPose();
    }
}