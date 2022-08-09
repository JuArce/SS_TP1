package ar.edu.itba.ss.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParticleTest {

    @Test
    public void distanceToTest() {
        final Particle p1 = new Particle(0.5, new Point(1, 1));
        final Particle p2 = new Particle(0.5, new Point(4, 5));
        assertEquals(4.0, p1.distanceTo(p2), 0.0001);
    }

    @Test
    public void isNeighbourTest() {
        final Particle p1 = new Particle(0.5, new Point(1, 1));
        final Particle p2 = new Particle(0.5, new Point(1, 1.5));
        assertTrue(p1.isNeighbour(p2, 1.0));
    }

    @Test
    public void isNotNeighbourTest() {
        final Particle p1 = new Particle(0.5, new Point(1, 1));
        final Particle p2 = new Particle(0.5, new Point(4, 5));
        assertFalse(p1.isNeighbour(p2, 1.0));
    }
}
