package net.spizzer.aoc2019.geometry2d;

class Vector2D {
    final Direction2D direction;
    final int distance;

    private Vector2D(Direction2D direction, int distance) {
        this.direction = direction;
        this.distance = distance;
    }

    Vector2D(Vector2D other) {
        this(other.direction, other.distance);
    }

    Vector2D(String vector) {
        this(Direction2D.valueOf(vector.substring(0, 1)),
                Integer.parseInt(vector.substring(1)));
    }

    boolean isHorizontal() {
        return direction.isHorizontal();
    }

    boolean isVertical() {
        return direction.isVertical();
    }

    int length() {
        return distance;
    }
}
