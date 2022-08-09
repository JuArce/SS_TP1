package ar.edu.itba.ss.tools;

import ar.edu.itba.ss.models.Particle;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CsvExporter implements Exporter {
    @Override
    public void export(String filename, Set<Particle> particles) {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter("src/main/output/" + filename), CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER);
            particles.forEach(p -> {
                List<String> line = new ArrayList<>();
                line.add(String.valueOf(p.getId()));
                line.addAll(p.getNeighbours().stream().map(Particle::getId).map(String::valueOf).toList());
                writer.writeNext(line.toArray(new String[0]));
            });
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace(); //TODO: handle exception
        }
    }
}
