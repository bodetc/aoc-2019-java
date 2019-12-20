package net.spizzer.aoc2019.helpers.maze.intcode;

import net.spizzer.aoc2019.common.Reject;
import net.spizzer.aoc2019.helpers.maze.ConstantScorer;
import net.spizzer.aoc2019.helpers.maze.RouteFinder;
import net.spizzer.aoc2019.helpers.maze.RouteNode;
import net.spizzer.aoc2019.helpers.geometry2d.Point2D;
import net.spizzer.aoc2019.intcode.IntcodeComputer;

public class MazeSolver extends RouteFinder<Point2D, MazeNode, Maze> {
    private final MazeNode origin;

    public MazeSolver(long[] program) {
        super(new Maze(), new ConstantScorer<>());
        IntcodeComputer computer = new IntcodeComputer(program);
        this.origin = new MazeNode(Point2D.ORIGIN, MazeTile.EMPTY, computer);
    }

    public int timeToTarget() {
        RouteNode<MazeNode> target = findTarget();

        return target.getRoute().size()-1;
    }

    public int timeToExplore() {
        RouteNode<MazeNode> target = findTarget();
        RouteFinderResult result;

        result = findRoute(target.getCurrent(), x -> false);
        Reject.ifDifferent(RouteFinderResult.NOT_FOUND, result, "Target should not be found!");

        return getAllNodes().stream()
                .mapToInt(RouteNode::getScore)
                .max()
                .orElseThrow();
    }

    private RouteNode<MazeNode> findTarget() {
        RouteFinderResult result = findRoute(origin, MazeNode::isTarget);
        Reject.ifDifferent(RouteFinderResult.FOUND, result, "Target was not found!");
        return getTarget();
    }
}
