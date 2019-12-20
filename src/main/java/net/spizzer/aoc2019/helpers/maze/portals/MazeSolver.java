package net.spizzer.aoc2019.helpers.maze.portals;

import net.spizzer.aoc2019.helpers.maze.ConstantScorer;
import net.spizzer.aoc2019.helpers.maze.RouteFinder;

import java.util.List;

public class MazeSolver extends RouteFinder<MazeNode, MazeNode, Maze> {
    public MazeSolver(List<String> lines, boolean flatWorld) {
        super(new Maze(lines, flatWorld), new ConstantScorer<>());
    }

    public Integer timeToTarget() {
        RouteFinderResult result = findRoute(getGraph().getStart(), getGraph().getEnd()::equals);
        return result == RouteFinderResult.FOUND
                ? getTarget().getRoute().size() - 1
                : null;
    }
}
