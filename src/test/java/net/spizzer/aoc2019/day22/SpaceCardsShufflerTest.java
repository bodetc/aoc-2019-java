package net.spizzer.aoc2019.day22;

import net.spizzer.aoc2019.utils.ParseUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class SpaceCardsShufflerTest {

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                arguments("day22/test1.txt", new int[]{0, 3, 6, 9, 2, 5, 8, 1, 4, 7}),
                arguments("day22/test2.txt", new int[]{3, 0, 7, 4, 1, 8, 5, 2, 9, 6}),
                arguments("day22/test3.txt", new int[]{6, 3, 0, 7, 4, 1, 8, 5, 2, 9}),
                arguments("day22/test4.txt", new int[]{9, 2, 5, 8, 1, 4, 7, 0, 3, 6})
        );
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void testShuffle(String path, int[] result) {
        List<String> lines = ParseUtils.readLines(path);
        SpaceCards cards = new SpaceCardsShuffler(10).shuffle(lines);
        assertEquals(SpaceCards.fromArray(result), cards);
    }
}