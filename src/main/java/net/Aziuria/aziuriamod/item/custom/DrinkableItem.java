package net.Aziuria.aziuriamod.item.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;


    public class DrinkableItem extends Item {
        private final int useTime;
        private final UseAnim useAnim;

        public DrinkableItem(Properties properties, int useTime, UseAnim useAnim) {
            super(properties);
            this.useTime = useTime;
            this.useAnim = useAnim;
        }

        @Override
        public UseAnim getUseAnimation(ItemStack stack) {
            return useAnim; // Use passed animation, e.g. UseAnim.DRINK
        }


        @Override
        public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(player.getItemInHand(hand));
        }

        @Override
        public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
            if (entity instanceof Player player) {
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }

                if (!level.isClientSide) {
                    level.playSound(null, player.getX(), player.getY(), player.getZ(),
                            SoundEvents.GENERIC_DRINK, SoundSource.PLAYERS, 1.0F, 1.0F);

                    ItemStack bottle = new ItemStack(Items.GLASS_BOTTLE);
                    if (!player.getInventory().add(bottle)) {
                        player.drop(bottle, false);
                    }
                }
            }
            return stack.isEmpty() ? new ItemStack(Items.GLASS_BOTTLE) : stack;
        }
    }
