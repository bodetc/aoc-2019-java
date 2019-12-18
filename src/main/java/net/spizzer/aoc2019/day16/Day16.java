package net.spizzer.aoc2019.day16;

import com.google.common.annotations.VisibleForTesting;
import net.spizzer.aoc2019.AbstractDay;
import net.spizzer.aoc2019.common.Reject;
import net.spizzer.aoc2019.utils.ParseUtils;

import java.util.List;
import java.util.stream.IntStream;

public class Day16 extends AbstractDay<int[], Integer, Integer> {

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
    public Integer solveFirstStar(int[] input) {
        for (int i = 0; i < 100; i++) {
            input = applyPhase(input);
        }

        int[] phases = input;
        return Ndigits(phases, 0, 8);
    }

    @VisibleForTesting
    static int[] applyPhase(int[] input) {
        int[] output = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            int value = 0;
            for (int j = 0; j < input.length; j++) {
                value += input[j] * getPatternValue(j, i);
            }
            output[i] = Math.abs(value % 10);
        }
        return output;
    }

    private static int getPatternValue(int i, int repeat) {
        return BASE_PATTERN[((i + 1) / (repeat + 1)) % BASE_PATTERN.length];
    }

    @Override
    public Integer solveSecondStar(final int[] input) {
        int skip = Ndigits(input, 0, 7);

        int[] signal = IntStream.range(0, input.length * 10000)
                .map(i -> input[i % input.length])
                .toArray();

        for (int i = 0; i < 100; i++) {
            int[] output = new int[signal.length];
            int sum = 0;
            for (int j = signal.length - 1; j >= skip; j--) {
                sum += signal[j];
                output[j] = sum % 10;
            }
            signal = output;
        }

        return Ndigits(signal, skip, 8);
    }

    @VisibleForTesting
    static int Ndigits(int[] input, int start, int length) {
        StringBuilder output = new StringBuilder();
        IntStream.range(start, start + length)
                .map(i -> input[i % input.length])
                .forEach(output::append);
        return Integer.parseInt(output.toString());
    }
}
