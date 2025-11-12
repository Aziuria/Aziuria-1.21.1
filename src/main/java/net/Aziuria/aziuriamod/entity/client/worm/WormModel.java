package net.Aziuria.aziuriamod.entity.client.worm;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.Aziuria.aziuriamod.AziuriaMod;
import net.Aziuria.aziuriamod.entity.custom.WormEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class WormModel<T extends WormEntity> extends HierarchicalModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(AziuriaMod.MOD_ID, "worm"), "main");

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart bodyUpper;
    private final ModelPart body;
    private final ModelPart tail;

    public WormModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("Head");
        this.bodyUpper = root.getChild("BodyUpper");
        this.body = root.getChild("Body");
        this.tail = root.getChild("Tail");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition root = meshdefinition.getRoot();

        root.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(9, 8)
                .addBox(-1.0F, 0.5F, -3.0F, 1.0F, 1.0F, 2.0F), PartPose.offset(0.0F, 22.5F, -2.0F));

        root.addOrReplaceChild("BodyUpper", CubeListBuilder.create().texOffs(0, 0)
                .addBox(-1.0F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F), PartPose.offset(0.0F, 24.0F, 0.0F));

        root.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(1, 1)
                .addBox(-1.0F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F), PartPose.offset(0.0F, 23.5F, 1.0F));

        root.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(1, 8)
                .addBox(-1.0F, 0.5F, -0.5F, 1.0F, 1.0F, 3.0F), PartPose.offset(0.0F, 22.5F, 2.5F));

        return LayerDefinition.create(meshdefinition, 32, 32);

    }

    @Override
    public void setupAnim(WormEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
                          float netHeadYaw, float headPitch) {

        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(WormAnimations.WORM_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(entity.idleAnimationState, WormAnimations.WORM_IDLE, ageInTicks, 1f);
    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 45f);

        this.head.yRot = (headYaw * 0.5f) * ((float)Math.PI / 180f);
        this.head.xRot = (headPitch * 0.5f) * ((float)Math.PI / 180f);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer,
                               int packedLight, int packedOverlay, int color) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return root;
    }
}