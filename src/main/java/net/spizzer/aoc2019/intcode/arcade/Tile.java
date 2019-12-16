package net.spizzer.aoc2019.intcode.arcade;

import net.spizzer.aoc2019.common.Printable;
import net.spizzer.aoc2019.common.ValueBase;

public enum Tile implements ValueBase<Integer>, Printable {
    EMPTY(0, ' '),
    WALL(1, '█'),
    BLOCK(2, '▒'),
    PADDLE(3, '═'),
    BALL(4, '○');

    private final int value;
    private final char print;

    Tile(int value, char print) {
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

    public static Tile fromValue(int value) {
        return ValueBase.fromValue(values(), value);
    }
}
