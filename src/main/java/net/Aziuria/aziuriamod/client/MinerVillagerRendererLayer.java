package net.Aziuria.aziuriamod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.Villager;

public class MinerVillagerRendererLayer extends RenderLayer<Villager, VillagerModel<Villager>> {

    private static final ResourceLocation MINER_TEXTURE = ResourceLocation.fromNamespaceAndPath("aziuriamod", "textures/entity/villager/profession/miner.png");

    public MinerVillagerRendererLayer(RenderLayerParent<Villager, VillagerModel<Villager>> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, Villager villager,
                       float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {

        String profString = villager.getVillagerData().getProfession().toString();
        if (profString.contains("miner")) {
            VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutout(MINER_TEXTURE));
            this.getParentModel().renderToBuffer(
                    poseStack,
                    vertexConsumer,
                    packedLight,
                    OverlayTexture.NO_OVERLAY,
                    -1
            );
        }
    }
}