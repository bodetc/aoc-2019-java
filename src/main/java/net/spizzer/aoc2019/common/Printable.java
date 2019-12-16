package net.spizzer.aoc2019.common;

import net.spizzer.aoc2019.helpers.geometry2d.Point2D;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface Printable {
    char getPrintChar();

    default void print() {
        System.out.print(getPrintChar());
    }

    static <T extends Printable> void print(Map<Point2D, T> screen) {
        print(screen, false);
    }

    static <T extends Printable> void print(Map<Point2D, T> screen, boolean invertScreen) {
        int minX = screen.keySet().stream().mapToInt(key -> key.x).min().orElseThrow();
        int maxX = screen.keySet().stream().mapToInt(key -> key.x).max().orElseThrow();
        int minY = screen.keySet().stream().mapToInt(key -> key.y).min().orElseThrow();
        int maxY = screen.keySet().stream().mapToInt(key -> key.y).max().orElseThrow();

        List<Integer> xRange = IntStream.rangeClosed(minX, maxX).boxed().collect(Collectors.toList());
        List<Integer> yRange = IntStream.rangeClosed(minY, maxY).boxed().collect(Collectors.toList());

        if (invertScreen) {
            Collections.reverse(yRange);
        }

        for (int y : yRange) {
            for (int x : xRange) {
                screen.get(new Point2D(x, y)).print();
            }
            System.out.println();
        }
    }
}
