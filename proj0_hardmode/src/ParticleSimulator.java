import edu.princeton.cs.algs4.StdDraw;

import java.util.HashMap;
import java.util.Map;

public class ParticleSimulator {
    public Particle[][] particles;
    int width, height;

    public static final Map<Character, ParticleFlavor> LETTER_TO_PARTICLE = Map.of(
            's', ParticleFlavor.SAND,
            'b', ParticleFlavor.BARRIER,
            'w', ParticleFlavor.WATER,
            'p', ParticleFlavor.PLANT,
            'f', ParticleFlavor.FIRE,
            '.', ParticleFlavor.EMPTY,
            'n', ParticleFlavor.FOUNTAIN,
            'r', ParticleFlavor.FLOWER
    );

    ParticleSimulator(int width, int height){
        this.height = height;
        this.width = width;
        this.particles = new Particle[width][height];
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                this.particles[i][j] = new Particle(ParticleFlavor.EMPTY);
            }
        }
    }

    public void drawParticles() {
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                StdDraw.setPenColor(particles[x][y].color());
                StdDraw.filledSquare(x, y, 0.5);
            }
        }
    }

    public boolean validIndex(int x, int y){
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public Map<Direction, Particle> getNeighbors(int x, int y){
        Map<Direction, Particle> neighbors = new HashMap<>();

        if (validIndex(x, y+1)){
            neighbors.put(Direction.UP, particles[x][y+1]);
        }else{
            neighbors.put(Direction.UP, new Particle(ParticleFlavor.BARRIER));
        }

        if (validIndex(x, y-1)){
            neighbors.put(Direction.DOWN, particles[x][y-1]);
        }else {
            neighbors.put(Direction.DOWN, new Particle(ParticleFlavor.BARRIER));
        }

        if (validIndex(x-1, y)){
            neighbors.put(Direction.LEFT, particles[x-1][y]);
        }else {
            neighbors.put(Direction.LEFT, new Particle(ParticleFlavor.BARRIER));
        }

        if (validIndex(x+1, y)){
            neighbors.put(Direction.RIGHT, particles[x+1][y]);
        }else {
            neighbors.put(Direction.RIGHT, new Particle(ParticleFlavor.BARRIER));
        }

        return neighbors;

    }

//    public void tick(){
//        for(int x = 0; x < width; x += 1) {
//            for(int y = 0; y < height; y += 1) {
//               return;
//            }
//        }
//    }

    static void main() {
        ParticleSimulator particleSimulator = new ParticleSimulator(150, 150);
        StdDraw.setXscale(0, particleSimulator.width);
        StdDraw.setYscale(0, particleSimulator.height);
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.BLACK);
        ParticleFlavor nextParticleFlavor = ParticleFlavor.SAND;

        while (true) {
            if (StdDraw.hasNextKeyTyped()){
                nextParticleFlavor = LETTER_TO_PARTICLE.get(StdDraw.nextKeyTyped());
            }

            if (StdDraw.isMousePressed()) {
                int x = (int) StdDraw.mouseX();
                int y = (int) StdDraw.mouseY();
                if (particleSimulator.validIndex(x, y)) {
                    particleSimulator.particles[x][y] = new Particle(nextParticleFlavor);
                }
            }

            particleSimulator.drawParticles();
            StdDraw.show();
            StdDraw.pause(5);
        }
    }
}
