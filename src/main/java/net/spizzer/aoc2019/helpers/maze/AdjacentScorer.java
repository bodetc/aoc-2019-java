package net.spizzer.aoc2019.helpers.maze;

import net.spizzer.aoc2019.common.Reject;
import net.spizzer.aoc2019.helpers.geometry2d.Point2D;

public class AdjacentScorer<T extends GraphNode<? extends Point2D>> implements Scorer<T> {

    @Override
    public int computeCost(T from, T to) {
        Reject.ifDifferent(1, from.getKey().taxiDistance(to.getKey()), "Only nearest neighbours are valid!");
        return 1;
    }

}
