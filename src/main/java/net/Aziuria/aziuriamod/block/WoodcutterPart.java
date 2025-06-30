package net.Aziuria.aziuriamod.block;

import net.minecraft.util.StringRepresentable;

public enum WoodcutterPart implements StringRepresentable {
    LEFT("left"),
    RIGHT("right");

    private final String name;

    WoodcutterPart(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}