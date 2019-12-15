package net.spizzer.aoc2019.geometry2d;

import net.spizzer.aoc2019.common.ValueBase;

public enum Direction2D implements ValueBase<Integer> {
    U(0),
    R(1),
    D(2),
    L(3);

    private final int value;

    Direction2D(int value) {
        this.value = value;
    }

    public boolean isHorizontal() {
        return this == R || this == L;
    }

    public boolean isVertical() {
        return this == U || this == D;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public Direction2D toLeft() {
        return ValueBase.fromValue(values(), Math.floorMod((value - 1), 4));
    }

    public Direction2D toRight() {
        return ValueBase.fromValue(values(), Math.floorMod((value + 1), 4));
    }
}
