package net.Aziuria.aziuriamod.thirst.handler;

import net.Aziuria.aziuriamod.client.damage.ModDamageSources;
import net.Aziuria.aziuriamod.thirst.capability.IThirst;
import net.Aziuria.aziuriamod.thirst.capability.ThirstProvider;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class ThirstDebuffHandler {

    @SubscribeEvent
    public static void onPlayerTickPost(PlayerTickEvent.Post event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        IThirst thirst = player.getCapability(ThirstProvider.THIRST_CAP, null);
        if (thirst == null) return;

        int thirstLevel = thirst.getThirst();

        // Prevent premature debuffing
        if (player.tickCount < 20 && thirstLevel == 0) return;

        if (thirstLevel > 10) {
            removeThirstDebuffs(player);
            return;
        }

        int amplifier;
        if (thirstLevel >= 6) {
            amplifier = 0; // Mild debuffs
            player.setSprinting(false);
        } else if (thirstLevel >= 1) {
            amplifier = 1; // Stronger debuffs
            player.setSprinting(false);
        } else {
            amplifier = 2; // Critical
            player.setSprinting(false);

            if (player.tickCount % 40 == 0) {
                ServerLevel level = (ServerLevel) player.getCommandSenderWorld();
                DamageSource dehydration = ModDamageSources.dehydration(level);
                player.hurt(dehydration, 1.0F);
                player.setDeltaMovement(0, player.getDeltaMovement().y, 0);
                player.hurtMarked = false;
            }
        }

        applyEffects(player, amplifier);
    }

    private static void applyEffects(ServerPlayer player, int amplifier) {
        applyOrUpdateEffect(player, MobEffects.DIG_SLOWDOWN, amplifier);
        applyOrUpdateEffect(player, MobEffects.WEAKNESS, amplifier);
        applyOrUpdateEffect(player, MobEffects.MOVEMENT_SLOWDOWN, amplifier);
    }

    private static void applyOrUpdateEffect(ServerPlayer player, Holder<net.minecraft.world.effect.MobEffect> effect, int amplifier) {
        MobEffectInstance current = player.getEffect(effect);
        if (current == null || current.getAmplifier() != amplifier || current.getDuration() <= 20) {
            player.addEffect(new MobEffectInstance(effect, 100, amplifier, false, false, false));
        }
    }

    public static void removeThirstDebuffs(ServerPlayer player) {
        player.removeEffect(MobEffects.DIG_SLOWDOWN);
        player.removeEffect(MobEffects.WEAKNESS);
        player.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
    }
}