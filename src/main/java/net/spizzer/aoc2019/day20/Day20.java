package net.spizzer.aoc2019.day20;

import net.spizzer.aoc2019.AbstractDay;
import net.spizzer.aoc2019.helpers.maze.portals.MazeSolver;

import java.util.List;

public class Day20 extends AbstractDay<MazeSolver, Integer, Void> {
    @Override
    public int getDay() {
        return 20;
    }

    @Override
    public MazeSolver parseInput(List<String> lines) {
        return new MazeSolver(lines);
    }

    @Override
    public Integer solveFirstStar(MazeSolver mazeSolver) {
        return mazeSolver.timeToTarget();
    }

    @Override
    public Void solveSecondStar(MazeSolver mazeSolver) {
        return null;
    }

    public static void main(String[] args) {
        new Day20().runDay();
    }
}
