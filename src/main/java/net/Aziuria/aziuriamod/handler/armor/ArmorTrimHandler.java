package net.Aziuria.aziuriamod.handler.armor;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.armortrim.ArmorTrim;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SculkSensorBlock;
import net.minecraft.world.level.block.SculkShriekerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.VanillaGameEvent;
import net.neoforged.neoforge.event.entity.EntityStruckByLightningEvent;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.entity.player.PlayerXpEvent;

import java.util.List;
import java.util.Optional;

public class ArmorTrimHandler {

    private static final String DIAMOND_LAST_STAND_TAG = "aziuria_diamond_last_stand_used";

    /* ========================================================= */
    /* ===================== TRIM DETECTION ==================== */
    /* ========================================================= */

    private static Optional<Holder<TrimMaterial>> getFullSetTrim(Player player) {
        ItemStack head = player.getInventory().getArmor(3);
        ItemStack chest = player.getInventory().getArmor(2);
        ItemStack legs = player.getInventory().getArmor(1);
        ItemStack feet = player.getInventory().getArmor(0);

        if (head.isEmpty() || chest.isEmpty() || legs.isEmpty() || feet.isEmpty()) {
            return Optional.empty();
        }

        ArmorTrim tHead = head.get(DataComponents.TRIM);
        ArmorTrim tChest = chest.get(DataComponents.TRIM);
        ArmorTrim tLegs = legs.get(DataComponents.TRIM);
        ArmorTrim tFeet = feet.get(DataComponents.TRIM);

        if (tHead == null || tChest == null || tLegs == null || tFeet == null) {
            return Optional.empty();
        }

        Holder<TrimMaterial> mat = tHead.material();

        if (!tChest.material().equals(mat) ||
                !tLegs.material().equals(mat) ||
                !tFeet.material().equals(mat)) {
            return Optional.empty();
        }

        return Optional.of(mat);
    }

    /* ========================================================= */
    /* ==================== COPPER – CONDUCTOR ================= */
    /* ========================================================= */

    @SubscribeEvent
    public static void onLightning(EntityStruckByLightningEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!(player.level() instanceof ServerLevel level)) return;

        Optional<Holder<TrimMaterial>> trim = getFullSetTrim(player);
        // ✅ Compare by assetName
        if (trim.isEmpty() || !trim.get().value().assetName().equals("copper")) return;

        // Reduced lightning damage
        player.hurt(event.getLightning().damageSources().lightningBolt(), 1.0F);
        event.setCanceled(true);

