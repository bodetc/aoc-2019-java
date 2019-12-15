package net.spizzer.aoc2019;

import net.spizzer.aoc2019.day01.Day01;
import net.spizzer.aoc2019.day03.Day03;
import net.spizzer.aoc2019.day04.Day04;
import net.spizzer.aoc2019.day06.Day06;
import net.spizzer.aoc2019.day08.Day08;

import java.util.List;

public class Aoc2019 {
    private static final List<AbstractDay> days = List.of(
            new Day01(),
            new Day03(),
            new Day04(),
            new Day06(),
            new Day08()
    );

    public static void main(String[] args) {
        for (AbstractDay day : days) {
            day.runDay();
        }
    }
}
