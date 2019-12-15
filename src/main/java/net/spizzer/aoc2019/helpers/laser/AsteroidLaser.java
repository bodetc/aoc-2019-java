package net.spizzer.aoc2019.helpers.laser;

import com.google.common.annotations.VisibleForTesting;
import net.spizzer.aoc2019.helpers.geometry2d.Point2D;
import net.spizzer.aoc2019.utils.MathUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

public class AsteroidLaser {
    private final List<Point2D> asteroids;

    public AsteroidLaser(List<String> lines) {
        asteroids = parseInput(lines, '#');
    }

    public List<Point2D> laserOrder() {
        return laserOrder(bestAsteroid());
    }

    public Point2D bestAsteroid() {
        return asteroids.stream()
                .max(Comparator.comparingLong(this::visibleAsteroids))
                .orElseThrow();
    }

    public long visibleAsteroids(Point2D a) {
        return asteroids.stream()
                .filter(not(a::equals))
                .filter(not(b -> isViewBlocked(a, b)))
                .count();
    }

    private boolean isViewBlocked(Point2D a, Point2D b) {
        return blockingAsteroids(a, b) > 0;
    }

    private long blockingAsteroids(Point2D a, Point2D b) {
        return asteroids.stream()
                .filter(not(a::equals))
                .filter(not(b::equals))
                .filter(c -> c.isCollinear(a, b))
                .filter(c -> c.isBetween(a, b))
                .count();
    }

    @VisibleForTesting
    List<Point2D> laserOrder(Point2D origin) {
        return asteroids.stream()
                .filter(Predicate.not(origin::equals))
                .sorted(Comparator.comparingDouble(other -> laserAngle(origin, other)))
                .collect(Collectors.toList());
    }

    @VisibleForTesting
    double laserAngle(Point2D origin, Point2D other) {
        Point2D a = new Point2D(0, -1);
        Point2D b = new Point2D(other.x - origin.x, other.y - origin.y);
        double theta = Math.atan2( a.x*b.y - a.y*b.x, a.x*b.x + a.y*b.y );
        return MathUtils.correctAngle(theta) + 2. * Math.PI * blockingAsteroids(origin, other);
    }

    private static List<Point2D> parseInput(List<String> lines, char match) {
        List<Point2D> asteroids = new ArrayList<>();
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == match) {
                    asteroids.add(new Point2D(x, y));
                }
            }
        }
        return asteroids;
    }

    @VisibleForTesting
    static Point2D findX(List<String> lines) {
        return parseInput(lines, 'X').get(0);
    }

}
