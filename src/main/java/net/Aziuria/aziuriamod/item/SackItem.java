package net.Aziuria.aziuriamod.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.Aziuria.aziuriamod.client.screen.custom.SackMenu;

public class SackItem extends Item {
    public SackItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        System.out.println("SackItem used");

        if (!level.isClientSide) {
            MenuProvider provider = new SimpleMenuProvider(
                    (windowId, inventory, playerEntity) ->
                            new SackMenu(windowId, inventory, stack),
                    Component.literal("Sack")
            );
            player.openMenu(provider);
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}