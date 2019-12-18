package net.spizzer.aoc2019.day09;

import net.spizzer.aoc2019.AbstractDay;
import net.spizzer.aoc2019.intcode.IntcodeComputer;
import net.spizzer.aoc2019.utils.ParseUtils;

import java.util.List;

public class Day09 extends AbstractDay<IntcodeComputer, Long, Long> {
    @Override
    public int getDay() {
        return 9;
    }

    @Override
    public IntcodeComputer parseInput(List<String> lines) {
        return new IntcodeComputer(ParseUtils.parseProgram(lines));
    }

    @Override
    public Long solveFirstStar(IntcodeComputer computer) {
        computer.run(1);
        return computer.getLastOutput();
    }

    @Override
    public Long solveSecondStar(IntcodeComputer computer) {
        computer.run(2);
        return computer.getLastOutput();
    }
}
