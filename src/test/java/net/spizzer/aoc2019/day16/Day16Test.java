package net.spizzer.aoc2019.day16;

import net.spizzer.aoc2019.utils.ParseUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Day16Test {

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                arguments("12345678", "48226158"),
                arguments("48226158", "34040438"),
                arguments("34040438", "03415518"),
                arguments("03415518", "01029498")
        );
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void applyPhase(String input, String expected) {
        int[] ints = ParseUtils.lineToIntegers(input);
        int[] output = Day16.applyPhase(ints);
        String digits = Day16.first8digits(output);
        assertEquals(expected, digits);
    }

    static Stream<Arguments> dataProvider100() {
        return Stream.of(
                arguments("80871224585914546619083218645595", "24176176", 100),
                arguments("19617804207202209144916044189917", "73745418", 100),
                arguments("69317163492948606335995924319873", "52432133", 100)
        );
    }

    @ParameterizedTest
    @MethodSource("dataProvider100")
    void applyPhases(String input, String expected, int amount) {
        int[] ints = ParseUtils.lineToIntegers(input);
        String digits = Day16.applyPhases(ints, amount);
        assertEquals(expected, digits);
    }
}