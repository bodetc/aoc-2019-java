package net.spizzer.aoc2019.day08;

import com.google.common.annotations.VisibleForTesting;
import net.spizzer.aoc2019.AbstractDay;
import net.spizzer.aoc2019.common.Reject;
import net.spizzer.aoc2019.utils.MathUtils;
import net.spizzer.aoc2019.utils.ParseUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Day08 extends AbstractDay<int[][][], String> {

    private final int width, height;

    public Day08() {
        this(25, 6);
    }

    @VisibleForTesting
    Day08(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int getDay() {
        return 8;
    }

    @Override
    public int[][][] parseInput(List<String> lines) {
        Reject.ifDifferent(1, lines.size(), "This day's input should have only one line!");
        int[] ints = ParseUtils.lineToIntegers(lines.get(0));
        return layerize(ints);
    }

    @Override
    public String solveFirstStar(int[][][] input) {
        // Find the layer with less zero
        int[][] layer = Arrays.stream(input)
                .min(Comparator.comparingInt(a -> countOf(a, 0)))
                .orElseThrow();

        // Return the amount of ones times the amount of twos in that layer
        return Integer.toString(countOf(layer, 1) * countOf(layer, 2));
    }

    @Override
    public String solveSecondStar(int[][][] input) {
        // Stack image
        int[][] image = stack(input);

        // Display output
        for (int[] line : image) {
            for (int value : line) {
                System.out.print(value == 1 ? '█' : ' ');
            }
            System.out.println();
        }

        // Output needs to be visualized here
        return "CJZHR";
    }

    private int[][][] layerize(int[] input) {
        int layers = input.length / (width * height);
        int[][][] output = new int[layers][height][width];
        for (int l = 0; l < layers; l++) {
            for (int h = 0; h < height; h++) {
                for (int w = 0; w < width; w++) {
                    int index = w + h * width + l * (width * height);
                    output[l][h][w] = input[index];
                }
            }
        }
        return output;
    }

    private static int countOf(int[][] input, int value) {
        int count = 0;
        for (int[] line : input) {
            for (int a : line) {
                if (a == value) {
                    count++;
                }
            }
        }
        return count;
    }

    @VisibleForTesting
    int[][] stack(int[][][] input) {
        int[][] image = MathUtils.arrayWithInitialValue(height, width, 2);
        for (int[][] layer : input) {
            image = stack(image, layer);
        }
        return image;
    }

    private int[][] stack(int[][] front, int[][] back) {
        int[][] out = new int[height][width];
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                out[h][w] = front[h][w] == 2 // 2 is transparent
                        ? back[h][w]
                        : front[h][w];
            }
        }
        return out;
    }
}
