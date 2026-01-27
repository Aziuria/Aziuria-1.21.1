package net.Aziuria.aziuriamod.tips;

import net.Aziuria.aziuriamod.block.ModBlocks;
import net.Aziuria.aziuriamod.tips.message.PebbleMessageToast;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class ModTips {

    public static void showPebbleMiningTip() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null) return;

        mc.getToasts().addToast(
                new PebbleMessageToast(
                        Component.literal(
                                "Find pebbles and break or craft them into stone shards to mine wood."
                        ),
                        new ItemStack(ModBlocks.PEBBLE_BLOCK.get())
                )
        );
    }

    public static void showPebbleCombatTip() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null) return;

        mc.getToasts().addToast(
                new PebbleMessageToast(
                        Component.literal(
                                "Pebbles can be used as projectiles to throw them at mobs."
                        ),
                        new ItemStack(ModBlocks.PEBBLE_BLOCK.get())
                )
        );
    }
}