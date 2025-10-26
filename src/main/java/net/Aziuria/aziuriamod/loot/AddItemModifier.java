package net.Aziuria.aziuriamod.loot;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

public class AddItemModifier extends LootModifier {
    public static final MapCodec<AddItemModifier> CODEC = RecordCodecBuilder.mapCodec(inst ->
            LootModifier.codecStart(inst).and(
                    BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(e -> e.item)
            ).apply(inst, AddItemModifier::new)
    );

    private final Item item;
    private final int minAmount;
    private final int maxAmount;

    /** Item constructor with default count 1 */
    public AddItemModifier(LootItemCondition[] conditionsIn, Item item) {
        this(conditionsIn, item, 1, 1);
    }

    /** Block constructor with default count 1 */
    public AddItemModifier(LootItemCondition[] conditionsIn, Block block) {
        this(conditionsIn, block.asItem(), 1, 1);
        if (block.asItem() == null) throw new IllegalArgumentException("Block " + block + " has no BlockItem!");
    }

    /** Item constructor with min/max amounts */
    public AddItemModifier(LootItemCondition[] conditionsIn, Item item, int minAmount, int maxAmount) {
        super(conditionsIn);
        this.item = item;
        this.minAmount = Math.max(1, minAmount);
        this.maxAmount = Math.max(this.minAmount, maxAmount);
    }

    /** Block constructor with min/max amounts */
    public AddItemModifier(LootItemCondition[] conditionsIn, Block block, int minAmount, int maxAmount) {
        this(conditionsIn, block.asItem(), minAmount, maxAmount);
        if (block.asItem() == null) throw new IllegalArgumentException("Block " + block + " has no BlockItem!");
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext lootContext) {
        for (LootItemCondition condition : this.conditions) {
            if (!condition.test(lootContext)) return generatedLoot;
        }
        int count = minAmount + lootContext.getRandom().nextInt(maxAmount - minAmount + 1);
        generatedLoot.add(new ItemStack(this.item, count));
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}