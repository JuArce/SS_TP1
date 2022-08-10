package ar.edu.itba.ss.models;

public class PeriodicPoint extends Point {
    private double side;

    public PeriodicPoint(double x, double y, double side) {
        super(x, y);
        this.side = side;
    }

    @Override
    public double distanceTo(Point point) {
        final double auxDeltaX = Math.abs(this.x - point.x);
        final double deltaX = Double.min(auxDeltaX, this.side - auxDeltaX);

        final double auxDeltaY = Math.abs(this.y - point.y);
        final double deltaY = Double.min(auxDeltaY, this.side - auxDeltaY);

        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }
}
