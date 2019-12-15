package net.spizzer.aoc2019.common;

import java.util.Arrays;

public interface ValueBase<V> {
    V getValue();

    static <V, T extends ValueBase<V>> T fromValue(T[] enumerators, V value) {
        return Arrays.stream(enumerators)
                .filter(instance -> instance.getValue() == value)
                .findAny()
                .orElseThrow();
    }
}
