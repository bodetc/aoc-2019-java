package net.spizzer.aoc2019.day17;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AsciiBoardTest {

    @Test
    void getIntersectionArea() {
        String input =
                "..#..........\n" +
                "..#..........\n" +
                "#######...###\n" +
                "#.#...#...#.#\n" +
                "#############\n" +
                "..#...#...#..\n" +
                "..#####...^..";
        AsciiBoard board = new AsciiBoard(input.chars().toArray());
        assertEquals(76, board.getIntersectionArea());
    }

    @Test
    void getRobotPath() {
        String input =
                "#######...#####\n" +
                "#.....#...#...#\n" +
                "#.....#...#...#\n" +
                "......#...#...#\n" +
                "......#...###.#\n" +
                "......#.....#.#\n" +
                "^########...#.#\n" +
                "......#.#...#.#\n" +
                "......#########\n" +
                "........#...#..\n" +
                "....#########..\n" +
                "....#...#......\n" +
                "....#...#......\n" +
                "....#...#......\n" +
                "....#####......";
        AsciiBoard board = new AsciiBoard(input.chars().toArray());
        assertEquals("R,8,R,8,R,4,R,4,R,8,L,6,L,2,R,4,R,4,R,8,R,8,R,8,L,6,L,2", board.getRobotPath());
    }

    @Test
    void getCompressPath() {
        List<String> strings = AsciiBoard.compressPath("R,8,R,8,R,4,R,4,R,8,L,6,L,2,R,4,R,4,R,8,R,8,R,8,L,6,L,2");
        assertEquals("A,B,C", strings.get(0) );
        assertEquals("R,8,R,8,R,4,R,4,R,8", strings.get(1) );
        assertEquals("L,6,L,2,R,4,R,4,R,8", strings.get(2) );
        assertEquals("R,8,R,8,L,6,L,2", strings.get(3) );
    }
}