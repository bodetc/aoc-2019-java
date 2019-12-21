package net.spizzer.aoc2019.helpers.maze.keys;

import com.google.common.collect.Sets;
import net.spizzer.aoc2019.common.TwoKeyMap;
import net.spizzer.aoc2019.helpers.geometry2d.Point2D;
import net.spizzer.aoc2019.helpers.maze.*;
import net.spizzer.aoc2019.helpers.maze.keys.scorer.TileMazeSolver;
import net.spizzer.aoc2019.utils.ParseUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class KeyMazeSolver implements Graph<KeyMazeNode, KeyMazeNode>, Scorer<KeyMazeNode> {

    private final TwoKeyMap<Character, Character, Integer> costs;
    private final Set<Character> keys;

    public KeyMazeSolver(List<String> lines) {
        Map<Point2D, Character> input = ParseUtils.linesToCharMap(lines);

        Set<Point2D> walkable = input.entrySet().stream()
                .filter(entry -> entry.getValue() == '.')
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        Map<Character, Point2D> pointsOfInterest = input.entrySet().stream()
                .filter(entry -> Character.isLetter(entry.getValue()) || entry.getValue() == '@')
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

        keys = pointsOfInterest.keySet().stream()
                .filter(Character::isLowerCase)
                .collect(Collectors.toSet());

        costs = new TwoKeyMap<>();
        for (Map.Entry<Character, Point2D> a : pointsOfInterest.entrySet()) {
            for (Map.Entry<Character, Point2D> b : pointsOfInterest.entrySet()) {
                if (a.getKey() != b.getKey()) {
                    TileMazeSolver solver = new TileMazeSolver(walkable, a.getValue(), b.getValue());
                    Integer cost = solver.timeToTarget();
                    if (cost != null) {
                        costs.put(a.getKey(), b.getKey(), cost);
                    }
                }
            }
        }
    }

    public Integer timeForAllKeys() {
        RouteFinder<KeyMazeNode, KeyMazeNode> routeFinder = new RouteFinder<>(this, this);
        RouteFinderResult result = routeFinder.findRoute(new KeyMazeNode('@'), this::hasAllKeys);
        //result.printRoute();

        return result.targetFound()
                ? result.score()
                : null;
    }

    @Override
    public int computeCost(KeyMazeNode from, KeyMazeNode to) {
        return costs.get(from.getName(), to.getName());
    }

    @Override
    public Set<KeyMazeNode> getConnections(KeyMazeNode current) {
        Set<KeyMazeNode> connections = new HashSet<>();
        Set<Character> keys = current.getKeys();
        for (Character other : costs.getKeys(current.getName())) {
            // Door
            if (Character.isUpperCase(other)) {
                if (keys.contains(Character.toLowerCase(other))) {
                    connections.add(new KeyMazeNode(other, keys));
                }
            // Key
            } else if (Character.isLowerCase(other)) {
                connections.add(new KeyMazeNode(other, Sets.union(keys, Set.of(other))));
            // Starting point
            } else {
                connections.add(new KeyMazeNode(other, keys));
            }
        }
        return connections;
    }

    private boolean hasAllKeys(KeyMazeNode current) {
        return current.getKeys().containsAll(keys);
    }
}
