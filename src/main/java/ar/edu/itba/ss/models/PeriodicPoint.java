package ar.edu.itba.ss.models;

public class PeriodicPoint extends Point {
    private double side;

    public PeriodicPoint(double x, double y, double side) {
        super(x, y);
        this.side = side;
    }

    @Override
    public double distanceTo(Point point) {
        final double aux_delta_x = this.x - point.x;
        final double delta_x = Double.min(aux_delta_x, this.side - aux_delta_x);
        final double aux_delta_y = this.y - point.y;
        final double delta_y = Double.min(aux_delta_y, this.side - aux_delta_y);
        return Math.sqrt(delta_x * delta_x + delta_y * delta_y);
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }
}
