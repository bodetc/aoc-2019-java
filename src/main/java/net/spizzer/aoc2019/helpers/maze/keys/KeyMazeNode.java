package net.spizzer.aoc2019.helpers.maze.keys;

import net.spizzer.aoc2019.helpers.maze.GraphNode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class KeyMazeNode implements GraphNode<KeyMazeNode> {
    private final List<Character> positions;
    private final Set<Character> keys;

    public KeyMazeNode(int count) {
        this.positions = IntStream.range(0, count)
                .mapToObj(c -> Character.forDigit(c, 10))
                .collect(Collectors.toList());
        this.keys = Set.of();
    }

    private KeyMazeNode(List<Character> name, Set<Character> keys) {
        this.positions = List.copyOf(name);
        this.keys = Set.copyOf(keys);
    }

    boolean canMoveTo(Character newPosition) {
        return !Character.isUpperCase(newPosition) || keys.contains(Character.toLowerCase(newPosition));
    }

    KeyMazeNode moveTo(int index, Character newPosition) {
        List<Character> newPositions = new ArrayList<>(positions);
        newPositions.set(index, newPosition);

        HashSet<Character> newKeys = new HashSet<>(keys);
        if(Character.isLowerCase(newPosition)){
            newKeys.add(newPosition);
        }

        return new KeyMazeNode(newPositions, newKeys);
    }

    @Override
    public KeyMazeNode getKey() {
        return this;
    }

    Character getPosition(int i) {
        return positions.get(i);
    }

    int getPositionSize() {
        return positions.size();
    }

    Set<Character> getKeys() {
        return keys;
    }

    private boolean equals(KeyMazeNode other) {
        return this.positions.equals(other.positions)
                && keys.containsAll(other.keys)
                && other.keys.containsAll(keys);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof KeyMazeNode && equals((KeyMazeNode) other);
    }

    @Override
    public String toString() {
        return "(" + positions.stream().map(c -> "" + c).collect(Collectors.joining(",")) + ")"
                + "-" + keys.stream().map(c -> "" + c).collect(Collectors.joining(""));
    }

    @Override
    public int hashCode() {
        return Objects.hash(positions, keys);
    }
}
