import org.junit.jupiter.api.Test;

import java.awt.*;

import static com.google.common.truth.Truth.assertThat;

public class TestParticle {
    @Test
    public void testConstructor() {
        Particle fp = new Particle(ParticleFlavor.FIRE);
        assertThat(fp.flavor).isEqualTo(ParticleFlavor.FIRE);
        assertThat(fp.lifespan).isEqualTo(10);

        Particle sp = new Particle(ParticleFlavor.SAND);
        assertThat(sp.flavor).isEqualTo(ParticleFlavor.SAND);
        assertThat(sp.lifespan).isEqualTo(-1);
    }

    @Test
    public void testColor() {
        Particle emptyParticle = new Particle(ParticleFlavor.EMPTY);
        assertThat(emptyParticle.color()).isEqualTo(Color.BLACK);
        Particle sandParticle = new Particle(ParticleFlavor.SAND);
        assertThat(sandParticle.color()).isEqualTo(Color.YELLOW);
        Particle barrierParticle = new Particle(ParticleFlavor.BARRIER);
        assertThat(barrierParticle.color()).isEqualTo(Color.GRAY);
        Particle waterParticle = new Particle(ParticleFlavor.WATER);
        assertThat(waterParticle.color()).isEqualTo(Color.BLUE);
        Particle fountainParticle = new Particle(ParticleFlavor.FOUNTAIN);
        assertThat(fountainParticle.color()).isEqualTo(Color.CYAN);
        Particle plantParticle = new Particle(ParticleFlavor.PLANT);
        assertThat(plantParticle.color()).isEqualTo(new Color(0, 255, 0));
        Particle fireParticle = new Particle(ParticleFlavor.FIRE);
        assertThat(fireParticle.color()).isEqualTo(new Color(255, 0, 0));
        Particle flowerParticle = new Particle(ParticleFlavor.FLOWER);
        assertThat(flowerParticle.color()).isEqualTo(new Color(255, 141, 161));
    }
}