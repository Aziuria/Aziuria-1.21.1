package net.Aziuria.aziuriamod.thirst.registry;

import net.Aziuria.aziuriamod.item.ModItems;
import net.minecraft.world.item.Items;

public class ThirstSetup {

    public static void registerThirstItems() {
        // Vanilla items - positive thirst restore
        ThirstRegistry.register(Items.POTION, 6);
        ThirstRegistry.register(Items.MILK_BUCKET, 8);

        // Mod items - positive thirst restore
        ThirstRegistry.register(ModItems.APPLE_JUICE.get(), 10);

        // Dry foods - negative thirst restore (penalty)
        ThirstRegistry.register(Items.BREAD, -3);
        ThirstRegistry.register(Items.COOKED_BEEF, -3);

        // Add more items as needed
    }
}