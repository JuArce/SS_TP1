package ar.edu.itba.ss.models;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Grid {
    private final Cell[][] cells;
    private final Set<Particle> particles;
    private final int m;
    private final int l;
    private final boolean isPeriodic;

    public Grid(int sideLength, List<Particle> particles, boolean isPeriodic) {
        this.l = sideLength;
        this.m = (int) Math.floor(sideLength / (Particle.RC + (2.0 * particles.stream().map(Particle::getRadius).max(Comparator.naturalOrder()).orElseThrow())));
        this.cells = new Cell[m][m];
        this.particles = new HashSet<>(particles);
        this.fillCells(this.particles);
        this.isPeriodic = isPeriodic;
    }

    private void fillCells(Set<Particle> particles) {
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
        return (int) Math.floor(particle.getPosition().getX() / ((double) l / m));
    }

    private int getYIndex(Particle particle) {
        return (int) Math.floor(particle.getPosition().getY() / ((double) l / m));
    }

    public void calculateNeighbours() {
        if (isPeriodic) {
            this.calculateNeighboursPeriodic();
        } else {
            this.calculateNeighboursSimple();
        }
    }

    private void calculateNeighboursSimple() {
        this.particles.forEach(p -> {
            int x = this.getXIndex(p);
            int y = this.getYIndex(p);

            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (i < 0 || i >= m || j < 0 || j >= m) {
                        continue;
                    }
                    if (cells[i][j] == null) {
                        continue;
                    }
                    p.setNeighbours(cells[i][j].getParticles());
                }
            }
        });
    }

    private void calculateNeighboursPeriodic() {
        this.particles.forEach(p -> {
            int x = this.getXIndex(p);
            int y = this.getYIndex(p);

            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    int mI = (i + m) % m;
                    int mJ = (j + m) % m;
                    if (cells[mI][mJ] == null) {
                        continue;
                    }

                    p.setNeighbours(cells[mI][mJ].getParticles());
                }
            }
        });
    }

    public Set<Particle> getParticles() {
        return particles;
    }
}
