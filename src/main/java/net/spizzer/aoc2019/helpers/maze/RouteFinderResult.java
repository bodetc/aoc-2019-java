package net.spizzer.aoc2019.helpers.maze;

import net.spizzer.aoc2019.common.Reject;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class RouteFinderResult<T extends GraphNode> {
    private final Set<RouteNode<T>> allNodes;
    private final RouteNode<T> target;

    RouteFinderResult(Collection<RouteNode<T>> allNodes, RouteNode<T> target) {
        Reject.ifTrue(allNodes != null && target != null, "Both variables cannot be defined at the same time!");
        this.allNodes = allNodes == null ? null : new HashSet<>(allNodes);
        this.target = target;
    }

    public Set<RouteNode<T>> getAllNodes() {
        Reject.ifNull(allNodes, "allNodes is not defined");
        return allNodes;
    }

    public RouteNode<T> getTarget() {
        Reject.ifNull(target, "target is not defined");
        return target;
    }

    public boolean targetFound() {
        return target != null;
    }

    public int score() {
        Reject.ifNull(target, "target is not defined");
        return target.getScore();
    }

    public T getTargetLocation() {
        Reject.ifNull(target, "target is not defined");
        return target.getCurrent();
    }

    public void printRoute() {
        Reject.ifNull(target, "target is not defined");
        target.getRoute().forEach(System.out::println);
    }
}
