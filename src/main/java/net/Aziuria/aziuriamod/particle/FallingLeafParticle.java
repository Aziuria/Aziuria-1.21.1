package net.Aziuria.aziuriamod.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class FallingLeafParticle extends TextureSheetParticle {
    private final SpriteSet spriteSet;

    protected FallingLeafParticle(ClientLevel level, double x, double y, double z,
                                  double xSpeed, double ySpeed, double zSpeed,
                                  SpriteSet spriteSet) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);
        this.spriteSet = spriteSet;

        this.friction = 0.85F;
        this.gravity = 0.05F;
        this.lifetime = 200 + level.random.nextInt(40); // 60–100 ticks

        this.xd = xSpeed;
        this.yd = ySpeed;
        this.zd = zSpeed;

        this.quadSize *= 0.5F + level.random.nextFloat(); // Randomize size
        this.setSpriteFromAge(spriteSet);

        this.rCol = 1.0F;
        this.gCol = 1.0F;
        this.bCol = 1.0F;
    }

    @Override
    public void tick() {
        super.tick();

        // Change sprite over time
        this.setSpriteFromAge(this.spriteSet);

        // Extra flutter (optional wobble)
        if (this.age % 2 == 0) {
            this.xd += (this.random.nextDouble() - 0.5) * 0.01;
            this.zd += (this.random.nextDouble() - 0.5) * 0.01;
        }

        if (this.onGround) {
            this.remove();
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    // The factory that creates the particle
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