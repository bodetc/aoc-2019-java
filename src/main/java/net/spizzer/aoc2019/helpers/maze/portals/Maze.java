package net.spizzer.aoc2019.helpers.maze.portals;

import net.spizzer.aoc2019.helpers.geometry2d.Direction2D;
import net.spizzer.aoc2019.helpers.geometry2d.Point2D;
import net.spizzer.aoc2019.helpers.maze.Graph;
import net.spizzer.aoc2019.utils.ParseUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Maze implements Graph<Point2D, MazeNode> {
    private final Map<Point2D, Set<MazeNode>> connections;
    private final MazeNode start;
    private final MazeNode end;

    public Maze(List<String> lines) {
        Map<Point2D, Character> input = ParseUtils.linesToCharMap(lines);

        Map<Point2D, MazeNode> walkableNodes = input.entrySet().stream()
                .filter(entry -> entry.getValue().equals('.'))
                .map(Map.Entry::getKey)
                .collect(Collectors.toMap(Function.identity(), MazeNode::new));


        Map<String, List<Point2D>> labels = parseLabels(input);
        start = walkableNodes.get(labels.get("AA").get(0));
        end = walkableNodes.get(labels.get("ZZ").get(0));

        List<MazePortal> portals = labels.values().stream()
                .filter(list -> list.size() == 2)
                .map(list -> new MazePortal(list.get(0), list.get(1)))
                .collect(Collectors.toList());

        connections = walkableNodes.values().stream()
                .collect(Collectors.toMap(MazeNode::getKey, node -> makeConnections(node, walkableNodes, portals)));
    }

    @Override
    public Set<MazeNode> getConnections(MazeNode node) {
        return connections.getOrDefault(node.getKey(), Set.of());
    }

    private static Set<MazeNode> makeConnections(MazeNode node, Map<Point2D, MazeNode> walkableNodes, List<MazePortal> portals) {
        Point2D point = node.getKey();

        Stream<Point2D> neighbours = Arrays.stream(Direction2D.values())
                .map(point::addDirection);
        Stream<Point2D> portalPoints = portals.stream()
                .map(p -> p.otherEnd(point));

        return Stream.concat(neighbours, portalPoints)
                .map(walkableNodes::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    private static Map<String, List<Point2D>> parseLabels(Map<Point2D, Character> input) {
        List<Point2D> letters = input.entrySet().stream()
                .filter(entry -> Character.isAlphabetic(entry.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        Map<String, List<Point2D>> labels = new HashMap<>();
        for (Point2D letter : letters) {
            for (Direction2D direction : Direction2D.values()) {
                Point2D point = letter.addDirection(direction);
                if (Character.valueOf('.').equals(input.get(point))) {
                    Point2D otherLetter = letter.addDirection(direction.opposite());
                    String key = makeString(input.get(letter), input.get(otherLetter));
                    List<Point2D> points = labels.computeIfAbsent(key, k -> new ArrayList<>());
                    points.add(point);
                }
            }
        }

        return labels;
    }

    private static String makeString(char a, char b) {
        return a < b
                ? "" + a + b
                : "" + b + a;
    }

    public MazeNode getStart() {
        return start;
    }

    public MazeNode getEnd() {
        return end;
    }
}
