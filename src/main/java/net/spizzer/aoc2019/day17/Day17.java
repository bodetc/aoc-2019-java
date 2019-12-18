package net.spizzer.aoc2019.day17;

import net.spizzer.aoc2019.AbstractDay;
import net.spizzer.aoc2019.intcode.IntcodeComputer;

import java.util.List;

public class Day17 extends AbstractDay<IntcodeComputer, Integer, Void> {

    private final boolean silent;

    public Day17() {
        this(true);
    }

    private Day17(boolean silent) {
        this.silent = silent;
    }

    @Override
    public int getDay() {
        return 17;
    }

    @Override
    public IntcodeComputer parseInput(List<String> lines) {
        return new IntcodeComputer(lines);
    }

    @Override
    public Integer solveFirstStar(IntcodeComputer computer) {
        computer.run();
        int[] output = computer.getIntOutput();
        AsciiBoard board = new AsciiBoard(output);
        if (!silent) {
            board.printBoard();
        }

        return board.getIntersectionArea();
    }

    @Override
    public Void solveSecondStar(IntcodeComputer computer) {
        return null;
    }

    public static void main(String[] args) {
        new Day17(false).runDay();
    }
}
