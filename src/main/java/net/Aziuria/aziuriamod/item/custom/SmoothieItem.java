package net.Aziuria.aziuriamod.item.custom;

import net.Aziuria.aziuriamod.item.ModItems;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

public class SmoothieItem extends Item {

    private static final int DRINK_DURATION = 32; // same as vanilla potions

    public SmoothieItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return ItemUtils.startUsingInstantly(level, player, hand);
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return DRINK_DURATION;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entityLiving) {
        if (entityLiving instanceof Player player) {
            // Apply food effects
            player.getFoodData().eat(this.getFoodProperties(stack, player));

            if (!player.getAbilities().instabuild) { // survival mode
                stack.shrink(1);

                // Give back empty cup
                ItemStack cup = new ItemStack(ModItems.EMPTY_CUP.get());
                if (!player.getInventory().add(cup)) {
                    player.drop(cup, false);
                }
            }

            // Award stats & trigger criteria
            if (entityLiving instanceof ServerPlayer serverPlayer) {
                serverPlayer.awardStat(Stats.ITEM_USED.get(this));
            }
        }

        entityLiving.gameEvent(GameEvent.DRINK);

        return stack.isEmpty() ? new ItemStack(ModItems.EMPTY_CUP.get()) : stack;
    }
}