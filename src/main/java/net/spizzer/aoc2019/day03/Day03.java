package net.spizzer.aoc2019.day03;

import net.spizzer.aoc2019.AbstractDay;
import net.spizzer.aoc2019.common.Pair;
import net.spizzer.aoc2019.common.Reject;
import net.spizzer.aoc2019.helpers.geometry2d.Line2D;
import net.spizzer.aoc2019.helpers.geometry2d.Point2D;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

public class Day03 extends AbstractDay<Pair<Line2D, Line2D>, Integer, Integer> {
    @Override
    public int getDay() {
        return 3;
    }

    @Override
    public Pair<Line2D, Line2D> parseInput(List<String> lines) {
        Reject.ifDifferent(2, lines.size(), "Input must consist of two lines.");
        Line2D a = new Line2D(lines.get(0));
        Line2D b = new Line2D(lines.get(1));
        return new Pair<>(a, b);
    }

    @Override
    public Integer solveFirstStar(Pair<Line2D, Line2D> input) {
        return findIntersection(input.getA(), input.getB(), Point2D.ORIGIN::taxiDistance);
    }

    @Override
    public Integer solveSecondStar(Pair<Line2D, Line2D> input) {
        return findIntersection(input.getA(), input.getB(),
                point -> input.getA().delay(point) + input.getB().delay(point));
    }

    private static int findIntersection(Line2D a, Line2D b, ToIntFunction<Point2D> distance) {
        return a.intersect(b) // Find intersections
                .filter(Predicate.not(Point2D.ORIGIN::equals)) // Remove origin
                .mapToInt(distance) // Get distances
                .min() // Get closest distance
                .orElseThrow();
    }
}
