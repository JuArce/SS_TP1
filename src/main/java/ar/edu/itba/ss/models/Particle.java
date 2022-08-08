package ar.edu.itba.ss.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Particle {
    private static int SEQUENCE = 1;
    private static final double RC = 1.0;

    private int id;
    private double x;
    private double y;
    private double radius;
    private final List<Particle> neighbours;

    public Particle(double radius, double x, double y) {
        this.id = SEQUENCE++;
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.neighbours = new ArrayList<>();
    }

    public Particle(double radius) {
        this(radius, 0, 0);
    }

    public void addNeighbour(Particle particle) {
        if (this.equals(particle)) {
            return;
        }
        this.neighbours.add(particle);
    }

    public boolean isNeighbour(Particle particle, double rc) {
        return this.distanceTo(particle) <= rc;
    }

    public boolean isNeighbour(Particle particle) {
        return this.isNeighbour(particle, Particle.RC);
    }

    public double distanceTo(Particle particle) {
        final double delta_x = this.x - particle.x;
        final double delta_y = this.y - particle.y;
        return Math.sqrt(delta_x * delta_x + delta_y * delta_y) - this.radius - particle.radius;
    }

    @Override
    public String toString() {
        return "Particle{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", radius=" + radius +
                ", neighbours=" + neighbours +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Particle particle = (Particle) o;
        return id == particle.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
