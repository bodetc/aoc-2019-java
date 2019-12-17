package net.spizzer.aoc2019.intcode.painter;

import net.spizzer.aoc2019.common.Color;
import net.spizzer.aoc2019.common.HashMapWithDefault;
import net.spizzer.aoc2019.common.Printable;
import net.spizzer.aoc2019.helpers.geometry2d.Direction2D;
import net.spizzer.aoc2019.helpers.geometry2d.Point2D;
import net.spizzer.aoc2019.helpers.geometry2d.Vector2D;
import net.spizzer.aoc2019.intcode.IntcodeComputer;
import net.spizzer.aoc2019.intcode.ReturnReason;

import java.util.Map;

public class PaintingRobot {

    private final IntcodeComputer computer;
    private final Map<Point2D, Color> hull;
    private Direction2D direction;
    private Point2D position;

    public PaintingRobot(long[] instructions) {
        this.computer = new IntcodeComputer(instructions);
        this.hull = new HashMapWithDefault<>(Color.BLACK);

        direction = Direction2D.U;
        position = Point2D.ORIGIN;
    }

    public void run() {
        ReturnReason returnReason;
        do {
            returnReason = runOnce();
        } while (returnReason != ReturnReason.EXIT);
    }

    public void startOnWhite() {
        hull.put(Point2D.ORIGIN, Color.WHITE);
    }

    private ReturnReason runOnce() {
        Color currentColor = hull.get(position);
        ReturnReason returnReason = computer.run(currentColor.getValue());
        long[] output = computer.getOutput();

        // Paint panel
        hull.put(position, Color.fromValue(Math.toIntExact(output[0])));

        // Rotate
        if (output[1] == 0) {
            direction = direction.toLeft();
        } else {
            direction = direction.toRight();
        }

        // Move
        position = position.addDirection(direction);

        // Clear output
        computer.clearOutput();

        return returnReason;
    }

    public long getPaintedTiles() {
        return hull.size();
    }

    public void printHull() {
        Printable.print(hull, true);
    }
}
