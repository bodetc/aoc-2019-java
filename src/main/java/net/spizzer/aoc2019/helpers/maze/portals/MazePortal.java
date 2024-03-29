package net.spizzer.aoc2019.helpers.maze.portals;

import net.spizzer.aoc2019.helpers.geometry2d.Point2D;

class MazePortal {
    private final Point2D inner;
    private final Point2D outer;

    private static final int MAX_DEPTH = 100;

    MazePortal(Point2D outer, Point2D inner) {
        this.outer = outer;
        this.inner = inner;
    }

    PortalMazeNode traverse(PortalMazeNode node, boolean flatWorld) {
        Point2D point = node.getPoint();
        int level = node.getLevel();
        if (flatWorld) {
            if (outer.equals(point)) {
                return new PortalMazeNode(inner, level);
            } else if (inner.equals(point)) {
                return new PortalMazeNode(outer, level);
            } else {
                return null;
            }
        } else {
            if (outer.equals(point) && level > 0) {
                return new PortalMazeNode(inner, level - 1);
            } else if (inner.equals(point) && level < MAX_DEPTH) {
                return new PortalMazeNode(outer, level + 1);
            } else {
                return null;
            }
        }
    }
}
