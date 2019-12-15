package net.spizzer.aoc2019.intcode.early;

import net.spizzer.aoc2019.intcode.IntcodeComputer;

public class EarlyIntcodeComputer {

    public static long runNounVerb(long[] instructions, long noun, long verb) {
        long[]  program = instructions.clone();
        program[1] = noun;
        program[2] = verb;

        IntcodeComputer computer = new IntcodeComputer(program);
        computer.run();

        return computer.getProgram()[0];
    }

    public static long runIO(long[] instructions, long input) {
        IntcodeComputer computer = new IntcodeComputer(instructions);
        computer.run(input);
        return computer.getLastOutput();
    }
}
