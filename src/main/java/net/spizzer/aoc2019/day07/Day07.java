package net.spizzer.aoc2019.day07;

import net.spizzer.aoc2019.AbstractDay;
import net.spizzer.aoc2019.utils.MathUtils;
import net.spizzer.aoc2019.utils.ParseUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class Day07 extends AbstractDay<long[], Long, Long> {

    @Override
    public int getDay() {
        return 7;
    }

    @Override
    public long[] parseInput(List<String> lines) {
        return ParseUtils.parseProgram(lines);
    }

    @Override
    public Long solveFirstStar(long[] program) {
        return searchPhases(program, 0,5);
    }

    @Override
    public Long solveSecondStar(long[] program) {
        return searchPhases(program, 5,10);
    }

    static long searchPhases(long[] program, int start, int end) {
        int[] phasesValues = IntStream.range(start, end).toArray();
        List<int[]> permutations = MathUtils.permutations(phasesValues);

        return permutations.stream()
                .map(phases -> Amplifier.runAmplifiers(program, phases))
                .max(Comparator.comparingLong(Long::longValue))
                .orElseThrow();
    }
}
