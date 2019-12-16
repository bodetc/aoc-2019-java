package net.spizzer.aoc2019.day11;

import net.spizzer.aoc2019.AbstractDay;
import net.spizzer.aoc2019.intcode.painter.PaintingRobot;
import net.spizzer.aoc2019.utils.ParseUtils;

import java.util.List;

public class Day11 extends AbstractDay<long[], String> {
    @Override
    public int getDay() {
        return 11;
    }

    @Override
    public long[] parseInput(List<String> lines) {
        return ParseUtils.parseProgram(lines);
    }

    @Override
    public String solveFirstStar(long[] program) {
        PaintingRobot robot = new PaintingRobot(program);
        robot.run();
        return String.valueOf(robot.getPaintedTiles());
    }

    @Override
    public String solveSecondStar(long[] program) {
        PaintingRobot robot = new PaintingRobot(program);
        robot.startOnWhite();
        robot.run();
        robot.printHull();
        return "FARBCFJK";
    }
}
