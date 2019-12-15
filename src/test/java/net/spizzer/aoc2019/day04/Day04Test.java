package net.spizzer.aoc2019.day04;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Day04Test {

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                arguments(111111, true, false),
                arguments(223450, false, false),
                arguments(123789, false, false),
                arguments(112233, true, true),
                arguments(123444, true, false),
                arguments(111122, true, true)
        );
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void solveFirstStar(int password, boolean firstValid, boolean secondValid) {
        assertEquals(firstValid ? 1 : 0, new Day04().solveFirstStar(List.of(password)));
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void solveSecondStar(int password, boolean firstValid, boolean secondValid) {
        assertEquals(secondValid ? 1 : 0, new Day04().solveSecondStar(List.of(password)));
    }
}