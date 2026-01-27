package net.Aziuria.aziuriamod.handler.custom;

import net.Aziuria.aziuriamod.item.custom.tools.StoneShardItem;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WoodMiningRestrictionHandler {

    // 🔁 GLOBAL DEFAULT (change this)
    // false = OFF by default
    // true  = ON by default
    private static final boolean DEFAULT_ENABLED = true;

    // Per-player overrides
    private static final Map<UUID, Boolean> playerOverrides = new HashMap<>();

    // ---- TOGGLE API ----
    public static void setEnabled(Player player, boolean enabled) {
        playerOverrides.put(player.getUUID(), enabled);
    }

    public static boolean isEnabled(Player player) {
        return playerOverrides.getOrDefault(player.getUUID(), DEFAULT_ENABLED);
    }

    // Optional: clear override (revert to default)
    public static void clearOverride(Player player) {
        playerOverrides.remove(player.getUUID());
    }

    // ---- EVENT ----
    @SubscribeEvent
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        if (player == null) return;

        // ⛔ Feature disabled
        if (!isEnabled(player)) return;

        // Only logs
        if (!event.getState().is(BlockTags.LOGS)) return;

        ItemStack held = player.getMainHandItem();

        // Allow axes
        if (held.getItem() instanceof AxeItem) return;

        // Allow stone shard
        if (held.getItem() instanceof StoneShardItem) return;

        // 🚫 Bedrock-style denial (no cracks, no break)
        event.setNewSpeed(0.0f);
    }
}