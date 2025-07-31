//package net.Aziuria.aziuriamod.thirst.handler;
//
//import net.Aziuria.aziuriamod.thirst.capability.IThirst;
//import net.Aziuria.aziuriamod.thirst.capability.ThirstProvider;
//import net.minecraft.server.level.ServerPlayer;
//import net.minecraft.world.effect.MobEffectInstance;
//import net.minecraft.world.effect.MobEffects;
//import net.minecraft.world.damagesource.DamageSource;
//import net.neoforged.bus.api.SubscribeEvent;
//import net.neoforged.neoforge.event.tick.PlayerTickEvent;
//
//public class ThirstDebuffHandler {
//
//    // Use existing starvation damage source from Minecraft
//    private static final DamageSource THIRST_DAMAGE = DamageSource.STARVE;
//
//    @SubscribeEvent
//    public static void onPlayerTickPost(PlayerTickEvent.Post event) {
//        if (!(event.getEntity() instanceof ServerPlayer player)) return;
//
//        IThirst thirst = player.getCapability(ThirstProvider.THIRST_CAP, null);
//        if (thirst == null) return;
//
//        int thirstLevel = thirst.getThirst();
//
//        if (thirstLevel > 15) {
//            removeThirstDebuffs(player);
//            return;
//        }
//
//        if (thirstLevel <= 15 && thirstLevel >= 11) {
//            player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 40, 0, false, true));
//            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 40, 0, false, true));
//            player.setSprinting(true);
//        } else if (thirstLevel <= 10 && thirstLevel >= 6) {
//            player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 40, 1, false, true));
//            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 40, 1, false, true));
//            player.setSprinting(false);
//        } else if (thirstLevel <= 5 && thirstLevel >= 1) {
//            player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 40, 2, false, true));
//            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 40, 2, false, true));
//            player.setSprinting(false);
//        } else if (thirstLevel <= 0) {
//            player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 40, 2, false, true));
//            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 40, 2, false, true));
//            player.setSprinting(false);
//
//            if (player.tickCount % 40 == 0) {
//                player.hurt(THIRST_DAMAGE, 1.0F);
//            }
//        }
//    }
//
//    private static void removeThirstDebuffs(ServerPlayer player) {
//        if (player.hasEffect(MobEffects.DIG_SLOWDOWN)) player.removeEffect(MobEffects.DIG_SLOWDOWN);
//        if (player.hasEffect(MobEffects.WEAKNESS)) player.removeEffect(MobEffects.WEAKNESS);
//    }
//}