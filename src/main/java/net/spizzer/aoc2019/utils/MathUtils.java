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
}
