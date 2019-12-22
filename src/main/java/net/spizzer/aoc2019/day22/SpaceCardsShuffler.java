package net.spizzer.aoc2019.day22;

import net.spizzer.aoc2019.common.Reject;

import java.util.List;

public class SpaceCardsShuffler {
    private static final String DEAL_INTO_NEW_STACK = "deal into new stack";
    private static final String CUT_ = "cut ";
    private static final String DEAL_WITH_INCREMENT_ = "deal with increment ";

    private final int length;

    public SpaceCardsShuffler(int length) {
        this.length = length;
    }

    public SpaceCards shuffle(List<String> instructions) {
        SpaceCards spaceCards = SpaceCards.factoryOrder(length);
        for (String instruction : instructions) {
            spaceCards = shuffle(instruction, spaceCards);
        }
        return spaceCards;
    }

    private SpaceCards shuffle(String instruction, SpaceCards cards) {
        if (instruction.equals(DEAL_INTO_NEW_STACK)) {
            return cards.dealIntoNewStack();
        } else if (instruction.startsWith(CUT_)) {
            int N = Integer.parseInt(instruction.substring("cut ".length()));
            return cards.cut(N);
        } else if (instruction.startsWith(DEAL_WITH_INCREMENT_)) {
            int N = Integer.parseInt(instruction.substring(DEAL_WITH_INCREMENT_.length()));
            return cards.dealWithIncrement(N);
        }
        throw Reject.always("Unsupported operation!");
    }
}
