package net.spizzer.aoc2019.day15;

import net.spizzer.aoc2019.AbstractDay;
import net.spizzer.aoc2019.helpers.maze.intcode.IntcodeMazeSolver;
import net.spizzer.aoc2019.utils.ParseUtils;

import java.util.List;

public class Day15 extends AbstractDay<IntcodeMazeSolver, Integer, Integer> {
    @Override
    public int getDay() {
        return 15;
    }

    @Override
    public IntcodeMazeSolver parseInput(List<String> lines) {
        long[] program = ParseUtils.parseProgram(lines);
        return new IntcodeMazeSolver(program);
    }

    @Override
    public Integer solveFirstStar(IntcodeMazeSolver mazeSolver) {
        return mazeSolver.timeToTarget();
    }

    @Override
    public Integer solveSecondStar(IntcodeMazeSolver mazeSolver) {
        return mazeSolver.timeToExplore();
    }
}
