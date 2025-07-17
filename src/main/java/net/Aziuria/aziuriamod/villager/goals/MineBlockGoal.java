package net.Aziuria.aziuriamod.villager.goals;

import net.Aziuria.aziuriamod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.EnumSet;
import java.util.Set;

public class MineBlockGoal extends Goal {

    private final Villager villager;
    private final double speed;
    private BlockPos targetBlockPos = null;

    // Set of ore blocks villager will mine
    private static final Set<Block> ORE_BLOCKS = Set.of(
            Blocks.COAL_ORE,
            Blocks.DEEPSLATE_COAL_ORE,
            Blocks.IRON_ORE,
            Blocks.DEEPSLATE_IRON_ORE,
            Blocks.GOLD_ORE,
            Blocks.DEEPSLATE_GOLD_ORE,
            Blocks.REDSTONE_ORE,
            Blocks.DEEPSLATE_REDSTONE_ORE,
            Blocks.DIAMOND_ORE,
            Blocks.DEEPSLATE_DIAMOND_ORE,
            Blocks.EMERALD_ORE,
            Blocks.DEEPSLATE_EMERALD_ORE,
            Blocks.LAPIS_ORE,
            Blocks.DEEPSLATE_LAPIS_ORE,
            Blocks.NETHER_GOLD_ORE,
            Blocks.NETHER_QUARTZ_ORE,
            Blocks.ANCIENT_DEBRIS,

            // Your custom ores (replace with your actual blocks)
            ModBlocks.POTASSIUM_ORE.get(),
            ModBlocks.DEEPSLATE_POTASSIUM_ORE.get(),
            ModBlocks.SULPHUR_ORE.get(),
            ModBlocks.DEEPSLATE_SULPHUR_ORE.get()
    );

    // Stone blocks to mine if no ores are nearby
    private static final Set<Block> STONE_BLOCKS = Set.of(
            Blocks.STONE,
            Blocks.DEEPSLATE,
            Blocks.COBBLESTONE
    );

    public MineBlockGoal(Villager villager, double speed) {
        this.villager = villager;
        this.speed = speed;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        ensureVillagerHasPickaxe(villager);
        ItemStack mainHand = villager.getMainHandItem();
        if (!(mainHand.getItem() instanceof PickaxeItem)) {
            return false;
        }

        targetBlockPos = findOreOrStone();
        return targetBlockPos != null;
    }

    @Override
    public boolean canContinueToUse() {
        if (targetBlockPos == null) return false;
        BlockState state = villager.level().getBlockState(targetBlockPos);
        Block block = state.getBlock();
        return ORE_BLOCKS.contains(block) || STONE_BLOCKS.contains(block);
    }

    @Override
    public void stop() {
        targetBlockPos = null;
        villager.getNavigation().stop();
    }

    @Override
    public void tick() {
        ensureVillagerHasPickaxe(villager);

        if (targetBlockPos == null) return;

        villager.getLookControl().setLookAt(
                targetBlockPos.getX() + 0.5,
                targetBlockPos.getY() + 0.5,
                targetBlockPos.getZ() + 0.5
        );

        if (!villager.getNavigation().isInProgress()) {
            villager.getNavigation().moveTo(
                    targetBlockPos.getX() + 0.5,
                    targetBlockPos.getY(),
                    targetBlockPos.getZ() + 0.5,
                    speed
            );
        }

        if (villager.blockPosition().closerThan(targetBlockPos, 1.5)) {
            mineBlock(targetBlockPos);
            targetBlockPos = null;
        }
    }

    private void mineBlock(BlockPos pos) {
        Level level = villager.level();
        BlockState state = level.getBlockState(pos);
        Block block = state.getBlock();

        if (!(ORE_BLOCKS.contains(block) || STONE_BLOCKS.contains(block))) return;

        level.playSound(null, pos, block.getSoundType(state, level, pos, villager).getBreakSound(),
                net.minecraft.sounds.SoundSource.BLOCKS, 1.0F, 1.0F);

        Block.getDrops(state, (ServerLevel) level, pos, null).forEach(stack -> villager.getInventory().addItem(stack));

        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
    }

    private BlockPos findOreOrStone() {
        BlockPos origin = villager.blockPosition();
        Level level = villager.level();
        int radius = 15;

        // Search for ores first
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -radius; dy <= radius; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    BlockPos pos = origin.offset(dx, dy, dz);
                    BlockState state = level.getBlockState(pos);
                    if (ORE_BLOCKS.contains(state.getBlock())) {
                        return pos;
                    }
                }
            }
        }

        // If no ores found, search for stone blocks
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -radius; dy <= radius; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    BlockPos pos = origin.offset(dx, dy, dz);
                    BlockState state = level.getBlockState(pos);
                    if (STONE_BLOCKS.contains(state.getBlock())) {
                        return pos;
                    }
                }
            }
        }

        return null;
    }

    private static void ensureVillagerHasPickaxe(Villager villager) {
        if (!(villager.getMainHandItem().getItem() instanceof PickaxeItem)) {
            villager.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.IRON_PICKAXE));
        }
    }
}