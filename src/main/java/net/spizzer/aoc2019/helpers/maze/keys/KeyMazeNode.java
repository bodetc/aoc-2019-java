package net.spizzer.aoc2019.helpers.maze.keys;

import net.spizzer.aoc2019.helpers.maze.GraphNode;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class KeyMazeNode implements GraphNode<KeyMazeNode> {
    private final Character name;
    private final Set<Character> keys;

    public KeyMazeNode(Character name) {
        this.name = name;
        this.keys = Set.of();
    }

    public KeyMazeNode(Character name, Set<Character> keys) {
        this.name = name;
        this.keys = Set.copyOf(keys);
    }

    @Override
    public KeyMazeNode getKey() {
        return this;
    }

    public Character getName() {
        return name;
    }

    public Set<Character> getKeys() {
        return keys;
    }

    private boolean equals(KeyMazeNode other) {
        return this.name.equals(other.name)
                && keys.containsAll(other.keys)
                && other.keys.containsAll(keys);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof KeyMazeNode && equals((KeyMazeNode) other);
    }

    @Override
    public String toString() {
        return name + "-" + keys.stream().map(c -> "" + c).collect(Collectors.joining(""));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, keys);
    }
}
