package net.Aziuria.aziuriamod.exhaustion.handler;

import net.Aziuria.aziuriamod.exhaustion.capability.Exhaustion;
import net.Aziuria.aziuriamod.exhaustion.capability.ExhaustionProvider;
import net.Aziuria.aziuriamod.exhaustion.capability.Iexhaustion;
import net.Aziuria.aziuriamod.fog.FogEventManager;
import net.Aziuria.aziuriamod.sounds.ModSounds;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.BedBlock;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.WeakHashMap;

public class ExhaustionDebuffHandler {

    private static final ResourceLocation SPEED_ID = ResourceLocation.fromNamespaceAndPath("aziuriamod", "exhaustion_speed_penalty");
    private static final ResourceLocation ATTACK_ID = ResourceLocation.fromNamespaceAndPath("aziuriamod", "exhaustion_attack_penalty");
    private static final ResourceLocation MINING_ID = ResourceLocation.fromNamespaceAndPath("aziuriamod", "exhaustion_mining_penalty");

    private static final float SLEEP_THRESHOLD_PERCENT = 40f;
    private static final float RECOVERY_PER_HOUR = 12.5f;

    // Tracks siren wake-ups
    private static final WeakHashMap<ServerPlayer, Boolean> sirenTriggered = new WeakHashMap<>();

    // Tracks when each player started sleeping
    private static final WeakHashMap<ServerPlayer, Long> sleepStartTick = new WeakHashMap<>();

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        Iexhaustion exhaustion = player.getCapability(ExhaustionProvider.EXHAUSTION_CAP, null);
        if (exhaustion == null) return;

        float level = exhaustion.getExhaustion();
        float percent = (level / Exhaustion.MAX_EXHAUSTION) * 100f;

        // Track sleep start tick
        if (player.isSleeping() && !sleepStartTick.containsKey(player)) {
            sleepStartTick.put(player, (long) player.tickCount);
        }

        // ✅ Apply debuffs only when exhausted (≤ 40%). Otherwise clear them.
        if (percent <= 40f) {
            applyExhaustionDebuffs(player, percent);
        } else {
            removeExhaustionModifiers(player);
        }

// Siren/fog wake-up only if a fog event is about to start
        if (player.isSleeping() && !sirenTriggered.containsKey(player)) {
            // Check fog manager for active or imminent fog
            if (FogEventManager.getActiveFog() != null) {
                player.stopSleeping();
                player.playNotifySound(ModSounds.SIREN.get(), player.getSoundSource(), 1.0f, 1.0f);
                player.displayClientMessage(
                        net.minecraft.network.chat.Component.literal("⚠ You were woken up by the fog siren!"), true
                );

                // Apply partial recovery based on slept hours
                applySleepRecovery(player);

                sirenTriggered.put(player, true);
            }
        }

        // Reset siren trigger when player is not sleeping
        if (!player.isSleeping() && sirenTriggered.containsKey(player)) {
            sirenTriggered.remove(player);
        }

        // If player wakes normally, apply recovery
        if (!player.isSleeping() && sleepStartTick.containsKey(player)) {
            applySleepRecovery(player);
            sleepStartTick.remove(player);
        }
    }

    private static void applySleepRecovery(ServerPlayer player) {
        Iexhaustion exhaustion = player.getCapability(ExhaustionProvider.EXHAUSTION_CAP, null);
        if (exhaustion == null) return;

        Long startTick = sleepStartTick.get(player);
        if (startTick == null) return;

        long sleptTicks = player.tickCount - startTick;
        float sleptHours = sleptTicks / 1200f; // 1200 ticks = 1 in-game hour

        // Subtract exhaustion instead of adding it
        float recoverAmount = Exhaustion.MAX_EXHAUSTION * (RECOVERY_PER_HOUR / 100f) * sleptHours;
        exhaustion.setExhaustion(Math.max(exhaustion.getExhaustion() - recoverAmount, 0));
    }

    private static void applyExhaustionDebuffs(ServerPlayer player, float percent) {
        double speedModifier, attackModifier, miningModifier;

        // ✅ Heavier penalties when extremely exhausted (≤ 10%), lighter otherwise (up to 40%)
        if (percent <= 10f) {
            // Heavy penalties
            speedModifier = -0.25;
            attackModifier = -0.15;
            miningModifier = -0.25;

            // Damage + stagger effect every in-game minute
            if (player.tickCount % 1200 == 0) {
                player.hurt(player.damageSources().starve(), 1.0F);
                player.setDeltaMovement(0, player.getDeltaMovement().y, 0);
                player.hurtMarked = false;
            }
        } else {
            // Light penalties (10%–40%)
            speedModifier = -0.1;
            attackModifier = -0.05;
            miningModifier = -0.1;
        }

        player.setSprinting(false);
        applyAttributeModifier(player, Attributes.MOVEMENT_SPEED, SPEED_ID, speedModifier);
        applyAttributeModifier(player, Attributes.ATTACK_DAMAGE, ATTACK_ID, attackModifier);
        applyAttributeModifier(player, Attributes.ATTACK_SPEED, MINING_ID, miningModifier);
    }

    private static void applyAttributeModifier(ServerPlayer player, Holder<Attribute> attributeHolder, ResourceLocation id, double amount) {
        AttributeInstance instance = player.getAttribute(attributeHolder);
        if (instance == null) return;

        AttributeModifier existing = instance.getModifier(id);
        if (existing != null) instance.removeModifier(existing);

        AttributeModifier modifier = new AttributeModifier(id, amount, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        instance.addTransientModifier(modifier);
    }

    public static void removeExhaustionModifiers(ServerPlayer player) {
        removeModifier(player, Attributes.MOVEMENT_SPEED, SPEED_ID);
        removeModifier(player, Attributes.ATTACK_DAMAGE, ATTACK_ID);
        removeModifier(player, Attributes.ATTACK_SPEED, MINING_ID);
    }

    private static void removeModifier(ServerPlayer player, Holder<Attribute> attributeHolder, ResourceLocation id) {
        AttributeInstance instance = player.getAttribute(attributeHolder);
        if (instance == null) return;

        AttributeModifier modifier = instance.getModifier(id);
        if (modifier != null) instance.removeModifier(modifier);
    }

    @SubscribeEvent
    public static void onPlayerSleepAttempt(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        if (!(player instanceof ServerPlayer serverPlayer)) return;

        // Accept all bed colors
        if (!(player.level().getBlockState(event.getPos()).getBlock() instanceof BedBlock)) return;

        Iexhaustion exhaustion = player.getCapability(ExhaustionProvider.EXHAUSTION_CAP, null);
        if (exhaustion == null) return;

        float percent = (exhaustion.getExhaustion() / Exhaustion.MAX_EXHAUSTION) * 100f;
        boolean isStorming = player.level().isRaining() || player.level().isThundering();

        if (percent > SLEEP_THRESHOLD_PERCENT) {
            if (isStorming) {
                serverPlayer.displayClientMessage(
                        net.minecraft.network.chat.Component.literal("You are not exhausted enough to sleep during a storm!"), true
                );
            } else {
                serverPlayer.displayClientMessage(
                        net.minecraft.network.chat.Component.literal("You are not ready to sleep yet!"), true
                );
            }
            event.setCanceled(true);
        }
    }
}