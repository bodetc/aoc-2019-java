package net.spizzer.aoc2019.intcode.maze;

import net.spizzer.aoc2019.helpers.astar.RouteFinder;
import net.spizzer.aoc2019.helpers.geometry2d.Point2D;
import net.spizzer.aoc2019.intcode.IntcodeComputer;

public class MazeSolver extends RouteFinder<Point2D, MazeNode> {
    private final MazeNode origin;

    public MazeSolver(long[] program) {
        super(new Maze(), new NearestNeighbourScorer(), MazeNode::isTarget);
        IntcodeComputer computer = new IntcodeComputer(program);
        this.origin = new MazeNode(new Point2D(0, 0), MazeTile.EMPTY, computer);
    }

    public int solve() {
        return findRoute(origin).size()-1;
    }
}
