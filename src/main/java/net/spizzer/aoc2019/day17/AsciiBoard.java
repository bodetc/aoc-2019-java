package net.spizzer.aoc2019.day17;

import net.spizzer.aoc2019.common.Printable;
import net.spizzer.aoc2019.helpers.geometry2d.Direction2D;
import net.spizzer.aoc2019.helpers.geometry2d.Point2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class AsciiBoard {
    private final Map<Point2D, AsciiTile> map;

    AsciiBoard(int[] values) {
        map = AsciiTile.fromValues(values);
    }

    int getIntersectionArea() {
        List<Point2D> intersections = getIntersections();
        return intersections.stream()
                .mapToInt(point -> point.x * point.y)
                .sum();
    }

    private List<Point2D> getIntersections() {
        return map.entrySet().stream()
                .filter(entry -> entry.getValue() == AsciiTile.SCAFFOLD)
                .map(Map.Entry::getKey)
                .filter(this::isIntersection)
                .collect(Collectors.toList());
    }

    private boolean isIntersection(Point2D point) {
        List<AsciiTile> collect = Stream.of(Direction2D.values())
                .map(point::addDirection)
                .map(map::get)
                .collect(Collectors.toList());
        return collect.stream()
                .filter(AsciiTile.SCAFFOLD::equals)
                .count() == 4;
    }

    void printBoard() {
        Printable.print(map);
    }

    String getRobotPath() {
        List<String> instructions = new ArrayList<>();
        Point2D robot = map.entrySet().stream()
                .filter(entry -> entry.getValue() == AsciiTile.UP)
                .map(Map.Entry::getKey)
                .findAny()
                .orElseThrow();
        Direction2D direction = Direction2D.U;
        int segment = 0;
        while (true) {
            // Straight ahead
            if(isScaffold(robot, direction)) {
                segment++;
                robot = robot.addDirection(direction);
            // To the left
            } else if (isScaffold(robot, direction.toLeft())) {
                instructions.add(String.valueOf(segment));
                instructions.add("L");

                direction=direction.toLeft();
                segment=0;
            // To the right
            } else if (isScaffold(robot, direction.toRight())) {
                instructions.add(String.valueOf(segment));
                instructions.add("R");

                direction=direction.toRight();
                segment=0;
            // We are now finished
            } else {
                return String.join(",", instructions);
            }
        }
    }

    private boolean isScaffold(Point2D robot, Direction2D direction) {
        return map.getOrDefault(robot.addDirection(direction), AsciiTile.EMPTY)==AsciiTile.SCAFFOLD;
    }
}
