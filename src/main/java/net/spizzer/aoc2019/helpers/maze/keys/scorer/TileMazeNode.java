package net.spizzer.aoc2019.helpers.maze.keys.scorer;

import net.spizzer.aoc2019.helpers.geometry2d.Point2D;
import net.spizzer.aoc2019.helpers.maze.GraphNode;

public class TileMazeNode extends Point2D implements GraphNode<Point2D> {

    TileMazeNode(Point2D point) {
        super(point);
    }

    @Override
    public Point2D getKey() {
        return this;
    }
}
