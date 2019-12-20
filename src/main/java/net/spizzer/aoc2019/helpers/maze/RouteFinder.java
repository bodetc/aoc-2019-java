package net.spizzer.aoc2019.helpers.maze;

import java.util.*;
import java.util.function.Predicate;

public class RouteFinder<K, T extends GraphNode<K>, G extends Graph<K, T>> {
    private final G graph;
    private final Scorer<T> scorer;

    private final Queue<RouteNode<T>> openSet = new PriorityQueue<>();
    private final Map<K, RouteNode<T>> allNodes = new HashMap<>();
    private Optional<RouteNode<T>> target = Optional.empty();

    public RouteFinder(G graph, Scorer<T> scorer) {
        this.graph = graph;
        this.scorer = scorer;
    }

    public enum RouteFinderResult {
        FOUND,
        NOT_FOUND
    }

    protected RouteFinderResult findRoute(T from, Predicate<T> isDestination) {
        allNodes.clear();
        openSet.clear();
        target = Optional.empty();

        RouteNode<T> start = new RouteNode<>(from, null, 0);
        openSet.add(start);
        allNodes.put(from.getKey(), start);

        while (!openSet.isEmpty()) {
            RouteNode<T> current = openSet.poll();
            if (isDestination.test(current.getCurrent())) {
                target = Optional.of(current);
                return RouteFinderResult.FOUND;
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
        return RouteFinderResult.NOT_FOUND;
    }

    protected RouteNode<T> getTarget() {
        return target.orElseThrow();
    }

    protected Set<RouteNode<T>> getAllNodes() {
        return new HashSet<>(allNodes.values());
    }

    protected G getGraph() {
        return graph;
    }
}