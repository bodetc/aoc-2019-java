package net.spizzer.aoc2019.day02;

import net.spizzer.aoc2019.AbstractDay;
import net.spizzer.aoc2019.common.Pair;
import net.spizzer.aoc2019.intcode.IntcodeComputer;
import net.spizzer.aoc2019.utils.ParseUtils;

import java.util.List;

public class Day02 extends AbstractDay<long[], Integer, Integer> {

    @Override
    public int getDay() {
        return 2;
    }

    @Override
    public long[] parseInput(List<String> lines) {
        return ParseUtils.parseProgram(lines);
    }

    @Override
    public Integer solveFirstStar(long[] program) {
        return runNounVerb(program, 12, 2);
    }

    @Override
    public Integer solveSecondStar(long[] program) {
        Pair<Integer, Integer> pair = searchIntcode(program, 19690720);
        int noun = pair.getA();
        int verb = pair.getB();
        System.out.println("noun=" + noun + ", verb=" + verb);
        return noun * 100 + verb;
    }

    private static Pair<Integer, Integer> searchIntcode(long[] input, int targetOutput) {
        for (int noun = 0; noun < 100; noun++) {
            for (int verb = 0; verb < 100; verb++) {
                int output = runNounVerb(input, noun, verb);
                if (output == targetOutput) {
                    return new Pair<>(noun, verb);
                }
            }
        }
        throw new IllegalStateException("Target output could not be found.");
    }

    private static int runNounVerb(long[] instructions, long noun, long verb) {
        long[]  program = instructions.clone();
        program[1] = noun;
        program[2] = verb;

        IntcodeComputer computer = new IntcodeComputer(program);
        computer.run();

        return Math.toIntExact(computer.getProgram()[0]);
    }
}
