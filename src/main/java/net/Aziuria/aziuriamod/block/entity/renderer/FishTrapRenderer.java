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
    public void render(FishTrapBlockEntity trap, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {

        ItemStack stack = trap.getBaitSlot();
        if (stack.isEmpty()) return;

        Direction facing = trap.getBlockState().getValue(net.minecraft.world.level.block.HorizontalDirectionalBlock.FACING);

        poseStack.pushPose();
        poseStack.translate(0.5f, 0.25f, 0.5f);

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
        poseStack.translate(0f, -0.15f, 0f);
        poseStack.scale(0.3f, 0.3f, 0.3f);

        itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED, combinedLight, combinedOverlay,
                poseStack, bufferSource, trap.getLevel(), 0);

        poseStack.popPose();
    }
}