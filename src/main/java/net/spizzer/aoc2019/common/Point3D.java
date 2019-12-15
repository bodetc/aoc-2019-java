package net.spizzer.aoc2019.common;

public class Point3D {
    public final int x;
    public final int y;
    public final int z;

    public static final Point3D ORIGIN = new Point3D(0, 0, 0);

    public Point3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int taxiDistance(Point3D other) {
        return Math.abs(other.x - x) + Math.abs(other.y - y) + Math.abs(other.z - z);
    }

    public Point3D add(int x, int y, int z) {
        return add(new Point3D(x, y, z));
    }

    public Point3D add(Point3D other) {
        return new Point3D(x+other.x, y+other.y, z+other.z);
    }

    public int taxiNorm() {
        return Math.abs(x)+Math.abs(y)+Math.abs(z);
    }

    private boolean equals(Point3D other) {
        return x == other.x && y == other.y && z == other.z;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Point3D && equals((Point3D) other);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + "," + z + ")";
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
