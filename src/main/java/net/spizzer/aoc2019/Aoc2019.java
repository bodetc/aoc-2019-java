package net.spizzer.aoc2019;

import net.spizzer.aoc2019.day01.Day01;
import net.spizzer.aoc2019.day02.Day02;
import net.spizzer.aoc2019.day03.Day03;
import net.spizzer.aoc2019.day04.Day04;
import net.spizzer.aoc2019.day05.Day05;
import net.spizzer.aoc2019.day06.Day06;
import net.spizzer.aoc2019.day07.Day07;
import net.spizzer.aoc2019.day08.Day08;
import net.spizzer.aoc2019.day09.Day09;
import net.spizzer.aoc2019.day10.Day10;
import net.spizzer.aoc2019.day11.Day11;
import net.spizzer.aoc2019.day12.Day12;
import net.spizzer.aoc2019.day13.Day13;
import net.spizzer.aoc2019.day14.Day14;
import net.spizzer.aoc2019.day15.Day15;
import net.spizzer.aoc2019.day16.Day16;

import java.util.List;

public class Aoc2019 {
    private static final List<AbstractDay> days = List.of(
            new Day01(),
            new Day02(),
            new Day03(),
            new Day04(),
            new Day05(),
            new Day06(),
            new Day07(),
            new Day08(),
            new Day09(),
            new Day10(),
            new Day11(),
            new Day12(),
            new Day13(),
            new Day14(),
            new Day15(),
            new Day16()
    );

    public static void main(String[] args) {
        for (AbstractDay day : days) {
            day.runDay();
        }
    }
}
