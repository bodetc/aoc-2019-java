package net.spizzer.aoc2019.helpers.maze.intcode;

import net.spizzer.aoc2019.common.Printable;
import net.spizzer.aoc2019.common.ValueBase;

public enum IntcodeMazeTile implements ValueBase<Integer>, Printable {
    WALL(0, '█'),
    EMPTY(1, ' '),
    TARGET(2, 'X');

    private final int value;
    private final char print;

    IntcodeMazeTile(int value, char print) {
        this.value = value;
        this.print = print;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public char getPrintChar() {
        return print;
    }

    public static IntcodeMazeTile fromValue(int value) {
        return ValueBase.fromValue(values(), value);
    }
}
