package net.spizzer.aoc2019.helpers.orbits;

import com.google.common.annotations.VisibleForTesting;
import net.spizzer.aoc2019.common.Point3D;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Moon {
    private Point3D position;
    private Point3D velocity;

    @VisibleForTesting
    Moon(Point3D position, Point3D velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    Moon(Point3D position) {
        this(position, Point3D.ORIGIN);
    }

    public void applyGravity(List<Moon> asteroids) {
        asteroids.stream()
                .filter(Predicate.not(this::equals))
                .collect(Collectors.toList())
                .forEach(this::applyGravity);
    }

    public void applyGravity(Moon asteroid) {
        velocity = velocity.add(
                gravityChange(position.x, asteroid.position.x),
                gravityChange(position.y, asteroid.position.y),
                gravityChange(position.z, asteroid.position.z));
    }

    public void applyVelocity() {
        position = position.add(velocity.x, velocity.y, velocity.z);
    }

    private static int gravityChange(int position, int other) {
        return Integer.compare(other, position);
    }

    public int energy() {
        return position.taxiNorm() * velocity.taxiNorm();
    }

    private boolean equals(Moon other) {
        return position.equals(other.position) && velocity.equals(other.velocity);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Moon && equals((Moon) other);
    }

    @Override
    public String toString() {
        return "<pos=" + position + ", vel=" + velocity + ">";
    }
}
