package net.spizzer.aoc2019.helpers.maze;

public interface Scorer<T extends GraphNode> {
    int computeCost(T from, T to);
}