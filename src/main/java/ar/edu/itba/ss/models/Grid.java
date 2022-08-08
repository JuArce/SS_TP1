package ar.edu.itba.ss.models;

import java.util.Comparator;
import java.util.List;

public class Grid {
    private final Cell[][] cells;
    private final int m;
    private final int l;

    public Grid(int sideLength, List<Particle> particles) {
        this.l = sideLength;
        this.m = (int)Math.floor(sideLength / (Particle.RC + (2.0 * particles.stream().map(Particle::getRadius).max(Comparator.naturalOrder()).orElseThrow())));
        this.cells = new Cell[m][m];
        this.setCells(particles);
    }

    private void setCells(List<Particle> particles) {
        particles.forEach(p -> {
            int x = (int)Math.floor(p.getX() / ((double)this.l / this.m));
            int y = (int)Math.floor(p.getY() / ((double)this.l / this.m));
            if (x < 0 || x >= m || y < 0 || y >= m) {
                return;
            }
            if (cells[x][y] == null) {
                cells[x][y] = new Cell();
            }
            cells[x][y].addParticle(p);
        });
    }


}
