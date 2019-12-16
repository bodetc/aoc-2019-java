package net.spizzer.aoc2019.common;

public enum Color implements ValueBase<Integer>, Printable {
    BLACK(0, ' '),
    WHITE(1, '█'),
    TRANSPARENT(2, ' ');

    private final int value;
    private final char print;

    Color(int value, char print) {
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

    public static Color fromValue(int value) {
        return ValueBase.fromValue(values(), value);
    }
}
