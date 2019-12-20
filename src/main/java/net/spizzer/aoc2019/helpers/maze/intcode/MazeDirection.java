package net.spizzer.aoc2019.helpers.maze.intcode;

import net.spizzer.aoc2019.common.ValueBase;
import net.spizzer.aoc2019.helpers.geometry2d.Direction2D;
import net.spizzer.aoc2019.helpers.geometry2d.Point2D;

public enum MazeDirection implements ValueBase<Integer> {
    NORTH(1, Direction2D.U),
    SOUTH(2, Direction2D.D),
    WEST(3, Direction2D.L),
    EAST(4, Direction2D.R);

    private final int value;
    private final Direction2D direction;

    MazeDirection(int value, Direction2D direction) {
        this.value = value;
        this.direction = direction;
    }

    public Point2D fromPoint(Point2D origin) {
        return origin.addDirection(direction);
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
