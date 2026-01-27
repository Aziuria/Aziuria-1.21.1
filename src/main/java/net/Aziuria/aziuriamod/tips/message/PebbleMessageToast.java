package net.Aziuria.aziuriamod.tips.message;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class PebbleMessageToast implements Toast {

    // ✅ MODERN sprite-based toast background (same system as TutorialToast)
    private static final ResourceLocation BACKGROUND_SPRITE =
            ResourceLocation.withDefaultNamespace("toast/tutorial");

    private final Component message;
    private final ItemStack icon;
    private long firstRendered = -1L;

    public PebbleMessageToast(Component message, ItemStack icon) {
        this.message = message;
        this.icon = icon;
    }

    @Override
    public Visibility render(GuiGraphics guiGraphics, ToastComponent toastComponent, long time) {
        Minecraft mc = toastComponent.getMinecraft();

        if (firstRendered == -1L) {
            firstRendered = time;
        }

        // === Draw vanilla toast background ===
        guiGraphics.blitSprite(
                BACKGROUND_SPRITE,
                0,
                0,
                this.width(),
                this.height()
        );

        // === Draw item icon (vanilla style) ===
        guiGraphics.renderFakeItem(icon, 8, 8);

        // === Proper vanilla-style text wrapping ===
        List<FormattedCharSequence> lines = mc.font.split(message, 125);

        if (lines.size() == 1) {
            guiGraphics.drawString(
                    mc.font,
                    lines.get(0),
                    30,
                    12 - 4, // shifted up 3 pixels
                    0xFF000000,
                    false
            );
        } else {
            int y = 7 - 4; // shift all lines up by 3 pixels
            for (FormattedCharSequence line : lines) {
                guiGraphics.drawString(
                        mc.font,
                        line,
                        30,
                        y,
                        0xFF000000,
                        false
                );
                y += 9;
            }
        }

        // === Auto-hide after 5 seconds ===
        return (time - firstRendered) >= 8000L
                ? Visibility.HIDE
                : Visibility.SHOW;
    }
}