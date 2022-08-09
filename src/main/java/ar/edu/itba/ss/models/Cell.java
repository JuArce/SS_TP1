package ar.edu.itba.ss.models;

import java.util.HashSet;
import java.util.Set;

public class Cell {
    private final Set<Particle> particles;

    public Cell() {
        this.particles = new HashSet<>();
    }

    public void addParticle(Particle particle) {
        this.particles.add(particle);
    }

    public Set<Particle> getParticles() {
        return particles;
    }
}
