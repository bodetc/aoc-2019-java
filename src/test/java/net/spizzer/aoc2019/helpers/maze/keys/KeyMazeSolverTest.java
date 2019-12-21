package net.spizzer.aoc2019.helpers.maze.keys;

import net.spizzer.aoc2019.utils.ParseUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
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
                arguments("helpers/maze/keys/test5.txt", 81)
        );
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void testTimeToTarget(String path, Integer steps) {
        List<String> lines = ParseUtils.readLines(path);
        KeyMazeSolver solver = new KeyMazeSolver(lines);
        assertEquals(steps, solver.timeForAllKeys());
    }
}