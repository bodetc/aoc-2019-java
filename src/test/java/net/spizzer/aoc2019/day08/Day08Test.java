package net.spizzer.aoc2019.day08;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day08Test {

    @Test
    void testLayerize() {
        int[][][] output = new Day08(3, 2).parseInput(List.of("123456789012"));
        int[][][] expected = new int[][][]{
                {
                        {1, 2, 3},
                        {4, 5, 6}
                },
                {
                        {7, 8, 9},
                        {0, 1, 2}
                }
        };
        for (int l = 0; l < expected.length; l++) {
            for (int h = 0; h < expected[l].length; h++) {
                for (int w = 0; w < expected[l][h].length; w++) {
                    assertEquals(expected[l][h][w], output[l][h][w]);
                }
            }
        }
    }

    @Test
    void testStack() {
        Day08 day08 = new Day08(2, 2);
        int[][][] layers = day08.parseInput(List.of("0222112222120000"));
        int[][] image = day08.stack(layers);

        int[][] expected = new int[][]{
                {0, 1},
                {1, 0}
        };

        for (int h = 0; h < expected.length; h++) {
            for (int w = 0; w < expected[h].length; w++) {
                assertEquals(expected[h][w], image[h][w]);
            }
        }
    }
}