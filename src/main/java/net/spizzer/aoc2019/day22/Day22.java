package net.spizzer.aoc2019.day22;

import net.spizzer.aoc2019.AbstractDay;

import java.util.List;

public class Day22 extends AbstractDay<List<String>, Long, Void> {

    @Override
    public int getDay() {
        return 22;
    }

    @Override
    public List<String> parseInput(List<String> lines) {
        return lines;
    }

    @Override
    public Long solveFirstStar(List<String> instructions) {
        return SpaceDeckShuffle.applyOnce(instructions, 10007).positionOf(2019);
    }

    @Override
    public Void solveSecondStar(List<String> strings) {
        return null;
    }

    public static void main(String[] args) {
        new Day22().runDay();
    }

}
