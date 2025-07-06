package net.Aziuria.aziuriamod.fog.network;

public class ClientFogState {

    private static String activeFogId = "";
    private static int currentIntensityOrdinal = 0;
    private static long fogStart = 0L;
    private static long fogEnd = 0L;
    private static long fogFadeInEnd = 0L;
    private static long fogFadeOutStart = 0L;

    public static void setFogState(String id, int intensity, long start, long end, long fadeIn, long fadeOut) {
        activeFogId = id;
        currentIntensityOrdinal = intensity;
        fogStart = start;
        fogEnd = end;
        fogFadeInEnd = fadeIn;
        fogFadeOutStart = fadeOut;
    }

    public static String getActiveFogId() {
        return activeFogId;
    }

    public static int getCurrentIntensityOrdinal() {
        return currentIntensityOrdinal;
    }

    public static long getFogStart() {
        return fogStart;
    }

    public static long getFogEnd() {
        return fogEnd;
    }

    public static long getFogFadeInEnd() {
        return fogFadeInEnd;
    }

    public static long getFogFadeOutStart() {
        return fogFadeOutStart;
    }
}