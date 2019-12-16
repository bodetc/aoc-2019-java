package net.spizzer.aoc2019.day08;

import net.spizzer.aoc2019.helpers.geometry2d.Point2D;
import net.spizzer.aoc2019.common.Color;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day08Test {

    @Test
    void testLayerize() {
        List<Map<Point2D, Color>> output = new Day08(3, 2).parseInput(List.of("012210011212"));
        int[][][] expected = new int[][][]{
                {
                        {0, 1, 2},
                        {2, 1, 0}
                },
                {
                        {0, 1, 1},
                        {2, 1, 2}
                }
        };
        for (int l = 0; l < expected.length; l++) {
            for (int h = 0; h < expected[l].length; h++) {
                for (int w = 0; w < expected[l][h].length; w++) {
                    assertEquals(Color.fromValue(expected[l][h][w]), output.get(l).get(new Point2D(w, h)));
                }
            }
        }
    }

    @Test
    void testStack() {
        Day08 day08 = new Day08(2, 2);
        List<Map<Point2D, Color>> layers = day08.parseInput(List.of("0222112222120000"));
        Map<Point2D, Color> image = day08.stack(layers);

        int[][] expected = new int[][]{
                {0, 1},
                {1, 0}
        };

        for (int h = 0; h < expected.length; h++) {
            for (int w = 0; w < expected[h].length; w++) {
                assertEquals(Color.fromValue(expected[h][w]), image.get(new Point2D(h, w)));
            }
        }
    }
}