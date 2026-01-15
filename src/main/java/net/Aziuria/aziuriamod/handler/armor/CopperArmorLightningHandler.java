package net.Aziuria.aziuriamod.handler.armor;

import net.Aziuria.aziuriamod.data.CopperLightningSavedData;
import net.Aziuria.aziuriamod.item.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityStruckByLightningEvent;

import java.util.UUID;

public class CopperArmorLightningHandler {

    private static final long COOLDOWN_MS = 5 * 60 * 1000L; // 5 minutes
    private static final double STRIKE_CHANCE = 0.10; // 10% chance
    private static final String DATA_NAME = "aziuria_copper_lightning";

    @SubscribeEvent
    public static void onStruckByLightning(EntityStruckByLightningEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (player.level().isClientSide) return;

        if (!(player.level() instanceof ServerLevel serverLevel)) return;

        CopperLightningSavedData data = CopperLightningSavedData.get(serverLevel);

        UUID playerId = player.getUUID();
        long currentTime = System.currentTimeMillis();
        long lastTime = data.getLastStrike(playerId);

        if (currentTime - lastTime < COOLDOWN_MS) return; // cooldown
        if (Math.random() > STRIKE_CHANCE) return; // chance failed

        data.setLastStrike(playerId, currentTime);

        // --- Full copper armor check ---
        ItemStack head = player.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack legs = player.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack feet = player.getItemBySlot(EquipmentSlot.FEET);

        if (head.is(ModItems.COPPER_HELMET.get()) &&
                chest.is(ModItems.COPPER_CHESTPLATE.get()) &&
                legs.is(ModItems.COPPER_LEGGINGS.get()) &&
                feet.is(ModItems.COPPER_BOOTS.get())) {

            // Apply buffs: 30 seconds (600 ticks)
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 0));
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600, 0));

            // Reduce lightning damage to half a heart
            player.hurt(event.getLightning().damageSources().lightningBolt(), 1.0F);

            // Cancel default damage
            event.setCanceled(true);
        }
    }
}