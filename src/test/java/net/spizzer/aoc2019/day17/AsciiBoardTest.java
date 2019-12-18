package net.spizzer.aoc2019.day17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AsciiBoardTest {

    @Test
    void getIntersectionArea() {
        String input = "..#..........\n" +
                "..#..........\n" +
                "#######...###\n" +
                "#.#...#...#.#\n" +
                "#############\n" +
                "..#...#...#..\n" +
                "..#####...^..";
        AsciiBoard board = new AsciiBoard(input.chars().toArray());
        assertEquals(76, board.getIntersectionArea());
    }
}