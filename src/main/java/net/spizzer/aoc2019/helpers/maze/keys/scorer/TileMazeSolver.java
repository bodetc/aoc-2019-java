package net.spizzer.aoc2019.helpers.maze.keys.scorer;

import net.spizzer.aoc2019.helpers.geometry2d.Direction2D;
import net.spizzer.aoc2019.helpers.geometry2d.Point2D;
import net.spizzer.aoc2019.helpers.maze.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TileMazeSolver implements Graph<Point2D, TileMazeNode> {

    private final Set<Point2D> walkable;
    private final TileMazeNode start;
    private final TileMazeNode end;

    public TileMazeSolver(Set<Point2D> points, Point2D start, Point2D end) {
        this.walkable = new HashSet<>(points);
        walkable.add(start);
        walkable.add(end);

        this.start = new TileMazeNode(start);
        this.end = new TileMazeNode(end);
    }


    @Override
    public Set<TileMazeNode> getConnections(TileMazeNode node) {
        Point2D current = node.getKey();
        return Arrays.stream(Direction2D.values())
                .map(current::addDirection)
                .filter(walkable::contains)
                .map(TileMazeNode::new)
                .collect(Collectors.toSet());
    }

    public Integer timeToTarget() {
        RouteFinder<Point2D, TileMazeNode> routeFinder = new RouteFinder<>(this, new AdjacentScorer<>());
        RouteFinderResult result = routeFinder.findRoute(start, end::equals);

        return result.targetFound()
                ? result.score()
                : null;
    }
}
