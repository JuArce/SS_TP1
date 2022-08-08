package ar.edu.itba.ss;

import ar.edu.itba.ss.models.Grid;
import ar.edu.itba.ss.models.Particle;
import ar.edu.itba.ss.tools.ParticleReader;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Instant start = Instant.now();

        ParticleReader particleReader = new ParticleReader(args[0],args[1]);
        List<Particle> particles = new ArrayList<>();
        Integer l = particleReader.read(particles);

        Grid grid = new Grid(l, particles);
        grid.calculateNeighbours();
        particles = grid.getParticles();
        particles.forEach(System.out::println);

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end));
        return;
    }
}
