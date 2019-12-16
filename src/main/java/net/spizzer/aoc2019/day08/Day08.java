package net.spizzer.aoc2019.day08;

import com.google.common.annotations.VisibleForTesting;
import net.spizzer.aoc2019.AbstractDay;
import net.spizzer.aoc2019.common.HashMapWithDefault;
import net.spizzer.aoc2019.common.Printable;
import net.spizzer.aoc2019.common.Reject;
import net.spizzer.aoc2019.helpers.geometry2d.Point2D;
import net.spizzer.aoc2019.common.Color;
import net.spizzer.aoc2019.utils.ParseUtils;

import java.util.*;

public class Day08 extends AbstractDay<List<Map<Point2D, Color>>, String> {

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
    public List<Map<Point2D, Color>> parseInput(List<String> lines) {
        Reject.ifDifferent(1, lines.size(), "This day's input should have only one line!");
        int[] ints = ParseUtils.lineToIntegers(lines.get(0));
        return layerize(ints);
    }

    private List<Map<Point2D, Color>> layerize(int[] input) {
        List<Map<Point2D, Color>> list = new ArrayList<>();
        int layers = input.length / (width * height);
        for (int l = 0; l < layers; l++) {
            Map<Point2D, Color> map = new HashMapWithDefault<>(Color.TRANSPARENT);
            for (int h = 0; h < height; h++) {
                for (int w = 0; w < width; w++) {
                    int index = w + h * width + l * (width * height);
                    int value = input[index];
                    map.put(new Point2D(w, h), Color.fromValue(value));
                }
            }
            list.add(map);
        }
        return list;
    }

    @Override
    public String solveFirstStar(List<Map<Point2D, Color>> input) {
        // Find the layer with less zero
        Map<Point2D, Color> layer = input.stream()
                .min(Comparator.comparingLong(l -> countOf(l, Color.BLACK)))
                .orElseThrow();

        // Return the amount of ones times the amount of twos in that layer
        return Long.toString(countOf(layer, Color.WHITE) * countOf(layer, Color.TRANSPARENT));
    }

    private static long countOf(Map<Point2D, Color> layer, Color value) {
        return layer.values().stream().filter(value::equals).count();
    }

    @Override
    public String solveSecondStar(List<Map<Point2D, Color>> input) {
        // Stack image
        Map<Point2D, Color> image = stack(input);

        // Display output
        Printable.print(image);

        // Output needs to be visualized here
        return "CJZHR";
    }

    @VisibleForTesting
    Map<Point2D, Color> stack(List<Map<Point2D, Color>> input) {
        Map<Point2D, Color> image = new HashMapWithDefault<>(Color.TRANSPARENT);
        for (Map<Point2D, Color> layer : input) {
            image = stack(image, layer);
        }
        return image;
    }

    private Map<Point2D, Color> stack(Map<Point2D, Color> front, Map<Point2D, Color> back) {
        Map<Point2D, Color> out = new HashMapWithDefault<>(Color.TRANSPARENT);
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                Point2D point = new Point2D(w, h);
                Color color = front.get(point) == Color.TRANSPARENT
                        ? back.get(point)
                        : front.get(point);
                out.put(point, color);

            }
        }
        return out;
    }
}
