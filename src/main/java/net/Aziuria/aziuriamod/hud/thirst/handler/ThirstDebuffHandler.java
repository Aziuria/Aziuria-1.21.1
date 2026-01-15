package net.Aziuria.aziuriamod.hud.thirst.handler;

import net.Aziuria.aziuriamod.client.damage.ModDamageSources;
import net.Aziuria.aziuriamod.hud.thirst.capability.IThirst;
import net.Aziuria.aziuriamod.hud.thirst.capability.ThirstProvider;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class ThirstDebuffHandler {

    private static final ResourceLocation SPEED_ID = ResourceLocation.fromNamespaceAndPath("aziuriamod", "thirst_speed_penalty");
    private static final ResourceLocation ATTACK_ID = ResourceLocation.fromNamespaceAndPath("aziuriamod", "thirst_attack_penalty");
    private static final ResourceLocation MINING_ID = ResourceLocation.fromNamespaceAndPath("aziuriamod", "thirst_mining_penalty");

    @SubscribeEvent
    public static void onPlayerTickPost(PlayerTickEvent.Post event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        IThirst thirst = player.getCapability(ThirstProvider.THIRST_CAP, null);
        if (thirst == null) return;

        int thirstLevel = thirst.getThirst();

        if (player.tickCount < 20 && thirstLevel == 0) return;

        if (thirstLevel > 10) {
            removeThirstModifiers(player);
            return;
        }

        player.setSprinting(false);

        double speedModifier, attackModifier, miningModifier;
        if (thirstLevel >= 6) {
            speedModifier = -0.1;
            attackModifier = -0.05;
            miningModifier = -0.1;
        } else if (thirstLevel >= 1) {
            speedModifier = -0.25;
            attackModifier = -0.15;
            miningModifier = -0.25;
        } else {
            speedModifier = -0.4;
            attackModifier = -0.25;
            miningModifier = -0.4;

            if (player.tickCount % 40 == 0) {
                ServerLevel level = (ServerLevel) player.getCommandSenderWorld();
                DamageSource dehydration = ModDamageSources.dehydration(level);
                player.hurt(dehydration, 1.0F);
                player.setDeltaMovement(0, player.getDeltaMovement().y, 0);
                player.hurtMarked = false;
            }
        }

        applyAttributeModifier(player, Attributes.MOVEMENT_SPEED, SPEED_ID, "Thirst Speed Penalty", speedModifier);
        applyAttributeModifier(player, Attributes.ATTACK_DAMAGE, ATTACK_ID, "Thirst Attack Penalty", attackModifier);
        applyAttributeModifier(player, Attributes.ATTACK_SPEED, MINING_ID, "Thirst Mining Penalty", miningModifier);
    }

    private static void applyAttributeModifier(ServerPlayer player, Holder<Attribute> attributeHolder, ResourceLocation id, String name, double amount) {
        AttributeInstance instance = player.getAttribute(attributeHolder);
        if (instance == null) return;

        AttributeModifier existing = instance.getModifier(id);
        if (existing != null) {
            instance.removeModifier(existing);
        }

        // Constructor now only takes 3 args: id, amount, operation
        AttributeModifier modifier = new AttributeModifier(id, amount, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        instance.addTransientModifier(modifier);
    }

    public static void removeThirstModifiers(ServerPlayer player) {
        removeModifier(player, Attributes.MOVEMENT_SPEED, SPEED_ID);
        removeModifier(player, Attributes.ATTACK_DAMAGE, ATTACK_ID);
        removeModifier(player, Attributes.ATTACK_SPEED, MINING_ID);
    }

    private static void removeModifier(ServerPlayer player, Holder<Attribute> attributeHolder, ResourceLocation id) {
        AttributeInstance instance = player.getAttribute(attributeHolder);
        if (instance == null) return;

        AttributeModifier modifier = instance.getModifier(id);
        if (modifier != null) {
            instance.removeModifier(modifier);
        }
    }
}