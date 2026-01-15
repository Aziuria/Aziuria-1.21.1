package net.Aziuria.aziuriamod.block.entity.custom;

import net.Aziuria.aziuriamod.block.entity.ModBlockEntities;
import net.Aziuria.aziuriamod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FishTrapBlockEntity extends BlockEntity {

    private static final int MIN_TICKS = 5000;        // minimum time for a bait to catch fish
    private static final int MAX_TICKS = 24000;       // maximum time (1 Minecraft day)
    private int targetTicks = MAX_TICKS;             // dynamically chosen per bait
    private static final int MAX_FISH_PER_BAIT = 4;

    private ItemStack baitSlot = ItemStack.EMPTY;
    private int baitCapacity = 0;
    private float fishAccumulated = 0f;
    private int caughtFish = 0;
    private int ticksActive = 0;
    private int dailyTarget = 0;
    private int[] fishCounts = new int[4];

    private static final Random random = new Random();

    public FishTrapBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FISH_TRAP_BLOCK_ENTITY.get(), pos, state);
    }

    // ----------------------------
    // Interaction
    // ----------------------------
    public InteractionResult onRightClick(Level level, BlockPos pos, Player player, ItemStack heldItem) {
        // Remove bait with empty hand (this is for future when bait based fishing becomes a thing)(THIS WAS LAST CHANGE I DID COMMENTING IT OUT IF ISSUE SHOULD ARISE)
//        if (baitSlot != ItemStack.EMPTY && heldItem.isEmpty()) {
//            if (!player.addItem(baitSlot)) player.drop(baitSlot, false);
//            baitSlot = ItemStack.EMPTY;
//            baitCapacity = 0;
//            fishAccumulated = 0f;
//
//            setChanged();
//            level.sendBlockUpdated(pos, getBlockState(), getBlockState(), 3);
//            return InteractionResult.CONSUME;
//        }

        // Add bait if trap is empty and holding valid bait
        if (baitSlot.isEmpty()) {
            if (!heldItem.isEmpty() && isValidBait(heldItem)) {
                baitSlot = heldItem.copy();
                baitSlot.setCount(1);
                heldItem.shrink(1);
                baitCapacity = level.random.nextInt(1, MAX_FISH_PER_BAIT + 1);
                fishAccumulated = 0f;

                targetTicks = random.nextInt(MIN_TICKS, MAX_TICKS + 1);
                ticksActive = 0; // start counting

                setChanged();
                level.sendBlockUpdated(pos, getBlockState(), getBlockState(), 3);
                return InteractionResult.CONSUME;
            }
        } else {
            // Add more bait if holding same type
            if (ItemStack.matches(baitSlot, heldItem) && baitSlot.getCount() < baitSlot.getMaxStackSize()) {
                int space = baitSlot.getMaxStackSize() - baitSlot.getCount();
                int add = Math.min(space, heldItem.getCount());
                baitSlot.grow(add);
                heldItem.shrink(add);

                setChanged();
                level.sendBlockUpdated(pos, getBlockState(), getBlockState(), 3);
                return InteractionResult.CONSUME;
            }
        }

        return InteractionResult.PASS;
    }

    // ----------------------------
    // Tick
    // ----------------------------
    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, FishTrapBlockEntity trap) {
        if (level.isClientSide || trap.baitSlot.isEmpty()) return;

        if (!trap.isValidBait(trap.baitSlot)) return; // only valid bait
        if (!trap.isSubmerged(level, pos)) return;   // must be underwater

        if (trap.ticksActive == 0) trap.dailyTarget = level.random.nextInt(1, 10);

        trap.ticksActive++;
        float ratePerTick = trap.baitCapacity / (float) trap.targetTicks;
        trap.fishAccumulated += ratePerTick;

        if (trap.fishAccumulated >= trap.baitCapacity) {
            int totalFish = trap.baitCapacity; // total fish caught
            trap.baitSlot = ItemStack.EMPTY;   // consume bait
            trap.baitCapacity = 0;
            trap.fishAccumulated = 0f;
            trap.targetTicks = MAX_TICKS; // reset target ticks

            // Reset counts
            for (int i = 0; i < trap.fishCounts.length; i++) trap.fishCounts[i] = 0;

            // Randomly assign fish
            for (int i = 0; i < totalFish; i++) {
                int index = trap.level.random.nextInt(4);
                trap.fishCounts[index]++;
            }

            trap.caughtFish = totalFish; // total for reference
            trap.setChanged();
            if (!trap.level.isClientSide) {
                BlockState currentState = trap.level.getBlockState(pos);
                trap.level.sendBlockUpdated(pos, currentState, currentState, 3);
            }
        }

        if (trap.ticksActive >= trap.targetTicks) trap.ticksActive = 0; // MODIFIED
        trap.setChanged();
    }

    // ----------------------------
    // Inventory for renderer
    // ----------------------------
    public ItemStack getBaitSlot() {
        return baitSlot;
    }

    // ----------------------------
    // Drops
    // ----------------------------
    public List<ItemStack> getDrops() {
        List<ItemStack> drops = new ArrayList<>();
        ItemStack[] possibleFish = new ItemStack[]{
                new ItemStack(Items.COD),
                new ItemStack(Items.SALMON),
                new ItemStack(Items.TROPICAL_FISH),
                new ItemStack(Items.PUFFERFISH)
        };

        // Add each fish individually according to fishCounts
        for (int i = 0; i < fishCounts.length; i++) {
            for (int j = 0; j < fishCounts[i]; j++) {
                drops.add(possibleFish[i].copy());
            }
        }

        // Reset counts after drop
        caughtFish = 0;
        for (int i = 0; i < fishCounts.length; i++) fishCounts[i] = 0;

        // Add remaining bait if any
        if (!baitSlot.isEmpty()) {
            drops.add(baitSlot.copy());
            baitSlot = ItemStack.EMPTY;
        }

        setChanged();
        return drops;
    }

    // ----------------------------
    // Submerged check
    // ----------------------------
    private boolean isSubmerged(Level level, BlockPos pos) {
        return level.getFluidState(pos).isSource();
    }

    // ----------------------------
    // Valid bait check
    // ----------------------------
    private boolean isValidBait(ItemStack stack) {
        return stack.is(ModItems.WORM.get()) || stack.is(ModItems.CORN_KERNELS.get()) || stack.is(ModItems.BREAD_BAIT);
    }

    // ----------------------------
    // Save / Load
    // ----------------------------
    @Override
    public void loadAdditional(CompoundTag nbt, HolderLookup.Provider provider) {
        super.loadAdditional(nbt, provider);

        if (nbt.contains("BaitSlot")) {
            baitSlot = ItemStack.parse(provider, nbt.get("BaitSlot")).orElse(ItemStack.EMPTY);
        } else {
            baitSlot = ItemStack.EMPTY;
        }

        baitCapacity = nbt.getInt("BaitCapacity");
        fishAccumulated = nbt.getFloat("FishAccumulated");
        ticksActive = nbt.getInt("TicksActive");
        dailyTarget = nbt.getInt("DailyTarget");
        caughtFish = nbt.getInt("CaughtFish");
        targetTicks = nbt.getInt("TargetTicks");

        // Load fishCounts
        if (nbt.contains("FishCounts")) {
            ListTag list = nbt.getList("FishCounts", 3); // 3 = TAG_Int
            for (int i = 0; i < list.size() && i < fishCounts.length; i++) {
                fishCounts[i] = list.getInt(i);
            }
        } else {
            fishCounts = new int[4];
        }
    }

    @Override
    public void saveAdditional(CompoundTag nbt, HolderLookup.Provider provider) {
        super.saveAdditional(nbt, provider);

        if (!baitSlot.isEmpty()) {
            nbt.put("BaitSlot", baitSlot.save(provider));
        }

        nbt.putInt("BaitCapacity", baitCapacity);
        nbt.putFloat("FishAccumulated", fishAccumulated);
        nbt.putInt("TicksActive", ticksActive);
        nbt.putInt("DailyTarget", dailyTarget);
        nbt.putInt("CaughtFish", caughtFish);
        nbt.putInt("TargetTicks", targetTicks);

        // Save fishCounts
        ListTag list = new ListTag();
        for (int i : fishCounts) {
            list.add(IntTag.valueOf(i));
        }
        nbt.put("FishCounts", list);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider provider) {
        return saveWithoutMetadata(provider);
    }

    public Component getDisplayName() {
        return Component.literal("Fish Trap");
    }
}