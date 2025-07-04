package net.Aziuria.aziuriamod.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.Aziuria.aziuriamod.block.WoodcutterBenchBlock;
import net.Aziuria.aziuriamod.block.WoodcutterPart;
import net.Aziuria.aziuriamod.block.entity.WoodcutterBenchBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class WoodcutterBenchRenderer implements BlockEntityRenderer<WoodcutterBenchBlockEntity> {

    private final BlockRenderDispatcher blockRenderer;

    public WoodcutterBenchRenderer(BlockEntityRendererProvider.Context context) {
        this.blockRenderer = Minecraft.getInstance().getBlockRenderer();
    }

    @Override
    public void render(WoodcutterBenchBlockEntity tileEntity, float partialTicks, PoseStack poseStack,
                       MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {
        BlockState state = tileEntity.getBlockState();
        Direction facing = state.getValue(WoodcutterBenchBlock.FACING);
        WoodcutterPart part = state.getValue(WoodcutterBenchBlock.PART);

        double offset = 0.001;

        // ⬇️ Added ONLY this line for Y-offset for top-down anti-flicker
        double yOffset = (part == WoodcutterPart.LEFT) ? -offset : offset; // ⬅️ changed

        switch (facing) {
            case NORTH, SOUTH -> {
                double xOffset = (part == WoodcutterPart.LEFT) ? -offset : offset;
                double zOffset = (part == WoodcutterPart.LEFT) ? offset : -offset;
                poseStack.translate(xOffset, yOffset, zOffset); // ⬅️ changed
            }
            case EAST, WEST -> {
                double zOffset = (part == WoodcutterPart.LEFT) ? -offset : offset;
                double xOffset = (part == WoodcutterPart.LEFT) ? offset : -offset;
                poseStack.translate(xOffset, yOffset, zOffset); // ⬅️ changed
            }
            default -> {
                poseStack.translate(0, yOffset, 0); // ⬅️ changed, covers UP/DOWN fallback safely
            }
        }

        blockRenderer.renderSingleBlock(state, poseStack, bufferSource, combinedLight, combinedOverlay);
    }
}