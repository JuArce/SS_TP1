package ar.edu.itba.ss;

import ar.edu.itba.ss.models.Particle;
import ar.edu.itba.ss.models.Point;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Generator {
    public static void main(String[] args) {
        int L = 100;
        int N = 10000;
        double r = 0.37;
        double minRandom = 0 + r;
        double maxRandom = L - r;

        List<Particle> particles = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Particle p = new Particle(r, new Point(getRandom(minRandom, maxRandom), getRandom(minRandom, maxRandom)));
            particles.add(p);
        }

        try {
            CSVWriter writer = new CSVWriter(new FileWriter("src/main/resources/staticN.txt"), ' ', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
            writer.writeNext(new String[]{String.valueOf(particles.size())});
            writer.writeNext(new String[]{String.valueOf(L)});
            particles.forEach(p -> {
                List<String> line = new ArrayList<>();
                line.add(String.valueOf(r));
                writer.writeNext(line.toArray(new String[0]));
            });
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace(); //TODO: handle exception
        }

        try {
            CSVWriter writer = new CSVWriter(new FileWriter("src/main/resources/dynamicN.txt"), ' ', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
            writer.writeNext(new String[]{String.valueOf(0)});
            particles.forEach(p -> {
                List<String> line = new ArrayList<>();
                line.add(String.valueOf(p.getPosition().getX()));
                line.add(String.valueOf(p.getPosition().getY()));
                writer.writeNext(line.toArray(new String[0]));
            });
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace(); //TODO: handle exception
        }
    }

    public static double getRandom(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

}
