package net.Aziuria.aziuriamod.util;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;

public class RenderLayerUtil {

    @SuppressWarnings({ "deprecation", "removal" })
    public static void setRenderLayer(Block block, RenderType renderType) {
        ItemBlockRenderTypes.setRenderLayer(block, renderType);
    }
}
