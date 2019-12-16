package net.spizzer.aoc2019.day06;

import net.spizzer.aoc2019.AbstractDay;
import net.spizzer.aoc2019.common.Reject;
import net.spizzer.aoc2019.helpers.tree.Tree;

import java.util.List;

public class Day06 extends AbstractDay<Tree<String>, Integer, Integer> {
    @Override
    public int getDay() {
        return 6;
    }

    @Override
    public Tree<String> parseInput(List<String> lines) {
        Tree<String> tree = new Tree<>();

        lines.forEach(line -> {
            String[] split = line.split("\\)");
            Reject.ifDifferent(2, split.length, "Each orbit must have one parent and one child!");
            tree.addLink(split[0], split[1]);
        });

        return tree;
    }

    @Override
    public Integer solveFirstStar(Tree<String> tree) {
        return tree.getNumberOfIndirectParents();
    }

    @Override
    public Integer solveSecondStar(Tree<String> tree) {
        return tree.getShortestPath("YOU", "SAN");
    }
}
