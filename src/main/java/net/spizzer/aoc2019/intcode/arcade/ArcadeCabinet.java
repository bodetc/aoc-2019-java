package net.spizzer.aoc2019.intcode.arcade;

import net.spizzer.aoc2019.common.HashMapWithDefault;
import net.spizzer.aoc2019.common.Printable;
import net.spizzer.aoc2019.common.Reject;
import net.spizzer.aoc2019.helpers.geometry2d.Point2D;
import net.spizzer.aoc2019.intcode.IntcodeComputer;

import java.util.Map;

public class ArcadeCabinet {
    private final IntcodeComputer computer;
    private final Map<Point2D, Tile> screen = new HashMapWithDefault<>(Tile.EMPTY);
    private int score = 0;

    public ArcadeCabinet(long[] instructions) {
        computer = new IntcodeComputer(instructions);
    }

    public void insertCoins() {
        computer.setProgram(0, 2);
    }

    public void run() {
        run(Joystick.NEUTRAL);
    }

    public void run(Joystick joystick) {
        computer.run(joystick.getValue());
        int[] output = computer.getIntOutput();

        for (int i = 0; i < output.length; i += 3) {
            int x = output[i];
            int y = output[i + 1];
            int z = output[i + 2];
            if (x == -1 && y == 0) {
                score = z;
            } else {
                screen.put(new Point2D(x, y), Tile.fromValue(z));
            }
        }

        computer.clearOutput();
    }

    public void printScreen() {
        Printable.print(screen);
        System.out.println("Score: " + score);
    }

    Point2D getPosition(Tile tile) {
        Reject.ifDifferent(1L, getCount(tile), "You should ask position of elements that are unique!");
        return screen.entrySet().stream()
                .filter(entry -> tile.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findAny()
                .orElseThrow();
    }

    public long getCount(Tile tile) {
        return screen.values().stream().filter(tile::equals).count();
    }

    public int getScore() {
        return score;
    }
}
