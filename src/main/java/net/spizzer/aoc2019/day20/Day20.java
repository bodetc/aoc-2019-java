package net.spizzer.aoc2019.day20;

import net.spizzer.aoc2019.AbstractDay;
import net.spizzer.aoc2019.helpers.maze.portals.MazeSolver;

import java.util.List;

public class Day20 extends AbstractDay<List<String>, Integer, Integer> {
    @Override
    public int getDay() {
        return 20;
    }

    @Override
    public List<String> parseInput(List<String> lines) {
        return lines;
    }

    @Override
    public Integer solveFirstStar(List<String> lines) {
        MazeSolver mazeSolver = new MazeSolver(lines, true);
        return mazeSolver.timeToTarget();
    }

    @Override
    public Integer solveSecondStar(List<String> lines) {
        MazeSolver mazeSolver = new MazeSolver(lines, false);
        return mazeSolver.timeToTarget();
    }

    public static void main(String[] args) {
        new Day20().runDay();
    }
}
