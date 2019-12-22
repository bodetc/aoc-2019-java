package net.spizzer.aoc2019.day22;

import com.google.common.annotations.VisibleForTesting;
import net.spizzer.aoc2019.common.Reject;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SpaceCards {
    private final int[] deck;
    private final int length;

    private SpaceCards(int[] cards) {
        this.deck = cards;
        this.length = cards.length;
    }

    public static SpaceCards factoryOrder(int length) {
        return new SpaceCards(IntStream.range(0, length).toArray());
    }

    @VisibleForTesting
    static SpaceCards fromArray(int[] cards) {
        return new SpaceCards(Arrays.copyOf(cards, cards.length));
    }

    public SpaceCards dealIntoNewStack() {
        int[] cards = new int[length];
        for (int i = 0; i < length; i++) {
            cards[i] = deck[length - 1 - i];
        }
        return new SpaceCards(cards);
    }

    public SpaceCards cut(int N) {
        int cut = Math.floorMod(N, length);
        int[] cards = new int[length];
        System.arraycopy(deck, cut, cards, 0, length - cut);
        System.arraycopy(deck, 0, cards, length - cut, cut);

        return new SpaceCards(cards);
    }

    public SpaceCards dealWithIncrement(int N) {
        int[] cards = new int[length];
        for (int i = 0; i < length; i++) {
            cards[(N * i) % length] = deck[i];
        }
        return new SpaceCards(cards);
    }

    public int positionOf(int card) {
        for (int i = 0; i < length; i++) {
            if (deck[i] == card) {
                return i;
            }
        }
        throw Reject.always("Card not found!");
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof SpaceCards && equals((SpaceCards) other);
    }

    private boolean equals(SpaceCards other) {
        return length == other.length && Arrays.equals(deck, other.deck);
    }
}
