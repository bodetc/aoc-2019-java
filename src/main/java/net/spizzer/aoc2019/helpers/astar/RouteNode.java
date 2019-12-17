package net.spizzer.aoc2019.helpers.astar;

import javax.annotation.Nonnull;

class RouteNode<T extends GraphNode> implements Comparable<RouteNode> {
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

    @Override
    public int compareTo(@Nonnull RouteNode other) {
        return Integer.compare(this.score, other.score);
    }

    T getCurrent() {
        return current;
    }

    RouteNode<T> getPrevious() {
        return previous;
    }

    void setPrevious(RouteNode<T> previous) {
        this.previous = previous;
    }

    int getScore() {
        return score;
    }

    void setScore(int score) {
        this.score = score;
    }
}