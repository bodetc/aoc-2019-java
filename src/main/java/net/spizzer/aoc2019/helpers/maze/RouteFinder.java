package net.spizzer.aoc2019.helpers.maze;

import java.util.*;
import java.util.function.Predicate;

public class RouteFinder<K, T extends GraphNode<K>> {
    private final Graph<K, T> graph;
    private final Scorer<T> scorer;

    public RouteFinder(Graph<K, T> graph, Scorer<T> scorer) {
        this.graph = graph;
        this.scorer = scorer;
    }

    public RouteFinderResult<T> findRoute(T from, Predicate<T> isDestination) {
        Queue<RouteNode<T>> openSet = new PriorityQueue<>();
        Map<K, RouteNode<T>> allNodes = new HashMap<>();

        RouteNode<T> start = new RouteNode<>(from, null, 0);
        openSet.add(start);
        allNodes.put(from.getKey(), start);

        while (!openSet.isEmpty()) {
            RouteNode<T> current = openSet.poll();
            if (isDestination.test(current.getCurrent())) {
                return new RouteFinderResult<>(null, current);
            }

            Set<T> connections = graph.getConnections(current.getCurrent());
            for (T connection : connections) {
                RouteNode<T> next = allNodes.computeIfAbsent(connection.getKey(), (key) -> new RouteNode<>(connection));

                int newScore = current.getScore() + scorer.computeCost(current.getCurrent(), connection);
                if (newScore < next.getScore()) {
                    next.setPrevious(current);
                    next.setScore(newScore);
                    openSet.add(next);
                }
            }
        }
        return new RouteFinderResult<>(allNodes.values(), null);
    }
}