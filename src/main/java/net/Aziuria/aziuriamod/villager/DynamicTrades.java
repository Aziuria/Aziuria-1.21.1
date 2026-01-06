package net.Aziuria.aziuriamod.villager;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.neoforge.common.BasicItemListing;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import org.jetbrains.annotations.Nullable;

public class DynamicTrades {

    public static void addBuyTrade(VillagerTradesEvent event, int level, ItemStack sellingItem, int price, int count) {
        event.getTrades().get(level).add(new BasicItemListing(
                price,
                sellingItem.copyWithCount(count),
                Integer.MAX_VALUE,
                2,
                0.05F
        ));
    }

    public static void addBuyTrade(VillagerTradesEvent event, int level, ItemStack sellingItem, int price, int count, int xp) {
        event.getTrades().get(level).add(new BasicItemListing(
                price,
                sellingItem.copyWithCount(count),
                Integer.MAX_VALUE,
                xp,
                0.05F
        ));
    }

    public static void addSellTrade(VillagerTradesEvent event, int level, ItemStack buyingItem, int count, int emeraldsReceived, int xp) {
        event.getTrades().get(level).add(new BasicItemListing(
                buyingItem.copyWithCount(count),
                new ItemStack(Items.EMERALD, emeraldsReceived),
                Integer.MAX_VALUE,
                xp,
                0.05F
        ));
    }
}