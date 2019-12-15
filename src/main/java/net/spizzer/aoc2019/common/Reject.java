package net.spizzer.aoc2019.common;

import java.util.Objects;

public class Reject {
    public static <T> void ifDifferent(T expected, T actual, String message) {
        if (!Objects.equals(expected, actual)) {
            always(message + "\nExpected: " + expected + "\nActual: " + actual);
        }
    }

    public static void always(String message) {
        throw new IllegalStateException(message);
    }
}
