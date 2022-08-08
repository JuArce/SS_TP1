package ar.edu.itba.ss.models;

import java.util.List;

public class Particle {
    private static int sequence = 1;
    private static final double rc = 1.0;
    int id;
    double x;
    double y;
    double radius;
    List<Particle> neighbours;

    public Particle(double radius, double x, double y) {
        this.id = sequence++;
        this.radius = radius;
        this.x = x;
        this.y = y;
    }

    public Particle(double radius) {
        this(radius, 0, 0);
    }

    public void addNeighbour(Particle particle) {
        //TODO
    }

    public boolean isNeighbour(Particle particle) {
        return false; //TODO
    }

    public double distanceTo(Particle particle) {
        return 0; //TODO
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
