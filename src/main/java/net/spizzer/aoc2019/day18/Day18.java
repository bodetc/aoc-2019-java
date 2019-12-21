package net.spizzer.aoc2019.day18;

import net.spizzer.aoc2019.AbstractDay;
import net.spizzer.aoc2019.helpers.maze.keys.KeyMazeSolver;

import java.util.List;

public class Day18 extends AbstractDay<List<String>, Integer, Void> {
    @Override
    public int getDay() {
        return 18;
    }

    @Override
    public List<String> parseInput(List<String> lines) {
        return lines;
    }

    @Override
    public Integer solveFirstStar(List<String> lines) {
        KeyMazeSolver keyMazeSolver = new KeyMazeSolver(lines);
        return keyMazeSolver.timeForAllKeys();
    }

    @Override
    public Void solveSecondStar(List<String> lines) {
        return null;
    }

    public static void main(String[] args) {
        new Day18().runDay();
    }

}
