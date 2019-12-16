package net.spizzer.aoc2019.day02;

import net.spizzer.aoc2019.AbstractDay;
import net.spizzer.aoc2019.common.Pair;
import net.spizzer.aoc2019.intcode.early.EarlyIntcodeComputer;
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
        return EarlyIntcodeComputer.runNounVerb(program, 12, 2);
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
                int output = EarlyIntcodeComputer.runNounVerb(input, noun, verb);
                if (output == targetOutput) {
                    return new Pair<>(noun, verb);
                }
            }
        }
        throw new IllegalStateException("Target output could not be found.");
    }
}
