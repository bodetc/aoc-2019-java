package net.spizzer.aoc2019.helpers.maze.intcode;

import net.spizzer.aoc2019.common.Reject;
import net.spizzer.aoc2019.helpers.maze.ConstantScorer;
import net.spizzer.aoc2019.helpers.maze.RouteFinder;
import net.spizzer.aoc2019.helpers.maze.RouteNode;
import net.spizzer.aoc2019.helpers.geometry2d.Point2D;
import net.spizzer.aoc2019.intcode.IntcodeComputer;

public class IntcodeMazeSolver extends RouteFinder<Point2D, IntcodeMazeNode, IntcodeMaze> {
    private final IntcodeMazeNode origin;

    public IntcodeMazeSolver(long[] program) {
        super(new IntcodeMaze(), new ConstantScorer<>());
        IntcodeComputer computer = new IntcodeComputer(program);
        this.origin = new IntcodeMazeNode(Point2D.ORIGIN, IntcodeMazeTile.EMPTY, computer);
    }

    public int timeToTarget() {
        RouteNode<IntcodeMazeNode> target = findTarget();

        return target.getRoute().size()-1;
    }

    public int timeToExplore() {
        RouteNode<IntcodeMazeNode> target = findTarget();
        RouteFinderResult result;

        result = findRoute(target.getCurrent(), x -> false);
        Reject.ifDifferent(RouteFinderResult.NOT_FOUND, result, "Target should not be found!");

        return getAllNodes().stream()
                .mapToInt(RouteNode::getScore)
                .max()
                .orElseThrow();
    }

    private RouteNode<IntcodeMazeNode> findTarget() {
        RouteFinderResult result = findRoute(origin, IntcodeMazeNode::isTarget);
        Reject.ifDifferent(RouteFinderResult.FOUND, result, "Target was not found!");
        return getTarget();
    }
}
