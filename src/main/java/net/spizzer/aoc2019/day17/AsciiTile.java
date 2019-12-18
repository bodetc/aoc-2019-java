package net.spizzer.aoc2019.day17;

import net.spizzer.aoc2019.common.Printable;
import net.spizzer.aoc2019.common.ValueBase;
import net.spizzer.aoc2019.helpers.geometry2d.Point2D;

import java.util.HashMap;
import java.util.Map;

public enum AsciiTile implements ValueBase<Integer>, Printable {
    EMPTY(46),
    SCAFFOLD(35),
    UP(94),
    DOWN(118),
    LEFT(60),
    RIGHT(62),
    NEW_LINE(10);

    private final int value;

    AsciiTile(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public char getPrintChar() {
        return (char) value;
    }

    public static AsciiTile fromValue(int value) {
        return ValueBase.fromValue(values(), value);
    }

    public static Map<Point2D, AsciiTile> fromValues(int[] values) {
        Map<Point2D, AsciiTile> map = new HashMap<>();
        int x=0, y=0;
        for (int value : values) {
            AsciiTile tile = fromValue(value);
            if(tile==NEW_LINE) {
                y++;
                x=0;
            } else {
                map.put(new Point2D(x, y), tile);
                x++;
            }
        }
        return map;
    }
}
