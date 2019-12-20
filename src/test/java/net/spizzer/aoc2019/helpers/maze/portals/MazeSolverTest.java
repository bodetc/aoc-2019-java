package net.spizzer.aoc2019.helpers.maze.portals;

import net.spizzer.aoc2019.utils.ParseUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class MazeSolverTest {

    static Stream<Arguments> dataProviderBestAsteroid() {
        return Stream.of(
                arguments("helpers/maze/portals/test1.txt", 23),
                arguments("helpers/maze/portals/test2.txt", 58)
        );
    }

    @ParameterizedTest
    @MethodSource("dataProviderBestAsteroid")
    void TestTimeToTarget(String path, int steps) {
        List<String> lines = ParseUtils.readLines(path);
        MazeSolver solver = new MazeSolver(lines);
        assertEquals(steps, solver.timeToTarget());
    }
}