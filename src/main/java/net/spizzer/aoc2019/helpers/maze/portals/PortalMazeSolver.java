package net.spizzer.aoc2019.helpers.maze.portals;

import net.spizzer.aoc2019.helpers.geometry2d.Direction2D;
import net.spizzer.aoc2019.helpers.geometry2d.Point2D;
import net.spizzer.aoc2019.helpers.maze.ConstantScorer;
import net.spizzer.aoc2019.helpers.maze.Graph;
import net.spizzer.aoc2019.helpers.maze.RouteFinder;
import net.spizzer.aoc2019.helpers.maze.RouteFinderResult;
import net.spizzer.aoc2019.utils.ParseUtils;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PortalMazeSolver implements Graph<PortalMazeNode, PortalMazeNode> {
    private final Set<Point2D> walkable;
    private final List<MazePortal> portals;

    private final PortalMazeNode start;
    private final PortalMazeNode end;

    private boolean flatWorld;

    public PortalMazeSolver(List<String> lines, boolean flatWorld) {
        this.flatWorld = flatWorld;
        Map<Point2D, Character> input = ParseUtils.linesToCharMap(lines);

        walkable = input.entrySet().stream()
                .filter(entry -> entry.getValue().equals('.'))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());


        Map<String, List<Point2D>> labels = parseLabels(input);
        start = new PortalMazeNode(labels.get("AA").get(0), 0);
        end = new PortalMazeNode(labels.get("ZZ").get(0), 0);

        portals = getPortals(labels);
    }

    public Integer timeToTarget() {
        RouteFinder<PortalMazeNode, PortalMazeNode> routeFinder = new RouteFinder<>(this, new ConstantScorer<>());
        RouteFinderResult result = routeFinder.findRoute(start,end::equals);
        return result.targetFound()
                ? result.timeToTarget()
                : null;
    }

    @Override
    public Set<PortalMazeNode> getConnections(PortalMazeNode node) {
        Point2D current = node.getPoint();

        Set<PortalMazeNode> neighbours = Arrays.stream(Direction2D.values())
                .map(current::addDirection)
                .filter(walkable::contains)
                .map(point -> new PortalMazeNode(point, node.getLevel()))
                .collect(Collectors.toSet());

        Set<PortalMazeNode> portals = this.portals.stream()
                .map(p -> p.traverse(node, flatWorld))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        Set<PortalMazeNode> result = new HashSet<>();
        result.addAll(neighbours);
        result.addAll(portals);
        return result;
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

    private List<MazePortal> getPortals(Map<String, List<Point2D>> labels) {
        List<Point2D> coordinates = labels.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        int minX = coordinates.stream().mapToInt(key -> key.x).min().orElseThrow();
        int maxX = coordinates.stream().mapToInt(key -> key.x).max().orElseThrow();
        int minY = coordinates.stream().mapToInt(key -> key.y).min().orElseThrow();
        int maxY = coordinates.stream().mapToInt(key -> key.y).max().orElseThrow();

        Predicate<Point2D> isOuter = point -> point.x == minX || point.x == maxX
                || point.y == minY || point.y == maxY;

        return labels.values().stream()
                .filter(list -> list.size() == 2)
                .map(list -> isOuter.test(list.get(0))
                        ? new MazePortal(list.get(0), list.get(1))
                        : new MazePortal(list.get(1), list.get(0)))
                .collect(Collectors.toList());
    }


    private static String makeString(char a, char b) {
        return a < b
                ? "" + a + b
                : "" + b + a;
    }
}
