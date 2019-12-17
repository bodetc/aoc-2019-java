package net.spizzer.aoc2019.helpers.astar;

public interface Scorer<T extends GraphNode> {
    int computeCost(T from, T to);
}