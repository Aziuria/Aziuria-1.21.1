package net.Aziuria.aziuriamod.exhaustion.registry;

import net.minecraft.world.item.Item;
import java.util.HashMap;
import java.util.Map;

public class ExhaustionRegistry {

    private static final Map<Item, Integer> exhaustionRestoreMap = new HashMap<>();

    public static void register(Item item, int exhaustionAmount) {
        if (item != null) {
            exhaustionRestoreMap.put(item, exhaustionAmount);
        }
    }

    public static int getExhaustionRestore(Item item) {
        return exhaustionRestoreMap.getOrDefault(item, 0);
    }
}