        // Temporary buffs
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 0));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600, 0));

        // Power nearby lamps/bulbs briefly
        BlockPos center = player.blockPosition();
        for (BlockPos pos : BlockPos.betweenClosed(center.offset(-6, -2, -6), center.offset(6, 2, 6))) {
            if (level.getBlockState(pos).is(Blocks.REDSTONE_LAMP)) {
                level.setBlock(pos, Blocks.REDSTONE_LAMP.defaultBlockState().setValue(net.minecraft.world.level.block.RedstoneLampBlock.LIT, true), 3);
            }
        }
    }

    /* ========================================================= */
    /* ==================== IRON – DEFLECTOR =================== */
    /* ========================================================= */

    @SubscribeEvent
    public static void onProjectileHit(ProjectileImpactEvent event) {
        Entity hit = event.getEntity();
        if (!(event.getRayTraceResult() instanceof net.minecraft.world.phys.EntityHitResult ehr)) return;
        if (!(ehr.getEntity() instanceof Player player)) return;

        Optional<Holder<TrimMaterial>> trim = getFullSetTrim(player);
        // ✅ Compare by assetName
        if (trim.isEmpty() || !trim.get().value().assetName().equals("iron")) return;

        if (player.getRandom().nextFloat() < 0.5F) {
            event.setCanceled(true);
        }
    }

    /* ========================================================= */
    /* ================== DIAMOND – LAST STAND ================= */
    /* ========================================================= */

    @SubscribeEvent
    public static void onFatalDamage(LivingDamageEvent.Pre event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!(player.level() instanceof ServerLevel level)) return;

        // Check for full Diamond trim
        Optional<Holder<TrimMaterial>> trim = getFullSetTrim(player);
        if (trim.isEmpty() || !trim.get().value().assetName().equals("diamond")) return;

        // Persistent NBT to prevent multiple triggers
        CompoundTag tag = player.getPersistentData();
        if (tag.getBoolean(DIAMOND_LAST_STAND_TAG)) return;

        // Only trigger if damage would be fatal
        if (event.getNewDamage() >= player.getHealth()) {
            event.setNewDamage(0f); // cancel fatal damage
            tag.putBoolean(DIAMOND_LAST_STAND_TAG, true);

            // Heal player like a Totem (50% HP)
            player.setHealth(player.getMaxHealth() * 0.5F);

            // Break all armor properly using hurtAndBreak
            EquipmentSlot[] slots = {EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
            for (EquipmentSlot slot : slots) {
                ItemStack stack = player.getItemBySlot(slot);
                if (!stack.isEmpty() && stack.isDamageableItem()) {
                    // Use NeoForge-compatible method
                    stack.hurtAndBreak(stack.getMaxDamage(), player, slot);
                }
            }

            // Knock back all nearby hostile mobs and players within 6-block horizontal radius
            List<LivingEntity> entities = level.getEntitiesOfClass(
                    LivingEntity.class,
                    player.getBoundingBox().inflate(6, 2, 6),
                    e -> e != player // exclude self
            );

            for (LivingEntity ent : entities) {
                // Only shove hostile mobs or other players
                if (!(ent instanceof Mob) && !(ent instanceof Player)) continue;

                // Compute normalized horizontal direction away from player
                double dx = ent.getX() - player.getX();
                double dz = ent.getZ() - player.getZ();
                double dist = Math.sqrt(dx * dx + dz * dz);
                if (dist == 0) dist = 0.1; // prevent divide by zero
                double nx = dx / dist;
                double nz = dz / dist;

                // Apply strong shove in one instant
                double shoveStrength = 1.8; // tweak as needed
                ent.setDeltaMovement(nx * shoveStrength, 0.5, nz * shoveStrength);
                ent.hurtMarked = true; // force immediate physics update

                // Apply short Slowness effect to stagger
                ent.addEffect(new MobEffectInstance(
                        MobEffects.MOVEMENT_SLOWDOWN,
                        100,  // 5 seconds
                        1,    // amplifier
                        false, // hide particles
                        true   // show icon
                ));
            }
        }
    }

    /* ========================================================= */
    /* ================= NETHERITE – ANCHOR ==================== */
    /* ========================================================= */

    @SubscribeEvent
    public static void onKnockback(LivingKnockBackEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        Optional<Holder<TrimMaterial>> trim = getFullSetTrim(player);
        // ✅ Compare by assetName
        if (trim.isEmpty() || !trim.get().value().assetName().equals("netherite")) return;

        event.setCanceled(true);
    }

    /* ========================================================= */
    /* ================== LAPIS – SCHOLAR ====================== */
    /* ========================================================= */

    @SubscribeEvent
    public static void onXpGain(PlayerXpEvent.XpChange event) {
        Player player = event.getEntity();
        Optional<Holder<TrimMaterial>> trim = getFullSetTrim(player);
        // ✅ Compare by assetName
        if (trim.isEmpty() || !trim.get().value().assetName().equals("lapis")) return;

        event.setAmount((int)(event.getAmount() * 1.5F));
    }

    /* ========================================================= */
    /* ====================== GOLD – BARTER =================== */
    /* ========================================================= */

    @SubscribeEvent
    public static void onPiglinChangeTarget(LivingChangeTargetEvent event) {
        LivingEntity entity = event.getEntity();

        // Only care about Piglins
        if (!(entity instanceof Piglin piglin)) return;

        LivingEntity newTarget = event.getNewAboutToBeSetTarget(); // ✅ correct method

        if (!(newTarget instanceof Player player)) return;

        Optional<Holder<TrimMaterial>> trim = getFullSetTrim(player);
        if (trim.isPresent() && trim.get().value().assetName().equals("gold")) {
            // Cancel aggression: prevent piglin from targeting player
            event.setCanceled(true);

        }
    }

    /* ========================================================= */
    /* ====================== QUARTZ – ENHANCER ================= */
    /* ========================================================= */

    private static final ThreadLocal<Boolean> QUARTZ_RECURSION = ThreadLocal.withInitial(() -> false);

    @SubscribeEvent
    public static void onEffectAdded(MobEffectEvent.Added event) {
        // Prevent recursion
        if (QUARTZ_RECURSION.get()) return;

        if (!(event.getEntity() instanceof Player player)) return;

        Optional<Holder<TrimMaterial>> trim = getFullSetTrim(player);
        if (trim.isEmpty() || !trim.get().value().assetName().equals("quartz")) return;

        MobEffectInstance oldEffect = event.getEffectInstance();
        if (oldEffect == null || oldEffect.getEffect() == null) return;

        // Only modify beneficial effects
        if (!oldEffect.getEffect().value().isBeneficial()) return;

        // Begin recursion guard
        QUARTZ_RECURSION.set(true);

        // Directly add a new effect with +1 amplifier
        MobEffectInstance boosted = new MobEffectInstance(
                oldEffect.getEffect(),
                oldEffect.getDuration(),
                oldEffect.getAmplifier() + 1,  // guaranteed +1
                oldEffect.isAmbient(),
                oldEffect.isVisible(),
                oldEffect.showIcon()
        );

        // Apply the boosted effect
        player.addEffect(boosted);

        // End recursion guard
        QUARTZ_RECURSION.set(false);
    }

    /* ========================================================= */
    /* ====================== EMERALD – LUCKY ================== */
    /* ========================================================= */
    @SubscribeEvent
    public static void onEmeraldLoot(LivingDropsEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;

        Optional<Holder<TrimMaterial>> trim = getFullSetTrim(player);
        if (trim.isEmpty() || !trim.get().value().assetName().equals("emerald")) return;

        // Looting I: 50% chance to duplicate each drop
        for (ItemEntity drop : event.getDrops()) {
            if (player.getRandom().nextFloat() < 0.5f) { // 50% chance
                ItemStack extra = drop.getItem().copy();
                BlockPos pos = drop.blockPosition();
                drop.level().addFreshEntity(new ItemEntity(drop.level(), pos.getX(), pos.getY(), pos.getZ(), extra));
            }
        }
    }

    /* ========================================================= */
    /* ===================== AMETHYST – SILENT ================= */
    /* ========================================================= */
    @SubscribeEvent
    public static void onVanillaGameEvent(VanillaGameEvent event) {
        // Only care if source is a player
        Entity source = event.getCause();
        if (!(source instanceof Player player)) return;

        // Check for full Amethyst trim
        Optional<Holder<TrimMaterial>> trim = getFullSetTrim(player);
        if (trim.isEmpty() || !trim.get().value().assetName().equals("amethyst")) return;

        Level level = event.getLevel();
        Vec3 posVec = event.getEventPosition();
        BlockPos pos = new BlockPos(
                Mth.floor(posVec.x),
                Mth.floor(posVec.y),
                Mth.floor(posVec.z)
        );

        BlockState state = level.getBlockState(pos);

        // Cancel vibrations for Sculk Sensor and Sculk Shrieker only
        if (state.getBlock() instanceof SculkSensorBlock || state.getBlock() instanceof SculkShriekerBlock) {
            event.setCanceled(true); // suppress the vibration
        }
    }
}