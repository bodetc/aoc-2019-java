package net.spizzer.aoc2019.day01;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Day01Test {

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                arguments(12, 2, 2),
                arguments(14, 2, 2),
                arguments(1969, 654, 966),
                arguments(100756, 33583, 50346)
        );
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void computeFuel(int mass, int fuel, int fuelForFuel) {
        assertEquals(fuel, Day01.computeFuel(mass));
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void computeFuelForFuel(int mass, int fuel, int fuelForFuel) {
        assertEquals(fuelForFuel, Day01.computeFuelForFuel(mass));
    }
}