package net.spizzer.aoc2019.day22;

import net.spizzer.aoc2019.utils.MathUtils;

import java.util.stream.LongStream;

class SpaceDeck {
    private long offset;
    private long increment;
    private final long cards;

    SpaceDeck(long cards) {
        this.offset = 0;
        this.increment = 1;
        this.cards = cards;
    }

    void apply(SpaceDeckShuffle shuffle) {
        switch (shuffle.technique) {
            case DEAL:
                increment = Math.floorMod(-increment, cards);
                offset = Math.floorMod(offset + increment, cards);
                break;
            case CUT:
                offset = Math.floorMod(offset + increment * shuffle.amount, cards);
                break;
            case INCREMENT:
                long power = Math.floorMod(MathUtils.power(shuffle.amount, cards-2 , cards), cards);
                increment = Math.floorMod(increment * power, cards);
                break;
        }
    }

    long cardAt(long position) {
        return Math.floorMod(offset + increment * position, cards);
    }

    long positionOf(long card) {
        return LongStream.range(0, cards).filter(position -> cardAt(position)==card).findAny().orElseThrow();
    }


}
