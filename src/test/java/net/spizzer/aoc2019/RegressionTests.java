package net.spizzer.aoc2019;

import net.spizzer.aoc2019.day01.Day01;
import net.spizzer.aoc2019.day03.Day03;
import net.spizzer.aoc2019.day04.Day04;
import net.spizzer.aoc2019.day06.Day06;
import net.spizzer.aoc2019.day08.Day08;
import net.spizzer.aoc2019.day10.Day10;
import net.spizzer.aoc2019.day12.Day12;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class RegressionTests {

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                arguments(new Day01(), 3286680, 4927158),
                arguments(new Day03(), 860, 9238),
                arguments(new Day04(), 454L, 288L),
                arguments(new Day06(), 245089, 511),
                arguments(new Day08(), "2159", "CJZHR"),
                arguments(new Day10(), 214, 502),
                arguments(new Day12(), 6423L, 327636285682704L)
        );
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void regressionTestFirstStar(AbstractDay day, Object firstStar, Object secondStar) {
        assertEquals(day.solveFirstStar(), firstStar);
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void regressionTestSecondStar(AbstractDay day, Object firstStar, Object secondStar) {
        assertEquals(day.solveSecondStar(), secondStar);
    }
}