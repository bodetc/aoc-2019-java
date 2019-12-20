package net.spizzer.aoc2019.helpers.maze.portals;

import net.spizzer.aoc2019.helpers.geometry2d.Point2D;
import net.spizzer.aoc2019.helpers.maze.GraphNode;

import java.util.Objects;

public class PortalMazeNode implements GraphNode<PortalMazeNode> {
    private final Point2D point;
    private final int level;

    public PortalMazeNode(Point2D point, int level) {
        this.point = point;
        this.level = level;
    }

    @Override
    public PortalMazeNode getKey() {
        return this;
    }

    public Point2D getPoint() {
        return point;
    }

    public int getLevel() {
        return level;
    }

    private boolean equals(PortalMazeNode other) {
        return this.point.equals(other.point) && this.level == other.level;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof PortalMazeNode && equals((PortalMazeNode) other);
    }

    @Override
    public String toString() {
        return point.toString() + "-" + level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, level);
    }

}
