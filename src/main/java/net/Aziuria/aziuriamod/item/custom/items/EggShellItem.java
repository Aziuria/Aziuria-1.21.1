package net.Aziuria.aziuriamod.item.custom.items;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;

public class EggShellItem extends Item {

    public EggShellItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();

        if (level.isClientSide()) return InteractionResult.SUCCESS;

        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);

        // Only work on bonemealable blocks
        if (!(state.getBlock() instanceof BonemealableBlock growable)) {
            return InteractionResult.PASS;
        }

        // Check if block CAN be bonemealed
        if (!growable.isValidBonemealTarget(level, pos, state)) {
            return InteractionResult.PASS;
        }

        // Apply growth (weaker than bonemeal by chance)
        if (growable.isBonemealSuccess(level, level.random, pos, state)) {
            growable.performBonemeal((ServerLevel) level, level.random, pos, state);

            // Consume one eggshell
            context.getItemInHand().shrink(1);

            return InteractionResult.CONSUME;
        }

        return InteractionResult.PASS;
    }
}