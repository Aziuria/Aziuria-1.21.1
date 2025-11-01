package net.Aziuria.aziuriamod.item.renderer;

import com.mojang.blaze3d.vertex.VertexConsumer;
import org.jetbrains.annotations.NotNull;

public class VertexConsumerWrapper implements VertexConsumer {

    private final VertexConsumer base;
    private final float alphaMultiplier;

    public VertexConsumerWrapper(VertexConsumer base, float alphaMultiplier) {
        this.base = base;
        this.alphaMultiplier = alphaMultiplier;
    }

    @Override
    public @NotNull VertexConsumer addVertex(float x, float y, float z) {
        return base.addVertex(x, y, z);
    }

    @Override
    public @NotNull VertexConsumer setColor(int red, int green, int blue, int alpha) {
        return base.setColor(red, green, blue, (int) (alpha * alphaMultiplier));
    }

    @Override
    public @NotNull VertexConsumer setColor(float red, float green, float blue, float alpha) {
        return base.setColor(red, green, blue, alpha * alphaMultiplier);
    }

    @Override
    public @NotNull VertexConsumer setUv(float u, float v) {
        return base.setUv(u, v);
    }

    @Override
    public @NotNull VertexConsumer setUv1(int u, int v) {
        return base.setUv1(u, v);
    }

    @Override
    public @NotNull VertexConsumer setUv2(int u, int v) {
        return base.setUv2(u, v);
    }

    @Override
    public @NotNull VertexConsumer setNormal(float x, float y, float z) {
        return base.setNormal(x, y, z);
    }
}