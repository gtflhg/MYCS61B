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
    public static final Map<ParticleFlavor, Color> COLORS =
            Map.of(ParticleFlavor.EMPTY, Color.BLACK,
                    ParticleFlavor.SAND, Color.YELLOW,
                    ParticleFlavor.BARRIER, Color.GRAY,
                    ParticleFlavor.WATER, Color.BLUE,
                    ParticleFlavor.FOUNTAIN, Color.CYAN,
                    ParticleFlavor.PLANT, new Color(0, 255, 0),
                    ParticleFlavor.FIRE, new Color(255, 0, 0),
                    ParticleFlavor.FLOWER, new Color(255, 141, 161)
            );

    ParticleFlavor flavor;
    int lifespan;

    public Particle(ParticleFlavor flavor) {
        this.flavor = flavor;
        this.lifespan = LIFESPANS.getOrDefault(flavor, -1);
    }

    public Color color(){
        return COLORS.get(flavor);
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
}