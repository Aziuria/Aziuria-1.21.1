package net.Aziuria.aziuriamod.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.textures.FluidSpriteCache;
import net.neoforged.neoforge.fluids.FluidStack;
import org.apache.commons.lang3.tuple.Pair;

public class FluidRenderer {

    public static Pair<TextureAtlasSprite, VertexConsumer> getFluidSpriteBuffer(Level level, BlockPos pos, FluidStack stack,
                                                                                MultiBufferSource bufferSource, RenderType renderType,
                                                                                FluidSpriteType type) {
        return getFluidSpriteBuffer(level, pos, stack, bufferSource, renderType, type, null);
    }

    public static Pair<TextureAtlasSprite, VertexConsumer> getFluidSpriteBuffer(Level level, BlockPos pos, FluidStack stack,
                                                                                MultiBufferSource bufferSource, RenderType renderType,
                                                                                FluidSpriteType type, FluidSpriteType backup) {
        FluidState state = stack.getFluid().defaultFluidState();
        VertexConsumer buffer = bufferSource.getBuffer(renderType);
        TextureAtlasSprite[] sprites = FluidSpriteCache.getFluidSprites(level, pos, state);
        TextureAtlasSprite sprite = sprites[type.typeIndex];
        if (sprite == null && backup != null) {
            sprite = sprites[backup.typeIndex];
        }

        return Pair.of(sprite, sprite != null ? sprite.wrap(buffer) : buffer);
    }

    public static int getFluidColor(Level level, BlockPos pos, FluidState state) {
        IClientFluidTypeExtensions attributes = IClientFluidTypeExtensions.of(state);
        return attributes.getTintColor(state, level, pos);
    }

    public static int getFluidColor(FluidStack stack) {
        IClientFluidTypeExtensions attributes = IClientFluidTypeExtensions.of(stack.getFluidType());
        return attributes.getTintColor(stack);
    }

    public static void renderFluidFace(VertexConsumer buffer, PoseStack poseStack, Vec3[] positions, int color, int light) {
        renderFluidFace(buffer, poseStack, positions, new int[]{color, color, color, color}, light);
    }

    public static void renderFluidFace(VertexConsumer buffer, PoseStack poseStack, Vec3[] positions, int[] colors, int light) {
        PoseStack.Pose pose = poseStack.last();
        Vec3 pos0 = positions[0];
        Vec3 pos1 = positions[1];
        Vec3 pos2 = positions[2];
        Vec3 pos3 = positions[3];
        buffer.addVertex(pose, (float) pos0.x(), (float) pos0.y(), (float) pos0.z()).setColor(colors[0]).setUv(0, 1).setLight(light).setNormal(pose, 0, 1, 0);
        buffer.addVertex(pose, (float) pos1.x(), (float) pos1.y(), (float) pos1.z()).setColor(colors[1]).setUv(1, 1).setLight(light).setNormal(pose, 0, 1, 0);
        buffer.addVertex(pose, (float) pos2.x(), (float) pos2.y(), (float) pos2.z()).setColor(colors[2]).setUv(1, 0).setLight(light).setNormal(pose, 0, 1, 0);
        buffer.addVertex(pose, (float) pos3.x(), (float) pos3.y(), (float) pos3.z()).setColor(colors[3]).setUv(0, 0).setLight(light).setNormal(pose, 0, 1, 0);
    }

    public enum FluidSpriteType {
        STILL(0), FLOWING(1), OVERLAY(2);

        public final int typeIndex;

        FluidSpriteType(int typeIndex) {
            this.typeIndex = typeIndex;
        }
    }
}