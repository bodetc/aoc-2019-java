package net.spizzer.aoc2019.helpers.maze.portals;

import net.spizzer.aoc2019.helpers.geometry2d.Point2D;
import net.spizzer.aoc2019.helpers.maze.GraphNode;

public class MazeNode implements GraphNode<Point2D> {
    private final Point2D point;

    public MazeNode(Point2D point) {
        this.point = point;
    }

    @Override
    public Point2D getKey() {
        return point;
    }

    private boolean equals(MazeNode other) {
        return this.point.equals(other.point);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof MazeNode && equals((MazeNode) other);
    }

}
