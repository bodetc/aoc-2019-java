package net.spizzer.aoc2019;

import com.google.common.annotations.VisibleForTesting;
import net.spizzer.aoc2019.utils.ParseUtils;

import java.util.List;

public abstract class AbstractDay<INPUT, OUTPUT> {
    public abstract int getDay();

    public abstract INPUT parseInput(List<String> lines);

    public abstract OUTPUT solveFirstStar(INPUT input);

    public abstract OUTPUT solveSecondStar(INPUT input);

    protected void runDay() {
        INPUT input = getParsedInput();

        String welcome = String.format("Solutions for Day %d:", getDay());
        System.out.println(welcome);
        System.out.println("*".repeat(welcome.length()));

        OUTPUT firstStar = solveFirstStar(input);
        System.out.println("First star: " + firstStar);

        OUTPUT secondStar = solveSecondStar(input);
        System.out.println("Second star: " + secondStar);

        System.out.println();
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
