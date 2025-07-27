package net.Aziuria.aziuriamod.water;

import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;

public class HydrationRegistry {

    private static final Map<Item, Integer> hydrationMap = new HashMap<>();

    /**
     * Register an item with its hydration value.
     * @param item Item to register
     * @param hydrationAmount Amount of hydration it provides
     */
    public static void register(Item item, int hydrationAmount) {
        hydrationMap.put(item, hydrationAmount);
    }

    /**
     * Get hydration amount for an item. Defaults to 0 if not registered.
     * @param item Item to check
     * @return hydration amount
     */
    public static int getHydration(Item item) {
        return hydrationMap.getOrDefault(item, 0);
    }
}