package net.spizzer.aoc2019.day16;

import net.spizzer.aoc2019.utils.ParseUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Day16Test {

    @Test
    void testSkip() {
        int[] ints = ParseUtils.lineToIntegers("98765432109876543210");
        assertEquals(21098765, Day16.Ndigits(ints, 7, 8));
    }

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                arguments("12345678", 48226158),
                arguments("48226158", 34040438),
                arguments("34040438", 3415518),
                arguments("03415518", 1029498)
        );
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void applyPhase(String input, int expected) {
        int[] ints = ParseUtils.lineToIntegers(input);
        int[] output = Day16.applyPhase(ints);
        int digits = Day16.Ndigits(output, 0, 8);
        assertEquals(expected, digits);
    }

    static Stream<Arguments> dataProviderFirstStar() {
        return Stream.of(
                arguments("80871224585914546619083218645595", 24176176),
                arguments("19617804207202209144916044189917", 73745418),
                arguments("69317163492948606335995924319873", 52432133)
        );
    }

    @ParameterizedTest
    @MethodSource("dataProviderFirstStar")
    void testFirstStar(String input, int expected) {
        assertEquals(expected, new Day16().solveFirstStar(ParseUtils.lineToIntegers(input)));
    }

    static Stream<Arguments> dataProviderSecondStar() {
        return Stream.of(
                arguments("03036732577212944063491565474664", 84462026),
                arguments("02935109699940807407585447034323", 78725270),
                arguments("03081770884921959731165446850517", 53553731)
        );
    }

    @ParameterizedTest
    @MethodSource("dataProviderSecondStar")
    void testSecondStar(String input, int expected) {
        assertEquals(expected, new Day16().solveSecondStar(ParseUtils.lineToIntegers(input)));
    }
}