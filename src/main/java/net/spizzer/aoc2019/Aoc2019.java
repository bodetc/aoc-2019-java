package net.spizzer.aoc2019;

import net.spizzer.aoc2019.day01.Day01;

import java.util.List;

public class Aoc2019 {
    private static final List<AbstractDay> days = List.of(
            new Day01()
    );

    public static void main(String[] args) {
        for (AbstractDay day : days) {
            day.runDay();
        }
    }
}
