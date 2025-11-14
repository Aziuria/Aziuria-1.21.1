package net.Aziuria.aziuriamod.block.entity;

import net.Aziuria.aziuriamod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
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

public class FishTrapBlockEntity extends BlockEntity {

    private static final int TICKS_PER_DAY = 24000;
    private static final int MAX_FISH_PER_BAIT = 4;

    private ItemStack baitSlot = ItemStack.EMPTY;
    private int baitCapacity = 0;
    private float fishAccumulated = 0f;
    private int caughtFish = 0;
    private int ticksActive = 0;
    private int dailyTarget = 0;

    public FishTrapBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FISH_TRAP_BLOCK_ENTITY.get(), pos, state);
    }

    // ----------------------------
    // Interaction
    // ----------------------------
    public InteractionResult onRightClick(Level level, BlockPos pos, Player player, ItemStack heldItem) {
        if (!isValidBait(heldItem) && !baitSlot.isEmpty()) {
            // If holding invalid bait, do nothing
            return InteractionResult.PASS;
        }

        if (baitSlot.isEmpty()) {
            if (!heldItem.isEmpty() && isValidBait(heldItem)) {
                baitSlot = heldItem.copy();
                baitSlot.setCount(1);
                heldItem.shrink(1);
                baitCapacity = level.random.nextInt(1, MAX_FISH_PER_BAIT + 1);
                fishAccumulated = 0f;

                setChanged();
                level.sendBlockUpdated(pos, getBlockState(), getBlockState(), 3);
                return InteractionResult.CONSUME;
            }
        } else {
            if (ItemStack.matches(baitSlot, heldItem) && baitSlot.getCount() < baitSlot.getMaxStackSize()) {
                int space = baitSlot.getMaxStackSize() - baitSlot.getCount();
                int add = Math.min(space, heldItem.getCount());
                baitSlot.grow(add);
                heldItem.shrink(add);

                setChanged();
                level.sendBlockUpdated(pos, getBlockState(), getBlockState(), 3);
                return InteractionResult.CONSUME;
            }

            if (!player.addItem(baitSlot)) player.drop(baitSlot, false);
            baitSlot = ItemStack.EMPTY;
            baitCapacity = 0;
            fishAccumulated = 0f;

            setChanged();
            level.sendBlockUpdated(pos, getBlockState(), getBlockState(), 3);
            return InteractionResult.CONSUME;
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
        float ratePerTick = trap.baitCapacity / (float) TICKS_PER_DAY;
        trap.fishAccumulated += ratePerTick;

        if (trap.fishAccumulated >= trap.baitCapacity) {
            trap.caughtFish += (int) trap.fishAccumulated; // move fish to caught
            trap.baitSlot = ItemStack.EMPTY;               // bait is gone
            trap.baitCapacity = 0;
            trap.fishAccumulated = 0f;

            trap.setChanged(); // mark dirty
            if (!level.isClientSide) {
                BlockState currentState = level.getBlockState(pos);
                level.sendBlockUpdated(pos, currentState, currentState, 3);
            }
        }

        if (trap.ticksActive >= TICKS_PER_DAY) trap.ticksActive = 0;
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
    public ItemStack getDrop() {
        ItemStack drop = ItemStack.EMPTY;

        if (caughtFish > 0) {
            // Mix fish types randomly
            ItemStack[] possibleFish = new ItemStack[] {
                    new ItemStack(Items.COD),
                    new ItemStack(Items.SALMON),
                    new ItemStack(Items.TROPICAL_FISH),
                    new ItemStack(Items.PUFFERFISH)
            };

            // Count of each fish type
            int[] counts = new int[possibleFish.length];

            for (int i = 0; i < caughtFish; i++) {
                int index = level.random.nextInt(possibleFish.length);
                counts[index]++;
            }

            // Combine into a single drop stack (growing if necessary)
            for (int i = 0; i < possibleFish.length; i++) {
                if (counts[i] > 0) {
                    if (drop.isEmpty()) {
                        drop = possibleFish[i].copy();
                        drop.setCount(counts[i]);
                    } else {
                        ItemStack temp = possibleFish[i].copy();
                        temp.setCount(counts[i]);
                        drop.grow(temp.getCount());
                    }
                }
            }

            caughtFish = 0; // reset after drop
        }

        if (!baitSlot.isEmpty()) {
            if (drop.isEmpty()) drop = baitSlot.copy();
            else drop.grow(baitSlot.getCount());
        }

        setChanged();
        return drop;
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
        return stack.is(ModItems.WORM.get()) || stack.is(ModItems.CORN.get()) || stack.is(ModItems.BREAD_BAIT);
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