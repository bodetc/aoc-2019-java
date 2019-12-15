package net.spizzer.aoc2019.day03;

import net.spizzer.aoc2019.common.Pair;
import net.spizzer.aoc2019.geometry2d.Line2D;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Day03Test {

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                arguments("R8,U5,L5,D3", "U7,R6,D4,L4", 6, 30),
                arguments("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83", 159, 610),
                arguments("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7", 135, 410)
        );
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void solveFirstStar(String a, String b, int taxiDistance, int delay) {
        Day03 day = new Day03();
        Pair<Line2D, Line2D> input = day.parseInput(List.of(a, b));
        assertEquals(taxiDistance, day.solveFirstStar(input));
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void solveSecondStar(String a, String b, int taxiDistance, int delay) {
        Day03 day = new Day03();
        Pair<Line2D, Line2D> input = day.parseInput(List.of(a, b));
        assertEquals(delay, day.solveSecondStar(input));
    }
}