package net.Aziuria.aziuriamod.loot;

import com.mojang.serialization.Codec;
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

    private final Item item;
    private final int minAmount;
    private final int maxAmount;

    public Item getItem() { return item; }
    public int getMinAmount() { return minAmount; }
    public int getMaxAmount() { return maxAmount; }

    // ✅ Proper NeoForge 1.21.1 codec composition using .and()
    public static final MapCodec<AddItemModifier> CODEC = RecordCodecBuilder.mapCodec(inst ->
            LootModifier.codecStart(inst)
                    .and(BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(AddItemModifier::getItem))
                    .and(Codec.INT.fieldOf("minAmount").forGetter(AddItemModifier::getMinAmount))
                    .and(Codec.INT.fieldOf("maxAmount").forGetter(AddItemModifier::getMaxAmount))
                    .apply(inst, AddItemModifier::new)
    );

    /** Main constructor for codec and internal calls */
    public AddItemModifier(LootItemCondition[] conditionsIn, Item item, int minAmount, int maxAmount) {
        super(conditionsIn);
        this.item = item;
        this.minAmount = Math.max(1, minAmount);
        this.maxAmount = Math.max(this.minAmount, maxAmount);
    }

    /** Helper constructor for items (default 1) */
    public AddItemModifier(LootItemCondition[] conditionsIn, Item item) {
        this(conditionsIn, item, 1, 1);
    }

    /** Helper constructor for blocks (default 1) */
    public AddItemModifier(LootItemCondition[] conditionsIn, Block block) {
        this(conditionsIn, getSafeItem(block), 1, 1);
    }

    /** Helper constructor for blocks with min/max */
    public AddItemModifier(LootItemCondition[] conditionsIn, Block block, int minAmount, int maxAmount) {
        this(conditionsIn, getSafeItem(block), minAmount, maxAmount);
    }

    private static Item getSafeItem(Block block) {
        Item item = block.asItem();
        if (item == null) throw new IllegalArgumentException("Block " + block + " has no BlockItem!");
        return item;
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