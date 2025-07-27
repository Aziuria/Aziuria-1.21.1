package net.Aziuria.aziuriamod.water;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class WaterFoodHandler {

    private static final Map<UUID, ItemStack> playersEating = new ConcurrentHashMap<>();

    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        ItemStack item = player.getItemInHand(event.getHand());

        if (isFoodOrDrink(item)) {
            playersEating.put(player.getUUID(), item.copy());
        }
    }

    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event) {
        if (!(event.getLevel() instanceof ServerLevel serverLevel)) return;

        for (ServerPlayer player : serverLevel.getServer().getPlayerList().getPlayers()) {
            UUID playerId = player.getUUID();

            if (playersEating.containsKey(playerId)) {
                if (!player.isUsingItem() || player.getUseItemRemainingTicks() <= 0) {
                    ItemStack eaten = playersEating.remove(playerId);
                    if (eaten != null) {
                        handleHydration(player, eaten);
                    }
                }
            }
        }
    }

    private static boolean isFoodOrDrink(ItemStack stack) {
        return stack.getItem().getFoodProperties(stack, null) != null;
    }

    private static void handleHydration(Player player, ItemStack eaten) {
        PlayerWaterCapability cap = player.getCapability(ModCapabilities.WATER_CAP);
        if (cap == null) return;

        int hydration = HydrationRegistry.getHydration(eaten.getItem());
        if (hydration > 0) {
            cap.addWater(hydration);

            if (player instanceof ServerPlayer serverPlayer) {
                WaterStateSyncPacket packet = new WaterStateSyncPacket(cap.getWaterLevel());
                WaterNetworkHandler.sendWaterStateToClient(serverPlayer, packet);
            }
        } else {
            if (isDryFood(eaten)) {
                cap.drainWater(3);
            }
        }
    }

    private static boolean isDryFood(ItemStack stack) {
        return stack.getItem() == Items.BREAD || stack.getItem() == Items.COOKED_BEEF;
    }
}