package ar.edu.itba.ss.tools;

import ar.edu.itba.ss.models.Particle;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParticleReader {
    File particleFile;
    File positionFile;

    public ParticleReader(String particlePath, String positionPath) {
        this.particleFile = new File(particlePath);
        this.positionFile = new File(positionPath);
//        Files.lines(particleFile.toPath())
//                .map(line -> line.split("\n"))// |s{3,4}
//                .forEach(System.out::println);
    }

    public int read(List<Particle> particles) {
        int sideLength = readParticles(particles);
        readPositions(particles, sideLength);
        return sideLength;
    }

    private int readParticles(List<Particle> particles) {
        int particleCount;
        int sideLength;
        List<String> lines = getLines(particleFile);

        particleCount = Integer.parseInt(lines.remove(0).trim());
        sideLength = Integer.parseInt(lines.remove(0).trim());

        if (lines.size() != particleCount) {
            throw new IllegalArgumentException("Particle count and particle lines don't match");
        }

        lines.forEach(l -> {
            String[] s = l.replaceAll("\\s+", " ").trim().split(" ");
            particles.add(new Particle(Double.parseDouble(s[0])));
        });
        return sideLength;
    }

    private void readPositions(List<Particle> particles, int sideLength) {

        List<String> lines = getLines(positionFile);
        Iterator<Particle> particleIterator = particles.iterator();
        lines.remove(0);//First elements is the frame number
        lines.forEach(l -> {
            String[] s = l.replaceAll("\\s+", " ").trim().split(" ");
            if (particleIterator.hasNext()) {
                Particle p = particleIterator.next();
                p.setX(Double.parseDouble(s[0]));
                p.setY(Double.parseDouble(s[1]));
            }
        });
    }

    private List<String> getLines(File file) {
        List<String> lines = new ArrayList<>();
        try (Stream<String> stream = Files.lines(file.toPath())) {
            lines = stream
                    .map(l -> l.split("\n"))
                    .map(l -> l[0])
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
