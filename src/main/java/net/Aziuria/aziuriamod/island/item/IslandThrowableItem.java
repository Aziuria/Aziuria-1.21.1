package net.Aziuria.aziuriamod.island.item;

import net.Aziuria.aziuriamod.island.type.IslandType;
import net.Aziuria.aziuriamod.island.type.IslandBiomeType;
import net.Aziuria.aziuriamod.island.entity.IslandThrowableEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class IslandThrowableItem extends Item {

    private final IslandType type;
    private final IslandBiomeType biomeType;

    public IslandThrowableItem(IslandType type, IslandBiomeType biomeType, Properties properties) {
        super(properties);
        this.type = type;
        this.biomeType = biomeType;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            IslandThrowableEntity entity = new IslandThrowableEntity(level, type, biomeType);
            entity.setOwner(player);
            entity.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());
            entity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.25F, 1.0F);
            level.addFreshEntity(entity);
        }

        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW,
                SoundSource.PLAYERS, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    public IslandType getIslandType() {
        return type;
    }

    public IslandBiomeType getIslandBiomeType() {
        return biomeType;
    }
}