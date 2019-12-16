package net.spizzer.aoc2019.intcode;

import java.util.ArrayList;
import java.util.List;

public class IntcodeComputer {

    private final Program program;
    private final List<Long> output = new ArrayList<>();
    private int position = 0;

    public IntcodeComputer(long[] instructions) {
        this.program = new Program(instructions);
    }

    public ReturnReason run() {
        return run(null);
    }

    public ReturnReason run(int input) {
        return run(Long.valueOf(input));
    }

    public ReturnReason run(Long input) {
        while (true) {
            int instruction = Math.toIntExact(program.get(position));
            OpCode opcode = OpCode.fromInstruction(instruction);

            if (opcode == OpCode.EXIT) {
                return ReturnReason.EXIT;
            }

            int numberOfInputParameters = opcode.getNumberOfInputParameters();
            ParameterMode[] modes = ParameterMode.fromInstruction(instruction, numberOfInputParameters);

            long[] parameters = new long[numberOfInputParameters];
            for (int i = 0; i < numberOfInputParameters; i++) {
                int parameterPosition = position + i + 1;
                parameters[i] = program.get(parameterPosition, modes[i]);
            }

            int outputPosition = position + opcode.getOutputOffset();
            ParameterMode outputMode = modes[numberOfInputParameters];
            boolean hasJumped = false;

            switch (opcode) {
                case ADD:
                    program.set(outputPosition, outputMode, parameters[0] + parameters[1]);
                    break;
                case MULTIPLY:
                    program.set(outputPosition, outputMode, parameters[0] * parameters[1]);
                    break;
                case INPUT:
                    if (input != null) {
                        program.set(outputPosition, outputMode, input);
                        input = null;
                    } else {
                        return ReturnReason.WAIT_FOR_INPUT;
                    }
                    break;
                case OUTPUT:
                    output.add(parameters[0]);
                    break;
                case JUMP_IF_TRUE:
                    if (parameters[0] > 0) {
                        position = Math.toIntExact(parameters[1]);
                        hasJumped = true;
                    }
                    break;
                case JUMP_IF_FALSE:
                    if (parameters[0] == 0) {
                        position = Math.toIntExact(parameters[1]);
                        hasJumped = true;
                    }
                    break;
                case LESS_THAN:
                    program.set(outputPosition, outputMode, parameters[0] < parameters[1] ? 1 : 0);
                    break;
                case EQUALS:
                    program.set(outputPosition, outputMode, parameters[0] == parameters[1] ? 1 : 0);
                    break;
                case RELATIVE_BASE_OFFSET:
                    program.offsetRelativeBase(parameters[0]);
                    break;
                default:
                    throw new IllegalStateException();
            }
            if (!hasJumped) {
                position += opcode.getPositionChange();
            }
        }
    }

    public long[] getProgram() {
        return program.toArray();
    }

    public long[] getOutput() {
        return output.stream().mapToLong(Long::longValue).toArray();
    }

    public int[] getIntOutput() {
        return output.stream().mapToInt(Math::toIntExact).toArray();
    }

    public long getLastOutput() {
        return output.get(output.size() - 1);
    }

    public long getLastIntOutput() {
        return Math.toIntExact(output.get(output.size() - 1));
    }

    public void clearOutput() {
        output.clear();
    }
}
