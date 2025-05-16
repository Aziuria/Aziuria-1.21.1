package net.Aziuria.aziuriamod.block.entity;

import net.Aziuria.aziuriamod.AziuriaMod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;
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
            if (!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
            AziuriaMod.LOGGER.info("Tank contents changed. Fluid amount: {}", getFluidAmount());
        }
    };

    public SteelBarrelBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.STEEL_BARREL_BLOCK_ENTITY.get(), pos, state);
    }


    public FluidTank getTank() {
        return tank;
    }

    // **NO @Override here**
    @Nullable
    public <T, C> T getCapability(BlockCapability<T, C> capability, C context) {
        if (capability == Capabilities.FluidHandler.BLOCK) {
            AziuriaMod.LOGGER.info("Fluid handler capability accessed from {}", context);
            return (T) tank;
        }
        return null;
    }

    // Data saving
    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        CompoundTag tankTag = new CompoundTag();
        tank.writeToNBT(registries, tankTag);
        tag.put("tank", tankTag);
    }

    // Data loading
    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains("tank")) {
            tank.readFromNBT(registries, tag.getCompound("tank"));
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
            AziuriaMod.LOGGER.info("Inserted {} mB of fluid from item into SteelBarrel at {}", filled, getBlockPos());
            return true;
        }

        return false;
    }
}
