package net.Aziuria.aziuriamod.handler;

import net.Aziuria.aziuriamod.item.ModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.item.ItemEntity;
import net.neoforged.neoforge.event.level.BlockDropsEvent;
import net.neoforged.bus.api.SubscribeEvent;

import java.util.Random;

public class BlockDropHandler {

    @SubscribeEvent
    public static void onBlockDrop(BlockDropsEvent event) {
        if (event.getState().getBlock() == Blocks.SHORT_GRASS || event.getState().getBlock() == Blocks.TALL_GRASS) {
            Random random = new Random();
            if (random.nextInt(100) < 15) {  // 15% chance for seed drop
                ItemStack seedDrop = getRandomSeed();
                ItemEntity itemEntity = new ItemEntity(event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), seedDrop);
                event.getDrops().add(itemEntity);
            }
        }
    }

    private static ItemStack getRandomSeed() {
        Random random = new Random();
        int chance = random.nextInt(100);

        if (chance < 3.0) {
            return new ItemStack(Items.WHEAT_SEEDS);
        } else if (chance < 6.0) {
            return new ItemStack(Items.BEETROOT_SEEDS);
        } else if (chance < 9.0) {
            return new ItemStack(Items.CARROT);
        } else if (chance < 12.0) {
            return new ItemStack(Items.POTATO);
        } else if (chance < 15.0) {
            return new ItemStack(Items.MELON_SEEDS);
        } else if (chance < 18.0) {
            return new ItemStack(Items.PUMPKIN_SEEDS);
        } else if (chance < 21.0) {
            return new ItemStack(ModItems.TOMATO_SEEDS.get());
        } else if (chance < 24.0) {
            return new ItemStack(ModItems.CUCUMBER_SEEDS.get());
        } else if (chance < 27.0) {
            return new ItemStack(ModItems.RADISH_SEEDS.get());
        } else if (chance < 30.0) {
            return new ItemStack(ModItems.LETTUCE_SEEDS.get());
        } else if (chance < 33.0) {
            return new ItemStack(ModItems.ONION_SEEDS.get());
        } else if (chance < 36.0) {
            return new ItemStack(ModItems.SPRING_ONION_SEEDS.get());
        } else if (chance < 39.0) {
            return new ItemStack(ModItems.CORN_SEEDS.get());
        } else if (chance < 42.0) {
            return new ItemStack(ModItems.PINEAPPLE_SEEDS.get());
        } else if (chance < 45.0) {
            return new ItemStack(ModItems.BLACKCURRANT_SEEDS.get());
        } else if (chance < 47.0) {
            return new ItemStack(ModItems.STRAWBERRY_SEEDS.get());
        } else if (chance < 49.0) {
            return new ItemStack(ModItems.COFFEE_SEEDS.get());
        } else if (chance < 51.0) {
            return new ItemStack(ModItems.TEA_SEEDS.get());
        } else {
            return ItemStack.EMPTY;
        }
    }
}