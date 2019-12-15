package net.spizzer.aoc2019.day12;

import net.spizzer.aoc2019.AbstractDay;
import net.spizzer.aoc2019.helpers.orbits.MoonOrbits;

import java.util.List;

public class Day12 extends AbstractDay<MoonOrbits, Long> {
    @Override
    public int getDay() {
        return 12;
    }

    @Override
    public MoonOrbits parseInput(List<String> lines) {
        return new MoonOrbits(lines);
    }

    @Override
    public Long solveFirstStar(MoonOrbits moonOrbits) {
        moonOrbits.timesteps(1000);
        return (long)moonOrbits.energy();
    }

    @Override
    public Long solveSecondStar(MoonOrbits moonOrbits) {
        return null;
    }
}
