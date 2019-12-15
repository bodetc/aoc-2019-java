package net.spizzer.aoc2019.helpers.reactions;

import net.spizzer.aoc2019.utils.ParseUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ReactionSolverTest {

    static Stream<Arguments> dataProviderSolveReaction() {
        return Stream.of(
                arguments("helpers/reactions/test1.txt", 165),
                arguments("helpers/reactions/test2.txt", 13312),
                arguments("helpers/reactions/test3.txt", 180697),
                arguments("helpers/reactions/test4.txt", 2210736)
        );
    }

    @ParameterizedTest
    @MethodSource("dataProviderSolveReaction")
    void solveReaction(String path, int ore) {
        ReactionSolver solver = new ReactionSolver(ParseUtils.readLines(path));
        long solution = solver.solveReaction();
        assertEquals(ore, solution);
    }

    static Stream<Arguments> dataProviderFuelForOre() {
        return Stream.of(
                arguments("helpers/reactions/test2.txt", 82892753),
                arguments("helpers/reactions/test3.txt", 5586022),
                arguments("helpers/reactions/test4.txt", 460664)
        );
    }

    @ParameterizedTest
    @MethodSource("dataProviderFuelForOre")
    void fuelForOre(String path, int fuel) {
        ReactionSolver solver = new ReactionSolver(ParseUtils.readLines(path));
        long solution = solver.fuelForOre(1000000000000L);
        assertEquals(fuel, solution);
    }
}