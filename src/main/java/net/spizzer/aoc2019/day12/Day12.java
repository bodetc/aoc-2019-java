package net.spizzer.aoc2019.day12;

import net.spizzer.aoc2019.AbstractDay;
import net.spizzer.aoc2019.common.Point3D;
import net.spizzer.aoc2019.helpers.orbits.MoonOrbits;
import net.spizzer.aoc2019.utils.ParseUtils;

import java.util.List;
import java.util.stream.Collectors;

public class Day12 extends AbstractDay<MoonOrbits, Integer, Long> {
    @Override
    public int getDay() {
        return 12;
    }

    @Override
    public MoonOrbits parseInput(List<String> lines) {
        List<Point3D> coordinates = lines
                .stream()
                .map(ParseUtils::parseCoordinates)
                .collect(Collectors.toList());
        return new MoonOrbits(coordinates);
    }

    @Override
    public Integer solveFirstStar(MoonOrbits moonOrbits) {
        moonOrbits.timesteps(1000);
        return moonOrbits.energy();
    }

    @Override
    public Long solveSecondStar(MoonOrbits moonOrbits) {
        return moonOrbits.findCycleTime();
    }
}
