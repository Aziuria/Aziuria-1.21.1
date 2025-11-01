package net.Aziuria.aziuriamod.item.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class SpectralDustRenderer extends BlockEntityWithoutLevelRenderer {

    private static final float ALPHA = 0.7f; // 0 = fully invisible, 1 = fully opaque

    public SpectralDustRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet modelSet) {
        super(dispatcher, modelSet);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void renderByItem(ItemStack stack, ItemDisplayContext context, PoseStack poseStack,
                             MultiBufferSource buffer, int light, int overlay) {

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        poseStack.pushPose();

        var model = itemRenderer.getModel(stack, null, null, 0);

        // Use a custom translucent RenderType with alpha
        VertexConsumer translucentBuffer = buffer.getBuffer(RenderType.entityTranslucentCull(model.getParticleIcon().atlasLocation()));

        // Wrap the vertex consumer to apply partial alpha
        VertexConsumer alphaWrapper = new VertexConsumerWrapper(translucentBuffer, ALPHA);

        // Render model lists with alpha applied
        itemRenderer.renderModelLists(model, stack, light, OverlayTexture.NO_OVERLAY, poseStack, alphaWrapper);

        poseStack.popPose();
    }
}