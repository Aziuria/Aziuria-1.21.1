package net.Aziuria.aziuriamod.water;


public class ClientWaterState {

    private static int waterLevel = 20;

    public static int getWaterLevel() {
        return waterLevel;
    }

    public static void setWaterLevel(int level) {
        waterLevel = Math.max(0, Math.min(20, level));
    }
}