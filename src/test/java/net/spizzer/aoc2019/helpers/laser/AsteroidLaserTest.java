package net.spizzer.aoc2019.helpers.laser;

import net.spizzer.aoc2019.helpers.geometry2d.Point2D;
import net.spizzer.aoc2019.utils.ParseUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class AsteroidLaserTest {

    static Stream<Arguments> dataProviderVisibleAsteroids() {
        return Stream.of(
                arguments(new Point2D(1, 0), 7),
                arguments(new Point2D(4, 0), 7),
                arguments(new Point2D(0, 2), 6),
                arguments(new Point2D(1, 2), 7),
                arguments(new Point2D(2, 2), 7),
                arguments(new Point2D(3, 2), 7),
                arguments(new Point2D(4, 2), 5),
                arguments(new Point2D(4, 3), 7),
                arguments(new Point2D(3, 4), 8),
                arguments(new Point2D(4, 4), 7)
        );
    }

    @ParameterizedTest
    @MethodSource("dataProviderVisibleAsteroids")
    void testVisibleAsteroids(Point2D point, int visibleAsteroids) {
        List<String> lines = ParseUtils.readLines("helpers/laser/test1.txt");
        AsteroidLaser laser = new AsteroidLaser(lines);
        assertEquals(visibleAsteroids, laser.visibleAsteroids(point));
    }

    static Stream<Arguments> dataProviderBestAsteroid() {
        return Stream.of(
                arguments("helpers/laser/test1.txt", new Point2D(3, 4), 8),
                arguments("helpers/laser/test2.txt", new Point2D(5, 8), 33),
                arguments("helpers/laser/test3.txt", new Point2D(1, 2), 35),
                arguments("helpers/laser/test4.txt", new Point2D(6, 3), 41),
                arguments("helpers/laser/test5.txt", new Point2D(11, 13), 210)
        );
    }

    @ParameterizedTest
    @MethodSource("dataProviderBestAsteroid")
    void testBestAsteroid(String path, Point2D bestAsteroid, int visibleAsteroids) {
        List<String> lines = ParseUtils.readLines(path);
        AsteroidLaser laser = new AsteroidLaser(lines);
        assertEquals(bestAsteroid, laser.bestAsteroid());
        assertEquals(visibleAsteroids, laser.visibleAsteroids(bestAsteroid));
    }

    @Test
    void testAngle() {
        AsteroidLaser laser = new AsteroidLaser(List.of());
        Point2D origin = new Point2D(5, 5);
        assertEquals(0., laser.laserAngle(origin, new Point2D(5, 0)));
        assertEquals(Math.PI / 2., laser.laserAngle(origin, new Point2D(10, 5)));
        assertEquals(Math.PI, laser.laserAngle(origin, new Point2D(5, 10)));
        assertEquals(3. * Math.PI / 2., laser.laserAngle(origin, new Point2D(0, 5)));
    }

    @Test
    void testLaser() {
        AsteroidLaser field = new AsteroidLaser(ParseUtils.readLines("helpers/laser/laser.txt"));
        Point2D origin = AsteroidLaser.findX(ParseUtils.readLines("helpers/laser/laser.txt"));
        List<Point2D> laserOrder = field.laserOrder(origin);
        assertEquals(new Point2D(8, 1), laserOrder.get(0));
        assertEquals(new Point2D(9, 0), laserOrder.get(1));
        assertEquals(new Point2D(9, 1), laserOrder.get(2));
        assertEquals(new Point2D(10, 0), laserOrder.get(3));
        assertEquals(new Point2D(9, 2), laserOrder.get(4));
        assertEquals(new Point2D(11, 1), laserOrder.get(5));
        assertEquals(new Point2D(12, 1), laserOrder.get(6));
        assertEquals(new Point2D(11, 2), laserOrder.get(7));
        assertEquals(new Point2D(15, 1), laserOrder.get(8));
    }

    @Test
    void testLaser5() {
        AsteroidLaser field = new AsteroidLaser(ParseUtils.readLines("helpers/laser/test5.txt"));
        Point2D origin = field.bestAsteroid();
        List<Point2D> laserOrder = field.laserOrder(origin);
        assertEquals(new Point2D(11, 12), laserOrder.get(0));
        assertEquals(new Point2D(12, 1), laserOrder.get(1));
        assertEquals(new Point2D(12, 2), laserOrder.get(2));
        assertEquals(new Point2D(12, 8), laserOrder.get(9));
        assertEquals(new Point2D(16, 0), laserOrder.get(19));
        assertEquals(new Point2D(16, 9), laserOrder.get(49));
        assertEquals(new Point2D(10, 16), laserOrder.get(99));
        assertEquals(new Point2D(9, 6), laserOrder.get(198));
        assertEquals(new Point2D(8, 2), laserOrder.get(199));
        assertEquals(new Point2D(10, 9), laserOrder.get(200));
        assertEquals(new Point2D(11, 1), laserOrder.get(298));
    }
}