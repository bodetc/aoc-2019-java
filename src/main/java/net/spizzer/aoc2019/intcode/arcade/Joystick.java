package net.spizzer.aoc2019.intcode.arcade;

import net.spizzer.aoc2019.common.ValueBase;

public enum Joystick implements ValueBase<Integer> {
    NEUTRAL(0),
    LEFT(-1),
    RIGHT(1),
    EXIT(99);

    private final int value;

    Joystick(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public static Joystick fromValue(int value) {
        return ValueBase.fromValue(values(), value);
    }
}
