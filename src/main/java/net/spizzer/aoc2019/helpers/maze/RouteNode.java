package net.spizzer.aoc2019.helpers.maze;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class RouteNode<T extends GraphNode> implements Comparable<RouteNode> {
    private final T current;
    private RouteNode<T> previous;
    private int score;

    RouteNode(T current) {
        this(current, null, Integer.MAX_VALUE);
    }

    RouteNode(T current, RouteNode<T> previous, int score) {
        this.current = current;
        this.previous = previous;
        this.score = score;
    }

    public  List<T> getRoute() {
        List<T> route = new ArrayList<>();
        RouteNode<T> current = this;
        do {
            route.add(0, current.getCurrent());
            current = current.previous;
        } while (current != null);
        return route;
    }

    @Override
    public int compareTo(@Nonnull RouteNode other) {
        return Integer.compare(this.score, other.score);
    }

    public T getCurrent() {
        return current;
    }

    void setPrevious(RouteNode<T> previous) {
        this.previous = previous;
    }

    public int getScore() {
        return score;
    }

    void setScore(int score) {
        this.score = score;
    }
}