package net.spizzer.aoc2019.day07;

import net.spizzer.aoc2019.intcode.IntcodeComputer;
import net.spizzer.aoc2019.intcode.ReturnReason;

import java.util.Arrays;

class Amplifier {
    private final IntcodeComputer computer;

    private Amplifier(long[] instructions, int phase) {
        this.computer = new IntcodeComputer(instructions);
        computer.run(phase);
    }

    private ReturnReason run(long input) {
        return computer.run(input);
    }

    private long getLastOutput(){
        return computer.getLastOutput();
    }

    static long runAmplifiers(long[] program, int[] phases) {
        Amplifier[] amplifiers = Arrays.stream(phases)
                .mapToObj(phase -> new Amplifier(program, phase))
                .toArray(Amplifier[]::new);

        long output = 0;
        ReturnReason returnReason = ReturnReason.WAIT_FOR_INPUT;

        while (returnReason==ReturnReason.WAIT_FOR_INPUT) {
            for (Amplifier amplifier : amplifiers) {
                returnReason = amplifier.run(output);
                output = amplifier.getLastOutput();
            }
        }
        return output;
    }
}
