package net.spizzer.aoc2019.utils;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ParseUtils {
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

    public static List<Long> linesToLong(List<String> lines) {
        return lines.stream()
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }

    public static List<Integer> linesToInteger(List<String> lines) {
        return lines.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
