package net.Aziuria.aziuriamod.hud.thirst.registry;

import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;

public class ThirstRegistry {
    private static final Map<Item, Integer> thirstRestoreMap = new HashMap<>();

    public static void register(Item item, int thirstAmount) {
        if (item != null) {
            thirstRestoreMap.put(item, thirstAmount);
        }
    }

    public static int getThirstRestore(Item item) {
        return thirstRestoreMap.getOrDefault(item, 0);
    }
}