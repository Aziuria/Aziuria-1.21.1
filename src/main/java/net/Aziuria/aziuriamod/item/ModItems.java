package net.Aziuria.aziuriamod.item;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AziuriaMod.MOD_ID);

    public static final DeferredItem<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STEEL_ALLOY_MESH = ITEMS.register("steel_alloy_mesh",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SULPHUR = ITEMS.register("sulphur",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> POTASSIUM = ITEMS.register("potassium",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SACK = ITEMS.register("sack",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);



    }
}
