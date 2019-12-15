package net.spizzer.aoc2019.geometry2d;

import net.spizzer.aoc2019.utils.MathUtils;

import java.util.Optional;

class Segment2D extends Vector2D {
    final Point2D start;
    final Point2D end;

    Segment2D(Point2D start, Vector2D vector) {
        super(vector);
        this.start = start;
        this.end = start.addVector(this);
    }

    Optional<Point2D> intersect(Segment2D other) {
        if (isHorizontal() && other.isVertical()
                && MathUtils.isBetween(start.x, end.x, other.start.x)
                && MathUtils.isBetween(other.start.y, other.end.y, start.y)) {
            return Optional.of(new Point2D(other.start.x, start.y));
        } else if (isVertical() && other.isHorizontal()
                && MathUtils.isBetween(other.start.x, other.end.x, start.x)
                && MathUtils.isBetween(start.y, end.y, other.start.y)) {
            return Optional.of(new Point2D(start.x, other.start.y));
        } else {
            return Optional.empty();
        }
    }

    boolean contains(Point2D point) {
        return (isHorizontal() && start.y==point.y && MathUtils.isBetween(start.x, end.x, point.x))
               ||  (isVertical() && start.x==point.x && MathUtils.isBetween(start.y, end.y, point.y));
    }
}
