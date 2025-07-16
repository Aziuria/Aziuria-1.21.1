package net.Aziuria.aziuriamod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.npc.Villager;

public class CustomProfessionLayer extends RenderLayer<Villager, VillagerModel<Villager>> {

    private final RenderLayer<Villager, VillagerModel<Villager>> vanillaProfessionLayer;
    private final WoodcutterVillagerRendererLayer woodcutterLayer;
    private final MinerVillagerRendererLayer minerLayer;

    public CustomProfessionLayer(RenderLayerParent<Villager, VillagerModel<Villager>> parent,
                                 RenderLayer<Villager, VillagerModel<Villager>> vanillaProfessionLayer) {
        super(parent);
        this.vanillaProfessionLayer = vanillaProfessionLayer;
        this.woodcutterLayer = new WoodcutterVillagerRendererLayer(parent);
        this.minerLayer = new MinerVillagerRendererLayer(parent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, Villager villager,
                       float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks,
                       float netHeadYaw, float headPitch) {

        String profession = villager.getVillagerData().getProfession().toString();

        if (profession.contains("woodcutter")) {
            woodcutterLayer.render(poseStack, bufferSource, packedLight, villager,
                    limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
        } else if (profession.contains("miner")) {
            minerLayer.render(poseStack, bufferSource, packedLight, villager,
                    limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
        } else {
            // Delegate rendering to vanilla profession layer for all other professions
            vanillaProfessionLayer.render(poseStack, bufferSource, packedLight, villager,
                    limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
        }
    }
}