package ar.edu.itba.ss;

import ar.edu.itba.ss.models.Particle;
import ar.edu.itba.ss.tools.Exporter;
import ar.edu.itba.ss.tools.OvitoExporter;
import ar.edu.itba.ss.tools.ParticleReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ovito {
    public static void main(String[] args) {
        ParticleReader particleReader = new ParticleReader(args[0], args[1]);
        List<Particle> particles = new ArrayList<>();
        particleReader.read(particles, false);

        List<String> lines = new ArrayList<>();
        try (Stream<String> stream = Files.lines(new File(args[2]).toPath())) {
            lines = stream
                    .map(l -> l.split("\n"))
                    .map(l -> l[0])
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }


        lines.forEach(l -> {
            String[] s = l.split(",");
            particles.stream().filter(p -> p.getId() == Integer.parseInt(s[0])).findFirst().ifPresent(p -> {
                Arrays.stream(s).forEach(candidate -> p.addNeighbour(particles.stream().filter(p2 -> p2.getId() == Integer.parseInt(candidate)).findFirst().orElseThrow()));
            });
        });

        OvitoExporter exporter = new OvitoExporter(Integer.parseInt(args[3]));
        exporter.export(args.length < 5 ? "outputOvito.csv" : args[4], new HashSet<>(particles));
    }
}
