package ar.edu.itba.ss.tools;

import ar.edu.itba.ss.models.Particle;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OvitoExporter {
    public void export(String filename, Set<Particle> particles, int selected) {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter("src/main/output/" + filename), ' ', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
            writer.writeNext(new String[]{String.valueOf(particles.size())});
            writer.writeNext(new String[]{});

            Particle selectedParticle = particles.stream().filter(p -> p.getId() == selected).findFirst().orElseThrow();
            particles.forEach(p -> {
                List<String> line = new ArrayList<>();
                line.add(String.valueOf(p.getId()));
                line.add(String.valueOf(p.getRadius()));
                line.add(String.valueOf(Particle.RC));
                line.add(String.valueOf(p.getPosition().getX()));
                line.add(String.valueOf(p.getPosition().getY()));
                if (p.equals(selectedParticle)) {
                    line.add("S");
                } else if (selectedParticle.getNeighbours().contains(p)) {
                    line.add("N");
                } else {
                    line.add("NN");
                }

                writer.writeNext(line.toArray(new String[0]));
            });
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace(); //TODO: handle exception
        }

    }
}
