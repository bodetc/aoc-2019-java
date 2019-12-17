package net.spizzer.aoc2019.day15;

import net.spizzer.aoc2019.AbstractDay;
import net.spizzer.aoc2019.intcode.maze.MazeSolver;
import net.spizzer.aoc2019.utils.ParseUtils;

import java.util.List;

public class Day15 extends AbstractDay<MazeSolver, Integer, Integer> {
    @Override
    public int getDay() {
        return 15;
    }

    @Override
    public MazeSolver parseInput(List<String> lines) {
        long[] program = ParseUtils.parseProgram(lines);
        return new MazeSolver(program);
    }

    @Override
    public Integer solveFirstStar(MazeSolver mazeSolver) {
        return mazeSolver.timeToTarget();
    }

    @Override
    public Integer solveSecondStar(MazeSolver mazeSolver) {
        return mazeSolver.timeToExplore();
    }
}
