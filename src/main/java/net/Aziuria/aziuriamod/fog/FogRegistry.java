package net.Aziuria.aziuriamod.fog;

import java.util.ArrayList;
import java.util.List;

public class FogRegistry {
    private static final List<FogType> fogTypes = new ArrayList<>();

    public static void register(FogType type) {
        fogTypes.add(type);
    }

    public static List<FogType> getAll() {
        return fogTypes;
    }

    public static void init() {
        register(new BasicFogType());
        register(new EvilFogType());
    }
}