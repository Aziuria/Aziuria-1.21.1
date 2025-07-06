package net.Aziuria.aziuriamod.fog.network;

import net.minecraft.resources.ResourceLocation;
import java.lang.reflect.Constructor;

public final class Identifier {
    private final String namespace;
    private final String path;

    public Identifier(String namespace, String path) {
        this.namespace = namespace;
        this.path = path;
    }

    public Identifier(String combined) {
        String[] parts = combined.split(":", 2);
        this.namespace = parts.length > 0 ? parts[0] : "minecraft";
        this.path = parts.length > 1 ? parts[1] : combined;
    }

    @Override
    public String toString() {
        return namespace + ":" + path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Identifier other)) return false;
        return namespace.equals(other.namespace) && path.equals(other.path);
    }

    @Override
    public int hashCode() {
        return 31 * namespace.hashCode() + path.hashCode();
    }

    // This converts to Minecraft's private ResourceLocation internally
    public ResourceLocation toResourceLocation() {
        try {
            Constructor<ResourceLocation> ctor = ResourceLocation.class.getDeclaredConstructor(String.class, String.class);
            ctor.setAccessible(true);
            return ctor.newInstance(namespace, path);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create ResourceLocation from Identifier", e);
        }
    }
}