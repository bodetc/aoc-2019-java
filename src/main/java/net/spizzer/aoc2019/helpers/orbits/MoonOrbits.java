package net.spizzer.aoc2019.helpers.orbits;

import net.spizzer.aoc2019.utils.ParseUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MoonOrbits {
    private final List<Moon> moons;
    private long time = 0;

    public MoonOrbits(List<String> lines) {
        this.moons = lines.stream()
                .map(ParseUtils::parseCoordinates)
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

    public int energy() {
        return moons.stream()
                .mapToInt(Moon::energy)
                .sum();
    }

    public long getTime() {
        return time;
    }

    public List<Moon> getMoons() {
        return Collections.unmodifiableList(moons);
    }
}
