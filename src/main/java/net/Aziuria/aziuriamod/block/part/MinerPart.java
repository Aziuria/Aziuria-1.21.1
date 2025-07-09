package net.Aziuria.aziuriamod.block.part;

import net.minecraft.util.StringRepresentable;

public enum MinerPart implements StringRepresentable {
    LEFT("left"),
    RIGHT("right");

    private final String name;

    MinerPart(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}