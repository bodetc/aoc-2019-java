package net.spizzer.aoc2019.helpers.maze;

import java.util.Set;

public interface Graph<K, T extends GraphNode<K>> {
    Set<T> getConnections(T node);
}