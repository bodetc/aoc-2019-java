package net.spizzer.aoc2019.utils;

import net.spizzer.aoc2019.common.Point3D;
import net.spizzer.aoc2019.common.Reject;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParseUtils {
    public static final Pattern COMMA_SEPARATOR = Pattern.compile(",");
    private static final Pattern COORDINATES_FORMAT = Pattern.compile("<x=\\s*([-+]?\\d+), y=\\s*([-+]?\\d+), z=\\s*([-+]?\\d+)>");

    public static List<String> readLines(String path) {
        try {
            URI uri = ParseUtils.class.getClassLoader().getResource(path).toURI();
            return Files.readAllLines(Paths.get(uri));
        } catch (Exception e) {
            System.err.println("Error reading file " + path);
            e.printStackTrace();
            return List.of();
        }
    }

    public static long[] parseProgram(List<String> lines) {
        Reject.ifDifferent(1, lines.size(), "Program should be a single line");
        return lineToCommaSeparatedLongs(lines.get(0));
    }

    public static List<Integer> linesToIntegers(List<String> lines) {
        return lines.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static int[] lineToIntegers(String line) {
        return line.chars()
                .mapToObj(i -> (char) i)
                .map(c -> Character.toString(c))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static Point3D parseCoordinates(String input) {
        int[] split = getGroups(COORDINATES_FORMAT, input)
                .stream()
                .mapToInt(Integer::parseInt)
                .toArray();
        return new Point3D(split[0], split[1], split[2]);
    }

    public static List<String> getGroups(Pattern pattern, String string) {
        Matcher matcher = pattern.matcher(string);
        List<String> groups = new ArrayList<>();
        while(matcher.find()) {
            for(int i=1; i<=matcher.groupCount(); i++) {
                groups.add(matcher.group(i));
            }
        }
        return groups;
    }

    public static long[] lineToCommaSeparatedLongs(String line) {
        return parseCommaSeparatedValues(line)
                .mapToLong(Long::parseLong)
                .toArray();
    }

    private static Stream<String> parseCommaSeparatedValues(String line) {
        return COMMA_SEPARATOR.splitAsStream(line);
    }
}
