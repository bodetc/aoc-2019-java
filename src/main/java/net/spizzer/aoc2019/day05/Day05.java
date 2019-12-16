package net.spizzer.aoc2019.day05;

import net.spizzer.aoc2019.AbstractDay;
import net.spizzer.aoc2019.intcode.early.EarlyIntcodeComputer;
import net.spizzer.aoc2019.utils.ParseUtils;

import java.util.List;

public class Day05 extends AbstractDay<long[], Long, Long> {
    @Override
    public int getDay() {
        return 5;
    }

    @Override
    public long[] parseInput(List<String> lines) {
        return ParseUtils.parseProgram(lines);
    }

    @Override
    public Long solveFirstStar(long[] program) {
        return EarlyIntcodeComputer.runIO(program,1);
    }

    @Override
    public Long solveSecondStar(long[] program) {
        return EarlyIntcodeComputer.runIO(program,5);
    }
}
