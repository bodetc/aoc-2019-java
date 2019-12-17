package net.spizzer.aoc2019.helpers.astar;

import java.util.*;
import java.util.function.Predicate;

public class RouteFinder<K, T extends GraphNode<K>> {
    private final Graph<K, T> graph;
    private final Scorer<T> scorer;
    private final Predicate<T> isDestination;

    public RouteFinder(Graph<K, T> graph, Scorer<T> scorer, Predicate<T> isDestination) {
        this.graph = graph;
        this.scorer = scorer;
        this.isDestination = isDestination;
    }

    protected List<T> findRoute(T from) {
        Queue<RouteNode<T>> openSet = new PriorityQueue<>();
        Map<K, RouteNode<T>> allNodes = new HashMap<>();

        RouteNode<T> start = new RouteNode<>(from, null, 0);
        openSet.add(start);
        allNodes.put(from.getKey(), start);

        while (!openSet.isEmpty()) {
            RouteNode<T> current = openSet.poll();
            if (isDestination.test(current.getCurrent())) {
                return getRoute(current);
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
        throw new IllegalStateException("No route found");
    }

    private List<T> getRoute(RouteNode<T> next) {
        List<T> route = new ArrayList<>();
        RouteNode<T> current = next;
        do {
            route.add(0, current.getCurrent());
            current = current.getPrevious();
        } while (current != null);
        return route;
    }
}