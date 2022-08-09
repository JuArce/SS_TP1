package ar.edu.itba.ss.tools;

import ar.edu.itba.ss.models.Particle;

import java.util.Set;

public interface Exporter {
    void export(String filename, Set<Particle> particles);
}
