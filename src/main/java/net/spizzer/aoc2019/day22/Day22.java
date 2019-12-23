package net.spizzer.aoc2019.day22;

import net.spizzer.aoc2019.AbstractDay;

import java.util.List;

public class Day22 extends AbstractDay<List<String>, Long, Long> {

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
    public Long solveSecondStar(List<String> instructions) {
        SpaceDeck deck = SpaceDeckShuffle.applyOnce(instructions, 119315717514047L);
        deck.iterate(101741582076661L);
        return deck.cardAt(2020);
    }

    public static void main(String[] args) {
        new Day22().runDay();
    }

}
