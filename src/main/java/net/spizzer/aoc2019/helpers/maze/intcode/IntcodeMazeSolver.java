package net.spizzer.aoc2019.helpers.maze.intcode;

import net.spizzer.aoc2019.common.Reject;
import net.spizzer.aoc2019.helpers.maze.AdjacentScorer;
import net.spizzer.aoc2019.helpers.maze.RouteFinder;
import net.spizzer.aoc2019.helpers.maze.RouteFinderResult;
import net.spizzer.aoc2019.helpers.maze.RouteNode;
import net.spizzer.aoc2019.helpers.geometry2d.Point2D;
import net.spizzer.aoc2019.intcode.IntcodeComputer;

public class IntcodeMazeSolver {

    private final RouteFinder<Point2D, IntcodeMazeNode> routeFinder = new RouteFinder<>(new IntcodeMaze(), new AdjacentScorer<>());
    private final IntcodeMazeNode origin;

    public IntcodeMazeSolver(long[] program) {
        IntcodeComputer computer = new IntcodeComputer(program);
        this.origin = new IntcodeMazeNode(Point2D.ORIGIN, IntcodeMazeTile.EMPTY, computer);
    }

    public int timeToTarget() {
        RouteFinderResult<IntcodeMazeNode> target = findTarget();
        return target.score();
    }

    public int timeToExplore() {
        RouteFinderResult<IntcodeMazeNode> target = findTarget();

        RouteFinderResult<IntcodeMazeNode> result = routeFinder.findRoute(target.getTargetLocation(), x -> false);
        Reject.ifTrue(result.targetFound(), "Target should not be found!");

        return result.getAllNodes().stream()
                .mapToInt(RouteNode::getScore)
                .max()
                .orElseThrow();
    }

    private RouteFinderResult<IntcodeMazeNode> findTarget() {
        RouteFinderResult<IntcodeMazeNode> result = routeFinder.findRoute(origin, IntcodeMazeNode::isTarget);
        Reject.ifFalse(result.targetFound(), "Target was not found!");
        return result;
    }
}
