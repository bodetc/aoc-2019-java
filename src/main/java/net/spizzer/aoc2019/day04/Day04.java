package net.spizzer.aoc2019.day04;

import com.google.common.primitives.Ints;
import net.spizzer.aoc2019.AbstractDay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day04 extends AbstractDay<List<Integer>, Long, Long> {
    @Override
    public int getDay() {
        return 4;
    }

    @Override
    public List<Integer> parseInput(List<String> lines) {
        return IntStream.range(402328, 864247)
                .boxed()
                .collect(Collectors.toList());
    }

    @Override
    public Long solveFirstStar(List<Integer> input) {
        return input.stream()
                .map(Day04::toDigits)
                .filter(Day04::is6digits)
                .filter(Day04::hasTwoSameAdjacentDigits)
                .filter(Day04::isMonotonouslyIncreasing)
                .count();
    }

    @Override
    public Long solveSecondStar(List<Integer> input) {
        return input.stream()
                .map(Day04::toDigits)
                .filter(Day04::is6digits)
                .filter(Day04::hasExactlyTwoSameAdjacentDigits)
                .filter(Day04::isMonotonouslyIncreasing)
                .count();
    }

    private static int[] toDigits(int number) {
        List<Integer> digits = new ArrayList<>(6);
        while (number > 0) {
            digits.add(number % 10);
            number = number / 10;
        }
        Collections.reverse(digits);
        return Ints.toArray(digits);
    }


    private static boolean is6digits(int[] digits) {
        return digits.length == 6;
    }

    private static boolean hasTwoSameAdjacentDigits(int[] digits) {
        for (int i = 0; i < digits.length - 1; i++) {
            if (digits[i] == digits[i + 1]) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasExactlyTwoSameAdjacentDigits(int[] digits) {
        for (int i = 0; i < digits.length - 1; i++) {
            int digit = digits[i];
            if ((digit == digits[i + 1])
                    && (i == digits.length - 2 || digits[i + 2] != digit)
                    && (i == 0 || digits[i - 1] != digit)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isMonotonouslyIncreasing(int[] digits) {
        for (int i = 0; i < digits.length - 1; i++) {
            if (digits[i] > digits[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
