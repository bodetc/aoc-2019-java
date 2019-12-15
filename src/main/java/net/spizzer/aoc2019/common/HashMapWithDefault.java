package net.spizzer.aoc2019.common;

import java.util.HashMap;

public class HashMapWithDefault<K, V> extends HashMap<K, V> {
    private final V defaultValue;

    public HashMapWithDefault(V defaultValue) {
        this.defaultValue = defaultValue;
    }


    @Override
    public V get(Object k) {
        return containsKey(k) ? super.get(k) : defaultValue;
    }
}
