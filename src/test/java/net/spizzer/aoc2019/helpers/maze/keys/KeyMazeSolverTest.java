package net.spizzer.aoc2019.helpers.maze.keys;

import net.spizzer.aoc2019.helpers.geometry2d.Point2D;
import net.spizzer.aoc2019.utils.ParseUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class KeyMazeSolverTest {

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                arguments("helpers/maze/keys/test1.txt", 8),
                arguments("helpers/maze/keys/test2.txt", 86),
                arguments("helpers/maze/keys/test3.txt", 132),
                arguments("helpers/maze/keys/test4.txt", 136),
                arguments("helpers/maze/keys/test5.txt", 81),
                arguments("helpers/maze/keys/test6.txt", 8),
                arguments("helpers/maze/keys/test7.txt", 24),
                arguments("helpers/maze/keys/test8.txt", 32),
                arguments("helpers/maze/keys/test9.txt", 72)
        );
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void testTimeToTarget(String path, Integer steps) {
        List<String> lines = ParseUtils.readLines(path);
        Map<Point2D, Character> input = ParseUtils.linesToCharMap(lines);
        KeyMazeSolver solver = new KeyMazeSolver(input);
        assertEquals(steps, solver.timeForAllKeys());
    }
}