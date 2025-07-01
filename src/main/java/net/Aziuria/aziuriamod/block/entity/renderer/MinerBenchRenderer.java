package net.Aziuria.aziuriamod.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.Aziuria.aziuriamod.block.MinerBenchBlock;
import net.Aziuria.aziuriamod.block.MinerPart;
import net.Aziuria.aziuriamod.block.entity.MinerBenchBlockEntity;
import net.Aziuria.aziuriamod.block.entity.WoodcutterBenchBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
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
        Direction facing = state.getValue(MinerBenchBlock.FACING);
        MinerPart part = state.getValue(MinerBenchBlock.PART);

        double offset = 0.001;

        switch (facing) {
            case NORTH, SOUTH -> {
                // For north/south facing, offset X for sides and Z for front/back
                double xOffset = (part == MinerPart.LEFT) ? -offset : offset;
                double zOffset = (part == MinerPart.LEFT) ? offset : -offset;
                poseStack.translate(xOffset, 0, zOffset);
            }
            case EAST, WEST -> {
                // For east/west facing, offset Z for sides and X for front/back
                double zOffset = (part == MinerPart.LEFT) ? -offset : offset;
                double xOffset = (part == MinerPart.LEFT) ? offset : -offset;
                poseStack.translate(xOffset, 0, zOffset);
            }
            default -> {}
        }

        blockRenderer.renderSingleBlock(state, poseStack, bufferSource, combinedLight, combinedOverlay);
    }
}