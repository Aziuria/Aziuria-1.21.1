package net.Aziuria.aziuriamod.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.Aziuria.aziuriamod.block.entity.MinerBenchBlockEntity;
import net.Aziuria.aziuriamod.block.entity.WoodcutterBenchBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;

public class MinerBenchRenderer implements BlockEntityRenderer<MinerBenchBlockEntity> {

    private final BlockRenderDispatcher blockRenderer;

    public MinerBenchRenderer(BlockEntityRendererProvider.Context context) {
        this.blockRenderer = Minecraft.getInstance().getBlockRenderer();
    }

    @Override
    public void render(MinerBenchBlockEntity tileEntity, float partialTicks, PoseStack poseStack,
                       MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {
        BlockState state = tileEntity.getBlockState();
        blockRenderer.renderSingleBlock(state, poseStack, bufferSource, combinedLight, combinedOverlay);
    }
}