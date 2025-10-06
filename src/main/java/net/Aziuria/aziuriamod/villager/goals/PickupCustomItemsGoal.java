package net.Aziuria.aziuriamod.villager.goals;

import net.Aziuria.aziuriamod.block.ModBlocks;
import net.Aziuria.aziuriamod.item.ModItems;
import net.Aziuria.aziuriamod.villager.ModVillagers;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.EnumSet;
import java.util.List;

public class PickupCustomItemsGoal extends Goal {

    private final Villager villager;
    private static final double PICKUP_RADIUS = 5.0D;

    public PickupCustomItemsGoal(Villager villager) {
        this.villager = villager;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        // Don’t run for babies and only run if at least one target item is nearby
        return !villager.isBaby() && !getNearbyItems().isEmpty();
    }

    @Override
    public void tick() {
        List<ItemEntity> items = getNearbyItems();

        if (items.isEmpty()) return;


        ItemEntity nearest = null;
        double closestDistance = Double.MAX_VALUE;

        for (ItemEntity entity : items) {
            double dist = villager.distanceToSqr(entity);
            if (dist < closestDistance) {
                closestDistance = dist;
                nearest = entity;
            }
        }

        if (nearest != null) {
            // Move towards it
            villager.getNavigation().moveTo(nearest, 1.0D);

            // Pickup if close enough
            if (closestDistance < 2.0D) {
                ItemStack stack = nearest.getItem();
                villager.getInventory().addItem(stack.copy());
                nearest.discard();
            }
        }
    }

    private List<ItemEntity> getNearbyItems() {
        return villager.level().getEntitiesOfClass(
                ItemEntity.class,
                villager.getBoundingBox().inflate(PICKUP_RADIUS),
                entity -> {
                    Item item = entity.getItem().getItem();
                    VillagerProfession profession = villager.getVillagerData().getProfession();

                    if (profession == VillagerProfession.FARMER) {
                        return isFarmerItem(item);
                    } else if (profession == VillagerProfession.FISHERMAN) {
                        return isFishermanItem(item);
                    } else if (profession == ModVillagers.WOODCUTTER.value()) {
                        return isWoodcutterItem(item);
                    } else if (profession == ModVillagers.MINER.value()) {
                        return isMinerItem(item);
                    }

                    return false;
                }
        );
    }

    private boolean isFarmerItem(Item item) {
        return item == ModItems.CUCUMBER_SEEDS.get()
                || item == ModItems.RADISH_SEEDS.get()
                || item == ModItems.LETTUCE_SEEDS.get()
                || item == ModItems.ONION_SEEDS.get()
                || item == ModItems.SPRING_ONION_SEEDS.get()
                || item == ModItems.TOMATO_SEEDS.get()
                || item == ModItems.BLACKCURRANT_SEEDS.get()
                || item == ModItems.STRAWBERRY_SEEDS.get()
                || item == ModItems.COFFEE_SEEDS.get()
                || item == ModItems.TEA_SEEDS.get()
                || item == ModItems.PINEAPPLE_SEEDS.get()
                || item == ModItems.CORN_SEEDS.get()
                || item == ModItems.CUCUMBER.get()
                || item == ModItems.LETTUCE.get()
                || item == ModItems.ONION.get()
                || item == ModItems.SPRING_ONION.get()
                || item == ModItems.RADISH.get()
                || item == ModItems.TOMATO.get()
                || item == ModItems.BLACKCURRANT.get()
                || item == ModItems.COFFEE_BEANS.get()
                || item == ModItems.TEA_LEAVES.get()
                || item == ModItems.CORN.get()
                || item == ModItems.PINEAPPLE.get()
                || item == ModItems.STRAWBERRY.get();
    }

    private boolean isFishermanItem(Item item) {
        return item == Items.FISHING_ROD
                || item == Items.COD
                || item == Items.SALMON
                || item == Items.PUFFERFISH
                || item == Items.TROPICAL_FISH;
    }

    private boolean isWoodcutterItem(Item item) {
        // Vanilla axes
        if (item == Items.WOODEN_AXE
                || item == Items.STONE_AXE
                || item == Items.IRON_AXE
                || item == Items.DIAMOND_AXE
                || item == Items.NETHERITE_AXE) {
            return true;
        }

        // Vanilla logs
        if (item == Items.OAK_LOG
                || item == Items.BIRCH_LOG
                || item == Items.SPRUCE_LOG
                || item == Items.JUNGLE_LOG
                || item == Items.ACACIA_LOG
                || item == Items.DARK_OAK_LOG
                || item == Items.MANGROVE_LOG
                || item == Items.CHERRY_LOG) {
            return true;
        }

        // Vanilla wood blocks
        if (item == Items.OAK_WOOD
                || item == Items.BIRCH_WOOD
                || item == Items.SPRUCE_WOOD
                || item == Items.JUNGLE_WOOD
                || item == Items.ACACIA_WOOD
                || item == Items.DARK_OAK_WOOD
                || item == Items.MANGROVE_WOOD
                || item == Items.CHERRY_WOOD) {
            return true;
        }

        // Vanilla saplings + mangrove propagule
        if (item == Items.OAK_SAPLING
                || item == Items.BIRCH_SAPLING
                || item == Items.SPRUCE_SAPLING
                || item == Items.JUNGLE_SAPLING
                || item == Items.ACACIA_SAPLING
                || item == Items.DARK_OAK_SAPLING
                || item == Items.MANGROVE_PROPAGULE
                || item == Items.STICK
                || item == Items.APPLE
                || item == ModItems.PEAR.get()
                || item == ModItems.CHERRY.get()
                || item == ModItems.AVOCADO.get()
                || item == ModBlocks.CHERRY_SAPLING.get().asItem()
                || item == ModBlocks.AVOCADO_SAPLING.get().asItem()
                || item == ModBlocks.APPLE_SAPLING.get().asItem() // <-- Your modded apple sapling here
                || item == ModBlocks.PEAR_SAPLING.get().asItem()) { // <-- Your modded pear sapling here
            return true;
        }

        // Modded steel axe
        if (item == ModItems.STEEL_AXE.get()) {
            return true;
        }

        return false;
    }

    private boolean isMinerItem(Item item) {
        return item == Items.COAL
                || item == Items.RAW_IRON
                || item == Items.RAW_GOLD
                || item == Items.DIAMOND
                || item == Items.EMERALD
                || item == Items.QUARTZ
                || item == Items.ANCIENT_DEBRIS
                || item == Items.LAPIS_LAZULI
                || item == Items.REDSTONE
                || item == Items.STONE
                || item == Items.COBBLESTONE
                || item == Items.AMETHYST_SHARD
                || item == Items.OBSIDIAN
                || item == ModItems.POTASSIUM.get()
                || item == ModItems.SULPHUR.get()
                || item == ModItems.SPINEL.get()
                || item == ModItems.STEEL_PICKAXE.get();
    }
}