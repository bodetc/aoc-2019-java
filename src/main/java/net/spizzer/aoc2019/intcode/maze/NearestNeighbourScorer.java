package net.spizzer.aoc2019.intcode.maze;

import net.spizzer.aoc2019.common.Reject;
import net.spizzer.aoc2019.helpers.astar.Scorer;

public class NearestNeighbourScorer implements Scorer<MazeNode> {

    @Override
    public int computeCost(MazeNode from, MazeNode to) {
        Reject.ifDifferent(1, from.getKey().taxiDistance(to.getKey()), "Only nearest neighbours can be measured");
        return 1;
    }

}
