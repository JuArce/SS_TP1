package ar.edu.itba.ss;

import ar.edu.itba.ss.models.Grid;
import ar.edu.itba.ss.models.Particle;
import ar.edu.itba.ss.tools.CsvExporter;
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
     * @param args: args[0] Static file path
     *              args[1] Dynamic file path
     *              args[2] Is periodic (true/false)
     *              args[3] Output filename [Optional] (ie: output.csv)
     */
    public static void main(String[] args) {
        Instant start = Instant.now();

        ParticleReader particleReader = new ParticleReader(args[0],args[1]);
        List<Particle> particles = new ArrayList<>();
        int l = particleReader.read(particles, Boolean.parseBoolean(args[2]));

        Grid grid = new Grid(l, particles);
        grid.calculateNeighbours();

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end));

        CsvExporter csvExporter = new CsvExporter();
        csvExporter.export(args.length < 4 ? "output.csv" : args[3], grid.getParticles());
    }
}
