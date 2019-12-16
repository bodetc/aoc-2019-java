package net.spizzer.aoc2019.day14;

import net.spizzer.aoc2019.AbstractDay;
import net.spizzer.aoc2019.helpers.reactions.ReactionSolver;

import java.util.List;

public class Day14 extends AbstractDay<ReactionSolver, Long, Long> {
    @Override
    public int getDay() {
        return 14;
    }

    @Override
    public ReactionSolver parseInput(List<String> lines) {
        return new ReactionSolver(lines);
    }

    @Override
    public Long solveFirstStar(ReactionSolver reactionSolver) {
        return reactionSolver.solveReaction();
    }

    @Override
    public Long solveSecondStar(ReactionSolver reactionSolver) {
        return reactionSolver.fuelForOre(1000000000000L);
    }
}
