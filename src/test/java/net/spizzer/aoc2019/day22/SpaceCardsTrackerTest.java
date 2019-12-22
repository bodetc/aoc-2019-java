package net.spizzer.aoc2019.day22;

import net.spizzer.aoc2019.common.Reject;
import net.spizzer.aoc2019.utils.ParseUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class SpaceCardsTrackerTest {
    static Stream<Arguments> dataProvider() {
        Stream.Builder<Arguments> builder = Stream.builder();
        for (int i = 0; i < 10; i++) {
            builder
                    .add(arguments(List.of("deal into new stack"), new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0}, i))
                    .add(arguments(List.of("cut 3"), new int[]{3, 4, 5, 6, 7, 8, 9, 0, 1, 2}, i))
                    .add(arguments(List.of("cut -4"), new int[]{6, 7, 8, 9, 0, 1, 2, 3, 4, 5}, i))
                    .add(arguments(List.of("deal with increment 3"), new int[]{0, 7, 4, 1, 8, 5, 2, 9, 6, 3}, i))
                    .add(arguments(List.of("deal with increment 7"), new int[]{0, 3, 6, 9, 2, 5, 8, 1, 4, 7}, i))
                    .add(arguments(ParseUtils.readLines("day22/test1.txt"), new int[]{0, 3, 6, 9, 2, 5, 8, 1, 4, 7}, i))
                    .add(arguments(ParseUtils.readLines("day22/test2.txt"), new int[]{3, 0, 7, 4, 1, 8, 5, 2, 9, 6}, i))
                    .add(arguments(ParseUtils.readLines("day22/test3.txt"), new int[]{6, 3, 0, 7, 4, 1, 8, 5, 2, 9}, i))
                    .add(arguments(ParseUtils.readLines("day22/test4.txt"), new int[]{9, 2, 5, 8, 1, 4, 7, 0, 3, 6}, i));
        }
        return builder.build();
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void testForward(List<String> instructions, int[] result, int i) {
        long actual = SpaceCardsTracker.track(instructions, 10, i, 1, true);
        long expected = positionOf(result, i);
        assertEquals(expected, actual);
    }

    private static long positionOf(int[] deck, int card) {
        for (int i = 0; i < deck.length; i++) {
            if (deck[i] == card) {
                return i;
            }
        }
        throw Reject.always("Card not found!");
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void testReverse(List<String> instructions, int[] result, int i) {
        long actual = SpaceCardsTracker.track(instructions, 10, i, 1, false);
        long expected = result[i];
        assertEquals(expected, actual);
    }
}