package net.Aziuria.aziuriamod.entity.client.worm;

import com.mojang.blaze3d.vertex.PoseStack;
import net.Aziuria.aziuriamod.AziuriaMod;
import net.Aziuria.aziuriamod.entity.custom.WormEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class WormRenderer extends MobRenderer<WormEntity, WormModel<WormEntity>> {
    public WormRenderer(EntityRendererProvider.Context context) {
        super(context, new WormModel<>(context.bakeLayer(WormModel.LAYER_LOCATION)), 0.01f);
    }

    @Override
    public ResourceLocation getTextureLocation(WormEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, "textures/entity/worm/worm.png");
    }

    @Override
    public void render(WormEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.45f, 0.45f, 0.45f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}