package net.spizzer.aoc2019.helpers.maze.portals;

import net.spizzer.aoc2019.helpers.geometry2d.Point2D;

public class MazePortal {
    private final Point2D from;
    private final Point2D to;

    public MazePortal(Point2D from, Point2D to) {
        this.from = from;
        this.to = to;
    }

    Point2D otherEnd(Point2D point) {
        if (from.equals(point)) {
            return to;
        } else if (to.equals(point)) {
            return from;
        } else {
            return null;
        }
    }
}
