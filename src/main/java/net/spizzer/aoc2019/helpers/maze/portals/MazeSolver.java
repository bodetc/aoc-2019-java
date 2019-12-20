package net.spizzer.aoc2019.helpers.maze.portals;

import net.spizzer.aoc2019.common.Reject;
import net.spizzer.aoc2019.helpers.geometry2d.Point2D;
import net.spizzer.aoc2019.helpers.maze.ConstantScorer;
import net.spizzer.aoc2019.helpers.maze.RouteFinder;

import java.util.List;

public class MazeSolver extends RouteFinder<Point2D, MazeNode, Maze> {
    public MazeSolver(List<String> lines) {
        super(new Maze(lines), new ConstantScorer<>(false));
    }

    public int timeToTarget() {
        RouteFinderResult result = findRoute(getGraph().getStart(), getGraph().getEnd()::equals);
        Reject.ifDifferent(RouteFinderResult.FOUND, result, "Route was not found!");
        return getTarget().getRoute().size()-1;
    }
}
