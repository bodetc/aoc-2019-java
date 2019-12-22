package net.spizzer.aoc2019.day22;

import net.spizzer.aoc2019.common.Reject;

import java.util.Collections;
import java.util.List;

class SpaceCardsTracker {
    private static final String DEAL_INTO_NEW_STACK = "deal into new stack";
    private static final String CUT_ = "cut ";
    private static final String DEAL_WITH_INCREMENT_ = "deal with increment ";

    private final long length;
    private final boolean forward;

    private SpaceCardsTracker(long length, boolean forward) {
        this.length = length;
        this.forward = forward;
    }

    static long track(List<String> instructions, long length, long position, long loops, boolean forward) {
        SpaceCardsTracker tracker = new SpaceCardsTracker(length, forward);

        if (!forward) {
            Collections.reverse(instructions);
        }

        for (int l = 0; l < loops; l++) {
            for (String instruction : instructions) {
                position = tracker.track(instruction, position);
            }
        }
        return position;
    }

    private long track(String instruction, long position) {
        if (instruction.equals(DEAL_INTO_NEW_STACK)) {
            return dealIntoNewStack(position);
        } else if (instruction.startsWith(CUT_)) {
            int N = Integer.parseInt(instruction.substring("cut ".length()));
            return cut(position, N);
        } else if (instruction.startsWith(DEAL_WITH_INCREMENT_)) {
            int N = Integer.parseInt(instruction.substring(DEAL_WITH_INCREMENT_.length()));
            return dealWithIncrement(position, N);
        }
        throw Reject.always("Unsupported operation!");
    }

    private long dealIntoNewStack(long position) {
        return length - 1 - position;
    }

    private long cut(long position, long N) {
        if (forward) {
            return Math.floorMod(position - N, length);
        } else {
            return Math.floorMod(position + N, length);
        }
    }

    private long dealWithIncrement(long position, long N) {
        if (forward) {
            return (N * position) % length;
        } else {
            // TODO: Make this more efficient
            for (int i = 0; i < length; i++) {
                if (((N * i) % length) == position) {
                    return i;
                }
            }
            throw Reject.always("Could't invert modulo");
        }
    }
}
