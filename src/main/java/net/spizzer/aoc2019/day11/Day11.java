package net.spizzer.aoc2019.day11;

import net.spizzer.aoc2019.AbstractDay;
import net.spizzer.aoc2019.intcode.painter.PaintingRobot;
import net.spizzer.aoc2019.utils.ParseUtils;

import java.util.List;

public class Day11 extends AbstractDay<PaintingRobot, Long, String> {
    @Override
    public int getDay() {
        return 11;
    }

    @Override
    public PaintingRobot parseInput(List<String> lines) {
        long[] program = ParseUtils.parseProgram(lines);
        return new PaintingRobot(program);
    }

    @Override
    public Long solveFirstStar(PaintingRobot robot) {
        robot.run();
        return robot.getPaintedTiles();
    }

    @Override
    public String solveSecondStar(PaintingRobot robot) {
        robot.startOnWhite();
        robot.run();
        robot.printHull();
        return "FARBCFJK";
    }
}
