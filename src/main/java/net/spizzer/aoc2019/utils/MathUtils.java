package net.spizzer.aoc2019.utils;

import java.util.ArrayList;
import java.util.List;

public class MathUtils {
    public static boolean isBetween(int a, int b, int c) {
        if (b < a) {
            return isBetween(b, a, c);
        }
        return a <= c && c <= b;
    }

    public static double correctAngle(double angle) {
        if (angle < 0) {
            return angle + 2. * Math.PI;
        } else if (angle > 2. * Math.PI) {
            return angle - 2. * Math.PI;
        } else {
            return angle;
        }
    }

    public static long leastCommonMultiplier(long m, long n) {
        if (m == n) {
            return m;
        }
        if (m == 1 || n == 1) {
            return m * n;
        }

        // this section increases the value of mm until it is greater
        // than or equal to nn, then does it again when the lesser
        // becomes the greater--if they aren't equal.
        long mm = m, nn = n;
        while (mm != nn) {
            while (mm < nn) {
                mm += m;
            }
            while (nn < mm) {
                nn += n;
            }
        }
        return mm;
    }

    public static List<int[]> permutations(int[] input) {
        int n = input.length;
        List<int[]> output = new ArrayList<>();

        int[] elements = input.clone();
        int[] indexes = new int[n];
        for (int i = 0; i < n; i++) {
            indexes[i] = 0;
        }

        output.add(elements.clone());

        int i = 0;
        while (i < n) {
            if (indexes[i] < i) {
                swap(elements, i % 2 == 0 ? 0 : indexes[i], i);
                output.add(elements.clone());
                indexes[i]++;
                i = 0;
            } else {
                indexes[i] = 0;
                i++;
            }
        }

        return output;
    }

    private static void swap(int[] input, int a, int b) {
        int tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }
}
