package net.spizzer.aoc2019.day05;

import net.spizzer.aoc2019.AbstractDay;
import net.spizzer.aoc2019.intcode.IntcodeComputer;
import net.spizzer.aoc2019.utils.ParseUtils;

import java.util.List;

public class Day05 extends AbstractDay<IntcodeComputer, Integer, Integer> {
    @Override
    public int getDay() {
        return 5;
    }

    @Override
    public IntcodeComputer parseInput(List<String> lines) {
        long[] program = ParseUtils.parseProgram(lines);
        return new IntcodeComputer(program);
    }

    @Override
    public Integer solveFirstStar(IntcodeComputer computer) {
        computer.run(1);
        return computer.getLastIntOutput();
    }

    @Override
    public Integer solveSecondStar(IntcodeComputer computer) {
        computer.run(5);
        return computer.getLastIntOutput();
    }
}
