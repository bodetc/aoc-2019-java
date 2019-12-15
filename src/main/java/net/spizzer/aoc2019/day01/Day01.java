package net.spizzer.aoc2019.day01;

import com.google.common.annotations.VisibleForTesting;
import net.spizzer.aoc2019.AbstractDay;
import net.spizzer.aoc2019.utils.ParseUtils;

import java.util.List;

public class Day01 extends AbstractDay<List<Integer>, Integer> {
    @Override
    public int getDay() {
        return 1;
    }

    @Override
    public List<Integer> parseInput(List<String> lines) {
        return ParseUtils.linesToIntegers(lines);
    }

    @Override
    public Integer solveFirstStar(List<Integer> input) {
        return input.stream()
                .mapToInt(Day01::computeFuel)
                .sum();
    }

    @Override
    public Integer solveSecondStar(List<Integer> input) {
        return input.stream()
                .mapToInt(Day01::computeFuelForFuel)
                .sum();
    }

    @VisibleForTesting
    static Integer computeFuel(Integer mass) {
        return Math.max(mass / 3 - 2, 0);
    }

    @VisibleForTesting
    static Integer computeFuelForFuel(Integer mass) {
        Integer fuel = computeFuel(mass);
        return fuel > 0
                ? fuel + computeFuelForFuel(fuel)
                : 0;
    }
}
