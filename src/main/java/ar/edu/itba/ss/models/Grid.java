package ar.edu.itba.ss.models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Grid {
    private final Cell[][] cells;
    private final List<Particle> particles;
    private final int m;
    private final int l;

    public Grid(int sideLength, List<Particle> particles) {
        this.l = sideLength;
        this.m = (int)Math.floor(sideLength / (Particle.RC + (2.0 * particles.stream().map(Particle::getRadius).max(Comparator.naturalOrder()).orElseThrow())));
        this.cells = new Cell[m][m];
        this.particles = particles;
        this.fillCells(particles);
    }

    private void fillCells(List<Particle> particles) {
        particles.forEach(p -> {
            int x = this.getXIndex(p);
            int y = this.getYIndex(p);
            if (x < 0 || x >= m || y < 0 || y >= m) {
                return;
            }
            if (cells[x][y] == null) {
                cells[x][y] = new Cell();
            }
            cells[x][y].addParticle(p);
        });
    }

    private int getXIndex(Particle particle) {
        return (int)Math.floor(particle.getX() / ((double)this.l / this.m));
    }

    private int getYIndex(Particle particle) {
        return (int)Math.floor(particle.getY() / ((double)this.l / this.m));
    }

    public void calculateNeighbours() {
        this.particles.forEach(p -> {
            int x = this.getXIndex(p);
            int y = this.getYIndex(p);

            List<Particle> candidates = new ArrayList<>();
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (i < 0 || i >= m || j < 0 || j >= m) {
                        continue;
                    }
                    if (cells[i][j] == null) {
                        continue;
                    }
                    candidates.addAll(cells[i][j].getParticles());
                }
            }
            p.setNeighbours(candidates);
        });
    }

    public List<Particle> getParticles() {
        return particles;
    }
}
