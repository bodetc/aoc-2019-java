package net.spizzer.aoc2019.day09;

import net.spizzer.aoc2019.AbstractDay;
import net.spizzer.aoc2019.intcode.IntcodeComputer;
import net.spizzer.aoc2019.utils.ParseUtils;

import java.util.List;

public class Day09 extends AbstractDay<long[], Long> {
    @Override
    public int getDay() {
        return 9;
    }

    @Override
    public long[] parseInput(List<String> lines) {
        return ParseUtils.parseProgram(lines);
    }

    @Override
    public Long solveFirstStar(long[] program) {
        IntcodeComputer computer = new IntcodeComputer(program);
        computer.run(1);
        return computer.getLastOutput();
    }

    @Override
    public Long solveSecondStar(long[] program) {
        IntcodeComputer computer = new IntcodeComputer(program);
        computer.run(2);
        return computer.getLastOutput();
    }
}
