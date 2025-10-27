package net.Aziuria.aziuriamod.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

public class RemoveItemModifier extends LootModifier {

    private final Item targetItem;

    public Item getTargetItem() { return targetItem; }

    // ✅ Proper NeoForge codec for global loot modifier
    public static final MapCodec<RemoveItemModifier> CODEC = RecordCodecBuilder.mapCodec(inst ->
            LootModifier.codecStart(inst)
                    .and(BuiltInRegistries.ITEM.byNameCodec().fieldOf("target_item").forGetter(RemoveItemModifier::getTargetItem))
                    .apply(inst, RemoveItemModifier::new)
    );

    /**
     * Main constructor used by codec and internal logic.
     */
    public RemoveItemModifier(LootItemCondition[] conditionsIn, Item targetItem) {
        super(conditionsIn);
        this.targetItem = targetItem;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext lootContext) {
        for (LootItemCondition condition : this.conditions) {
            if (!condition.test(lootContext)) return generatedLoot;
        }

        // Remove all stacks of the target item from the loot
        generatedLoot.removeIf(stack -> stack.getItem() == this.targetItem);
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}