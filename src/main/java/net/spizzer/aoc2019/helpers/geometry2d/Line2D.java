package net.spizzer.aoc2019.helpers.geometry2d;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.spizzer.aoc2019.utils.ParseUtils.COMMA_SEPARATOR;

public class Line2D {
    private final List<Segment2D> segments = new ArrayList<>();

    private Line2D(List<Vector2D> path) {
        Point2D start = new Point2D(0, 0);

        for (Vector2D vector : path) {
            Segment2D segment = new Segment2D(start, vector);
            segments.add(segment);
            start=segment.end;
        }
    }

    public Line2D(String path) {
        this(COMMA_SEPARATOR.splitAsStream(path).map(Vector2D::new).collect(Collectors.toList()));
    }

    public Stream<Point2D> intersect(Line2D other) {
        return segments.stream()
                .flatMap(s1 -> other.segments.stream().map(s1::intersect))
                .filter(Optional::isPresent)
                .map(Optional::get);
    }

    public int delay(Point2D point) {
        int delay = 0;
        for (Segment2D segment : segments) {
            if (segment.contains(point)) {
                delay += point.taxiDistance(segment.start);
                return delay;
            } else {
                delay += segment.length();
            }
        }
        return delay;
    }
}
