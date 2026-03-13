import edu.princeton.cs.algs4.StdRandom;

import java.util.Map;
import java.awt.Color;

public class Particle {
    public static final int PLANT_LIFESPAN = 150;
    public static final int FLOWER_LIFESPAN = 75;
    public static final int FIRE_LIFESPAN = 10;
    public static final Map<ParticleFlavor, Integer> LIFESPANS =
            Map.of(ParticleFlavor.FLOWER, FLOWER_LIFESPAN,
                    ParticleFlavor.PLANT, PLANT_LIFESPAN,
                    ParticleFlavor.FIRE, FIRE_LIFESPAN);


    ParticleFlavor flavor;
    int lifespan;

    public Particle(ParticleFlavor flavor) {
        this.flavor = flavor;
        this.lifespan = LIFESPANS.getOrDefault(flavor, -1);
    }

    public Color color(){
        if (flavor == ParticleFlavor.FLOWER) {
            double ratio = (double) Math.max(0, Math.min(lifespan, FLOWER_LIFESPAN)) / FLOWER_LIFESPAN;
            int r = 120 + (int) Math.round((255 - 120) * ratio);
            int g = 70 + (int) Math.round((141 - 70) * ratio);
            int b = 80 + (int) Math.round((161 - 80) * ratio);
            return new Color(r, g, b);
        }
        if (flavor == ParticleFlavor.PLANT) {
            double ratio = (double) Math.max(0, Math.min(lifespan, PLANT_LIFESPAN)) / PLANT_LIFESPAN;
            int g = 120 + (int) Math.round((255 - 120) * ratio);
            return new Color(0, g, 0);
        }
        if (flavor == ParticleFlavor.FIRE) {
            double ratio = (double) Math.max(0, Math.min(lifespan, FIRE_LIFESPAN)) / FIRE_LIFESPAN;
            int r = (int) Math.round(255 * ratio);
            return new Color(r, 0, 0);
        }
        if (flavor == ParticleFlavor.SAND) {
            return Color.YELLOW;
        }
        if (flavor == ParticleFlavor.BARRIER) {
            return Color.GRAY;
        }
        if (flavor == ParticleFlavor.WATER) {
            return Color.BLUE;
        }
        if (flavor == ParticleFlavor.FOUNTAIN) {
            return Color.CYAN;
        }
        return Color.BLACK;
    }

    public void moveInto(Particle other){
        other.flavor = flavor;
        other.lifespan = lifespan;
        flavor = ParticleFlavor.EMPTY;
        lifespan = -1;
    }

    public void fall(Map<Direction, Particle> neighbors){
        if (neighbors.get(Direction.DOWN).flavor == ParticleFlavor.EMPTY) {
            this.moveInto(neighbors.get(Direction.DOWN));
        }
    }

    public void action(Map<Direction, Particle> neighbors){
        if (this.flavor == ParticleFlavor.EMPTY) {
            return;
        }
        if (this.flavor != ParticleFlavor.BARRIER){
            this.fall(neighbors);
        }
        if (this.flavor == ParticleFlavor.WATER){
            this.flow(neighbors);
        }
        if (this.flavor == ParticleFlavor.PLANT||this.flavor == ParticleFlavor.FLOWER){
            this.grow(neighbors);
        }
        if (this.flavor == ParticleFlavor.FIRE){
            this.burn(neighbors);
        }
    }

    public void flow(Map<Direction, Particle> neighbors){
        int x = StdRandom.uniformInt(3);

        if (x == 0){
            return;
        }else if (x == 1){
            if (neighbors.get(Direction.LEFT).flavor == ParticleFlavor.EMPTY){
                this.moveInto(neighbors.get(Direction.LEFT));
            }
        }else if (x == 2){
            if (neighbors.get(Direction.RIGHT).flavor == ParticleFlavor.EMPTY){
                this.moveInto(neighbors.get(Direction.RIGHT));
            }
        }
    }

    public void becomeSame(Particle other){
        other.flavor = this.flavor;
        other.lifespan = LIFESPANS.getOrDefault(this.flavor, -1);
    }

    public void grow(Map<Direction, Particle> neighbors){
        int x = StdRandom.uniformInt(10);
        if (x == 0){
            if (neighbors.get(Direction.UP).flavor == ParticleFlavor.EMPTY){
                this.becomeSame(neighbors.get(Direction.UP));
            }
        }else if (x == 1){
            if (neighbors.get(Direction.LEFT).flavor == ParticleFlavor.EMPTY){
                this.becomeSame(neighbors.get(Direction.LEFT));
            }
        }else if (x == 2){
            if (neighbors.get(Direction.RIGHT).flavor == ParticleFlavor.EMPTY){
                this.becomeSame(neighbors.get(Direction.RIGHT));
            }
        }
    }

    public void burn(Map<Direction, Particle> neighbors){
        if (neighbors.get(Direction.UP).flavor == ParticleFlavor.PLANT||neighbors.get(Direction.RIGHT).flavor == ParticleFlavor.FLOWER){
            int x = StdRandom.uniformInt(10);
            if (x < 5){
                this.becomeSame(neighbors.get(Direction.UP));
            }
        }
        if (neighbors.get(Direction.LEFT).flavor == ParticleFlavor.PLANT||neighbors.get(Direction.RIGHT).flavor == ParticleFlavor.FLOWER){
            int x = StdRandom.uniformInt(10);
            if (x < 5){
                this.becomeSame(neighbors.get(Direction.LEFT));
            }
        }
        if (neighbors.get(Direction.RIGHT).flavor == ParticleFlavor.PLANT||neighbors.get(Direction.RIGHT).flavor == ParticleFlavor.FLOWER){
            int x = StdRandom.uniformInt(10);
            if (x < 5){
                this.becomeSame(neighbors.get(Direction.RIGHT));
            }
        }
        if (neighbors.get(Direction.DOWN).flavor == ParticleFlavor.PLANT||neighbors.get(Direction.RIGHT).flavor == ParticleFlavor.FLOWER){
            int x = StdRandom.uniformInt(10);
            if (x < 5){
                this.becomeSame(neighbors.get(Direction.DOWN));
            }
        }
    }

    public void decrementLifespan(){
        if (lifespan == -1){
            return;
        }else if (lifespan == 0){
            lifespan = -1;
            flavor = ParticleFlavor.EMPTY;
        }else{
            lifespan--;

        }
    }

}

