package net.spizzer.aoc2019.day22;

import net.spizzer.aoc2019.common.Reject;

import java.util.List;

class SpaceDeckShuffle {
    private static final String DEAL_INTO_NEW_STACK = "deal into new stack";
    private static final String CUT_ = "cut ";
    private static final String DEAL_WITH_INCREMENT_ = "deal with increment ";


    final SpaceDeckTechnique technique;
    final long amount;

    private SpaceDeckShuffle(SpaceDeckTechnique technique, long amount) {
        this.technique = technique;
        this.amount = amount;
    }

    static SpaceDeck applyOnce(List<String> instructions, long cards) {
        SpaceDeck deck = new SpaceDeck(cards);
        instructions.stream()
                .map(SpaceDeckShuffle::fromString)
                .forEach(deck::apply);
        return deck;
    }

    private static SpaceDeckShuffle fromString(String instruction) {
        if (instruction.equals(DEAL_INTO_NEW_STACK)) {
            return new SpaceDeckShuffle(SpaceDeckTechnique.DEAL, 0);
        } else if (instruction.startsWith(CUT_)) {
            int N = Integer.parseInt(instruction.substring("cut ".length()));
            return new SpaceDeckShuffle(SpaceDeckTechnique.CUT, N);

        } else if (instruction.startsWith(DEAL_WITH_INCREMENT_)) {
            int N = Integer.parseInt(instruction.substring(DEAL_WITH_INCREMENT_.length()));
            return new SpaceDeckShuffle(SpaceDeckTechnique.INCREMENT, N);
        }
        throw Reject.always("Unsupported operation!");
    }
}
