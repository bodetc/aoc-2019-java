package net.spizzer.aoc2019.helpers.reactions;

import java.util.List;
import java.util.stream.Collectors;

public class ReactionSolver {
    private static final String FUEL = "FUEL";
    private static final String ORE = "ORE";

    private final List<ChemicalReaction> reactions;

    public ReactionSolver(List<String> reactions) {
        this.reactions = reactions.stream()
                .map(ChemicalReaction::new)
                .collect(Collectors.toList());
    }

    public long solveReaction() {
        return solveReaction(ORE, 1);
    }

    private long oreForFuel(long fuelAmount) {
        return solveReaction(ORE, fuelAmount);
    }

    public long fuelForOre(long oreAmount) {
        long lowerBound = oreAmount / oreForFuel(1);
        long upperBound = 2 * lowerBound;

        while (oreForFuel(upperBound) < oreAmount) {
            lowerBound *= 2;
            upperBound *= 2;
        }

        while (lowerBound < upperBound - 1) {
            long fuel = (lowerBound + upperBound) / 2;
            long ore = oreForFuel(fuel);
            if (ore == oreAmount) {
                return fuel;
            } else if (ore < oreAmount) {
                lowerBound = fuel;
            } else {
                upperBound = fuel;
            }
        }
        return lowerBound;
    }

    private long solveReaction(String chemical, long fuelAmount) {
        if (FUEL.equals(chemical)) {
            return fuelAmount;
        }
        return reactions.stream()
                .mapToLong(reaction -> {
                    WeightedName input = reaction.getInput(chemical);
                    if (input == null) {
                        return 0;
                    } else {
                        return input.amount
                                * (long) Math.ceil((double) solveReaction(reaction.output.name, fuelAmount) / (double) reaction.output.amount);
                    }
                })
                .sum();
    }
}
