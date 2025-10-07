package net.Aziuria.aziuriamod.datamaps;

import net.Aziuria.aziuriamod.block.ModBlocks;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles waxable, unwaxable and scrapeable block mappings (NeoForge 1.21.1 compatible)
 */
public class ModDataMapHooks {

    /** Waxable block pairs: block -> waxed version */
    public static final Map<Block, Block> WAXABLES = new HashMap<>();

    /** Unwaxable block pairs: waxed -> unwaxed version */
    public static final Map<Block, Block> UNWAXABLES = new HashMap<>();

    /** Scrapeable block pairs: block -> previous (less oxidized) version */
    public static final Map<Block, Block> SCRAPEABLES = new HashMap<>();

    /** Register all waxable and scrapeable mappings */
    public static void register() {
        // Waxable blocks
        WAXABLES.put(ModBlocks.COPPER_BARS.get(), ModBlocks.WAXED_COPPER_BARS.get());
        WAXABLES.put(ModBlocks.EXPOSED_COPPER_BARS.get(), ModBlocks.WAXED_EXPOSED_COPPER_BARS.get());
        WAXABLES.put(ModBlocks.WEATHERED_COPPER_BARS.get(), ModBlocks.WAXED_WEATHERED_COPPER_BARS.get());
        WAXABLES.put(ModBlocks.OXIDIZED_COPPER_BARS.get(), ModBlocks.WAXED_OXIDIZED_COPPER_BARS.get());

        // Unwaxable blocks (reverse of waxables)
        UNWAXABLES.put(ModBlocks.WAXED_COPPER_BARS.get(), ModBlocks.COPPER_BARS.get());
        UNWAXABLES.put(ModBlocks.WAXED_EXPOSED_COPPER_BARS.get(), ModBlocks.EXPOSED_COPPER_BARS.get());
        UNWAXABLES.put(ModBlocks.WAXED_WEATHERED_COPPER_BARS.get(), ModBlocks.WEATHERED_COPPER_BARS.get());
        UNWAXABLES.put(ModBlocks.WAXED_OXIDIZED_COPPER_BARS.get(), ModBlocks.OXIDIZED_COPPER_BARS.get());

        // Scrapeable blocks (axe interaction)
        SCRAPEABLES.put(ModBlocks.EXPOSED_COPPER_BARS.get(), ModBlocks.COPPER_BARS.get());
        SCRAPEABLES.put(ModBlocks.WEATHERED_COPPER_BARS.get(), ModBlocks.EXPOSED_COPPER_BARS.get());
        SCRAPEABLES.put(ModBlocks.OXIDIZED_COPPER_BARS.get(), ModBlocks.WEATHERED_COPPER_BARS.get());
    }

    public static Block getWaxed(Block block) {
        return WAXABLES.getOrDefault(block, block);
    }

    public static Block getUnwaxed(Block block) {
        return UNWAXABLES.getOrDefault(block, block);
    }

    public static Block getScraped(Block block) {
        return SCRAPEABLES.getOrDefault(block, block);
    }
}