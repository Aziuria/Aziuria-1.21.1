package net.Aziuria.aziuriamod.block.world;

import net.minecraft.util.RandomSource;

public enum IslandType {
    SMALL(15, 50, 15, 50, 5, IslandBiomeType.PLAINS),
    MEDIUM(100, 175, 100, 175, 5, IslandBiomeType.FOREST),
    LARGE(300, 475, 300, 475, 5, IslandBiomeType.SWAMP);

    private final int minWidth;
    private final int maxWidth;
    private final int minLength;
    private final int maxLength;
    private final int maxHeight;
    private final IslandBiomeType defaultBiome;

    IslandType(int minWidth, int maxWidth, int minLength, int maxLength, int maxHeight, IslandBiomeType defaultBiome) {
        this.minWidth = minWidth;
        this.maxWidth = maxWidth;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.maxHeight = maxHeight;
        this.defaultBiome = defaultBiome;
    }

    public int getRandomWidth(RandomSource random) {
        return minWidth + random.nextInt(maxWidth - minWidth + 1);
    }

    public int getRandomLength(RandomSource random) {
        return minLength + random.nextInt(maxLength - minLength + 1);
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public IslandBiomeType getDefaultBiome() {
        return defaultBiome;  // <-- Return the field
    }
}