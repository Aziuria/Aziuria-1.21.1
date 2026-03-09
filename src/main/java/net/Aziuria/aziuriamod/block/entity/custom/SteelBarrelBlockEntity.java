package net.Aziuria.aziuriamod.block.entity.custom;

import net.Aziuria.aziuriamod.block.custom.custom.functional.CopperBarrelBlock;
import net.Aziuria.aziuriamod.block.custom.custom.functional.SteelBarrelBlock;
import net.Aziuria.aziuriamod.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.Nullable;

public class SteelBarrelBlockEntity extends BlockEntity {

    private static final int BUCKET_AMOUNT = 1000;

    public final FluidTank tank = new FluidTank(BUCKET_AMOUNT * 10) {
        @Override
        protected void onContentsChanged() {
            setChanged();
            if (level != null && !level.isClientSide()) {

                // <<< ADDED : check if tank contains lava
                boolean hasLava = tank.getFluid().getFluid() == Fluids.LAVA;

                BlockState state = level.getBlockState(worldPosition);

                if (state.hasProperty(SteelBarrelBlock.LAVA_LIT)
                        && state.getValue(SteelBarrelBlock.LAVA_LIT) != hasLava) {

                    level.setBlock(
                            worldPosition,
                            state.setValue(SteelBarrelBlock.LAVA_LIT, hasLava),
                            3
                    );
                }
                // <<< END ADDED

                if (state.hasProperty(CopperBarrelBlock.LAVA_LIT)
                        && state.getValue(CopperBarrelBlock.LAVA_LIT) != hasLava) {

                    level.setBlock(
                            worldPosition,
                            state.setValue(CopperBarrelBlock.LAVA_LIT, hasLava),
                            3
                    );
                }
// <<< END ADDED


                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
            }
        }
    };

    public SteelBarrelBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.STEEL_BARREL_BLOCK_ENTITY.get(), pos, state);
    }


    public FluidTank getTank() {
        return tank;
    }

    // **NO @Override here**
    @SuppressWarnings("unchecked")
    @Nullable
    public <T, C> T getCapability(BlockCapability<T, C> capability, C context) {
        if (capability == Capabilities.FluidHandler.BLOCK) {
            return (T) tank;
        }
        return null;
    }

    @Override
    public void setChanged() {
        super.setChanged();
    }

    // Data saving
    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        CompoundTag tankTag = new CompoundTag();
        tank.writeToNBT(registries, tankTag);
        tag.put("tank", tankTag);
        setChanged();
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        if (tag.contains("tank")) {
            CompoundTag tankTag = tag.getCompound("tank");
            tank.readFromNBT(registries, tankTag);

            setChanged(); // marks the block entity as dirty for saving again if needed

            if (level != null && !level.isClientSide()) {
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
            }
        }
    }


    // Syncing to client
    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    public boolean tryInsertFluidFromItem(ItemStack stack, Player player) {
        if (stack.isEmpty()) return false;

        Level level = player.level();

        var fluidHandlerOptional = FluidUtil.getFluidHandler(stack);
        if (fluidHandlerOptional.isEmpty()) return false;

        var itemFluidHandler = fluidHandlerOptional.get();

        int maxTransfer = tank.getCapacity() - tank.getFluidAmount();
        if (maxTransfer <= 0) return false;

        FluidStack drained = itemFluidHandler.drain(maxTransfer, IFluidHandler.FluidAction.SIMULATE);
        if (drained.isEmpty()) return false;

        int filled = tank.fill(drained, IFluidHandler.FluidAction.EXECUTE);
        if (filled > 0) {
            itemFluidHandler.drain(filled, IFluidHandler.FluidAction.EXECUTE);

            setChanged();

            if (!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
            return true;
        }

        return false;
    }

    // ===============================
    // NEW CODE: Auto rain & lava collection
    // ===============================
    public void tick() {
        if (level == null || level.isClientSide()) return;

        BlockPos above = worldPosition.above();

        // --- Rain collection with chance (0.025% per tick) ---
        if (level.isRainingAt(above) && level.getRandom().nextFloat() < 0.00025F) {
            tank.fill(new FluidStack(Fluids.WATER, 1000), IFluidHandler.FluidAction.EXECUTE);
        }

        // --- Lava drip collection with chance (0.05% per tick) ---
        if (level.getFluidState(above).getType() == Fluids.LAVA && level.getRandom().nextFloat() < 0.0005F) {
            tank.fill(new FluidStack(Fluids.LAVA, 1000), IFluidHandler.FluidAction.EXECUTE);
        }

        //// =========================================
        //// DRIPSTONE VANILLA UPGRADE START
        //// =========================================

        // --- DRIPSTONE SAFE UPGRADE ---
        BlockPos scanPos = above;

        while (scanPos.getY() < level.getMaxBuildHeight()) { // safety: world bounds
            if (!level.isLoaded(scanPos)) break; // safety: chunk loaded

            BlockState dripstoneState = level.getBlockState(scanPos);

            // Stop if solid block (non-air, non-dripstone)
            if (!dripstoneState.isAir() && !(dripstoneState.getBlock() instanceof PointedDripstoneBlock)) {
                break;
            }

            // If we hit a downward-pointing dripstone, proceed as usual
            if (dripstoneState.getBlock() instanceof PointedDripstoneBlock
                    && dripstoneState.getValue(PointedDripstoneBlock.TIP_DIRECTION) == Direction.DOWN) {

                BlockPos topDripstone = scanPos;

                // Walk upward through dripstone chain
                while (level.getBlockState(topDripstone).getBlock() instanceof PointedDripstoneBlock) {
                    topDripstone = topDripstone.above();
                }

                // Check for fluid source above stalactite
                BlockPos sourcePos = topDripstone.above();
                var fluid = level.getFluidState(sourcePos).getType();

                if (fluid == Fluids.WATER && level.getRandom().nextFloat() < 0.000925F) {
                    tank.fill(new FluidStack(Fluids.WATER, 100), IFluidHandler.FluidAction.EXECUTE);
                }

                if (fluid == Fluids.LAVA && level.getRandom().nextFloat() < 0.000925F) {
                    tank.fill(new FluidStack(Fluids.LAVA, 100), IFluidHandler.FluidAction.EXECUTE);
                }

                break; // only process the first valid dripstone
            }

            // Move scanPos one block up
            scanPos = scanPos.above();
        }

        //// =========================================
        //// DRIPSTONE VANILLA UPGRADE END
        //// =========================================
    }
}