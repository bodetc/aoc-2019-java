package net.spizzer.aoc2019.helpers.astar;

import java.util.Set;

public interface Graph<K, T extends GraphNode<K>> {
    Set<T> getConnections(T node);
}