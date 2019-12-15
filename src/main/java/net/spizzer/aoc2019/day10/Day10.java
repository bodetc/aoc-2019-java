package net.spizzer.aoc2019.day10;

import net.spizzer.aoc2019.AbstractDay;
import net.spizzer.aoc2019.helpers.geometry2d.Point2D;
import net.spizzer.aoc2019.helpers.laser.AsteroidLaser;

import java.util.List;

public class Day10 extends AbstractDay<AsteroidLaser, Integer> {
    @Override
    public int getDay() {
        return 10;
    }

    @Override
    public AsteroidLaser parseInput(List<String> lines) {
        return new AsteroidLaser(lines);
    }

    @Override
    public Integer solveFirstStar(AsteroidLaser asteroidLaser) {
        Point2D bestAsteroid = asteroidLaser.bestAsteroid();
        System.out.println("Best point: " + bestAsteroid);
        return Math.toIntExact(asteroidLaser.visibleAsteroids(bestAsteroid));
    }

    @Override
    public Integer solveSecondStar(AsteroidLaser asteroidLaser) {
        List<Point2D> points = asteroidLaser.laserOrder();
        Point2D asteroid200 = points.get(199);
        System.out.println("200th Asteroid: " + asteroid200);
        return asteroid200.x * 100 + asteroid200.y;
    }
}
