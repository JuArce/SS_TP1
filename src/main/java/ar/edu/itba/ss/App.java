package ar.edu.itba.ss;

import ar.edu.itba.ss.models.Grid;
import ar.edu.itba.ss.models.Particle;
import ar.edu.itba.ss.tools.CsvExporter;
import ar.edu.itba.ss.tools.Exporter;
import ar.edu.itba.ss.tools.ParticleReader;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Cell Index Method
 */
public class App {
    /**
     * Cell Index Method
     *
     * @param args: args[0] Static file path
     *              args[1] Dynamic file path
     *              args[2] Is periodic (true/false)
     *              args[3] Output filename [Optional] (ie: output.csv)
     */
    public static void main(String[] args) {
        String particlePath = args[0];
        String positionPath = args[1];
        boolean isPeriodic = Boolean.parseBoolean(args[2]);
        String outputFilename = args.length < 4 ? "output.csv" : args[3];

        Instant start = Instant.now();

        ParticleReader particleReader = new ParticleReader(particlePath, positionPath);
        List<Particle> particles = new ArrayList<>();
        int l = particleReader.read(particles, isPeriodic);

        Grid grid = new Grid(l, particles, isPeriodic);
        grid.calculateNeighbours();

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end));

        Exporter exporter = new CsvExporter();
        exporter.export(outputFilename, grid.getParticles());
    }
}
