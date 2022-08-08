package ar.edu.itba.ss.models;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private final List<Particle> particles;

    public Cell() {
        this.particles = new ArrayList<>();
    }

    public void addParticle(Particle particle) {
        this.particles.add(particle);
    }

    public List<Particle> getParticles() {
        return particles;
    }
}
