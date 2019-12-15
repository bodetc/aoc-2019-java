package net.spizzer.aoc2019.intcode;

import net.spizzer.aoc2019.common.HashMapWithDefault;

import java.util.Map;

public class Program {
    private final Map<Integer, Long> program;

    private int relativeBase = 0;

    Program(long[] initialProgram) {
        program = new HashMapWithDefault<>(0L);
        for (int i = 0; i < initialProgram.length; i++) {
            program.put(i, initialProgram[i]);
        }
    }

    public long get(int index) {
        return get(index, ParameterMode.IMMEDIATE);
    }

    public long get(int index, ParameterMode mode) {
        switch (mode) {
            case POSITION:
                return program.get(Math.toIntExact(program.get(index)));
            case IMMEDIATE:
                return program.get(index);
            case RELATIVE:
                return program.get(relativeBase + Math.toIntExact(program.get(index)));
        }
        throw new IllegalStateException("Unsupported mode");
    }

    void set(int index, ParameterMode mode, long value) {
        switch (mode) {
            case POSITION:
                program.put(Math.toIntExact(program.get(index)), value);
                break;
            case IMMEDIATE:
                program.put(index, value);
                break;
            case RELATIVE:
                program.put(relativeBase + Math.toIntExact(program.get(index)), value);
                break;
        }
    }

    public void offsetRelativeBase(long offset) {
        this.relativeBase += offset;
    }

    public long[] toArray() {
        int maxValue = program.keySet().stream().max(Integer::compareTo).orElseThrow() + 1;
        long[] array = new long[maxValue];
        for (int i = 0; i < maxValue; i++) {
            array[i] = program.get(i);
        }
        return array;
    }
}
