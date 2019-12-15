package net.spizzer.aoc2019;

import com.google.common.annotations.VisibleForTesting;
import net.spizzer.aoc2019.utils.ParseUtils;

import java.util.List;

public abstract class AbstractDay<INPUT, OUTPUT> {
    public abstract int getDay();

    public abstract INPUT parseInput(List<String> lines);

    public abstract OUTPUT solveFirstStar(INPUT input);

    public abstract OUTPUT solveSecondStar(INPUT input);

    public void runDay() {
        INPUT input = getParsedInput();

        OUTPUT firstStar = solveFirstStar(input);
        System.out.println("Solution for first star: " + firstStar);

        OUTPUT secondStar = solveSecondStar(input);
        System.out.println("Solution for second star: " + secondStar);
    }

    @VisibleForTesting
    OUTPUT solveFirstStar() {
        return solveFirstStar(getParsedInput());
    }

    @VisibleForTesting
    OUTPUT solveSecondStar() {
        return solveSecondStar(getParsedInput());
    }

    private INPUT getParsedInput() {
        String inputPath = String.format("day%02d.txt", getDay());
        List<String> lines = ParseUtils.readLines(inputPath);
        return parseInput(lines);
    }
}
