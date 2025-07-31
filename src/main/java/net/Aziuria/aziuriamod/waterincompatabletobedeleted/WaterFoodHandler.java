//package net.Aziuria.aziuriamod.water;
//
//import net.minecraft.server.level.ServerPlayer;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.Items;
//import net.neoforged.bus.api.SubscribeEvent;
//import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
//
//public class WaterFoodHandler {
//
//    @SubscribeEvent
//    public static void onItemUseFinish(LivingEntityUseItemEvent.Finish event) {
//        if (!(event.getEntity() instanceof ServerPlayer player)) return;
//
//        ItemStack item = event.getItem();
//        if (item.isEmpty()) return;
//
//        PlayerWaterCapability cap = player.getCapability(ModCapabilities.WATER_CAP);
//        if (cap == null) return;
//
//        int hydration = HydrationRegistry.getHydration(item.getItem());
//
//        if (hydration > 0) {
//            cap.addWater(hydration);
//            System.out.println("[Hydration] " + player.getName().getString() + " drank " + item.getItem());
//        } else if (isDryFood(item)) {
//            cap.drainWater(3);
//            System.out.println("[Hydration] " + player.getName().getString() + " ate dry food " + item.getItem());
//        }
//        // Sync happens via callback; no manual send here
//    }
//
//    private static boolean isDryFood(ItemStack stack) {
//        return stack.is(Items.BREAD) || stack.is(Items.COOKED_BEEF);
//    }
//}