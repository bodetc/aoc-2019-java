package net.spizzer.aoc2019.day19;

import net.spizzer.aoc2019.intcode.IntcodeComputer;

class TractorBeam {
    private final long[] program;

    TractorBeam(long[] program) {
        this.program = program;
    }

    boolean containsPoint(long x, long y) {
        IntcodeComputer computer = new IntcodeComputer(program);
        computer.run(x);
        computer.run(y);
        return computer.getLastIntOutput() == 1;
    }
}
