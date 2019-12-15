package net.spizzer.aoc2019.helpers.orbits;

import com.google.common.annotations.VisibleForTesting;
import net.spizzer.aoc2019.common.Point3D;
import net.spizzer.aoc2019.utils.MathUtils;

import java.util.Collections;
import java.util.List;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class MoonOrbits {
    private final List<Point3D> initialPositions;
    private final List<Moon> moons;
    private long time = 0;

    public MoonOrbits(List<Point3D> initialPositions) {
        this.initialPositions = initialPositions;
        this.moons = initialPositions.stream()
                .map(Moon::new)
                .collect(Collectors.toList());
    }

    private void timestep() {
        moons.forEach(asteroid -> asteroid.applyGravity(moons));
        moons.forEach(Moon::applyVelocity);
        time++;
    }

    public void timesteps(int N) {
        for (int i = 0; i < N; i++) {
            timestep();
        }
    }

    public long findCycleTime() {
        return Point3D.TO_COMPONENTS.stream()
                .mapToLong(this::findCycleTime)
                .reduce(1, MathUtils::leastCommonMultiplier);
    }

    private long findCycleTime(ToIntFunction<Point3D> toComponent) {
        MoonOrbits orbits = new MoonOrbits(initialPositions);
        do {
            orbits.timestep();
        } while (!orbits.hasCycledForComponent(toComponent));
        return orbits.time;
    }

    public int energy() {
        return moons.stream()
                .mapToInt(Moon::energy)
                .sum();
    }

    @VisibleForTesting
    List<Moon> getMoons() {
        return Collections.unmodifiableList(moons);
    }

    private boolean hasCycledForComponent(ToIntFunction<Point3D> toComponent) {
        for (int i = 0; i < moons.size(); i++) {
            Moon moon = moons.get(i);
            int position = toComponent.applyAsInt(moon.getPosition());
            int velocity = toComponent.applyAsInt(moon.getVelocity());
            int initialPosition = toComponent.applyAsInt(initialPositions.get(i));

            if (position != initialPosition || velocity != 0) {
                return false;
            }
        }
        return true;
    }
}
