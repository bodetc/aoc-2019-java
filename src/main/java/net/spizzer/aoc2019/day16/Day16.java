package net.spizzer.aoc2019.day16;

import com.google.common.annotations.VisibleForTesting;
import net.spizzer.aoc2019.AbstractDay;
import net.spizzer.aoc2019.common.Reject;
import net.spizzer.aoc2019.utils.ParseUtils;

import java.util.Arrays;
import java.util.List;

public class Day16 extends AbstractDay<int[], String, Void> {

    private static final int[] BASE_PATTERN = new int[]{0, 1, 0, -1};

    @Override
    public int getDay() {
        return 16;
    }

    @Override
    public int[] parseInput(List<String> lines) {
        Reject.ifDifferent(1, lines.size(), "This day's input should have only one line!");
        return ParseUtils.lineToIntegers(lines.get(0));
    }

    @Override
    public String solveFirstStar(int[] input) {
        return applyPhases(input, 100);
    }

    @Override
    public Void solveSecondStar(int[] input) {
        return null;
    }

    @VisibleForTesting
    static String applyPhases(int[] input, int amount) {
        for (int i = 0; i < amount; i++) {
            input = applyPhase(input);
        }
        return first8digits(input);
    }

    @VisibleForTesting
    static int[] applyPhase(int[] input) {
        int[] output = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            output[i] = applyPattern(input, repeatPattern(i + 1));
        }
        return output;
    }

    private static int applyPattern(int[] input, int[] repeatPattern) {
        int value = 0;
        for (int i = 0; i < input.length; i++) {
            value += input[i] * repeatPattern[(i + 1) % repeatPattern.length];
        }
        return Math.abs(value % 10);
    }

    private static int[] repeatPattern(int count) {
        int[] output = new int[BASE_PATTERN.length * count];
        for (int i = 0; i < BASE_PATTERN.length; i++) {
            for (int j = 0; j < count; j++) {
                output[count * i + j] = BASE_PATTERN[i];
            }
        }
        return output;
    }

    @VisibleForTesting
    static String first8digits(int[] input) {
        StringBuilder output = new StringBuilder();
        Arrays.stream(input)
                .limit(8)
                .forEach(output::append);
        return output.toString();
    }
}
