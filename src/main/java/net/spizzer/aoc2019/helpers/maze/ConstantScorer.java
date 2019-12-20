package net.spizzer.aoc2019.helpers.maze;

import net.spizzer.aoc2019.common.Reject;
import net.spizzer.aoc2019.helpers.geometry2d.Point2D;

public class ConstantScorer<T extends GraphNode<Point2D>> implements Scorer<T> {

    private final boolean checkNearestNeighbour;

    public ConstantScorer(boolean checkNearestNeighbour) {
        this.checkNearestNeighbour = checkNearestNeighbour;
    }

    public ConstantScorer() {
        this(true);
    }

    @Override
    public int computeCost(T from, T to) {
        if (checkNearestNeighbour) {
            Reject.ifDifferent(1, from.getKey().taxiDistance(to.getKey()), "Only nearest neighbours can be measured");
        }
        return 1;
    }

}
