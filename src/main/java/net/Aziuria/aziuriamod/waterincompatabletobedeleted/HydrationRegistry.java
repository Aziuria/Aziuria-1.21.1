//package net.Aziuria.aziuriamod.water;
//
//import net.minecraft.world.item.Item;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class HydrationRegistry {
//
//    private static final Map<Item, Integer> hydrationMap = new HashMap<>();
//
//    /** Register hydration value for an item. */
//    public static void register(Item item, int hydrationAmount) {
//        if (item != null && hydrationAmount > 0) {
//            hydrationMap.put(item, hydrationAmount);
//        }
//    }
//
//    /** Get hydration value for an item; returns 0 if unregistered. */
//    public static int getHydration(Item item) {
//        if (item == null) return 0;
//        return hydrationMap.getOrDefault(item, 0);
//    }
//}