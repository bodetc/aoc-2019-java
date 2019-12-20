package net.spizzer.aoc2019.helpers.maze.portals;

import net.spizzer.aoc2019.utils.ParseUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PortalMazeSolverTest {

    static Stream<Arguments> dataProviderFlatMaze() {
        return Stream.of(
                arguments("helpers/maze/portals/test1.txt", 23),
                arguments("helpers/maze/portals/test2.txt", 58),
                arguments("helpers/maze/portals/test3.txt", 77)
        );
    }

    @ParameterizedTest
    @MethodSource("dataProviderFlatMaze")
    void testFlatMaze(String path, Integer steps) {
        List<String> lines = ParseUtils.readLines(path);
        PortalMazeSolver solver = new PortalMazeSolver(lines, true);
        assertEquals(steps, solver.timeToTarget());
    }

    static Stream<Arguments> dataProviderBestAsteroid() {
        return Stream.of(
                arguments("helpers/maze/portals/test1.txt", 26),
                arguments("helpers/maze/portals/test2.txt", null),
                arguments("helpers/maze/portals/test3.txt", 396)
        );
    }

    @ParameterizedTest
    @MethodSource("dataProviderBestAsteroid")
    void TestDeepMaze(String path, Integer steps) {
        List<String> lines = ParseUtils.readLines(path);
        PortalMazeSolver solver = new PortalMazeSolver(lines, false);
        assertEquals(steps, solver.timeToTarget());
    }
}