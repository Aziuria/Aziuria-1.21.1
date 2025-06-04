package net.Aziuria.aziuriamod.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class FallingLeafParticle extends TextureSheetParticle {
    private final SpriteSet spriteSet;

    protected FallingLeafParticle(ClientLevel level, double x, double y, double z,
                                  double xSpeed, double ySpeed, double zSpeed,
                                  SpriteSet spriteSet) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);
        this.spriteSet = spriteSet;

        this.friction = 0.85F;
        this.gravity = 0.08F;
        this.lifetime = 200 + level.random.nextInt(40); // 200–240 ticks

        this.xd = xSpeed;
        this.yd = ySpeed;
        this.zd = zSpeed;

        this.quadSize *= 0.4F + level.random.nextFloat() * 0.2F; // Randomize size
        this.setSpriteFromAge(spriteSet);

        // Default to white, you can override with setColor(r, g, b)
        this.rCol = 1.0f;
        this.gCol = 1.0f;
        this.bCol = 1.0f;
    }

    // Allow setting color
    public void setColor(float r, float g, float b) {
        this.rCol = r;
        this.gCol = g;
        this.bCol = b;
    }

    @Override
    public void tick() {
        super.tick();

        // Update sprite as particle ages
        this.setSpriteFromAge(this.spriteSet);

        // Flutter / sway effect
        if (this.age % 2 == 0) {
            double swayX = Math.sin(this.age * 0.3) * 0.02;
            double swayZ = Math.cos(this.age * 0.3) * 0.02;
            this.xd += swayX * 0.2;
            this.zd += swayZ;

            // Optional slight rotation for realism
            this.roll += 0.02f;
            this.oRoll = this.roll;
        }

        // Remove particle on ground
        if (this.onGround) {
            this.remove();
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    // Particle factory/provider class
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level,
                                       double x, double y, double z,
                                       double xSpeed, double ySpeed, double zSpeed) {
            return new FallingLeafParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, spriteSet);
        }
    }
}