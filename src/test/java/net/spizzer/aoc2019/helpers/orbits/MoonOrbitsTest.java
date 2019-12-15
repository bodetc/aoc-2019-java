package net.spizzer.aoc2019.helpers.orbits;

import net.spizzer.aoc2019.common.Point3D;
import net.spizzer.aoc2019.utils.ParseUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class MoonOrbitsTest {
    static Stream<Arguments> dataProviderTimesteps() {
        return Stream.of(
                arguments("helpers/orbits/test-input1.txt", "helpers/orbits/test-results1.txt"),
                arguments("helpers/orbits/test-input2.txt", "helpers/orbits/test-results2.txt")
        );
    }

    @ParameterizedTest
    @MethodSource("dataProviderTimesteps")
    void testTimesteps(String inputPath, String resultPath) {
        List<String> lines = ParseUtils.readLines(resultPath);
        for (int i = 0; i < lines.size(); i += 5) {
            int step = getStep(lines.get(i));
            MoonOrbits moonOrbits = getMoonOrbits(inputPath);
            moonOrbits.timesteps(step);

            Moon moon1 = getMoon(lines.get(i + 1));
            Moon moon2 = getMoon(lines.get(i + 2));
            Moon moon3 = getMoon(lines.get(i + 3));
            Moon moon4 = getMoon(lines.get(i + 4));

            List<Moon> actual = moonOrbits.getMoons();

            assertEquals(moon1, actual.get(0));
            assertEquals(moon2, actual.get(1));
            assertEquals(moon3, actual.get(2));
            assertEquals(moon4, actual.get(3));
        }
    }

    static Stream<Arguments> dataProviderEnergy() {
        return Stream.of(
                arguments("helpers/orbits/test-input1.txt", 10, 179),
                arguments("helpers/orbits/test-input2.txt", 100, 1940)
        );
    }

    @ParameterizedTest
    @MethodSource("dataProviderEnergy")
    void testEnergy(String inputPath, int timesteps, int energy) {
        MoonOrbits moonOrbits = getMoonOrbits(inputPath);
        moonOrbits.timesteps(timesteps);
        assertEquals(energy, moonOrbits.energy());
    }

    static Stream<Arguments> dataFindCycleTime() {
        return Stream.of(
                arguments("helpers/orbits/test-input1.txt", 2772L),
                arguments("helpers/orbits/test-input2.txt", 4686774924L)
        );
    }

    @ParameterizedTest
    @MethodSource("dataFindCycleTime")
    void testFindCycleTime(String inputPath, long cycle) {
        MoonOrbits moonOrbits = getMoonOrbits(inputPath);
        assertEquals(cycle, moonOrbits.findCycleTime());
    }

    private static final Pattern STEPS_FORMAT = Pattern.compile("After ([-+]?\\d+) steps:");
    private static final Pattern ASTEROID_FORMAT = Pattern.compile("pos=<x=\\s*([-+]?\\d+), y=\\s*([-+]?\\d+), z=\\s*([-+]?\\d+)>, vel=<x=\\s*([-+]?\\d+), y=\\s*([-+]?\\d+), z=\\s*([-+]?\\d+)>");

    private static int getStep(String line) {
        List<String> groups = ParseUtils.getGroups(STEPS_FORMAT, line);
        return Integer.parseInt(groups.get(0));
    }

    private static Moon getMoon(String line) {
        int[] input = ParseUtils.getGroups(ASTEROID_FORMAT, line)
                .stream()
                .mapToInt(Integer::parseInt)
                .toArray();

        Point3D position = new Point3D(input[0], input[1], input[2]);
        Point3D velocity = new Point3D(input[3], input[4], input[5]);
        return new Moon(position, velocity);
    }

    private MoonOrbits getMoonOrbits(String inputPath) {
        List<Point3D> coordinates = ParseUtils.readLines(inputPath)
                .stream()
                .map(ParseUtils::parseCoordinates)
                .collect(Collectors.toList());
        return new MoonOrbits(coordinates);
    }
}