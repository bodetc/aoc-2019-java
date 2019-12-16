package net.spizzer.aoc2019;

import com.google.common.annotations.VisibleForTesting;
import net.spizzer.aoc2019.utils.ParseUtils;

import java.util.List;

public abstract class AbstractDay<INPUT, FIRST, SECOND> {
    public abstract int getDay();

    public abstract INPUT parseInput(List<String> lines);

    public abstract FIRST solveFirstStar(INPUT input);

    public abstract SECOND solveSecondStar(INPUT input);

    protected void runDay() {
        String welcome = String.format("Solutions for Day %d:", getDay());
        System.out.println(welcome);
        System.out.println("*".repeat(welcome.length()));

        FIRST firstStar = solveFirstStar();
        System.out.println("First star: " + firstStar);

        SECOND secondStar = solveSecondStar();
        System.out.println("Second star: " + secondStar);

        System.out.println();
    }

    @VisibleForTesting
    FIRST solveFirstStar() {
        return solveFirstStar(getParsedInput());
    }

    @VisibleForTesting
    SECOND solveSecondStar() {
        return solveSecondStar(getParsedInput());
    }

    private INPUT getParsedInput() {
        String inputPath = String.format("day%02d.txt", getDay());
        List<String> lines = ParseUtils.readLines(inputPath);
        return parseInput(lines);
    }
}
