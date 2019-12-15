package net.spizzer.aoc2019.utils;

public class MathUtils {
    public static boolean isBetween(int a, int b, int c) {
        if (b < a) {
            return isBetween(b, a, c);
        }
        return a <= c && c <= b;
    }

    public static int[][] arrayWithInitialValue(int height, int width, int value) {
        int[][] array = new int[height][width];
        for (int[] line : array) {
            for (int i = 0; i < line.length; i++) {
                line[i] = value;
            }
        }
        return array;
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
}
