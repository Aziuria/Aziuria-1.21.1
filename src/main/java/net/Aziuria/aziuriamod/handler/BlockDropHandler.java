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

        if (chance < 5.0) {
            return new ItemStack(Items.WHEAT_SEEDS);
        } else if (chance < 10.0) {
            return new ItemStack(Items.BEETROOT_SEEDS);
        } else if (chance < 15.0) {
            return new ItemStack(Items.CARROT);
        } else if (chance < 20.0) {
            return new ItemStack(Items.POTATO);
        } else if (chance < 25.0) {
            return new ItemStack(Items.MELON_SEEDS);
        } else if (chance < 30.0) {
            return new ItemStack(Items.PUMPKIN_SEEDS);
        } else if (chance < 35.0) {
            return new ItemStack(ModItems.TOMATO_SEEDS.get());
        } else if (chance < 40.0) {
            return new ItemStack(ModItems.CUCUMBER_SEEDS.get());
        } else if (chance < 45.0) {
            return new ItemStack(ModItems.RADISH_SEEDS.get());
        } else if (chance < 50.0) {
            return new ItemStack(ModItems.LETTUCE_SEEDS.get());
        } else if (chance < 55.0) {
            return new ItemStack(ModItems.ONION_SEEDS.get());
        } else if (chance < 60.0) {
            return new ItemStack(ModItems.SPRING_ONION_SEEDS.get());
        } else if (chance < 65.0) {
            return new ItemStack(ModItems.CORN_SEEDS.get());
        } else if (chance < 70.0) {
            return new ItemStack(ModItems.PINEAPPLE_SEEDS.get());
        } else {
            return ItemStack.EMPTY;
        }
    }
}