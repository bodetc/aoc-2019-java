package net.spizzer.aoc2019.day19;

import net.spizzer.aoc2019.AbstractDay;
import net.spizzer.aoc2019.utils.ParseUtils;

import java.util.List;
import java.util.stream.LongStream;

public class Day19 extends AbstractDay<TractorBeam, Integer, Integer> {
    @Override
    public int getDay() {
        return 19;
    }

    @Override
    public TractorBeam parseInput(List<String> lines) {
        return new TractorBeam(ParseUtils.parseProgram(lines));
    }

    @Override
    public Integer solveFirstStar(TractorBeam tractorBeam) {
        long count = LongStream.range(0, 50).map(
                x -> LongStream.range(0, 50)
                        .filter(y -> tractorBeam.containsPoint(x, y))
                        .count()
        ).sum();
        return Math.toIntExact(count);
    }

    @Override
    public Integer solveSecondStar(TractorBeam tractorBeam) {
        int x=0, y=0;
        while(!tractorBeam.containsPoint(x+99, y)) {
            y++;
            while(!tractorBeam.containsPoint(x, y+99)) {
                x++;
            }
        }
        return  x*10000+y;
    }

    public static void main(String[] args) {
        new Day19().runDay();
    }
}
