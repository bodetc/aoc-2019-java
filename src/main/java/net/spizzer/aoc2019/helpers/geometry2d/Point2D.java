package net.spizzer.aoc2019.helpers.geometry2d;

import net.spizzer.aoc2019.utils.MathUtils;

public class Point2D {
    public final int x;
    public final int y;

    public static final Point2D ORIGIN = new Point2D(0, 0);

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int taxiDistance(Point2D other) {
        return Math.abs(other.x - x) + Math.abs(other.y - y);
    }

    Point2D addVector(Vector2D vector) {
        switch (vector.direction) {
            case R:
                return new Point2D(x + vector.distance, y);
            case L:
                return new Point2D(x - vector.distance, y);
            case U:
                return new Point2D(x, y + vector.distance);
            case D:
                return new Point2D(x, y - vector.distance);
        }
        throw new IllegalStateException("This point cannot be reached.");
    }

    public boolean isCollinear(Point2D a, Point2D b) {
        // Area of the triangle ABC (multiplied by 2)
        int area = a.x * (b.y - y) + b.x * (y - a.y) + x * (a.y - b.y);

        return area == 0;
    }

    public boolean isBetween(Point2D a, Point2D b) {
        return MathUtils.isBetween(a.x, b.x, x)
                && MathUtils.isBetween(a.y, b.y, y);
    }

    private boolean equals(Point2D other) {
        return x == other.x && y == other.y;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Point2D && equals((Point2D) other);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
