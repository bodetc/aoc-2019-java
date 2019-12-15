package net.spizzer.aoc2019.intcode;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class IntcodeComputerTest {

    static Stream<Arguments> dataProviderRunProgram() {
        return Stream.of(
                arguments(new long[]{99}, new long[]{99}),
                arguments(new long[]{1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50}, new long[]{3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50}),
                arguments(new long[]{1, 0, 0, 0, 99}, new long[]{2, 0, 0, 0, 99}),
                arguments(new long[]{2, 3, 0, 3, 99}, new long[]{2, 3, 0, 6, 99}),
                arguments(new long[]{2, 4, 4, 5, 99, 0}, new long[]{2, 4, 4, 5, 99, 9801}),
                arguments(new long[]{1, 1, 1, 4, 99, 5, 6, 0, 99}, new long[]{30, 1, 1, 4, 2, 5, 6, 0, 99}),
                arguments(new long[]{1002, 4, 3, 4, 33}, new long[]{1002, 4, 3, 4, 99}),
                arguments(new long[]{1101, 100, -1, 4, 0}, new long[]{1101, 100, -1, 4, 99})
        );
    }

    @ParameterizedTest
    @MethodSource("dataProviderRunProgram")
    void testRunProgram(long[] before, long[] after) {
        IntcodeComputer computer = new IntcodeComputer(before);
        computer.run();
        long[] actual = computer.getProgram();
        assertArrayEquals(after, actual);
    }

    static Stream<Arguments> dataProviderLargeValues() {
        return Stream.of(
                arguments(new long[]{1102, 34915192, 34915192, 7, 4, 7, 99, 0}, 1219070632396864L),
                arguments(new long[]{104, 1125899906842624L, 99}, 1125899906842624L)
                );
    }

    @ParameterizedTest
    @MethodSource("dataProviderLargeValues")
    void testLargeValues(long[] program, long output) {
        IntcodeComputer computer = new IntcodeComputer(program);
        computer.run();
        assertEquals(output, computer.getLastOutput());
    }

    @Test
    void testSelfCopy() {
        long[] program = new long[]{109, 1, 204, -1, 1001, 100, 1, 100, 1008, 100, 16, 101, 1006, 101, 0, 99};
        IntcodeComputer computer = new IntcodeComputer(program);
        computer.run();
        assertArrayEquals(program, computer.getOutput());
    }
}