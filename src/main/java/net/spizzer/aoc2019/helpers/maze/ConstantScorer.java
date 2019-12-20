package net.spizzer.aoc2019.helpers.maze;

public class ConstantScorer<T extends GraphNode> implements Scorer<T> {

    @Override
    public int computeCost(T from, T to) {
        return 1;
    }

}
