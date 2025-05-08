package net.Aziuria.aziuriamod.handler;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.item.ItemEntity;
import net.neoforged.neoforge.event.level.BlockDropsEvent;  // Correct NeoForge BlockDropsEvent import
import net.neoforged.bus.api.SubscribeEvent;  // NeoForge event system

import java.util.Random;

public class BlockDropHandler {

    @SubscribeEvent
    public static void onBlockDrop(BlockDropsEvent event) {
        System.out.println("Block drop event triggered!");

        // Check if the broken block is short grass or tall grass
        if (event.getState().getBlock() == Blocks.SHORT_GRASS || event.getState().getBlock() == Blocks.TALL_GRASS) {
            // Add a 15% chance for additional seed drops
            Random random = new Random();
            if (random.nextInt(100) < 15) {  // 15% chance for seed drop
                ItemStack seedDrop = getRandomSeed();

                // Create an ItemEntity from the ItemStack (this is the physical item that will drop)
                ItemEntity itemEntity = new ItemEntity(event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), seedDrop);

                // Add the ItemEntity to the drops list (not ItemStack)
                event.getDrops().add(itemEntity);
            }
        }
    }

    private static ItemStack getRandomSeed() {
        // Randomly choose a seed with 15% chance for each
        Random random = new Random();
        int chance = random.nextInt(100);  // We will use a 100% range for better control

        if (chance < 15) {  // 15% chance for Wheat Seeds
            return new ItemStack(Items.WHEAT_SEEDS);
        } else if (chance < 30) {  // 15% chance for Beetroot Seeds
            return new ItemStack(Items.BEETROOT_SEEDS);
        } else if (chance < 45) {  // 15% chance for Carrot
            return new ItemStack(Items.CARROT);
        } else if (chance < 60) {  // 15% chance for Potato
            return new ItemStack(Items.POTATO);
        } else if (chance < 75) {  // 15% chance for Melon Seeds
            return new ItemStack(Items.MELON_SEEDS);
        } else if (chance < 90) {  // 15% chance for Pumpkin Seeds
            return new ItemStack(Items.PUMPKIN_SEEDS);
        } else {  // 10% chance for nothing (optional, or you can give it any item)
            return ItemStack.EMPTY;
        }
    }
}