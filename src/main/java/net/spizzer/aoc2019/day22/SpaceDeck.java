package net.spizzer.aoc2019.day22;

import net.spizzer.aoc2019.utils.MathUtils;

import java.math.BigInteger;
import java.util.stream.LongStream;

import static java.math.BigInteger.*;

class SpaceDeck {
    private BigInteger offset;
    private BigInteger increment;
    private final BigInteger cards;

    SpaceDeck(long cards) {
        this.offset = ZERO;
        this.increment = ONE;
        this.cards = valueOf(cards);
    }

    void apply(SpaceDeckShuffle shuffle) {
        BigInteger amount = valueOf(shuffle.amount);
        switch (shuffle.technique) {
            case DEAL:
                increment = increment.negate().mod(cards);
                offset = offset.add(increment).mod(cards);
                break;
            case CUT:
                offset = offset.add(increment.multiply(amount)).mod(cards);
                break;
            case INCREMENT:
                increment = increment.multiply(inverse(amount)).mod(cards);
                break;
        }
    }

    void iterate(long iterations) {
        BigInteger offset_diff = offset;
        BigInteger increment_mul = increment;
        BigInteger n = valueOf(iterations);

        increment = increment_mul.modPow(n, cards);
        offset = offset_diff
                .multiply(ONE.add(increment.negate()))
                .multiply(inverse(ONE.add(increment_mul.negate())))
                .mod(cards);
    }

    private BigInteger inverse(BigInteger amount) {
        return amount.modPow(cards.add(valueOf(-2)), cards);
    }

    long cardAt(long position) {
        return offset.add(increment.multiply(valueOf(position))).mod(cards).longValueExact();
    }

    long positionOf(long card) {
        return LongStream.range(0, cards.longValueExact())
                .filter(position -> cardAt(position) == card)
                .findAny()
                .orElseThrow();
    }


}
