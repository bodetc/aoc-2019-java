package net.spizzer.aoc2019.helpers.maze.keys;

import net.spizzer.aoc2019.common.TwoKeyMap;
import net.spizzer.aoc2019.helpers.geometry2d.Point2D;
import net.spizzer.aoc2019.helpers.maze.Graph;
import net.spizzer.aoc2019.helpers.maze.RouteFinder;
import net.spizzer.aoc2019.helpers.maze.RouteFinderResult;
import net.spizzer.aoc2019.helpers.maze.Scorer;
import net.spizzer.aoc2019.helpers.maze.keys.scorer.TileMazeSolver;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class KeyMazeSolver implements Graph<KeyMazeNode, KeyMazeNode>, Scorer<KeyMazeNode> {

    private final TwoKeyMap<Character, Character, Integer> costs;
    private final Set<Character> keys;
    private final int robotCount;

    public KeyMazeSolver(Map<Point2D, Character> input) {
        Set<Point2D> walkable = input.entrySet().stream()
                .filter(entry -> entry.getValue() == '.')
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        int i = 0;
        for (Map.Entry<Point2D, Character> entry : input.entrySet()) {
            if (entry.getValue() == '@') {
                entry.setValue(Character.forDigit(i, 10));
                i++;
            }
        }
        robotCount = i;

        Map<Character, Point2D> pointsOfInterest = input.entrySet().stream()
                .filter(entry -> Character.isLetter(entry.getValue()) || Character.isDigit(entry.getValue()))
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
        RouteFinderResult result = routeFinder.findRoute(new KeyMazeNode(robotCount), this::hasAllKeys);

        return result.targetFound()
                ? result.score()
                : null;
    }

    @Override
    public int computeCost(KeyMazeNode from, KeyMazeNode to) {
        int cost = 0;
        for (int i = 0; i < from.getPositionSize(); i++) {
            Character fromPosition = from.getPosition(i);
            Character toPosition = to.getPosition(i);

            if (!fromPosition.equals(toPosition)) {
                cost += costs.get(fromPosition, toPosition);
            }
        }
        return cost;
    }

    @Override
    public Set<KeyMazeNode> getConnections(KeyMazeNode current) {
        Set<KeyMazeNode> connections = new HashSet<>();
        for (int i = 0; i < current.getPositionSize(); i++) {
            Character position = current.getPosition(i);
            for (Character newPosition : costs.getKeys(position)) {
                if (current.canMoveTo(newPosition)) {
                    connections.add(current.moveTo(i, newPosition));
                }
            }
        }
        return connections;
    }

    private boolean hasAllKeys(KeyMazeNode current) {
        return current.getKeys().containsAll(keys);
    }
}
