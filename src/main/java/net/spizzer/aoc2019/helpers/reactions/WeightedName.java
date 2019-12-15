package net.spizzer.aoc2019.helpers.reactions;

import net.spizzer.aoc2019.common.Reject;

class WeightedName {
    final int amount;
    final String name;

    WeightedName(String input) {
        String[] split = input.split(" ");
        Reject.ifDifferent(2, split.length, "Reaction members should have an amount and a name");
        this.amount = Integer.parseInt(split[0]);
        this.name = split[1];
    }
}
