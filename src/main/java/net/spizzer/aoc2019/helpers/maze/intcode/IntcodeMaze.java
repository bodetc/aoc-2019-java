package net.spizzer.aoc2019.helpers.maze.intcode;

import net.spizzer.aoc2019.helpers.maze.Graph;
import net.spizzer.aoc2019.helpers.geometry2d.Point2D;

import java.util.Set;

public class IntcodeMaze implements Graph<Point2D, IntcodeMazeNode> {

    @Override
    public Set<IntcodeMazeNode> getConnections(IntcodeMazeNode node) {
        return node.getConnections();
    }
}
