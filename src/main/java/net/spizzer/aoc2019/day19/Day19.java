package net.spizzer.aoc2019.day19;

import net.spizzer.aoc2019.AbstractDay;
import net.spizzer.aoc2019.intcode.IntcodeComputer;
import net.spizzer.aoc2019.utils.ParseUtils;

import java.util.List;
import java.util.stream.LongStream;

public class Day19 extends AbstractDay<long[], Integer, Void> {
    @Override
    public int getDay() {
        return 19;
    }

    @Override
    public long[] parseInput(List<String> lines) {
        return ParseUtils.parseProgram(lines);
    }

    @Override
    public Integer solveFirstStar(long[] program) {
        long count = LongStream.range(0, 50).map(
                x -> LongStream.range(0, 50)
                        .filter(y -> isOnTractorBeam(program, x, y))
                        .count()
        ).sum();
        return Math.toIntExact(count);
    }

    private static boolean isOnTractorBeam(long[] program, long x, long y) {
        IntcodeComputer computer = new IntcodeComputer(program);
        computer.run(x);
        computer.run(y);
        return computer.getLastIntOutput() == 1;
    }

    @Override
    public Void solveSecondStar(long[] longs) {
        return null;
    }

    public static void main(String[] args) {
        new Day19().runDay();
    }
}
