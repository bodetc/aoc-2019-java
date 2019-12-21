package net.spizzer.aoc2019.day18;

import net.spizzer.aoc2019.AbstractDay;
import net.spizzer.aoc2019.helpers.geometry2d.Point2D;
import net.spizzer.aoc2019.helpers.maze.keys.KeyMazeSolver;
import net.spizzer.aoc2019.utils.ParseUtils;

import java.util.List;
import java.util.Map;

public class Day18 extends AbstractDay<Map<Point2D, Character>, Integer, Void> {
    @Override
    public int getDay() {
        return 18;
    }

    @Override
    public Map<Point2D, Character> parseInput(List<String> lines) {
        return ParseUtils.linesToCharMap(lines);
    }

    @Override
    public Integer solveFirstStar(Map<Point2D, Character> input) {
        KeyMazeSolver keyMazeSolver = new KeyMazeSolver(input);
        return keyMazeSolver.timeForAllKeys();
    }

    @Override
    public Void solveSecondStar(Map<Point2D, Character> input) {
        return null;
    }

    public static void main(String[] args) {
        new Day18().runDay();
    }

}
