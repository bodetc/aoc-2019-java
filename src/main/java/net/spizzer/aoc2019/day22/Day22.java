package net.spizzer.aoc2019.day22;

import net.spizzer.aoc2019.AbstractDay;

import java.util.List;

public class Day22 extends AbstractDay<List<String>, Integer, Void> {

    private static final int DECK_SIZE = 10007;

    @Override
    public int getDay() {
        return 22;
    }

    @Override
    public List<String> parseInput(List<String> lines) {
        return lines;
    }

    @Override
    public Integer solveFirstStar(List<String> instructions) {
        SpaceCardsShuffler shuffler = new SpaceCardsShuffler(DECK_SIZE);
        SpaceCards spaceCards = shuffler.shuffle(instructions);
        return spaceCards.positionOf(2019);
    }

    @Override
    public Void solveSecondStar(List<String> strings) {
        return null;
    }

    public static void main(String[] args) {
        new Day22().runDay();
    }

}
