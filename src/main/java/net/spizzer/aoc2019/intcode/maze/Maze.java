package net.spizzer.aoc2019.intcode.maze;

import net.spizzer.aoc2019.helpers.astar.Graph;
import net.spizzer.aoc2019.helpers.geometry2d.Point2D;

import java.util.Set;

public class Maze implements Graph<Point2D, MazeNode> {

    @Override
    public Set<MazeNode> getConnections(MazeNode node) {
        return node.getConnections();
    }
}
