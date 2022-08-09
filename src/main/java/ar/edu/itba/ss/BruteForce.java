package ar.edu.itba.ss;

import ar.edu.itba.ss.models.Grid;
import ar.edu.itba.ss.models.Particle;
import ar.edu.itba.ss.tools.ParticleReader;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class BruteForce {
    public static void main(String[] args) {
        Instant start = Instant.now();

        ParticleReader particleReader = new ParticleReader(args[0],args[1]);
        List<Particle> particles = new ArrayList<>();
        Integer l = particleReader.read(particles, Boolean.parseBoolean(args[2]));

        particles.forEach(p -> p.setNeighbours(particles));
        particles.forEach(System.out::println);

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end));
        return;
    }
}
