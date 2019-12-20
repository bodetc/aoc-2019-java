package net.spizzer.aoc2019.helpers.maze.portals;

import net.spizzer.aoc2019.helpers.geometry2d.Point2D;
import net.spizzer.aoc2019.helpers.maze.GraphNode;

import java.util.Objects;

public class MazeNode implements GraphNode<MazeNode> {
    private final Point2D point;
    private final int level;

    public MazeNode(Point2D point, int level) {
        this.point = point;
        this.level = level;
    }

    @Override
    public MazeNode getKey() {
        return this;
    }

    public Point2D getPoint() {
        return point;
    }

    public int getLevel() {
        return level;
    }

    private boolean equals(MazeNode other) {
        return this.point.equals(other.point) && this.level == other.level;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof MazeNode && equals((MazeNode) other);
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
